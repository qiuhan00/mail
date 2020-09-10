package com.cfang.service.impl;

import com.cfang.entity.OaEmail;
import com.cfang.model.Email;
import com.cfang.service.IMailService;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MailServiceImplTest {

    @Autowired
    IMailService mailService;

    @Test
    public void send() {
        try {
            Email email = new Email().setEmail(new String[]{"807304054@qq.com"})
                    .setContent("test mail")
                    .setSubject("wwel2");
            mailService.send(email);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void sendHtml() {
    }

    @Test
    public void sendFreemarker() {
        try {
            Email email = new Email().setEmail(new String[]{"807304054@qq.com"})
                    .setContent("test mail")
                    .setSubject("TTL2")
                    .setTemplate("welcome.flt");
            mailService.sendQueue(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}