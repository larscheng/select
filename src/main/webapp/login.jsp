<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
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
	<link rel="stylesheet" href="${ctx}/resources/style/font-awesome.css">
	<!-- prettyPhoto -->
	<link rel="stylesheet" href="${ctx}/resources/style/prettyPhoto.css">

	<link href="${ctx}/resources/jquery/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

	<!-- Main stylesheet -->
	<link href="${ctx}/resources/style/style.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctx}/resources/style/bootstrapValidator.css"/>
	<link rel="stylesheet" href="${ctx}/resources/style/font-awesome.css">
	<link rel="alternate icon" type="image/x-icon" href="${ctx}/resources/favicon.ico">
</head>


<body style="background-color: #0a628f">

<!-- Form area -->
<div class="admin-form">
	<div class="container">
		<div class="row">
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
							<form id="defaultForm" class="form-horizontal" action="${ctx}/selectUserBase/login" method="post">
								<!-- Email -->
								<div class="form-group">
									<label class="control-label col-lg-3">账号</label>
									<div class="col-lg-9">
										<input type="text" class="form-control" name="userName"  placeholder="Email">
									</div>
								</div>
								<!-- Password -->
								<div class="form-group">
									<label class="control-label col-lg-3">密码</label>
									<div class="col-lg-9">
										<input type="password" class="form-control" name="userPassword" placeholder="Password">
									</div>
								</div>
								<!-- Remember me checkbox and sign in button -->
								<%--<div class="form-group">--%>
									<%--<div class="col-lg-8 col-lg-offset-3">--%>
										<%--<div class="checkbox">--%>
											<%--<label>--%>
												<%--<input type="checkbox"> 记住我--%>
											<%--</label>--%>
										<%--</div>--%>
									<%--</div>--%>
								<%--</div>--%>
								<div class="col-lg-9 col-lg-offset-2">
									<button type="submit" class="btn btn-danger">登录</button>
									<button type="reset" class="btn btn-default">重填</button>
								</div>
								<br />
							</form>

						</div>
					</div>

					<div class="widget-foot">
						Not Registred? <a href="#">Register here</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<!-- JS -->
<script src="resources/js/jquery.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrapValidator.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        /**
         * 下面是进行插件初始化
         * 你只需传入相应的键值对
         * */
        $('#defaultForm').bootstrapValidator({
            message: 'This value is not valid',
            fields: {/*验证*/
                userName: {/*键名username和input name值对应*/
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '用户名不能为空'
                        },
                        stringLength: {/*长度提示*/
                            min: 2,
                            max: 30,
                            message: '用户名长度必须在2到30之间'
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