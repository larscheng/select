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
        <li><a href="${ctx}/test.jsp" target="mainFrame" ><i class="icon-list-alt"></i> 首页  </a></li>
        <%--<li><a href="${ctx}/selectBugLog/bugInitList" target="mainFrame" ><i class="icon-list-alt"></i> <b style="color: red">bug提交，谢谢 </b> </a></li>--%>
        <li class="has_sub"><a href="#"><i class="icon-list-alt"></i> 管理员信息  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
                <li><a href="${ctx}/selectUserBase/admList" target="mainFrame" >管理员列表</a></li>
                <li><a href="${ctx}/selectUserBase/admSelfInfo?id=${sessionScope.sessionUser.id}" target="mainFrame" >我的信息</a></li>
            </ul>
        </li>

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

        <li><a href="${ctx}/selectSubject/subAllList" target="mainFrame" ><i class="icon-file-alt"></i> 历届题目信息 </a></li>

        <li class="has_sub"><a href="#"><i class="icon-file-alt"></i> 报表统计  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
                <li><a href="${ctx}/selectSubject/subAllList" target="mainFrame">题目列表</a></li>
                <li><a href="${ctx}/selectTopic/topicCountList"  target="mainFrame">选题列表</a></li>
                <li><a href="${ctx}/selectTopic/topicScoreList"  target="mainFrame">成绩列表</a></li>
            </ul>
        </li>
        <li><a href="${ctx}/selectTopic/topicUploadList" target="mainFrame" ><i class="icon-file-alt"></i> 成绩上传 </a></li>
        <li><a href="${ctx}/selectProcessControl/pcList"  target="mainFrame"><i class="icon-bar-chart"></i>流程控制管理</a></li>
        <li><a href="${ctx}/selectScorePer/scoreList"  target="mainFrame"><i class="icon-bar-chart"></i>成绩比例划分</a></li>
        <li><a href="${ctx}/logout"  target="_top"><i class="icon-bar-chart"></i>注销</a></li>

    </ul>
</div>

<!-- Sidebar ends -->
<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>
</body>
</html>
