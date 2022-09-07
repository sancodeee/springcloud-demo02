package com.wang.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true) //链式写法
public class Dept implements Serializable { //序列化操作
    private Long deptno;//主键
    private String dname; //这个数据库存在于哪个数据库字段
    private String db_source; //所在数据库名称，微服务一个服务对应一个数据库，同一个信息可能所在的数据库不同

    public Dept(String dname) {
        this.dname = dname;
    }

}
