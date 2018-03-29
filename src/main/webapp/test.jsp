<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <!-- Title and other stuffs -->
    <title>Select System</title>
    <meta name="author" content="">
    <%@include file="/WEB-INF/pages/common/macTopCommon.jsp" %>


    <link rel="alternate icon" href="resources/assets/i/favicon.ico">
    <link rel="stylesheet" href="resources/assets/css/amazeui.min.css">
    <link rel="stylesheet" href="resources/assets/css/app.css">

    <!-- umeditor css -->
    <link href="resources/umeditor/themes/default/css/umeditor.css" rel="stylesheet">

    <style>
        .title {
            text-align: center;
        }

        .chat-content-container {
            height: 29rem;
            overflow-y: scroll;
            border: 1px solid silver;
        }
    </style>

</head>

<body>



<!-- Main bar -->
<div class="mainbar">

    <!-- Page heading -->
    <div class="page-head">
        <h2 class="pull-left"><i class="icon-file-alt"></i> Gallery</h2>

        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
            <a href="index.html"><i class="icon-home"></i> Home</a>
            <!-- Divider -->
            <span class="divider">/</span>
            <a href="#" class="bread-current">Dashboard</a>
        </div>

        <div class="clearfix"></div>

    </div>
    <!-- Page heading ends -->

    <!-- Matter -->

    <div class="matter">
        <div class="container">
            <div class="row">
                <div class="col-md-12">



                        <!-- Chats widget -->
                        <div class="col-md-10">
                            <!-- Widget -->
                            <div class="widget">
                                <!-- Widget title -->
                                <div class="widget-head">
                                    <div style="border: 1px solid red">
                                        <a class="btn btn-default" href="http://10.200.0.64:8012/onlinePreview?url=http://10.200.0.64:8012/demo/2018橙色起点选课.xlsx" target="_blank">预览</a>
                                    </div>
                                    <div style="border: 1px solid red">
                                        <a class="btn btn-default" href="${ctx}/selectUserBase/admList" target="mainFrame">预览</a>
                                    </div>
                                    <div class="pull-left">Chats</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>

                                <div class="widget-content">
                                    <!-- Widget content -->
                                    <div class="padd chat-content-container" style="min-height: 400px;">

                                        <ul class="chats" id="message-list">



                                        </ul>

                                    </div>
                                    <!-- Widget footer -->
                                    <div class="widget-foot">

                                        <form class="form-inline">
                                            <div class="form-group">
                                                <input id="nickname" type="hidden" value="${sessionScope.get('sessionUser').userName}"/>
                                                <input type="text" id="inputMsg" class="form-control" size="100" placeholder="Type your message here...">
                                            </div>
                                            <button type="submit" id="send" class="btn btn-default">Send</button>
                                        </form>


                                    </div>
                                </div>


                            </div>
                        </div>



    <!-- Matter ends -->

</div>

<!-- Mainbar ends -->
<div class="clearfix"></div>


<!--[if (gte IE 9)|!(IE)]><!-->
<script src="resources/assets/js/jquery.min.js"></script>
<!--<![endif]-->
<!--[if lte IE 8 ]>
<!--<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>-->
<![endif]-->

<!-- umeditor js -->
<%--<script charset="utf-8" src="resources/umeditor/umeditor.config.js"></script>--%>
<%--<script charset="utf-8" src="resources/umeditor/umeditor.min.js"></script>--%>
<%--<script src="resources/umeditor/lang/zh-cn/zh-cn.js"></script>--%>

<script>
    $(function() {
        var from = ${sessionScope.get('sessionUser').id};
        var to = 1;
        // 初始化消息输入框
        var um = $("#inputMsg");

        // 新建WebSocket对象，最后的/websocket对应服务器端的@ServerEndpoint("/websocket")
        var socket = new WebSocket('ws://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/webSocket/${sessionScope.get('sessionUser').id}');





        // 处理服务器端发送的数据
        socket.onmessage = function(event) {
            addMessage(event.data);
        };






        // 点击Send按钮时的操作
        $('#send').on('click', function() {
            console.log("点击发送按钮");
            var nickname = $("#nickname").val();
            // 发送消息
            socket.send(JSON.stringify({
                content : um.val(),
                from:from,
                to:to,
                nickname : nickname
            }));
            // 清空消息输入框
            um.val('');
            // 消息输入框获取焦点
            um.focus();
        });


        // 把消息添加到聊天内容中
        function addMessage(message) {
            message = JSON.parse(message);
            console.log(message);
            if('系统消息' == message.nickname){
                message.nickname = '<font color="RED">' + message.nickname + '</font>';
            }

            var messageItem = '<li class="'
                + (message.isSelf ? 'by-other' : 'by-me')
                + '"><div class="'
                + (message.isSelf ? 'avatar pull-right' : 'avatar pull-left')
                +'"><img src="resources/img/'
                + (message.isSelf ? 'user.jpg' : 'user.jpg')
                +'"/></div><div class="chat-content"><div class="chat-meta">'
                + (message.isSelf ? message.date +'<span class="' : message.nickname +'<span class="')
                + (message.isSelf ? 'pull-left' : 'pull-right')
                + (message.isSelf ? '">'+message.nickname+'</span></div>' : '">'+message.date+'</span></div>')
                + message.content
                + '<div class="clearfix"></div></div></li> ';
            console.log(messageItem)
//            $(messageItem).appendTo('#message-list');
            $("#message-list").append(messageItem);
        }

    });




