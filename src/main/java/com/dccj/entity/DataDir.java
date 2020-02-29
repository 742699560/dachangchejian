package com.dccj.entity;

import java.util.Date;
import lombok.Data;

@Data
public class DataDir {
    private Integer id;

    private String value;

    private String name;

    private String type;

    private Date createTime;

    private Date updateTime;
}