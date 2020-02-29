package com.dccj.entity;

import java.util.Date;
import lombok.Data;

@Data
public class CarOrderStep {
    private Integer id;

    private Integer orderId;

    private Integer step;

    private String name;

    private Integer status;

    private Integer userId;

    private Integer sysUserId;

    private Date createTime;

    private Date updateTime;
}