package com.wang.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wang.pojo.Dept;
import com.wang.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//提供Restful服务
@RestController
public class DeptController {

    //controller层调service层
    @Autowired
    private DeptService deptService;

    @HystrixCommand(fallbackMethod = "hystrixGet")
    @GetMapping("/dept/get/{id}")
    public Dept get (@PathVariable("id") Long id){
        Dept dept = deptService.queryById(id);
        if(dept == null){
            throw new RuntimeException("id:"+id+",不存在该用户");
        }
        return dept;
    }

    //备选方法
    public Dept hystrixGet(@PathVariable("id")Long id){
        return new Dept()
                .setDeptno(id)
                .setDname("id:"+id+",没有对应的信息,null--Hystrix")
                .setDb_source("no this database in mysql");
    }



}
