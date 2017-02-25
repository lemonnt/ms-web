package com.lemonnt.ms.common.bean.sendinformation;

import org.apache.log4j.Logger;

import com.lemonnt.ms.common.bean.AdvancedMatcher;
import com.lemonnt.ms.common.bean.RegExpression;


public class Message{
	
    private final static String DOT = ",";
    private final static Logger LOGGER  = Logger.getLogger(Message.class);
    
    private String baseUrl;
    private String type;
    private String content;
    private String cellphoneNumber;
    private String parameters;
    
    
    public boolean check(){
        
        if(null == this.cellphoneNumber || null == this.type || this.content == null ||
                null == this.baseUrl) return false;
        AdvancedMatcher advancedMatcher = new AdvancedMatcher(RegExpression.TELEPHONY_NUMBER);
        String cellphoneNumbers = "";
        if(this.cellphoneNumber.contains(DOT)){
            String[] numbers = this.cellphoneNumber.split(DOT);
            for(String number : numbers){
                if(advancedMatcher.match(number)){
                    cellphoneNumbers +=number+DOT;
                }else{
                    LOGGER.error("The number ["+number+"] don't match type of cellphone number");
                }
            }
            if(!"".equals(cellphoneNumbers))
                this.setCellphoneNumber(cellphoneNumbers.substring(0,cellphoneNumbers.length()-1));
            return "".equals(cellphoneNumbers);
        }else{
            return advancedMatcher.match(this.cellphoneNumber);
        }
        
    }
    
   
    /**
     * @return the parameters
     */
    public String getParameters() {
        return parameters;
    }


    /**
     * @param parameters the parameters to set
     */
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }


    /**
     * @return the cellphoneNumber
     */
    public String getCellphoneNumber() {
        return cellphoneNumber;
    }
    /**
     * @param cellphoneNumber the cellphoneNumber to set
     */
    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }
    /**
     * @return the baseUrl
     */
    public String getBaseUrl() {
        return baseUrl;
    }
    /**
     * @param baseUrl the baseUrl to set
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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

}
