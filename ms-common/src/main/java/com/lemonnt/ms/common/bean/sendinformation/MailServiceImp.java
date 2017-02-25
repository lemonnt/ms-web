package com.lemonnt.ms.common.bean.sendinformation;


import java.io.File;
import javax.mail.internet.MimeMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;
import com.lemonnt.ms.common.exception.MessageException;
import com.lemonnt.ms.common.util.Util;

public class MailServiceImp{

	private final static Log logger = LogFactory.getLog(MailServiceImp.class);


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
				helper.setTo(tos.split(","));			
			if (!StringUtils.isEmpty(ccs)) 
				helper.setCc(ccs.split(","));			
			if (!StringUtils.isEmpty(bbc)) 
				helper.setBcc(bbc.split(","));
			
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
