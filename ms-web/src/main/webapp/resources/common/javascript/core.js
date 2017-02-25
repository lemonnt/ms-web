/**
 * Created by gavli on 6/13/2016.
 */
define(function(require, exports, module) {
    var $ = require('jquery');   
    /****Light spring frame (start)***/
    module.exports = {
        syncCallService : function(moduleName, methodName, data, success,
                errorCallBack){
          rootCallService(false,moduleName, methodName, data, success,
                  errorCallBack);
        },
        callService :function(moduleName, methodName, data, success,
                errorCallBack){
          rootCallService(true,moduleName, methodName, data, success,
                  errorCallBack);
          
        }
    };
  
    function rootCallService(isSysn, moduleName, methodName, data, success, errorCallBack) {
        var mydata = {};
        mydata.moduleName = moduleName;
        mydata.methodName = methodName;
        mydata.$data = data;
        var json = JSON.stringify(mydata);
        if (!WEBROOT) {
            alert("No web root was defined when call remote service");
            return;
        }
        $.ajax({
            type: 'POST',
            dataType: "json",
            async: isSysn,
            data: {
                "data": json
            },
            url: WEBROOT + "call.lsf",
            success: function(data) {
                if (data.error) {
            	  //$(".loading_wrap").remove();
                  //$(".backgroundShade").remove();
                  if(errorCallBack) errorCallBack();               	 
                  swal({title:"哎呦，有麻烦喽 !!",text: data.exceptionMessage,type:"error",showConfirmButton: true }); 
                } else {
                    success(data);
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                if (!errorCallBack) {
                    var tip = "";
                    if (XMLHttpRequest.responseText == null || XMLHttpRequest.responseText == "") {
                        tip = "XHR Error, readystate=" + XMLHttpRequest.readyState + ", status=" + XMLHttpRequest.status;
                    } else {
                        var txt = XMLHttpRequest.responseText;
                        if ((txt.indexOf("{") == 0) && (txt.lastIndexOf("}") == (txt.length - 1))) {
                            var error = eval("(" + txt + ")");
                            tip = error.code + " : " + error.message;
                        } else {
                            tip = txt;
                        }
                    }
                    
                } else {               	
                    errorCallBack(XMLHttpRequest, textStatus, errorThrown);
                    if(XMLHttpRequest.status != 200){
                		swal({title:"哎呦，有麻烦喽 !!",text: "错误状态 : "+XMLHttpRequest.status,type:"error",showConfirmButton: true }); 
                	}
                }
            }
        });
    };
    
});
