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

        <li><a href="${ctx}/selectUserBase/stuSelfInfo"  target="mainFrame" class=""><i class="icon-home"></i>个人信息变更</a></li>
        <%--<li><a href="${ctx}/selectUserBase/stuList" target="mainFrame" ><i class="icon-list-alt"></i> 学生信息查看  </a></li>--%>
        <li><a href="${ctx}/selectUserBase/teaList" target="mainFrame" ><i class="icon-file-alt"></i> 教师信息查看 </a></li>
        <li><a href="${ctx}/selectSubject/stuSubList" target="mainFrame" ><i class="icon-file-alt"></i> 论文题目列表 </a></li>
        <li><a href="${ctx}/selectTopic/topicList" target="mainFrame" ><i class="icon-file-alt"></i> 选题信息管理 </a></li>
        <li><a href="${ctx}/selectSubject/subAllList" target="mainFrame" ><i class="icon-file-alt"></i> 历届论文信息 </a></li>

        <li><a href="javascript:;" onclick="score()" target="mainFrame"><i class="icon-calendar"></i>成绩查询</a></li>
        <li><a href="${ctx}/logout" target="_top"><i class="icon-bar-chart"></i>注销</a></li>

    </ul>
</div>

<!-- Sidebar ends -->
<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>
<script>


    function score() {

        $.ajax({
            type: "post",
            url: "${ctx}/selectProcessControl/testPc",
            data: {"id": 7},
            dataType: "json",
            success: function (msg) {
                if ("OK" != msg) {
                    window.open('times.html','mainFrame')
                } else {
                    window.open('selectTopic/topicUploadList','mainFrame')
                }
            },
            error: function (e) {
                alert(" 😥 系统异常，请与我们的工程师联系！");
            }
        });
    }

</script>
</body>
</html>
