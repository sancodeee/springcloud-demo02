package com.wang.controller;

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

    @Autowired
    private DiscoveryClient client;

    @PostMapping("/dept/add")
    public boolean addDept(@RequestBody Dept dept){
        return  deptService.addDept(dept);
    }
    @GetMapping ("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return deptService.queryById(id);
    }
    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }

    //注册进来的微服务获取一些消息
    @GetMapping("/dept/discovery")
    public Object discovery(){
        List<String> services = client.getServices();//获取微服务列表清单
        System.out.println("discovery=>services:" + services);

        //通过微服务id得到一个具体的信息
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");

        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost()+"\t"
                    +instance.getPort()+"\t"
                    +instance.getUri()+"\t"
                    +instance.getServiceId()
            );
        }
        return this.client;
    }

}
