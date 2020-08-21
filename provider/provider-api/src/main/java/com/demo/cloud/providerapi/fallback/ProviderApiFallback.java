package com.demo.cloud.providerapi.fallback;

import com.demo.cloud.domain.Product;
import com.demo.cloud.providerapi.feign.ProviderApi;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author 蓝健
 * @version 1.0
 * @date 2020/7/27 16:30
 */
@Component("productApiFallback")
public class ProviderApiFallback implements ProviderApi {
    @Override
    public List<Product> queryListById(Long id) {
        System.out.println("服务调用超时");
        return Arrays.asList(new Product());
    }
}
