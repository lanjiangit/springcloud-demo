package com.demo.cloud.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 蓝健
 * @version 1.0
 * @date 2020/7/29 11:20
 */
@Getter
@Setter
@ToString
public class OrderDto implements Serializable {
    
    private String appName;
    
    private String appVersion;
}
