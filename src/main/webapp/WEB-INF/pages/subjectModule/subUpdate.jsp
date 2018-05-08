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
            <a href="${ctx}/index" target="_top"><i class="icon-home"></i> 论文题目管理</a>
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
                                <form class="form-horizontal" role="form" id="updateForm">

                                    <div class="form-group ">

                                        <label class="col-lg-1 control-label">面向系别</label>
                                        <div class="col-lg-2">
                                            <select name="forDepId"  class="form-control" id="forDepId">
                                                <option value="" selected>---请选择---</option>
                                                <c:forEach var="dep" items="${requestScope.depList}">

                                                    <c:choose>
                                                        <c:when test="${requestScope.sub.forDepId eq dep.id }">
                                                            <option selected value="${dep.id}">${dep.depName}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${dep.id}">${dep.depName}</option>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>
                                            </select>
                                        </div>


                                        <label class="col-lg-1 control-label">面向届别</label>
                                        <div class="col-lg-2" >
                                            <select name="subYear" class="form-control">
                                                <option value="" selected>---请选择---</option>
                                                <c:forEach var="year" items="${requestScope.yearList}">

                                                    <c:choose>
                                                        <c:when test="${requestScope.sub.subYear eq year.stuYear }">
                                                            <option selected value="${year.stuYear}">${year.stuYear}级</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${year.stuYear}">${year.stuYear}级</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <label class="col-lg-1 control-label">题目类型</label>
                                        <div class="col-lg-2" >
                                            <select name="subType" class="form-control">
                                                <option value="" selected>---请选择---</option>
                                                <c:forEach var="type" items="${requestScope.subType}">
                                                    <c:choose>
                                                        <c:when test="${requestScope.sub.subType eq type.key }">
                                                            <option selected value="${type.key}">${type.value}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${type.key}">${type.value}</option>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>
                                            </select>
                                        </div>


                                    </div>


                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">题目名称</label>
                                        <div class="col-lg-8">
                                            <input type="hidden" class="form-control" id="id" name="id" value="${requestScope.sub.id}" >
                                            <input type="text" disabled class="form-control" id="subName" name="subName"  value="${requestScope.sub.subName}">
                                        </div>
                                    </div>

                                    <%--<div class="form-group">--%>
                                        <%--<label class="col-lg-4 control-label">题目附件</label>--%>
                                        <%--<div class="col-lg-6">--%>
                                            <%--<input type="file" class="form-control" name="subFile">--%>

                                        <%--</div>--%>
                                        <%--<div class="col-lg-2 ">支持pdf、office文件、压缩包</div>--%>
                                    <%--</div>--%>

                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">题目内容</label>
                                        <div class="col-lg-8">
                                            <textarea class="form-control" rows="4" name="subContent" >${requestScope.sub.subContent}</textarea>
                                        </div>
                                    </div>

                                    <hr />
                                    <div class="form-group">
                                        <div class="col-lg-offset-1 col-lg-9">
                                            <button type="button" id="updateSubmit" class="btn btn-success">提交</button>
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

    $(document).ready(function() {
        /**
         * 下面是进行插件初始化
         * 你只需传入相应的键值对
         * */
        $('#updateForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {/*验证*/
                forDepId: {/*键名username和input name值对应*/
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '系别不能为空'
                        }
                    }
                },
                subYear: {/*键名username和input name值对应*/
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '届别不能为空'
                        }
                    }
                },
                subType: {/*键名username和input name值对应*/
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '题目类型不能为空'
                        }
                    }
                }


            }
        });
    });

    $(function(){

        $("#updateSubmit").click(function(){
            //获取表单对象
            var bootstrapValidator = $("#updateForm").data('bootstrapValidator');
            //手动触发验证
            bootstrapValidator.validate();

            if(bootstrapValidator.isValid()){
            <%--var teaId = ${sessionScope.sessionUser.id}--%>
            <%--$("#teaId").val(teaId);--%>
            var formData = new FormData($( "#updateForm" )[0]);  // 要求使用的html对象
            $.ajax({
                type: "post",
                url: "${ctx}/selectSubject/subUpdate",
//                data: $("#addForm").serialize(),
                data: formData,
                async: true,
                // 下面三个参数要指定，如果不指定，会报一个JQuery的错误
                cache: false,
                contentType: false,
                processData: false,
                dataType:"json",
                success:function(msg){
                    if("OK"!=msg){
                        alert(" 😅 "+msg);
                    }else {
                        alert(" 😎 修改成功","",function () {
                            location.href="${ctx}/selectSubject/subList"
                        },{type:"success",confirmButtonText:"好的"});
                    }

                },
                error: function(e) {
                    alert(" 😥 系统异常，请与我们的工程师小哥哥联系！");
                }
            });
            }
        });
    });


    /* Curve chart ends */
</script>

</body>
</html>