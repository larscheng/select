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
    <title>Mac风格响应式后台管理模版演示 - 源码之家</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="">
    <%@include file="/WEB-INF/pages/common/macTopCommon.jsp" %>
</head>

<body>



    <div class="mainbar">

        <!-- Page heading -->
        <div class="page-head">
            <h2 class="pull-left"><i class="icon-home"></i> 系别管理</h2>

            <!-- Breadcrumb -->
            <div class="bread-crumb pull-right">
                <a href="#"><i class="icon-home"></i> 系别管理</a>
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
                                <div class="pull-left">系别列表</div>
                                <div class="widget-icons pull-right">
                                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                </div>
                                <div class="row navbar-form " style="position: absolute; top: -5px; right: 50px">
                                        <button type="button" style="margin-right: 10px"  class="btn btn-info pull-left" onclick="depDeleteAll()"><i class="icon-remove"> </i>批量删除</button>
                                        <button type="button" style="margin-right: 10px" onclick="window.location.href='/selectDepartment/depInitAdd';" class="btn btn-success pull-left"><i class="icon-edit"> </i>添加系别</button>
                                </div>
                                <div class="clearfix"></div>
                            </div>

                            <div class="widget-content">

                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th class=" text-center"><input type="checkbox" id="selectAll"></th>
                                        <th>序号</th>
                                        <th>系别名称</th>
                                        <th>系别状态</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="dep" items="${depList}" varStatus="index">
                                        <tr>
                                            <td class=" text-center">
                                                <input name="ids" type="checkbox" value="${dep.id}"/>
                                            </td>
                                            <td>${index.count}</td>
                                            <td>${dep.depName}</td>
                                            <td>
                                                <c:set var="status" value="${dep.depStatus}"/>
                                                <c:choose>
                                                    <c:when test="${status eq 1}">
                                                        <span class="label label-success">启用</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="label label-danger">禁用</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td><fmt:formatDate value="${dep.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

                                            <td>
                                                <c:choose>
                                                    <c:when test="${status eq 0}">
                                                        <button class="btn btn-xs btn-success" onclick="depAble('${dep.id}')"><i class="icon-ok"></i>启用</button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button class="btn btn-xs btn-danger"  onclick="depDisable('${dep.id}')"><i class="icon-remove"></i>禁用</button>
                                                    </c:otherwise>
                                                </c:choose>
                                                <button class="btn btn-xs btn-warning" onclick="window.location.href='/selectDepartment/depInitUpdate?id=${dep.id}';"><i class="icon-pencil">编辑</i>
                                                </button>
                                                <button class="btn btn-xs btn-danger" onclick="depDelete('${dep.id}')"><i class="icon-remove">删除</i>
                                                </button>

                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>

                                <div class="widget-foot center">
                                    <ul class="pagination ">
                                        <c:if test="${page.current-1 eq 0}">
                                            <li><a href="#" class="btn  disabled">上一页</a></li>
                                        </c:if>
                                        <c:if test="${page.current-1 > 0}">
                                            <li><a class="disabled" href="${ctx}/selectDepartment/depList?page=${page.current-1}">上一页</a></li>
                                            <li><a href="${ctx}/selectDepartment/depList?page=${page.current-1}">${page.current-1}</a></li>
                                        </c:if>


                                        <li><a href="${ctx}/selectDepartment/depList?page=${page.current}">${page.current}</a></li>

                                        <c:if test="${page.current+1 <= page.pages}">
                                            <li><a href="${ctx}/selectDepartment/depList?page=${page.current+1}">${page.current+1}</a></li>
                                        </c:if>
                                        <c:if test="${page.current+2 <= page.pages}">
                                            <li><a href="${ctx}/selectDepartment/depList?page=${page.current+2}">${page.current+2}</a></li>
                                        </c:if>
                                        <c:if test="${page.current+1 <= page.pages}">
                                            <li><a href="${ctx}/selectDepartment/depList?page=${page.current+1}">下一页</a></li>
                                        </c:if>
                                        <c:if test="${page.current+1 > page.pages}">
                                            <li><a class="btn  disabled" href="#">下一页</a></li>
                                        </c:if>
                                        <li><label style="line-height: 35px">共 ${requestScope.page.total} 条记录</label></li>
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
        function depDeleteAll(){
            var arrayid = new Array();
            $('input[name="ids"]:checked').each(function(){arrayid.push($(this).val());});
            if(arrayid.length==0){
                alert("无实例选中");
                event.preventDefault(); // 兼容标准浏览器
                window.event.returnValue = false; // 兼容IE6~8
            }else{
                confirm("确认删除?","", function (isConfirm) {
                    if (isConfirm) {
                        //after click the confirm

                        $.ajax({
                            type:"POST",
                            url:"/selectDepartment/depDeleteAll",
                            data: { "selectedIDs": arrayid },
                            dataType:"json",
                            traditional: true,
                            success:function(msg){
                                if("OK"!=msg){
                                    alert(msg);
                                }else{
                                    alert("删除成功","", function () {
                                        location.href="/selectDepartment/depList";
                                    }, {type: 'success', confirmButtonText: '好的'});
                                }
                            },
                            error:function(e){
                                alert("后台异常！");
                            }
                        });
                    }
                }, {confirmButtonText: '删除', cancelButtonText: '取消', width: 400});
            }



        }

        function depDisable(id){
            confirm("确认禁用?","", function (isConfirm) {
                if (isConfirm) {
                    //after click the confirm

                    $.ajax({
                        type:"POST",
                        url:"/selectDepartment/depDisable",
                        data:{"id":id,"depStatus":0},
                        dataType:"json",
                        success:function(msg){
                            if ("OK"!=msg){
                                alert(msg);
                            }else {
                                alert("禁用成功", "", function () {
                                    location.href="/selectDepartment/depList";
                                }, {type: 'success', confirmButtonText: '好的'});
                            }
                        },
                        error:function(e){
                            alert("系统异常！");
                        }
                    });
                }
            }, {confirmButtonText: '禁用', cancelButtonText: '取消', width: 400});
        }

        function depAble(id){

            confirm("确认启用?","", function (isConfirm) {
                if (isConfirm) {
                    $.ajax({
                        type:"POST",
                        url:"/selectDepartment/depDisable",
                        data:{"id":id,"depStatus":1},
                        dataType:"json",
                        success:function(msg){
                            if ("OK"!=msg){
                                alert(msg);
                            }else {
                                alert("启用成功", "", function () {
                                    location.href="/selectDepartment/depList";
                                }, {type: 'success', confirmButtonText: '好的'});
                            }
                        },
                        error:function(e){
                            alert("系统异常");
                        }
                    });
                }
            }, {confirmButtonText: '启用', cancelButtonText: '取消', width: 400});
        }


        function depDelete(id){
            confirm("确认删除?","", function (isConfirm) {
                if (isConfirm) {
                    $.ajax({
                        type:"POST",
                        url:"/selectDepartment/depDelete",
                        data:{"id":id},
                        dataType:"json",
                        success:function(msg){
                            if("OK"!=msg){
                                alert(msg);
                            }else{
                                alert("删除成功","", function () {
                                    location.href="/selectDepartment/depList";
                                }, {type: 'success', confirmButtonText: '好的'});
                            }
                        },
                        error:function(e){
                            alert("系统异常！");
                        }
                    })
                }
            }, {confirmButtonText: '删除', cancelButtonText: '取消', width: 400});





        }
    </script>
<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>
</body>
</html>