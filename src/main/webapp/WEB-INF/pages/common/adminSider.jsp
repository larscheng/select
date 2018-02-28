<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/7
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/pages/common/macTopCommon.jsp" %>
</head>
<body>

<!-- Sidebar -->
<div class="sidebar">
    <div class="sidebar-dropdown">
        <%--<a href="#">导航</a>--%>
    </div>

    <!--- Sidebar navigation -->
    <!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->
    <ul id="nav" style="display: block;">
        <!-- Main menu with font awesome icon -->
        <li><a href="${ctx}/selectUserBase/userList"  target="mainFrame" class=""><i class="icon-home"></i> 首页</a></li>

        <li class="has_sub"><a href="#"><i class="icon-list-alt"></i> 院系专业  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
                <li><a href="${ctx}/selectDepartment/depList" target="mainFrame" >系别</a></li>
                <li><a href="${ctx}/selectMajor/majList" target="mainFrame" >专业</a></li>
            </ul>
        </li>

        <li><a href="${ctx}/selectUserBase/stuList" target="mainFrame" ><i class="icon-list-alt"></i> 学生信息管理  </a></li>
        <li><a href="${ctx}/selectUserBase/teaList" target="mainFrame" ><i class="icon-file-alt"></i> 教师信息管理 </a></li>
        <li class="has_sub"><a href="#"><i class="icon-file-alt"></i> 论文题目审核  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
                <li><a href="${ctx}/selectSubject/unSubList" target="mainFrame">待审核列表</a></li>
                <li><a href="${ctx}/selectSubject/subList"  target="mainFrame">已审核列表</a></li>
            </ul>
        </li>
        <li><a href="${ctx}/selectTopic/topicList" target="mainFrame" ><i class="icon-file-alt"></i> 选题信息列表 </a></li>
        <li><a href="charts.html"><i class="icon-bar-chart"></i>未作</a></li>
        <li><a href="tables.html"><i class="icon-table"></i>未作</a></li>
        <li><a href="forms.html"><i class="icon-tasks"></i>未作</a></li>
        <li><a href="ui.html"><i class="icon-magic"></i>未作</a></li>
        <li><a href="calendar.html"><i class="icon-calendar"></i>未作</a></li>
    </ul>
</div>

<!-- Sidebar ends -->
<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>
</body>
</html>
