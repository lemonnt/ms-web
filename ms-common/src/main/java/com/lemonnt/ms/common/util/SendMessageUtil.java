/**
 * @author : Gavin Li
 * @date   : Nov 7, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.util.SendMessageUtil
 */
package com.lemonnt.ms.common.util;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import com.lemonnt.ms.common.bean.AdvancedDate;
import com.lemonnt.ms.common.bean.sendinformation.InformationSendClient;
import com.lemonnt.ms.common.bean.sendinformation.Message;
import com.lemonnt.ms.common.bean.sendinformation.MsgResponse;
import com.lemonnt.ms.common.file.csv.CSVFile;


/**
 * @author : Gavin Li
 * @date   : Nov 7, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.util.SendMessageUtil
 */
public class SendMessageUtil{
    
    public static MsgResponse sendRegisterMessage(String number,String baseUrl,String header,String footer,String parameters) throws Exception{
        Message message = new Message();
        message.setCellphoneNumber(number);
        message.setType(MsgConstant.CONTENT_TYPE);
        message.setBaseUrl(baseUrl);
        message.setParameters(parameters);
        message.setContent(content(message.getCellphoneNumber(), header, message.getParameters(), footer));
        return new InformationSendClient().sendMessage(message);
        
    }

    private static String content(String number,String header,String userName,String footer) {
        return "accountSid=" + MsgConstant.ACCOUNT_SID + "&to=" + number + "&smsContent=" +header+userName+footer+ biuldPrivateParameters();
    }

    private static String biuldPrivateParameters() {
        String timeStamp = new AdvancedDate().formatter(new Date(),"yyyyMMddHHmmss"), sig = DigestUtils.md5Hex(MsgConstant.ACCOUNT_SID + MsgConstant.AUTH_TOKEN + timeStamp);
        return "&timestamp=" + timeStamp + "&sig=" + sig + "&respDataType=" + MsgConstant.RESP_DATA_TYPE;
    }
    
//    public static void main(String[] args) {
//        try {
//            //男装
//            //尊敬的{1}，淘宝嘉年华，多买多优惠，猛戳领取红包 https://c.tb.cn/c.ZIhh0 回T退订!
//            //女装
//            //尊敬的{1}，淘宝嘉年华，多买多优惠，猛戳领取红包 https://c.tb.cn/c.ZIS1j 回T退订!
//            //MsgResponse response =  sendRegisterMessage("15156000511", MsgConstant.BASE_URL, MsgConstant.SMSCONTENTHEADER, MsgConstant.SMSCONTENTFOOTER,Util.createMessageCode());
//            //MsgResponse response = sendRegisterMessage("13456852199", "https://api.miaodiyun.com/20150822/affMarkSMS/sendSMS", "【柠檬网络科技】尊敬的 ", "先生/女士，淘宝双十一嘉年华，多买多送，多购多得，猛戳领取红包 https://shop123251418.taobao.com/dc-3773.htm，回 T退订。","李智");
//            //S//ystem.out.println(response.getRespCode()+":"+response.getStatus());
//           /* List<Number> numbers = new CSVFile().csv2JavaObject("C:\\cisco\\member.csv", Number.class);
//            /*MsgResponse response = sendRegisterMessage("13865756960", 
//                "https://api.miaodiyun.com/20150822/affMarkSMS/sendSMS", 
//                "【柠檬网络科技】尊敬的", 
//                "，淘宝嘉年华，多买多优惠，猛戳领取红包 https://c.tb.cn/c.ZIhh0 回T退订!",
//                "李静先生");
//            System.out.println(response.getRespCode());*/
//            /*for(Number number : numbers){
//                String userName = number.getName();
//                String content = "";
//                if(number.getGender().equals("男")){
//                    userName +="先生";
//                    content = "，淘宝嘉年华，多买多优惠，猛戳领取最后一波红包 https://c.tb.cn/c.ZIhh0 回T退订！";
//                }else if(number.getGender().equals("女")){
//                    userName +="女士";
//                    content = "，淘宝嘉年华，多买多优惠，猛戳领取最后一波红包 https://c.tb.cn/c.ZIS1j 回T退订!";
//                }else{
//                    userName +="先生/女士";
//                    content = "，淘宝嘉年华，多买多优惠，猛戳领取红包 c.tb.cn/c.ZIS1j 回T退订!";
//                }
//               
//                MsgResponse response = sendRegisterMessage(number.getNumber(), 
//                    "https://api.miaodiyun.com/20150822/affMarkSMS/sendSMS", 
//                    "【柠檬网络科技】尊敬的", 
//                    content,
//                    userName);
//                System.out.println(userName+":"+",status : "+response.getRespCode()+",content : "+content+",性别 :"+number.getGender());
//            }*/
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        
//    }
    
    public static void main(String[] args) throws IOException {
		List<Number> numbers = new CSVFile("C:\\cisco\\member.csv").csv2JavaObject(Number.class);
		new CSVFile("C:\\cisco\\member2.csv").buildCSVFile(numbers);
		System.out.println(numbers);
	}
    
}
