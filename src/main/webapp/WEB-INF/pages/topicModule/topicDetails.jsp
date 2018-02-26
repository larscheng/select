<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <span class="page-meta">选题详情</span>
        </h2>


        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
            <a href="index.html"><i class="icon-home"></i> 选题信息管理</a>
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
                            <div class="pull-left">详情</div>
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

                                        <label class="col-lg-4 control-label">题目名称</label>

                                        <div class="col-lg-4 panel panel-default pdl" >
                                            ${requestScope.topicDetails.subName}
                                        </div>

                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">发布教师</label>

                                        <div class="col-lg-4 panel panel-default pdl" >
                                            ${requestScope.topicDetails.teaName}
                                        </div>
                                    </div>

                                        <div class="form-group">
                                            <label class="col-lg-4 control-label">选题学生</label>
                                            <div class="col-lg-4 panel panel-default pdl" >
                                                ${requestScope.topicDetails.stuName}
                                            </div>

                                        </div>


                                    <div class="form-group">

                                        <label class="col-lg-4 control-label">选择理由</label>
                                        <div class="col-lg-4 panel panel-default pdl" >
                                            <c:out value="${requestScope.topicDetails.stuSelectReason}" default="无" escapeXml="false"/>
                                        </div>
                                    </div>

                                    <div class="form-group">

                                        <label class="col-lg-4 control-label">题目届别</label>
                                        <div class="col-lg-4 panel panel-default pdl" >
                                            ${requestScope.topicDetails.topicYear}级
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">审核状态</label>
                                        <div class="col-lg-4 panel panel-default pdl" >
                                            <c:if test="${requestScope.topicDetails.teaAuditState eq 0}">
                                                未处理
                                            </c:if>
                                            <c:if test="${requestScope.topicDetails.teaAuditState eq 1}">
                                                审核不通过
                                            </c:if>
                                            <c:if test="${requestScope.topicDetails.teaAuditState eq 2}">
                                                审核通过
                                            </c:if>
                                        </div>
                                    </div>

                                        <div class="form-group">
                                            <label class="col-lg-4 control-label">审核意见</label>

                                            <div class="col-lg-4 panel panel-default pdl" >
                                                <c:out value="${requestScope.topicDetails.teaAuditContent}" default="无" escapeXml="false"/>
                                            </div>
                                        </div>



                                    <c:choose>
                                        <c:when test="${requestScope.topicDetails.teaAuditState eq 0}">
                                            <div class="form-group">
                                                <label class="col-lg-4 control-label">选题时间</label>
                                                <div class="col-lg-4 panel panel-default pdl" >
                                                    <fmt:formatDate value="${requestScope.topicDetails.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="form-group">
                                                <label class="col-lg-4 control-label">审核时间</label>

                                                <div class="col-lg-4 panel panel-default pdl" >
                                                    <fmt:formatDate value="${requestScope.topicDetails.gmtModify}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>


                                    <hr />
                                    <div class="form-group">
                                        <div class="col-lg-offset-1 col-lg-9">
                                            <button type="button" class="btn btn-info" onclick="window.history.go(-1);">返回</button>
                                            <c:if test="${sessionScope.userType eq 1}">
                                                <c:if test="${requestScope.sub.admAuditState eq 1}">
                                                    <button  type="button" class="btn  btn-success" onclick="subSuccess(${requestScope.sub.id})">通过</button>
                                                </c:if>
                                            </c:if>

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


    function cleanAll() {
        $("#reason").val("");
    }

    function subSuccess(id){
        confirm(" 😲 确认审核通过？","",function (isConfirm) {
            if (isConfirm){
                $.ajax({
                    type:"POST",
                    url:"/selectSubject/subAudited",
                    data:{"id":id,"admAuditState":2,"admAuditId":${sessionScope.sessionUser.id}},
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else {
                            alert(" 😋 审核通过","",function () {
                                location.href="/selectSubject/unSubList";
                            },{type:"success",confirmButtonText:"好的"});
                        }
                    },
                    error:function(e){
                        alert("😥 系统异常，请与我们的程序员小哥哥联系！");
                    }
                });
            }
        });
    }


    function subFail(id){

        confirm(" 😲 确认不通过？","",function (isConfirm) {
            if (isConfirm){
                $.ajax({
                    type:"POST",
                    url:"/selectSubject/subAudited",
                    data:{"id":id,"admAuditState":1,"admAuditId":${sessionScope.sessionUser.id},"admAuditContent":$("#reason").val()},
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else{
                            alert(" 😋 审核完成！","",function () {
                                location.href="/selectSubject/unSubList";
                            },{type:"success",confirmButtonText:"好的"});
                        }
                    },
                    error:function(e){
                        alert("😥 系统异常，请与我们的工程师联系！");
                    }
                });
            }
        });

    }



    $(function(){



        $("#updateSubmit").click(function(){

            $.ajax({
                type: "post",
                url: "/selectUserBase/teaUpdate",
                data: $("#updateForm").serialize(),
                dataType:"json",
                success:function(msg){
                    if("OK"!=msg){
                        alert(" 😅 "+msg);
                    }else {
                        alert(" 😎 修改成功","",function () {
                            location.href="/selectUserBase/teaList";
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