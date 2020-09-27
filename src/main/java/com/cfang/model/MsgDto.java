package com.cfang.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author cfang 2020/9/14 14:40
 * @description
 */
@Data
@Accessors(chain = true)
public class MsgDto {

    private String type; //发送类型，1-邮件，2-短信，3-钉钉
    private String json; //发送json格式数据
}
