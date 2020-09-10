package com.cfang.common.queue;

import com.cfang.model.Email;
import com.cfang.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class ConsumerQueue {

    @Autowired
    IMailService mailService;

    ExecutorService e ;

    @PostConstruct
    public void startThread() {
//        e = Executors.newFixedThreadPool(2);// 两个大小的固定线程池
//        e.submit(new PollMail(mailService));
//        e.submit(new PollMail(mailService));
        e = Executors.newSingleThreadExecutor();
        e.submit(new PollMail(mailService));
    }

    class PollMail implements Runnable {
        IMailService mailService;

        public PollMail(IMailService mailService) {
            this.mailService = mailService;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Email mail = MailQueue.getInstance().consume();
                    if (null != mail) {
                        log.info("剩余邮件总数:{}",MailQueue.getInstance().getSize());
                        mailService.sendFreemarker(mail);
                        mailService.updateStatus(mail.getId(), "1");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("发送邮件异常,msg:{}", e.getMessage());
//                    mailService.updateStatus(mail.getId(), "2");
                }
            }
        }
    }

    @PreDestroy
    public void stopThread() {
        log.info("destroy");
        e.shutdown();
    }
}
