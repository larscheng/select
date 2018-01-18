<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <!-- Title and other stuffs -->
    <title>Mac风格响应式后台管理模版演示 - 源码之家</title>
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
            <span class="page-meta">系别编辑</span>
        </h2>


        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
            <a href="index.html"><i class="icon-home"></i>系别编辑</a>
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

                    <div class="widget wred">
                        <div class="widget-head">
                            <div class="pull-left">编辑</div>
                            <div class="widget-icons pull-right">
                                <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                <a href="#" class="wclose"><i class="icon-remove"></i></a>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                        <div class="widget-content">
                            <div class="padd">

                                <!-- Profile form -->

                                <div class="form profile">
                                    <!-- Edit profile form (not working)-->
                                    <form class="form-horizontal" role="form" id="updateForm">

                                        <div class="form-group">
                                            <label class="col-lg-4 control-label">系别名称</label>
                                            <div class="col-lg-8">
                                                <input type="hidden" class="form-control" name="id" value="${dep.id}">
                                                <input type="text" class="form-control" name="depName" value="${dep.depName}" disabled placeholder="系别名称">
                                            </div>
                                        </div>


                                        <div class="form-group">
                                            <label class="col-lg-4 control-label">系别介绍</label>
                                            <div class="col-lg-8">
                                                <textarea class="form-control" rows="4" name="depInfo" placeholder="系别介绍">${dep.depInfo}</textarea>
                                            </div>
                                        </div>


                                        <hr />
                                        <div class="form-group">
                                            <div class="col-lg-offset-1 col-lg-9">
                                                <button type="button" id="updateSubmit" class="btn btn-success">提交</button>
                                                <button type="button" class="btn btn-success" onclick="window.history.go(-1);">返回</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>

                            </div>
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


    $(function(){

        $("#updateSubmit").click(function(){
            $.ajax({
                type: "post",
                url: "/selectDepartment/depUpdate",
                data: $("#updateForm").serialize(),
                dataType:"json",
                success:function(msg){
                    if("OK"!=msg){
                        alert(msg);
                    }else {
                        alert("编辑成功","",function () {
                            location.href="/selectDepartment/depList";
                        },{type:"success",confirmButtonText:"好的"});
                    }
                },
                error: function(e) {
                    alert("后台异常");
                }
            });
        });
    });


    /* Curve chart ends */
</script>

</body>
</html>