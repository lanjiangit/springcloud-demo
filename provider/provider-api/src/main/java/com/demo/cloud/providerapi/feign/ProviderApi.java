package com.demo.cloud.providerapi.feign;

import com.demo.cloud.domain.Product;
import com.demo.cloud.providerapi.fallback.ProviderApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author: lzc
 * @Description:
 * @Date: Create in 15:20 2020/7/23
 */
@FeignClient(name = "product-server",path = "/product",fallback = ProviderApiFallback.class)
public interface ProviderApi {

    @GetMapping(value = "/queryListById/{id}", produces = {"application/json; charset=UTF-8"})
    List<Product> queryListById(@PathVariable("id") Long id);
}
