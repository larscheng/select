<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<!-- Stylesheets -->
<link href="${ctx}/resources/style/bootstrap.css" rel="stylesheet">

<!-- Calendar -->
<link rel="stylesheet" href="${ctx}/resources/style/fullcalendar.css">
<!-- prettyPhoto -->
<link rel="stylesheet" href="${ctx}/resources/style/prettyPhoto.css">

<link rel="stylesheet" href="${ctx}/resources/style/font-awesome.css">

<link href="${ctx}/resources/jquery/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="https://apps.bdimg.com/libs/bootstrap/3.2.0/css/bootstrap.min.css">
<%--<link rel="stylesheet" href="${ctx}/resources/style/bootstrap3-2.min.css">--%>
<!-- CLEditor -->
<link rel="stylesheet" href="${ctx}/resources/style/jquery.cleditor.css">


<!-- Main stylesheet -->
<link href="${ctx}/resources/style/style.css" rel="stylesheet">
<link href="${ctx}/resources/beAlert/BeAlert.css" rel="stylesheet">

<link rel="stylesheet" href="${ctx}/resources/style/bootstrapValidator.css"/>



<script>
    function toIndex() {
        window.location.href = '${ctx}/';
    }
</script>