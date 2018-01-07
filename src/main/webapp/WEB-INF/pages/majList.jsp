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


<!-- Header starts -->
<header>
    <%@include file="/WEB-INF/pages/top.jsp" %>
</header>
<!-- Header ends -->

<!-- Main content starts -->

<div class="content">

    <!-- Sidebar -->
    <%@include file="/WEB-INF/pages/sider.jsp" %>
    <!-- Sidebar ends -->

    <div class="mainbar">

        <!-- Page heading -->
        <div class="page-head">
            <h2 class="pull-left"><i class="icon-home"></i> 专业管理</h2>

            <!-- Breadcrumb -->
            <div class="bread-crumb pull-right">
                <a href="#"><i class="icon-home"></i> 专业管理</a>
                <!-- Divider -->
                <span class="divider">/</span>
                <a href="#" class="bread-current">控制台</a>
            </div>

            <div class="clearfix"></div>

        </div>
        <!-- Page heading ends -->


        <!-- Matter -->

        <div class="matter">
            <div class="container">

                <!-- 搜索页 ================================================== -->
                <div class="row center">
                    <form class="navbar-form" role="add">
                        <button type="button" class="btn btn-info pull-left"><i class="icon-remove"> </i>批量删除</button>
                        <button type="submit" class="btn btn-success pull-left"><i class="icon-edit"> </i>添加专业</button>
                    </form>
                </div>
                <!-- Table -->
                <div class="row">

                    <div class="col-md-12">

                        <div class="widget">

                            <div class="widget-head">
                                <div class="pull-left">Tables</div>
                                <div class="widget-icons pull-right">
                                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                </div>
                                <div class="clearfix"></div>
                            </div>

                            <div class="widget-content">

                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" id="selectAll" name="check"/>序号</th>
                                        <th>专业名称</th>
                                        <th>专业班级数量</th>
                                        <th>所属系别</th>
                                        <th>专业状态</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="maj" items="${majList}" varStatus="index">
                                        <tr>
                                            <td>
                                                <input type="checkbox" value="${maj.id}"/>
                                                    ${index.count}
                                            </td>
                                            <td>${maj.majName}</td>
                                            <td>${maj.majClassNum}</td>
                                            <td>${maj.depId}</td>
                                            <td>
                                                <c:set var="status" value="${maj.majStatus}"/>
                                                <c:choose>
                                                    <c:when test="${status eq 1}">
                                                        <span class="label label-success">启用</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="label label-danger">禁用</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${maj.gmtCreate}</td>

                                            <td>
                                                <c:choose>
                                                    <c:when test="${status eq 0}">
                                                        <button class="btn btn-xs btn-success"><i class="icon-ok"></i>启用</button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button class="btn btn-xs btn-danger"><i class="icon-remove"></i>禁用</button>
                                                    </c:otherwise>
                                                </c:choose>
                                                <button class="btn btn-xs btn-warning"><i class="icon-pencil">编辑</i>
                                                </button>
                                                <button class="btn btn-xs btn-danger"><i class="icon-remove">删除</i>
                                                </button>

                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>

                                <div class="widget-foot center">
                                    <ul class="pagination ">
                                        <li><a href="#">Prev</a></li>
                                        <li><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">Next</a></li>
                                    </ul>

                                    <div class="clearfix"></div>

                                </div>

                            </div>

                        </div>

                    </div>

                </div>

            </div>
        </div>

        <!-- Matter ends -->

    </div>


    <div class="clearfix"></div>

</div>
<!-- Content ends -->

<!-- Footer starts -->
<%@include file="/WEB-INF/pages/common/footer.jsp" %>
<!-- Footer ends -->

<!-- Scroll to top -->
<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>

<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>
</body>
</html>