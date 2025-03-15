package org.dows.log;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author
 * @description
 * @date 2024年4月20日 上午10:44:44
 */
@EnableSpringUtil
@EnableCaching
@SpringBootApplication(scanBasePackages = {"com.shdy.admin", "org.dows.log"})
public class LogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class, args);
    }
}

