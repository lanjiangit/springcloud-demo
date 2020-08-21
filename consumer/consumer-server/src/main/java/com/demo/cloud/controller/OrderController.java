package com.demo.cloud.controller;

import com.demo.cloud.domain.Product;
import com.demo.cloud.dto.OrderDto;
import com.demo.cloud.providerapi.feign.ProviderApi;
import com.demo.cloud.rest.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @Author: lzc
 * @Description:
 * @Date: Create in 16:43 2020/7/23
 */
@RestController
@RequestMapping("/${application.order.name}")
public class OrderController {
    private final static Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private ProviderApi providerApi;
    @Autowired
    private OrderDto orderDto;

    @GetMapping(value = "/${application.order.version}/queryProductListById/{id}", produces={"application/json; charset=UTF-8"})
    public BaseResponse queryProductListById(@PathVariable("id") Long id){
        //List<Product> list = productApi.queryListById(id);
        //System.out.println(list);
        System.out.println(orderDto);
        ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest req = sra.getRequest();
        System.out.println(req.getRequestURL().toString());
        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        List<Product> products = providerApi.queryListById(id);
        return BaseResponse.success(products);
    }
}
