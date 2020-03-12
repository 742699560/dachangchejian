package com.dccj.entity;

import java.util.Date;
import lombok.Data;

@Data
public class CarCenter {
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 车检人员姓名
     */
    private String username;

    /**
     * 车牌号
     */
    private String carNum;

    /**
     * 品牌型号
     */
    private String carBrandNum;

    /**
     * 发证日期
     */
    private String carBeginDate;

    /**
     * 使用性质
     */
    private String carUse;

    /**
     * 发动机号码
     */
    private String carDriveNum;

    /**
     * 所有人
     */
    private String carPeople;

    /**
     * 住址
     */
    private String carAddress;

    /**
     * 注册日期
     */
    private String carRegNum;

    /**
     * 车辆识别代号
     */
    private String carIdNum;

    /**
     * 车辆类型
     */
    private String carType;

    /**
     * 整备质量
     */
    private String carWeight;

    /**
     * 准牵引总质量
     */
    private String carTowWeight;

    /**
     * 核定载人数
     */
    private String carLoadPeople;

    /**
     * 外廓尺寸
     */
    private String carSize;

    /**
     * 总质量
     */
    private String carTotalWeight;

    /**
     * 档案编号
     */
    private String carRecordNum;

    /**
     * 检验记录
     */
    private String carCheckLog;

    /**
     * 燃油类型
     */
    private String carFuel;

    /**
     * 核定载质量
     */
    private String carLoadApproved;

    /**
     * 备注
     */
    private String carRemark;

    /**
     * 行驶证正面
     */
    private String carImgFront;

    /**
     * 行驶证背面
     */
    private String carImgBack;

    /**
     * 变速箱类型 1:手动 2:自动 3手自一体
     */
    private String carAtType;

    /**
     * 车身颜色 1:A-白 2:B-灰 3:C-黄 4:D-粉 5:E-红 6:F-紫 7:G-绿 8:H-蓝 9:I-棕 1:J-黑 1:Z-其他
     */
    private String carColor;

    /**
     * 驱动形式 1:前驱 2:后驱 3:四驱
     */
    private String carDriveType;

    /**
     * 排量
     */
    private String carOut;

    private Date createTime;

    private Date updateTime;

    /**
     * 附件图片1
     */
    private String carAttachment1;

    /**
     * 附件图片2
     */
    private String carAttachment2;

    /**
     * 附件图片3
     */
    private String carAttachment3;
}