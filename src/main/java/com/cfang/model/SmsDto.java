package com.cfang.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author cfang 2020/9/11 10:59
 * @description
 */
@Data
@Accessors(chain = true)
public class SmsDto {

    private String mobile;
    private String code;
}
