package com.cfang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;

@Data
@Accessors(chain = true)
public class Email implements Serializable {

    //必填参数
    private String[] email;//接收方邮件
    private String subject;//主题
    private String content;//邮件内容
    //选填
    private String template;//模板
    private HashMap<String, String> kvMap;// 自定义参数
    private Long id;
}
