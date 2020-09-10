package com.cfang;

import com.cfang.common.queue.MailQueue;
import com.cfang.converter.MailConverter;
import com.cfang.entity.OaEmail;
import com.cfang.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@Slf4j
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@EnableScheduling
public class MailApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }

    @Autowired
    IMailService mailService;

    @Override
    public void run(String... args) throws Exception {
        List<OaEmail> list = mailService.findByStatus("0");
        list.forEach(item -> {
            try {
                MailQueue.getInstance().produce(MailConverter.MAIL_CONVERTER.domain2dto(item));
            } catch (InterruptedException e) {
                log.error("类型转换异常，msg:{}", e.getMessage());
            }
        });
    }
}
