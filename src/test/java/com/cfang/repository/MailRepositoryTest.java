package com.cfang.repository;

import com.cfang.entity.OaEmail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MailRepositoryTest {

    @Autowired
    MailRepository mailRepository;

    @Test
    public void selectAll(){
        List<OaEmail> list = mailRepository.findAll();
        list.forEach(System.out :: println);

        OaEmail email = mailRepository.findBySubject("welcome");
        System.out.println(email);
    }
}