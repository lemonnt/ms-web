package com.lemonnt.ms.lsf.bean;

import java.util.HashMap;
import java.util.Map;

public class Language {
	
	private String kind;
	private Map<String, Language> language = new HashMap<String, Language>();
	private Map<String, Object> properties = new HashMap<String,Object>();
	
	public void addProperties(String key,String value){
		this.properties.put(key, value);
	}
	
	public void addLanguage(String key,Language value){
		this.language.put(key, value);
	}
	
	
	
	public void setLanguage(Map<String, Language> language) {
		this.language = language;
	}

	public String getKind() {
		return kind;
	}
	
	public void setKind(String kind) {
		this.kind = kind;
	}
	

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public Map<String, Language> getLanguage() {
		return language;
	}
	
	
	

}
