<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>综合示例</title>
</head>
<frameset cols="50%,*">
    <frame src="${ctx}/WEB-INF/pages/menu.jsp" scrolling="no" name="Left">
    <frame src="top.jsp" scrolling="auto" name="Main">
    <noframes>
        <body>
        <p>对不起，您的浏览器不支持“框架”！</p>
        </body>
    </noframes>
</frameset>
</html>