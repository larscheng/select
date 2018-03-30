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
            <span class="page-meta">成绩评分</span>
        </h2>


        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
            <a href="index.html"><i class="icon-home"></i> 成绩上传管理</a>
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
                                        <input type="hidden" name="id" value="${requestScope.topicDetails.id}"/>
                                        <div class="col-lg-4">
                                            <input type="tel" disabled class="form-control" value="${requestScope.topicDetails.subName}"  name="subName" placeholder="电话">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">发布教师</label>

                                        <div class="col-lg-4">
                                            <input type="tel" disabled class="form-control" value="${requestScope.topicDetails.teaName}"  name="teaName" placeholder="电话">
                                        </div>
                                    </div>

                                        <div class="form-group">
                                            <label class="col-lg-4 control-label">选题学生</label>

                                            <div class="col-lg-4">
                                                <input type="tel" disabled class="form-control" value="${requestScope.topicDetails.stuName}"  name="stuName" placeholder="电话">
                                            </div>
                                        </div>





                                    <c:choose>
                                        <c:when test="${requestScope.topicDetails.teaAuditState eq 0}">
                                            <div class="form-group">
                                                <label class="col-lg-4 control-label">选题时间</label>
                                                <div class="col-lg-4">
                                                    <input type="tel" disabled class="form-control" value="<fmt:formatDate value="${requestScope.topicDetails.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/>"  name="gmtCreate" placeholder="电话">
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="form-group">
                                                <label class="col-lg-4 control-label">审核时间</label>
                                                <div class="col-lg-4">
                                                    <input type="tel" disabled class="form-control" value="<fmt:formatDate value="${requestScope.topicDetails.gmtModify}" pattern="yyyy-MM-dd HH:mm:ss"/>"  name="gmtCreate" placeholder="电话">
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>


                                    <c:if test="${sessionScope.userType eq 2}">
                                        <div class="form-group ">
                                            <label class="col-lg-1 control-label">指导老师打分</label>
                                            <div class="col-lg-3">
                                                <input type="hidden" class="form-control" name="id" value="${requestScope.topicDetails.id}">
                                                <input type="text" class="form-control" name="tutorScore" value="${requestScope.topicDetails.tutorScore}"  placeholder="请输入分数">
                                            </div>
                                        </div>
                                    </c:if>

                                        <c:if test="${sessionScope.userType eq 0 ||sessionScope.userType eq 1 }">
                                            <div class="form-group ">

                                                <label class="col-lg-4 control-label">评阅老师打分</label>
                                                <div class="col-lg-3">
                                                    <input type="hidden" class="form-control" name="id"
                                                           value="${requestScope.topicDetails.id}">
                                                    <input type="text" class="form-control" name="judgeScore"
                                                           value="${requestScope.topicDetails.judgeScore}"
                                                           placeholder="请输入分数">
                                                </div>

                                            </div>

                                            <div class="form-group ">

                                                <label class="col-lg-4 control-label">答辩得分</label>
                                                <div class="col-lg-3">
                                                    <input type="text" class="form-control" name="defenceScore"
                                                           value="${requestScope.topicDetails.defenceScore}"
                                                           placeholder="请输入分数">
                                                </div>

                                            </div>
                                        </c:if>

                                    <hr />
                                    <div class="form-group">
                                        <div class="col-lg-offset-1 col-lg-9">
                                            <button  type="button" id="updateSubmit" class="btn  btn-danger">提交</button>
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
    sessionStorage.setItem("userType",${sessionScope.userType});

    var manType = sessionStorage.getItem("userType");


    $(function(){
        var url = "${ctx}/selectTopic/topicUploadList?teaId=${sessionScope.sessionUser.id}";
        if(manType==0||manType==1){
            url = "${ctx}/selectTopic/topicUploadList";
        }
        $("#updateSubmit").click(function(){
            $.ajax({
                type: "post",
                url: "/selectTopic/uploadScore",
                data: $("#updateForm").serialize(),
                dataType:"json",
                success:function(msg){
                    if("OK"!=msg){
                        alert(msg);
                    }else {
                        alert("编辑成功","",function () {
                            location.href=url;
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