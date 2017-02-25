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
  
  var $superMember = $("#superMember");
  
  var template = require("template");
  
  var modal = require("text!../html/modal.html");
  //渲染
  var render = template.compile(modal);  


  
  //对外暴露的接口
  module.exports = {
     init : function(){
    	 common.formaterPickerDates($("#startDate"),common.formatDate(common.dateAdd("m",-1),"yyyy-MM-dd"),null,null);
         common.formaterPickerDates($("#endDate"),null,null,null);
         $informationModal = $("#information_modal").find("#informationModal_panelBody");
         createTable();
         util.showAllSupplierProduct();
         util.showAllInsaleProduct();
         util.showAllProduct();
      }        
  }
  
  var $informationModal = null;
  var $productTable = null;
  var $resetBtn =  null;
  var $submitBtn =  null;
  var lastprocurementType =  null;
  var $allProductTable= null;
  var $allInsaleProductTable = null;
  var $procudurementProductTable = null;
  /**
   * create L1 for the table
   * @returns
   */
  function createTable(){
	  $informationModal.append("<div role='tabpanel' class='tab-pane' id='productShowTableOuter'>" +
	  		"<table id='productShowTable' class='table table-bordered table-striped'> " +
	  		"</table> </div>");
	  $productTable = $informationModal.find("#productShowTable");
	  tableMain.supplierForL1();
      util.queryByDate();
      util.deleteSupplier();
      util.addSupplier();
  }
  
  function createTableForAllSupplierProduct(){
	  $informationModal.find("#informationModal_panelBody").children().remove();
	  $informationModal.append("<div role='tabpanel' class='tab-pane' id='productShowTableOuter'>" +
	  		"<table id='productShowTable' class='table table-bordered table-striped'> " +
	  		"</table> </div>");
	  $allProductTable = $informationModal.find("#productShowTable");
	  tableMain.showAllSupplierProduct();
	  util.addSupplier();
	  util.queryByDateForAllSupplier();
	  util.deleteAllProductSupplier();
	  
  }
  
  function createTableForAllInsaleProduct(){
	  $informationModal.find("#informationModal_panelBody").children().remove();
	  $informationModal.append("<div role='tabpanel' class='tab-pane' id='productShowTableOuter'>" +
	  		"<table id='productShowTable' class='table table-bordered table-striped'> " +
	  		"</table> </div>");
	  $allInsaleProductTable = $informationModal.find("#productShowTable");
	  tableMain.showAllInsaleProduct();
	  util.addSupplier();
	  util.deleteAllInsaleProduct();
	  util.queryByDateForAllInsaleProduct();
	  //util.deleteAllProductSupplier();
	  
  }
  
  var util = {
	 glyphiconFormatter : function(value,row){
		 return '<a href="javascript:void(0)" title="展开" data-paramter='+value+'><i class="glyphicon glyphicon-plus icon-plus"></i></a>';
	 },
	 deleteFormatter : function(value,row,index){
		 return "<input data-index='"+index+"' name='btSelectItem' type='checkbox' value='"+row.id+"'>";
	 },
	 deleteFormatters : function(value,row,index){
		return '<a href="javascript:void(0)" title="删除" class="removeSupplierProduct" data-paramter='+value+'><i class="glyphicon glyphicon-remove"></i></a>';
	 },
	 productDetail :{
	       'click .icon-plus' : function(e,v,r,i){
		         e.stopPropagation();
		         var $this = $(this),$tr = $this.parents('tr'),$child=$tr.next('.child');
		         if($this.hasClass('glyphicon-minus icon-minus')){
		           $child.hide();
		           $this.removeClass('glyphicon-minus icon-minus').addClass('glyphicon-plus icon-plus');
		           return;
		         }
		         $this.removeClass('glyphicon-plus icon-plus').addClass('glyphicon-minus icon-minus');
		         if($child.length>0){
		           $child.show();
		           return;
		         }
		         $tr.after('<tr class="child">'
		             +'<td></td>'
		         +'<td id="product_'+i+'" colspan=8>'
		         + '<div role="tabpanel" class="tab-pane">'
		         +'<table class="table table-striped table-bordered supplier4Product" data-id='+r.id+'></table>'
		         + '</div>'
		         +'</td>'
		           +'</tr>');
		         var supplierId = r.id;
		         tableMain.supplierForProduct($("#product_"+i).find('table'),supplierId);
		         $procudurementProductTable = $("#product_"+i).find('table');
		         $("#product_"+i).find('table').find(".addRelatedProductForSupplier_InTable").click(function(){
		        	 r.title="添加产品";
		        	 r.btn1="取消";
		        	 r.btn2="确定";
		        	 util.showSupplierProduct(r,$("#product_"+i).find('table'));
		         })
	         }
	         
	     },
	     deleteSupplierProduct : {
	    	 'click .removeSupplierProduct': function (e, value, row, index) {
	             swal({ 
	               title: "删除产品", 
	               text: "删除不可恢复!", 
	               type: "warning", 
	               showCancelButton: true, 
	               closeOnConfirm: true, 
	               confirmButtonText: "继续",
	               cancelButtonText:"取消",
	               confirmButtonColor: "#ec6c62"            
	              }, function() { 
	                service.callService("product","invalidSupplierProduct",[row.id],function(data){
	                  if(data.data > 0 ){  
	                    $(e.target).parent().parent().parent().parent().parent().bootstrapTable('remove', {
	                       field: 'id',
	                       values: [row.id]
	                    });
	                    swal({title:"删除成功",text: "名称为 ["+row.name+"]",type:"success",showConfirmButton: true });
	                  }else{
	                    swal({title:"删除失败",text: "总共删除会员数量为 [0]",type:"error",showConfirmButton: true });
	                  }
	                });
	                
	              });
	            
	             
	           } 
	     },
	     queryByDate : function(){
	    	 $("#qryformations_btn").unbind("click");
	       $("#qryformations_btn").click(function(){
	        
	    	 tableMain.supplierForL1();
	    	 util.addSupplier();
	         util.deleteSupplier();
	       });
	     },
	     queryByDateForAllSupplier : function(){
	    	 $("#qryformations_btn").unbind("click");
		       $("#qryformations_btn").click(function(){
		    	 tableMain.showAllSupplierProduct();
		    	 util.deleteAllProductSupplier();
		    	 util.addSupplier();
		       });
		 },
	     queryByDateForAllInsaleProduct : function(){
	        $("#qryformations_btn").unbind("click");
		       $("#qryformations_btn").click(function(){
		    	tableMain.showAllInsaleProduct();
		    	util.addSupplier();
		    	util.deleteAllInsaleProduct();
		    });
		 },
	     deleteSupplier : function(){
	       $(".deleteBtn").unbind("click");
	       $(".deleteBtn").click(function(){
	         var deleteObject = $productTable.bootstrapTable("getSelections");
	         var length = deleteObject.length;
	         if(length <= 0){
	           swal({title:"请勾选需要删除的供应商",text: "删除失败",type:"warning",showConfirmButton: true });
	         }else{
	           swal({ 
	             title: "删除供应商", 
	             text: "删除供应商同时删除产品", 
	             type: "warning", 
	             showCancelButton: true, 
	             closeOnConfirm: true, 
	             confirmButtonText: "继续",
	             cancelButtonText:"取消",
	             confirmButtonColor: "#ec6c62"            
	            }, function() { 
	              var id = "";
	              var ids = new Array();
	              $.each(deleteObject,function(i,o){
	                if(i == length-1){
	                  id +=o.id;
	                }else{
	                  id +=o.id+",";
	                }
	                ids[i] = o.id;
	              });
	              service.callService("product","invalidSupplier",[id],function(data){
	                if(data.data){  
	                	$productTable.bootstrapTable('remove', {
	                      field: 'id',
	                      values: ids
	                  });
	                  swal({title:"删除成功",text: "总共删除供应商数量为 ["+deleteObject.length+"]",type:"success",showConfirmButton: true });
	                }else{
	                  swal({title:"删除失败",text: "总共删除供应商数量为 [0]",type:"error",showConfirmButton: true });
	                }
	              });
	            });
	           
	         }
	       });
	     },
	     deleteAllProductSupplier : function(){
		       $(".deleteBtn").unbind("click");
		       $(".deleteBtn").click(function(){
		         var deleteObject = $allProductTable.bootstrapTable("getSelections");
		         var length = deleteObject.length;
		         if(length <= 0){
		           swal({title:"请勾选需要删除的产品",text: "删除失败",type:"warning",showConfirmButton: true });
		         }else{
		           swal({ 
		             title: "删除产品", 
		             text: "产品删除不可恢复", 
		             type: "warning", 
		             showCancelButton: true, 
		             closeOnConfirm: true, 
		             confirmButtonText: "继续",
		             cancelButtonText:"取消",
		             confirmButtonColor: "#ec6c62"            
		            }, function() { 
		              var id = "";
		              var ids = new Array();
		              $.each(deleteObject,function(i,o){
		                if(i == length-1){
		                  id +=o.id;
		                }else{
		                  id +=o.id+",";
		                }
		                ids[i] = o.id;
		              });
		              service.callService("product","invalidSupplierProduct",[id],function(data){
		                if(data.data){  
		                	$allProductTable.bootstrapTable('remove', {
		                      field: 'id',
		                      values: ids
		                  });
		                  swal({title:"删除成功",text: "总共删除产品数量为 ["+deleteObject.length+"]",type:"success",showConfirmButton: true });
		                }else{
		                  swal({title:"删除失败",text: "总共删除产品数量为 [0]",type:"error",showConfirmButton: true });
		                }
		              });
		            });
		           
		         }
		       });
		     }
	     ,
	     deleteAllInsaleProduct : function(){
		       $(".deleteBtn").unbind("click");
		       $(".deleteBtn").click(function(){
		    	   debugger;
		         var deleteObject = $allInsaleProductTable.bootstrapTable("getSelections");
		         var length = deleteObject.length;
		         if(length <= 0){
		           swal({title:"请勾选需要删除的产品",text: "删除失败",type:"warning",showConfirmButton: true });
		         }else{
		           swal({ 
		             title: "删除产品", 
		             text: "产品删除不可恢复", 
		             type: "warning", 
		             showCancelButton: true, 
		             closeOnConfirm: true, 
		             confirmButtonText: "继续",
		             cancelButtonText:"取消",
		             confirmButtonColor: "#ec6c62"            
		            }, function() { 
		              var id = "";
		              var ids = new Array();
		              $.each(deleteObject,function(i,o){
		                if(i == length-1){
		                  id +=o.id;
		                }else{
		                  id +=o.id+",";
		                }
		                ids[i] = o.id;
		              });
		              service.callService("product","invalidInsaledProduct",[id],function(data){
		                if(data.data){  
		                	$allInsaleProductTable.bootstrapTable('remove', {
		                      field: 'id',
		                      values: ids
		                  });
		                  swal({title:"删除成功",text: "总共删除产品数量为 ["+deleteObject.length+"]",type:"success",showConfirmButton: true });
		                }else{
		                  swal({title:"删除失败",text: "总共删除产品数量为 [0]",type:"error",showConfirmButton: true });
		                }
		              });
		            });
		           
		         }
		       });
		     },
	     addCoreModule : function(row,html){
	    	 if($('body').children('#product_modal')){
				  $('body').children('#product_modal').remove();
			  }
			  $('body').append(render({title:row.title,btn1:row.btn1,btn2:row.btn2})); 
			  $productModal = $("#_modal_productModal");
			  $productModal.find(".modal-content").draggable();
			  $productModal.modal({
	           backdrop: 'static',
	           keyboard: false
	         });
			  $productModal.find(".modal-body").html(html);
			  $resetBtn =  $productModal.find(".closeBtn");
			  $submitBtn =  $productModal.find(".submitBtn");;
	     },
	     showSupplierProduct : function(row,$table){
	    	  var productModal = require("text!../html/main-procurement-product2.html");
	    	  //渲染
	    	  var productModalRender = template.compile(productModal);  

	    	  
	    	  this.addCoreModule(row,productModalRender());
	    	  
	    	  var mainProduct = require("text!../html/management_main_product.html");
	    	  
	    	  $("#_modal_productModal").find(".main_product_").html(template.compile(mainProduct)());
	    	  procurementType($("#_modal_productModal"));
	    	  this.closeModal();
	    	  setBasicProductDefalueValue($("#_modal_productModal"));
	    	  common.formaterPickerDates($("#procurementDate"),null,null,null);
	    	  common.listenerInputValue($("#_modal_productModal"),function(){
	    		  $("#_modal_productModal").data("status",true);
	    	  });
	    	  this.checkSupplierProduct($("#_modal_productModal"));
	    	  addSupplierProduct($("#_modal_productModal"),row.id,$table);    	
	    	  
	     },
	     closeModal : function(){
	    	 $("#_modal_productModal").find(".closeBtn").click(function(){
	   		   var isChange = $("#_modal_productModal").data("status");
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
	   		        	 $("#_modal_productModal").modal('hide'); 
	   		          });
	   				  
	   		   }else{
	   			  $("#_modal_productModal").modal('hide');
	   		   }
	   	   });
	     },
	     checkSupplierProduct : function($id){
	    	 $id.find("input[name!='procurementDate']").keyup(function(){
	   		  var flag = common.inputMatcher($(this));	
	   		  if(!flag) throw new Error("Failed 2 check input content []");
	   		  //自动计算总价格计算总价格
	   		  if((flag && $(this).prop("name") == "unitPrice")){
	   			  var unitPrice = $(this).val();
	   			  var inNumber = $id.find("input[name=inNumber]").val();
	   			  inNumber = (inNumber == null || "" == inNumber) ? 0 : inNumber;
	   			  var $totalPrice = $id.find("input[name=totalPrice]");
	   			  $totalPrice.val(parseFloat(unitPrice)*parseFloat(inNumber));
	   			  $totalPrice.trigger("blur");
	   			  
	   		  }else if((flag && $(this).prop("name") == "inNumber")){
	   			  var inNumber = $(this).val();
	   			  var unitPrice = $id.find("input[name=unitPrice]").val();
	   			  unitPrice = (unitPrice == null || "" == unitPrice) ? 0 : unitPrice;
	   			  var $totalPrice = $id.find("input[name=totalPrice]");
	   			  $totalPrice.val(parseFloat(unitPrice)*parseFloat(inNumber));
	   			  $totalPrice.trigger("blur");
	   		  }
	   		  
	   		 if((flag && $(this).prop("name") == "brandPrice")){
				  var brandPrice = $(this).val();
				  var discountPercent = $id.find("input[name=discountPercent]").val();
				  discountPercent = (discountPercent == null || "" == discountPercent) ? 0 : discountPercent;
				  var $salePrice = $id.find("input[name=salePrice]");
				  $salePrice.val(common.fomatFloat(parseFloat(brandPrice)*parseFloat(discountPercent),2));
				  $salePrice.trigger("blur");
				  
			  }else if((flag && $(this).prop("name") == "discountPercent")){
				  var discountPercent = $(this).val();
				  var brandPrice = $id.find("input[name=brandPrice]").val();
				  brandPrice = (brandPrice == null || "" == brandPrice) ? 0 : brandPrice;
				  var $salePrice = $id.find("input[name=salePrice]");
				  $salePrice.val(common.fomatFloat(parseFloat(discountPercent)*parseFloat(brandPrice),2));
				  $salePrice.trigger("blur");
				  //自动计算库存与剩余库存
			  }
	   		  
	   	  });
	   	  
	   	  //触发focus，检查字段的功能
	    	 $id.find("input[name!='procurementDate']").focus(function(){
	   		  if(!common.inputMatcher($(this))) throw new Error("Failed 2 check input content []");

	   	  });
	   	  
	   	  //触发focus，检查字段的功能
	    	 $id.find("input[name!='procurementDate']").blur(function(){
	   		 if(!common.inputMatcher($(this))) throw new Error("Failed 2 check input content []");

	   	  });
	     },
	     addSupplier : function(){
	    	 $(".addSupplier").click(function(){	    		  
	    		  var productHtml = require("text!../html/product.html");
	    		  var productAdd = require('modules/product/javascript/product-add');
	    		  $superMember.html(template.compile(productHtml));
	              productAdd.init();
	              $($("#productManagementModule").find("li")[0]).addClass("hoverInColor").removeClass("hoverOutColor");
	              $($("#productManagementModule").find("li")[1]).removeClass("hoverInColor").addClass("hoverOutColor");
	    	 });
	     },
	     showAllSupplierProduct : function(){
	    	 $(".procurementProduct").click(function(){
	    		 createTableForAllSupplierProduct();
	    	 })
	     },
	     showAllInsaleProduct : function(){
	    	 $(".productInsale").click(function(){
	    		 createTableForAllInsaleProduct();
	    	 })
	     },
	     showAllProduct : function(){
	    	 $(".allProductContent").click(function(){
	    		 createTable();
	    	 })
	     },
  }
	
  /**
   * 列名
   */
  var column = {
          columnL1 : function (){
            return [{
              title: '#',
              formatter: util.glyphiconFormatter,
              events: util.productDetail,
              valign:'middle',
              width:'5%',
              align: 'center',
              
             },{
               field:'id',
               title:'ID',
               width:'8%',
               visible:false
             },{
               field:'name',
               title:'商家名称',
               width:'23%'
             },{
               field:'brand',
               title:'商家品牌',
               width:'13%'
             },{
               field:'level',
               title:'等级',
               width:'5%',
               formatter : function(value,row,inde){
              	 var level = row.level;
              	 
                   if(level == "优秀"){
                  	 return "<div class='lineShade' style='width：40%;height:60%;background:#1CCB58;color:white;text-align:center;'>优秀</div>";
                   }else if(level == "良好"){
                  	 return "<div class='lineShade' style='width：40%;height:60%;background:#7CC10A;color:white;text-align:center;'>良好</div>";
                   }else if(level == "合格"){
                  	return "<div class='lineShade' style='width：40%;height:60%;background:#FCAD31;color:white;text-align:center;'>合格</div>"; 
                   }else{
                	   return "<div class='lineShade' style='width：40%;height:60%;background:#D9534F;color:white;text-align:center;'>黑名单</div>";
                   }
                   
                 },
               sortable:true
             },{
               field:'procurementDate',
               title:'日期',
               width:'10%',
               sortable:true,
               formatter : function(value,row,index){
                  return common.formatDate(new Date(value),"yyyy-MM-dd")
               }
             },{
               field:'address',
               title:'地址'
             },{
               field:'contact',
               title:'联系方式'
             },{
               title: "<input name='btSelectAll' type='checkbox'>",
               formatter: util.deleteFormatter,
               valign:'middle',
               width:'5%',
               align: 'center'
               
              }];
          },
          columnL2 : function (id){
            return [
            {
              field:'id',
              title:'ID',
              width:'8%',
              visible:false
            },{
               field:'name',
               title:'名称',
             },{
               field:'productStatus',
               title:'供货状态',
             },{
               field:'unitPrice',
               title:'单价(￥)',
             },{
               field:'inNumber',
               title:'库存',
             },{
               field:'purchasingAgent',
               title:'采购人',
             },{
               field:'status',
               title:'状态',
               formatter : function(value,row,inde){
            	 var isPassed = row.status;
            	 
                 if(isPassed == 1){
                	 return "通过";
                 }else if(isPassed == 0){
                	 return "审核中";
                 }else{
                	return "不通过"; 
                 }
                 
               }
             },{
               field:'procurementDate',
               title:'日期',
               formatter : function(value,row,index){
                  return common.formatDate(new Date(value),"yyyy-MM-dd")
               }
             },{
               field:'isInsaled',
               title:'交易状态',
               width:'8%',
               formatter : function(value,row,index){
            	   if(row.procurementType == 1 || row.procurementType == 2) return "-";
            	   var status = row.isInsaled;
            	   if(status == 1){
                  	 return "<div class='lineShade' style='width：40%;height:60%;background:#39C41A;color:white;text-align:center;'>上架</div>";
                   }else{
                  	 return "<div class='lineShade' style='width：40%;height:60%;background:#FCAD31;color:white;text-align:center;'>未上架</div>";
                   }
                   
                   
                 }
             },{
                 field:'procurementType',
                 title:'采购种类',
                 formatter : function(value,row,inde){
                	 var status = row.procurementType;
                     if(status == 0){
                    	 return "主品";
                     }else if(status == 1){
                    	 return "辅材";
                     }else{
                    	 return "其他";
                     }
                     
                   }
               },{
               title: '<a  href = "javascript:void(0)" title="添加关联产品"><i class="glyphicon glyphicon-plus-sign addRelatedProductForSupplier_InTable" data-id='+id+'></i></a>',
               formatter: util.deleteFormatters,
               valign:'middle',
               width:'5%',
               events:util.deleteSupplierProduct,
               align: 'center'              
              }];
          },
          columnLForAllSupplierProduct : function (id){
              return [
              {
                field:'id',
                title:'ID',
                width:'8%',
                visible:false
              },{
                 field:'name',
                 title:'名称',
               },{
                 field:'productStatus',
                 title:'供货状态',
               },{
                 field:'unitPrice',
                 title:'单价(￥)',
               },{
                 field:'inNumber',
                 title:'库存',
               },{
                 field:'purchasingAgent',
                 title:'采购人',
               },{
                 field:'status',
                 title:'状态',
                 formatter : function(value,row,inde){
              	 var isPassed = row.status;
              	 
                   if(isPassed == 1){
                  	 return "通过";
                   }else if(isPassed == 0){
                  	 return "审核中";
                   }else{
                  	return "不通过"; 
                   }
                   
                 }
               },{
                 field:'procurementDate',
                 title:'日期',
                 formatter : function(value,row,index){
                    return common.formatDate(new Date(value),"yyyy-MM-dd")
                 }
               },{
                 field:'isInsaled',
                 title:'交易状态',
                 width:'8%',
                 formatter : function(value,row,index){
              	   if(row.procurementType == 1 || row.procurementType == 2) return "-";
              	   var status = row.isInsaled;
                     if(status == 1){
                    	 return "<div class='lineShade' style='width：40%;height:60%;background:#39C41A;color:white;text-align:center;'>上架</div>";
                     }else{
                    	 return "<div class='lineShade' style='width：40%;height:60%;background:#FCAD31;color:white;text-align:center;'>未上架</div>";
                     }
                     
                   }
               },{
                   field:'procurementType',
                   title:'采购种类',
                   formatter : function(value,row,inde){
                  	 var status = row.procurementType;
                       if(status == 0){
                      	 return "主品";
                       }else if(status == 1){
                      	 return "辅材";
                       }else{
                      	 return "其他";
                       }
                       
                     }
                 },{
                     title: "<input name='btSelectAll' type='checkbox'>",
                     formatter: util.deleteFormatter,
                     valign:'middle',
                     width:'5%',
                     align: 'center'
                     
                 }];
            },
            columnLForAllInsaleProduct : function (id){
                return [
                {
                  field:'id',
                  title:'ID',
                  width:'8%',
                  visible:false
                },{
                    field:'brand',
                    title:'品牌',
                },{
                   field:'name',
                   title:'名称',
                 },{
                   field:'number',
                   title:'编号',
                 },{
                   field:'allInventory',
                   title:'总存',
                 },{
                   field:'saled',
                   title:'已售',
                 },{
                   field:'inventory',
                   title:'库存',
                 },{
                     field:'salePrice',
                     title:'售价',
                 },{
                     field:'whereToSale',
                     title:'平台',
                 },{
                   field:'allInventory-inventory',
                   title:'货源',
                   formatter : function(value,row,inde){
                	 var inventory = row.inventory;
                	 
                     if(inventory <= 0){
                    	 return "<div class='lineShade' style='width：40%;height:60%;background:#D9534F;color:white;text-align:center;'>断货</div>";
                     }else if(inventory <= 5 && inventory > 0){
                    	 return "<div class='lineShade' style='width：40%;height:60%;background:#FCAD31;color:white;text-align:center;'>紧缺</div>";
                     }else{
                    	return "<div class='lineShade' style='width：40%;height:60%;background:#39C41A;color:white;text-align:center;'>充足</div>"; 
                     }
                     
                   }
                 },{
                     field:'status',
                     title:'状态',
                     formatter : function(value,row,inde){
                  	 var status = row.status; 
                       if(status == 0){
                      	 return "<div class='lineShade' style='width：40%;height:60%;background:#D9534F;color:white;text-align:center;'>下架</div>";
                       }else{
                      	return "<div class='lineShade' style='width：40%;height:60%;background:#39C41A;color:white;text-align:center;'>上架</div>"; 
                       }
                       
                     }
                   },{
                   field:'createDate',
                   title:'日期',
                   formatter : function(value,row,index){
                      return common.formatDate(new Date(value),"yyyy-MM-dd")
                   }
                 },{
                       title: "<input name='btSelectAll' type='checkbox'>",
                       formatter: util.deleteFormatter,
                       valign:'middle',
                       width:'5%',
                       align: 'center'
                       
                   }];
              }
  }

  
  var tableMain = {
		  supplierForL1 : function(){
			  $productTable.bootstrapTable("destroy");
	            $(".deleteBtn").remove();
	            $(".sendMsgBtn").remove();
			  $productTable.bootstrapTable({
	              toolbar:'<button class="btn deleteBtn"> <i class="glyphicon glyphicon-remove"></i><span style="padding-left: 10px;">删除</span></button>'
	                +'<button class="btn addSupplier sendMsgBtn"> <i class="glyphicon glyphicon-pencil"></i><span style="padding-left: 10px;">添加</span></button>',
	              columns:column.columnL1(),
	              url: url.L1procurementProductUrl,
	              showRefresh:true,
	              showColumns: true,
	              //searchOnEnterKey: true,
	              search: true,
	              contentType: "application/x-www-form-urlencoded; charset=utf-8",
	              pageSize: 20,
	              pageNumber: 1,
	              showToggle:true,
	              method: "post",
	              pageList: [20, 40, 60, 100, 200, 300],
	              pagination: true,
	              sidePagination:'server',
	              queryParams: function(params) {
	                return {
	                  startDate:$('#startDate').val(),endDate:$('#endDate').val(),limit:params.limit,offset:params.offset,search:params.search,order:params.order,sort:params.sort
	                };
	              },
	              onLoadSuccess : function(data,e){
	                //util.deleteMember(data);
	              },
	              onClickCell:function(field,value,row,e){
	            	  if(field == 0) return;
	            	  jumpSupplier(row);
	            	  
	              }
	                  
	              });
	          },
	          supplierForProduct : function($id,supplierId){
	        	  $id.bootstrapTable("destroy");
	        	  $id.bootstrapTable({
		              columns:column.columnL2(),
		              url: url.L2procurementProductUrl,
		              //showRefresh:true,
		              //showColumns: true,
		              //search: true,
		              contentType: "application/x-www-form-urlencoded; charset=utf-8",
		              pageSize: 20,
		              pageNumber: 1,
		              method: "post",
		              pageList: [5, 10, 20, 40, 80, 100],
		              pagination: true,
		              sidePagination:'server',
		              queryParams: function(params) {
		                return {
		                	supplierId:supplierId,limit:params.limit,offset:params.offset,search:params.search,order:params.order,sort:params.sort
		                };
		              },
		              onLoadSuccess : function(data,e){
		                
		              },
		              onClickCell:function(field,value,row,e){
		            	  if(field == 10) return;
		            	  if("isInsaled" == field && 0 == value){
		            		  //主产品采购
		            		  if(row.procurementType == 0){
		            			  setDefaultForSetProductInsale(row);
			            		  //未上架，弹出上架modal
			            		  setProductInsale(row);
		            		  }else{
		            			  swal({title:"辅材产品不可上架",text: "上架失败 !",type:"warning",showConfirmButton: true });
		            		  }
		            		  
		            	  }else if("isInsaled" == field && 1 == value){
		            		  //弹出已经上架产品信息
		            	  }else{

		            		  jumpSupplierProduct(row,$procudurementProductTable);
		            	  }
		            	  
		              }
		                  
		              });
	          },
	          showAllSupplierProduct : function(){
	        	  $allProductTable.bootstrapTable("destroy");
	        	  $(".deleteBtn").remove();
		          $(".sendMsgBtn").remove();
	        	  $allProductTable.bootstrapTable({
	        		  toolbar:'<button class="btn deleteBtn"> <i class="glyphicon glyphicon-remove"></i><span style="padding-left: 10px;">删除</span></button>'
	  	                +'<button class="btn addSupplier sendMsgBtn"> <i class="glyphicon glyphicon-pencil"></i><span style="padding-left: 10px;">添加</span></button>',
		              columns:column.columnLForAllSupplierProduct(),
		              url: url.showAllSupplierProduct,
		              showRefresh:true,
		              showColumns: true,
		              search: true,
		              showToggle:true,
		              contentType: "application/x-www-form-urlencoded; charset=utf-8",
		              pageSize: 20,
		              pageNumber: 1,
		              method: "post",
		              pageList: [5, 10, 20, 40, 80, 100],
		              pagination: true,
		              sidePagination:'server',
		              queryParams: function(params) {
		                return {
		                	startDate:$('#startDate').val(),endDate:$('#endDate').val(),limit:params.limit,offset:params.offset,search:params.search,order:params.order,sort:params.sort
		                };
		              },
		              onLoadSuccess : function(data,e){
		                //util.deleteMember(data);
		              },
		              onClickCell:function(field,value,row,e){
		            	  if("isInsaled" == field || field == 10) return;
		            	  jumpSupplierProduct(row,$allProductTable);
		              }
		                  
		              });
	          },
	          showAllInsaleProduct : function(){
	        	  $allInsaleProductTable.bootstrapTable("destroy");
	        	  $(".deleteBtn").remove();
		          $(".sendMsgBtn").remove();
		          $allInsaleProductTable.bootstrapTable({
	        		  toolbar:'<button class="btn deleteBtn"> <i class="glyphicon glyphicon-remove"></i><span style="padding-left: 10px;">删除</span></button>'
	  	                +'<button class="btn addSupplier sendMsgBtn"> <i class="glyphicon glyphicon-pencil"></i><span style="padding-left: 10px;">添加</span></button>',
		              columns:column.columnLForAllInsaleProduct(),
		              url: url.showAllInsaleProduct,
		              showRefresh:true,
		              showColumns: true,
		              search: true,
		              showToggle:true,
		              contentType: "application/x-www-form-urlencoded; charset=utf-8",
		              pageSize: 20,
		              pageNumber: 1,
		              method: "post",
		              pageList: [5, 10, 20, 40, 80, 100],
		              pagination: true,
		              sidePagination:'server',
		              queryParams: function(params) {
		                return {
		                	startDate:$('#startDate').val(),endDate:$('#endDate').val(),limit:params.limit,offset:params.offset,search:params.search,order:params.order,sort:params.sort
		                };
		              },
		              onLoadSuccess : function(data,e){
		                //util.deleteMember(data);
		              },
		              onClickCell:function(field,value,row,e){
		            	  jumpInsaleProduct(row);
		              }
		                  
		              });
	          }
	          
  }
  
  /**
   * 获取采购的产品对象
   * @param current parent id
   * @returns
   */
  function obtainProductObject($id,supplierId,procurementType){
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
	  product.procurementDate = inputValue($id,"procurementDate");
	  product.procurementType = procurementType;
	  product.supplierId = supplierId;
	  product.updatePeople = userName;
	  return product;
  }
  
  function addSupplierProduct($basicProductionID,supplierId,$table){
	  $submitBtn.click(function(){
		  var flag = true;
		  //检查必要字段是否合法
		  try{
			  $basicProductionID.find("input[name!='procurementDate']").trigger("blur");
			  var procurementType = $("#_modal_productModal").find('option:selected').val();
			  var product = JSON.stringify(obtainProductObject($basicProductionID,supplierId,procurementType));  
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
						        	  afterSuccess($table);

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
							 afterSuccess($table);
							 swal({title:"添加成功",text: "",type:"success",showConfirmButton: true });
					     }else{
							 enableSubmitBtn();
						    swal({title:"添加产品失败",text: "请联系管理员",type:"error",showConfirmButton: true });
					     }

					 },function(){
						 enableSubmitBtn();
					 });
			     }
		  }catch(e){
			  //alert(e);
		  }
		  
	  });
  }
  
  function afterSuccess($table){
	  $table.bootstrapTable("refresh");
	  $("#_modal_productModal").data("status",false);
	  $('#_modal_productModal').modal("hide");
  }
  
  function disableSubmitBtn(){
	  $submitBtn.attr("disabled", true);
	  $resetBtn.attr("disabled", true);
  }
  
  function enableSubmitBtn(){
	  $submitBtn.removeAttr("disabled");
	  $resetBtn.removeAttr("disabled");
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
   * 设置必要的默认值
   * @returns
   */
  function setBasicProductDefalueValue($basicProductionID){
	  $basicProductionID.find("input[name=purchasingAgent]").val(userName);
	  $basicProductionID.find("input[name=purchasingAgent]").parent().next().find("span").removeClass("glyphicon-asterisk").addClass(" glyphicon-ok").css({
			color:'#4BC181'
	  });
	  $basicProductionID.find("input[name=procurementDate]").parent().next().find("span").removeClass("glyphicon-asterisk").addClass(" glyphicon-ok").css({
			color:'#4BC181'
	  });
	  
  }
  
  /**
   * 采购类型变化的监听函数
   * @returns
   */
  function procurementType($basicProductionID){
	  getLastSelectValue($basicProductionID);
	  $basicProductionID.find(".procurementType").change(function(){
		  var isChange = $("#_modal_productModal").data("status");
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
		        		  resetValueByprocurementType($basicProductionID,value);	 
		        	  }else{
		        		  $this.find("option[value='"+lastprocurementType+"']").prop("selected",true); 
		        	  }
		          });
			  
		  }else{
			  resetValueByprocurementType($basicProductionID,value);
		  }
		  
	  });
  }
  
  function getLastSelectValue($basicProductionID){
	  $basicProductionID.find(".procurementType").focus(function(){
		  lastprocurementType = $(this).find('option:selected').val();
	  });
  }
  
  /**
   * 采购类型改变时触发
   * @param value
   * @returns
   */
  function resetValueByprocurementType($basicProductionID,value){
	  var realContentArea = $basicProductionID.find(".modal-body").find(".main_product_");
	  realContentArea.children().remove();
	  if(value == 0){		
		  var mainProduct = require("text!../html/management_main_product.html");	  
		  realContentArea.html(template.compile(mainProduct)());
	  }else if(value == 1 || value == 2){		  
		  var assistantProduct = require("text!../html/assistant-procurement-product2.html");	  
		  realContentArea.html(template.compile(assistantProduct)());
	  }
	  setBasicProductDefalueValue($("#_modal_productModal"));
	  common.listenerInputValue($("#_modal_productModal"),function(){
		  $("#_modal_productModal").data("status",true);
	  });
	  util.checkSupplierProduct($("#_modal_productModal"));
  }
  
  function jumpSupplier(row){
	  jumpShowContent(row,"供应商",require("text!../html/edit_supplier.html"),function(){
		  var supplier = obtainSupplierObject($("#_modal_productModal"),row);
		  service.callService("product","updateSupplier",[JSON.stringify(supplier)],function(data){
                if(data.data){  
                  $productTable.bootstrapTable("refresh");
                  $("#_modal_productModal").data("status",false);
                  $("#_modal_productModal").modal('hide');
                  swal({title:"更新成功",text: "",type:"success",showConfirmButton: true });
                  enableSubmitBtn();
                }else{
                  swal({title:"更新失败",text: "请联系管理员!",type:"error",showConfirmButton: true });
                  enableSubmitBtn();
                }
          },function(){
        	  enableSubmitBtn();
          });
	  },function(){
    	  /*if(row.purchasingAgent != userName){
    		  $("#_modal_productModal").find(".submitBtn").prop("disabled",true);
    	  }*/
		  $("#_modal_productModal").find(".supplierLevel").find("option[value='"+row.level+"']").prop("selected",true);
	  });
  }


  function jumpSupplierProduct(row,$table){
	  jumpShowContent(row,"采购产品",require("text!../html/edit_supplier_product.html"),function(){
		  var supplier = obtainProductObjects($("#_modal_productModal"),row);
		  
		  service.callService("product","updateSupplierProduct",[JSON.stringify(supplier)],function(data){
                if(data.data){  
                  $table.bootstrapTable("refresh");
                  $("#_modal_productModal").data("status",false);
                  $("#_modal_productModal").modal('hide');
                  swal({title:"更新成功",text: "",type:"success",showConfirmButton: true });
                  enableSubmitBtn();
                }else{
                  swal({title:"更新失败",text: "请联系管理员!",type:"error",showConfirmButton: true });
                  enableSubmitBtn();
                }
          },function(){
        	  enableSubmitBtn();
          });
	  },function(){
		  $("#_modal_productModal").find(".productStatus").find("option[value='"+row.productStatus+"']").prop("selected",true);
	  });
  }

  function setProductInsale(row){
	  jumpShowContent(row,"上架产品",require("text!../html/edit_insale_product.html"),function(){
		  var insaleProduct = obtainInsaleProductObject($("#_modal_productModal"),row);	
		  insaleProduct.id = null;
		  service.callService("product","createInsaleProduct",[JSON.stringify(insaleProduct)],function(data){
                if(data.data){  
                	$procudurementProductTable.bootstrapTable("refresh");
                  $("#_modal_productModal").data("status",false);
                  $("#_modal_productModal").modal('hide');
                  swal({title:"更新成功",text: "",type:"success",showConfirmButton: true });
                  enableSubmitBtn();
                }else{
                  swal({title:"更新失败",text: "请联系管理员!",type:"error",showConfirmButton: true });
                  enableSubmitBtn();
                }
          },function(){
        	  enableSubmitBtn();
          });
	  },function(){
		  common.addMultiDivContentFromSelect($(".whereToSale"),$(".whereToSale2"));
          //设置发布到的平台
		  if(row.whereToSale){
			  var platForms = row.whereToSale.split(";");
			  var formLength = platForms.length;
    		  $.each(platForms,function(i,o){
    			  if(formLength == 1){
    				  addPlatForm($(".whereToSale2"),o);
    			  }else{
    				  if(i != formLength -1)
            			   addPlatForm($(".whereToSale2"),o); 
    			  }
    			  
    		  });
		  }
		  $("#_modal_productModal").find(".whereToSale").find("option[value='电商平台']").prop("selected",true);
		  
	  });
  }

  
  function jumpInsaleProduct(row){
	  jumpShowContent(row,"上架产品",require("text!../html/edit_insale_product.html"),function(){
		  var insaleProduct = obtainInsaleProductObject($("#_modal_productModal"),row);	  
		  service.callService("product", "updateInsaleProduct",[JSON.stringify(insaleProduct)],function(data){
                if(data.data){  
                	$allInsaleProductTable.bootstrapTable("refresh");
                  $("#_modal_productModal").data("status",false);
                  $("#_modal_productModal").modal('hide');
                  swal({title:"更新成功",text: "",type:"success",showConfirmButton: true });
                  enableSubmitBtn();
                }else{
                  swal({title:"更新失败",text: "请联系管理员!",type:"error",showConfirmButton: true });
                  enableSubmitBtn();
                }
          },function(){
        	  enableSubmitBtn();
          });
	  },function(){
		  common.addMultiDivContentFromSelect($(".whereToSale"),$(".whereToSale2"));
          //设置发布到的平台
		  if(row.whereToSale){
			  var platForms = row.whereToSale.split(";");
			  var formLength = platForms.length;
    		  $.each(platForms,function(i,o){
    			  if(formLength == 1){
    				  addPlatForm($(".whereToSale2"),o);
    			  }else{
    				  if(i != formLength -1)
            			   addPlatForm($(".whereToSale2"),o); 
    			  }
    			  
    		  });
		  }
		  
		  $("#_modal_productModal").find(".productStatus").find("option[value='"+row.productStatus+"']").prop("selected",true);
	  });
  }
  
  /**
   * 
   * @param row current value for the row
   * @param title the title of modal
   * @param html  
   * @param callMethod callback method
   * @returns
   */
  function jumpShowContent(row,title,html,callMethod,setDefaultValue){
	  var supplierTemplate = template.compile(html);
	  row.btn1="取消";
	  row.btn2="编辑";
	  row.title=title;
	  util.addCoreModule(row,supplierTemplate({row:row}));
      $.when(listerEidtInputValue()).done(function(){
    	  if(typeof setDefaultValue === "function"){
    		  setDefaultValue();
    	  }	
      });
	  
	  util.closeModal();
	  editShowContent(row,callMethod);
  }
  
  function listerEidtInputValue(){
	  common.listenerInputValue($("#_modal_productModal"),function(){
		  $("#_modal_productModal").data("status",true);
	  });
	  $(".edit_outer").find(".glyphicon").css({display:'none'});
  }
  
  /**
   * edit the modal and submit the result 
   * @param row
   * @param callMethod
   * @returns
   */
  function editShowContent(row,callMethod){
	  var submitBtn = $("#_modal_productModal").find(".submitBtn");
	  submitBtn.unbind('click');
	  submitBtn.click(function(){
		  $("#_modal_productModal").find('input').removeAttr("disabled");
		  $("#_modal_productModal").find('select').removeAttr("disabled");
		  $("#_modal_productModal").find(".glyphicon").css({display:'inline'});
		  $(this).removeClass("submitBtn").addClass("confirmBtn").html("提交");
		  util.checkSupplierProduct($("#_modal_productModal"));
		  $submitBtn = $("#_modal_productModal").find(".confirmBtn");
		  $resetBtn = $("#_modal_productModal").find(".closeBtn");
		  $(".whereToSale2").removeClass("disabled");
		  $(".confirmBtn").unbind('click');
		  $(".confirmBtn").click(function(){
			  try{
				  $("#_modal_productModal").find("input").trigger("blur");
				  if(!$("#_modal_productModal").data("status")){
					  swal({title:"无内容更新",text: "更新失败!",type:"warning",showConfirmButton: true });
				  }else{
					  disableSubmitBtn();
					  //调用更新方法
					  if(typeof callMethod === "function"){
						  callMethod();
					  }				 
				  }
			  }catch(e){
				  //alert(e);
				 // alert(e);
			  }
			  
			  
			 
		  });
	  });
  }
 
	  
  /**
   * obtain supplier object
   * @param current parent id
   * @returns
   */
  function obtainSupplierObject($id,row){

    var supplier = {};
	supplier.name=inputValue($id,"supplierName");
	supplier.brand=inputValue($id,"productBrand");
	supplier.contact=inputValue($id,"contactMethod");
	supplier.address=inputValue($id,"supplierAddress");
	supplier.level=selectValue($id,"supplierLevel");
	supplier.description=inputValue($id,"supplierDescription");
	supplier.procurementDate = common.formatDate(new Date(row.procurementDate));
	supplier.createDate = row.createDate;
	supplier.id = row.id;
	supplier.updatePeople = userName;
	return supplier;
	  
  }
  
  /**
   * 获取采购的产品对象
   * @param current parent id
   * @returns
   */
  function obtainProductObjects($id,row){
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
	  product.updatePeople = userName;
      product.id = row.id;
	  return product;
  }
  
  function addPlatForm($div,value){
	  $div.append("<div class='mutliFromSelect' data-value='"+value+"'>&nbsp;"+value+"&nbsp;&nbsp;<span class='glyphicon glyphicon-remove'></span>&nbsp;</div>");
	  var multiFromSelectHeight = ($div.height()-$(".mutliFromSelect").height())/2;
		   $(".mutliFromSelect").css({
			   "line-height":"24.4px",
			   "margin-top":'4px'
			   
		   });

  }
  
  /**
   * 获取上架产品信息
   * @param $id
   * @returns
   */
  function obtainInsaleProductObject($id,row){
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
		  insaleProduct.productId = row.productId;
		  insaleProduct.id = row.id;
		  insaleProduct.whereToSale= "" == saleTo ? selectValue($id,"whereToSale") : saleTo;
		  return insaleProduct;
	  }
  
  function setDefaultForSetProductInsale(row){
	  row.allInventory = row.inNumber;
	  row.productId=row.id;
	  row.brand=row.name;
	  row.saled = 0;
	  row.brandPrice = row.unitPrice*2.5;
	  row.inventory = row.inNumber;
	  row.discountPercent = 0.80;
	  
	  row.salePrice = common.fomatFloat(row.brandPrice*row.discountPercent,2);
  }
  
});