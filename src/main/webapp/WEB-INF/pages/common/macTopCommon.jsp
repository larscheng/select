<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!-- HTML5 Support for IE -->
<!--[if lt IE 9]>
<!--<script src="/resources/js/html5shim.js"></script>-->
<![endif]-->

<!-- Stylesheets -->
<link href="${ctx}/resources/style/bootstrap.css" rel="stylesheet">
<!-- Font awesome icon -->
<link rel="stylesheet" href="${ctx}/resources/style/font-awesome.css">
<!-- jQuery UI -->
<%--<link rel="stylesheet" href="${ctx}/resources/style/jquery-ui.css">--%>
<!-- Calendar -->
<link rel="stylesheet" href="${ctx}/resources/style/fullcalendar.css">
<!-- prettyPhoto -->
<link rel="stylesheet" href="${ctx}/resources/style/prettyPhoto.css">
<!-- Star rating -->
<%--<link rel="stylesheet" href="${ctx}/resources/style/rateit.css">--%>
<!-- Date picker -->
<%--<link rel="stylesheet" href="${ctx}/resources/style/bootstrap-datetimepicker.min.css">--%>

<link href="${ctx}/resources/jquery/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="${ctx}/resources/style/bootstrap.min.css">
<!-- CLEditor -->
<link rel="stylesheet" href="${ctx}/resources/style/jquery.cleditor.css">


<!-- Uniform -->
<%--<link rel="stylesheet" href="${ctx}/resources/style/uniform.default.css">--%>


<!-- Bootstrap toggle -->
<%--<link rel="stylesheet" href="${ctx}/resources/style/bootstrap-switch.css">--%>
<!-- Main stylesheet -->
<link href="${ctx}/resources/style/style.css" rel="stylesheet">
<link href="${ctx}/resources/beAlert/BeAlert.css" rel="stylesheet">
<!-- Widgets stylesheet -->
<%--<link href="${ctx}/resources/style/widgets.css" rel="stylesheet">--%>
<link rel="stylesheet" href="${ctx}/resources/style/bootstrapValidator.css"/>



<script>
    function toIndex() {
        window.location.href = '${ctx}/';
    }
</script>