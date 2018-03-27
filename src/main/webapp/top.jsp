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

<!-- Header starts -->
<header>
    <div class="container">
        <div class="row">

            <!-- Logo section -->
            <div class="col-md-4">
                <!-- Logo. -->
                <div class="logo">
                    <h1><a href="#">logo<span class="bold"></span></a></h1>
                    <p class="meta">商洛学院毕业选题系统</p>
                </div>
                <!-- Logo ends -->
            </div>


            <ul class="pull-right" style="list-style: none; padding-top: 10px">
                <li class="dropdown pull-right">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#" style="color: black;padding-right: 50px;text-decoration: none;">

                        <c:choose>
                            <c:when test="${sessionScope.get('sessionUser').userType eq 0}">
                                <i class="icon-user"></i>超级管理员 :
                            </c:when>
                            <c:when test="${sessionScope.get('sessionUser').userType eq 1}">
                                <i class="icon-user"></i>管理员 :
                            </c:when>
                            <c:when test="${sessionScope.get('sessionUser').userType eq 2}">
                                <i class="icon-user"></i>教师 :
                            </c:when>
                            <c:otherwise>
                                <i class="icon-user"></i>学生 :
                            </c:otherwise>
                        </c:choose>

                        ${sessionScope.get("sessionUser").userName}

                        <b class="caret"></b>
                    </a>

                    <!-- Dropdown menu -->
                    <ul class="dropdown-menu">

                        <li><a href="login.jsp"><i class="icon-off"></i> 退出</a></li>
                    </ul>
                </li>

            </ul>
            <!-- Data section -->



        </div>
    </div>
</header>

<!-- Header ends -->


<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>

</body>
</html>
