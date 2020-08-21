package com.demo.cloud.mapper;

import com.demo.cloud.domain.Product;

/**
 * @Author: lzc
 * @Description:
 * @Date: Create in 17:19 2020/8/3
 */
public interface ProviderMapper {
    
    Product queryById(Long id);
}
