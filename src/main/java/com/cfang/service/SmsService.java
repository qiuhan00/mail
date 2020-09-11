package com.cfang.service;

import com.cfang.model.Result;
import com.cfang.model.SmsDto;

/**
 * @author cfang 2020/9/11 10:04
 * @description
 */
public interface SmsService {

    Result sendSms(SmsDto dto);
}
