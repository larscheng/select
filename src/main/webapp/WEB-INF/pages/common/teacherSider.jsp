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
        <li><a href="${ctx}/selectBugLog/bugInitList" target="mainFrame" ><i class="icon-list-alt"></i> <b style="color: red">bug提交，谢谢 </b> </a></li>

        <li><a href="${ctx}/selectUserBase/teaSelfInfo?id=${sessionScope.sessionUser.id}"  target="mainFrame" class=""><i class="icon-home"></i>个人信息变更</a></li>

        <li class="has_sub"><a href="#"><i class="icon-list-alt"></i> 院系专业  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
                <li><a href="${ctx}/selectDepartment/depList" target="mainFrame" >系别</a></li>
                <li><a href="${ctx}/selectMajor/majList" target="mainFrame" >专业</a></li>
            </ul>
        </li>

        <li><a href="${ctx}/selectUserBase/stuList" target="mainFrame" ><i class="icon-list-alt"></i> 学生信息管理  </a></li>
        <li><a href="${ctx}/selectUserBase/teaList" target="mainFrame" ><i class="icon-file-alt"></i> 教师信息查看 </a></li>

        <li class="has_sub"><a href="#"><i class="icon-file-alt"></i> 论文题目审核  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
                <li><a href="${ctx}/selectSubject/mySubList?teaId=${sessionScope.sessionUser.id}" target="mainFrame">我的题目</a></li>
                <li><a href="${ctx}/selectSubject/optionalList"  target="mainFrame">已通过列表</a></li>
            </ul>
        </li>

        <li class="has_sub"><a href="#"><i class="icon-file-alt"></i> 选题信息管理  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
                <li><a href="${ctx}/selectTopic/topicList?teaId=${sessionScope.sessionUser.id}" target="mainFrame" > 被选题记录 </a></li>
                <li><a href="${ctx}/selectTopic/noTopicList?teaId=${sessionScope.sessionUser.id}&teaAuditState=0"  target="mainFrame">待审核记录</a></li>
            </ul>
        </li>


        <li><a href="${ctx}/selectTopic/topicUploadList?teaId=${sessionScope.sessionUser.id}" target="mainFrame" ><i class="icon-file-alt"></i> 成绩上传 </a></li>

        <li><a href="${ctx}/selectSubject/subAllList" target="mainFrame" ><i class="icon-file-alt"></i> 历届题目信息 </a></li>
        <li><a href="${ctx}/logout"  target="_top"><i class="icon-bar-chart"></i>注销</a></li>


    </ul>
</div>

<!-- Sidebar ends -->
<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>
</body>
</html>
