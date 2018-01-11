<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/7
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/pages/common/macTopCommon.jsp" %>
</head>
<body>

<!-- Sidebar -->

<c:set var="usertype" value="${sessionScope.get('user').userType}"/>
<c:choose>
    <c:when test="${usertype eq 1}">
        <%@include file="/WEB-INF/pages/common/adminSider.jsp" %>
    </c:when>
    <c:when test="${usertype eq 2}">
        <%@include file="/WEB-INF/pages/common/teacherSider.jsp" %>
    </c:when>
    <c:otherwise>
        <%@include file="/WEB-INF/pages/common/studentSider.jsp" %>
    </c:otherwise>
</c:choose>

</body>
</html>
