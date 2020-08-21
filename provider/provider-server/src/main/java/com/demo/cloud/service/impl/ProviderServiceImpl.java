package com.demo.cloud.service.impl;

import com.demo.cloud.domain.Product;
import com.demo.cloud.mapper.ProviderMapper;
import com.demo.cloud.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Author: lzc
 * @Description:
 * @Date: Create in 16:05 2020/7/23
 */
@Service
public class ProviderServiceImpl implements IProviderService {
    @Autowired
    private ProviderMapper providerMapper;
    
    @Override
    public List<Product> queryListById(Long id) {
        Product product = providerMapper.queryById(id);
        return Collections.singletonList(product);
    }
}
