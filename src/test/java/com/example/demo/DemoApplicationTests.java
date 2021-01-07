package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.Apple;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    void test() {
        String itemCodeUrl = "http://ck.sh-farm.com:10003/#/produce?batch_number=123&commodity_code=456&warehouse_id=789&number=100";
        String substring = itemCodeUrl.substring(itemCodeUrl.indexOf("?") + 1);
        String replace = "{"+substring.replace("&", ",").replace("=",":")+"}";
        Map<String,Object> map = JSON.parseObject(replace);
        System.out.println(map);
        System.out.println(map.get("number"));

        String[] split = itemCodeUrl.substring(itemCodeUrl.indexOf("?") + 1).split("&");
        Map<String, Object> objectMap = new HashMap<>();
        for (String s : split) {
            String[] split1 = s.split("=");
            objectMap.put(split1[0], split1[1]);
        }
        System.out.println(objectMap);

        Map<String,Object> m = Maps.newHashMap();
        m.put("a","1");
        m.put("b","2");
        System.out.println(m);
    }

    @Test
    void testJDK() {
        List<Apple> appleList = new ArrayList<>();//存放apple对象集合

        Apple apple1 =  new Apple(1,"苹果1",new BigDecimal("3.25"),10);
        Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
        Apple apple2 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30);
        Apple apple3 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40);

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);

        appleList.stream().forEach(apple -> {
            System.out.println(apple);
        });
    }
}
