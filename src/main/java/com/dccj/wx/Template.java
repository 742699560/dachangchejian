package com.dccj.wx;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Template {
    
    private static Logger log = LoggerFactory.getLogger(Template.class);

       /**
        * 发送模板消息
        * appId 公众账号的唯一标识
        * appSecret 公众账号的密钥
        * openId 用户标识
     * @return 
        */
       public TTResult send_template_message(String appId, String appSecret, String openId) {
    //因为我申请的模板是需要填写当前时间戳的，所以在这里我获取了当前的时间
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-HH-MM");
          String format = simpleDateFormat.format(new Date());
          Token token = CommonUtil.getToken(appId, appSecret);//这里要注意，如果你是申请的正式公众号的话，获取token的时候，一定要在后台加上你的ip，不然获取token的时候会报错
          String access_token = token.getAccessToken();
          String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
          String templateId = "填写你所使用的模板id";
          String goUrl = "填写接收模板消息之后，你想要跳转的url页面。";

          Data_style first = new Data_style();
          Data_style keyword1 = new Data_style();
          Data_style keyword2 = new Data_style();
          Data_style remark = new Data_style();

          NewOrdersTemplate temp = new NewOrdersTemplate();
          Data data = new Data();

          first.setValue(format);
          first.setColor("#173177");

          keyword1.setValue("您申请的审核已通过，请到PC端浏览器输入以下链接进行管理后台的设置：(这些都是自定义内容)"
                  + "自定义内容");
          keyword1.setColor("#173177");

          keyword2.setValue(format);
          keyword2.setColor("#173177");

          remark.setValue("");
          remark.setColor("#173177");

          data.setFirst(first);
          data.setKeyword1(keyword1);
          data.setKeyword2(keyword2);
          data.setRemark(remark);

          temp.setTouser(openId);
          temp.setTemplate_id(templateId);
          temp.setUrl(goUrl);
          temp.setTopcolor("#173177");
          temp.setData(data);

          String jsonString = JSONObject.fromObject(temp).toString().replace("day", "Day");
          JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonString);
          System.out.println(jsonObject);
          int result = 0;
          if (null != jsonObject) {  
             if (0 != jsonObject.getInt("errcode")) {  
                result = jsonObject.getInt("errcode");  
                log.error("错误 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
             }  
          }
          log.info("模板消息发送结果："+result);
        return TTResult.ok();
       }
}