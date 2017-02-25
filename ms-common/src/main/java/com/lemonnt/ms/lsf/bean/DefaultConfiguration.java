/**  
 * Project Name:ms-common  
 * File Name:DefaultConfiguration.java  
 * Package Name:com.lemonnt.ms.common.bean  
 * Date:Dec 13, 201610:53:43 AM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
/**  
 * Project Name: ms-common  
 * File Name: DefaultConfiguration.java  
 * Package Name: com.lemonnt.ms.common.bean  
 * Date: Dec 13, 201610:53:43 AM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
 */  
  
package com.lemonnt.ms.lsf.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**  
 * ClassName: DefaultConfiguration <br/>
 * Function: TODO ADD FUNCTION <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Dec 13, 2016 10:53:43 AM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6  
 */
@Component("defaultConfigration")
public class DefaultConfiguration {
	
	@Value("${exception.language}")
	private String exceptionLanguage;
	
	@Value("${filter.pages}")
	private String filterPages;

	@Value("${exception.properties.root.path}")
	private String exceptionRootPath;

	/**  
	 * exceptionLanguage.  
	 *  
	 * @return  the exceptionLanguage  
	 * @since   JDK 1.6  
	 */
	public String getExceptionLanguage() {
		return exceptionLanguage;
	}

	/**  
	 * filterPages.  
	 *  
	 * @return  the filterPages  
	 * @since   JDK 1.6  
	 */
	public String getFilterPages() {
		return filterPages;
	}

	/**  
	 * exceptionRootPath.  
	 *  
	 * @return  the exceptionRootPath  
	 * @since   JDK 1.6  
	 */
	public String getExceptionRootPath() {
		return exceptionRootPath;
	}

	/**  
	 * exceptionLanguage.  
	 *  
	 * @param   exceptionLanguage    the exceptionLanguage to set  
	 * @since   JDK 1.6  
	 */
	public void setExceptionLanguage(String exceptionLanguage) {
		this.exceptionLanguage = exceptionLanguage;
	}

	/**  
	 * filterPages.  
	 *  
	 * @param   filterPages    the filterPages to set  
	 * @since   JDK 1.6  
	 */
	public void setFilterPages(String filterPages) {
		this.filterPages = filterPages;
	}

	/**  
	 * exceptionRootPath.  
	 *  
	 * @param   exceptionRootPath    the exceptionRootPath to set  
	 * @since   JDK 1.6  
	 */
	public void setExceptionRootPath(String exceptionRootPath) {
		this.exceptionRootPath = exceptionRootPath;
	}
	
	
	
	

}
  
