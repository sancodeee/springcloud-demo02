package com.wang.controller;

import com.wang.pojo.Dept;
import com.wang.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
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
    @Autowired

    private DeptClientService service = null;

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
         return this.service.queryById(id);
    }

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return this.service.addDept(dept);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return this.service.queryAll();
    }
}
