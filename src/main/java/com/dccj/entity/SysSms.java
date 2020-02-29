package com.dccj.entity;

import java.util.Date;
import lombok.Data;

@Data
public class SysSms {
    private Integer id;

    private String phone;

    private String time;

    private String code;

    private Date createTime;

    private Date updateTime;
}