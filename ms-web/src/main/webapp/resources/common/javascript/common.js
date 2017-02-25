/**
 * Created by gavli on 6/13/2016.
 */
define(function(require, exports, module) {
    var $ = require("jquery");
    var cookies = require("jquery-cookie");
    var bootstrap = require("bootstrap");
    if($ == null || $ === 'undefined' || $ == undefined){
        throw new Error('Common\'s JavaScript requires jQuery');
    }
    module.exports = {
        formatDate: function (date, pattern){
          if (date == undefined || null == date) {  
            date = new Date();  
          }  
          if (pattern == undefined) {  
              pattern = "yyyy-MM-dd hh:mm:ss";  
          }  
          return date.format(pattern);  
              
        },
        /**格式化时间函数*/
        formaterPickerDates : function  ($id,now,startDate,endDate){
            var formaterDate = this.formatDate(new Date(),"yyyy-MM-dd");
            if(startDate == null || startDate == undefined){
              startDate = "1970-11-11";
            }
            if(endDate == null || endDate == undefined){
              endDate = formaterDate;
            }
            
            if(now == null || now == undefined){
              now = formaterDate;
            }
            $id.datepicker({
              format : "yyyy-mm-dd",
              endDate : endDate,
              startDate:"1920-11-11",
              autoclose : true
            }).datepicker('update',now);
        },
        dateAdd : function (strInterval, NumDay, dtTmp) {
          if (dtTmp == null | dtTmp == "")
            dtTmp = new Date();
          switch (strInterval) {
          case "h":
            return new Date(Date.parse(dtTmp) + (3600000 * NumDay));
          case "d":
            return new Date(Date.parse(dtTmp) + (86400000 * (NumDay + 1)));
          case "w":
            return new Date(Date.parse(dtTmp) + ((86400000 * 7) * NumDay)
                + 86400000);
          case "m":
            return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + NumDay, dtTmp
                .getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp
                .getSeconds());
          case "y":
            return new Date((dtTmp.getFullYear() + NumDay), dtTmp.getMonth(), dtTmp
                .getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp
                .getSeconds());
          case "r":
            var year = dtTmp.getFullYear(),day =dtTmp.getDate(),month =dtTmp.getMonth();
            return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + NumDay, getRealDate(year,month,day,NumDay)
                    , dtTmp.getHours(), dtTmp.getMinutes(), dtTmp
                .getSeconds());
            
          }
        },
        password: function(p) {
            var pattern = /^(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[_#@]+$).{8,32}$/;
            return this.check(pattern,p);
        },
        userName: function(p) {
            var pattern = /^[a-zA-Z0-9_]{3,24}$/;
            return this.check(pattern,p);
        },
        email: function(p) {
            var pattern = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
            return this.check(pattern,p);
        },
        telephonyNumber: function(p) {
          var pattern = /^1[3|4|5|7|8]\d{9}$/;
          return this.check(pattern,p);
        },
        number : function(p,l){
            if(p == 0) return true;
            var pattern = /^[0-9]*$/;
            if(this.check(pattern,p) && p <= l){
              return true;
            }
            return false;
        },
        double : function(p,l){
            if(p == 0) return true;
            var pattern = /^\\d+(\\.\\d+)?$/;
            if(this.check(pattern,p) && p <= l){
              return true;
            }
            return false;
        },
        length : function(value){
          return length(value);
        },
        check : function(pattern,value){
          return pattern.test(value);
        },
        setSid : function(url,flag){
          var sids = (sid == null || sid == "null")? sessionId:sid;
          if(flag) return url+"?sid="+sids;
          return url+"&sid="+sids;
        },
        /**loading 函数 依赖common.css*/
        loading : function(p){
          var defaultValue = {
             type :'squre',
             title: 'Loading ...',
             color:'#67CF22',

          };
          var value = $.extend({}, defaultValue, p),className = "loading_"+value.type;
          $('body').append("<div class='backgroundShade'></div><div class='loading_wrap'></div>");
          if(value.type == 'squre'){
            $('.loading_wrap').append("<div class='"+className+"'></div><div class='loading_txt_color'>"+value.title+"</div>");
            if(value.color){
              $('.'+className).css({
                background:value.color
              })
            }
          }else if(value.type == 'circle'){    
             $('.loading_wrap').append("<div class='"+className+"'><div class='double-bounce1'></div><div class='double-bounce2'></div>" +
             		"</div><div class='loading_txt_color'>"+value.title+"</div>");
             if(value.color){
                 $(".double-bounce1,.double-bounce2").css({
                     background:value.color
                 })
             }
             
          }
        },
        stopLoading : function(){
          $(".loading_wrap").remove();
          $(".backgroundShade").remove();
        },
        cookies : function(cookieName,content,expiresDateMinutes){
          var date = new Date();
          date.setTime(date.getTime() + (expiresDateMinutes * 60 * 1000));
          $.cookie(cookieName, content, {
            expires: date
          });
        },
        getCookies : function(cookieName){
          return $.cookie(cookieName);
        },
        importCss : function(url){
        	var cssUrl = require.toUrl(url);
        	var link = document.createElement("link");
        	link.type = "text/css";
        	link.rel = "stylesheet";
        	link.href = cssUrl;
        	document.getElementsByTagName("head")[0].appendChild(link);
        },
        inputToolTip : function(selector,triggerMethod){
        	selector.attr({
	    		"data-toggle":"tooltip",
	    		"data-placement":"top"
    	    });
        	selector.tooltip({
      		  trigger:triggerMethod
      		});
    	
        },
        inputMatcher : function(selector){
        	/**
        	 * focus方式启动tip
        	 */

        	/**
        	 * 1. data-matcher : 填写正则表达式
        	 * 2. data-scope : [m,n] 
        	 *   (1) n >= m 时，value >= m && m <= n
        	 *   (2) n == m 时，value == m == n
        	 *   (3) value 必须是数字
        	 *   其他情况为false
        	 */
        	var scope = selector.data("scope"); 	
    		var value = selector.val();
        	if(null != scope && undefined != scope && "" != scope){
        		if(!isNumber(parseInt(value))){
        			//标红
        			return setError(selector);
        		}
        		
        		if(isArray(scope) && scope.length == 2){
        			var one = scope[0],two = scope[1];
            		if(!isNumber(one) || !isNumber(two)){
            			//标红
            			return setError(selector);
            		}
        			if(value <= two && value >= one){
        				//标绿
        				return setSuccess(selector);
        			}else{
        				//标红
        				return setError(selector);
        			}
        			
        		}else if(isString(scope)){
        			if(scope.startWith('\\[,') && scope.endWith('\\]')){
        				var maxValue = scope.getNumber();
        				if(value >= maxValue){
        					//标红
        					return setError(selector);
        				}else{
        					//标绿
        					return setSuccess(selector);
        				}
        			}else if(scope.startWith('\\[') && scope.endWith(',\\]')){
        				var minValue = scope.getNumber();
        				if(value <= minValue){
        					//标红
        					return setError(selector);
        				}else{
        					//标绿
        					return setSuccess(selector);
        				}
        			}else{
        				//标红
        				return setError(selector);
        			}
        		}
        		
        	}
        	
        	var matcher = selector.data("matcher");     
            if(null != matcher && undefined != matcher && "" != matcher){
            	//console.log(new RegExp(matcher).test(value));
            	if(null == value || !value.match(matcher)){
            		return setError(selector);
            	}else{
            		return setSuccess(selector);
            	}
            	
        	}
            
            return true;
        	
        },
        fomatFloat :function (src,pos){   
            return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);   
         },
        addMultiDivContentFromSelect : function ($select,$div,css){
      	   $select.change(function(){
      		   var value = $select.find('option:selected').text();
      		   var flag = true;
      		   $div.find(".mutliFromSelect").each(function(i,o){
      			   if(value == $(o).data("value")){
      				   flag = false;
      				   return;
      			   }
      		   });
      		   if(flag){
      			   $div.append("<div class='mutliFromSelect' data-value='"+value+"'>&nbsp;"+value+"&nbsp;&nbsp;<span class='glyphicon glyphicon-remove'></span>&nbsp;</div>");
      			   var multiFromSelectHeight = ($div.height()-$(".mutliFromSelect").height())/2;
      			   $(".mutliFromSelect").css({
      				   "line-height":$(".mutliFromSelect").height()+"px",
      				   "margin-top":multiFromSelectHeight+'px'
      				   
      			   });
      			   if(null != css && undefined != css)
      			   $(".mutliFromSelect").css(css);
      		   }
      		   
      	   });
      	   
      	   $div.on("click",".glyphicon-remove",function(){
      		   $(this).parent().remove();
      	   });
         },
         listenerInputValue : function($id,callback){
        	 $id.find("select.wasChecked").change(function(){
        		if(typeof callback === "function"){
        		   callback();
        		 }
        	 });
        	 $id.find("input").change(function(){
         		if(typeof callback === "function"){
         		   callback();
         		 }
         	 });
        	 $id.find("textarea").change(function(){
         		if(typeof callback === "function"){
         		   callback();
         		 }
         	 });
         }
        
    }
    
  //扩展Date的format方法   
    Date.prototype.format = function (format) {  
        var o = {  
            "M+": this.getMonth() + 1,  
            "d+": this.getDate(),  
            "h+": this.getHours(),  
            "m+": this.getMinutes(),  
            "s+": this.getSeconds(),  
            "q+": Math.floor((this.getMonth() + 3) / 3),  
            "S": this.getMilliseconds()  
        }  
        if (/(y+)/.test(format)) {  
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
        }  
        for (var k in o) {  
            if (new RegExp("(" + k + ")").test(format)) {  
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
            }  
        }  
        return format;  
    }  
    
    
    String.prototype.startWith=function(str){     
    	var reg=new RegExp("^"+str);     
    	return reg.test(this);        
    }  
    
    String.prototype.endWith=function(str){     
    	var reg=new RegExp(str+"$");     
    	return reg.test(this);        
    }
    
    String.prototype.getNumber = function(){
    	return this.replace(/[^0-9]/ig,""); 

    }
    
 
  
    
    
  
});



