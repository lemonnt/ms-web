 require.config({
    baseUrl:SERVERNAME+"/resources/",
    paths:{
    "jquery": "plugin/jquery/jquery-2.0.2",
    "jquery-ui": "plugin/jquery/jquery-ui.min",
    "jquery-cookie": "plugin/jquery/jquery.cookie",
    "bootstrap": "plugin/bootstrap/js/bootstrap.min",
    "bootstrap-table": "plugin/bootstrap/js/bootstrap-table",
    "custom": "common/javascript/custom",
    "service": "common/javascript/core",
    "sweetAlert": "plugin/sweetalert/sweetalert.min",
    "distpicker":"plugin/distpicker/distpicker",
    "common": "common/javascript/common",
    "datePicker": "plugin/bootstrap-datepicker/js/bootstrap-datepicker",
    "kindEditor": "plugin/kindeditor/kindeditor",
    "chineseLanguage": "plugin/kindeditor/zh_CN",
    "template": "plugin/template/template",
    "text":"plugin/require/text"
    },
    shim: {
         'custom': {
             deps: [
               'jquery'
             ]
         },
         'bootstrap': {
            deps: [
              'jquery', 'jquery-ui'
            ]
         },
         'bootstrap-table': {
           deps: [
             'jquery'
           ]
          },
          'common': {
            deps: [
              'jquery',
              'distpicker',
              'sweetAlert',
              'bootstrap'
            ]
           },
         'distpicker': {
           deps: [
             'jquery'
           ]
         },
         'datePicker': {
           deps: [
             'jquery'
           ]
         }
      }

  
   });
define(function(require){
    var index = require('modules/index/javascript/index');
    var product = require('modules/product/javascript/product');
    index.main();
    product.main();
   
});