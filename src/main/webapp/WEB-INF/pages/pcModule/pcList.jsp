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
    <title></title>
    <meta name="author" content="">
    <%@include file="/WEB-INF/pages/common/macTopCommon.jsp" %>
</head>

<body>


<!-- Main content starts -->


<div class="mainbar">

    <!-- Page heading -->
    <div class="page-head">
        <h2 class="pull-left"><i class="icon-home"></i> 流程控制列表</h2>

        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
            <a href="#"><i class="icon-home"></i> 流程控制列表</a>
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


            <!-- Table -->
            <div class="row">

                <div class="col-md-12">

                    <div class="widget">

                        <div class="widget-head" style="position: relative">
                            <div class="pull-left">流程列表</div>
                            <div class="widget-icons pull-right">
                                <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                <a href="#" class="wclose"><i class="icon-remove"></i></a>
                            </div>

                            <div class="clearfix"></div>
                        </div>

                        <div class="widget-content ">
                            <c:choose>
                                <c:when test="${empty requestScope.pcList }">
                                    <div class="row col-lg-pull-12">
                                        暂无数据
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="pc" items="${requestScope.pcList}" varStatus="index">
                                        <div class="row col-lg-pull-12">

                                            <form class="navbar-form center" id="pcForm${pc.id}">

                                                <input type="hidden" name="id" value="${pc.id}"/>
                                                <div class="form-group" style="margin-right: 10px">
                                                    <b>${pc.proName} : </b>
                                                    <div class="input-group date form_datetime">
                                                        <input name="proStartTime" id="proStartTime${pc.id}" onclick="aaa()" class="form-control" type="text"
                                                               placeholder="起始时间" value="<fmt:formatDate value="${pc.proStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly style="width: 200px">
                                                        <span class="input-group-addon" onclick="aaa()"><i
                                                                class="glyphicon glyphicon-remove icon-remove"></i></span>
                                                        <span class="input-group-addon" onclick="aaa()"><i
                                                                class="glyphicon glyphicon-th icon-calendar"></i></span>
                                                    </div>
                                                    -----------------------
                                                    <div class="input-group date form_datetime" onclick="aaa()">
                                                        <input name="proEndTime" id="proEndTime${pc.id}" onclick="aaa()" class="form-control" type="text"
                                                               placeholder="结束时间" value="<fmt:formatDate value="${pc.proEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly style="width: 200px">
                                                        <span class="input-group-addon" onclick="aaa()"><i
                                                                class="glyphicon glyphicon-remove icon-remove"></i></span>
                                                        <span class="input-group-addon" onclick="aaa()"><i
                                                                class="glyphicon glyphicon-th icon-calendar"></i></span>
                                                    </div>

                                                </div>

                                                <button type="button" id="updateSubmit" onclick="updatePc(${pc.id})" class="btn btn-warning">修改</button>
                                                <button type="reset" class="btn btn-success">重填</button>


                                            </form>
                                        </div>

                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>

                        </div>

                    </div>

                </div>

            </div>

        </div>
    </div>

    <!-- Matter ends -->
</div>


<div class="clearfix"></div>
<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>
<script type="text/javascript">
    /***
     * 修改流程时间
     * @param id
     */
    function updatePc(id) {
        var start = '#proStartTime'+id;
        var end = '#proEndTime'+id;
        var s = $(start).val();
        var e = $(end).val();
        if(s==="" || e===""){
            alert("时间不可以为空");
            return;
        }
        s = s.replace(/-/g,"/");
        e = e.replace(/-/g,"/");
        var sdate = new Date(s);
        var edate = new Date(e);
        $.ajax({
            type: "post",
            url: "/selectProcessControl/updatePc", //你处理上传文件的服务端
            data: {"id" : id,
                "proStartTime" :sdate,
                "proEndTime" : edate
            },
            dataType: "json",
            success: function (msg) {//调用成功时怎么处理
                if ("OK" != msg) {
                    alert(" 😅 " + msg);
                } else {
                    alert(" 😋 修改成功", "", function () {
                        location.href = "/selectProcessControl/pcList";
                    }, {type: "success", confirmButtonText: "好的"});
                }

            },//end success
            error: function (e) {
                alert(" 😥 系统异常，请与我们的工程师联系！");
            }
        });
    }



</script>


</body>
</html>