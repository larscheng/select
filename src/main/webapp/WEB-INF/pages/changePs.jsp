<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <!-- Title and other stuffs -->
  <title>修改密码</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="author" content="">

    <%@include file="/WEB-INF/pages/common/macTopCommon.jsp" %>
</head>

<body>
<script type="text/javascript" color="black" opacity="0.7" count="250" src="${ctx}/resources/bg/canvas-nest.js"></script>

<!-- Form area -->
<div class="error-page">
  <div class="" style="margin: auto;width: 1000px">

    <div class="row">
      <div class="col-md-6">
        <!-- Widget starts -->
            <div class="widget">
              <!-- Widget head -->
              <div class="widget-head">
                <i class="icon-question-sign"></i> 密码修改
              </div>

              <div class="widget-content">
                <div class="padd error">
                  
                  <h2 style="color: red;text-align: center" >为了您的账户安全请修改密码先😐</h2>
                  <p style="text-align: center">密码是您最重要的信息，请设置新密码后进入系统😐😐😐😐😐</p>



                    <div class="widget-content">
                        <div class="">
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
                                        <input type="email" class="form-control" value="${sessionScope.sessionUser.userMail}" name="userMail" placeholder="接收新密码的邮箱">
                                        <input type="hidden" id="userId" class="form-control" name="userId" value="${sessionScope.sessionUser.id}" placeholder="">

                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-4 control-label">手机号码</label>
                                    <div class="col-lg-8">
                                        <input type="tel" class="form-control" name="userPhone" placeholder="您的手机号(选填)">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-4 control-label">qq号</label>
                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="userQq" placeholder="您的QQ号(选填)">
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
                                <div class="form-group center">
                                    <div class="col-lg-offset-1 col-lg-9">
                                        <button type="button" id="updateSubmit" class="btn btn-success">提交</button>
                                        <button type="reset" class="btn btn-info">重填</button>
                                        <button type="button" class="btn btn-info" onclick="window.location.href='${ctx}/logout'">退出</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="widget-foot">
                        <!-- Footer goes here -->
                    </div>

                </div>
                  <div class="horizontal-links center">
                  <a href="${ctx}/logout">首页</a> | <a href="#">About Us</a> | <a href="#">Contact us</a> | <a href="#">FAQ</a>
                 </div>


                <div class="widget-foot">
                  <!-- Footer goes here -->
                </div>
              </div>
            </div>  
      </div>
    </div>
  </div> 
</div>
	
		

<!-- JS -->
<%--<script src="${ctx}/resources/js/jquery.js"></script>--%>
<%--<script src="${ctx}/resources/js/bootstrap.js"></script>--%>
<%--<script type="text/javascript" src="${ctx}/resources/js/bootstrapValidator.js"></script>--%>

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
                            max: 19,
                            message: '用户原始密码长度大于5小于20'
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
                            max: 19,
                            message: '用户新密码长度大于5小于20'
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
                            max: 19,
                            message: '用户确认密码长度大于5小于20'
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
                            alert(" 😎 修改成功","",function () {
                                location.href="${ctx}/login";
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