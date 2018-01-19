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
            <span class="page-meta">学生添加</span>
        </h2>


        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
            <a href="index.html"><i class="icon-home"></i> 学生管理</a>
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
                            <div class="pull-left">学生</div>
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
                                <form class="form-horizontal" role="form" id="addForm">

                                    <div class="form-group ">

                                        <label class="col-lg-1 control-label">专业</label>
                                        <div class="col-lg-2">
                                            <select name="stuMajorName"  class="form-control">
                                                <option value="" selected>---请选择---</option>
                                                <c:forEach var="major" items="${requestScope.majorList}">
                                                    <option value="${major.stuMajorName}">${major.stuMajorName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>




                                        <label class="col-lg-1 control-label">班级</label>
                                        <div class="col-lg-2">
                                            <%--${requestScope.classList}--%>
                                            <select name="stuClass" class="form-control">
                                                <option value="" selected>---请选择---</option>
                                                <c:forEach var="cla" items="${requestScope.classList}">
                                                    <option value="${cla.stuClass}">${cla.stuClass}班</option>
                                                </c:forEach>
                                            </select>
                                        </div>




                                        <label class="col-lg-1 control-label">届别</label>
                                        <div class="col-lg-2" >
                                            <select name="stuYear" class="form-control">
                                                <option value="" selected>---请选择---</option>
                                                <c:forEach var="year" items="${requestScope.yearList}">
                                                    <option value="${year.stuYear}">${year.stuYear}级</option>
                                                </c:forEach>
                                            </select>
                                        </div>



                                    </div>


                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">学生账号</label>
                                        <div class="col-lg-8">
                                            <input type="text" class="form-control" name="userCode" placeholder="学生账号">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">学生姓名</label>
                                        <div class="col-lg-8">
                                            <input type="text" class="form-control" name="userName" placeholder="学生姓名">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">学生性别</label>
                                        <div class="col-lg-8">
                                                <label class="radio-inline">
                                                    <input type="radio" name="userSex"  value="1" checked>男
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="userSex"   value="2">女
                                                </label>
                                            </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">邮箱</label>
                                        <div class="col-lg-8">
                                            <input type="email" class="form-control" name="userMail" placeholder="邮箱">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">电话</label>
                                        <div class="col-lg-8">
                                            <input type="tel" class="form-control" name="userPhone" placeholder="电话">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">qq</label>
                                        <div class="col-lg-8">
                                            <input type="text" class="form-control" name="userQq" placeholder="qq">
                                        </div>
                                    </div>


                                    <hr />
                                    <div class="form-group">
                                        <div class="col-lg-offset-1 col-lg-9">
                                            <button type="button" id="addSubmit" class="btn btn-success">提交</button>
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


    $(function(){

        $("#addSubmit").click(function(){

            $.ajax({
                type: "post",
                url: "/selectUserBase/stuAdd",
                data: $("#addForm").serialize(),
                dataType:"json",
                success:function(msg){
                    if("OK"!=msg){
                        alert(" 😅 "+msg);
                    }else {
                        alert(" 😎 添加成功","",function () {
                            location.href="/selectUserBase/stuList";
                        },{type:"success",confirmButtonText:"好的"});
                    }

                },
                error: function(e) {
                    alert(" 😥 系统异常，请与我们的工程师小哥哥联系！");
                }
            });
        });
    });


    /* Curve chart ends */
</script>

</body>
</html>