package com.shdy.tenant;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description: </br>
 * @author: lait.zhang@gmail.com
 * @date: 3/20/2024 10:41 AM
 * @history: </br>
 * <author>      <time>      <version>    <desc>
 * 修改人姓名      修改时间        版本号       描述
 */
@Configuration
@ComponentScan(basePackages = {"org.dows.log.mysql.mapper", "org.dows.log.mongo", "org.dows.log.mysql.repository",
        "org.dows.log.config", "org.dows.log.biz", "org.dows.log.handler", "org.dows.log.rest.tenant"})
public class LogTenantAutoConfig {
}

