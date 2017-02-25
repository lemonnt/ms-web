define(function(require, exports, module) {
  //获取jquery
  var $ = require("jquery");
  //获取bootstrap
  var bootstrap = require("bootstrap-table");
  //获取custom
  var custom = require("custom");
  //获取lsf的核心方法
  var service = require("service");
  //获取弹出框插件对象
  var sweetAlert = require("sweetAlert");
 
  //编辑器
  var kindEditor = require("kindEditor");
  //编辑器支持的语言
 // var chineseLanguage = require("chineseLanguage");
  
  //对外暴露的接口
  module.exports = {
          init : function(){
            KindEditor.ready(function(K) {
                 window.editor = K.create('#editor_id');
            });
            
          }        
  }
  
  
});