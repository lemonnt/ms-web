/**
 * @author : Gavin Li
 * @date   : Nov 7, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.bean.sendinformation.InformationSendClient
 */
package com.lemonnt.ms.common.bean.sendinformation;

import com.alibaba.fastjson.JSONObject;
import com.lemonnt.ms.common.bean.HttpsClient;
import com.lemonnt.ms.common.exception.MessageException;
import com.lemonnt.ms.common.util.Util;

/**
 * @author : Gavin Li
 * @date   : Nov 7, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.bean.sendinformation.InformationSendClient
 */
public class InformationSendClient extends HttpsClient{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MsgResponse sendMessage(Message message) throws Exception{
        if(Util.isEmpty(message)) throw new MessageException("Message is null");
        if(!message.check()) throw new MessageException("Failed to check message");
        String result = doPost(message.getBaseUrl(), message.getContent(), message.getType());
        MsgResponse response = JSONObject.toJavaObject(JSONObject.parseObject(result), MsgResponse.class);    
        return response;
    }

    public MsgResponse sendEmail4Alibaba(Email email){
        return new EmailSender().sendMailForALY(email);
    }
    
    public boolean sendEmail(Email email){
        return new EmailSender().sendMail(email);
    }
}
