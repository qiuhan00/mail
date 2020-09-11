package com.cfang.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.cfang.entity.OaSms;
import com.cfang.model.Result;
import com.cfang.model.SmsDto;
import com.cfang.repository.MailRepository;
import com.cfang.repository.SmsRepository;
import com.cfang.service.SmsService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author cfang 2020/9/11 10:06
 * @description
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Value("${dx.secretId}")
    String secretId;
    @Value("${dx.secretKey}")
    String secretKey;
    @Value("${dx.sign}")
    String sign;
    @Value("${dx.templateID}")
    String templateID;
    @Value("${dx.appid}")
    String appid;

    @Autowired
    SmsRepository smsRepository;

    @Override
    public Result sendSms(SmsDto dto) {
        OaSms sms = new OaSms().setSendTime(DateUtil.date()).setStatus("0");
        try {
            String mobile = dto.getMobile();
            String code = dto.getCode();
            sms.setMobile(mobile).setCode(code);
            Credential credential = new Credential(secretId, secretKey);
            ClientProfile clientProfile = new ClientProfile();
            SmsClient smsClient = new SmsClient(credential, "ap-guangzhou", clientProfile);
            SendSmsRequest request = new SendSmsRequest();
            request.setSmsSdkAppid(appid);
            request.setSign(sign);
            request.setSessionContext(mobile);
            request.setTemplateID(templateID);
            request.setTemplateParamSet(new String[]{code});
            request.setPhoneNumberSet(new String[]{"+86" + mobile});
            SendSmsResponse response = smsClient.SendSms(request);
            log.info("response:{}", JSON.toJSONString(response));
            if(null != response && "Ok".equals(response.getSendStatusSet()[0].getCode())){
                sms.setStatus("1");
                smsRepository.save(sms);
                return Result.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("sendSms err, msg:{}", e.getMessage());
        }
        smsRepository.save(sms);
        return Result.error();
    }
}
