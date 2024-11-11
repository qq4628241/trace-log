package com.scott.trace.config;

import feign.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class FeignConfiguration {
    /**
     * feign 日志级别 默认 NONE, BASIC, HEADERS, FULL;
     * 配合logger的配置使用
     * log4j2.xml
     * <configuration>
     *     <logger name="com.example.feignclient" level="DEBUG" />
     * </configuration>
     * @param logLevel
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel(@Value("${feign.log.level:NONE}") String logLevel) {
        Logger.Level level;
        try{
            level = Logger.Level.valueOf(logLevel);
        }catch (Exception e){
            level = Logger.Level.NONE;
        }
        return level;
    }


}
