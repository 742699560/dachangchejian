package com.dccj.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class CarOrder {
    private Integer id;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 车检所ID
     */
    private Integer stationId;

    /**
     * 车辆ID
     */
    private Integer carId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 备注
     */
    private String comment;

    /**
     * 姓名
     */
    private String username;

    /**
     * 车牌号
     */
    private String carNum;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 预约日期
     */
    private String times;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 支付金额
     */
    private BigDecimal payAmount;

    /**
     * 预约时间
     */
    private Integer timeId;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 支付数据包
     */
    private String payData;

    /**
     * 订单状态
     */
    private Integer status;

    private String statusStr;

    /**
     * 1: 现场 2: 预约 3:其他
     */
    private Integer type;

    /**
     * 1:未打印 1:已打印
     */
    private Integer printStatus;

    /**
     * 环保录入人员ID
     */
    private Integer inputUserId;

    /**
     * 环保录入时间
     */
    private Date inputDate;

    /**
     * 环保录入状态 1:未录入 2:录入中 3:录入结束
     */
    private Integer inputStatus;

    /**
     * 安检录入人员ID
     */
    private Integer inputAjId;

    /**
     * 安检录入时间
     */
    private Date intputAjDate;

    /**
     * 安检录入状态 1:未录入 2:录入中 3:录入结束
     */
    private Integer inputAjStatus;

    /**
     * 附件图片1
     */
    private String attachment1;

    /**
     * 附件图片2
     */
    private String attachment2;

    /**
     * 附件图片3
     */
    private String attachment3;

    /**
     * 打印单号
     */
    private String printNumber;

    private Date createTime;

    private Date updateTime;
}