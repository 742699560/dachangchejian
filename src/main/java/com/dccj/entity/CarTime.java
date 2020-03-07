package com.dccj.entity;

import java.util.Date;

import lombok.Data;

@Data
public class CarTime {
    private Integer id;

    private Integer stationId;

    /**
     * 时间段
     */
    private String timeSub;

    /**
     * 可预约人数
     */
    private Integer timeNum;

    private Date createTime;

    private Date updateTime;

    private Integer useNum;
}