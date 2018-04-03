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


<!-- Main content starts -->


    <div class="mainbar">

        <!-- Page heading -->
        <div class="page-head">
            <h2 class="pull-left"><i class="icon-home"></i> 学生信息管理</h2>

            <!-- Breadcrumb -->
            <div class="bread-crumb pull-right">
                <a href="#"><i class="icon-home"></i> 学生信息管理</a>
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
                <div class="row">

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
                            <select  class="form-control" name="stuMajorId" id="stuMajorId" onchange="initClass()">
                                <option value="" selected>专业</option>
                                <c:forEach var="major" items="${requestScope.majorList}">
                                    <option value="${major.id}">${major.majName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group " style="margin-right: 10px">
                            <select  class="form-control" name="stuClass" id="stuClass">
                                <option value="" selected>班级</option>
                            </select>
                        </div>
                        <div class="form-group" style="margin-right: 10px">
                            <select  class="form-control" name="stuYear">
                                <option value="" selected>届别</option>
                                <c:forEach var="year" items="${requestScope.yearList}">
                                    <option value="${year.stuYear}">${year.stuYear}级</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group" style="margin-right: 10px">
                                <div class="input-group date form_datetime">
                                    <input name="searchBgnTime" onclick="aaa()" class="form-control"type="text" placeholder="起始时间" value="" readonly style="width: 140px">
                                    <span class="input-group-addon" onclick="aaa()"><i class="glyphicon glyphicon-remove icon-remove"></i></span>
                                    <span class="input-group-addon" onclick="aaa()"><i class="glyphicon glyphicon-th icon-calendar"></i></span>
                                </div> --
                                <div class="input-group date form_datetime" onclick="aaa()">
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

                            <div class="widget-head" style="position: relative">
                                <div class="pull-left">学生列表</div>
                                <div class="widget-icons pull-right">
                                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                </div>
                                <c:if test="${sessionScope.userType != 3}">
                                    <div class="row navbar-form " style="position: absolute; top: -5px; right: 50px">
                                        <button type="button" onclick="stuDeleteAll()" class="btn btn-info pull-left " style="margin-right: 10px"><i class="icon-remove"></i>批量删除</button>
                                        <button type="button" class="btn btn-info pull-left " onclick="upload()" style="margin-right: 10px"><i class="icon-upload"></i>批量导入</button>
                                        <div style="display: none">
                                            <form id="uploadForm"  >
                                                <input type="file" id="fileField" name="fileField" style="display: none" onchange="ajaxUpload()"/>
                                            </form>
                                        </div>
                                        <button type="button"  onclick="window.location.href='/selectUserBase/stuInitAdd';" style="margin-right: 10px" class="btn btn-info pull-left "><i class="icon-upload"></i>学生添加</button>
                                        <button type="button"  onclick="window.location.href='/selectUserBase/stuFileDown';" class="btn btn-default pull-left "><i class="icon-upload"></i>模板下载</button>
                                    </div>
                                </c:if>
                                <div class="clearfix"></div>
                            </div>

                            <div class="widget-content ">

                                <table class="table table-striped table-bordered table-hover" >
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
                                        <th>专业</th>
                                        <th>班级</th>
                                        <th>届别</th>
                                        <th>状态</th>
                                        <th>创建时间</th>
                                        <c:if test="${sessionScope.userType != 3}">
                                        <th>操作</th>
                                        </c:if>
                                    </tr>
                                    </thead>
                                    <tbody id="items">
                                    <c:forEach var="user" items="${userList}" varStatus="index">
                                        <tr>
                                            <td  class=" text-center"><input type="checkbox" name="ids" value="${user.id}" /></td>
                                            <td>${index.count}</td>
                                            <td>${user.userName}</td>
                                            <td>${user.userCode}</td>
                                            <td>${user.sex}</td>
                                            <td><a href="mailto:${user.userMail}">${user.userMail}</a></td>
                                            <td>${user.userPhone}</td>
                                            <td><a href="tencent://AddContact/?fromId=50&fromSubId=1&subcmd=all&uin=${user.userQq}">${user.userQq}</a></td>
                                            <td>${user.stuMajorName}</td>
                                            <td>${user.stuClass}班</td>
                                            <td>${user.stuYear}级</td>
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
                                            <c:if test="${sessionScope.userType != 3}">
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${status eq 0}">
                                                            <button class="btn btn-xs btn-success"  onclick="stuAble('${user.id}')"><i class="icon-ok"></i>启用</button>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <button class="btn btn-xs btn-danger"  onclick="stuDisAble('${user.id}')"><i class="icon-remove"></i>禁用</button>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <button class="btn btn-xs btn-warning" onclick="window.location.href='/selectUserBase/stuInitUpdate?id=${user.id}';"><i class="icon-pencil">编辑</i>
                                                    </button>
                                                    <button class="btn btn-xs btn-danger" onclick="stuDelete('${user.id}')"><i class="icon-remove">删除</i></button>
                                                </td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>

                                <div class="widget-foot center">
                                    <ul class="pagination">
                                        <c:if test="${page.current-1 eq 0}">
                                            <li><a href="#" class="btn btn-default" disabled="disabled">上一页</a></li>
                                        </c:if>
                                        <c:if test="${page.current-1 > 0}">
                                            <li><a class="disabled" href="/selectUserBase/stuList?page=${page.current-1}">上一页</a></li>
                                            <li><a href="/selectUserBase/stuList?page=${page.current-1}">${page.current-1}</a></li>
                                        </c:if>


                                        <li><a href="/selectUserBase/stuList?page=${page.current}">${page.current}</a></li>

                                        <c:if test="${page.current+1 <= page.pages}">
                                            <li><a href="/selectUserBase/stuList?page=${page.current+1}">${page.current+1}</a></li>
                                        </c:if>
                                        <c:if test="${page.current+2 <= page.pages}">
                                            <li><a href="/selectUserBase/stuList?page=${page.current+2}">${page.current+2}</a></li>
                                        </c:if>
                                        <c:if test="${page.current+1 <= page.pages}">
                                            <li><a href="/selectUserBase/stuList?page=${page.current+1}">下一页</a></li>
                                        </c:if>
                                        <c:if test="${page.current+1 > page.pages}">
                                            <li><a class="btn  disabled" href="#">下一页</a></li>
                                        </c:if>
                                        <li><label style="line-height: 35px">共${page.total}条记录</label></li>
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

    var manType = ${sessionScope.userType};
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
                        $("#stuClass").append( "<option value='' selected>班级</option>" );
                        for (var i =1 ; i<=msg ; i++){
                            $("#stuClass").append( "<option value="+i+">"+i+"班</option>" );
                        }
                    }else {
                        $("#stuClass").html(null);
                        $("#stuClass").append( "<option value='' selected>班级</option>" );
//                        alert(" 😥 "+msg);
                    }
            },//end success
            error: function(e) {
                alert(" 😥 系统异常，请与我们的工程师联系！");
            }
        });
    }


    function upload() {
        confirm("导入前请下载好导入模板！","",function (isConfirm) {
            if (isConfirm) {
                //after click the confirm
                $("#fileField").click();
            } else {
                //after click the cancel
                //TODO 跳转到下载页
                window.location.href='/selectUserBase/stuFileDown';
            }
        }, {confirmButtonText: '已有模板', cancelButtonText: '下载模板', width: 400});
    }

    function ajaxUpload() {
//        alert("nininini");
        $.ajax({
            type:'POST',
            url:'/selectUserBase/stuUpload', //你处理上传文件的服务端
            data: new FormData($('#uploadForm')[0]),
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            dataType:"json",
            success: function (msg) {//调用成功时怎么处理
                    alert(" 😋 "+msg,"",function () {
                        location.href="/selectUserBase/stuList";
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
            url: "/selectUserBase/stuListAjax",
            data:{"search":$(" input[ name='search' ] ").val()},
            dataType:"json",
            success:function(objects){
                initStuPage(objects);
            },//end success
            error: function(e) {
                alert(" 😥 系统异常，请与我们的工程师联系！");
            }
        });
    }

    function pageSearch(page) {
        $.ajax({
            type: "post",
            url: "/selectUserBase/stuListAjax",
            data:{"page":page,
                "search":$(" input[ name='search' ] ").val(),
                "userSex":$(" select[ name='userSex' ] ").val(),
                "userStatus":$(" select[ name='userStatus' ] ").val(),
                "stuClass":$(" select[ name='stuClass' ] ").val(),
                "stuYear":$(" select[ name='stuYear' ] ").val(),
                "stuMajorName":$(" select[ name='stuMajorName' ] ").val()
            },
            dataType:"json",
            success:function(objects){
                initStuPage(objects);
            },//end success
            error: function(e) {
                alert(" 😥 系统异常，请与我们的工程师联系！");
            }
        });
    }

    $("#searchSubmit").click(function(){
        $.ajax({
            type: "post",
            url: "/selectUserBase/stuListAjax",
            data: $("#searchForm").serialize(),
            dataType:"json",
            success:function(objects){
                initStuPage(objects);
            },
            error: function(e) {
                alert(" 😥 系统异常，请与我们的工程师联系！");
            }
        });
    });

    function stuAble(id){
        confirm(" 😲 确认启用？","",function (isConfirm) {
            if (isConfirm){
                $.ajax({
                    type:"POST",
                    url:"/selectUserBase/stuAble",
                    data:{"id":id,"userStatus":1},
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else {
                            alert(" 😋 启用成功","",function () {
                                location.href="/selectUserBase/stuList";
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

    function stuDisAble(id){
        confirm(" 😲 确认禁用？","",function (isConfirm) {
            if (isConfirm){
                $.ajax({
                    type:"POST",
                    url:"/selectUserBase/stuAble",
                    data:{"id":id,"userStatus":0},
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else{
                            alert(" 😋 禁用成功！","",function () {
                                location.href="/selectUserBase/stuList";
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


    function stuUpdate(id) {
        window.location.href='/selectUserBase/stuInitUpdate?id='+id;
    }


    function stuDelete(id){
        confirm(" 😲 确认删除吗？","",function (isconfirm) {
            if (isconfirm){
                $.ajax({
                    type:"POST",
                    url:"/selectUserBase/stuDelete",
                    data:{"id":id},
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else{
                            alert(" 😋 删除成功！","",function () {
                                location.href="/selectUserBase/stuList";
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

    function stuDeleteAll(){
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
                        url:"/selectUserBase/stuDeleteAll",
                        data: { "selectedIDs": arrayId },
                        dataType:"json",
                        traditional: true,
                        success:function(msg){
                            if("OK"!=msg){
                                alert(" 😅 "+msg);
                            }else{
                                alert(" 😋 删除成功！","",function () {
                                    location.href="/selectUserBase/stuList";
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




    function initStuPage(objects) {
        var obj =JSON.parse(objects);
        var stuList = obj.stuList;
        $("#items").html(null);
        if (jQuery.isEmptyObject(stuList)){
            $("#items").append("<tr><td colspan='14' class='text-center'> 😑 暂无数据！</td></tr>");
        }else{
            $(stuList).each(function (index) {
                var val = stuList[index];
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
                    +"<td>"+val.stuMajorName+"</td>"
                    +"<td>"+val.stuClass+"班</td>"
                    +"<td>"+val.stuYear+"级</td>"

                ;
    //                    console.log(item);
                if (parseInt(val.userStatus) == 1){
                    item +=
                        "<td><span class='label label-success'>启用</span></td>"
                        +"<td>"+time+"</td>"
                        ;

                }else{
                    item+=
                        "<td><span class='label label-danger'>禁用</span></td>"
                        +"<td>"+time+"</td>"
                        ;
                }
                if (parseInt(manType)!=3){

                    if (parseInt(val.userStatus) == 1){
                        item+=
                            "<td>" +
                            "<button onclick='stuDisAble("+val.id+")' class='btn btn-xs btn-danger' style='margin-right: 5px'><i class='icon-ok'></i>禁用</button>" +
                            "<button onclick='stuUpdate("+val.id+")' class='btn btn-xs btn-warning' style='margin-right: 5px'><i class='icon-pencil'></i>编辑</button>" +
                            "<button onclick='stuDelete("+val.id+")' class='btn btn-xs btn-danger' style='margin-right: 5px'><i class='icon-remove'>删除</i></button>" +
                            "</td>"
                            +"</tr>"
                        ;

                    }else{
                        item+=
                            "<td>" +
                            "<button onclick='stuAble("+val.id+")' class='btn btn-xs btn-success' style='margin-right: 5px'><i class='icon-ok'></i>启用</button>" +
                            "<button onclick='stuUpdate("+val.id+")' class='btn btn-xs btn-warning' style='margin-right: 5px'><i class='icon-pencil'></i>编辑</button>" +
                            "<button onclick='stuDelete("+val.id+")' class='btn btn-xs btn-danger' style='margin-right: 5px'><i class='icon-remove'>删除</i></button>" +
                            "</td>"
                            +"</tr>"
                        ;
                    }


                    }

                console.log(item);
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