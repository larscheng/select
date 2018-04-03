<%@ page import="org.springframework.web.context.request.SessionScope" %>
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
    <title></title>
    <meta name="author" content="">
    <%@include file="/WEB-INF/pages/common/macTopCommon.jsp" %>
</head>

<body>



    <div class="mainbar">

        <!-- Page heading -->
        <div class="page-head">
            <h2 class="pull-left"><i class="icon-home"></i> 教师信息管理</h2>
            <!-- Breadcrumb -->
            <div class="bread-crumb pull-right">
                <a href="#"><i class="icon-home"></i> 教师信息管理</a>
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

                <!-- 搜索页 ================================================== -->
                <div class="row small">

                    <form class="navbar-form center" role="search" id="searchForm">

                        <div class="form-group " style="position: relative;margin-right: 10px">
                            <input type="text" class="form-control" id="search" name="search" placeholder="账号/姓名/邮箱/电话/qq">
                            <span onclick="search()" style="position: absolute;left: 155px;top: 6px;cursor: pointer"><i class="icon-search" ></i></span>
                        </div>

                        <div class="form-group " style="margin-right: 10px">
                            <select  class="form-control" name="userSex">
                                <option value="" selected>性别</option>
                                <option value="1">男</option>
                                <option value="2">女</option>
                            </select>
                        </div>
                        <div class="form-group " style="margin-right: 10px">
                            <select  class="form-control" name="userStatus">
                                <option value="" selected>状态</option>
                                <option value="1">启用</option>
                                <option value="0">禁用</option>
                            </select>
                        </div>
                        <div class="form-group " style="margin-right: 10px">
                            <select  class="form-control" name="teaPosition">
                                <option value="" selected>职称</option>
                                    <c:forEach var="teaPosition" items="${requestScope.teaPosition}">
                                        <option value="${teaPosition.key}">${teaPosition.value}</option>
                                    </c:forEach>
                            </select>
                        </div>
                        <div class="form-group" style="margin-right: 10px">
                            <select  class="form-control" name="teaEducation">
                                <option value="" selected>学历</option>
                                <c:forEach var="teaEducation" items="${requestScope.teaEducation}">
                                    <option value="${teaEducation.key}">${teaEducation.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group " style="margin-right: 10px">
                            <select  class="form-control" name="teaDepId">
                                <option value="" selected>所属系别</option>
                                <c:forEach var="dep" items="${requestScope.teaDepList}">
                                    <option value="${dep.id}">${dep.depName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group" style="margin-right: 10px">
                            <div class="input-group date form_datetime">
                                <input name="searchBgnTime" onclick="aaa()" class="form-control"type="text" placeholder="起始时间" value="" readonly style="width: 140px">
                                <span class="input-group-addon" onclick="aaa()"><i class="glyphicon glyphicon-remove icon-remove"></i></span>
                                <span class="input-group-addon" onclick="aaa()"><i class="glyphicon glyphicon-th icon-calendar"></i></span>
                            </div> --
                            <div class="input-group date form_datetime">
                                <input name="searchEndTime" onclick="aaa()" class="form-control"type="text" placeholder="结束时间" value="" readonly style="width: 140px">
                                <span class="input-group-addon" onclick="aaa()"><i class="glyphicon glyphicon-remove icon-remove"></i></span>
                                <span class="input-group-addon" onclick="aaa()"><i class="glyphicon glyphicon-th icon-calendar"></i></span>
                            </div>

                        </div>
                        <button type="button" id="searchSubmit" class="btn btn-default">搜索</button>





                    </form>
                </div>
                <!-- Table -->
                <div class="row">

                    <div class="col-md-12">

                        <div class="widget">

                            <div class="widget-head"  style="position: relative">
                                <div class="pull-left">教师列表</div>
                                <div class="widget-icons pull-right">
                                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                </div>
                                <c:if test="${sessionScope.sessionUser.userType eq 1|| sessionScope.sessionUser.userType eq 0 }">
                                    <div class="row navbar-form " style="position: absolute; top: -5px; right: 50px">
                                        <button type="button" onclick="teaDeleteAll()" class="btn btn-info pull-left " style="margin-right: 10px"><i class="icon-remove"></i>批量删除</button>
                                        <button type="button" class="btn btn-info pull-left " onclick="upload()"  style="margin-right: 10px"><i class="icon-upload"></i>批量导入</button>
                                        <div style="display: none">
                                            <form id="uploadForm"  >
                                                <input type="file" id="fileField" name="fileField" style="display: none" onchange="teaUpload()"/>
                                            </form>
                                        </div>
                                        <button type="button"  onclick="window.location.href='${ctx}/selectUserBase/teaInitAdd';" style="margin-right: 10px" class="btn btn-info pull-left "><i class="icon-upload"></i>教师添加</button>
                                        <button type="button"  onclick="window.location.href='${ctx}/selectUserBase/teaFileDown';" class="btn btn-default pull-left "><i class="icon-upload"></i>模板下载</button>

                                    </div>
                                </c:if>

                                <div class="clearfix"></div>
                            </div>

                            <div class="widget-content ">

                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th class=" text-center"><input type="checkbox" id="selectAll"></th>
                                        <th>序号</th>
                                        <th>姓名</th>
                                        <th>账号</th>
                                        <th>性别</th>
                                        <th>邮箱</th>
                                        <th>电话</th>
                                        <th>qq</th>
                                        <th>所属系别</th>
                                        <th>职称</th>
                                        <th>最高学历</th>
                                        <th>状态</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="items">


                                    <c:choose>
                                        <c:when test="${empty requestScope.userList }">
                                            <tr><td colspan='14' class='text-center'> 😑 暂无数据！</td></tr>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="user" items="${requestScope.userList}" varStatus="index">
                                                <tr>
                                                    <td  class=" text-center"><input type="checkbox" name="ids" value="${user.id}" /></td>
                                                    <td>${index.count}</td>
                                                    <td>${user.userName}</td>
                                                    <td>${user.userCode}</td>
                                                    <td>${user.sex}</td>
                                                    <td><a href="mailto:${user.userMail}">${user.userMail}</a></td>
                                                    <td>${user.userPhone}</td>
                                                    <td><a href="tencent://AddContact/?fromId=50&fromSubId=1&subcmd=all&uin=${user.userQq}">${user.userQq}</a></td>
                                                    <td>${user.teaDepName}</td>
                                                    <td><span class="label label-primary">${user.teaPositionZ}</span></td>
                                                    <td><span class="label label-warning">${user.teaEducationZ}</span></td>
                                                    <td>
                                                        <c:set var="status" value="${user.userStatus}"/>
                                                        <c:choose>
                                                            <c:when test="${status eq 1}">
                                                                <span class="label label-success">启用</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="label label-danger">禁用</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td><fmt:formatDate value="${user.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    <td>
                                                        <c:if test="${sessionScope.sessionUser.userType eq 1|| sessionScope.sessionUser.userType eq 0}">
                                                            <c:set var="status" value="${user.userStatus}"/>
                                                            <c:choose>
                                                                <c:when test="${status eq 0}">
                                                                    <button class="btn btn-xs btn-success" onclick="teaAble(${user.id})"><i class="icon-ok"></i>启用</button>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <button class="btn btn-xs btn-danger" onclick="teaDisAble(${user.id})"><i class="icon-remove"></i>禁用</button>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:if>

                                                        <button class="btn btn-xs btn-info" onclick="teaDetails(${user.id})"><i class="icon-pencil"></i>详情</button>
                                                        <c:if test="${sessionScope.sessionUser.userType eq 1 || sessionScope.sessionUser.userType eq 0}">
                                                            <button class="btn btn-xs btn-warning" onclick="window.location.href='${ctx}/selectUserBase/teaInitUpdate?id=${user.id}';"><i class="icon-pencil"></i>编辑</button>
                                                            <button class="btn btn-xs btn-danger" onclick="teaDelete('${user.id}')"><i class="icon-remove">删除</i></button>
                                                        </c:if>
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
                                            <li><a class="disabled" href="${ctx}/selectUserBase/teaList?page=${page.current-1}">上一页</a></li>
                                            <li><a href="${ctx}/selectUserBase/teaList?page=${page.current-1}">${page.current-1}</a></li>
                                        </c:if>


                                        <li><a href="${ctx}/selectUserBase/teaList?page=${page.current}">${page.current}</a></li>

                                        <c:if test="${page.current+1 <= page.pages}">
                                            <li><a href="${ctx}/selectUserBase/teaList?page=${page.current+1}">${page.current+1}</a></li>
                                        </c:if>
                                        <c:if test="${page.current+2 <= page.pages}">
                                            <li><a href="${ctx}/selectUserBase/teaList?page=${page.current+2}">${page.current+2}</a></li>
                                        </c:if>
                                        <c:if test="${page.current+1 <= page.pages}">
                                            <li><a href="${ctx}/selectUserBase/teaList?page=${page.current+1}">下一页</a></li>
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
<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>
<script type="text/javascript">
    sessionStorage.setItem("userType",${sessionScope.userType});
    function upload() {
        confirm("导入前请下载好导入模板！","",function (isConfirm) {
            if (isConfirm) {
                //after click the confirm
                $("#fileField").click();
            } else {
                //after click the cancel
                window.location.href='${ctx}/selectUserBase/teaFileDown';
            }
        }, {confirmButtonText: '已有模板', cancelButtonText: '下载模板', width: 400});
    }

    function teaUpload() {
        $.ajax({
            type:'POST',
            url:'${ctx}/selectUserBase/teaUpload', //你处理上传文件的服务端
            data: new FormData($('#uploadForm')[0]),
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            dataType:"json",
            success: function (msg) {//调用成功时怎么处理
                    alert("😋"+msg,"",function () {
                        location.href="${ctx}/selectUserBase/teaList";
                    },{type:"success",confirmButtonText:"好的"});
            },//end success
            error: function(e) {
                alert(" 😥 系统异常，请与我们的工程师联系！");
            }
        });
    }




    $("#search").keydown(function (e) {
        if(event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码
            search()
        }

    });
    function search() {
        $.ajax({
            type: "post",
            url: "${ctx}/selectUserBase/teaListAjax",
            data:{"search":$(" input[ name='search' ] ").val()},
            dataType:"json",
            success:function(objects){
                initTeaPage(objects);
            },//end success
            error: function(e) {
                alert(" 😥 系统异常，请与我们的工程师联系！");
            }
        });
    }

    function pageSearch(page) {
        $.ajax({
            type: "post",
            url: "${ctx}/selectUserBase/teaListAjax",
            data:{"page":page,
                "search":$(" input[ name='search' ] ").val(),
                "userSex":$(" select[ name='userSex' ] ").val(),
                "userStatus":$(" select[ name='userStatus' ] ").val(),
                "teaPosition":$(" select[ name='teaPosition' ] ").val(),
                "teaEducation":$(" select[ name='teaEducation' ] ").val(),
                "teaDepId":$(" select[ name='teaDepId' ] ").val()
            },
            dataType:"json",
            success:function(objects){
                initTeaPage(objects);
            },//end success
            error: function(e) {
                alert(" 😥 系统异常，请与我们的工程师联系！");
            }
        });
    }

    $("#searchSubmit").click(function(){
        $.ajax({
            type: "post",
            url: "${ctx}/selectUserBase/teaListAjax",
            data: $("#searchForm").serialize(),
            dataType:"json",
            success:function(objects){
                initTeaPage(objects);
            },
            error: function(e) {
                alert(" 😥 系统异常，请与我们的工程师联系！");
            }
        });
    });



    function teaAble(id){
        confirm(" 😲 确认启用？","",function (isConfirm) {
            if (isConfirm){
                $.ajax({
                    type:"POST",
                    url:"${ctx}/selectUserBase/teaAble",
                    data:{"id":id,"userStatus":1},
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else {
                            alert(" 😋 启用成功","",function () {
                                location.href="${ctx}/selectUserBase/teaList";
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


    function teaDisAble(id){
        confirm(" 😲 确认禁用？","",function (isConfirm) {
            if (isConfirm){
                $.ajax({
                    type:"POST",
                    url:"${ctx}/selectUserBase/teaAble",
                    data:{"id":id,"userStatus":0},
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else{
                            alert(" 😋 禁用成功！","",function () {
                                location.href="${ctx}/selectUserBase/teaList";
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



    function teaDelete(id){
        confirm(" 😲 确认删除吗？","",function (isconfirm) {
            if (isconfirm){
                $.ajax({
                    type:"POST",
                    url:"${ctx}/selectUserBase/teaDelete",
                    data:{"id":id},
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else{
                            alert(" 😋 删除成功！","",function () {
                                location.href="${ctx}/selectUserBase/teaList";
                            },{type:"success",confirmButtonText:"好的"});
                        }
                    },
                    error:function(e){
                        alert("😥 系统异常，请与我们的工程师联系！");
                    }
                });
            }
        })

    }

    function teaDeleteAll(){
        var arrayId = new Array();
        $('input[name="ids"]:checked').each(function(){arrayId.push($(this).val());});
        if(arrayId.length==0){
            alert(" 😨 无实例选中");
            event.preventDefault(); // 兼容标准浏览器
            window.event.returnValue = false; // 兼容IE6~8
        }else{
            confirm(" 😲 确认删除吗？","",function (is) {
                if (is){
                    $.ajax({
                        type:"POST",
                        url:"${ctx}/selectUserBase/teaDeleteAll",
                        data: { "selectedIDs": arrayId },
                        dataType:"json",
                        traditional: true,
                        success:function(msg){
                            if("OK"!=msg){
                                alert(" 😅 "+msg);
                            }else{
                                alert(" 😋 删除成功！","",function () {
                                    location.href="${ctx}/selectUserBase/teaList";
                                },{type:"success",confirmButtonText:"好的"});
                            }

                        },
                        error:function(e){
                            alert("😥 系统异常，请与我们的工程师联系！");
                        }
                    });
                }
            })

        }

    }

    function teaDetails(id) {
        window.location.href="${ctx}/selectUserBase/teaDetails?id="+id;
    }

    function teaUpdate(id) {
        window.location.href='${ctx}/selectUserBase/teaInitUpdate?id='+id;
    }






    function initTeaPage(objects) {
        var obj =JSON.parse(objects);
        var teaList = obj.teaList;
        $("#items").html(null);
        if (jQuery.isEmptyObject(teaList)){
            $("#items").append("<tr><td colspan='14' class='text-center'> 😑 暂无数据！</td></tr>");
        }else{
            $(teaList).each(function (index) {
                var val = teaList[index];
                var time = getLocalTime(val.gmtCreate);
                var item =
                    "<tr>"
                    +"<td class='text-center'>"+"<input type='checkbox' name='ids' value='"+val.id+"'/>"+"</td>"
                    +"<td>"+(parseInt(index)+1)+"</td>"
                    +"<td>"+val.userName+"</td>"
                    +"<td>"+val.userCode+"</td>"
                    +"<td>"+val.sex+"</td>"
                    +"<td><a href='mailto:"+val.userMail+"'>"+val.userMail+"</a></td>"
                    +"<td>"+val.userPhone+"</td>"
                    +"<td><a href='tencent://AddContact/?fromId=50&fromSubId=1&subcmd=all&uin="+val.userQq+"'>"+val.userQq+"</a></td>"
                    +"<td>"+val.teaDepName+"</td>"
                    +"<td><span class='label label-primary'>"+val.teaPositionZ+"</span></td>"
                    +"<td><span class='label label-warning'>"+val.teaEducationZ+"</span></td>"

                ;
                var manType = sessionStorage.getItem("userType");
                if (parseInt(manType) == 1 ||parseInt(manType) == 0){
                    if (parseInt(val.userStatus) == 1){
                        item +=
                            "<td><span class='label label-success'>启用</span></td>"
                            +"<td>"+time+"</td>"
                            +"<td>" +
                            "<button onclick='teaDisAble("+val.id+")' class='btn btn-xs btn-danger' style='margin-right: 5px'><i class='icon-remove'></i>禁用</button>" +
                            "<button onclick='teaDetails("+val.id+")' class='btn btn-xs btn-info' style='margin-right: 5px'><i class='icon-pencil'></i>详情</button>" +
                            "<button onclick='teaUpdate("+val.id+")' class='btn btn-xs btn-warning' style='margin-right: 5px'><i class='icon-pencil'></i>编辑</button>" +
                            "<button onclick='teaDelete("+val.id+")' class='btn btn-xs btn-danger' style='margin-right: 5px'><i class='icon-remove'>删除</i></button>" +
                            "</td>"
                            +"</tr>"
                        ;
                    }else{
                        item+=
                            "<td><span class='label label-danger'>禁用</span></td>"
                            +"<td>"+time+"</td>"
                            +"<td>" +
                            "<button onclick='teaAble("+val.id+")' class='btn btn-xs btn-success' style='margin-right: 5px'><i class='icon-ok'></i>启用</button>" +
                            "<button onclick='teaDetails("+val.id+")' class='btn btn-xs btn-info' style='margin-right: 5px'><i class='icon-pencil'></i>详情</button>" +
                            "<button onclick='teaUpdate("+val.id+")' class='btn btn-xs btn-warning' style='margin-right: 5px'><i class='icon-pencil'></i>编辑</button>" +
                            "<button onclick='teaDelete("+val.id+")' class='btn btn-xs btn-danger' style='margin-right: 5px'><i class='icon-remove'>删除</i></button>" +
                            "</td>"
                            +"</tr>"
                        ;
                    }
                }else {
                    if (parseInt(val.userStatus) == 1){
                        item +=
                            "<td><span class='label label-success'>启用</span></td>"
                            +"<td>"+time+"</td>"
                            +"<td>" +
                            "<button onclick='teaDetails("+val.id+")' class='btn btn-xs btn-info' style='margin-right: 5px'><i class='icon-pencil'></i>详情</button>" +
                            "</td>"
                            +"</tr>"
                        ;
                    }else{
                        item+=
                            "<td><span class='label label-danger'>禁用</span></td>"
                            +"<td>"+time+"</td>"
                            +"<td>" +
                            "<button onclick='teaDetails("+val.id+")' class='btn btn-xs btn-info' style='margin-right: 5px'><i class='icon-pencil'></i>详情</button>" +
                            "</td>"
                            +"</tr>"
                        ;
                    }
                }


                $("#items").append(item);
            });
        }
        $(".pagination").html(null);
        var page = obj.page;
        var pageItem="";
        if ((parseInt(page.current)-1) == 0){
            pageItem+= "<li><a href='#' class='btn btn-default' disabled='disabled'>上一页</a></li>";

        }else {
            pageItem+="<li><a class='disabled' onclick='pageSearch("+(parseInt(page.current)-1)+")'>上一页</a></li>" +
                "<li><a onclick='pageSearch("+(parseInt(page.current)-1)+")'>"+(parseInt(page.current)-1)+"</a></li>";

        }
        pageItem+="<li><a onclick='pageSearch("+(parseInt(page.current))+")'>"+page.current+"</a></li>";
        if((parseInt(page.current)+1) <= parseInt(page.pages)){
            pageItem += "<li><a onclick='pageSearch("+(parseInt(page.current)+1)+")'>"+(parseInt(page.current)+1)+"</a></li>" +
                "<li><a onclick='pageSearch("+(parseInt(page.current)+1)+")'>下一页</a></li>";

        }else {
            pageItem += "<li><a class='btn  disabled' href='#'>下一页</a></li>";
        }
        if((parseInt(page.current)+2) <= parseInt(page.pages)){
            pageItem += "<li><a onclick='pageSearch("+(parseInt(page.current)+2)+")'>"+(parseInt(page.current)+2)+"</a></li>";
        }
        pageItem+="<li><label style='line-height: 35px'>共 "+page.total+" 条记录</label></li>";
        $(".pagination").append(pageItem);
    }
</script>


</body>
</html>