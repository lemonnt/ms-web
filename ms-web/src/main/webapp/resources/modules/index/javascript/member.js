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
  
  var isSubmitForMember = true;
  
  //对外暴露的接口
  module.exports = {
          init : function(){
          //退出函数
            logout($(".logOutBtn"));
            //loading 快递信息
            setExpress($("#collapseOne"));
           // setExpress($("#collapseTwo"));
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
            addMemberBtn($("#memeberCreate"));
            listenrAllValue($("#collapseOne"));
            listenrAllValue($("#collapseTwo"));
            
          },
          setExpress : function($id,realValue){
            setExpress($id,realValue);
          },
          formaterPickerDate : function($id){
            formaterPickerDate($id);
          },
          checkMsg : function($id){
            return checkMsg($id);
          },
          getValueForMemberBasicInformation: function($id,id){
            return getValueForMemberBasicInformation($id,id);
          },
          setProduct : function(row){
            setProduct(row);
          },
          checkProductMsg : function($id){
            return checkProductMsg($id);
          },
          getValueForMemberProductInformation : function ($id,id){
            return getValueForMemberProductInformation($id,id);
          },
          setAge : function($id){
            setAge($id);
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
  function setProductOther($id,row){
    setProductOtherService($id,createProductForOther($id),row);
  }
  
  //根据产品名称和货号确定品牌名称
  function setProductBrand($id,row){
    //setProductBrandService($id,createProductForBrand($id));
    $id.find(".productNumber").change(function(){
      var products = {};
      products.name =$id.find(".productName").find("option:selected").text();
      products.number = $(this).find("option:selected").text();
      var result = JSON.stringify(products);
      setProductOther($id,row);
      //setProductBrandService($id,result);
    });
  }
  
  //设置货号
  function setProductNumber($id,v,row){
    debugger;
    if(row != null) v = row.name;
    setProductNumberService($id,createProduct(v),row);
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
    if(typeof(v) ==  "string"){
      products.name = v;
    }else{
      products.name =v.name;
    }
    
    return JSON.stringify(products);
  }
  
  //初始化由产品名称，货号和品牌确定时查询其他对象时创建的产品对象
  function createProductForOther($id){
    var products = {};
    products.name =$id.find(".productName").find("option:selected").text();
    products.number = $id.find(".productNumber").find("option:selected").text();
    return JSON.stringify(products);
  }
  
  //
  function setProductNumberService($id,result,row){
    service.syncCallService("member","product",[result],function(data){
      var value = data.data;
      if(value != null){
        $id.find(".productNumber").children().remove();      
        $.each(value,function(i,o){
          if(null != row && row.productNumber == o.number){
            $id.find(".productNumber").append("<option selected>"+o.number+"</option>"); 
          }else{
            $id.find(".productNumber").append("<option>"+o.number+"</option>"); 
          }
          
        });        
        setProductOtherService($id,createProductForOther($id),row);
      }
      
    });
  }
  
  function setProductOtherService($id,result,row){
    debugger;
    service.syncCallService("member","product",[result],function(data){
      var value = data.data[0];
      //种类
      if(value.kind != null){
        $id.find(".kind").children().remove();
        var v = value.kind.split(",");
        $.each(v,function(i,o){
          if(row != null && row.kind == o){            
            $id.find(".kind").append("<option selected>"+row.kind+"</option>");
          }else{
            $id.find(".kind").append("<option>"+o+"</option>");
          }           
        });
      }
      //颜色
      if(value.color != null){
        $id.find(".color").children().remove();  
        var v = value.color.split(",");
        $.each(v,function(i,o){
          if(row != null && row.color == o){
            $id.find(".color").append("<option selected>"+row.color+"</option>");
          }else{
            $id.find(".color").append("<option>"+o+"</option>");
          }          
        });
       
      }
      //款式
      if(value.style != null){
        $id.find(".style").children().remove();
        var v = value.style.split(",");
        $.each(v,function(i,o){
          if(row != null && row.style == o){
            $id.find(".style").append("<option selected>"+row.style+"</option>");
          }else{
            $id.find(".style").append("<option>"+o+"</option>");
          }          
        });
        
      }
      //尺寸
      if(value.size != null){
        $id.find(".size").children().remove();
        var v = value.size.split(",");
        $.each(v,function(i,o){
          if(row != null && row.size == o){
            $id.find(".size").append("<option selected>"+row.size+"</option>");
          }else{
            $id.find(".size").append("<option>"+o+"</option>");
          }          
        });
      }
      
      //价格
      if(value.outprice != null){
        if(row != null && row.price != null){
          $id.find("input[name='price']").val(row.price);
        }else{
          $id.find("input[name='price']").val(value.outprice);
        }        
      }
      
      //品牌
      if(value.brand != null){
        if(row != null && row.brand != null){
          $id.find("input[name='brand']").val(row.brand);
        }else{
          $id.find("input[name='brand']").val(value.brand);
        }
      }

    });
  }
  
  //加载快递信息，从数据库
  function setExpress($id,realValue){
    var expressCookies = common.getCookies("express");
    if(expressCookies != null){
       var expressArray =  JSON.parse(expressCookies);
       //设置会员快递
       configuration(expressArray,$id,"express",realValue);      
       //设置产品快递
       configuration(expressArray,$("#collapseTwo"),"expressName",realValue);
    }else{
        service.callService("member","express",[],function(data){
          var value = data.data;
          if(value != null){
            common.cookies("express",JSON.stringify(value),30);
            //设置会员快递
            configuration(value,$id,"express",realValue);      
            //设置产品快递
            configuration(value,$("#collapseTwo"),"expressName",realValue);
          }
        });
    }
    
    
  }
  
  //加载产品信息，从数据库，row用于传入默认值
  function setProduct(row){
    var productNameCookies = common.getCookies("produtName");
    if(productNameCookies != null){
      var value =  JSON.parse(productNameCookies);
      setProductDetail(value,row);
    }else{
      service.callService("member","productName",[],function(data){
        var value = data.data;
        if(value != null && value.length > 0){
          common.cookies("produtName",JSON.stringify(value),30);
          setProductDetail(value,row);
        }
      });
    }
    
  }
  
  function setProductDetail(value,row){
    configurationForProduct(value,$("#collapseTwo"),"productName",row);
    var selectedValue = value[0];
    setProductNumber($("#collapseTwo"),selectedValue,row);
    setProductBrand($("#collapseTwo"),row);
    setProductOther($("#collapseTwo"),row);
  }

  
  function configuration(value,$id,clazz,realValue){
    $id.find("."+clazz).children().remove();
    $.each(value,function(i,o){
      if(null != realValue && undefined != realValue && realValue == o.name){
        $id.find("."+clazz).append("<option selected>"+o.name+"</option>")
      }else{
        $id.find("."+clazz).append("<option>"+o.name+"</option>")
      }
      
    });
    
  }
  
  function configurationForProduct(value,$id,clazz,row){
    $id.find("."+clazz).children().remove();
    //var existValue = setSelectValue($id,clazz);
    if(row != null && undefined != row){
      $.each(value,function(i,o){
        if(row.name == o.name){
          $id.find("."+clazz).append("<option selected>"+o.name+"</option>");
        }else{
          $id.find("."+clazz).append("<option>"+o.name+"</option>");
        }
        
        
      });
    }else{
      $.each(value,function(i,o){
         $id.find("."+clazz).append("<option>"+o.name+"</option>");
      });
    }
    
  }
  
  /**提交会员基本信息内容*/
  function submitMemberBasicMsg($id){
      $id.find(".submitbtn").click(function(){
        //检查信息通过后
        if(checkMsg($id)){
          var result = getValueForMemberBasicInformation($id);
          service.callService("member","buildMember",[result],function(data){          
            if(data.data != null){
              isAddProduct($id,1);
              $("#memberID").val(data.data);
              $id.find(".submitbtn").data("status",false);
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
              $id.find(".submitbtn").data("status",false);
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
  function getValueForMemberBasicInformation($id,id){
       var memberBasicInformation = {};
       if(id != null || id != undefined) memberBasicInformation.id=id;
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
       if(checkSelectCode($id,"area")){
         memberBasicInformation.area = "";
       }else{
         memberBasicInformation.area=selectValue($id,"area");
       }
       return JSON.stringify(memberBasicInformation);
      
    
  }
  
  /**获取会员的基本信息*/
  function getValueForMemberProductInformation($id,id){
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
       if(id == null || id == undefined){
         productInformation.memberId=$("#memberID").val();
       }else{
         productInformation.memberId=id;
       }
       
       productInformation.province=selectValue($id,"province");
       productInformation.city=selectValue($id,"city");
       if(checkSelectCode($id,"area")){
         productInformation.area = "";
       }else{
         productInformation.area=selectValue($id,"area");
       }
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

      if(checkSelectCode($id,"province")){
        swal({title:"请选择省",text: "提交失败",type:"error",showConfirmButton: true });
        return false;
      }

      if(checkSelectCode($id,"city")){
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
      

      var flag = true;
      $id.find(".form-group").each(function(i,o){
        var $value = $(o).find("input");
        var type = $value.data("type"),len = $value.data("length"),value = $value.val(),
        key = $(o).find("label").html();
        key = key.replace(":","").replace(/ /g,"");
        if(value == "" || value == null || value == undefined){
          swal({title:"["+key+"] 必填",text: "字符少于 "+len+" !",type:"error",showConfirmButton: true });
          flag = false;
          return;
        }
          
        if(type == "number"){
          if(!common.number(value,len)){
            swal({title:"["+key+"] 格式错误",text: "数字类型，值小于 "+len+" !",type:"error",showConfirmButton: true });
            flag = false;
            return;
          }
        }else if(type == "price"){
          if(!common.double(value,len)){
            swal({title:"["+key+"] 格式错误",text: "数字类型，值小于 "+len+" !",type:"error",showConfirmButton: true });
            flag = false;
            return;
          }
          
        } else{
          if(value != null && value != undefined && value != "")
            if(common.length(value) > len){
              swal({title:"["+key+"] 字符过多",text: "字符少于 "+len+" !",type:"error",showConfirmButton: true });
              flag = false;
              return;
            }
           
        }
      });
      return flag;
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
      
      //var province = selectValue($id,"province");
      if(checkSelectCode($id,"province")){
        swal({title:"请选择省",text: "提交失败",type:"error",showConfirmButton: true });
        return false;
      }
      
      //var city = selectValue($id,"city");
      //alert(checkSelectCode($id,"city"));
      if(checkSelectCode($id,"city")){
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
      var flag = true;
      $id.find(".form-group").each(function(i,o){
        var $value = $(o).find("input");
        var type = $value.data("type"),len = $value.data("length"),value = $value.val(),
        key = $(o).find("label").html();
        key = key.replace(":","").replace(/ /g,"");
        if(type == "number"){
          value = value == "" ? 0:value;
          if(!common.number(value,len)){
            swal({title:"["+key+"] 格式错误",text: "数字类型，值小于 "+len+" !",type:"error",showConfirmButton: true });
            flag = false;
            return;
          }
        }else{
          if(value != null && value != undefined && value != "")
            if(common.length(value) > len){
              swal({title:"["+key+"] 字符过多",text: "字符少于 "+len+" !",type:"error",showConfirmButton: true });
              flag = false;
              return;
            }
           
        }
      });
      return flag;
  }
  
  function checkSelectCode($id,clazz){
    return "" == $id.find("."+clazz).find("option:selected").data('code');
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
  
  function addMemberBtn($id){
    $id.find(".addMemberBtn").click(function(){
       var memberSubmitStatus = $id.find(".submitbtn").data("status");
       var productSubmitStatus = $id.find("#collapseTwo").find(".submitbtn").data("status");
       if(memberSubmitStatus || ($(".productInformation").hasClass("activeStatus") && productSubmitStatus)){
         swal({ 
           title: "信息未保存", 
           text: "确认离开当前页面 ?", 
           type: "warning", 
           showCancelButton: true, 
           closeOnConfirm: true, 
           confirmButtonText: "离开",
           cancelButtonText:"取消",
           confirmButtonColor: "#ec6c62"            
          }, function() { 
            enableMember();
          });
       }else{
            enableMember();
       }
    });
  }
  
  function enableMember(){
    var $parent = $("#memeberCreate").find("#collapseOne");
    var $submit = $parent.find(".submitbtn"),$resetBtn = $parent.find(".resetbtn");
    $parent.find(".continueAddProductMsgbtn").hide();
    $parent.find(".submitbtn").show();
    $parent.find(".resetbtn").show();
    $submit.removeAttr('disabled');
    $resetBtn.removeAttr('disabled');
    clearContent($("#collapseOne"));
    if($(".productInformation").hasClass("activeStatus")){
      $(".productInformation").addClass("inactiveStatus").removeClass("activeStatus");
    }
  }
  
  function listenrAllValue($id){
    listenerInputValue($id,function(){
      $id.find(".submitbtn").data("status",true);
    });
    listenerSelectValue($id,function(){
      $id.find(".submitbtn").data("status",true);
    });
    listenerTextAreaValue($id,function(){
      $id.find(".submitbtn").data("status",true);
    });
  }
  
  
  function listenerInputValue($id,callback){
    $id.find("input").change(function(){
       if(typeof callback === "function"){
         callback();
       }
    });
  }
  
  function listenerSelectValue($id,callback){
    $id.find("select").change(function(){
      if(typeof callback === "function"){
        callback();
      }
    });
  }
  
  function listenerTextAreaValue($id,callback){
    $id.find("textarea").change(function(){
      if(typeof callback === "function"){
        callback();
      }
    });
  }
  
  //clazz must be select
  function setSelectValue($id,clazz){
    var $existValue = $id.find("."+clazz).children();
    if($existValue.length == 1){
       return $existValue.html();
    }else{
       $id.find("."+clazz).children().remove();
       return null;
    }
  }
});