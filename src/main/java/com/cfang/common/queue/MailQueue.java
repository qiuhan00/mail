package com.cfang.common.queue;

import com.cfang.model.Email;
import com.google.common.collect.Queues;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class MailQueue {

    private static final int queue_size = 1000;

    private static LinkedBlockingQueue<Email> emailQueue = Queues.newLinkedBlockingQueue(queue_size);

    private MailQueue(){}

    private static class SingletonHolder{
        private static MailQueue queue = new MailQueue();
    }

    public static MailQueue getInstance(){
        return SingletonHolder.queue;
    }

    public void produce(Email email) throws InterruptedException {
        emailQueue.put(email);
    }

    public Email consume() throws InterruptedException {
        return emailQueue.take();
    }

    public int getSize(){
        return emailQueue.size();
    }
}
