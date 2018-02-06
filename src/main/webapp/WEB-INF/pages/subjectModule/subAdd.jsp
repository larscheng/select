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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="">
    <%@include file="/WEB-INF/pages/common/macTopCommon.jsp" %>
</head>

<body>



<!-- Main bar -->
<div class="mainbar">

    <!-- Page heading -->
    <div class="page-head">
        <!-- Page heading -->
        <h2 class="pull-left">
            <!-- page meta -->
            <span class="page-meta">题目添加</span>
        </h2>


        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
            <a href="index.html"><i class="icon-home"></i> 论文题目管理</a>
            <!-- Divider -->
            <span class="divider">/</span>
            <a href="#" class="bread-current">首页</a>
        </div>

        <div class="clearfix"></div>

    </div>
    <!-- Page heading ends -->



    <!-- Matter -->

    <div class="matter">
        <div class="container">

            <div class="row">

                <div class="col-md-12">


                    <div class="widget wgreen">

                        <div class="widget-head">
                            <div class="pull-left">题目</div>
                            <div class="widget-icons pull-right">
                                <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                <a href="#" class="wclose"><i class="icon-remove"></i></a>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                        <div class="widget-content">
                            <div class="padd">
                                <hr />
                                <!-- Form starts.  -->
                                <form class="form-horizontal" role="form" id="addForm">

                                    <div class="form-group ">

                                        <label class="col-lg-1 control-label">面向系别</label>
                                        <div class="col-lg-2">
                                            <select name="forDepId"  class="form-control" id="forDepId">
                                                <option value="" selected>---请选择---</option>
                                                <c:forEach var="dep" items="${requestScope.depList}">
                                                    <option value="${dep.id}">${dep.depName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>


                                        <label class="col-lg-1 control-label">面向届别</label>
                                        <div class="col-lg-2" >
                                            <select name="subYear" class="form-control">
                                                <option value="" selected>---请选择---</option>
                                                <c:forEach var="year" items="${requestScope.yearList}">
                                                    <option value="${year.stuYear}">${year.stuYear}级</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <label class="col-lg-1 control-label">题目类型</label>
                                        <div class="col-lg-2" >
                                            <select name="subType" class="form-control">
                                                <option value="" selected>---请选择---</option>
                                                <c:forEach var="type" items="${requestScope.subType}">
                                                    <option value="${type.key}">${type.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>


                                    </div>


                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">题目名称</label>
                                        <div class="col-lg-8">
                                            <input type="hidden" class="form-control" id="teaId" name="teaId" >
                                            <input type="text" class="form-control" name="subName" placeholder="题目名称">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">题目内容</label>
                                        <div class="col-lg-8">
                                            <textarea class="form-control" rows="4" name="subContent" placeholder="题目内容"></textarea>
                                        </div>
                                    </div>

                                    <hr />
                                    <div class="form-group">
                                        <div class="col-lg-offset-1 col-lg-9">
                                            <button type="button" id="addSubmit" class="btn btn-success">提交</button>
                                            <button type="reset" class="btn btn-info">重填</button>
                                            <button type="button" class="btn btn-info" onclick="window.history.go(-1);">返回</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="widget-foot">
                            <!-- Footer goes here -->
                        </div>
                    </div>

                </div>

            </div>

        </div>
    </div>

    <!-- Matter ends -->

</div>

<!-- Mainbar ends -->
<div class="clearfix"></div>

<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>

<script type="text/javascript">

    /***
     * 根据专业查询并生成班级下拉
     */
    function initClass() {
        $.ajax({
            type: "post",
            url: "/selectUserBase/initClass",
            data:{"stuMajorName":$("#stuMajorName").val()},
            dataType:"json",
            success:function(msg){
                if (parseInt(msg)>0){
                    $("#stuClass").html(null);
                    $("#stuClass").append( "<option value='' selected>---请选择---</option>" );
                    for (var i =1 ; i<=msg ; i++){
                        $("#stuClass").append( "<option value="+i+">"+i+"班</option>" );
                    }
                }else {
                    alert(" 😥 "+msg);
                }
            },//end success
            error: function(e) {
                alert(" 😥 系统异常，请与我们的工程师联系！");
            }
        });
    }



    $(function(){

        $("#addSubmit").click(function(){
            var teaId = ${sessionScope.sessionUser.id}
            $("#teaId").val(teaId);
            $.ajax({
                type: "post",
                url: "/selectSubject/subAdd",
                data: $("#addForm").serialize(),
                dataType:"json",
                success:function(msg){
                    if("OK"!=msg){
                        alert(" 😅 "+msg);
                    }else {
                        alert(" 😎 添加成功","",function () {
                            location.href="/selectSubject/mySubList?teaId=${sessionScope.sessionUser.id}";
                        },{type:"success",confirmButtonText:"好的"});
                    }

                },
                error: function(e) {
                    alert(" 😥 系统异常，请与我们的工程师小哥哥联系！");
                }
            });
        });
    });


    /* Curve chart ends */
</script>

</body>
</html>