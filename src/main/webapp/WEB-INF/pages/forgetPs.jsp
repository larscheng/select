<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <!-- Title and other stuffs -->
  <title>忘记密码</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="author" content="">

    <%@include file="/WEB-INF/pages/common/macTopCommon.jsp" %>
</head>

<body>
<script type="text/javascript" color="black" opacity="0.7" count="250" src="${ctx}/resources/bg/canvas-nest.js"></script>

<!-- Form area -->
<div class="error-page">
  <div class="" style="margin: auto;width: 900px">

    <div class="row">
      <div class="col-md-8">
        <!-- Widget starts -->
            <div class="widget">
              <!-- Widget head -->
              <div class="widget-head">
                <i class="icon-question-sign"></i> 忘记密码
              </div>

              <div class="widget-content">
                <div class="padd error">
                  
                  <h2 style="color: red;text-align: center" >为了您的账户安全请将新密码妥善保管😐</h2>
                  <p style="text-align: center">我们将需要验证您的账号，以及您的预留邮箱，用来接收验证码😐😐😐😐😐</p>



                    <div class="widget-content">
                        <div class="">
                            <hr />
                            <!-- Form starts.  -->
                            <form class="form-horizontal" role="form" id="updateForm">

                                <div class="form-group">
                                    <label class="col-lg-4 control-label">账号</label>
                                    <div class="col-lg-8">
                                        <input type="text"  class="form-control" id="userCode" name="userCode" placeholder="账号">
                                        <input type="hidden" id="userId" class="form-control" name="userId" value="${sessionScope.sessionUser.id}" placeholder="">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-4 control-label">邮箱</label>
                                    <div class="col-lg-8">
                                        <input type="email" class="form-control" id="userMail" name="userMail" placeholder="接收新密码的邮箱">
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

                                <div class="form-group">
                                    <label class="col-lg-4 control-label">验证码</label>
                                    <div class="col-lg-6">
                                        <input type="text" class="form-control" id="sixCode"  name="sixCode" placeholder="验证码">

                                    </div>
                                    <input type="button" class="btn btn-warning" id="btn" value="获取验证码" />
                                </div>

                                <hr />
                                <div class="form-group center">
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
                  <div class="horizontal-links center">
                  <a href="${ctx}/login">首页</a> | <a href="#">About Us</a> | <a href="#">Contact us</a> | <a href="#">FAQ</a>
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
                userCode: {/*键名username和input name值对应*/
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '账号不能为空'
                        },
                        <%--threshold : 8 , //有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）--%>
                        <%--remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}--%>
                            <%--threshold : 8 ,--%>
                            <%--url: '${ctx}/selectUserBase/checkCode',//验证地址--%>
                            <%--data: function(validator) {--%>
                                <%--return {--%>
                                    <%--userCode: $('#userCode').val()--%>
                                <%--};--%>
                            <%--},--%>
                            <%--dataType: "json",--%>
                            <%--message: '该账号已存在！',//提示消息--%>
                            <%--delay :  5000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）--%>
                            <%--type: 'POST'//请求方式--%>
                        <%--},--%>
                        stringLength: {/*长度提示*/
                            min: 8,
                            max: 8,
                            message: '账号长度必须为8位'
                        }/*最后一个没有逗号*/
                    }
                },
                userMail: {
                    validators: {
                        notEmpty: {
                            message: '邮箱不能为空'
                        },
                        threshold : 10 , //有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
                        remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                            url: '${ctx}/checkMail',//验证地址
                            data: function(validator) {
                                return {
                                    userMail: $('#userMail').val(),
                                    userCode: $('#userCode').val()
                                };
                            },
                            dataType: "json",
                            message: '此邮箱不是您的预留邮箱！',//提示消息
                            delay :  5000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                            type: 'POST'//请求方式
                        },
                        emailAddress: {
                            message: '邮箱格式错误'
                        }
                    }
                },
                sixCode: {/*键名username和input name值对应*/

                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '验证码不能为空'
                        },
                        threshold : 6 , //有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
                        remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                            url: '${ctx}/checkSixCode',//验证地址
                            data: function(validator) {
                                return {
                                    sixCode: $('#sixCode').val(),
                                    userCode: $('#userCode').val()
                                };
                            },
                            dataType: "json",
                            message: '验证码错误！',//提示消息
                            delay :  5000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                            type: 'POST'//请求方式
                        },
                        stringLength: {/*长度提示*/
                            min: 6,
                            max: 6,
                            message: '验证码必须为6位'
                        }/*最后一个没有逗号*/
                    }
                },

                newPassWord: {
                    validators: {
                        notEmpty: {
                            message: '用户新密码不能为空'
                        },
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

//修改密码
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
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else {
                            alert(" 😎 修改成功","",function () {
                                location.href="${ctx}/";
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


    //发送验证码
    function sendSixCode() {
        $.ajax({
            type: "post",
            url: "${ctx}/sendSixCode",
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
                    alert(" 😎 验证码发送成功，请在邮箱查看","",function () {

                    },{type:"success",confirmButtonText:"好的"});
                }

            },
            error: function(e) {
                alert(" 😥 系统异常，请与我们的工程师小哥哥联系！");
            }
        });

    }

    /**
     * 校验文本是否为空
     * tips：提示信息
     * 使用方法：$("#id").validate("提示文本");
     * @itmyhome
     */
    $.fn.validate = function(tips){

        if($(this).val() == "" || $.trim($(this).val()).length == 0){
           return false;
        }else {
            return true;
        }
    };


    var sleep = 60, interval = null;
    var btn = document.getElementById ('btn');
    window.onload = function ()
    {

        btn.onclick = function ()
        {
            if (!$("#userCode").validate()){
                alert(" 😅 请填写您的账号");
                return;
            }
            if (!$("#userMail").validate()){
                alert(" 😅 请填写您的邮箱");
                return;
            }


            $.ajax({
                type: "post",
                url: "${ctx}/checkMail",
                data: $("#updateForm").serialize(),
                async: true,
                // 下面三个参数要指定，如果不指定，会报一个JQuery的错误
//                    cache: false,
//                    contentType: false,
//                    processData: false,
                dataType:"json",
                success:function(msg){
                    if(!msg.valid){
                        alert(" 😅 请填写正确的账户名和邮箱");
                        return
                    }else {
                       sendSixCode();
                        if (!interval)
                        {
                            btn.style.backgroundColor = 'rgb(243, 182, 182)';
                            btn.disabled = "disabled";
                            btn.style.cursor = "wait";
                            btn.value = "重新发送 (" + sleep-- + ")";
                            interval = setInterval (function ()
                            {
                                if (sleep == 0)
                                {
                                    if (!!interval)
                                    {
                                        clearInterval (interval);
                                        interval = null;
                                        sleep = 30;
                                        btn.style.cursor = "pointer";
                                        btn.removeAttribute ('disabled');
                                        btn.value = "获取验证码";
                                        btn.style.backgroundColor = '';
                                    }
                                    return false;
                                }
                                btn.value = "重新发送 (" + sleep-- + ")";
                            }, 1000);
                        }
                    }

                },
                error: function(e) {
                    alert(" 😥 系统异常，请与我们的工程师小哥哥联系！");

                }
            });


        }
    }
</script>
</body>
</html>