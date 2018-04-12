<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>




<script type="text/javascript" src="${ctx}/resources/jquery/jquery.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/resources/jquery/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<%--<script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>--%>
<%--<script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>--%>
<script src="${ctx}/resources/js/fullcalendar.min.js"></script> <!-- Full Google Calendar - Calendar -->
<script src="${ctx}/resources/js/jquery.prettyPhoto.js"></script> <!-- prettyPhoto -->


<script src="${ctx}/resources/js/jquery.cleditor.min.js"></script> <!-- CLEditor -->

<script src="${ctx}/resources/beAlert/BeAlert.js"></script> <!-- pop -->

<script type="text/javascript" src="${ctx}/resources/js/bootstrapValidator.js"></script>


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

    $('.form_datetime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        format: 'yyyy-mm-dd hh:ii:ss', //日期的格式
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
    });
    function aaa() {
        $(".datetimepicker").height(280);
    }


    function getLocalTime(nS) {
        var date = new Date(nS);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
    }
    /* Curve chart ends */
</script>

<script src="${ctx}/resources/js/custom.js"></script> <!-- Custom codes -->
<script>
    function toIndex() {
        window.location.href = '${ctx}/';
    }
</script>