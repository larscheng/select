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
            <span class="page-meta">修改密码</span>
        </h2>


        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
            <a href="#"><i class="icon-home"></i> 密码修改</a>
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

                        <div class="widget-head">
                            <div class="pull-left">修改密码</div>
                            <div class="widget-icons pull-right">
                                <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                <a href="#" class="wclose"><i class="icon-remove"></i></a>
                            </div>

                            <div class="clearfix"></div>
                        </div>

                        <div class="widget-content">
                            <div class="padd">
                                <hr />
                                <!-- Form starts.  -->
                                <form class="form-horizontal" role="form" id="updateForm">

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">用户名</label>
                                        <div class="col-lg-8">
                                            <input type="text" disabled class="form-control" value="${sessionScope.sessionUser.userName}" placeholder="原始密码">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">邮箱</label>
                                        <div class="col-lg-8">
                                            <input type="email"  value="${sessionScope.sessionUser.userMail}" class="form-control disabled" name="userMail" placeholder="接收新密码的邮箱">
                                            <input type="hidden" id="userId" class="form-control" name="userId" value="${sessionScope.sessionUser.id}" placeholder="">

                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">原始密码</label>
                                        <div class="col-lg-8">
                                            <input type="password" class="form-control" id="passWord"  name="passWord" placeholder="原始密码">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">新密码</label>
                                        <div class="col-lg-8">
                                            <input type="password" class="form-control"   name="newPassWord" placeholder="新密码">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">确认密码</label>
                                        <div class="col-lg-8">
                                            <input type="password" class="form-control"   name="newPassWord2" placeholder="确认密码">
                                        </div>
                                    </div>

                                    <hr />
                                    <div class="form-group">
                                        <div class="col-lg-offset-3 col-lg-9">
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

<script>
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
                userMail: {
                    validators: {
                        notEmpty: {
                            message: '邮箱不能为空'
                        },
                        emailAddress: {
                            message: '邮箱格式错误'
                        }
                    }
                },
                passWord: {
                    validators: {
                        notEmpty: {
                            message: '用户原始密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 20,
                            message: '密码长度必须在6到20位之间'
                        },
                        threshold : 6 , //有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
                        remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                            url: '${ctx}/checkPs',//验证地址
                            data: function(validator) {
                                return {
                                    passWord: $('#passWord').val(),
                                    userId: $('#userId').val()
                                };
                            },
                            dataType: "json",
                            message: '原始密码错误！',//提示消息
                            delay :  5000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                            type: 'POST'//请求方式

                        },
                        regexp: {
                            regexp: /^[^ ]+$/,
                            message: '用户原始密码不能有空格'
                        }
                    }
                },
                newPassWord: {
                    validators: {
                        notEmpty: {
                            message: '用户新密码不能为空'
                        },
                        different: {
                            field: 'passWord',
                            message: '新密码与原始密码不能相同'
                        },
//                        identical: {
//                            field: 'newPassWord2',
//                            message: '用户新密码与确认密码不一致！'
//                        },
                        stringLength: {
                            min: 6,
                            max: 20,
                            message: '密码长度必须在6到20位之间'
                        },
                        regexp: {
                            regexp: /^[^ ]+$/,
                            message: '用户新密码不能有空格'
                        }

                    }
                },
                newPassWord2: {
                    validators: {
                        identical: {
                            field: 'newPassWord',
                            message: '用户新密码与确认密码不一致！'
                        },
                        notEmpty: {
                            message: '用户确认密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 20,
                            message: '密码长度必须在6到20位之间'
                        },

                        regexp: {
                            regexp: /^[^ ]+$/,
                            message: '用户确认密码不能有空格'
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

                $.ajax({
                    type: "post",
                    url: "${ctx}/changePs",
                    data: $("#updateForm").serialize(),
                    async: true,
                    // 下面三个参数要指定，如果不指定，会报一个JQuery的错误
//                    cache: false,
//                    contentType: false,
//                    processData: false,
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else {
                            alert(" 😎 修改成功,下次登录时生效","",function () {
                                <%--location.href="${ctx}/";--%>
                                window.top.frames.location.href="${ctx}/";
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
</script>

</body>
</html>