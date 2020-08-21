package com.demo.cloud.utils;

import com.alibaba.fastjson.JSON;
import com.demo.cloud.ProviderServerApplication;
import com.demo.cloud.domain.Product;
import com.demo.cloud.service.IProviderService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProviderServerApplication.class)
public class JwtUtilsTest {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private IProviderService IProviderService;

    @Test
    public void jwtTest(){
        List<Product> products = IProviderService.queryListById(2L);
        //System.out.println(products);
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(products.get(0)), Map.class);
        String token = jwtUtils.createAccessToken(map);
        //System.out.println(token);

        Claims claims = jwtUtils.parseAccessToken(token);

        Jwt body = Jwts.parser().setSigningKey(jwtUtils.generateKey()).parse(token);
        Object b = body.getBody();
        System.out.println(b);


    }
}