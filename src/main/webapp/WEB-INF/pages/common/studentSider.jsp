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
        <li><a href="${ctx}/selectUserBase/stuSelfInfo?id=${sessionScope.sessionUser.id}"  target="mainFrame" class=""><i class="icon-home"></i>个人信息变更</a></li>
        <li><a href="${ctx}/selectSubject/stuSubList?selectId=${sessionScope.sessionUser.id}" target="mainFrame" ><i class="icon-file-alt"></i> 论文题目列表 </a></li>
        <li><a href="${ctx}/selectTopic/topicList?stuId=${sessionScope.sessionUser.id}" target="mainFrame" ><i class="icon-file-alt"></i> 选题信息管理 </a></li>
        <li><a href="${ctx}/selectSubject/subAllList?selectId=${sessionScope.sessionUser.id}" target="mainFrame" ><i class="icon-file-alt"></i> 历届论文信息 </a></li>

        <li><a href="${ctx}/selectTopic/topicUploadList?stuId=${sessionScope.sessionUser.id}" target="mainFrame"><i class="icon-calendar"></i>成绩查询</a></li>
    </ul>
</div>

<!-- Sidebar ends -->
<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>
<script>

    function stuSubList() {
        window.location.href='${ctx}/selectSubject/stuSubList?stuMajorId=${sessionScope.sessionUser.stuMajorId}&stuYear=${sessionScope.sessionUser.stuYear}';
    }

</script>
</body>
</html>
