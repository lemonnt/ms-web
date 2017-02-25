define(function(require, exports, module){
	//获取common包，试用通用方法
	require("common").importCss("modules/product/css/financial.css");
   //获取会员管理模块对象
	var $ = require("jquery");
   var template = require("template"); 
   var productHtml = require("text!../html/product.html");
   var productManagementHtml = require("text!../html/management-modal.html");
   var $superMember = $("#superMember");
   var productAdd = require('modules/product/javascript/product-add');
   var proudctManagementJS = require('modules/product/javascript/product-management');
   module.exports = {
       main : function(){
           //页签变化
           pageToggle();    

       }
   };
   
//   /**点击标签是切换main区域的内容*/
   function pageToggle(){
       $("#productManagementModule").find(".child_menu").find("a").click(function(){
         $("#managementCorePage").hide();
         var moduleId = $(this).attr("href");
         initModule(moduleId);
         $(".child_menu").find("a").each(function(i,o){
           var $a = $(this).parent();
           var mid = $(o).attr("href");
           $a.removeClass("hoverInColor").addClass("hoverOutColor");
         });      
         $(this).parent().addClass("hoverInColor").removeClass("hoverOutColor");
         
       });
   }
   
   function initModule(id){
       var refreshOrnot = checkSaveOrNot($(id));
       if(refreshOrnot) return;
       if(id == "#addProduct"){
          $superMember.html(template.compile(productHtml));
          productAdd.init();
       }else if(id == "#productInformations"){
    	   $superMember.html(template.compile(productManagementHtml)({title:"产品信息"}));
    	   proudctManagementJS.init();
       }
   }
   
   function checkSaveOrNot($id){
     return $id.length >= 1;
   }
   
});