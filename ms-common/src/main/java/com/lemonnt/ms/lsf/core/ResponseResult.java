package com.lemonnt.ms.lsf.core;

import java.io.Serializable;

public class ResponseResult implements Serializable {
	private static final long serialVersionUID = -4452978360732935172L;

	private Object data;
	private transient Throwable exception;
	private boolean isError;
	private String exceptionMessage;
	private Long time;
	
	

	/**
     * @return the time
     */
    public Long getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Long time) {
        this.time = time;
    }

    public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
}
