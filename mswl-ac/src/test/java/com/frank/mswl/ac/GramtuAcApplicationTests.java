package com.frank.mswl.ac;

import com.frank.mswl.core.utils.Md5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GramtuAcApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void initPasswd() {
        System.out.println(Md5Util.MD5Encode("123456", "utf-8"));
    }

}
