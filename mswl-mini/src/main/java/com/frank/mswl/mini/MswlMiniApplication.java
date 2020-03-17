package com.frank.mswl.mini;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小程序端应用入口.
 *
 * @author 张孝党 2019/12/25.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/12/25. 张孝党 创建.
 */
@Slf4j
@EnableAsync
@RestController
@EnableScheduling
@CrossOrigin(origins = "*")
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("com.frank.mswl.mini.*.")
@SpringBootApplication(scanBasePackages = {"com.frank.mswl.mini", "com.frank.mswl.core"})
public class MswlMiniApplication {

    public static void main(String[] args) {
        SpringApplication.run(MswlMiniApplication.class, args);
    }
}
