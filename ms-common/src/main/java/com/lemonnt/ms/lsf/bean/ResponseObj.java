package com.lemonnt.ms.lsf.bean;

import com.lemonnt.ms.common.exception.RootException;


public class ResponseObj<T> {

	private boolean result;
	private T data;
	private String message;

	public ResponseObj() {
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSuccessData(T data) {
		this.result = true;
		this.data = data;
	}

	public void setFailMessage(String errorMessage) {
		this.result = false;
		this.message = errorMessage;
	}

	public void setFailMessage(Exception ex) {
		this.result = false;
		this.message = "";//

		if (ex instanceof RootException) {
			this.message = ex.getMessage();
		}

	}
}
