<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<!-- Title and other stuffs -->
	<title>毕业论文选题系统</title>

	<!-- Stylesheets -->
	<link href="${ctx}/resources/style/bootstrap.css" rel="stylesheet">
	<!-- Font awesome icon -->
	<%--<link rel="stylesheet" href="${ctx}/resources/style/font-awesome.css">--%>

	<link href="${ctx}/resources/jquery/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

	<!-- Main stylesheet -->
	<link href="${ctx}/resources/style/style.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctx}/resources/style/bootstrapValidator.css"/>
	<link rel="alternate icon" type="image/x-icon" href="${ctx}/resources/favicon.ico">

	<!-- CSS -->
	<link rel="stylesheet" href="${ctx}/resources/bg/login/supersized.css"/>
	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>

	<![endif]-->
	<style>
		canvas{position: fixed; top: 0px; left: 0px; }
	</style>


</head>


<body id="body">
<script type="text/javascript" color="255,255,255" opacity="0.7" count="150" src="${ctx}/resources/bg/canvas-nest.js"></script>
<!-- Form area -->
<div class="admin-form">
	<div class="container">
		<div class="row" style="margin-top: 130px">
			<div class="col-md-12 center">
				<%--<img src="/resources/favicon.ico" width="60px" height="60px" alt="">--%>

				<b style="font-size: 35px ;line-height: 60px">选题系统</b>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<!-- Widget starts -->
				<div class="widget worange">
					<!-- Widget head -->
					<div class="widget-head">
						<i class="icon-lock"></i> 登录
					</div>

					<div class="widget-content">
						<div class="padd">
							<!-- Login form -->
							<form id="defaultForm" class="form-horizontal" action="${ctx}/login"  method="post">
								<!-- Email -->
								<div class="form-group">
									<label class="control-label col-lg-3">账号</label>
									<div class="col-lg-9">
										<input type="text" id="userCode" class="form-control" name="userCode"  placeholder="请输入账号学号">
									</div>
								</div>
								<!-- Password -->
								<div class="form-group">
									<label class="control-label col-lg-3">密码</label>
									<div class="col-lg-9">
										<input type="password" class="form-control" name="userPassword" placeholder="请输入密码">
									</div>
								</div>
								<!-- Remember me checkbox and sign in button -->
								<div class="form-group">
									<div class="col-lg-8 col-lg-offset-3">

											<label id="msg" style="display: block">
												<b style="color: red">${requestScope.msg}</b>
											</label>

									</div>
								</div>
								<div class="col-lg-9 col-lg-offset-4">
									<button type="submit" class="btn btn-danger">登录</button>
									<button type="reset" class="btn btn-default">重填</button>
								</div>
								<br />
							</form>

						</div>
					</div>

					<div class="widget-foot center">
						<p class="copy">
							<a href="#">商洛学院</a>|
							Copyright &copy; 2018|
							<a href="${ctx}/initForgetPs">忘记密码</a>
						</p>

					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 center">
				<b style="color: red">(管理员)测试账号：66666666，密码：123456。</br> 其他角色账号可自行添加测试</b>
			</div>
		</div>
	</div>
</div>



<!-- JS -->
<%--<script src="${ctx}/resources/js/jquery.js"></script>--%>
<script  src="${ctx}/resources/jquery/jquery-2.2.3.min.js"></script>


<script  src="${ctx}/resources/bg/supersized.3.2.7.min.js"></script>
<script  src="${ctx}/resources/bg/supersized-init.js"></script>
<script src="${ctx}/resources/js/bootstrap.js"></script>
<script src="${ctx}/resources/bg/html5shiv.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrapValidator.js"></script>

<script type="text/javascript">
    $("input").focus(function(){
        $("#msg").css("display","none");
    });

    $(document).ready(function() {

        /**
         * 下面是进行插件初始化
         * 你只需传入相应的键值对
         * */
        $('#defaultForm').bootstrapValidator({
            message: 'This value is not valid',
            fields: {/*验证*/
                userCode: {/*键名username和input name值对应*/
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '账号不能为空'
                        },
                        stringLength: {/*长度提示*/
                            min: 8,
                            message: '账号长度必须位8位'
                        }/*最后一个没有逗号*/
                    }
                },
                userPassword: {
                    message:'密码无效',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: '用户名长度必须在6到30之间'
                        }
                    }
                }
            }
        });
    });
</script>

</body>
</html>