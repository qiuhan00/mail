package com.cfang.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * @author cfang 2020/9/11 11:03
 * @description
 */
@Data
@Accessors(chain = true)
@Table(name = "oa_sms")
@Entity
public class OaSms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mobile;
    private String code;
    private Date sendTime; //发送时间
    private String status;
}



