<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>后台管理</title>
    <link rel="alternate icon" type="image/x-icon" href="${ctx}/resources/favicon.ico">
</head>
<frameset rows="66,*,40" cols="*" frameborder="yes" border="0" framespacing="0" scrolling="yes" >

    <frame  src="${ctx}/top.jsp"  name="topFrame" scrolling="No" noresize="noresize"
            id="topFrame" title="topFrame" />
    <frameset cols="230,*" frameborder="No" border="0" framespacing="0" scrolling="No">
        <frame src="${ctx}/sider.jsp" name="leftFrame" scrolling="No" noresize="noresize"
               id="leftFrame" title="leftFrame" />
        <frame src="${ctx}/test.jsp" name="mainFrame" border="0" noresize="noresize"
               id="mainFrame" title="mainFrame" />
    </frameset>
    <frame  src="${ctx}/footer.jsp"  name="footFrame" scrolling="No"  border="0" noresize="noresize"
            id="footFrame" title="footFrame" />

</frameset>
<body onunload="window.location='${ctx}/logout'">

</body>
</html>

<script type="text/javascript">

</script>