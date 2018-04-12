<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <!-- Title and other stuffs -->
    <title>Select System</title>
    <meta name="author" content="">
    <%@include file="/WEB-INF/pages/common/macTopCommon.jsp" %>

    <link rel="stylesheet" href="${ctx}/resources/style/bootstrap4.0.min.css">
    <style>
        /* Make the image fully responsive */
        .carousel-inner img {
            width: 100%;
            height: 100%;
        }
    </style>

</head>

<body>


<!-- Main bar -->
<div class="mainbar">

    <!-- Page heading -->
    <div class="page-head">
        <h2 class="pull-left"><i class="icon-file-alt"></i> 首页</h2>

        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
            <a href="index.html"><i class="icon-home"></i> 首页</a>
            <!-- Divider -->
            <span class="divider">/</span>
            <a href="#" class="bread-current">选题管理系统</a>
        </div>

        <div class="clearfix"></div>

    </div>
    <!-- Page heading ends -->

    <!-- Matter -->

    <div class="matter">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">

                    <div class="widget wgreen">

                        <div class="widget-head">
                            <div class="pull-left">bug留言板</div>
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
                                <form class="form-horizontal" role="form" id="defaultForm">



                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">标题</label>
                                        <div class="col-lg-8">
                                            <input type="hidden" class="form-control" name="userId" value="${sessionScope.sessionUser.id}">
                                            <input type="text" class="form-control" name="bugTitle" placeholder="bug标题">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">内容</label>
                                        <div class="col-lg-8">
                                            <textarea class="form-control" rows="4" name="bugContent" placeholder="bug内容"></textarea>
                                        </div>
                                    </div>


                                    <hr />
                                    <div class="form-group">
                                        <div class="col-lg-offset-4 col-lg-9">
                                            <button type="button" id="addSubmit" class="btn btn-success">提交</button>
                                            <button type="reset" class="btn btn-info">重填</button>
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
            <div class="row small">
                <div class="col-md-10 col-md-offset-1">


                    <div class="widget wgreen">

                        <div class="widget-head">
                            <div class="pull-left">历史记录</div>
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
                                <div class="container">
                                    <c:choose>
                                        <c:when test="${empty requestScope.bugList }">
                                            <div class="alert alert-danger alert-dismissable fade show">
                                                <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>
                                                <strong>郑其龙:</strong>
                                                欢迎大家测试项目，并提出bug
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="bug" items="${requestScope.bugList}" varStatus="index">
                                                <div class="alert alert-danger alert-dismissable fade show">
                                                    <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>
                                                    <strong>  ${bug.userName}
                                                        (<fmt:formatDate value="${bug.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/> )发现bug：
                                                    </strong>
                                                    <p>【${bug.bugTitle}】：${bug.bugContent}</p>

                                                </div>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>

                                </div>
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
        $('#defaultForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {/*验证*/
                bugTitle: {/*键名username和input name值对应*/
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '标题不能为空'
                        }
                    }
                },
                bugContent: {
                    message:'内容无效',
                    validators: {
                        notEmpty: {
                            message: '内容不能为空'
                        }
                    }
                }
            }
        });
    });




    $(function(){

        $("#addSubmit").click(function(){
            //获取表单对象
            var bootstrapValidator = $("#defaultForm").data('bootstrapValidator');
            //手动触发验证
            bootstrapValidator.validate();

            if(bootstrapValidator.isValid()){
                $.ajax({
                    type: "post",
                    url: "${ctx}/selectBugLog/bugAdd",
                    data: $("#defaultForm").serialize(),
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else {
                            alert(" 😎 感谢感谢，给你个赞","",function () {
                                location.href="${ctx}/selectBugLog/bugInitList";
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