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
        <li><a href="${ctx}/selectUserBase/userList"  target="mainFrame" class=""><i class="icon-home"></i> tea</a>
            <!-- Sub menu markup
            <ul>
              <li><a href="#">Submenu #1</a></li>
              <li><a href="#">Submenu #2</a></li>
              <li><a href="#">Submenu #3</a></li>
            </ul>-->
        </li>

        <li class="has_sub"><a href="#"><i class="icon-list-alt"></i> 院系专业  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
                <li><a href="${ctx}/selectDepartment/depList" target="mainFrame" >系别</a></li>
                <li><a href="${ctx}/selectMajor/majList" target="mainFrame" >专业</a></li>
            </ul>
        </li>

        <li><a href="${ctx}/selectUserBase/stuList" target="mainFrame" ><i class="icon-list-alt"></i> 学生信息管理  </a>
            <%--<ul>--%>
            <%--<li><a href="/selectUserBase/test"  >插件页面 #1</a></li>--%>
            <%--<li><a href="widgets2.html">插件页面 #2</a></li>--%>
            <%--<li><a href="widgets3.html">插件页面 #3</a></li>--%>
            <%--</ul>--%>
        </li>
        <li><a href="${ctx}/selectUserBase/teaList" target="mainFrame" ><i class="icon-file-alt"></i> 教师信息管理 </a>
            <%--<ul>--%>
            <%--<li><a href="post.html">表单Post</a></li>--%>
            <%--<li><a href="login.html">登录页</a></li>--%>
            <%--<li><a href="register.html">注册页面</a></li>--%>
            <%--<li><a href="support.html">帮助页</a></li>--%>
            <%--<li><a href="invoice.html">购物清单</a></li>--%>
            <%--<li><a href="profile.html">个人资料</a></li>--%>
            <%--<li><a href="gallery.html">相册页面</a></li>--%>
            <%--</ul>--%>
        </li>
        <li class="has_sub"><a href="#"><i class="icon-file-alt"></i> 论文题目审核  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
                <li><a href="${ctx}/selectSubject/mySubList?teaId=${sessionScope.sessionUser.id}" target="mainFrame">我的题目</a></li>
                <li><a href="${ctx}/selectSubject/optionalList"  target="mainFrame">已通过列表</a></li>
            </ul>
        </li>
        <li><a href="charts.html"><i class="icon-bar-chart"></i>图表</a></li>
        <li><a href="tables.html"><i class="icon-table"></i>表格</a></li>
        <li><a href="forms.html"><i class="icon-tasks"></i>表单</a></li>
        <li><a href="ui.html"><i class="icon-magic"></i>UI图标</a></li>
        <li><a href="calendar.html"><i class="icon-calendar"></i>日历</a></li>
    </ul>
</div>

<!-- Sidebar ends -->
<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>
</body>
</html>
