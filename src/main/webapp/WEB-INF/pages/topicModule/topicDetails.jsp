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
            <a href="${ctx}/index" target="_top"><i class="icon-home"></i> 选题信息管理</a>
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

                                    <c:if test="${requestScope.topicDetails.teaAuditState eq 2}">
                                        <div class="form-group ">

                                            <label class="col-lg-1 control-label">总分</label>

                                            <div class="col-lg-1 panel panel-default pdl" >
                                                <c:out value="${requestScope.topicDetails.finalTotalScore}" default="0"/>
                                            </div>
                                        </div>

                                        <div class="form-group ">


                                            <label class="col-lg-1 control-label">指导老师打分</label>

                                            <div class="col-lg-1 panel panel-default pdl" >

                                                <c:out value="${requestScope.topicDetails.tutorScore}" default="0"/>
                                            </div>



                                            <label class="col-lg-4 control-label">评阅老师打分</label>

                                            <div class="col-lg-1 panel panel-default pdl" >

                                                <c:out value="${requestScope.topicDetails.judgeScore}" default="0"/>
                                            </div>

                                            <label class="col-lg-2 control-label">答辩得分</label>

                                            <div class="col-lg-1 panel panel-default pdl" >

                                                <c:out value="${requestScope.topicDetails.defenceScore}" default="0"/>
                                            </div>


                                        </div>

                                    </c:if>


                                    <c:if test="${sessionScope.userType eq 3}">
                                        <c:choose>
                                            <c:when test="${requestScope.topicDetails.taskFile != null}">
                                                <div class="form-group">
                                                    <label class="col-lg-4 control-label">任务书</label>
                                                    <div class="col-lg-3 panel panel-default pdl" >
                                                            ${requestScope.topicDetails.taskFile}
                                                    </div>
                                                    <div class="col-lg-2">
                                                        <a class="btn btn-info"
                                                           href="http://127.0.0.1:8012/onlinePreview?url=http://127.0.0.1:8012/${requestScope.topicDetails.taskFile}" target="_blank">预览</a>
                                                        <a class="btn btn-info"
                                                           href="${ctx}/selectSubject/subFileDown?fileName=${requestScope.topicDetails.taskFile}" target="_blank">下载</a>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="form-group">
                                                    <label class="col-lg-4 control-label">任务书</label>
                                                    <div class="col-lg-3">
                                                        <input type="file" class="form-control" name="taskFile">
                                                    </div>
                                                    <button type="button" class="btn btn-success icon-upload" onclick="uploadTaskBook(1)">上传</button>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${requestScope.topicDetails.openingReport != null}">
                                                <div class="form-group">
                                                    <label class="col-lg-4 control-label">开题报告</label>
                                                    <div class="col-lg-3 panel panel-default pdl" >
                                                            ${requestScope.topicDetails.openingReport}
                                                    </div>
                                                    <div class="col-lg-2">
                                                        <a class="btn btn-info"
                                                           href="http://localhost:8012/onlinePreview?url=http://localhost:8012/${requestScope.topicDetails.openingReport}" target="_blank">预览</a>
                                                        <a class="btn btn-info"
                                                           href="${ctx}/selectSubject/subFileDown?fileName=${requestScope.topicDetails.openingReport}" target="_blank">下载</a>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="form-group">
                                                    <label class="col-lg-4 control-label">开题报告</label>
                                                    <div class="col-lg-3">
                                                        <input type="file" class="form-control" name="openingReport">
                                                    </div>
                                                    <button type="button" class="btn btn-success icon-upload"  onclick="uploadTaskBook(2)">上传</button>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${requestScope.topicDetails.dissertation != null}">
                                                <div class="form-group">
                                                    <label class="col-lg-4 control-label">毕业论文</label>
                                                    <div class="col-lg-3 panel panel-default pdl" >
                                                            ${requestScope.topicDetails.dissertation}
                                                    </div>
                                                    <div class="col-lg-2">
                                                        <a class="btn btn-info"
                                                           href="http://localhost:8012/onlinePreview?url=http://localhost:8012/${requestScope.topicDetails.dissertation}" target="_blank">预览</a>
                                                        <a class="btn btn-info"
                                                           href="${ctx}/selectSubject/subFileDown?fileName=${requestScope.topicDetails.dissertation}" target="_blank">下载</a>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="form-group">
                                                    <label class="col-lg-4 control-label">毕业论文</label>
                                                    <div class="col-lg-3">
                                                        <input type="file" class="form-control" name="dissertation">
                                                    </div>
                                                    <button type="button" class="btn btn-success icon-upload" onclick="uploadTaskBook(3)">上传</button>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                    <c:if test="${sessionScope.userType != 3}">
                                        <c:choose>
                                            <c:when test="${requestScope.topicDetails.taskFile != null}">
                                                <div class="form-group">
                                                    <label class="col-lg-4 control-label">任务书</label>
                                                    <div class="col-lg-3 panel panel-default pdl" >
                                                            ${requestScope.topicDetails.taskFile}
                                                    </div>
                                                    <div class="col-lg-2">
                                                        <a class="btn btn-info"
                                                           href="http://localhost:8012/onlinePreview?url=http://localhost:8012/${requestScope.topicDetails.taskFile}" target="_blank">预览</a>
                                                        <a class="btn btn-info"
                                                           href="${ctx}/selectSubject/subFileDown?fileName=${requestScope.topicDetails.taskFile}" target="_blank">下载</a>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="form-group">
                                                    <label class="col-lg-4 control-label">任务书</label>
                                                    <div class="col-lg-4 panel panel-default pdl" >
                                                        <b style="color: red"> 暂未上传</b>
                                                    </div>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${requestScope.topicDetails.openingReport != null}">
                                                <div class="form-group">
                                                    <label class="col-lg-4 control-label">开题报告</label>
                                                    <div class="col-lg-3 panel panel-default pdl" >
                                                            ${requestScope.topicDetails.openingReport}
                                                    </div>
                                                    <div class="col-lg-2">
                                                        <a class="btn btn-info"
                                                           href="http://localhost:8012/onlinePreview?url=http://localhost:8012/${requestScope.topicDetails.openingReport}" target="_blank">预览</a>
                                                        <a class="btn btn-info"
                                                           href="${ctx}/selectSubject/subFileDown?fileName=${requestScope.topicDetails.openingReport}" target="_blank">下载</a>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="form-group">
                                                    <label class="col-lg-4 control-label">开题报告</label>
                                                    <div class="col-lg-4 panel panel-default pdl" >
                                                        <b style="color: red"> 暂未上传</b>
                                                    </div>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${requestScope.topicDetails.dissertation != null}">
                                                <div class="form-group">
                                                    <label class="col-lg-4 control-label">毕业论文</label>
                                                    <div class="col-lg-3 panel panel-default pdl" >
                                                            ${requestScope.topicDetails.dissertation}
                                                    </div>
                                                    <div class="col-lg-2">
                                                        <a class="btn btn-info"
                                                           href="http://localhost:8012/onlinePreview?url=http://localhost:8012/${requestScope.topicDetails.dissertation}" target="_blank">预览</a>
                                                        <a class="btn btn-info"
                                                           href="${ctx}/selectSubject/subFileDown?fileName=${requestScope.topicDetails.dissertation}" target="_blank">下载</a>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="form-group">
                                                    <label class="col-lg-4 control-label">毕业论文</label>
                                                    <div class="col-lg-4 panel panel-default pdl" >
                                                        <b style="color: red"> 暂未上传</b>
                                                    </div>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>






                                    <div class="form-group">

                                        <label class="col-lg-4 control-label">题目名称</label>
                                        <input type="hidden" name="id" value="${requestScope.topicDetails.id}"/>
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
                                                <b style="color: red">审核不通过 <c:if test="${sessionScope.userType eq 3}">（删除该记录可以重新选题）</c:if></b>
                                            </c:if>
                                            <c:if test="${requestScope.topicDetails.teaAuditState eq 2}">
                                               <b style="color: green">审核通过</b>
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

                                            <c:if test="${sessionScope.userType eq 3}">
                                                <c:if test="${requestScope.topicDetails.teaAuditState eq 1}">
                                                    <button  type="button" class="btn  btn-danger" onclick="topicDel(${requestScope.topicDetails.id})">删除</button>
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

    //上传题目文件
    function uploadTaskBook(type) {
        var uploadUrl;
        if (type === 1){
            uploadUrl = "${ctx}/selectTopic/uploadTaskBook";
        }else if (type === 2){
            uploadUrl = "${ctx}/selectTopic/uploadOpeningReport";
        }else {
            uploadUrl = "${ctx}/selectTopic/uploadDissertation";
        }
        var formData = new FormData($( "#updateForm" )[0]);  // 要求使用的html对象
        console.log(uploadUrl);
        confirm(" 😲 确认上传吗？","",function (isConfirm) {
            if (isConfirm){
                $.ajax({
                    type:"POST",
                    url:uploadUrl,
                    data:formData,
                    async: true,
                    // 下面三个参数要指定，如果不指定，会报一个JQuery的错误
                    cache: false,
                    contentType: false,
                    processData: false,
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else {
                            alert(" 😋 上传成功","",function () {
                                location.href="${ctx}/selectTopic/topicDetails?id=${requestScope.topicDetails.id}";
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





    function topicDel(id){
        confirm(" 😲 确认删除吗？","",function (isConfirm) {
            if (isConfirm){
                $.ajax({
                    type:"GET",
                    url:"${ctx}/selectTopic/topicDel",
                    data:{"id":id},
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else {
                            alert(" 😋 删除成功","",function () {
                                location.href="${ctx}/selectTopic/topicList?stuId=${sessionScope.sessionUser.id}";
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







    /* Curve chart ends */
</script>

</body>
</html>