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


<!-- Header starts -->
<%--<header>--%>
    <%--<%@include file="/WEB-INF/pages/top.jsp" %>--%>
<%--</header>--%>
<!-- Header ends -->

<!-- Main content starts -->


    <div class="mainbar">

        <!-- Page heading -->
        <div class="page-head">
            <h2 class="pull-left"><i class="icon-home"></i> 教师信息管理</h2>

            <!-- Breadcrumb -->
            <div class="bread-crumb pull-right">
                <a href="#"><i class="icon-home"></i> 教师信息管理</a>
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

                <!-- 搜索页 ================================================== -->
                <div class="row small">

                    <form class="navbar-form center" role="search" id="searchForm">

                        <div class="form-group " style="position: relative;margin-right: 10px">
                            <input type="text" class="form-control" id="search" name="search" placeholder="账号/姓名/邮箱/电话/qq">
                            <span onclick="search()" style="position: absolute;left: 155px;top: 6px;cursor: pointer"><i class="icon-search" ></i></span>
                        </div>

                        <div class="form-group " style="margin-right: 10px">
                            <select  class="form-control" name="userSex">
                                <option value="" selected>性别</option>
                                <option value="1">男</option>
                                <option value="2">女</option>
                            </select>
                        </div>
                        <div class="form-group " style="margin-right: 10px">
                            <select  class="form-control" name="userStatus">
                                <option value="" selected>状态</option>
                                <option value="1">启用</option>
                                <option value="0">禁用</option>
                            </select>
                        </div>
                        <div class="form-group " style="margin-right: 10px">
                            <select  class="form-control" name="stuClass">
                                <option value="" selected>班级</option>
                                <c:forEach var="cla" items="${requestScope.classList}">
                                    <option value="${cla.stuClass}">${cla.stuClass}班</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group" style="margin-right: 10px">
                            <select  class="form-control" name="stuYear">
                                <option value="" selected>届别</option>
                                <c:forEach var="year" items="${requestScope.yearList}">
                                    <option value="${year.stuYear}">${year.stuYear}级</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group " style="margin-right: 10px">
                            <select  class="form-control" name="stuMajorName">
                                <option value="" selected>专业</option>
                                <c:forEach var="major" items="${requestScope.majorList}">
                                    <option value="${major.stuMajorName}">${major.stuMajorName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group" style="margin-right: 10px">
                            <div class="input-group date form_datetime" onclick="aaa()">
                                <input name="searchBgnTime" class="form-control"type="text" placeholder="起始时间" value="" readonly style="width: 140px">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-remove icon-remove"></i></span>
                                <span class="input-group-addon"><i class="glyphicon glyphicon-th icon-calendar"></i></span>
                            </div> --
                            <div class="input-group date form_datetime" onclick="aaa()">
                                <input name="searchEndTime" class="form-control"type="text" placeholder="结束时间" value="" readonly style="width: 140px">
                                <span class="input-group-addon" onclick="aaa()"><i class="glyphicon glyphicon-remove icon-remove"></i></span>
                                <span class="input-group-addon" onclick="aaa()"><i class="glyphicon glyphicon-th icon-calendar"></i></span>
                            </div>

                        </div>
                        <button type="button" id="searchSubmit" class="btn btn-default">搜索</button>





                    </form>
                </div>
                <!-- Table -->
                <div class="row">

                    <div class="col-md-12">

                        <div class="widget">

                            <div class="widget-head">
                                <div class="pull-left">教师列表</div>
                                <div class="widget-icons pull-right">
                                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                </div>
                                <div class="clearfix"></div>
                            </div>

                            <div class="widget-content ">

                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th class=" text-center"><input type="checkbox" id="selectAll"></th>
                                        <th>序号</th>
                                        <th>姓名</th>
                                        <th>账号</th>
                                        <th>性别</th>
                                        <th>邮箱</th>
                                        <th>电话</th>
                                        <th>qq</th>
                                        <th>所属系别</th>
                                        <th>职称</th>
                                        <th>最高学历</th>
                                        <th>状态</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="user" items="${requestScope.userList}" varStatus="index">
                                        <tr>
                                            <td  class=" text-center"><input type="checkbox" name="ids" value="${user.id}" /></td>
                                            <td>${index.count}</td>
                                            <td>${user.userName}</td>
                                            <td>${user.userCode}</td>
                                            <td>${user.sex}</td>
                                            <td>${user.userMail}</td>
                                            <td>${user.userPhone}</td>
                                            <td>${user.userQq}</td>
                                            <td>${user.teaDepName}</td>
                                            <td><span class="label label-success">${user.teaPositionZ}</span></td>
                                            <td><span class="label label-success">${user.teaEducationZ}</span></td>
                                            <td>
                                                <c:set var="status" value="${user.userStatus}"/>
                                                <c:choose>
                                                    <c:when test="${status eq 1}">
                                                        <span class="label label-success">启用</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="label label-danger">禁用</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td><fmt:formatDate value="${user.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td>
                                                <c:set var="status" value="${user.userStatus}"/>
                                                <c:choose>
                                                    <c:when test="${status eq 0}">
                                                        <button class="btn btn-xs btn-success"><i class="icon-ok"></i>启用</button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button class="btn btn-xs btn-danger"><i class="icon-remove"></i>禁用</button>
                                                    </c:otherwise>
                                                </c:choose>
                                                <button class="btn btn-xs btn-warning"><i class="icon-pencil"></i>编辑</button>
                                                <button class="btn btn-xs btn-info"><i class="icon-pencil"></i>详情</button>
                                                <button class="btn btn-xs btn-danger"><i class="icon-remove">删除</i>
                                                </button>

                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>

                                <div class="widget-foot center">
                                    <ul class="pagination ">
                                        <c:if test="${page.current-1 eq 0}">
                                            <li><a href="#" class="btn  disabled">上一页</a></li>
                                        </c:if>
                                        <c:if test="${page.current-1 > 0}">
                                            <li><a class="disabled" href="/selectUserBase/userList?page=${page.current-1}">上一页</a></li>
                                            <li><a href="/selectUserBase/userList?page=${page.current-1}">${page.current-1}</a></li>
                                        </c:if>


                                        <li><a href="/selectUserBase/userList?page=${page.current}">${page.current}</a></li>

                                        <c:if test="${page.current+1 <= page.pages}">
                                            <li><a href="/selectUserBase/userList?page=${page.current+1}">${page.current+1}</a></li>
                                        </c:if>
                                        <c:if test="${page.current+2 <= page.pages}">
                                            <li><a href="/selectUserBase/userList?page=${page.current+2}">${page.current+2}</a></li>
                                        </c:if>
                                        <c:if test="${page.current+1 <= page.pages}">
                                            <li><a href="/selectUserBase/userList?page=${page.current+1}">下一页</a></li>
                                        </c:if>
                                        <c:if test="${page.current+1 > page.pages}">
                                            <li><a class="btn  disabled" href="#">下一页</a></li>
                                        </c:if>

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
<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>



</body>
</html>