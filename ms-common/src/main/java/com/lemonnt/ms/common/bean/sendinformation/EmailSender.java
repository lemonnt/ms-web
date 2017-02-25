/**
 * @author : Gavin Li
 * @date   : Nov 7, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.bean.sendinformation.EmailSender
 */
package com.lemonnt.ms.common.bean.sendinformation;

import java.io.File;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import org.apache.commons.logging.*;
import org.springframework.mail.javamail.*;
import org.springframework.util.StringUtils;
import com.lemonnt.ms.common.exception.MessageException;
import com.lemonnt.ms.common.util.Util;

/**
 * @author : Gavin Li
 * @date   : Nov 7, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.bean.sendinformation.EmailSender
 */
class EmailSender {
    private final static String DOT = ",";
    private final static Log logger = LogFactory.getLog(EmailSender.class);
    public MsgResponse sendMailForALY(Email email) throws MessageException {
        if(Util.isEmpty(email)) throw new MessageException("Email msg is null");
        if(!email.check()) throw new MessageException("Failed to check email");
        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", email.getHost());
        props.put("mail.smtp.port", email.getPort());   
        props.put("mail.user", email.getUserName());
        props.put("mail.password", email.getPassword());
        MsgResponse response = new MsgResponse();
        try {           
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            Session mailSession = Session.getInstance(props, authenticator);
            MimeMessage message = new MimeMessage(mailSession);
            InternetAddress form = new InternetAddress(
                    props.getProperty("mail.user"));
            message.setFrom(form);
            InternetAddress to = new InternetAddress(email.getTo());
            message.setRecipient(MimeMessage.RecipientType.TO, to);
            message.setSubject(email.getSubject());
            message.setContent(email.getContent(), "text/html;charset=UTF-8");
            Transport.send(message);
        } catch (Exception e) {
            logger.error("Failed to send Mail",e);
        }
        return response;
    }
    
    public boolean sendMail(Email email) {
        try {
            if(Util.isEmpty(email)) throw new MessageException("Email msg is null");
            if(!email.check()) throw new MessageException("Failed to check email");
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            sender.setHost(email.getHost());
            sender.setUsername(email.getUserName());
            sender.setPassword(email.getPassword());
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
            helper.setFrom(email.getFrom());
            helper.setPriority(email.getPriority());
            for(File file : email.getAttachments()){
                helper.addAttachment(file.getName(), file); 
            }
            String tos = email.getTo(),ccs = email.getCcs(),bbc = email.getBbc();
            if (!StringUtils.isEmpty(tos)) 
                helper.setTo(tos.split(DOT));           
            if (!StringUtils.isEmpty(ccs)) 
                helper.setCc(ccs.split(DOT));           
            if (!StringUtils.isEmpty(bbc)) 
                helper.setBcc(bbc.split(DOT));           
            helper.setText(email.getContent(), true);   
            helper.setSubject(email.getSubject());
            sender.send(message);
            return true;
        } catch (Exception t) {
            logger.error("send mail occur error", t);
            return false;
        }
    }

}
