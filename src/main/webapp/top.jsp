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
    <!-- Logo section -->
    <div class="col-md-3 ">
        <!-- Logo. -->
        <div class="logo">
            <h1 class=" pull-left"><a href="#"><img src="resources/favicon.ico" width="60px" height="60px"><span class="bold"></span></a></h1>
            <div class="meta" style="line-height: 60px;color: black; font-size: 28px; font-family: 华文楷体 ;"><b>毕业设计综合信息管理系统</b></div>
        </div>
        <!-- Logo ends -->
    </div>

    <div class="container">

        <div class="row">
            <div class="col-md-4">
                <!-- Logo. -->
                <div class="logo pull-right">
                    <%--<h1><a href="#"><img src="resources/favicon.ico" width="35px" height="35px"><span class="bold"></span></a></h1>--%>
                    <%--<p class="meta">商洛学院毕业选题系统</p>--%>
                </div>
                <!-- Logo ends -->
            </div>


            <ul class="pull-right" style="list-style: none;  line-height: 60px">
                <li class="">
                    <a  href="#" style=" ; font-size:15px;color: black;padding-right: 50px;text-decoration: none;">
                        欢迎您 <i class="icon-user"></i> :
                        <b>${sessionScope.get("sessionUser").userName}</b>
                        <c:choose>
                            <c:when test="${sessionScope.get('sessionUser').userType eq 0}">
                                超级管理员
                            </c:when>
                            <c:when test="${sessionScope.get('sessionUser').userType eq 1}">
                                管理员
                            </c:when>
                            <c:when test="${sessionScope.get('sessionUser').userType eq 2}">
                                老师
                            </c:when>
                            <c:otherwise>
                                同学
                            </c:otherwise>
                        </c:choose>



                    </a>


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
