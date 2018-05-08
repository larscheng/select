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
    <style>
        ul.countdown {
            list-style: none;
            /*margin: 75px 0;*/
            /*margin-left: 110px;*/
            /*margin-right: 50px;*/
            padding: 0;
            display: block;
            text-align: center;
            /*background: black*/
        }

        ul.countdown li {
            display: inline-block;
        }

        ul.countdown li span {
            font-size: 40px;
            font-weight: 300;
            line-height: 60px;
        }

        ul.countdown li.seperator {
            font-size: 40px;
            line-height: 60px;
            vertical-align: top;
        }

        ul.countdown li p {
            color: #a7abb1;
            font-size: 14px;
        }

        a {
            color: #76949F;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        .source {
            width: 405px;
            margin: 0 auto;
            background: #4f5861;
            color: #a7abb1;
            font-weight: bold;
            display: block;
            white-space: pre;
            -webkit-border-radius: 3px;
            -moz-border-radius: 3px;
            border-radius: 3px;
        }

        .btn {
            background: #f56c4c;
            margin: 40px auto;
            padding: 12px;
            display: block;
            width: 100px;
            color: white;
            text-align: center;
            text-transform: uppercase;
            font-weight: bold;
            text-decoration: none;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border-radius: 2px;
        }

        .btn:hover {
            text-decoration: none;
            opacity: .7;
        }
    </style>
</head>
<body>

<!-- Header starts -->
<header>
    <div style="background:#fafafa url(resources/img/cream.png) repeat; height: 100px">


        <!-- Logo section -->
        <div class="col-md-4">
            <!-- Logo. -->
            <div class="logo">
                <h1 class=" pull-left"><a href="#"><img src="resources/favicon.ico" width="60px" height="60px"><span
                        class="bold"></span></a></h1>
                <div class="meta" style="line-height: 60px;color: black; font-size: 23px; font-family: 华文楷体 ;"><b>毕业设计综合信息管理系统</b>
                </div>
            </div>
            <!-- Logo ends -->
        </div>

        <div class="<%--container--%>col-md-8">

            <%--<div class="row">--%>


            <ul class="countdown pull-left">
                <li><span style="font-size: 20px;vertical-align:super ;">距离<b
                        style=" font-family: 华文楷体,serif;">${sessionScope.pro.proName}</b>结束还剩：</span>
                </li>
                <li><span class="days">00</span>天
                </li>
                <li class="seperator">.</li>
                <li><span class="hours">00</span>小时
                </li>
                <li class="seperator">:</li>
                <li><span class="minutes">00</span>分钟
                </li>
                <li class="seperator">:</li>
                <li><span class="seconds">00</span>秒
                </li>
            </ul>

            <ul class="pull-right" style="list-style: none;line-height: 60px;">
                <li class="">
                    <a href="#" style=" ; font-size:15px;color: black;text-decoration: none;">
                        欢迎您 <i class="icon-user"></i> :
                        <b>${sessionScope.get("sessionUser").userName}</b>
                        <c:choose>
                            <c:when test="${sessionScope.get('sessionUser').userType eq 0}">
                                <span class="label label-success">超级管理员</span>
                            </c:when>
                            <c:when test="${sessionScope.get('sessionUser').userType eq 1}">
                                <span class="label label-success">管理员</span>
                            </c:when>
                            <c:when test="${sessionScope.get('sessionUser').userType eq 2}">
                                <span class="label label-success">老师</span>
                            </c:when>
                            <c:otherwise>
                                <span class="label label-success">同学</span>
                            </c:otherwise>
                        </c:choose>


                    </a>


                </li>

            </ul>
            <!-- Data section -->


            <%--</div>--%>
        </div>
        <div class="<%--container--%>col-sm-6">

            <%--<div class="row">--%>


            <%--<ul class="countdown pull-left">--%>
            <%--<li> <span style="font-size: 20px;vertical-align:super ;">距离<b style=" font-family: 华文楷体,serif;">${sessionScope.pro.proName}</b>结束还剩：</span>--%>
            <%--</li>--%>
            <%--<li> <span class="days">00</span>天--%>
            <%--</li>--%>
            <%--<li class="seperator">.</li>--%>
            <%--<li> <span class="hours">00</span>小时--%>
            <%--</li>--%>
            <%--<li class="seperator">:</li>--%>
            <%--<li> <span class="minutes">00</span>分钟--%>
            <%--</li>--%>
            <%--<li class="seperator">:</li>--%>
            <%--<li> <span class="seconds">00</span>秒--%>
            <%--</li>--%>
            <%--</ul>--%>

            <%--<ul class="pull-right" style="list-style: none;line-height: 60px;">--%>
            <%--<li class="">--%>
            <%--<a  href="#" style=" ; font-size:15px;color: black;text-decoration: none;">--%>
            <%--欢迎您 <i class="icon-user"></i> :--%>
            <%--<b>${sessionScope.get("sessionUser").userName}</b>--%>
            <%--<c:choose>--%>
            <%--<c:when test="${sessionScope.get('sessionUser').userType eq 0}">--%>
            <%--<span class="label label-success">超级管理员</span>--%>
            <%--</c:when>--%>
            <%--<c:when test="${sessionScope.get('sessionUser').userType eq 1}">--%>
            <%--<span class="label label-success">管理员</span>--%>
            <%--</c:when>--%>
            <%--<c:when test="${sessionScope.get('sessionUser').userType eq 2}">--%>
            <%--<span class="label label-success">老师</span>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
            <%--<span class="label label-success">同学</span>--%>
            <%--</c:otherwise>--%>
            <%--</c:choose>--%>


            <%--</a>--%>


            <%--</li>--%>

            <%--</ul>--%>
            <!-- Data section -->


            <%--</div>--%>
        </div>
    </div>
</header>

<!-- Header ends -->


<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>
<script type="text/javascript" src="${ctx}/resources/js/jquery.downCount.js"></script>
<script type="text/javascript">

    /**************************************时间格式化处理************************************/
    function dateFtt(fmt, date) { //author: meizz
        var o = {
            "M+": date.getMonth() + 1,                 //月份
            "d+": date.getDate(),                    //日
            "h+": date.getHours(),                   //小时
            "m+": date.getMinutes(),                 //分
            "s+": date.getSeconds(),                 //秒
            "q+": Math.floor((date.getMonth() + 3) / 3), //季度
            "S": date.getMilliseconds()             //毫秒
        };
        if (/(y+)/.test(fmt))
            fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

    var crtTime = new Date("${sessionScope.pro.proEndTime}");

    <%--console.log( dateFtt("yyyy/MM/dd hh:mm:ss",crtTime));--%>
    $('.countdown').downCount({
        date: dateFtt("yyyy/MM/dd hh:mm:ss", crtTime),
        offset: +10
    }, function () {
        alert('倒计时结束!');
    });
</script>

</body>
</html>
