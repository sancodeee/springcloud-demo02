package com.wang.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

    //配置负载均衡实现Ribbon
    @Bean
    @LoadBalanced //Ribbon
    public RestTemplate getRestTemplate() {  //需要谁返回谁，即注入
        return new RestTemplate(); //注入Restplate对象
    }



}
