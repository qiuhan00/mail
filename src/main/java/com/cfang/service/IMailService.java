package com.cfang.service;

import com.cfang.entity.OaEmail;
import com.cfang.model.Email;
import com.cfang.model.Result;

import java.util.List;

public interface IMailService {

    void send(Email mail) throws Exception;

    void sendFreemarker(Email mail) throws Exception;

    void sendQueue(Email email) throws Exception;

    Result listMail(Email mail);

    List<OaEmail> findByStatus(String status) throws Exception;

    int updateStatus(Long id, String status);
}
