package com.zhwl.home_server.util;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.zhwl.home_server.bean.ResultVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 腾讯短信云工具类
 */
@Configuration
public class SmsSendUtil {

    public static Integer QCLOUDSMS_APPID;
    public static String QCLOUDSMS_APPKEY;

    @Value("${QCLOUDSMS_APPID}")    //ID
    public void setQCLOUDSMS_APPID(Integer QCLOUDSMS_APPID) {
        SmsSendUtil.QCLOUDSMS_APPID = QCLOUDSMS_APPID;
    }

    @Value("${QCLOUDSMS_APPKEY}")   //key
    public void setQCLOUDSMS_APPKEY(String QCLOUDSMS_APPKEY) {
        SmsSendUtil.QCLOUDSMS_APPKEY = QCLOUDSMS_APPKEY;
    }

    public static ResultVo singleSender(String phoneNumber, Integer templateId, String ...params) {
        try {
//            String[] params = {"8888", "5"};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            SmsSingleSender ssender = new SmsSingleSender(QCLOUDSMS_APPID, QCLOUDSMS_APPKEY);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,
                    templateId, params, null, "", "");  // sign签名参数未提供或者为空时，会使用默认签名发送短信
            return ResultVo.ok(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }
    public static ResultVo smsMultiSender(ArrayList<String> phoneNumbers, Integer templateId, String ...params) {
        try {
//            String[] params = {"8888", "5"};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            ArrayList<String> paramList = (ArrayList)Arrays.asList(params);
            SmsMultiSender msender = new SmsMultiSender(QCLOUDSMS_APPID, QCLOUDSMS_APPKEY);
            SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers,
                    templateId,paramList ,"" , "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            return ResultVo.ok(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }
}

