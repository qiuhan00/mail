package com.cfang.repository;

import com.cfang.entity.OaEmail;
import com.cfang.entity.OaSms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SmsRepository extends JpaRepository<OaSms, Integer> {


}
