package com.demo.cloud.config;

import com.demo.cloud.dto.OrderDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 蓝健
 * @version 1.0
 * @date 2020/7/29 9:57
 */
@Configuration
public class TestConfig {
    @Value("${application.order.name}")
    private String appName;
    @Value("${application.order.version}")
    private String appVersion;
    
    @Bean
    public OrderDto orderDto(){
        OrderDto orderDto = new OrderDto();
        orderDto.setAppName(this.appName);
        orderDto.setAppVersion(this.appVersion);
        return orderDto;
    }
}
