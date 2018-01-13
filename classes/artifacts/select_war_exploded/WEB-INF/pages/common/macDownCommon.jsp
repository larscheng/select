<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!-- JS -->
<script src="${ctx}/resources/js/jquery.js"></script> <!-- jQuery -->
<script src="${ctx}/resources/js/bootstrap.js"></script> <!-- Bootstrap -->
<script src="${ctx}/resources/js/jquery-ui-1.9.2.custom.min.js"></script> <!-- jQuery UI -->
<script src="${ctx}/resources/js/fullcalendar.min.js"></script> <!-- Full Google Calendar - Calendar -->
<script src="${ctx}/resources/js/jquery.rateit.min.js"></script> <!-- RateIt - Star rating -->
<script src="${ctx}/resources/js/jquery.prettyPhoto.js"></script> <!-- prettyPhoto -->

<!-- jQuery Flot -->
<script src="${ctx}/resources/js/excanvas.min.js"></script>
<script src="${ctx}/resources/js/jquery.flot.js"></script>
<script src="${ctx}/resources/js/jquery.flot.resize.js"></script>
<script src="${ctx}/resources/js/jquery.flot.pie.js"></script>
<script src="${ctx}/resources/js/jquery.flot.stack.js"></script>

<!-- jQuery Notification - Noty -->
<script src="${ctx}/resources/js/jquery.noty.js"></script> <!-- jQuery Notify -->
<script src="${ctx}/resources/js/themes/default.js"></script> <!-- jQuery Notify -->
<script src="${ctx}/resources/js/layouts/bottom.js"></script> <!-- jQuery Notify -->
<script src="${ctx}/resources/js/layouts/topRight.js"></script> <!-- jQuery Notify -->
<script src="${ctx}/resources/js/layouts/top.js"></script> <!-- jQuery Notify -->
<!-- jQuery Notification ends -->

<script src="${ctx}/resources/js/sparklines.js"></script> <!-- Sparklines -->
<script src="${ctx}/resources/js/jquery.cleditor.min.js"></script> <!-- CLEditor -->
<script src="${ctx}/resources/js/bootstrap-datetimepicker.min.js"></script> <!-- Date picker -->
<%--<script src="${ctx}/resources/js/jquery.uniform.min.js"></script> <!-- jQuery Uniform -->--%>
<script src="${ctx}/resources/js/bootstrap-switch.min.js"></script> <!-- Bootstrap Toggle -->
<script src="${ctx}/resources/js/filter.js"></script> <!-- Filter for support page -->
<script src="${ctx}/resources/js/custom.js"></script> <!-- Custom codes -->
<script src="${ctx}/resources/js/charts.js"></script> <!-- Charts & Graphs -->

<!-- Script for this page -->

<script type="text/javascript">


    $(function(){
        var checkid = [];
        $("#selectAll").bind("click",function(){
            if($(this).prop("checked")){
                $("input[type='checkbox']").not(this).prop("checked",true);
            }else{
                $("input[type='checkbox']").not(this).prop("checked",false);
            }
        });


    });


    /* Curve chart ends */
</script>


<script>
    function toIndex() {
        window.location.href = '${ctx}/';
    }
</script>