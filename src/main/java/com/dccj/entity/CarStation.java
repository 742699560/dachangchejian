package com.dccj.entity;

import java.util.Date;
import lombok.Data;

@Data
public class CarStation {
    private Integer id;

    /**
    * 车检所名称
    */
    private String name;

    /**
    * 车检所图标
    */
    private String img;

    /**
    * 描述
    */
    private String description;

    /**
    * 法人
    */
    private String legal;

    /**
    * 联系人
    */
    private String contats;

    /**
    * 电话
    */
    private String phone;

    /**
    * 地址
    */
    private String address;

    /**
    * 经度
    */
    private String lat;

    /**
    * 纬度
    */
    private String lng;

    private Date createTime;

    private Date updateTime;
}