function isString(str){ 
    return (typeof str=='string')&&str.constructor==String; 
}

function  setError(selector){
	selector.tooltip('show');
	selector.css({
		border:"1px solid red"
	});
	selector.parent().next().find("span").removeClass("glyphicon-ok").addClass("glyphicon-asterisk").css({
		color:'red'
	});
	return false;
}

function setSuccess(selector){
	selector.tooltip('hide');
	selector.css({
		border:"1px solid #4BC181"
	});
	selector.parent().next().find("span").removeClass("glyphicon-asterisk").addClass("glyphicon-ok").css({
		color:'#4BC181'
	});
	return true;
}

function isNumber(obj){ 
	return (typeof obj=='number')&&obj.constructor==Number; 
} 
//获取字节长度
function length(val){
  var zhlength=0;// 全角
  var enlength=0;// 半角
  for(var i=0;i<val.length;i++){
    if(val.substring(i, i + 1).match(/[^\x00-\xff]/ig) != null)
      zhlength+=1;
    else
      enlength+=1;
  }
  return (zhlength*2)+enlength;
}

function isArray(value){
    if (value instanceof Array ||
		(!(value instanceof Object) &&
		 (Object.prototype.toString.call((value)) == '[object Array]') ||
		        typeof value.length == 'number' &&
		        typeof value.splice != 'undefined' &&
		        typeof value.propertyIsEnumerable != 'undefined' &&
		        !value.propertyIsEnumerable('splice'))) {
		return true;
     }
    return false;
}
