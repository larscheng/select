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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="">
    <%@include file="/WEB-INF/pages/common/macTopCommon.jsp" %>
</head>

<body>



<!-- Main bar -->
<div class="mainbar">

    <!-- Page heading -->
    <div class="page-head">
        <!-- Page heading -->
        <h2 class="pull-left">
            <!-- page meta -->
            <span class="page-meta"><i class="icon-cog"></i> 信息编辑</span>
        </h2>


        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
            <a href="${ctx}/index" target="_top"><i class="icon-cog"></i> 信息编辑</a>
            <!-- Divider -->
            <span class="divider">/</span>
            <a href="#" class="bread-current">首页</a>
        </div>

        <div class="clearfix"></div>

    </div>
    <!-- Page heading ends -->



    <!-- Matter -->

    <div class="matter">
        <div class="container">

            <div class="row">

                <div class="col-md-12">


                    <div class="widget wgreen">

                        <div class="widget-head"  style="position: relative">
                            <div class="pull-left">信息</div>
                            <div class="widget-icons pull-right">
                                <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                <a href="#" class="wclose"><i class="icon-remove"></i></a>
                            </div>
                            <div class="row navbar-form " style="position: absolute; top: -5px; right: 50px">

                                <button type="button"  onclick="window.location.href='${ctx}/initChangeAdmPs';" style="margin-right: 10px" class="btn btn-info pull-left "><i class="icon-upload"></i>密码修改</button>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                        <div class="widget-content">
                            <div class="padd">
                                <hr />
                                <!-- Form starts.  -->
                                <form class="form-horizontal" role="form" id="updateForm">


                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">账号</label>
                                        <div class="col-lg-8">
                                            <input type="hidden" class="form-control" name="id" value="${requestScope.user.id}" placeholder="学生账号">
                                            <input type="text" disabled class="form-control" name="userCode" value="${requestScope.user.userCode}" placeholder="学生账号">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">姓名</label>
                                        <div class="col-lg-8">
                                            <input type="text" class="form-control" name="userName" value="${requestScope.user.userName}" placeholder="学生姓名">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">性别</label>
                                        <div class="col-lg-8">
                                            <c:if test="${requestScope.user.userSex eq 1}">
                                                <label class="radio-inline">
                                                    <input type="radio" name="userSex"  value="1" checked>男
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="userSex"   value="2">女
                                                </label>
                                            </c:if>

                                            <c:if test="${requestScope.user.userSex eq 2}">
                                                <label class="radio-inline">
                                                    <input type="radio" name="userSex"  value="1" >男
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="userSex" checked  value="2">女
                                                </label>
                                            </c:if>
                                            </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">邮箱</label>
                                        <div class="col-lg-8">
                                            <input type="email" class="form-control" name="userMail" value="${requestScope.user.userMail}" placeholder="邮箱">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">电话</label>
                                        <div class="col-lg-8">
                                            <input type="tel" class="form-control" value="${requestScope.user.userPhone}"  name="userPhone" placeholder="电话">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">qq</label>
                                        <div class="col-lg-8">
                                            <input type="text" class="form-control" value="${requestScope.user.userQq}"  name="userQq" placeholder="qq">
                                        </div>
                                    </div>


                                    <hr />
                                    <div class="form-group">
                                        <div class="col-lg-offset-1 col-lg-9">
                                            <button type="button" id="updateSubmit" class="btn btn-success">提交</button>
                                            <button type="reset" class="btn btn-info">重填</button>
                                            <button type="button" class="btn btn-info" onclick="window.history.go(-1);">返回</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="widget-foot">
                            <!-- Footer goes here -->
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
        $('#updateForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {/*验证*/
                userCode: {/*键名username和input name值对应*/
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '账号不能为空'
                        },
                        stringLength: {/*长度提示*/
                            min: 8,
                            max: 8,
                            message: '账号长度必须为8位'
                        }/*最后一个没有逗号*/
                    }
                },
                userName: {/*键名username和input name值对应*/
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '姓名不能为空'
                        },
                        stringLength: {/*长度提示*/
                            min: 2,
                            max: 8,
                            message: '姓名长度必须为2到8位'
                        }/*最后一个没有逗号*/
                    }
                },
                userSex: {
                    validators: {
                        notEmpty: {
                            message: '性别未选择'
                        }
                    }
                },
                userPhone: {
                    message:'格式错误',
                    validators: {
                        notEmpty: {
                            message: '电话号码不能为空'
                        },
                        stringLength: {
                            min: 7,
                            max: 15,
                            message: '电话号码长度必须在7到15之间'
                        }
                    }
                },
                userQq: {
                    message:'格式错误',
                    validators: {
                        notEmpty: {
                            message: 'QQ号码不能为空'
                        }
                    }
                },
                userMail: {
                    validators: {
                        notEmpty: {
                            message: '邮箱不能为空'
                        },
                        emailAddress: {
                            message: '邮箱格式错误'
                        }
                    }
                }
            }
        });
    });




    $(function(){

        $("#updateSubmit").click(function(){
            //获取表单对象
            var bootstrapValidator = $("#updateForm").data('bootstrapValidator');
            //手动触发验证
            bootstrapValidator.validate();

            if(bootstrapValidator.isValid()){
                //表单提交的方法、比如ajax提交
            $.ajax({
                type: "post",
                url: "${ctx}/selectUserBase/admUpdate",
                data: $("#updateForm").serialize(),
                dataType:"json",
                success:function(msg){
                    console.log(msg);
                    if("OK" !== msg){
                        alert(" 😅 "+msg);
                    }else {
                        alert(" 😎 修改成功！","",function () {
                            location.href="${ctx}/selectUserBase/admList";
                        },{type:"success",confirmButtonText:"好的"});
                    }

                },
                error: function(e) {
                    alert(" 😥 系统异常，请与我们的工程师小哥哥联系！");
                }
            });
            }
        });
    });


    /* Curve chart ends */
</script>

</body>
</html>