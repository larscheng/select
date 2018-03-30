<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <!-- Title and other stuffs -->
    <title>Select System</title>
    <meta name="author" content="">
    <%@include file="/WEB-INF/pages/common/macTopCommon.jsp" %>


</head>

<body>



<!-- Main bar -->
<div class="mainbar">

    <!-- Page heading -->
    <div class="page-head">
        <h2 class="pull-left"><i class="icon-file-alt"></i> Gallery</h2>

        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
            <a href="index.html"><i class="icon-home"></i> Home</a>
            <!-- Divider -->
            <span class="divider">/</span>
            <a href="#" class="bread-current">Dashboard</a>
        </div>

        <div class="clearfix"></div>

    </div>
    <!-- Page heading ends -->

    <!-- Matter -->

    <div class="matter">
        <div class="container">
            <div class="row">
                <div class="col-md-12">

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
                                        <label class="control-label col-lg-3" for="inputEmail">账号</label>
                                        <div class="col-lg-9">
                                            <input type="text" class="form-control" name="userName" id="inputEmail" placeholder="Email">
                                        </div>
                                    </div>
                                    <!-- Password -->
                                    <div class="form-group">
                                        <label class="control-label col-lg-3" for="inputPassword">密码</label>
                                        <div class="col-lg-9">
                                            <input type="password" class="form-control" name="userPassword" id="inputPassword" placeholder="Password">
                                        </div>
                                    </div>
                                    <!-- Remember me checkbox and sign in button -->
                                    <div class="form-group">
                                        <div class="col-lg-9 col-lg-offset-3">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"> 记住我
                                                </label>
                                            </div>
                                        </div>
                                    </div>
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
<!-- Matter ends -->

</div>

<!-- Mainbar ends -->
<div class="clearfix"></div>

 <%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>

<script type="text/javascript">
    $(document).ready(function() {
        /**
         * 下面是进行插件初始化
         * 你只需传入相应的键值对
         * */
        $('#defaultForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {/*验证*/
                userName: {/*键名username和input name值对应*/
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '用户名不能为空'
                        },
                        stringLength: {/*长度提示*/
                            min: 6,
                            max: 30,
                            message: '用户名长度必须在6到30之间'
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
                },
//                email: {
//                    validators: {
//                        notEmpty: {
//                            message: 'The email address is required and can\'t be empty'
//                        },
//                        emailAddress: {
//                            message: 'The input is not a valid email address'
//                        }
//                    }
//                }
            }
        });
    });
</script>

</body>
</html>