<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/7
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/pages/common/macTopCommon.jsp" %>
</head>
<body>
<ul class="pagination ">
    <c:if test="${page.current-1 eq 0}">
        <li><a href="#" class="btn  disabled">上一页</a></li>
    </c:if>
    <c:if test="${page.current-1 > 0}">
        <li><a class="disabled" href="${ctx}/selectUserBase/userList?page=${page.current-1}">上一页</a></li>
        <li><a href="${ctx}/selectUserBase/userList?page=${page.current-1}">${page.current-1}</a></li>
    </c:if>

    <c:if test="${page.current-1 > 0}">

    </c:if>

    <li><a href="${ctx}/selectUserBase/userList?page=${page.current}">${page.current}</a></li>

    <c:if test="${page.current+1 <= page.pages}">
        <li><a href="${ctx}/selectUserBase/userList?page=${page.current+1}">${page.current+1}</a></li>
    </c:if>
    <c:if test="${page.current+2 <= page.pages}">
        <li><a href="${ctx}/selectUserBase/userList?page=${page.current+2}">${page.current+2}</a></li>
    </c:if>
    <c:if test="${page.current+1 <= page.pages}">
        <li><a href="${ctx}/selectUserBase/userList?page=${page.current+1}">下一页</a></li>
    </c:if>
    <c:if test="${page.current+1 > page.pages}">
        <li><a class="btn  disabled" href="#">下一页</a></li>
    </c:if>

</ul>


</body>
</html>
