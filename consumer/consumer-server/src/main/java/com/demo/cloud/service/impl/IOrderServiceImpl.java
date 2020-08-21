package com.demo.cloud.service.impl;

import com.demo.cloud.dto.OrderDto;
import com.demo.cloud.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 蓝健
 * @version 1.0
 * @date 2020/7/31 9:37
 */
@Service
public class IOrderServiceImpl implements IOrderService {
    @Override
    public List<OrderDto> queryAll() {
        return null;
    }
}
