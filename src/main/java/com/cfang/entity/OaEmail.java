package com.cfang.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "oa_email")
public class OaEmail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String receiveEmail; // 接收人邮箱(多个逗号分开)
    private String subject; //主题
    private String content; //发送内容
    private String template; //模板
    private Date sendTime; //发送时间
    private String status;
}
