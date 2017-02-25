define(function(require){
   var $ = require("jquery");
   var service = require("service");
   var common = require("common");
   var sweetAlert = require("sweetAlert");
   var accountStatus = require("global");
   $("#_login_").click(function(){
     var account = {};
     account.name = inputValue("#login_module","name");
     account.password = inputValue("#login_module","password");
     var result = JSON.stringify(account);     
     common.loading({type:'squre',title:'正在验证用户，请稍后 ...',color:'#006C89'});   
     service.callService("account","login",[result],function(data){
        if(data.data){         
           window.open(common.setSid(WEBROOT+'index/',true),'_self');
        }else{
          swal({title:"用户名或密码错误",text: "登录失败",type:"error",showConfirmButton: true });
        }
        common.stopLoading();
     },function(){
    	 common.stopLoading();
     });
   });
   
   
   $("#createUser").click(function(){
     var account = {};
     account.name = inputValue("#register","name");
     account.password = inputValue("#register","password");
     account.email = inputValue("#register","email");
     if(!common.userName(account.name)){
       swal({title:"用户名格式错误",text: "只能为数字或者字母，3到24位",type:"error",showConfirmButton: true });
       return;
     }
     if(!common.password(account.password)){
       swal({title:"密码格式错误",text: "必须包含数字和字母，8到32位",type:"error",showConfirmButton: true });
       return;
     }
     if(!common.email(account.email)){
       swal({title:"邮箱格式错误",text: "符合邮箱格式，形如 administrator@lemonnt.com",type:"error",showConfirmButton: true });
       return;
     }
     var result = JSON.stringify(account);     
     common.loading({type:'squre',title:'正在注册中，请稍后 ...',color:'#00A5B7'});
     service.callService("account","createUser",[result],function(data){
        common.stopLoading();
        var status = data.data;
        if(status == accountStatus.success){
          swal({ 
            title: "注册成功", 
            text: "欢迎加入我们 !", 
            type: "success", 
            showCancelButton: true, 
            closeOnConfirm: true, 
            confirmButtonText: "立即登录",
            cancelButtonText:"取消",
            confirmButtonColor: "#ec6c62"            
           }, function() { 
             $("#login_module").find("input[name='name']").val(account.name);
             $("#login_module").find("input[name='password']").val(account.password);
             window.location.href="login.jsp?dev#signin";
           });
          
        }else if(status == accountStatus.dup){
          swal({title:"用户民已经被注册 !",text: "创建失败 ",type:"warning",showConfirmButton: true });
        }else if(status == accountStatus.authException){
          swal({title:"用户名，密码，邮箱格式错误 ",text: "创建失败",type:"warning",showConfirmButton: true });
        }else {
          swal({title:"服务器繁忙，请稍后再试 ",text: "创建失败",type:"warning",showConfirmButton: true });
        }
        
     });
   });
   
   
   function inputValue(id,name){
     return $(id).find("input[name='"+name+"']").val();
   }
   
   
});