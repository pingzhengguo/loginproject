package com.pzg.code.loginproject.shiro.config;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: shiro 生命周期处理器配置
 * @ModifiedBy:
 */
@Configuration
public class ShiroLifecycleBeanPostProcessorConfig {

    /**
     * shiro 周命周期处理器
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
