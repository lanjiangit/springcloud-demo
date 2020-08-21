package com.demo.cloud.utils;


import com.demo.cloud.OrderServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderServerApplication.class)
public class JwtUtilsTest {
    @Autowired
    private JwtUtils jwtUtils;
    
    @Test
    public void jwtTest(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("username","lanCode");
        String token = jwtUtils.createAccessToken(map);
        System.out.println(jwtUtils);
        System.out.println(token);
    }

}