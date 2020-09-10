package com.cfang.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cfang.common.queue.MailQueue;
import com.cfang.converter.MailConverter;
import com.cfang.entity.OaEmail;
import com.cfang.model.Email;
import com.cfang.model.Result;
import com.cfang.repository.MailRepository;
import com.cfang.service.IMailService;
import com.google.common.collect.Lists;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MailServiceImpl implements IMailService {

    @Value("${spring.mail.username}")
    public String userName;//发送者
    @Value("${server.path}")
    public String path;//发送者
    @Value("${mail.default.template}")
    public String defaultTemplate;//默认模板

    @Autowired
    MailRepository mailRepository;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    Configuration configuration;

    @Override
    public void send(Email mail) throws Exception {
        log.info("发送邮件：{}",mail.getContent());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(userName);
        message.setTo(mail.getEmail());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        mailSender.send(message);
        OaEmail oaEmail = MailConverter.MAIL_CONVERTER.dto2domain(mail);
        mailRepository.save(oaEmail);
    }

    @Override
    public void sendFreemarker(Email mail) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(userName,"qiuhan"); //自定义发件人名称
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());
        Map<String, Object> model = new HashMap<>();
        model.put("mail", mail);
        model.put("path", path);
        if(StrUtil.isBlank(mail.getTemplate())){
            mail.setTemplate(defaultTemplate);
        }
        Template template = configuration.getTemplate(mail.getTemplate());
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(text, true);
        mailSender.send(mimeMessage);
    }

    @Override
    public void sendQueue(Email email) throws Exception {
        OaEmail oaEmail = MailConverter.MAIL_CONVERTER.dto2domain(email);
        mailRepository.save(oaEmail);
        email.setId(oaEmail.getId());
        MailQueue.getInstance().produce(email);
    }

    @Override
    public Result listMail(Email mail) {
        List<OaEmail> list = mailRepository.findAll();
        if(null != mail && StrUtil.isNotBlank(mail.getSubject())){
            list.removeIf(item -> !mail.getSubject().equals(item.getSubject()));
        }
        return Result.ok(list);
    }

    @Override
    public List<OaEmail> findByStatus(String status) throws Exception {
        return mailRepository.findByStatus(status);
    }

    @Override
    @Transactional
    public int updateStatus(Long id, String status) {
        return mailRepository.updateStatus(status, id);
    }

}
