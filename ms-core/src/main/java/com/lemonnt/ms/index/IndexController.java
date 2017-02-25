/**
 * @author : Gavin Li
 * @date   : Oct 27, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.index.Index
 */
package com.lemonnt.ms.index;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lemonnt.ms.common.util.Util;
import com.lemonnt.ms.lsf.bean.Page;
import com.lemonnt.ms.lsf.core.LSFSession;


/**
 * @author : Gavin Li
 * @date   : Oct 27, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.index.Index
 */

@Controller
@RequestMapping("/index/")
public class IndexController extends LSFSession {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Page("index")
    public void index(Model params){
        String userName = String.valueOf(getAttribute("name"));
        params.addAttribute("userName", userName);
        params.addAttribute("ipAddress",Util.ipAddress());
        
    }

}
