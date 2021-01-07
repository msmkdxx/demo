package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
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
        System.out.println(map.get("commodity_code"));
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

    }
}
