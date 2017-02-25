<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@include file="webroot.inc"%>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Management System [柠檬网络科技] </title>

    <!-- Bootstrap -->
    <link href="../resources/plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../resources/plugin/font-awesome/font-awesome.min.css" rel="stylesheet">
     <link href="../resources/plugin/sweetalert/sweetalert.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../resources/common/css/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="../resources/common/css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../resources/modules/login/css/custom.css" rel="stylesheet">
    
     <!-- Custom Theme Style -->
    <link href="../resources/modules/login/css/login.css" rel="stylesheet">
    
    <!-- Custom Theme Style -->
    <link href="../resources/common/css/common.css" rel="stylesheet">

  </head>

  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>
     
      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form id='login_module'>
              <h1>登录</h1>
              <div>
                <input type="text" class="form-control" placeholder="Username" required="" name = "name" />
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" required="" name = "password" />
              </div>
              <div>
                <a class="btn btn-default submit" href="#" id="_login_">登录</a>
                <a class="reset_pass" href="#">忘记密码?</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">
                   <a href="#signup" class="to_register"> 未注册 ? 创建用户 </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <a href='http://www.lemonnt.com'><h1><i class="fa fa-paw"></i>&nbsp;&nbsp;柠檬网络科技</h1></a>
                  <p>Copyright @ 安徽六安柠檬网络科技有限公司  版权所有 皖ICP备15006126号. All Rights Reserved.
			      </br>旗下品牌: 雅戈维尼 (Youner Willing)</p>
                </div>
              </div>
            </form>
          </section>
        </div>

        <div id="register" class="animate form registration_form">
          <section class="login_content">
            <form>
              <h1>注册</h1>
              <div>
                <input type="text" class="form-control" placeholder="用户名" required="true" name="name"/>
              </div>
              <div>
                <input type="email" class="form-control" placeholder="邮箱" required="true" name = "email"/>
              </div>
              <div>
                <input type="password" class="form-control" placeholder="密码" required="true" name = "password"/>
              </div>
              <div>
                <a class="btn btn-default submit" href="#signup" id='createUser'>提交</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">已经注册 ?
                  <a href="#signin" class="to_register"> 登录 </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-paw"></i>柠檬网络科技</h1>
                  <p>Copyright @ 安徽六安柠檬网络科技有限公司  版权所有 皖ICP备15006126号. All Rights Reserved.
			      </br>旗下品牌: 雅戈维尼 (Youner Willing)</p>
                </div>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>

  </body>

    <script type="text/javascript" data-main="login.js" src="../resources/plugin/require/require.js"></script>
    <script>
    require.config({
      baseUrl:SERVERNAME+"/resources/modules/login/javascript",
      paths:{
        "jquery": "../../../plugin/jquery/jquery-2.0.2",
        "jquery-cookie": "../../../plugin/jquery/jquery.cookie",
	    "common": "../../../common/javascript/common",
	    "service": "../../../common/javascript/core",
	    "sweetAlert": "../../../plugin/sweetalert/sweetalert.min",
	    "global": "../../../common/javascript/global",
	    "bootstrap": "../../../plugin/bootstrap/js/bootstrap.min"
      },
      
      
    });

	</script>