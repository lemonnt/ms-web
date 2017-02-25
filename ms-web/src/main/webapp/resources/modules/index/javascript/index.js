define(function(require, exports, module){

   //获取common包，试用通用方法
   require("common").importCss("modules/index/css/index.css");
   //获取会员管理模块对象
   var template = require("template"); 
   var member = require("modules/index/javascript/member");
   var memberInfo = require("modules/index/javascript/member-info");
   var memberMarketing = require("modules/index/javascript/member-marketing");
   var memberHtml = require("text!../html/member.html");
   var memberInfoHtml = require("text!../html/member-info.html");
   var memberProductHtml = require("text!../html/member-product.html");
   var memberMaketingHtml = require("text!../html/member-marketing.html");
   var $superMember = $("#superMember");
   module.exports = {
       main : function(){
           //页签变化
           pageToggle();             
       }
   };
   
//   /**点击标签是切换main区域的内容*/
   function pageToggle(){
       $("#memberManagementModule").find(".child_menu").find("a").click(function(){
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
       if(id == "#memeberManagement"){
          $superMember.html(template.compile(memberInfoHtml));
           //初始化用户信息查询的模块
          memberInfo.init();
       }else if(id == "#memeberCreate"){
          $superMember.html(template.compile(memberHtml));
          $(id).append(template.compile(memberProductHtml));
           //初始化会员管理模块
          member.init();
         
       }else if(id == "#memeberMarketing"){
         //memberMaketing
         $superMember.html(template.compile(memberMaketingHtml));
         //初始化用户信息查询的模块
         memberMarketing.init();
       }
   }
   
   function checkSaveOrNot($id){
     return $id.length >= 1;
   }
   
});