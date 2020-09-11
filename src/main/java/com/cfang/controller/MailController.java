package com.cfang.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cfang.model.Email;
import com.cfang.model.Result;
import com.cfang.model.SmsDto;
import com.cfang.service.IMailService;
import com.cfang.service.SmsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags ="邮件管理")
@RestController
@RequestMapping("/mail")
@Slf4j
public class MailController {

	@Autowired
	private IMailService mailService;
	@Autowired
	SmsService smsService;

	@PostMapping("send")
	public Result send(Email mail) {
		Entry entry = null;
		try {
			entry = SphU.entry("sendMail");
			mailService.sendQueue(mail);
		} catch (BlockException e){
			//资源访问阻止，被限流或被降级，在此进行相应处理
			log.error("sentinel 限流或降级...");
			e.printStackTrace();
			return  Result.error();
		} catch (Exception e) {
			e.printStackTrace();
			return  Result.error();
		} finally {
			if(null != entry){
				entry.exit();
			}
		}
		return  Result.ok();
	}

	@PostMapping("list")
	public Result list(Email mail) {
		return mailService.listMail(mail);
	}

	@PostMapping("sendSms")
	public Result sendSms(SmsDto dto){
		return smsService.sendSms(dto);
	}
}
