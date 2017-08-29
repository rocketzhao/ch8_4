package com.wisely.ch8_4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * jsp配置类
 *
 * Created by jun.zhao on 2017/8/29.
 */
@Configuration
public class JspConfiguration {
    @Bean
    InternalResourceViewResolver internalResourceViewResolver () {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
