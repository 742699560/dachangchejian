package com.dccj.wx;

import java.io.InputStream;

public class MyConfig extends WXPayConfig {

    public MyConfig(String wxPayAppId,String wxPayMchId,String wxPaySecretKey){
        this.wxPayAppId = wxPayAppId;
        this.wxPayMchId = wxPayMchId;
        this.wxPaySecretKey = wxPaySecretKey;
    }

    private String wxPayAppId;

    private String wxPayMchId;

    private String wxPaySecretKey;

    @Override
    String getAppID() {
        return wxPayAppId;
    }

    @Override
    String getMchID() {
        return wxPayMchId;
    }

    @Override
    public String getKey() {
        return wxPaySecretKey;
    }

    @Override
    InputStream getCertStream() {
        return null;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }
            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;
    }

    @Override
    public boolean shouldAutoReport(){
        return false;
    }
}
