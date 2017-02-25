define(function(require){
   var $ = require("jquery");
   var service = require("service");
   var common = require("common");
   var sweetAlert = require("sweetAlert");
   $("#_login_").click(function(){
     window.open(seajs.data.vars.indexPath,'_self');
     //service.callService("account","login",["lijing"],function(data){
       
     //});
   });
  
   
   $("#createUser").click(function(){
     var account = {};
     account.name = inputValue("#register","name");
     account.password = inputValue("#register","password");
     account.email = inputValue("#register","email");
     if(!common.userName(account.name)){
       alert("Error userName");
       return;
     }
     if(!common.password(account.password)){
       alert("Error password");
       return;
     }
     if(!common.email(account.email)){
       alert("Error email");
       return;
     }
     var result = JSON.stringify(account);     
     service.callService("account","createUser",[result],function(data){
        if(data.data){
          window.location.href="login.jsp?dev#signin";
        }else{
          
        }
        
     });
   });
   
   
   function inputValue(id,name){
     return $(id).find("input[name='"+name+"']").val();
   }
   
   
});