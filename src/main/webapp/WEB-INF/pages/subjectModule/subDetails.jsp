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
            <span class="page-meta">题目详情</span>
        </h2>


        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
            <a href="index.html"><i class="icon-home"></i> 论文信息管理</a>
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
                            <div class="pull-left">详情</div>
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
                                <form class="form-horizontal" role="form" id="updateForm">

                                    <%--<div class="form-group ">--%>

                                        <%--<label class="col-lg-1 control-label">总分</label>--%>

                                        <%--<div class="col-lg-1 panel panel-default pdl" >--%>
                                            <%--${requestScope.sub.finalTotalScore}--%>
                                        <%--</div>--%>



                                        <%--<label class="col-lg-1 control-label">指导老师打分</label>--%>

                                        <%--<div class="col-lg-1 panel panel-default pdl" >--%>
                                            <%--${requestScope.sub.tutorScore}--%>
                                        <%--</div>--%>



                                        <%--<label class="col-lg-1 control-label">评阅老师打分</label>--%>

                                        <%--<div class="col-lg-2 panel panel-default pdl" >--%>
                                            <%--${requestScope.sub.judgeScore}--%>
                                        <%--</div>--%>

                                        <%--<label class="col-lg-1 control-label">答辩得分</label>--%>

                                        <%--<div class="col-lg-2 panel panel-default pdl" >--%>
                                            <%--${requestScope.sub.defenceScore}--%>
                                        <%--</div>--%>


                                    <%--</div>--%>


                                    <div class="form-group">

                                        <label class="col-lg-4 control-label">题目名称</label>

                                        <div class="col-lg-4 panel panel-default pdl" >
                                            ${requestScope.sub.subName}
                                        </div>

                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">发布教师</label>

                                        <div class="col-lg-4 panel panel-default pdl" >
                                            ${requestScope.sub.subTeaName}
                                        </div>
                                    </div>

                                        <div class="form-group">
                                            <label class="col-lg-4 control-label">题目类型</label>
                                            <div class="col-lg-4 panel panel-default pdl" >
                                                ${requestScope.sub.typeName}
                                            </div>

                                        </div>

                                        <div class="form-group">
                                            <label class="col-lg-4 control-label">审核状态</label>
                                            <div class="col-lg-4 panel panel-default pdl" >
                                                ${requestScope.sub.subState}
                                            </div>
                                        </div>

                                    <div class="form-group">

                                        <label class="col-lg-4 control-label">题目年份</label>
                                        <div class="col-lg-4 panel panel-default pdl" >
                                            ${requestScope.sub.subYear}
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">题目内容</label>

                                        <div class="col-lg-4 panel panel-default pdl" >
                                            ${requestScope.sub.subContent}
                                        </div>
                                    </div>

                                    <hr />
                                    <div class="form-group">
                                        <div class="col-lg-offset-1 col-lg-9">
                                            <button type="button" class="btn btn-info" onclick="window.history.go(-1);">返回</button>
                                            <button  type="button" class="btn  btn-success" onclick="subSuccess(${requestScope.sub.id})">通过</button>
                                            <button type="button" onclick="cleanAll()" class="btn  btn-danger" id="modal-317062" href="#modal-container-317062" role="button"  data-toggle="modal">不通过</button>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="container">
                                            <div class="row clearfix">
                                                <div class="col-md-6 column">
                                                    <div class="modal fade" id="modal-container-317062" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog" style="left: 3px">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                    <h4 class="modal-title" id="myModalLabel">
                                                                        审核失败原因：
                                                                    </h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                   <textarea class="form-control" rows="4" id="reason" placeholder="原因"></textarea>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                                    <button type="button" class="btn btn-primary" onclick="subFail(${requestScope.sub.id})">确认</button>
                                                                </div>
                                                            </div>

                                                        </div>

                                                    </div>

                                                </div>
                                                <div class="col-md-6 column">
                                                </div>
                                            </div>
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


    function cleanAll() {
        $("#reason").val("");
    }

    function subSuccess(id){
        confirm(" 😲 确认审核通过？","",function (isConfirm) {
            if (isConfirm){
                $.ajax({
                    type:"POST",
                    url:"/selectSubject/subAudited",
                    data:{"id":id,"admAuditState":2,"admAuditId":${sessionScope.user.id}},
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else {
                            alert(" 😋 审核通过","",function () {
                                location.href="/selectSubject/unSubList";
                            },{type:"success",confirmButtonText:"好的"});
                        }
                    },
                    error:function(e){
                        alert("😥 系统异常，请与我们的程序员小哥哥联系！");
                    }
                });
            }
        });
    }


    function subFail(id){

        confirm(" 😲 确认不通过？","",function (isConfirm) {
            if (isConfirm){
                $.ajax({
                    type:"POST",
                    url:"/selectSubject/subAudited",
                    data:{"id":id,"admAuditState":1,"admAuditId":${sessionScope.user.id},"admAuditContent":$("#reason").val()},
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else{
                            alert(" 😋 审核完成！","",function () {
                                location.href="/selectSubject/unSubList";
                            },{type:"success",confirmButtonText:"好的"});
                        }
                    },
                    error:function(e){
                        alert("😥 系统异常，请与我们的工程师联系！");
                    }
                });
            }
        });

    }



    $(function(){



        $("#updateSubmit").click(function(){

            $.ajax({
                type: "post",
                url: "/selectUserBase/teaUpdate",
                data: $("#updateForm").serialize(),
                dataType:"json",
                success:function(msg){
                    if("OK"!=msg){
                        alert(" 😅 "+msg);
                    }else {
                        alert(" 😎 修改成功","",function () {
                            location.href="/selectUserBase/teaList";
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