package com.lemonnt.ms.lsf.language;

import java.util.*;

import org.springframework.util.StringUtils;

import com.lemonnt.ms.lsf.bean.Language;

public abstract class InternationalLanguage {
	protected static List<Language> languages = new ArrayList<Language>();
	protected static List<String> kinds = new ArrayList<String>();
	

	public static List<Language> getLanguages() {
		return languages;
	}

	public static List<String> getKinds() {
		return kinds;
	}




	public static void setKinds(List<String> kinds) {
		InternationalLanguage.kinds = kinds;
	}

	public static Map<String, Object> getLanguage(String kind,String module){
		if(StringUtils.isEmpty(kind) || StringUtils.isEmpty(module))
			return new HashMap<String, Object>();
		for(Language language : languages){
			if(kind.equals(language.getKind())){
				Map<String, Language> m = language.getLanguage();
				for(Map.Entry<String, Language> moduleLanguage : m.entrySet()){
					String key = moduleLanguage.getKey();
					if(key.equals(module)){
						Language l = moduleLanguage.getValue();
						if(null != l){
							Map<String, Object> reuslt = l.getProperties();
							//如果一个语言资源文件依赖另外资源文件可以添加include元素导入其他资源文件
							String include = String.valueOf(reuslt.get("include"));
							if(include != null){
								if(include.contains(";")){
									String[] includes = include.split(";");
									for(String i : includes){
										reuslt.putAll(getLanguage(kind, i));
									}
								}else{
									reuslt.putAll(getLanguage(kind, include));
								}
								
							}
							return reuslt;
						}
							
					}
						
				}
			}
		}
		return new HashMap<String, Object>();
	}
	
}