</script>


    <%--<div class="mainbar">--%>

        <%--<!-- Page heading -->--%>
        <%--<div class="page-head">--%>
            <%--<h2 class="pull-left"><i class="icon-home"></i> 首页</h2>--%>

            <%--<!-- Breadcrumb -->--%>
            <%--<div class="bread-crumb pull-right">--%>
                <%--<a href="#"><i class="icon-home"></i> 首页</a>--%>
                <%--<!-- Divider -->--%>
                <%--<span class="divider">/</span>--%>
                <%--<a href="#" class="bread-current">控制台</a>--%>
            <%--</div>--%>

            <%--<div class="clearfix"></div>--%>

        <%--</div>--%>
        <%--<!-- Page heading ends -->--%>


        <%--<!-- Matter -->--%>

        <%--<div class="matter">--%>
            <%--<div class="container">--%>

                <%--<!-- 搜索页 ================================================== -->--%>
                <%--<div class="row small">--%>
                    <%--<form class="navbar-form center" role="search">--%>
                        <%--<div class="form-group">--%>
                            <%--<input type="text" class="form-control" placeholder="学号/姓名">--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--<input type="text" class="form-control" placeholder="学号/姓名">--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--<input type="text" class="form-control" placeholder="学号/姓名">--%>
                        <%--</div>--%>
                        <%--<button type="submit" class="btn btn-default">搜索</button>--%>
                        <%--<button type="button" class="btn btn-info pull-left"><i class="icon-remove"></i>批量删除</button>--%>
                    <%--</form>--%>
                <%--</div>--%>
                <%--<!-- Table -->--%>
                <%--<div class="row">--%>

                    <%--<div class="col-md-12">--%>

                        <%--<div class="widget">--%>

                            <%--<div class="widget-head">--%>
                                <%--<div class="pull-left">用户列表</div>--%>
                                <%--<div class="widget-icons pull-right">--%>
                                    <%--<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>--%>
                                    <%--<a href="#" class="wclose"><i class="icon-remove"></i></a>--%>
                                <%--</div>--%>
                                <%--<div class="clearfix"></div>--%>
                            <%--</div>--%>

                            <%--<div class="widget-content ">--%>

                                <%--<table class="table table-striped table-bordered table-hover">--%>
                                    <%--<thead>--%>
                                    <%--<tr>--%>
                                        <%--<th class=" text-center"><input type="checkbox" id="selectAll"></th>--%>
                                        <%--<th>序号</th>--%>
                                        <%--<th>姓名</th>--%>
                                        <%--<th>邮箱</th>--%>
                                        <%--<th>电话</th>--%>
                                        <%--<th>qq</th>--%>
                                        <%--<th>状态</th>--%>
                                        <%--<th>操作</th>--%>
                                    <%--</tr>--%>
                                    <%--</thead>--%>
                                    <%--<tbody>--%>
                                    <%--<c:forEach var="user" items="${requestScope.get('userList')}" varStatus="index">--%>
                                        <%--<tr>--%>
                                            <%--<td  class=" text-center"><input type="checkbox" value="${user.id}" /></td>--%>
                                            <%--<td>${index.count}</td>--%>
                                            <%--<td>${user.userName}</td>--%>
                                            <%--<td>${user.userMail}</td>--%>
                                            <%--<td>${user.userPhone}</td>--%>
                                            <%--<td>${user.gmtCreate}</td>--%>
                                            <%--<td>--%>
                                                <%--<c:set var="status" value="${user.userStatus}"/>--%>
                                                <%--<c:choose>--%>
                                                    <%--<c:when test="${status eq 1}">--%>
                                                        <%--<span class="label label-success">启用</span>--%>
                                                    <%--</c:when>--%>
                                                    <%--<c:otherwise>--%>
                                                        <%--<span class="label label-danger">禁用</span>--%>
                                                    <%--</c:otherwise>--%>
                                                <%--</c:choose>--%>
                                            <%--</td>--%>
                                            <%--<td>--%>
                                                <%--<c:set var="status" value="${user.userStatus}"/>--%>
                                                <%--<c:choose>--%>
                                                    <%--<c:when test="${status eq 0}">--%>
                                                        <%--<button class="btn btn-xs btn-success"><i class="icon-ok"></i>启用</button>--%>
                                                    <%--</c:when>--%>
                                                    <%--<c:otherwise>--%>
                                                        <%--<button class="btn btn-xs btn-danger"><i class="icon-remove"></i>禁用</button>--%>
                                                    <%--</c:otherwise>--%>
                                                <%--</c:choose>--%>
                                                <%--<button class="btn btn-xs btn-warning"><i class="icon-pencil"></i>编辑</button>--%>
                                                <%--<button class="btn btn-xs btn-danger"><i class="icon-remove">删除</i>--%>
                                                <%--</button>--%>

                                            <%--</td>--%>
                                        <%--</tr>--%>
                                    <%--</c:forEach>--%>

                                    <%--</tbody>--%>
                                <%--</table>--%>

                                <%--<div class="widget-foot center">--%>
                                    <%--<ul class="pagination ">--%>
                                        <%--<li><a href="#">Prev</a></li>--%>
                                        <%--<li><a href="#">${page.current}</a></li>--%>
                                        <%--<li><a href="#">${page.pages}</a></li>--%>
                                        <%--<li><a href="#">${page.current+2}</a></li>--%>
                                        <%--<li><a href="#">4</a></li>--%>
                                        <%--<li><a href="#">Next</a></li>--%>
                                    <%--</ul>--%>

                                    <%--<div class="clearfix"></div>--%>

                                <%--</div>--%>

                            <%--</div>--%>

                        <%--</div>--%>

                    <%--</div>--%>

                <%--</div>--%>

            <%--</div>--%>
        <%--</div>--%>

        <%--<!-- Matter ends -->--%>
    <%--</div>--%>


    <%--<div class="clearfix"></div>--%>

    <%--<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>--%>
                <%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>
</body>
</html>