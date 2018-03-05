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
            <h2 class="pull-left"><i class="icon-home"></i> 待审核记录列表</h2>
            <!-- Breadcrumb -->
            <div class="bread-crumb pull-right">
                <a href="#"><i class="icon-home"></i> 选题信息管理</a>
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
                            <input type="text" class="form-control" id="search" name="search" placeholder="题目名称/题目届别">
                            <span onclick="search()" style="position: absolute;left: 155px;top: 6px;cursor: pointer"><i class="icon-search" ></i></span>
                        </div>

                        <div class="form-group " style="margin-right: 10px">
                            <select  class="form-control" name="teaId">
                                <option value="" selected>教师名</option>
                                <c:forEach var="tea" items="${requestScope.teaList}">
                                    <option value="${tea.id}">${tea.userName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group " style="margin-right: 10px">
                            <select  class="form-control" name="stuId">
                                <option value="" selected>学生名</option>
                                <c:forEach var="stu" items="${requestScope.stuList}">
                                    <option value="${stu.id}">${stu.userName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group " style="margin-right: 10px">
                            <select  class="form-control" name="teaAuditState">
                                <option value="" selected>审核状态</option>
                                <option value="0">未处理</option>
                                <option value="1">审核不通过</option>
                                <option value="2">审核通过</option>
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

                                <div class="clearfix"></div>
                            </div>

                            <div class="widget-content ">

                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th class=" text-center"><input type="checkbox" id="selectAll"></th>
                                        <th>序号</th>
                                        <th>题目名</th>
                                        <th>教师姓名</th>
                                        <th>学生姓名</th>
                                        <th>审核状态</th>
                                        <th>题目届别</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="items">
                                    <c:forEach var="topic" items="${requestScope.topicList}" varStatus="index">
                                        <tr>
                                            <td  class=" text-center"><input type="checkbox" name="ids" value="${topic.id}" /></td>
                                            <td>${index.count}</td>
                                            <td>${topic.subName}</td>
                                            <td>${topic.teaName}</td>
                                            <td>${topic.stuName}</td>
                                            <td>
                                                <c:set var="status" value="${topic.teaAuditState}"/>
                                                <c:choose>
                                                    <c:when test="${status eq 0}">
                                                        <span class="label label-warning">未处理</span>
                                                    </c:when>
                                                    <c:when test="${status eq 1}">
                                                        <span class="label label-danger">审核不通过</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="label label-success">审核通过</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${topic.topicYear}级</td>
                                            <td><fmt:formatDate value="${topic.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td>
                                                <button class="btn btn-xs btn-info" onclick="topicDetails(${topic.id})"><i class="icon-pencil"></i>详情</button>
                                                <c:if test="${status eq 0}">
                                                    <button class="btn btn-xs btn-success" onclick="subSuccess(${topic.id})"><i class="icon-ok-circle"></i>通过</button>
                                                    <button class="btn btn-xs btn-danger" onclick="cleanAll(${topic.id})"
                                                            id="modal-317062" href="#modal-container-317062" role="button"  data-toggle="modal"><i class="icon-remove-sign"></i>不通过</button>
                                                </c:if>

                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>

                                <div class="row clearfix">
                                    <div class="col-md-6 column">
                                        <div class="modal fade" id="modal-container-317062" role="dialog"
                                             aria-labelledby="myModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" style="left: 3px">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-hidden="true">
                                                        </button>
                                                        <h4 class="modal-title" id="myModalLabel">审核不通过原因：</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <input type="hidden" id="hid"/>
                                                        <textarea class="form-control" rows="4" id="reason"
                                                                  placeholder="原因"></textarea>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default"
                                                                data-dismiss="modal">关闭
                                                        </button>
                                                        <button type="button" class="btn btn-primary"
                                                                onclick="subFail()">确认
                                                        </button>
                                                    </div>
                                                </div>

                                            </div>

                                        </div>

                                    </div>
                                    <div class="col-md-6 column">
                                    </div>
                                </div>

                                <div class="widget-foot center">
                                    <c:choose>
                                        <c:when test="${sessionScope.userType eq 3}">
                                            <ul class="pagination ">
                                                <c:if test="${page.current-1 eq 0}">
                                                    <li><a href="#" class="btn  disabled">上一页</a></li>
                                                </c:if>
                                                <c:if test="${page.current-1 > 0}">
                                                    <li><a class="disabled" href="${ctx}/selectTopic/topicList?stuId=${sessionScope.sessionUser.id}&page=${page.current-1}">上一页</a></li>
                                                    <li><a href="${ctx}/selectTopic/topicList?stuId=${sessionScope.sessionUser.id}&page=${page.current-1}">${page.current-1}</a></li>
                                                </c:if>


                                                <li><a href="${ctx}/selectTopic/topicList?stuId=${sessionScope.sessionUser.id}&page=${page.current}">${page.current}</a></li>

                                                <c:if test="${page.current+1 <= page.pages}">
                                                    <li><a href="${ctx}/selectTopic/topicList?stuId=${sessionScope.sessionUser.id}&page=${page.current+1}">${page.current+1}</a></li>
                                                </c:if>
                                                <c:if test="${page.current+2 <= page.pages}">
                                                    <li><a href="${ctx}/selectTopic/topicList?stuId=${sessionScope.sessionUser.id}&page=${page.current+2}">${page.current+2}</a></li>
                                                </c:if>
                                                <c:if test="${page.current+1 <= page.pages}">
                                                    <li><a href="${ctx}/selectTopic/topicList?stuId=${sessionScope.sessionUser.id}&page=${page.current+1}">下一页</a></li>
                                                </c:if>
                                                <c:if test="${page.current+1 > page.pages}">
                                                    <li><a class="btn  disabled" href="#">下一页</a></li>
                                                </c:if>

                                            </ul>

                                        </c:when>
                                        <c:otherwise>
                                            <ul class="pagination ">
                                                <c:if test="${page.current-1 eq 0}">
                                                    <li><a href="#" class="btn  disabled">上一页</a></li>
                                                </c:if>
                                                <c:if test="${page.current-1 > 0}">
                                                    <li><a class="disabled" href="${ctx}/selectTopic/topicList?page=${page.current-1}">上一页</a></li>
                                                    <li><a href="${ctx}/selectTopic/topicList?page=${page.current-1}">${page.current-1}</a></li>
                                                </c:if>


                                                <li><a href="${ctx}/selectTopic/topicList?page=${page.current}">${page.current}</a></li>

                                                <c:if test="${page.current+1 <= page.pages}">
                                                    <li><a href="${ctx}/selectTopic/topicList?page=${page.current+1}">${page.current+1}</a></li>
                                                </c:if>
                                                <c:if test="${page.current+2 <= page.pages}">
                                                    <li><a href="${ctx}/selectTopic/topicList?page=${page.current+2}">${page.current+2}</a></li>
                                                </c:if>
                                                <c:if test="${page.current+1 <= page.pages}">
                                                    <li><a href="${ctx}/selectTopic/topicList?page=${page.current+1}">下一页</a></li>
                                                </c:if>
                                                <c:if test="${page.current+1 > page.pages}">
                                                    <li><a class="btn  disabled" href="#">下一页</a></li>
                                                </c:if>

                                            </ul>
                                        </c:otherwise>
                                    </c:choose>

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

    var manType = sessionStorage.getItem("userType");





    function cleanAll(id) {
        $("#reason").val("");
        $("#hid").val(id)
    }

    $("#search").keydown(function (e) {
        if(event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码
            search()
        }

    });
    var url="/selectTopic/stuTopicAjaxList?stuId=${sessionScope.sessionUser.id}";
    function search() {

        $.ajax({
            type: "post",
            url: url,
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
            url: url,
            data:{"page":page,
                "search":$(" input[ name='search' ] ").val(),
                "teaId":$(" select[ name='teaId' ] ").val(),
                "stuId":$(" select[ name='stuId' ] ").val(),
                "teaAuditState":$(" select[ name='teaAuditState' ] ").val()
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
            url: url,
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



    function subSuccess(id){
        confirm(" 😲 确认审核通过？","",function (isConfirm) {
            if (isConfirm){
                $.ajax({
                    type:"POST",
                    url:"/selectTopic/topicAudited",
                    data:{"id":id,"teaAuditState":2},
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else {
                            alert(" 😋 审核通过","",function () {
                                window.location.href= "/selectTopic/noTopicList?teaId=${sessionScope.sessionUser.id}";
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


    function subFail(){

        confirm(" 😲 确认不通过？","",function (isConfirm) {
            if (isConfirm){
                $.ajax({
                    type:"POST",
                    url:"/selectTopic/topicAudited",
                    data:{"id":$("#hid").val(),"teaAuditState":1,"teaAuditContent":$("#reason").val()},
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else{
                            alert(" 😋 审核完成！","",function () {
                                window.location.href= "/selectTopic/noTopicList?teaId=${sessionScope.sessionUser.id}";
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

    function topicDetails(id) {
        window.location.href="/selectTopic/topicDetails?id="+id;
    }


    function initTeaPage(objects) {
        var obj =JSON.parse(objects);
        var topicList = obj.topicList;
        $("#items").html(null);
        if (jQuery.isEmptyObject(topicList)){
            $("#items").append("<tr><td colspan='14' class='text-center'> 😑 暂无数据！</td></tr>");
        }else{
            $(topicList).each(function (index) {
                var val = topicList[index];
                var time = getLocalTime(val.gmtCreate);
                var item =
                    "<tr>"
                    +"<td class='text-center'>"+"<input type='checkbox' name='ids' value='"+val.id+"'/>"+"</td>"
                    +"<td>"+(parseInt(index)+1)+"</td>"
                    +"<td>"+val.subName+"</td>"
                    +"<td>"+val.teaName+"</td>"
                    +"<td>"+val.stuName+"</td>"
                ;
                    if (parseInt(val.teaAuditState) == 0){
                        item +=
                            "<td><span class='label label-warning'>未处理</span></td>"
                            +"<td>"+val.topicYear+"级</td>"
                            +"<td>"+time+"</td>"
                            +"<td>" +
                                "<button onclick='teaDetails("+val.id+")' class='btn btn-xs btn-info' style='margin-right: 5px'><i class='icon-pencil'></i>详情</button>" +
                            "</td>"
                            +"</tr>"
                        ;
                    }else if (parseInt(val.teaAuditState) == 1){
                        item+=
                            "<td><span class='label label-danger'>审核不通过</span></td>"
                            +"<td>"+val.topicYear+"级</td>"
                            +"<td>"+time+"</td>"
                            +"<td>" +
                            "<button onclick='teaDetails("+val.id+")' class='btn btn-xs btn-info' style='margin-right: 5px'><i class='icon-pencil'></i>详情</button>" +
                            "</td>"
                            +"</tr>"
                        ;
                    }else {
                        item+=
                            "<td><span class='label label-success'>审核通过</span></td>"
                            +"<td>"+val.topicYear+"级</td>"
                            +"<td>"+time+"</td>"
                            +"<td>" +
                            "<button onclick='teaDetails("+val.id+")' class='btn btn-xs btn-info' style='margin-right: 5px'><i class='icon-pencil'></i>详情</button>" +
                            "</td>"
                            +"</tr>"
                        ;
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