define(function(require, exports, module) {
  //获取jquery
  var $ = require("jquery");
  var jqueryUi = require("jquery-ui");
  //获取bootstrap
  var bootstrap = require("bootstrap-table");
  //获取custom
  var custom = require("custom");
  //获取lsf的核心方法
  var service = require("service");
  //获取弹出框插件对象
  var sweetAlert = require("sweetAlert");
 
  //获取common包，试用通用方法
  var common = require("common");
  //获取日期插件
  var datePicker = require("datePicker");
  var members = require("modules/index/javascript/member");

  var template = require("template");
  var modal = require("text!../html/modal.html");
  var modalProduct = require("text!../html/modal-product.html");
  var member = require("text!../html/member-content.html");
  var product = require("text!../html/product-content.html");
  //渲染
  var render = template.compile(modal);  
  
  var renderProduct = template.compile(modalProduct);
  //无参数
  var memberHtml = template.compile(member);
  
  var productHtml = template.compile(product);

  //对外暴露的接口
  module.exports = {
          init : function(){
            $member = $("#collapseThree").find("#memberInformationsForTable");          
            common.formaterPickerDates($("#startDate"),common.formatDate(common.dateAdd("m",-1),"yyyy-MM-dd"),null,null);
            common.formaterPickerDates($("#endDate"),null,null,null);
            main.memberForL1();
            util.deleteMember();
            util.queryByDate();
            util.sendMessage();

            
          }        
  }
  
  
  
  
  var $member = null;
  var util = {
     glyphiconFormatter : function(value,row){
       return '<a href="javascript:void(0)" title="展开" data-paramter='+value+'><i class="glyphicon glyphicon-plus icon-plus"></i></a>';
     },
     deleteFormatter : function(value,row,index){
       return "<input data-index='"+index+"' name='btSelectItem' type='checkbox' value='"+row.id+"'>";
     },
     deleteFormatters : function(value,row,index){
       return '<a href="javascript:void(0)" title="删除" class="removeProduct" data-paramter='+value+'><i class="glyphicon glyphicon-remove"></i></a>';
     },
     closeModal : function(){
       //memberModal
       $('#memberModal').find(".closeBtn").click(function(){
         //memberContentEdit
         if(!$('#memberModal').find(".memberContentSubmit").data("status")) {
           $('#memberModal').modal('hide');
           return;
         }
         swal({ 
           title: "未保存", 
           text: "尚未保存，确认离开当前页面 ?", 
           type: "warning", 
           showCancelButton: true, 
           closeOnConfirm: true, 
           confirmButtonText: "离开",
           cancelButtonText:"取消",
           confirmButtonColor: "#ec6c62"            
          }, function() { 
            $('#memberModal').modal('hide');
          });
           
       });
     },
     closeModal2 : function(){
       //memberModal
       $('#memberModal').find(".closeBtnProduct").click(function(){
         //memberContentEdit
         if(!$('#memberModal').find(".submitProduct").data("status")) {
           $('#memberModal').modal('hide');
           return;
         }
         swal({ 
           title: "未保存", 
           text: "尚未保存，确认离开当前页面 ?", 
           type: "warning", 
           showCancelButton: true, 
           closeOnConfirm: true, 
           confirmButtonText: "离开",
           cancelButtonText:"取消",
           confirmButtonColor: "#ec6c62"            
          }, function() { 
            $('#memberModal').modal('hide');
          });
           
       });
     },
     detail :{
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
         +'<td id="member'+i+'" colspan=8>'
         + '<div role="tabpanel" class="tab-pane">'
         +'<table class="table table-striped table-bordered productTable4Member" data-id='+r.id+'></table>'
         + '</div>'
         +'</td>'
           +'</tr>');
         var memberID = r.id;

         //查询用户对应的关联产品的数据
         $("#member"+i).find('table').bootstrapTable({
                 columns:column.columnL2(r.id),
                 url: url.L2MemberUrl,
                 contentType: "application/x-www-form-urlencoded; charset=utf-8",
                 pageSize: 5,
                 method: "post",
                 pageList: [10, 20, 30],
                 pagination: true,
                 queryParams: function(params) {
                   return {
                     memberId : memberID
                   };
                 },
                 onClickCell:function(field,value,row,e){
                   if(field == "9") return;
                   //product当前的row的信息，r是会员信息的r
                   displayProductModal(row,"编辑","edit",r);
                   
                 }
           });
         //触发点击添加关联产品的事件
           util.addRelatedProductForNumber($("#member"+i).find('table').find(".addRelatedProductForMember_InTable"),r);
       
         }
         
     },
     deleteProduct : {
    	    'click .removeProduct': function (e, value, row, index) {
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
                   service.callService("member","removeProduct",[row.id],function(data){
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
     deleteMember : function(){
       $(".deleteBtn").click(function(){
         var deleteObject = $member.bootstrapTable("getSelections");
         var length = deleteObject.length;
         if(length <= 0){
           swal({title:"请勾选需要删除的用户",text: "删除失败",type:"warning",showConfirmButton: true });
         }else{
           swal({ 
             title: "删除会员", 
             text: "删除会员同时删除产品", 
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
              service.callService("member","invalidateMember",[id],function(data){
                if(data.data > 0 ){  
                  $member.bootstrapTable('remove', {
                      field: 'id',
                      values: ids
                  });
                  swal({title:"删除成功",text: "总共删除会员数量为 ["+deleteObject.length+"]",type:"success",showConfirmButton: true });
                }else{
                  swal({title:"删除失败",text: "总共删除会员数量为 [0]",type:"error",showConfirmButton: true });
                }
              });
            });
           
         }
       });
     },
     queryByDate : function(){
       $("#queryMemberInformations").click(function(){
        
         main.memberForL1();
         util.deleteMember();
       });
     },
     sendMessage : function(){
       $(".sendMsgBtn").click(function(){
         
       });
     },
     memberContentEdit : function(id){
       $(".memberContentEdit").click(function(){
          $("#memberContent").find("input").prop("disabled",false);
          $("#memberContent").find("select").prop("disabled",false);
          $("#memberContent").find("textarea").prop("disabled",false);
          $("#memberContent").find("span").css({
            display:'inline'
          });
          $(this).html("提交");
          
          $(this).removeClass("memberContentEdit").addClass("memberContentSubmit");
          $(".memberContentSubmit").unbind("click");
          $(".memberContentSubmit").click(function(){
            //应该在成功后进行修改
            var $id = $("#memberContent");
            if(!$('#memberModal').find(".memberContentSubmit").data("status")){
              swal({title:"无内容被更改",text: "",type:"warning",showConfirmButton: true });
              return;
            }
            if(members.checkMsg($id)){
                var result = members.getValueForMemberBasicInformation($id,id);
                service.callService("member","updateMember",[result],function(data){          
                    if(data.data > 0){
                       $member.bootstrapTable("refresh");
                       swal({title:"更新成功",text: "",type:"success",showConfirmButton: true });
                       $('#memberModal').modal("hide");
                    }else{
                       swal({title:"更新失败",text: "",type:"error",showConfirmButton: true });
                    }
                });
            }
          });
       });
     },
     addRelatedProductForNumber : function($id,r){     
       $id.click(function(){
         displayProductModal(r,"提交","add");
       });
     },
     addRelatedProductSubmit : function(id){
       $("#largeMemberModal").find(".submitProduct").click(function(){
          var $table = $(".productTable4Member");
          var $id = $("#collapseTwo");
          if(members.checkProductMsg($id)){
             var result = members.getValueForMemberProductInformation($id,id);
             service.callService("member","buildMemberProducts",[result],function(data){
               if(data.data > 0){
                 $table.bootstrapTable("refresh");
                 $('#memberModal').modal("hide");
                 swal({title:"添加成功",text: "",type:"success",showConfirmButton: true });
               }else{
                 swal({title:"添加失败",text: "请稍后再试",type:"error",showConfirmButton: true });
               }
             });
          }
        });
     },
     addRelatedProductSubmit2 : function(){
       $("#largeMemberModal").find("#memberContent_edit").click(function(){
          $(this).html("提交");
          $("#memberContent").find("input").prop("disabled",false);
          $("#memberContent").find("select").prop("disabled",false);
          $("#memberContent").find("textarea").prop("disabled",false);
          $(this).unbind("click");
          $(this).click(function(){
        	 if(!$('#memberModal').find(".submitProduct").data("status")){
               swal({title:"无内容被更改",text: "",type:"warning",showConfirmButton: true });
               return;
            }
            var $table = $(".productTable4Member");
            var $id = $("#collapseTwo");
            if(members.checkProductMsg($id)){
              //productId
              var productId = $("#memberModal").find(".productId").val();
              var memberId = $("#memberModal").find(".memberId").val();
              var result = members.getValueForMemberProductInformation($id,memberId);
              service.callService("member","updateProduct",[result,productId],function(data){
                if(data.data > 0){
                  $table.bootstrapTable("refresh");
                  $('#memberModal').modal("hide");
                  swal({title:"修改成功",text: "",type:"success",showConfirmButton: true });
                }else{
                  swal({title:"修改失败",text: "请稍后再试",type:"error",showConfirmButton: true });
                }
              });
           }
          });
       });
    }
     
  }

  /**查询会员信息*/
  var column = {
          columnL1 : function (){
            return [{
              title: '#',
              formatter: util.glyphiconFormatter,
              events: util.detail,
              valign:'middle',
              width:'5%',
              align: 'center',
              
             },{
               field:'id',
               title:'ID',
               width:'8%',
               visible:false
             },{
               field:'userName',
               title:'会员姓名',
               width:'8%'
             },{
               field:'alias',
               title:'昵称',
               width:'10%'
             },{
               field:'gender',
               title:'性别',
               width:'5%',
               sortable:true
             },{
               field:'age',
               title:'年龄',
               width:'5%',
               sortable:true
             },{
               field:'cellphoneNumber',
               title:'手机',
               width:'15%'
             },{
               field:'createdDate',
               title:'日期',
               width:'15%',
               sortable:true,
               formatter : function(value,row,index){
                  return common.formatDate(new Date(value),"yyyy-MM-dd")
               }
             },{
               field:'address',
               title:'地址',
               formatter : function(value,row,inde){
                 var address = row.address;
                 if(null == address || "null" == address){
                   return row.province+" "+row.city+" "+row.area;   
                 }else{
                   return row.province+" "+row.city+" "+row.area+" "+row.address;
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
               field:'brand',
               title:'品牌',
             },{
               field:'platform',
               title:'平台',
             },{
               field:'purchaseShop',
               title:'店铺',
             },{
               field:'cellphoneNumber',
               title:'手机',
             },{
               field:'address',
               title:'地址',
               formatter : function(value,row,inde){
                 var address = row.address;
                 if(null == address || "null" == address){
                   return row.province+" "+row.city+" "+row.area;   
                 }else{
                   return row.province+" "+row.city+" "+row.area+" "+row.address;
                 }
                 
               }
             },{
               field:'purchaseDate',
               title:'日期',

               formatter : function(value,row,index){
                  return common.formatDate(new Date(value),"yyyy-MM-dd")
               }
             },{
               field:'status',
               title:'交易状态',
             },{
               title: '<a  href = "javascript:void(0)" title="添加关联产品"><i class="glyphicon glyphicon-plus-sign addRelatedProductForMember_InTable" data-id='+id+'></i></a>',
               formatter: util.deleteFormatters,
               valign:'middle',
               width:'5%',
               events:util.deleteProduct,
               align: 'center'              
              }];
          }
  }
  
  
  var main = {
          //用户的具体的信息
          memberForL1 : function(){
            $member.bootstrapTable("destroy");
            $(".deleteBtn").remove();
            $(".sendMsgBtn").remove();
            $member.bootstrapTable({
              toolbar:'<button class="btn deleteBtn"> <i class="glyphicon glyphicon-remove"></i><span style="padding-left: 10px;">删除</span></button>'
                +'<button class="btn sendMsgBtn"> <i class="glyphicon glyphicon-envelope"></i><span style="padding-left: 10px;">短信</span></button>',
              columns:column.columnL1(),
              url: url.L1MemberUrl,
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
                  if(field == "0" || field == "9") return;
                  //有参数
                  $('body').find("#largeMemberModal").remove();
                  //会员信息 [{{name}}]
                  $('body').append(render({title:"会员信息 ["+row.userName+"]"}));               
                  //无参数
                  var $content = $("#memberModal").find(".modal-body");
                  $content.html(memberHtml({member:row}));
                  //initDist($("#memberModal"));
                  initDists($("#memberModal"),row.province,row.city,row.area);
                  $("#memberModal").find(".modal-content").draggable();
                  $('#memberModal').modal({
                    backdrop: 'static',
                    keyboard: false
                  });
                  util.memberContentEdit(row.id);
                  members.setExpress($("#memberContent"),row.expression);
                  members.formaterPickerDate($("#memberBirthday"));
                  
                  listenrAllValue($("#memberContent"),$("#memberModal"));
                  //set gender
                  setSelectOptionValue($("#memberContent"),".gender",row.gender);
                  setInputValue($("#memberContent"),".memberBirthday",common.formatDate(new Date(row.birthday),"yyyy-MM-dd"));
                  //express
                  //setSelectOptionValue($("#memberContent"),".express",row.expression);
                  setSelectOptionValue($("#memberContent"),".backgroundeducation",row.backgroundeducation);
                  setSelectOptionValue($("#memberContent"),".income",row.income);
                  setSelectOptionValue($("#memberContent"),".platform",row.platform);
                  setSelectOptionValue($("#memberContent"),".level",row.level);
                  members.setAge($("#memberContent"));
                  util.closeModal();
              }
            });
          }
          
  }
  
  function initDist($id){
    $id.find(".distpicker").distpicker({
      province: '-------- 选择省 --------',
      city: '-------- 选择市 --------',
      district: '-------- 选择区 --------'
    });
  }
  
  
  function initDists($id,province,city,area){
    $id.find(".distpicker").distpicker({
      province: province,
      city: city,
      district: area
    });
  }
  
  
  
  function setSelectOptionValue($id,clazz,value){
    $id.find(clazz).find("option").each(function(i,o){
         if($(o).html()==value) $(o).prop("selected",true);
    });
  }
  
  function setInputValue($id,clazz,value){
    $id.find(clazz).val(value);
  }
  
  function setSelectOptionValue2($id,clazz,value){
    $id.find(clazz).children().remove();
    if(value != null)
     $id.find(clazz).append("<option selected>"+value+"</option>");
  }

  
  function listenrAllValue($id1,$id2){
    listenerInputValue($id1,function(){
      $id2.find("#memberContent_edit").data("status",true);
    });
    listenerSelectValue($id1,function(){
      $id2.find("#memberContent_edit").data("status",true);
    });
    listenerTextAreaValue($id1,function(){
      $id2.find("#memberContent_edit").data("status",true);
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
  
  function displayProductModal(row,btnName,type,r){

    $('body').find("#largeMemberModal").remove();   
    //会员信息 [{{name}}]
    if(type == "add"){      
      $('body').append(renderProduct({title:"关联产品  ["+row.userName+"]",btnName:btnName}));   
    }else{
      $('body').append(renderProduct({title:"关联产品  ["+row.name+"]",btnName:btnName,id:row.id,memberId:row.memberId}));        
    }       
    //无参数
    var $content = $("#memberModal").find(".modal-body");
    $content.html(productHtml({row:row}));

    $("#memberModal").find(".modal-content").draggable();
    $('#memberModal').modal({
      backdrop: 'static',
      keyboard: false
    });
    members.setExpress($("#memberContent"),row.expressName);
    //
    members.formaterPickerDate($("#purchaseDate"));
    if(type == "add"){
      members.setProduct();
      initDists($("#memberModal"));
      util.addRelatedProductSubmit(row.id);
    }else{
      members.setProduct(row);
      initDists($("#memberModal"),row.province,row.city,row.area);
      setSelectOptionValue($("#memberContent"),".platform",row.platform);
      setSelectOptionValue($("#memberContent"),".shopName",row.purchaseShop);
      setSelectOptionValue($("#memberContent"),".status",row.status);
      setSelectOptionValue($("#memberContent"),".comments",row.comments);
      setInputValue($("#memberContent"),".purchaseDate",common.formatDate(new Date(row.purchaseDate),"yyyy-MM-dd"));
      //setSelectOptionValue2($("#memberContent"),".productName",row.name);
      //setSelectOptionValue2($("#memberContent"),".productNumber",row.productNumber);
      $("#memberContent").find("input").prop("disabled",true);
      $("#memberContent").find("select").prop("disabled",true);
      $("#memberContent").find("textarea").prop("disabled",true);
      util.addRelatedProductSubmit2();
    }

    listenrAllValue($("#memberContent"),$("#memberModal"));
    util.closeModal2();
    
  }

  
  
  
  
});