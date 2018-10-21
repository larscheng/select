<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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



    <div class="mainbar">

        <!-- Page heading -->
        <div class="page-head">
            <h2 class="pull-left"><i class="icon-home"></i> 专业管理</h2>

            <!-- Breadcrumb -->
            <div class="bread-crumb pull-right">
                <a href="#"><i class="icon-home"></i> 专业管理</a>
                <!-- Divider -->
                <span class="divider">/</span>
                <a href="#" class="bread-current">控制台</a>
            </div>

            <div class="clearfix"></div>

        </div>
        <!-- Page heading ends -->


        <!-- Matter -->

        <div class="matter">
            <div class="container">

                <!-- 搜索页 ================================================== -->

                <!-- Table -->
                <div class="row">

                    <div class="col-md-12">

                        <div class="widget">

                            <div class="widget-head" style="position: relative">
                                <div class="pull-left">专业列表</div>
                                <div class="widget-icons pull-right">
                                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                </div>
                                <c:if test="${sessionScope.userType eq 0 || sessionScope.userType eq 1}">
                                    <div class="row navbar-form " style="position: absolute; top: -5px; right: 50px">
                                        <c:if test="${sessionScope.userType eq 0 }">
                                            <button type="button" onclick="majDeleteAll()" class="btn btn-info pull-left" style="margin-right: 10px"><i class="icon-remove"> </i>批量删除</button>
                                        </c:if>
                                        <button type="button" style="margin-right: 10px" onclick="window.location.href='${ctx}/selectMajor/majInitAdd';"  class="btn btn-success pull-left"><i class="icon-edit"> </i>添加专业</button>
                                    </div>
                                </c:if>
                                <div class="clearfix"></div>
                            </div>

                            <div class="widget-content">

                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th class="center"><input type="checkbox" id="selectAll" name="check"/></th>
                                        <th>序号</th>
                                        <th>专业名称</th>
                                        <th>专业班级数量</th>
                                        <th>所属系别</th>
                                        <th>专业状态</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:choose>
                                        <c:when test="${empty requestScope.majList }">
                                            <tr><td colspan='9' class='text-center'> 😑 暂无数据！</td></tr>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="maj" items="${requestScope.majList}" varStatus="index">
                                                <tr>
                                                    <td class="center">
                                                        <input name="ids" type="checkbox" value="${maj.id}"/>
                                                    </td>
                                                    <td>${index.count}</td>
                                                    <td>${maj.majName}</td>
                                                    <td>${maj.majClassNum}</td>
                                                    <td>${maj.depName}</td>
                                                    <td>
                                                        <c:set var="status" value="${maj.majStatus}"/>
                                                        <c:choose>
                                                            <c:when test="${status eq 1}">
                                                                <span class="label label-success">启用</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="label label-danger">禁用</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td><fmt:formatDate value="${maj.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

                                                    <td>
                                                        <c:if test="${sessionScope.userType eq 0}">
                                                            <c:choose>
                                                                <c:when test="${status eq 0}">
                                                                    <button class="btn btn-xs btn-success"  onclick="majAble('${maj.id}')"><i class="icon-ok"></i>启用</button>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <button class="btn btn-xs btn-danger"  onclick="majDisAble('${maj.id}')"><i class="icon-remove"></i>禁用</button>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <button class="btn btn-xs btn-warning" onclick="window.location.href='${ctx}/selectMajor/majInitUpdate?Id=${maj.id}';"><i class="icon-pencil">编辑</i>
                                                            </button>
                                                            <button class="btn btn-xs btn-danger" onclick="majDelete('${maj.id}')"><i class="icon-remove">删除</i>
                                                            </button>
                                                        </c:if>

                                                        <c:if test="${sessionScope.userType eq 1 && sessionScope.sessionUser.teaDepId eq maj.depId}">
                                                            <c:choose>
                                                                <c:when test="${status eq 0}">
                                                                    <button class="btn btn-xs btn-success"  onclick="majAble('${maj.id}')"><i class="icon-ok"></i>启用</button>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <button class="btn btn-xs btn-danger"  onclick="majDisAble('${maj.id}')"><i class="icon-remove"></i>禁用</button>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <button class="btn btn-xs btn-warning" onclick="window.location.href='${ctx}/selectMajor/majInitUpdate?Id=${maj.id}';"><i class="icon-pencil">编辑</i>
                                                            </button>
                                                            <button class="btn btn-xs btn-danger" onclick="majDelete('${maj.id}')"><i class="icon-remove">删除</i>
                                                            </button>
                                                        </c:if>

                                                        <button class="btn btn-xs btn-info" onclick="window.location.href='${ctx}/selectMajor/majFind?Id=${maj.id}';"><i class="icon-pencil">查看</i>
                                                        </button>


                                                    </td>
                                                </tr>
                                            </c:forEach>

                                        </c:otherwise>
                                    </c:choose>


                                    </tbody>
                                </table>

                                <div class="widget-foot center">
                                    <ul class="pagination ">
                                        <c:if test="${page.current-1 eq 0}">
                                            <li><a href="#" class="btn  disabled">上一页</a></li>
                                        </c:if>
                                        <c:if test="${page.current-1 > 0}">
                                            <li><a class="disabled" href="${ctx}/selectMajor/majList?page=${page.current-1}">上一页</a></li>
                                            <li><a href="${ctx}/selectMajor/majList?page=${page.current-1}">${page.current-1}</a></li>
                                        </c:if>


                                        <li><a href="${ctx}/selectMajor/majList?page=${page.current}">${page.current}</a></li>

                                        <c:if test="${page.current+1 <= page.pages}">
                                            <li><a href="${ctx}/selectMajor/majList?page=${page.current+1}">${page.current+1}</a></li>
                                        </c:if>
                                        <c:if test="${page.current+2 <= page.pages}">
                                            <li><a href="${ctx}/selectMajor/majList?page=${page.current+2}">${page.current+2}</a></li>
                                        </c:if>
                                        <c:if test="${page.current+1 <= page.pages}">
                                            <li><a href="${ctx}/selectMajor/majList?page=${page.current+1}">下一页</a></li>
                                        </c:if>
                                        <c:if test="${page.current+1 > page.pages}">
                                            <li><a class="btn  disabled" href="#">下一页</a></li>
                                        </c:if>

                                    </ul>


                                    <div class="clearfix"></div>

                                </div>

                            </div>

                        </div>

                    </div>

                </div>

            </div>
        </div>

        <!-- Matter ends -->

    </div>


    <div class="clearfix"></div>

    <script type="text/javascript">

        function majAble(id){
            confirm("确认启用？","",function (isConfirm) {
                if (isConfirm){
                    $.ajax({
                        type:"POST",
                        url:"${ctx}/selectMajor/majDisable",
                        data:{"id":id,"majStatus":1},
                        dataType:"json",
                        success:function(msg){
                            if("OK"!=msg){
                                alert(msg);
                            }else {
                                alert("启用成功","",function () {
                                    location.href="${ctx}/selectMajor/majList";
                                },{type:"success",confirmButtonText:"好的"});
                            }
                        },
                        error:function(e){
                            alert("系统异常！");
                        }
                    });
                }
            });

        }

        function majDisAble(id){
            confirm("确认禁用？","",function (isConfirm) {
                if (isConfirm){
                    $.ajax({
                        type:"POST",
                        url:"${ctx}/selectMajor/majDisable",
                        data:{"id":id,"majStatus":0},
                        dataType:"json",
                        success:function(msg){
                            if("OK"!=msg){
                                alert(msg);
                            }else{
                                alert("禁用成功！","",function () {
                                    location.href="${ctx}/selectMajor/majList";
                                },{type:"success",confirmButtonText:"好的"});
                            }
                        },
                        error:function(e){
                            alert("系统异常！");
                        }
                    });
                }
            });

        }

        function majDelete(id){
            confirm("确认删除吗？","",function (isconfirm) {
                if (isconfirm){
                    $.ajax({
                        type:"POST",
                        url:"${ctx}/selectMajor/majDelete",
                        data:{"id":id},
                        dataType:"json",
                        success:function(msg){
                            if("OK"!=msg){
                                alert(msg);
                            }else{
                                alert("删除成功！","",function () {
                                    location.href="${ctx}/selectMajor/majList";
                                },{type:"success",confirmButtonText:"好的"});
                            }
                        },
                        error:function(e){
                            alert("系统异常！");
                        }
                    });
                }
            })

        }

        function majDeleteAll(){
            var arrayId = new Array();
            $('input[name="ids"]:checked').each(function(){arrayId.push($(this).val());});
            if(arrayId.length==0){
                alert("无实例选中");
                event.preventDefault(); // 兼容标准浏览器
                window.event.returnValue = false; // 兼容IE6~8
            }else{
                confirm("确认删除吗？","",function (is) {
                    if (is){
                        $.ajax({
                            type:"POST",
                            url:"${ctx}/selectMajor/majDeleteAll",
                            data: { "selectedIDs": arrayId },
                            dataType:"json",
                            traditional: true,
                            success:function(msg){
                                if("OK"!=msg){
                                    alert(msg);
                                }else{
                                    alert("删除成功！","",function () {
                                        location.href="${ctx}/selectMajor/majList";
                                    },{type:"success",confirmButtonText:"好的"});
                                }

                            },
                            error:function(e){
                                alert("后台异常！");
                            }
                        });
                    }
                })

            }

        }

    </script>

<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>
</body>
</html>