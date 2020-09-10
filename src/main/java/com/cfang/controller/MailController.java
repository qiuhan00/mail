package com.cfang.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.cfang.model.Email;
import com.cfang.model.Result;
import com.cfang.service.IMailService;
import com.google.common.base.Splitter;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Spliterators;

@Api(tags ="邮件管理")
@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private IMailService mailService;

	@PostMapping("send")
	public Result send(Email mail) {
		Entry entry = null;
		try {
			entry = SphU.entry("sendMail");
			mailService.sendQueue(mail);
		} catch (Exception e) {
			e.printStackTrace();
			return  Result.error();
		} finally {
			if(null != entry){
				entry.exit();
			}
		}
		return  Result.ok();
	}

	@PostMapping("list")
	public Result list(Email mail) {
		return mailService.listMail(mail);
	}

}
