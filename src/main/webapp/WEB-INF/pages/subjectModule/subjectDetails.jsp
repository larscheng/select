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
            <span class="page-meta">题目详情</span>
        </h2>


        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
            <a href="index.html"><i class="icon-home"></i> 论文信息管理</a>
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
                                    <c:if test="${requestScope.sub.admAuditState eq 2}">
                                        <div class="form-group ">

                                            <label class="col-lg-1 control-label">总分</label>

                                            <div class="col-lg-1 panel panel-default pdl" >
                                                <c:out value="${requestScope.sub.finalTotalScore}" default="0"/>
                                            </div>
                                        </div>

                                        <div class="form-group ">


                                            <label class="col-lg-1 control-label">指导老师打分</label>

                                            <div class="col-lg-1 panel panel-default pdl" >

                                                <c:out value="${requestScope.sub.tutorScore}" default="0"/>
                                            </div>



                                            <label class="col-lg-4 control-label">评阅老师打分</label>

                                            <div class="col-lg-1 panel panel-default pdl" >

                                                <c:out value="${requestScope.sub.judgeScore}" default="0"/>
                                            </div>

                                            <label class="col-lg-2 control-label">答辩得分</label>

                                            <div class="col-lg-1 panel panel-default pdl" >

                                                <c:out value="${requestScope.sub.defenceScore}" default="0"/>
                                            </div>


                                        </div>

                                    </c:if>

                                    <div class="form-group">

                                        <label class="col-lg-4 control-label">题目名称</label>

                                        <div class="col-lg-4 panel panel-default pdl" >
                                            ${requestScope.sub.subName}
                                        </div>

                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">发布教师</label>

                                        <div class="col-lg-4 panel panel-default pdl" >
                                            ${requestScope.sub.subTeaName}
                                        </div>
                                    </div>

                                        <div class="form-group">
                                            <label class="col-lg-4 control-label">题目类型</label>
                                            <div class="col-lg-4 panel panel-default pdl" >
                                                ${requestScope.sub.typeName}
                                            </div>

                                        </div>


                                    <div class="form-group">

                                        <label class="col-lg-4 control-label">题目届别</label>
                                        <div class="col-lg-4 panel panel-default pdl" >
                                            ${requestScope.sub.subYear}级
                                        </div>
                                    </div>

                                    <div class="form-group">

                                        <label class="col-lg-4 control-label">题目文件</label>
                                        <div class="col-lg-2 panel panel-default pdl" >
                                            ${requestScope.sub.subFile}
                                        </div>
                                        <div class="col-lg-2">
                                            <a class="btn btn-info"
                                               href="http://10.200.0.64:8012/onlinePreview?url=http://10.200.0.64:8012/${requestScope.sub.subFile}" target="_blank">预览</a>
                                            <a class="btn btn-info"
                                               href="${ctx}/selectSubject/subFileDown?fileName=${requestScope.sub.subFile}" target="_blank">下载</a>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">题目内容</label>

                                        <div class="col-lg-4 panel panel-default pdl" >
                                            ${requestScope.sub.subContent}
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">审核状态</label>
                                        <div class="col-lg-4 panel panel-default pdl" >
                                            ${requestScope.sub.subState}
                                        </div>
                                    </div>

                                        <div class="form-group">
                                            <label class="col-lg-4 control-label">审核意见</label>

                                            <div class="col-lg-4 panel panel-default pdl" >
                                                <c:out value="${requestScope.sub.admAuditContent}" default="无" escapeXml="false"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-lg-4 control-label">审核人</label>

                                            <div class="col-lg-4 panel panel-default pdl" >
                                                <c:out value="${requestScope.sub.admAuditName}" default="无"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-lg-4 control-label">选题状态</label>
                                            <div class="col-lg-4 panel panel-default pdl" >
                                                ${requestScope.sub.subSelectStatusName}
                                            </div>
                                        </div>

                                        <c:if test="${requestScope.sub.admAuditState != 0}">

                                        <div class="form-group">
                                            <c:choose>
                                                <c:when test="${requestScope.sub.subSelectStatus eq 0}">
                                                    <label class="col-lg-4 control-label">审核时间</label>
                                                </c:when>
                                                <c:otherwise>
                                                    <label class="col-lg-4 control-label">选题时间</label>
                                                </c:otherwise>
                                            </c:choose>


                                            <div class="col-lg-4 panel panel-default pdl" >
                                                <fmt:formatDate value="${requestScope.sub.gmtModify}"   pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </div>
                                        </div>
                                        </c:if>
                                    <hr />
                                    <div class="form-group">
                                        <div class="col-lg-offset-1 col-lg-9">
                                            <button type="button" class="btn btn-info" onclick="window.history.go(-1);">返回</button>
                                            <c:if test="${sessionScope.userType eq 1}">
                                                <c:if test="${requestScope.sub.admAuditState eq 1 || sessionScope.sessionUser.userType eq 0}">
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
                    url:"${ctx}/selectSubject/subAudited",
                    data:{"id":id,"admAuditState":2,"admAuditId":${sessionScope.sessionUser.id}},
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else {
                            alert(" 😋 审核通过","",function () {
                                location.href="${ctx}/selectSubject/unSubList";
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
                    url:"${ctx}/selectSubject/subAudited",
                    data:{"id":id,"admAuditState":1,"admAuditId":${sessionScope.sessionUser.id},"admAuditContent":$("#reason").val()},
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else{
                            alert(" 😋 审核完成！","",function () {
                                location.href="${ctx}/selectSubject/unSubList";
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
                url: "${ctx}/selectUserBase/teaUpdate",
                data: $("#updateForm").serialize(),
                dataType:"json",
                success:function(msg){
                    if("OK"!=msg){
                        alert(" 😅 "+msg);
                    }else {
                        alert(" 😎 修改成功","",function () {
                            location.href="${ctx}/selectUserBase/teaList";
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