package com.dccj.entity;

import java.util.Date;

public class UserCar {
    private Integer id;

    /**
     * wx open_id
     */
    private String openId;

    /**
     * 预约单号
     */
    private String number;

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
     * 付费金额
     */
    private String prices;

    /**
     * 预约时间端
     */
    private String timeSub;

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态  1:未支付 2:已支付 3:审核通过 4:已完成
     */
    private Integer status;

    /**
     * 排量
     */
    private String carOut;

    /**
     * 公里数
     */
    private String carMileage;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 支付数据包
     */
    private String payData;

    /**
     * 1: 现场 2: 预约
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getTimeSub() {
        return timeSub;
    }

    public void setTimeSub(String timeSub) {
        this.timeSub = timeSub;
    }

    public String getCarBrandNum() {
        return carBrandNum;
    }

    public void setCarBrandNum(String carBrandNum) {
        this.carBrandNum = carBrandNum;
    }

    public String getCarBeginDate() {
        return carBeginDate;
    }

    public void setCarBeginDate(String carBeginDate) {
        this.carBeginDate = carBeginDate;
    }

    public String getCarUse() {
        return carUse;
    }

    public void setCarUse(String carUse) {
        this.carUse = carUse;
    }

    public String getCarDriveNum() {
        return carDriveNum;
    }

    public void setCarDriveNum(String carDriveNum) {
        this.carDriveNum = carDriveNum;
    }

    public String getCarPeople() {
        return carPeople;
    }

    public void setCarPeople(String carPeople) {
        this.carPeople = carPeople;
    }

    public String getCarAddress() {
        return carAddress;
    }

    public void setCarAddress(String carAddress) {
        this.carAddress = carAddress;
    }

    public String getCarRegNum() {
        return carRegNum;
    }

    public void setCarRegNum(String carRegNum) {
        this.carRegNum = carRegNum;
    }

    public String getCarIdNum() {
        return carIdNum;
    }

    public void setCarIdNum(String carIdNum) {
        this.carIdNum = carIdNum;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarWeight() {
        return carWeight;
    }

    public void setCarWeight(String carWeight) {
        this.carWeight = carWeight;
    }

    public String getCarTowWeight() {
        return carTowWeight;
    }

    public void setCarTowWeight(String carTowWeight) {
        this.carTowWeight = carTowWeight;
    }

    public String getCarLoadPeople() {
        return carLoadPeople;
    }

    public void setCarLoadPeople(String carLoadPeople) {
        this.carLoadPeople = carLoadPeople;
    }

    public String getCarSize() {
        return carSize;
    }

    public void setCarSize(String carSize) {
        this.carSize = carSize;
    }

    public String getCarTotalWeight() {
        return carTotalWeight;
    }

    public void setCarTotalWeight(String carTotalWeight) {
        this.carTotalWeight = carTotalWeight;
    }

    public String getCarRecordNum() {
        return carRecordNum;
    }

    public void setCarRecordNum(String carRecordNum) {
        this.carRecordNum = carRecordNum;
    }

    public String getCarCheckLog() {
        return carCheckLog;
    }

    public void setCarCheckLog(String carCheckLog) {
        this.carCheckLog = carCheckLog;
    }

    public String getCarFuel() {
        return carFuel;
    }

    public void setCarFuel(String carFuel) {
        this.carFuel = carFuel;
    }

    public String getCarLoadApproved() {
        return carLoadApproved;
    }

    public void setCarLoadApproved(String carLoadApproved) {
        this.carLoadApproved = carLoadApproved;
    }

    public String getCarRemark() {
        return carRemark;
    }

    public void setCarRemark(String carRemark) {
        this.carRemark = carRemark;
    }

    public String getCarImgFront() {
        return carImgFront;
    }

    public void setCarImgFront(String carImgFront) {
        this.carImgFront = carImgFront;
    }

    public String getCarImgBack() {
        return carImgBack;
    }

    public void setCarImgBack(String carImgBack) {
        this.carImgBack = carImgBack;
    }

    public String getCarAtType() {
        return carAtType;
    }

    public void setCarAtType(String carAtType) {
        this.carAtType = carAtType;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarDriveType() {
        return carDriveType;
    }

    public void setCarDriveType(String carDriveType) {
        this.carDriveType = carDriveType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCarOut() {
        return carOut;
    }

    public void setCarOut(String carOut) {
        this.carOut = carOut;
    }

    public String getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(String carMileage) {
        this.carMileage = carMileage;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayData() {
        return payData;
    }

    public void setPayData(String payData) {
        this.payData = payData;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPrintStatus() {
        return printStatus;
    }

    public void setPrintStatus(Integer printStatus) {
        this.printStatus = printStatus;
    }

    public Integer getInputUserId() {
        return inputUserId;
    }

    public void setInputUserId(Integer inputUserId) {
        this.inputUserId = inputUserId;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public Integer getInputStatus() {
        return inputStatus;
    }

    public void setInputStatus(Integer inputStatus) {
        this.inputStatus = inputStatus;
    }

    public Integer getInputAjId() {
        return inputAjId;
    }

    public void setInputAjId(Integer inputAjId) {
        this.inputAjId = inputAjId;
    }

    public Date getIntputAjDate() {
        return intputAjDate;
    }

    public void setIntputAjDate(Date intputAjDate) {
        this.intputAjDate = intputAjDate;
    }

    public Integer getInputAjStatus() {
        return inputAjStatus;
    }

    public void setInputAjStatus(Integer inputAjStatus) {
        this.inputAjStatus = inputAjStatus;
    }

    public String getAttachment1() {
        return attachment1;
    }

    public void setAttachment1(String attachment1) {
        this.attachment1 = attachment1;
    }

    public String getAttachment2() {
        return attachment2;
    }

    public void setAttachment2(String attachment2) {
        this.attachment2 = attachment2;
    }

    public String getAttachment3() {
        return attachment3;
    }

    public void setAttachment3(String attachment3) {
        this.attachment3 = attachment3;
    }
}