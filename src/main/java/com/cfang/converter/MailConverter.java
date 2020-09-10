package com.cfang.converter;

import com.cfang.entity.OaEmail;
import com.cfang.model.Email;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MailConverter {

    MailConverter MAIL_CONVERTER = Mappers.getMapper(MailConverter.class);

    @Mappings({
            @Mapping(target = "receiveEmail", expression = "java(cn.hutool.core.util.ArrayUtil.join(email.getEmail(), \",\"))"),
            @Mapping(source = "subject", target = "subject"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "template", target = "template"),
            @Mapping(target = "sendTime", expression = "java(cn.hutool.core.date.DateUtil.date())"),
            @Mapping(target = "status", constant = "0")
    })
    OaEmail dto2domain(Email email);

    @Mappings({
            @Mapping(target = "email", expression = "java(cn.hutool.core.util.StrUtil.split(email.getReceiveEmail(), \",\"))"),
            @Mapping(source = "subject", target = "subject"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "template", target = "template"),
            @Mapping(source = "id", target = "id")
    })
    Email domain2dto(OaEmail email);
}
