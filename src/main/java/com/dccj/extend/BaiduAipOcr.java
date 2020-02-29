package com.dccj.extend;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

public class BaiduAipOcr {
    //设置APPID/AK/SK
    private static final String APP_ID = "17694443";
    private static final String API_KEY = "DAKss5qDtGgYjNjpydwK2gc2";
    private static final String SECRET_KEY = "BzGkK674Hjg62zKWQT5oqtl2DXSnvpvg";
    private static AipOcr client;
    private static AipOcr getInstance(){
        if(client == null)
            client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        return client;
    }

    public static JSONObject readCarImgByBaidu(String file, HashMap<String,String> map){
        // 初始化一个AipOcr
        AipOcr client = getInstance();
        JSONObject res = client.basicGeneral(file, map);
        return res;
    }
}