define(function(require, exports, module) {
  //获取jquery
  var $ = require("jquery");
  //jqueryUi
  var jqueryUi = require("jquery-ui");
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
  
  var template = require("template");
  
  var modal = require("text!../html/modal.html");
  
  var mainProduct = require("text!../html/main-procurement-product.html");
  //渲染
  var render = template.compile(modal);  

  var mainProductRender = template.compile(mainProduct);

  
  //对外暴露的接口
  module.exports = {
     init : function(){
    	  $basicProductionID = $("#_product_basicProductInformation");
    	  $basicProductionOneID = $("#_product_collapseOne");
    	  $basicProductionTwoID = $("#_product_collapseTwo");
    	  $resetBtn =  $("._product_resetBtn");
    	  $submitBtn =  $("._product_submitBtn");
    	  $continueBtn = $("._product_continueAddBasicProduct");
    	  common.inputToolTip($basicProductionID.find("input"),'focus');
    	  //初始化采购时间
    	  common.formaterPickerDates($("#procurementDate"),null,null,null);
    	  //渲染主产品采购模块
    	  $('#_product_collapseTwo').append(mainProductRender()); 
    	  //采购产品种类选择
    	  procurementType();
    	  setBasicProductDefalueValue();
    	  checkProductInformation();
    	  submitProductMsg();
    	  readdNewSupplier();
    	  resetInput();
    	  listenerAllValue();
		  closeOrRefreshBrowse();
		  

      }        
  }
  
  /**
   * common var
   */
  var isContinueAddProductFlag = false;
  var isContinueAddInsaleProductFlag = false;
  var $basicProductionID = null;
  var $basicProductionOneID = null;
  var $basicProductionTwoID = null;
  var $resetBtn =  null;
  var $submitBtn =  null;
  var $continueBtn = null;
  var isinValidInputValueName = null;
  var isinValidInputValueName4InsaleProduct= null;
  var $insaleProductModal = null;
  var lastprocurementType =  null;

  function listenerAllValue(){
	  listenerValue($basicProductionID);
	  //listenerValue($basicProductionOneID);
	  //listenerValue($basicProductionTwoID);
  }
  /**
   * 采购类型变化的监听函数
   * @returns
   */
  function procurementType(){
	  getLastSelectValue();
	  $basicProductionID.find(".procurementType").change(function(){
		  var isChange = $basicProductionID.find(".supplierHeader").data("change");
		  var $this = $(this);
		  var value = $this.find('option:selected').val();	  
		  if(isChange){			  
			  swal({ 
		           title: "未保存", 
		           text: "尚未保存，确认切换类型 ?", 
		           type: "warning", 
		           showCancelButton: true, 
		           closeOnConfirm: true, 
		           confirmButtonText: "离开",
		           cancelButtonText:"取消",
		           confirmButtonColor: "#ec6c62"            
		          }, function(isConfirm) { 
		        	  if(isConfirm){
		        		  resetValueByprocurementType(value);
			        	  resetAllValue();
		        		 
		        	  }else{
		        		  $this.find("option[value='"+lastprocurementType+"']").prop("selected",true); 
		        	  }
		          });
			  
		  }else{
			  resetValueByprocurementType(value);
			  resetAllValue();
		  }
		  
	  });
  }
  
  function getLastSelectValue(){
	  $basicProductionID.find(".procurementType").focus(function(){
		  lastprocurementType = $(this).find('option:selected').val();
	  });
  }
  
  /**
   * 采购类型改变时触发
   * @param value
   * @returns
   */
  function resetValueByprocurementType(value){
	  $('#_product_collapseTwo').children().remove();
	  if(value == 0){			 
		  $('#_product_collapseTwo').append(mainProductRender()); 
	  }else if(value == 1 || value == 2){		  
		  var assistantProduct = require("text!../html/assistant-procurement-product.html");
		  var assistantProductRender = template.compile(assistantProduct);
		  $('#_product_collapseTwo').append(assistantProductRender());
	  }
	  checkProductInformation();
	  setBasicProductDefalueValue();
	  listenerAllValue();
	  
  }
  /**
   * 检查用户输入的信息是否合法
   * @returns
   */
  function checkProductInformation(){ 
	  $basicProductionID.find("input[name!='procurementDate']").keyup(function(){
		  var flag = common.inputMatcher($(this));		  
		  //自动计算总价格计算总价格
		  if((flag && $(this).prop("name") == "unitPrice")){
			  var unitPrice = $(this).val();
			  var inNumber = $basicProductionID.find("input[name=inNumber]").val();
			  inNumber = (inNumber == null || "" == inNumber) ? 0 : inNumber;
			  var $totalPrice = $basicProductionID.find("input[name=totalPrice]");
			  $totalPrice.val(parseFloat(unitPrice)*parseFloat(inNumber));
			  $totalPrice.trigger("blur");
			  
		  }else if((flag && $(this).prop("name") == "inNumber")){
			  var inNumber = $(this).val();
			  var unitPrice = $basicProductionID.find("input[name=unitPrice]").val();
			  unitPrice = (unitPrice == null || "" == unitPrice) ? 0 : unitPrice;
			  var $totalPrice = $basicProductionID.find("input[name=totalPrice]");
			  $totalPrice.val(parseFloat(unitPrice)*parseFloat(inNumber));
			  $totalPrice.trigger("blur");
		  }
		  
	  });
	  
	  //触发focus，检查字段的功能
	  $basicProductionID.find("input[name!='procurementDate']").focus(function(){
		  var flag = common.inputMatcher($(this));
		  if(!flag){
			  //只取第一个input不符合要求的值
			  if(null == isinValidInputValueName ) isinValidInputValueName = $(this).prop("name");
			  isContinueAddProductFlag = false;
		  }
	  });
	  
	  //触发focus，检查字段的功能
	  $basicProductionID.find("input[name!='procurementDate']").blur(function(){
		  common.inputMatcher($(this));

	  });
	  
	  
	  
  }
  
  /**
   * 设置必要的默认值
   * @returns
   */
  function setBasicProductDefalueValue(){
	  $basicProductionID.find("input[name=purchasingAgent]").val(userName);
	  $basicProductionID.find("input[name=purchasingAgent]").parent().next().find("span").removeClass("glyphicon-asterisk").addClass(" glyphicon-ok").css({
			color:'#4BC181'
	  });
	  //$("#_product_collapseBasic"),"procurementDate"
	  $("#_product_collapseBasic").find("input[name=procurementDate]").parent().next().find("span").removeClass("glyphicon-asterisk").addClass(" glyphicon-ok").css({
			color:'#4BC181'
	  });
  }
  
  /**
   * 提交函数，提交采购产品信息到后台
   * @returns
   */
  function submitProductMsg(){	 
	  $submitBtn.click(function(){
		  isContinueAddProductFlag = true;
		  //检查必要字段是否合法
		  $basicProductionID.find("input[name!='procurementDate']").trigger("focus");
		  if(null != isinValidInputValueName){
			  $basicProductionID.find("input[name="+isinValidInputValueName+"]").trigger("focus");
			  isinValidInputValueName = null;
		  }
		  if(isContinueAddProductFlag && $basicProductionID.find(".supplierHeader").data("change")){
			  var supplier = JSON.stringify(obtainSupplierObject($basicProductionOneID)); 
			  var product = JSON.stringify(obtainProductObject($basicProductionTwoID)); 
			   var procurementType = $basicProductionID.find(".procurementType").find('option:selected').val();
			   disableSubmitBtn();
			   if(procurementType == 0){
				 //立即上架产品			      
					  service.callService("product","createSupplierAndProduct",[supplier,product],function(data){
						if(data.data){				   
						   swal({ 
					           title: "添加成功", 
					           text: "是否立即上架该产品?", 
					           type: "warning", 
					           showCancelButton: true, 
					           closeOnConfirm: true, 
					           confirmButtonText: "立即",
					           cancelButtonText:"取消",
					           confirmButtonColor: "#ec6c62"            
					          }, function(isConfirm) { 
					         	  $basicProductionID.find("#_product_collapseTwo").data("supplierid",data.data.split("#")[0]);
					        	  $basicProductionID.find("#_product_collapseTwo").data("productid",data.data.split("#")[1]);
					        	  if(isConfirm){
					        		//立即上架产品
					        		  insaleProduct();   
					        	  }
					        	  enableSubmitBtn();
					        	  afterSuccess2Add();
					          });
						}else{
							enableSubmitBtn();
							swal({title:"添加失败",text: "请联系管理员",type:"error",showConfirmButton: true });
						}
					  },function(){
						  enableSubmitBtn();
					  });
			   }else{
					  service.callService("product","createSupplierAndAssistantProduct",[supplier,product],function(data){
						if(data.data){				   
							swal({title:"添加成功",text: "",type:"success",showConfirmButton: true });
					        $basicProductionID.find("#_product_collapseTwo").data("supplierid",data.data);
					        afterSuccess2Add();
						}else{
							
							swal({title:"添加失败",text: "请联系管理员",type:"error",showConfirmButton: true });
						}
						enableSubmitBtn();
					  },function(){
						  enableSubmitBtn();
					  });
			   }
    		  
			  
		  }
		 
	  });
  }
  
  function afterSuccess2Add(){
	  $submitBtn.addClass("displayNone");
	  $resetBtn.addClass("displayNone");
	  $continueBtn.addClass("displayBlock");
	  continueAddnewProduct();
	  disableSupplier();
	  //setBasicProductDefalueValue();
	  $basicProductionID.find(".supplierHeader").data("change",false);
  }
  
  function disableSubmitBtn(){
	  $submitBtn.attr("disabled", true);
	  $resetBtn.attr("disabled", true);
  }
  
  function enableSubmitBtn(){
	  $submitBtn.removeAttr("disabled");
	  $resetBtn.removeAttr("disabled");
  }
  
  /**
   * 仅仅提交采购的产品信息，可能一个商家采购多款产品
   * @returns
   */
  function submitOnlyProduct(){
	  $("._product_only_submitBtn").click(function(){
		  isContinueAddProductFlag = true;
		  //检查必要字段是否合法
		  $basicProductionID.find("input[name!='procurementDate']").trigger("focus");
		  if(null != isinValidInputValueName){
			  $basicProductionID.find("input[name="+isinValidInputValueName+"]").trigger("focus");
			  isinValidInputValueName = null;
		  }
		  if(!isContinueAddProductFlag) return;
		  //内容如果没有改变再次执行提交时，禁止提交
		  if($basicProductionID.find(".supplierHeader").data("change")){

		     var product = JSON.stringify(obtainProductObject($basicProductionTwoID));  
		     var procurementType = $basicProductionID.find(".procurementType").find('option:selected').val();
		     disableSubmitBtn();
		     if(procurementType == 0){
		    	 service.callService("product","createProcurementProduct",[product],function(data){
					 if(data.data){
						 swal({ 
					           title: "添加成功", 
					           text: "是否立即上架该产品?", 
					           type: "warning", 
					           showCancelButton: true, 
					           closeOnConfirm: true, 
					           confirmButtonText: "立即",
					           cancelButtonText:"取消",
					           confirmButtonColor: "#ec6c62"            
					          }, function(isConfirm) { 
					        	  $basicProductionID.find("#_product_collapseTwo").data("productid",data.data);
					        	  if(isConfirm){
					        		  debugger;					        		  
					        		//立即上架产品
					        		  insaleProduct();  
					        	  }
					        	  enableSubmitBtn();
					        	  afterSuccess2Add();
					          });
				     }else{
				    	enableSubmitBtn();
					    swal({title:"添加产品失败",text: "请联系管理员",type:"error",showConfirmButton: true });
				     }
				 },function(){
					 enableSubmitBtn();
				 });
		     }else{
		    	 service.callService("product","createProcurementAssistantProduct",[product],function(data){
					 if(data.data){
						 swal({title:"添加成功",text: "",type:"success",showConfirmButton: true });
					     afterSuccess2Add();
				     }else{
					    swal({title:"添加产品失败",text: "请联系管理员",type:"error",showConfirmButton: true });
				     }
					 enableSubmitBtn();
				 },function(){
					 enableSubmitBtn();
				 });
		     }
			 
		  }else{
			  swal({title:"不能重复提交",text: "",type:"warning",showConfirmButton: true });
		  }
		  
	  });
  }
  
  /**
   * 重新添加supplier
   * @returns
   */
  function readdNewSupplier(){
	  $basicProductionID.find(".readdNewSupplier").click(function(){
		  //如果未保存
		  var isChange = $basicProductionID.find(".supplierHeader").data("change");
		  if(isChange){
			  
			  swal({ 
		           title: "未保存", 
		           text: "尚未保存，确认重置内容 ?", 
		           type: "warning", 
		           showCancelButton: true, 
		           closeOnConfirm: true, 
		           confirmButtonText: "离开",
		           cancelButtonText:"取消",
		           confirmButtonColor: "#ec6c62"            
		          }, function() { 
		        	  resetAllValue();
		          });
			  
		  }else{
			  resetAllValue();
			  
		  }
		  
	  });
  }
  
  /**
   * 重置所有值
   * @returns
   */
  function resetAllValue(){
	  releaseInput($basicProductionOneID);
	  releaseInput($basicProductionTwoID);
	  $basicProductionID.find(".supplierHeader").removeClass("glyphicon-lock");	 
	  removeInputContent($basicProductionOneID);
	  removeInputContent($basicProductionTwoID);
	  $submitBtn.removeClass("displayNone");
	  $resetBtn.removeClass("displayNone");
	  $continueBtn.removeClass("displayBlock").addClass("displayNone");
	  $basicProductionID.find("#_product_collapseTwo").data("supplierid","");
	  $submitBtn.unbind("click");
	  $submitBtn.removeClass("_product_only_submitBtn").addClass("_product_submitBtn");
	  submitProductMsg();
	  $basicProductionID.find(".supplierHeader").data("change",false);
  }
  
  /**
   * 继续添加新的产品功能
   * @returns
   */
  function continueAddnewProduct(){
	  $continueBtn.click(function(){
		  releaseInput($basicProductionTwoID);
		  removeInputContent($basicProductionTwoID);
		  $submitBtn.removeClass("displayNone").removeClass("_product_submitBtn").addClass("_product_only_submitBtn");
		  $resetBtn.removeClass("displayNone");
		  $continueBtn.removeClass("displayBlock").addClass("displayNone");
		  $submitBtn.unbind("click");
		  submitOnlyProduct();
	  });
  }
  
  /**
   * 重置input内容
   * @returns
   */
  function resetInput(){
	  $resetBtn.click(function(){
		  if($basicProductionID.find(".supplierHeader").hasClass("glyphicon-lock")){
			  releaseInput($basicProductionTwoID);
			  removeInputContent($basicProductionTwoID);
		  }else{
			  $basicProductionID.find(".readdNewSupplier").trigger("click");
		  }
	  });
  }
  
  function closeOrRefreshBrowse(){
	  window.onbeforeunload = function() {  
          if($basicProductionID.find(".supplierHeader").data("change")){
        	  return "";
          }
     } 
  }
  /**
   * obtain supplier object
   * @param current parent id
   * @returns
   */
  function obtainSupplierObject($id){

    var supplier = {};
	supplier.name=inputValue($id,"supplierName");
	supplier.brand=inputValue($id,"productBrand");
	supplier.contact=inputValue($id,"contactMethod");
	supplier.address=inputValue($id,"supplierAddress");
	supplier.level=selectValue($id,"supplierLevel");
	supplier.description=inputValue($id,"supplierDescription");
	supplier.procurementDate = inputValue($("#_product_collapseBasic"),"procurementDate");
	supplier.updatePeople = userName;
	return supplier;
	  
  }
  
  /**
   * 获取采购的产品对象
   * @param current parent id
   * @returns
   */
  function obtainProductObject($id){
	  var product = {};
	  product.name=inputValue($id,"productName");
	  product.number=inputValue($id,"productNumber");
	  product.productStatus=selectValue($id,"productStatus");
	  product.color=inputValue($id,"productColor");
	  product.style=inputValue($id,"productStyle");
	  product.kind=inputValue($id,"productKind");
	  
	  product.size=inputValue($id,"productSize");
	  product.material=inputValue($id,"productMaterial");
	  product.co=inputValue($id,"productCO");
	  product.productStandard=inputValue($id,"productStandard");
	  product.safetyStandard=inputValue($id,"safetyStandard");
	  product.level=inputValue($id,"productLevel");
	  
	  product.inNumber=inputValue($id,"inNumber");
	  product.unitPrice=inputValue($id,"unitPrice");
	  product.totalPrice=inputValue($id,"totalPrice");
	  product.purchasingAgent=inputValue($id,"purchasingAgent");
	  product.description=inputValue($id,"description");
	  product.procurementDate = inputValue($("#_product_collapseBasic"),"procurementDate");
	  product.procurementType = $basicProductionID.find(".procurementType").find('option:selected').val();
	  if($basicProductionID.find("#_product_collapseTwo").data("supplierid") != "")
		  product.supplierId = $basicProductionID.find("#_product_collapseTwo").data("supplierid");
	  product.updatePeople = userName;
	  return product;
  }
  
  /**根据id和name的值获取input的value值*/
  function inputValue($id,value){
    return $id.find("input[name='"+value+"']").val();
  }
  
  /**根据id和name的值获取input的value值*/
  function selectValue($id,clazz){
    return $id.find("."+clazz).find("option:selected").text();
  }
  
  /**
   * 锁定supplier输入框
   * @returns
   */
  function disableSupplier(){
	  $basicProductionOneID.find("input").attr("disabled","disabled");
	  $basicProductionOneID.find("select").attr("disabled","disabled");
	  $basicProductionID.find(".supplierHeader").addClass("glyphicon-lock");
  }
  
  
  /**
   * 解锁supplier输入框
   * @returns
   */
  function releaseInput($id){
	  $id.find("input").removeAttr("disabled").css("border","1px solid #CCCCCC").parent().next().find("span").removeClass("glyphicon-ok").addClass(" glyphicon-asterisk").css({
			color:'#FF626F'
	  });
	  $id.find("select").removeAttr("disabled").css("border","1px solid #CCCCCC").parent().next().find("span").removeClass("glyphicon-ok").addClass("glyphicon-asterisk").css({
			color:'#FF626F'
	  });
  }
  
  /**
   * 清除supplier的内容
   */
  function removeInputContent($id){
	  $id.find("input").val("");
	  setBasicProductDefalueValue();
  }
  
  function listenerValue($id){
	  listenerInputValue($id,function(){
		  
		  $basicProductionID.find(".supplierHeader").data("change",true);
	  });
	  listenerSelectValue($id,function(){
    	  $basicProductionID.find(".supplierHeader").data("change",true);
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
	$id.find("select.wasChecked").change(function(){
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
  
   
   /************************************************insale-product.js*************************************/
 
   function insaleProduct(){
	   //product_modal
	   if($('body').children('#product_modal')){
			  $('body').children('#product_modal').remove();
		  }
		  $('body').append(render({title:"产品上架",btn1:"取消",btn2:"上架"})); 
		  $insaleProductModal = $("#_modal_productModal");
		  $insaleProductModal.find(".modal-content").draggable();
		  $insaleProductModal.modal({
           backdrop: 'static',
           keyboard: false
         });
		  var insale_product = require("text!../html/insale-product.html");
		  var products = obtainProductObject($basicProductionTwoID);
		  var defaultValue = setDefaultValue(products);
		  $insaleProductModal.find(".modal-body").html(template.compile(insale_product)({product:products,defaultValue:defaultValue}));
		  closeInsaleProductModal();
		  $.when(checkInsaleProduct()).done(function(){
			  listenerInsaleValue($("#_insale_insaleProduct"));
			  insaleSubmit();
			  common.addMultiDivContentFromSelect($(".whereToSale"),$(".whereToSale2"));
		  });
		  
		  
		 
   }
   
   
   
   function closeInsaleProductModal(){
	   $insaleProductModal.find(".closeBtn").click(function(){
		   var isChange = $insaleProductModal.find("#_insale_insaleProduct").data("status");
		   if(isChange){
			   swal({ 
		           title: "未保存", 
		           text: "尚未保存，确认离开当前页面", 
		           type: "warning", 
		           showCancelButton: true, 
		           closeOnConfirm: true, 
		           confirmButtonText: "离开",
		           cancelButtonText:"取消",
		           confirmButtonColor: "#ec6c62"            
		          }, function() {
		               $insaleProductModal.modal('hide'); 
		          });
				  
		   }else{
			   $insaleProductModal.modal('hide');
		   }
	   });
	   
   }
   
   function setDefaultValue(product){
	  var defaultValue = {};
	  defaultValue.brandPrice = product.unitPrice*2.5;
	  defaultValue.saled=0;
	  defaultValue.inventory = product.inNumber - defaultValue.saled;
	  defaultValue.discountPercent = 0.80;
	  defaultValue.salePrice = common.fomatFloat(defaultValue.brandPrice*defaultValue.discountPercent,2);
	  defaultValue.userName = userName;
	  return defaultValue;
	  
   }
   
   function checkInsaleProduct(){
	   $insaleProductModal.find("input").keyup(function(){
			  var flag = common.inputMatcher($(this));		  
			  //自动计算总价格计算总价格
			  if((flag && $(this).prop("name") == "brandPrice")){
				  var brandPrice = $(this).val();
				  var discountPercent = $insaleProductModal.find("input[name=discountPercent]").val();
				  discountPercent = (discountPercent == null || "" == discountPercent) ? 0 : discountPercent;
				  var $salePrice = $insaleProductModal.find("input[name=salePrice]");
				  $salePrice.val(common.fomatFloat(parseFloat(brandPrice)*parseFloat(discountPercent),2));
				  $salePrice.trigger("blur");
				  
			  }else if((flag && $(this).prop("name") == "discountPercent")){
				  var discountPercent = $(this).val();
				  var brandPrice = $insaleProductModal.find("input[name=brandPrice]").val();
				  brandPrice = (brandPrice == null || "" == brandPrice) ? 0 : brandPrice;
				  var $salePrice = $insaleProductModal.find("input[name=salePrice]");
				  $salePrice.val(common.fomatFloat(parseFloat(discountPercent)*parseFloat(brandPrice),2));
				  $salePrice.trigger("blur");
				  //自动计算库存与剩余库存
			  }else if((flag && $(this).prop("name") == "allInventory")){
				  var allInventory = $(this).val();
				  var saled = $insaleProductModal.find("input[name=saled]").val();
				  saled = (saled == null || "" == saled) ? 0 : saled;				  
				  var $inventory = $insaleProductModal.find("input[name=inventory]");
				  var realInventory = allInventory-saled;
				  realInventory = realInventory < 0 ? -1: realInventory;
				  $inventory.val(realInventory);
				  $inventory.trigger("blur");
			  }else if((flag && $(this).prop("name") == "saled")){
				  var saled = $(this).val();
				  var allInventory = $insaleProductModal.find("input[name=allInventory]").val();
				  allInventory = (allInventory == null || "" == allInventory) ? 0 : allInventory;				  
				  var $inventory = $insaleProductModal.find("input[name=inventory]");
				  var realInventory = allInventory-saled;
				  realInventory = realInventory < 0 ? -1: realInventory;
				  $inventory.val(realInventory);
				  $inventory.trigger("blur");
			  }
			  
		  });
		  
		  //触发focus，检查字段的功能
	      $insaleProductModal.find("input").focus(function(){
	    	  var flags = common.inputMatcher($(this));
			  if(!flags){
				  //只取第一个input不符合要求的值
				  if(null == isinValidInputValueName4InsaleProduct ) isinValidInputValueName4InsaleProduct = $(this).prop("name");
				  isContinueAddInsaleProductFlag = false;
			  }
		  });
		  
		  //触发focus，检查字段的功能
	      $insaleProductModal.find("input").blur(function(){
			  common.inputMatcher($(this));

		  });
   }
  
   /**
    * 监听insale 的状态值是否发生变化
    * @param $id
    * @returns
    */
   function listenerInsaleValue($id){
		  listenerInputValue($id,function(){
			  $insaleProductModal.find("#_insale_insaleProduct").data("status",true);
		  });
		  listenerSelectValue($id,function(){
			  
			  $insaleProductModal.find("#_insale_insaleProduct").data("status",true);
		  });
    }
   
   /**
    * insale的product 上架
    * @returns
    */
   function insaleSubmit(){
	   //首先自动判定发布的产品是否符合规范
	   window.setTimeout(function(){
		   $insaleProductModal.find("input").trigger("focus");
	   } , 1000);
	   $insaleProductModal.find(".submitBtn").click(function(){
		   isContinueAddInsaleProductFlag = true;		   
		   $insaleProductModal.find("input").trigger("focus");
		   if(null != isinValidInputValueName4InsaleProduct){
				  $basicProductionID.find("input[name="+isinValidInputValueName4InsaleProduct+"]").trigger("focus");
				  isinValidInputValueName4InsaleProduct = null;
		   }
		   if(!isContinueAddInsaleProductFlag) return;
		   service.callService("product","createInsaleProduct",[JSON.stringify(obtainInsaleProductObject($("#_insale_insaleProduct")))],function(data){
			   if(data.data){
				   $insaleProductModal.find("#_insale_insaleProduct").data("status",false);
				   $insaleProductModal.find(".closeBtn").trigger("click");
				   swal({title:"产品上架成功",text: "",type:"success",showConfirmButton: true });		 
			   }else{
				  swal({title:"上架产品失败",text: "",type:"error",showConfirmButton: true });
			   }
			});

		   
	   });
   }
   
   /**
    * 获取上架产品信息
    * @param $id
    * @returns
    */
   function obtainInsaleProductObject($id){
		  var insaleProduct = {};
		  insaleProduct.name=inputValue($id,"productName");
		  insaleProduct.number=inputValue($id,"productNumber");
		  insaleProduct.brand=inputValue($id,"productBrand");
		  insaleProduct.color=inputValue($id,"productColor");
		  insaleProduct.style=inputValue($id,"productStyle");
		  insaleProduct.kind=inputValue($id,"productKind");
		  
		  insaleProduct.size=inputValue($id,"productSize");
		  insaleProduct.material=inputValue($id,"productMaterial");
		  insaleProduct.co=inputValue($id,"productCO");
		  insaleProduct.productStandard=inputValue($id,"productStandard");
		  insaleProduct.safetyStandard=inputValue($id,"safetyStandard");
		  insaleProduct.level=inputValue($id,"productLevel");
		  //
		  insaleProduct.allInventory=inputValue($id,"allInventory");
		  insaleProduct.saled=inputValue($id,"saled");
		  insaleProduct.inventory=inputValue($id,"inventory");
		  insaleProduct.brandPrice=inputValue($id,"brandPrice");
		  insaleProduct.discountPercent=inputValue($id,"discountPercent");
		  
		  insaleProduct.salePrice=inputValue($id,"salePrice");
		  insaleProduct.purchasingAgent=inputValue($id,"purchasingAgent");
		  insaleProduct.description=inputValue($id,"description");
		  insaleProduct.updatePeople = userName;
		  var saleTo = "";
		  $(".whereToSale2").find(".mutliFromSelect").each(function(i,o){
			  saleTo +=$(o).data("value")+";";
		  });
		  
		  insaleProduct.whereToSale= "" == saleTo ? selectValue($id,"whereToSale") : saleTo;
		  //if($basicProductionID.find("#_product_collapseTwo").data("supplierid") != "")
			//  insaleProduct.supplierId = $basicProductionID.find("#_product_collapseTwo").data("supplierid");
		  if($basicProductionID.find("#_product_collapseTwo").data("productid") != "")
			  insaleProduct.productId = $basicProductionID.find("#_product_collapseTwo").data("productid");
		  return insaleProduct;
	  }
   
	  
});