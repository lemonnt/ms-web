/**
 * @author : Gavin Li
 * @date   : Nov 7, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.bean.sendinformation.Email
 */
package com.lemonnt.ms.common.bean.sendinformation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.lemonnt.ms.common.bean.AdvancedMatcher;
import com.lemonnt.ms.common.bean.RegExpression;
import com.lemonnt.ms.common.util.Util;

/**
 * @author : Gavin Li
 * @date   : Nov 7, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.bean.sendinformation.Email
 */
public class Email {
    private final static String DOT = ",";
    private final static Logger LOGGER  = Logger.getLogger(Message.class);
    private String host;
    private String userName;
    private String password;
    private String to;
    private String subject;
    private String content;
    private String ccs;
    private String bbc;
    private int priority;
    private String from;
    private String port;
    private String charSet = "UTF-8";
    private List<File> attachments = new ArrayList<File>();
    
    
    
    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the charSet
     */
    public String getCharSet() {
        return charSet;
    }

    /**
     * @param charSet the charSet to set
     */
    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    public void addAttachments(File file){
        this.attachments.add(file);
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the ccs
     */
    public String getCcs() {
        return ccs;
    }

    /**
     * @param ccs the ccs to set
     */
    public void setCcs(String ccs) {
        this.ccs = ccs;
    }

    /**
     * @return the bbc
     */
    public String getBbc() {
        return bbc;
    }

    /**
     * @param bbc the bbc to set
     */
    public void setBbc(String bbc) {
        this.bbc = bbc;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the attachments
     */
    public List<File> getAttachments() {
        return attachments;
    }

    /**
     * @param attachments the attachments to set
     */
    public void setAttachments(List<File> attachments) {
        this.attachments = attachments;
    }
    
    public boolean check(){
        if(Util.isEmpty(this.from) || Util.isEmpty(this.host)) return false;
        if(Util.isEmpty(this.to) && Util.isEmpty(this.ccs)) return false;
        AdvancedMatcher advancedMatcher = new AdvancedMatcher(RegExpression.EMAIL);
        if(!advancedMatcher.match(this.from)) return false;
        this.setTo(recreateEmail(this.to));
        this.setCcs(recreateEmail(this.ccs));
        this.setBbc(recreateEmail(this.bbc));
        return true;
    }

    
    private String recreateEmail(String email){
        if(Util.isEmpty(email)) return null;
        AdvancedMatcher advancedMatcher = new AdvancedMatcher(RegExpression.EMAIL);
        if(email.contains(DOT)){
            String[] emails = email.split(DOT);
            String result = "";
            for(String e : emails){
                if(advancedMatcher.match(e)){
                    result +=e+",";      
                }else{
                    LOGGER.error("The email ["+e+"] don't match type of email");
                }
            }
            return result.substring(0,result.length()-1);
        }else{
            if(advancedMatcher.match(email)) return email;
        }
        return null;
    }
    
    public static void main(String[] args) {
        Email email = new Email();
        email.setTo("467323@email.com,23423@qq.com,weffsfsf,fsfewe@email.com");
        System.out.println(email.recreateEmail(email.getTo()));
    }
}
