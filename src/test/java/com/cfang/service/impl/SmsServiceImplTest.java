package com.cfang.service.impl;

import com.cfang.model.SmsDto;
import com.cfang.service.SmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author cfang 2020/9/11 10:17
 * @description
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SmsServiceImplTest {

    @Autowired
    SmsService smsService;

    @Test
    public void sendSms(){
        smsService.sendSms(new SmsDto().setMobile("15900665997").setCode("1668"));
    }
}