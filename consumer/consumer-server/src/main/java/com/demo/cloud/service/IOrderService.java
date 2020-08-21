package com.demo.cloud.service;

import com.demo.cloud.dto.OrderDto;

import java.util.List;

/**
 * @Author: lzc
 * @Description:
 * @Date: Create in 9:36 2020/7/31
 */
public interface IOrderService {
    
    List<OrderDto> queryAll();
}
