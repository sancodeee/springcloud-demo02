package com.wang.controller;

import com.wang.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {
    //消费者，不应该有service层
    //RestTemplate供我们直接调用，注册到spring中即可
    //消费者通过url远程从提供者拿数据，实现服务消费者和服务提供者解耦
    @Autowired
    private RestTemplate restTemplate;
//每次请求都从固定的url请求服务，所以可以写死
//    private static final String REST_URL_PREFIX="http://localhost:8001";
//    做负载均衡ribbon应该通过服务名去访问
    private static final String REST_URL_PREFIX="http://SPRINGCLOUD-PROVIDER-DEPT";
    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);//get请求返回对象
    }


    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);//post形式返回对象
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);//get请求返回对象
    }
}
