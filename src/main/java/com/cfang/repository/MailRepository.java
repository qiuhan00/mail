package com.cfang.repository;

import com.cfang.entity.OaEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MailRepository extends JpaRepository<OaEmail, Integer> {

    OaEmail findBySubject(String subject);

    @Query("update OaEmail set status = ?1 where id = ?2")
    @Modifying
    int updateStatus(String status, Long id);

    List<OaEmail> findByStatus(String status);
}
