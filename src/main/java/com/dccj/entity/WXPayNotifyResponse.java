package com.dccj.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "xml")
public class WXPayNotifyResponse implements Serializable {

    private String returnCode;
    private String returnMsg;

    public String getReturnCode() {
        return returnCode;
    }

    @XmlElement(name = "return_code")
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    @XmlElement(name = "return_msg")
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

}
