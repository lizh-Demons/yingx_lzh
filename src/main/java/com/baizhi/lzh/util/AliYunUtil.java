package com.baizhi.lzh.util;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class AliYunUtil {
    //参数1 手机号，参数2 签名  参数3 模板id 参数4 验证码
    public static SendSmsResponse testAliYunCode(String phoneNumbers, String signName, String templateCode, String templateParam) throws Exception {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "LTAI4GFTYCcFpF9SdK81kLut";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "72gM5CnrkhbGiJrzYajyKLlvcmV6TA";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
//        "13233144525,16636182256"
        request.setPhoneNumbers(phoneNumbers);
        //必填:短信签名-可在短信控制台中找到
//        "赤行课堂"
        request.setSignName(signName);             //签名
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
//        "SMS_205621548"
        request.setTemplateCode(templateCode);   //模板id
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        //参考：request.setTemplateParam("{\"变量1\":\"值1\",\"变量2\":\"值2\",\"变量3\":\"值3\"}")
        request.setTemplateParam(templateParam);
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isp.RAM_PERMISSION_DENY")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.OUT_OF_SERVICE")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.PRODUCT_UN_SUBSCRIPT")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.PRODUCT_UNSUBSCRIBE")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.ACCOUNT_NOT_EXISTS")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.ACCOUNT_ABNORMAL")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.SMS_TEMPLATE_ILLEGAL")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.SMS_SIGNATURE_ILLEGAL")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.INVALID_PARAMETERS")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isp.SYSTEM_ERROR")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.MOBILE_COUNT_OVER_LIMIT")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.TEMPLATE_MISSING_PARAMETERS")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.BUSINESS_LIMIT_CONTROL")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.INVALID_JSON_PARAM")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.BLACK_KEY_CONTROL_LIMIT")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.PARAM_LENGTH_LIMIT")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.PARAM_NOT_SUPPORT_URL")) {
            //请求成功
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("isv.AMOUNT_NOT_ENOUGH")) {
            //请求成功
        }
        return sendSmsResponse;
    }


    public static String sendPhoneMsg(String phoneNumbers, String code) {
        String msg = null;
        try {

            String signName = "赤行课堂";
            String templateCode = "SMS_205621548";
            String templateParam = "{ \"code\":\"" + code + "\"}";


            SendSmsResponse sendSmsResponse = testAliYunCode(phoneNumbers, signName, templateCode, templateParam);
            //获取验证码
            String message = sendSmsResponse.getCode();
            if (message.equals("OK")) {
                msg = "验证码发送成功";
            }
            if (message.equals("isp.RAM_PERMISSION_DENY")) {
                msg = "RAM权限DENY";
            }
            if (message.equals("isv.OUT_OF_SERVICE")) {
                msg = "业务停机";
            }
            if (message.equals("isv.PRODUCT_UN_SUBSCRIPT")) {
                msg = "未开通云通信产品的阿里云客户";
            }
            if (message.equals("isv.PRODUCT_UNSUBSCRIBE")) {
                msg = "产品未开通";
            }
            if (message.equals("isv.ACCOUNT_NOT_EXISTS")) {
                msg = "账户不存在";
            }
            if (message.equals("isv.ACCOUNT_ABNORMAL")) {
                msg = "账户异常";
            }
            if (message.equals("isv.SMS_TEMPLATE_ILLEGAL")) {
                msg = "短信模板不合法";
            }
            if (message.equals("isv.SMS_SIGNATURE_ILLEGAL")) {
                msg = "短信签名不合法";
            }
            if (message.equals("isv.INVALID_PARAMETERS")) {
                msg = "参数异常";
            }
            if (message.equals("isp.SYSTEM_ERROR")) {
                msg = "系统错误";
            }
            if (message.equals("isv.MOBILE_COUNT_OVER_LIMIT")) {
                msg = "手机号码数量超过限制";
            }
            if (message.equals("isv.TEMPLATE_MISSING_PARAMETERS")) {
                msg = "模板缺少变量";
            }
            if (message.equals("isv.BUSINESS_LIMIT_CONTROL")) {
                msg = "业务限流";
            }
            if (message.equals("isv.INVALID_JSON_PARAM")) {
                msg = "JSON参数不合法，只接受字符串值";
            }
            if (message.equals("isv.BLACK_KEY_CONTROL_LIMIT")) {
                msg = "黑名单管控";
            }
            if (message.equals("isv.PARAM_LENGTH_LIMIT")) {
                msg = "参数超出长度限制";
            }
            if (message.equals("isv.PARAM_NOT_SUPPORT_URL")) {
                msg = "不支持URL";
            }
            if (message.equals("isv.AMOUNT_NOT_ENOUGH")) {
                msg = "账户余额不足";
            }


//            code = sendSmsResponse.getCode();
//            System.out.println("发送的验证码为" + code);
//            String bizId = sendSmsResponse.getBizId();
//            System.out.println("bizId" + bizId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }


    public static void main(String[] args) {
        String securityCode = ImageCodeUtil.getSecurityCode();
        System.out.println("验证码 " + securityCode);
        String sendPhoneMsg = sendPhoneMsg("13233144525", securityCode);
        System.out.println(sendPhoneMsg);
    }
}
