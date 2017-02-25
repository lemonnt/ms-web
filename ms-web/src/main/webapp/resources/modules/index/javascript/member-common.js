define(function(require, exports, module) {
  //获取jquery
  var $ = require("jquery");
  //获取bootstrap
  var bootstrap = require("bootstrap");
  //获取custom
  var custom = require("custom");
  //获取lsf的核心方法
  var service = require("service");
  //获取弹出框插件对象
  var sweetAlert = require("sweetAlert");
  //获取省级三联动插件
  var distpicker = require("distpicker");
  //获取common包，试用通用方法
  var common = require("common");
  //获取日期插件
  var datePicker = require("datePicker");
  
  //对外暴露的接口
  module.exports = {
          init : function(){
          //退出函数
            logout($(".logOutBtn"));
            //loading 快递信息
            setExpress($("#collapseOne"));
            //loading 产品名称信息
            setProduct();
           
            //重置会员信息内容
            resetContent($("#collapseOne"));
            //重置产品信息内容
            resetContent($("#collapseTwo"));
            //提交会员信息内容
            submitMemberBasicMsg($("#collapseOne"));
            //添加多个产品，确定是否继续添加产品信息
            isContinueAddProductInformationForMember($("#collapseOne"));
            isContinueAddProductInformationForProduct($("#collapseTwo"));
          //提交会员信息内容
            submitMemberProductMsg($("#collapseTwo"));
            //初始化会员基本信息的生日
            formaterPickerDate($("#collapseOne").find("#memberBirthday"));
            setAge($("#collapseOne"));
            
            //初始化产品信息的购买日期
            formaterPickerDate($("#collapseTwo").find("#purchaseDate"));
            initDist($("#collapseTwo"));
            initDist($("#collapseOne"));
            
          }        
  }
  
  /**查询会员信息*/
  
  
  
  //自动计算年龄
  function setAge($id){
    $('#memberBirthday').datepicker().on('changeDate', function(ev){
      var age = Math.floor((new Date().getTime() - ev.date.valueOf())/(365*24*60*60*1000));
      $id.find(".form-group").find("input[name='age']").val(age);
    });
        
  }
  
  
  //根据产品名称和货号确定品牌名称
  function setProductOther($id){
    setProductOtherService($id,createProductForOther($id));
  }
  
  //根据产品名称和货号确定品牌名称
  function setProductBrand($id){
    //setProductBrandService($id,createProductForBrand($id));
    $id.find(".productNumber").change(function(){
      var products = {};
      products.name =$id.find(".productName").find("option:selected").text();
      products.number = $(this).find("option:selected").text();
      var result = JSON.stringify(products);
      setProductOther($id);
      //setProductBrandService($id,result);
    });
  }
  
  //设置货号
  function setProductNumber($id,v){
    setProductNumberService($id,createProduct(v));
    $id.find("#productName").change(function(){
      var products = {};
      products.name =$(this).find("option:selected").text();
      var result = JSON.stringify(products);
      setProductNumberService($id,result);
    });
  }
  
  
  //初始化货号是创建的产品对象
  function createProduct(v){
    var products = {};
    products.name =v.name;
    return JSON.stringify(products);
  }
  //初始化brand是创建的产品对象
  /*function createProductForBrand($id){
    var products = {};
    products.name =$id.find(".productName").find("option:selected").text();
    products.number = $id.find(".productNumber").find("option:selected").text();
    return JSON.stringify(products);
  }*/
  
  //初始化由产品名称，货号和品牌确定时查询其他对象时创建的产品对象
  function createProductForOther($id){
    var products = {};
    products.name =$id.find(".productName").find("option:selected").text();
    products.number = $id.find(".productNumber").find("option:selected").text();
    //products.brand = inputValue($id.find(".brandName").find("option:selected").text();
    //products.brand = inputValue($id,"brandName");
    return JSON.stringify(products);
  }
  
  //
  function setProductNumberService($id,result){
    service.syncCallService("member","product",[result],function(data){
      var value = data.data;
      if(value != null){
        $id.find(".productNumber").children().remove();      
        $.each(value,function(i,o){
          $id.find(".productNumber").append("<option>"+o.number+"</option>");
        });        
        //setProductBrandService($id,createProductForBrand($id));
        setProductOtherService($id,createProductForOther($id));
      }
      
    });
  }
  
  
  /*function setProductBrandService($id,result){
    service.syncCallService("member","product",[result],function(data){
      var value = data.data;
      if(value != null){
        $id.find(".brandName").children().remove();
        $.each(value,function(i,o){
          $id.find(".brandName").append("<option>"+o.brand+"</option>");
        });     
        setProductOtherService($id,createProductForOther($id));
      }    
    });
  }*/
  
  function setProductOtherService($id,result){
    service.syncCallService("member","product",[result],function(data){
      var value = data.data[0];
      //种类
      if(value.kind != null){
        $id.find(".kind").children().remove();
        var v = value.kind.split(",");
        $.each(v,function(i,o){
           $id.find(".kind").append("<option>"+o+"</option>");
        });
      }
      //颜色
      if(value.color != null){
        $id.find(".color").children().remove();  
        var v = value.color.split(",");
        $.each(v,function(i,o){
           $id.find(".color").append("<option>"+o+"</option>");
        });
       
      }
      //款式
      if(value.style != null){
        $id.find(".style").children().remove();
        var v = value.style.split(",");
        $.each(v,function(i,o){
           $id.find(".style").append("<option>"+o+"</option>");
        });
        
      }
      //尺寸
      if(value.size != null){
        $id.find(".size").children().remove();
        var v = value.size.split(",");
        $.each(v,function(i,o){
           $id.find(".size").append("<option>"+o+"</option>");
        });
      }
      
      //价格
      if(value.outprice != null){
        $id.find("input[name='price']").val(value.outprice);
      }
      
      //品牌
      if(value.brand != null){
        $id.find("input[name='brand']").val(value.brand);
      }

    });
  }
  
  //加载快递信息，从数据库
  function setExpress($id){
    service.callService("member","express",[],function(data){
      var value = data.data;
      if(value != null){
        configuration(value,$id,"express");
        //configuration(value,$("#collapseTwo"),"expressName");
      }
    });
  }
  
  //加载产品信息，从数据库
  function setProduct(){
    //$(".productInformation").addClass("activeStatus").removeClass("inactiveStatus");
    service.callService("member","productName",[],function(data){
    	debugger;
      var value = data.data;
      if(value != null && value.length > 0){
        configurationForProduct(value,$("#collapseTwo"),"productName");
        var v = value[0];
        setProductNumber($("#collapseTwo"),v);
        setProductBrand($("#collapseTwo"));
        setProductOther($("#collapseTwo"));
      }
    });
  }

  
  function configuration(value,$id,clazz){
    $id.find("."+clazz).children().remove();
    $.each(value,function(i,o){
      $id.find("."+clazz).append("<option>"+o.name+"</option>")
    });
    
  }
  
  function configurationForProduct(value,$id,clazz){
    $id.find("."+clazz).children().remove();
    $.each(value,function(i,o){
      $id.find("."+clazz).append("<option>"+o.name+"</option>")
    });
    
  }
  
  /**提交会员基本信息内容*/
  function submitMemberBasicMsg($id){
      $id.find(".submitbtn").click(function(){
        //检查信息通过后
        if(checkMsg($id)){
          var result = getValueForMemberBasicInformation($id);
          service.callService("member","buildMember",[result],function(data){
            debugger;          
            if(data.data != null){
              isAddProduct($id,1);
              $("#memberID").val(data.data);
            }else{
              swal({title:"添加失败",text: "请稍后再试",type:"error",showConfirmButton: true });
            }
          });
        }       
      });
  }
  
  
  /**提交会员关联的产品基本信息内容*/
  function submitMemberProductMsg($id){
      //$(".productInformation").addClass("activeStatus").removeClass("inactiveStatus");
      $id.find(".submitbtn").unbind("click");
      $id.find(".submitbtn").click(function(){     
        //检查信息通过后
        if(checkProductMsg($id)){
          var result = getValueForMemberProductInformation($id);
          service.callService("member","buildMemberProducts",[result],function(data){
            if(data.data){
              isAddProduct($id,2);
            }else{
              swal({title:"添加失败",text: "请稍后再试",type:"error",showConfirmButton: true });
            }
          });
        }       
      });
  }
  
  
  
  /**格式化时间函数*/
  function formaterPickerDate ($id){
      var formaterDate = common.formatDate(new Date(),"yyyy-MM-dd");
      $id.datepicker({
        format : "yyyy-mm-dd",
        endDate : formaterDate,
        startDate:"1920-11-11",
        autoclose : true
      }).datepicker('update',formaterDate);
   }
  
  
  /**重置内容*/
  function resetContent($id){
      $id.find(".resetbtn").click(function(){
        clearContent($id);
      });
  }

  
  /**添加多个产品后，添加继续添加产品提示客户函数*/
  function isContinueAddProductInformationForMember($id){
    $id.find(".continueAddProductMsgbtn").click(function(){
      var productInformation = $(".productInformation");
      if(!productInformation.hasClass("activeStatus")){
        $(".productInformation").addClass("activeStatus").removeClass("inactiveStatus");
        $id.find(".continueAddProductMsgbtn").hide();
        $id.find(".submitbtn").show().attr('disabled',"true");
        $id.find(".resetbtn").show().attr('disabled',"true");
      }
    });
  }
  
  /**添加多个产品后，添加继续添加产品提示客户函数*/
  function isContinueAddProductInformationForProduct($id){
    $id.find(".continueAddProductMsgbtn").click(function(){
        $id.find(".continueAddProductMsgbtn").hide();
        $id.find(".submitbtn").show();
        $id.find(".resetbtn").show();
        clearContent($("#collapseTwo"));
        //$("#collapseTwo").find("input").val(""); 
    });
  }
  
  /**在添加完会员信息基本信息后，提示是否添加产品*/
  function isAddProduct($id,type){
    if(type == 1){
      //swal({title:"会员添加成功",text: "可以为用户添加关联产品",type:"success",showConfirmButton: true });
      swal({ 
        title: "会员添加成功", 
        text: "点击添加关联产品", 
        type: "warning", 
        showCancelButton: true, 
        closeOnConfirm: true, 
        confirmButtonText: "添加", 
        cancelButtonText:"取消",
        confirmButtonColor: "#ec6c62" 
       }, function() { 
         $id.find(".continueAddProductMsgbtn").show();
         $id.find(".submitbtn").hide();
         $id.find(".resetbtn").hide();
       });
    }else if(type == 2){
      swal({title:"产品添加成功",text: "可以为继续添加关联产品",type:"success",showConfirmButton: true });
      $id.find(".continueAddProductMsgbtn").show();
      $id.find(".submitbtn").hide();
      $id.find(".resetbtn").hide();
    }
    
      
  }
  
  
  /**退出*/
  function logout($id){
      $id.click(function(){
        swal({ 
          title: "确认退出吗?", 
          text: "退出后，需要重新登录", 
          type: "warning", 
          showCancelButton: true, 
          closeOnConfirm: true, 
          confirmButtonText: "退出", 
          cancelButtonText:"取消",
          confirmButtonColor: "#ec6c62" 
         }, function() { 
           var account = {};
           account.name = $(".profile_info").find("font").html();
           var result = JSON.stringify(account);
           service.callService("account","logout",[result],function(data){
             if(data.data != null && data.data != ""){
               window.location.href="http://"+data.data+":8080/ms-web/"; 
             }else{
               swal({title:"退出失败",text: "服务器繁忙，请稍后再试 ! ",type:"warning",showConfirmButton: true });
             }
           });
         });
        
     });
    
  }
  
  /**获取会员的基本信息*/
  function getValueForMemberBasicInformation($id){
       var memberBasicInformation = {};
       memberBasicInformation.userName=inputValue($id,"name");
       memberBasicInformation.alias=inputValue($id,"alias");
       memberBasicInformation.gender=selectValue($id,"gender");
       memberBasicInformation.cellphoneNumber=inputValue($id,"cellphone");
       memberBasicInformation.email=inputValue($id,"email");
       memberBasicInformation.expression=selectValue($id,"express");
       memberBasicInformation.prefession=inputValue($id,"profession");
       memberBasicInformation.educationBackground=selectValue($id,"backgroundeducation");
       memberBasicInformation.income=selectValue($id,"income");
       memberBasicInformation.height=inputValue($id,"height");
       memberBasicInformation.weight=inputValue($id,"weight");
       memberBasicInformation.age=inputValue($id,"age");
       memberBasicInformation.platform=selectValue($id,"platform");
       memberBasicInformation.birthday=inputValue($id,"birthday");     
       memberBasicInformation.level=selectValue($id,"level");
       memberBasicInformation.address=inputValue($id,"address");
       memberBasicInformation.description=$id.find(".description").val();
       memberBasicInformation.province=selectValue($id,"province");
       memberBasicInformation.city=selectValue($id,"city");
       var area = selectValue($id,"area");
       if(area == "-------- 选择区 --------") area = "";
       memberBasicInformation.area=area;
       return JSON.stringify(memberBasicInformation);
      
    
  }
  
  /**获取会员的基本信息*/
  function getValueForMemberProductInformation($id){
       var productInformation = {};
       productInformation.name=selectValue($id,"productName");
       productInformation.brand=inputValue($id,"brand");
       productInformation.platform=selectValue($id,"platform");
       productInformation.kind=selectValue($id,"kind");
       productInformation.size=selectValue($id,"size");
       productInformation.style=selectValue($id,"style");
       productInformation.price=inputValue($id,"price");
       productInformation.color=selectValue($id,"color");
       productInformation.purchaseDate=inputValue($id,"purchaseDate");
       productInformation.cellphoneNumber=inputValue($id,"cellphone");
       productInformation.expressNumber=inputValue($id,"expressNumber");
       productInformation.expressName=selectValue($id,"expressName");
       productInformation.purchaseShop=selectValue($id,"shopName");
       productInformation.status=selectValue($id,"status");
       productInformation.comments=selectValue($id,"comments");
       productInformation.address=inputValue($id,"address");
       productInformation.commentReason=$id.find(".commentReason").val();
       productInformation.description=$id.find(".description").val();
       productInformation.memberId=$("#memberID").val();
       productInformation.province=selectValue($id,"province");
       productInformation.city=selectValue($id,"city");
       var area = selectValue($id,"area");
       if(area == "-------- 选择区 --------") area = "";
       productInformation.area=area;
       productInformation.productNumber=selectValue($id,"productNumber");
       return JSON.stringify(productInformation);
      
    
  }
  
  /**检查提交会员基本信息的格式是否正确*/
  function checkProductMsg($id){
      /**检查手机格式*/
      if(!common.telephonyNumber(inputValue($id,"cellphone"))){
        swal({title:"手机格式错误",text: "提交失败",type:"error",showConfirmButton: true });
        return false;
      }     
      
      var province = selectValue($id,"province");
      if(province == "" || province == "-------- 选择省 --------"){
        swal({title:"请选择省",text: "提交失败",type:"error",showConfirmButton: true });
        return false;
      }
      
      var city = selectValue($id,"city");
      if(city == "" || city == "-------- 选择市 --------"){
        swal({title:"请选择市",text: "提交失败",type:"error",showConfirmButton: true });
        return false;
      }
      
      /*var area = selectValue($id,"area");
      if(area == "" || area == "-------- 选择区 --------"){
        swal({title:"请选择区",text: "提交失败",type:"error",showConfirmButton: true });
        flag = false;
      }*/
      
      var description = $id.find(".description").val(),len = $id.find(".description").data("length");
      if(common.length(description) > len){
        swal({title:"[描述] 字符过长",text: "提交失败",type:"error",showConfirmButton: true });
        return false;
      }
      
      var commentReason = $id.find(".commentReason").val(),len = $id.find(".commentReason").data("length");
      if(common.length(commentReason) > len){
        swal({title:"[评价理由] 字符过长",text: "提交失败",type:"error",showConfirmButton: true });
        return false;
      }
      

      
      $id.find(".form-group").each(function(i,o){
        var $value = $(o).find("input");
        var type = $value.data("type"),len = $value.data("length"),value = $value.val(),
        key = $(o).find("label").html();
        key = key.replace(":","").replace(/ /g,"");
        if(value == "" || value == null || value == undefined){
          swal({title:"["+key+"] 必填",text: "字符少于 "+len+" !",type:"error",showConfirmButton: true });
          return false;
        }
          
        if(type == "number"){
          if(!common.number(value,len)){
            swal({title:"["+key+"] 格式错误",text: "数字类型，值小于 "+len+" !",type:"error",showConfirmButton: true });
            return false;
          }
        }else if(type == "price"){
          if(!common.double(value,len)){
            swal({title:"["+key+"] 格式错误",text: "数字类型，值小于 "+len+" !",type:"error",showConfirmButton: true });
            return false;
          }
          
        } else{
          if(value != null && value != undefined && value != "")
            if(common.length(value) > len){
              swal({title:"["+key+"] 字符过多",text: "字符少于 "+len+" !",type:"error",showConfirmButton: true });
              return false;
            }
           
        }
      });
      return true;
  }
  
  /**检查提交会员基本信息的格式是否正确*/
  function checkMsg($id){
      /**检查姓名*/
      if(inputValue($id,"name") == ""){
        swal({title:"姓名不能为空",text: "提交失败",type:"error",showConfirmButton: true });
        return false;
      }
      
      /**检查淘宝昵称*/
      if(inputValue($id,"alias") == ""){
        swal({title:"昵称不能为空",text: "提交失败",type:"error",showConfirmButton: true });
        return false;
      }
      
      /**检查手机格式*/
      if(!common.telephonyNumber(inputValue($id,"cellphone"))){
        swal({title:"手机格式错误",text: "提交失败",type:"error",showConfirmButton: true });
        return false;
      }     
      
      var province = selectValue($id,"province");
      if(province == "" || province == "-------- 选择省 --------"){
        swal({title:"请选择省",text: "提交失败",type:"error",showConfirmButton: true });
        return false;
      }
      
      var city = selectValue($id,"city");
      if(city == "" || city == "-------- 选择市 --------"){
        swal({title:"请选择市",text: "提交失败",type:"error",showConfirmButton: true });
        return false;
      }
      
      /*var area = selectValue($id,"area");
      if(area == "" || area == "-------- 选择区 --------"){
        swal({title:"请选择区",text: "提交失败",type:"error",showConfirmButton: true });
        flag =  false;
      }*/
      
      var description = $id.find(".description").val(),len = $id.find(".description").data("length");
      
      if(common.length(description) > len){
        swal({title:"[描述] 字符过长",text: "提交失败",type:"error",showConfirmButton: true });
        return false;
      }
      
      var address = inputValue($id,"address");
      if(address == "" || null == address){
        swal({title:"[详细地址] 必填",text: "提交失败",type:"error",showConfirmButton: true });
        return false;
      }
      $id.find(".form-group").each(function(i,o){
        var $value = $(o).find("input");
        var type = $value.data("type"),len = $value.data("length"),value = $value.val(),
        key = $(o).find("label").html();
        key = key.replace(":","").replace(/ /g,"");
        if(type == "number"){
          value = value == "" ? 0:value;
          if(!common.number(value,len)){
            swal({title:"["+key+"] 格式错误",text: "数字类型，值小于 "+len+" !",type:"error",showConfirmButton: true });
            return false;
          }
        }else{
          if(value != null && value != undefined && value != "")
            if(common.length(value) > len){
              swal({title:"["+key+"] 字符过多",text: "字符少于 "+len+" !",type:"error",showConfirmButton: true });
              return false;
            }
           
        }
      });
      return true;
  }
  
  /**根据id和name的值获取input的value值*/
  function inputValue($id,value){
    return $id.find("input[name='"+value+"']").val();
  }
  
  /**根据id和name的值获取input的value值*/
  function selectValue($id,clazz){
    return $id.find("."+clazz).find("option:selected").text();
  }
  
  function clearContent($id){
    $id.find("input").val("");
    $id.find("textarea").val("");
  }
  
  function initDist($id){
    $id.find(".distpicker").distpicker({
      province: '-------- 选择省 --------',
      city: '-------- 选择市 --------',
      district: '-------- 选择区 --------'
    });
  }
  
});