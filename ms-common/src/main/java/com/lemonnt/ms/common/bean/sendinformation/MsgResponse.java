package com.lemonnt.ms.common.bean.sendinformation;



public class MsgResponse {
	private String respCode;
	private Integer failCount;
	private String smsId;
	private String failList;
	private Integer status;
    /**
     * @return the respCode
     */
    public String getRespCode() {
        return respCode;
    }
    /**
     * @param respCode the respCode to set
     */
    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }
    /**
     * @return the failCount
     */
    public Integer getFailCount() {
        return failCount;
    }
    /**
     * @param failCount the failCount to set
     */
    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }
    /**
     * @return the smsId
     */
    public String getSmsId() {
        return smsId;
    }
    /**
     * @param smsId the smsId to set
     */
    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }
    /**
     * @return the failList
     */
    public String getFailList() {
        return failList;
    }
    /**
     * @param failList the failList to set
     */
    public void setFailList(String failList) {
        this.failList = failList;
    }
    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

	

}
