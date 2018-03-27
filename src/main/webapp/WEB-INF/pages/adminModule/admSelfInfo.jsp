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
            <span class="page-meta">我的信息</span>
        </h2>


        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
            <a href="index.html"><i class="icon-home"></i> 我的信息</a>
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
                            <div class="pull-left">信息</div>
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


                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">账号</label>
                                        <div class="col-lg-8">
                                            <input type="hidden" class="form-control" name="id" value="${requestScope.user.id}" placeholder="学生账号">
                                            <input type="text" class="form-control" name="userCode" value="${requestScope.user.userCode}" placeholder="学生账号">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">姓名</label>
                                        <div class="col-lg-8">
                                            <input type="text" class="form-control" name="userName" value="${requestScope.user.userName}" placeholder="学生姓名">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">性别</label>
                                        <div class="col-lg-8">
                                            <c:if test="${requestScope.user.userSex eq 1}">
                                                <label class="radio-inline">
                                                    <input type="radio" name="userSex"  value="1" checked>男
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="userSex"   value="2">女
                                                </label>
                                            </c:if>

                                            <c:if test="${requestScope.user.userSex eq 0}">
                                                <label class="radio-inline">
                                                    <input type="radio" name="userSex"  value="1" >男
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="userSex" checked  value="2">女
                                                </label>
                                            </c:if>
                                            </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">邮箱</label>
                                        <div class="col-lg-8">
                                            <input type="email" class="form-control" name="userMail" value="${requestScope.user.userMail}" placeholder="邮箱">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">电话</label>
                                        <div class="col-lg-8">
                                            <input type="tel" class="form-control" value="${requestScope.user.userPhone}"  name="userPhone" placeholder="电话">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">qq</label>
                                        <div class="col-lg-8">
                                            <input type="text" class="form-control" value="${requestScope.user.userQq}"  name="userQq" placeholder="qq">
                                        </div>
                                    </div>


                                    <hr />
                                    <div class="form-group">
                                        <div class="col-lg-offset-1 col-lg-9">
                                            <button type="button" id="updateSubmit" class="btn btn-success">提交</button>
                                            <button type="reset" class="btn btn-info">重填</button>
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
            data:{"stuMajorId":$("#stuMajorId").val()},
            dataType:"json",
            success:function(msg){
                if (parseInt(msg)>0){
                    $("#stuClass").html(null);
                    $("#stuClass").append( "<option value='' selected>--请选择--</option>" );
                    for (var i =1 ; i<=msg ; i++){
                        $("#stuClass").append( "<option value="+i+">"+i+"班</option>" );
                    }
                }else {
//                    alert(" 😥 "+msg);
                    $("#stuClass").html(null);
                    $("#stuClass").append( "<option value='' selected>--请选择--</option>" );
                }
            },//end success
            error: function(e) {
                alert(" 😥 系统异常，请与我们的工程师联系！");
            }
        });
    }





    $(function(){

        $("#updateSubmit").click(function(){

            $.ajax({
                type: "post",
                url: "/selectUserBase/stuUpdate",
                data: $("#updateForm").serialize(),
                dataType:"json",
                success:function(msg){
                    if("OK"!=msg){
                        alert(" 😅 "+msg);
                    }else {
                        alert(" 😎 修改成功！","",function () {
                            location.href="/selectUserBase/admSelfInfo?id=${sessionScope.sessionUser.id}";
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