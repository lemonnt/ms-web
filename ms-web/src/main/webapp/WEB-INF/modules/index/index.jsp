<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@include file="../webroot.inc"%>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Management System (柠檬网络科技) </title>

    <!-- Bootstrap -->
    <link href="<c:url value='/resources/plugin/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
    <!-- Font Awesome "<c:url value='/resources/common/css/custom.min.css'/>"-->
    <link href="<c:url value='/resources/plugin/font-awesome/font-awesome.min.css'/>" rel="stylesheet">
    <!-- NProgress -->
    <link href="<c:url value='/resources/common/css/nprogress.css'/>" rel="stylesheet">
    <!-- iCheck -->
    <link href="<c:url value='/resources/plugin/iCheck/skins/flat/green.css'/>" rel="stylesheet">
    <!-- bootstrap-progressbar -->
    <link href="<c:url value='/resources/plugin/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css'/>" rel="stylesheet">
    
    <link href="<c:url value='/resources/plugin/bootstrap-datepicker/css/bootstrap-datepicker.css'/>" rel="stylesheet">
    <!-- JQVMap -->
    <link href="<c:url value='/resources/plugin/jqvmap/jqvmap.min.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/resources/plugin/sweetalert/sweetalert.css'/>" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="<c:url value='/resources/common/css/custom.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/common/css/common.css'/>" rel="stylesheet">
    
   <!--  <link href="<c:url value='/resources/modules/index/css/index.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/modules/product/css/product.css'/>" rel="stylesheet"> -->
    <link href="<c:url value='/resources/plugin/bootstrap/css/bootstrap-table.css'/>" rel="stylesheet">
  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>柠檬网络科技</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile">
              <div class="profile_pic">
                <img src="<c:url value='/resources/modules/index/images/picture.jpg'/>" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>昵称 : <font>${userName}</font></span><br/>
                <h7>状态 : 普通</h7>
                <h2></h2>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
               <h3 style='visibility:hidden'>Language : 汉语</h3>
                <ul class="nav side-menu">
                  <li id = "memberManagementModule" ><a><i class="fa fa-home"></i> ${language.memberManagement} <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="#memeberManagement">${language.memberInformation}</a></li>
                      <li><a href="#memeberCreate">会员登记</a></li>
                      <li><a href="#memeberMarketing">会员营销</a></li>
                    </ul>
                  </li>
                  <li id = "caiwuManagementModule" ><a><i class="fa fa-edit"></i> 财务管理 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="#expenseApplication">费用申请</a></li>
                      <li><a href="#expensePayment">费用报销</a></li>
                    </ul>
                  </li>
                  <li id = "productManagementModule" ><a><i class="fa fa-desktop"></i> 采购管理 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="#addProduct">采购产品</a></li>
                      <li><a href="#productInformations">产品信息</a></li>
       
                    </ul>
                  </li>
                  <li><a><i class="fa fa-table"></i> 订单管理 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="tables.html">订单添加</a></li>
                      <li><a href="tables_dynamic.html">订单信息</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-bar-chart-o"></i> 售后管理 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="chartjs.html">Chart JS</a></li>
                      <li><a href="chartjs2.html">Chart JS2</a></li>
                      <li><a href="morisjs.html">Moris JS</a></li>
                      <li><a href="echarts.html">ECharts</a></li>
                      <li><a href="other_charts.html">Other Charts</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-clone"></i>数据统计 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="fixed_sidebar.html">Fixed Sidebar</a></li>
                      <li><a href="fixed_footer.html">Fixed Footer</a></li>
                    </ul>
                  </li>
                </ul>
              </div>
              <div class="menu_section">
                <h3>设置</h3>
                <ul class="nav side-menu">
                  <li><a><i class="fa fa-bug"></i> 基本设置 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="e_commerce.html">E-commerce</a></li>
                      <li><a href="projects.html">Projects</a></li>
                      <li><a href="project_detail.html">Project Detail</a></li>
                      <li><a href="contacts.html">Contacts</a></li>
                      <li><a href="profile.html">Profile</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-windows"></i> Extras <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="page_403.html">403 Error</a></li>
                      <li><a href="page_404.html">404 Error</a></li>
                      <li><a href="page_500.html">500 Error</a></li>
                      <li><a href="plain_page.html">Plain Page</a></li>
                      <li><a href="login.html">Login Page</a></li>
                      <li><a href="pricing_tables.html">Pricing Tables</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-sitemap"></i> Multilevel Menu <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                        <li><a href="#level1_1">Level One</a>
                        <li><a>Level One<span class="fa fa-chevron-down"></span></a>
                          <ul class="nav child_menu">
                            <li class="sub_menu"><a href="level2.html">Level Two</a>
                            </li>
                            <li><a href="#level2_1">Level Two</a>
                            </li>
                            <li><a href="#level2_2">Level Two</a>
                            </li>
                          </ul>
                        </li>
                        <li><a href="#level1_2">Level One</a>
                        </li>
                    </ul>
                  </li>                  
                  <li><a href="javascript:void(0)"><i class="fa fa-laptop"></i> Landing Page <span class="label label-success pull-right">Coming Soon</span></a></li>
                </ul>
              </div>

            </div>
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="设置">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="全屏">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="锁定">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="退出" class="logOutBtn">
                <span class="glyphicon glyphicon-off" aria-hidden="true" ></span>
              </a>
            </div>
            <!-- /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <img src="<c:url value='/resources/modules/index/images/picture.jpg'/>" alt="">${userName}
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                    <li><a href="javascript:;"> 个人信息</a></li>
                    <li>
                      <a href="javascript:;">
                        <span class="badge bg-red pull-right">50%</span>
                        <span>设置</span>
                      </a>
                    </li>
                    <li><a href="javascript:;">帮助</a></li>
                    <li><a href="#" class = "logOutBtn"><i class="fa fa-sign-out pull-right"></i> 退出</a></li>
                  </ul>
                </li>

                <li role="presentation" class="dropdown">
                  <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                    <i class="fa fa-envelope-o"></i>
                    <span class="badge bg-green">6</span>
                  </a>
                  <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                    <li>
                      <a>
                        <span class="image"><img src="<c:url value='/resources/modules/index/images/picture.jpg'/>" alt="Profile Image" /></span>
                        <span>
                          <span>John Smith</span>
                          <span class="time">3 mins ago</span>
                        </span>
                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                      </a>
                    </li>
                    <li>
                      <a>
                        <span class="image"><img src="<c:url value='/resources/modules/index/images/picture.jpg'/>" alt="Profile Image" /></span>
                        <span>
                          <span>John Smith</span>
                          <span class="time">3 mins ago</span>
                        </span>
                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                      </a>
                    </li>
                    <li>
                      <a>
                        <span class="image"><img src="<c:url value='/resources/modules/index/images/picture.jpg'/>" alt="Profile Image" /></span>
                        <span>
                          <span>John Smith</span>
                          <span class="time">3 mins ago</span>
                        </span>
                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                      </a>
                    </li>
                    <li>
                      <a>
                        <span class="image"><img src="<c:url value='/resources/modules/index/images/picture.jpg'/>" alt="Profile Image" /></span>
                        <span>
                          <span>John Smith</span>
                          <span class="time">3 mins ago</span>
                        </span>
                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                      </a>
                    </li>
                    <li>
                      <div class="text-center">
                        <a>
                          <strong>See All Alerts</strong>
                          <i class="fa fa-angle-right"></i>
                        </a>
                      </div>
                    </li>
                  </ul>
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->

        <div class="right_col" role="main" id = "managementCore">
        <!-- 登录默认首页 (登录之后默认不应该显示内容，应该显示欢迎等一系列页面)-->
        <section>
               <div id="managementCorePage" class='row pageActive' style="height:918px;background:white;">
                                    ${userName} 欢迎您 !
               
               </div>
        
        </section>
      <!--   include file="member.jsp"-->
      <section id="superMember"> 
      </section> 
  
           
        <section>
       
           
        </section>
           
           
           
        
        </div>

        <!-- footer content -->
        <footer>
          <div class="pull-right">
            Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>


    <!-- <script type="text/javascript" src="<c:url value='/resources/plugin/sea/sea-debug.js'/>"></script>
    jQuery "<c:url value='/resources/plugin/jquery/jquery-2.0.2.js'/>"
    <script src="<c:url value='/resources/plugin/jquery/jquery.min.js'/>"></script>
    <!-- Bootstrap 
    <script src="<c:url value='/resources/plugin/bootstrap/js/bootstrap.min.js'/>"></script>
   <!-- Custom Theme Scripts
    <script src="<c:url value='/resources/common/javascript/custom.min.js'/>"></script>
    <script src="<c:url value='/resources/plugin/jquery/jquery.min.js'/>"></script>
    
    <script type="text/javascript"  src="<c:url value='/resources/plugin/kindeditor/kindeditor.js'/>"></script>
    <script type="text/javascript"  src="<c:url value='/resources/plugin/kindeditor/zh_CN.js'/>"></script>-->
    
    <script type="text/javascript" data-main="<c:url value='/resources/modules/main.js'/>" src="<c:url value='/resources/plugin/require/require.js'/>"></script>
    
    <script type="text/javascript">
     /* KindEditor.ready(function(K) {
         var options = {
                filterMode : true
         };
         window.editor = K.create('#sendMessageManyEditor',options);
         //var options = {
          //       filterMode : true
         //};
         //var editor = K.create('textarea[name="content"]', options);
      });*/
      var url = {
              L1MemberUrl : '<c:url value="/member/memberL1"/>',
              L2MemberUrl : '<c:url value="/member/memberL2"/>',
              L1procurementProductUrl : '<c:url value="/product/supplierL1"/>',
              L2procurementProductUrl : '<c:url value="/product/procurementProductL2"/>',
              showAllSupplierProduct : '<c:url value="/product/showAllSupplierProduct"/>',
              showAllInsaleProduct: '<c:url value="/product/showAllInsaleProduct"/>'
      }
    
    </script>

 
  </body>

