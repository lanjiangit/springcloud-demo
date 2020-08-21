package com.demo.cloud.service;

import com.demo.cloud.domain.Product;

import java.util.List;

/**
 * @Author: lzc
 * @Description:
 * @Date: Create in 16:04 2020/7/23
 */
public interface IProviderService {

    List<Product> queryListById(Long id);
}
