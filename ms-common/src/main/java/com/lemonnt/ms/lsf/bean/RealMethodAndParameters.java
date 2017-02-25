/**  
 * Project Name: ms-common  
 * File Name: RealMethodAndParameters.java  
 * Package Name: com.lemonnt.ms.lsf.bean  
 * Date: Jan 23, 20176:46:50 PM  
 * Copyright (c) 2017 All Rights Reserved.  
 *  
 */  
  
package com.lemonnt.ms.lsf.bean;  

/**  
 * ClassName: RealMethodAndParameters <br/>
 * Function: TODO ADD FUNCTION <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Jan 23, 2017 6:46:50 PM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6  
 */
public class RealMethodAndParameters {
	
	private Class<?>[] parameters;
	
	private String annoationMethodName;
	
	private String realName;
	
	private String definedName;
	
	

	/**  
	 * definedName.  
	 *  
	 * @return  the definedName  
	 * @since   JDK 1.6  
	 */
	public String getDefinedName() {
		return definedName;
	}

	/**  
	 * definedName.  
	 *  
	 * @param   definedName    the definedName to set  
	 * @since   JDK 1.6  
	 */
	public void setDefinedName(String definedName) {
		this.definedName = definedName;
	}

	/**  
	 * parameters.  
	 *  
	 * @return  the parameters  
	 * @since   JDK 1.6  
	 */
	public Class<?>[] getParameters() {
		return parameters;
	}

	/**  
	 * parameters.  
	 *  
	 * @param   parameters    the parameters to set  
	 * @since   JDK 1.6  
	 */
	public void setParameters(Class<?>[] parameters) {
		this.parameters = parameters;
	}

	/**  
	 * annoationMethodName.  
	 *  
	 * @return  the annoationMethodName  
	 * @since   JDK 1.6  
	 */
	public String getAnnoationMethodName() {
		return annoationMethodName;
	}

	/**  
	 * annoationMethodName.  
	 *  
	 * @param   annoationMethodName    the annoationMethodName to set  
	 * @since   JDK 1.6  
	 */
	public void setAnnoationMethodName(String annoationMethodName) {
		this.annoationMethodName = annoationMethodName;
	}

	/**  
	 * realName.  
	 *  
	 * @return  the realName  
	 * @since   JDK 1.6  
	 */
	public String getRealName() {
		return realName;
	}

	/**  
	 * realName.  
	 *  
	 * @param   realName    the realName to set  
	 * @since   JDK 1.6  
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	

}
  
