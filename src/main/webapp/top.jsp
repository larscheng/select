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

            <%--<!-- Button section -->--%>
            <%--<div class="col-md-4">--%>

                <%--<!-- Buttons -->--%>
                <%--<ul class="nav nav-pills">--%>

                    <%--<!-- Comment button with number of latest comments count -->--%>
                    <%--<li class="dropdown dropdown-big">--%>
                        <%--<a class="dropdown-toggle" href="#" data-toggle="dropdown">--%>
                            <%--<i class="icon-comments"></i> 聊天 <span   class="label label-info">6</span>--%>
                        <%--</a>--%>

                        <%--<ul class="dropdown-menu">--%>
                            <%--<li>--%>
                                <%--<!-- Heading - h5 -->--%>
                                <%--<h5><i class="icon-comments"></i> 聊天</h5>--%>
                                <%--<!-- Use hr tag to add border -->--%>
                                <%--<hr />--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<!-- List item heading h6 -->--%>
                                <%--<h6><a href="#">你好 :)</a> <span class="label label-warning pull-right">10:42</span></h6>--%>
                                <%--<div class="clearfix"></div>--%>
                                <%--<hr />--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<h6><a href="#">你怎么样?</a> <span class="label label-warning pull-right">20:42</span></h6>--%>
                                <%--<div class="clearfix"></div>--%>
                                <%--<hr />--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<h6><a href="#">你在干撒呢?</a> <span class="label label-warning pull-right">14:42</span></h6>--%>
                                <%--<div class="clearfix"></div>--%>
                                <%--<hr />--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<div class="drop-foot">--%>
                                    <%--<a href="#">查看所有</a>--%>
                                <%--</div>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    <%--</li>--%>

                    <%--<!-- Message button with number of latest messages count-->--%>
                    <%--<li class="dropdown dropdown-big">--%>
                        <%--<a class="dropdown-toggle" href="#" data-toggle="dropdown">--%>
                            <%--<i class="icon-envelope-alt"></i> 收件箱 <span class="label label-primary">6</span>--%>
                        <%--</a>--%>

                        <%--<ul class="dropdown-menu">--%>
                            <%--<li>--%>
                                <%--<!-- Heading - h5 -->--%>
                                <%--<h5><i class="icon-envelope-alt"></i> 消息</h5>--%>
                                <%--<!-- Use hr tag to add border -->--%>
                                <%--<hr />--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<!-- List item heading h6 -->--%>
                                <%--<h6><a href="#">你好啊?</a></h6>--%>
                                <%--<!-- List item para -->--%>
                                <%--<p>最近咋样啊...</p>--%>
                                <%--<hr />--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<h6><a href="#">今天很好啊?</a></h6>--%>
                                <%--<p>相当好...</p>--%>
                                <%--<hr />--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<div class="drop-foot">--%>
                                    <%--<a href="#">查看所有</a>--%>
                                <%--</div>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    <%--</li>--%>

                    <%--<!-- Members button with number of latest members count -->--%>
                    <%--<li class="dropdown dropdown-big">--%>
                        <%--<a class="dropdown-toggle" href="#" data-toggle="dropdown">--%>
                            <%--<i class="icon-user"></i> 用户 <span   class="label label-success">6</span>--%>
                        <%--</a>--%>

                        <%--<ul class="dropdown-menu">--%>
                            <%--<li>--%>
                                <%--<!-- Heading - h5 -->--%>
                                <%--<h5><i class="icon-user"></i> 用户</h5>--%>
                                <%--<!-- Use hr tag to add border -->--%>
                                <%--<hr />--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<!-- List item heading h6-->--%>
                                <%--<h6><a href="#">Ravi Kumar</a> <span class="label label-warning pull-right">免费</span></h6>--%>
                                <%--<div class="clearfix"></div>--%>
                                <%--<hr />--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<h6><a href="#">Balaji</a> <span class="label label-important pull-right">高级</span></h6>--%>
                                <%--<div class="clearfix"></div>--%>
                                <%--<hr />--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<h6><a href="#">Kumarasamy</a> <span class="label label-warning pull-right">免费</span></h6>--%>
                                <%--<div class="clearfix"></div>--%>
                                <%--<hr />--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<div class="drop-foot">--%>
                                    <%--<a href="#">查看所有</a>--%>
                                <%--</div>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    <%--</li>--%>

                <%--</ul>--%>

            <%--</div>--%>


            <ul class="pull-right" style="list-style: none; padding-top: 10px">
                <li class="dropdown pull-right">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#" style="color: black;padding-right: 50px;text-decoration: none;">
                        <i class="icon-user"></i> 管理员 : ${sessionScope.get("sessionUser").userName}<b class="caret"></b>
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
