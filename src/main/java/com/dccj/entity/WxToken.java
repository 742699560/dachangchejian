package com.dccj.entity;

import java.util.Date;
import lombok.Data;

@Data
public class WxToken {
    private Integer id;

    private String accessToken;

    private String jsapiTicket;

    private Date tokenTime;

    private Date jspaiTime;

    private String accessTokenUser;

    private Date tokenUserTime;

    private String refreshTokenUser;

    private Date refreshUserTime;
}