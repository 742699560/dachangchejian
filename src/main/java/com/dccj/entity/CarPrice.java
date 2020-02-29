package com.dccj.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class CarPrice {
    private Integer id;

    private Integer typeId;

    private Integer stationId;

    private String carType;

    private BigDecimal price;

    /**
     * 车身长度从 米
     */
    private BigDecimal heightFrom;

    /**
     * 车身长度至 米
     */
    private BigDecimal heightEnd;

    private Date createTime;

    private Date updateTime;
}