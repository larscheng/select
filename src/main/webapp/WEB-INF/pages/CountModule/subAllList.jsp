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
            <h2 class="pull-left"><i class="icon-home"></i> 题目列表</h2>

            <!-- Breadcrumb -->
            <div class="bread-crumb pull-right">
                <a href="#"><i class="icon-home"></i> 历届题目列表</a>
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
                            <select  class="form-control" name="subType">
                                <option value="" selected>题目类型</option>
                                <c:forEach var="type" items="${requestScope.subType}">
                                    <option value="${type.key}">${type.value}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group " style="margin-right: 10px">
                            <select  class="form-control" name="forDepId">
                                <option value="" selected>面向系别</option>
                                <c:forEach var="dep" items="${requestScope.depList}">
                                    <option value="${dep.id}">${dep.depName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group " style="margin-right: 10px">
                            <select  class="form-control" name="admAuditState">
                                <option value="" selected>审核状态</option>
                                <option value="1">审核失败</option>
                                <option value="2">审核成功</option>
                            </select>
                        </div>

                        <div class="form-group " style="margin-right: 10px">
                            <select  class="form-control" name="teaId">
                                <option value="" selected>教师名</option>
                                <c:forEach var="tea" items="${requestScope.teaSet}">
                                    <option value="${tea.id}">${tea.userName}</option>
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

                            <div class="widget-head">
                                <div class="pull-left">题目列表</div>
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
                                        <th>题目名称</th>
                                        <th>发布教师</th>
                                        <th>教师电话</th>
                                        <th>题目类型</th>
                                        <th>题目届别</th>
                                        <th>审核状态</th>
                                        <th>面向系别</th>
                                        <th>选题状态</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="items">
                                    <c:forEach var="subject" items="${requestScope.subjectList}" varStatus="index">
                                        <tr>
                                            <td  class=" text-center"><input type="checkbox" name="ids" value="${subject.id}" /></td>
                                            <td>${index.count}</td>
                                            <td>${subject.subName}</td>
                                            <td>${subject.subTeaName}</td>
                                            <td>${subject.teaPhone}</td>
                                            <td><span class="label label-primary">${subject.typeName}</span></td>
                                            <td>${subject.subYear}级</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${subject.admAuditState eq 1}">
                                                        <span class="label label-danger">审核不通过</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="label label-success">审核通过</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${subject.forDepName}</td>
                                            <td> <span class="label label-primary">${subject.subSelectStatusName}</span></td>
                                            <td><fmt:formatDate value="${subject.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td>
                                                <button class="btn btn-xs btn-info" onclick="subjectDetails(${subject.id})"><i class="icon-pencil"></i>详情</button>
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
                                            <li><a class="disabled" href="${ctx}/selectSubject/subList?page=${page.current-1}">上一页</a></li>
                                            <li><a href="${ctx}/selectSubject/subList?page=${page.current-1}">${page.current-1}</a></li>
                                        </c:if>

                                        <li><a href="${ctx}/selectSubject/subList?page=${page.current}">${page.current}</a></li>

                                        <c:if test="${page.current+1 <= page.pages}">
                                            <li><a href="${ctx}/selectSubject/subList?page=${page.current+1}">${page.current+1}</a></li>
                                        </c:if>
                                        <c:if test="${page.current+2 <= page.pages}">
                                            <li><a href="${ctx}/selectSubject/subList?page=${page.current+2}">${page.current+2}</a></li>
                                        </c:if>
                                        <c:if test="${page.current+1 <= page.pages}">
                                            <li><a href="${ctx}/selectSubject/subList?page=${page.current+1}">下一页</a></li>
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
    sessionStorage.setItem("userType",${sessionScope.userType});
    var manType = sessionStorage.getItem("userType");
    var url = "/selectSubject/subListAjax";
    if(manType==3){
        url = "/selectSubject/subListAjax?selectId=${sessionScope.sessionUser.id}";
    }

    $("#search").keydown(function (e) {
        if(event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码
            search()
        }

    });
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
                "admAuditState":$(" select[ name='admAuditState' ] ").val(),
                "subType":$(" select[ name='subType' ] ").val(),
                "forDepId":$(" select[ name='forDepId' ] ").val(),
                "teaId":$(" select[ name='teaId' ] ").val()
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




    function subjectDetails(id) {
        window.location.href="/selectSubject/subjectDetail?id="+id;
    }


    function initTeaPage(objects) {
        var obj =JSON.parse(objects);
        var subjectList = obj.subjectList;
        $("#items").html(null);
        if (jQuery.isEmptyObject(subjectList)){
            $("#items").append("<tr><td colspan='14' class='text-center'> 😑 暂无数据！</td></tr>");
        }else{
            $(subjectList).each(function (index) {
                var val = subjectList[index];
                var time = getLocalTime(val.gmtCreate);
                var item =
                    "<tr>"
                    +"<td class='text-center'>"+"<input type='checkbox' name='ids' value='"+val.id+"'/>"+"</td>"
                    +"<td>"+(parseInt(index)+1)+"</td>"
                    +"<td>"+val.subName+"</td>"
                    +"<td>"+val.subTeaName+"</td>"
                    +"<td>"+val.teaPhone+"</td>"
                    +"<td><span class='label label-primary'>"+val.typeName+"</span></td>"
                    +"<td>"+val.subYear+"级</td>"
                ;
//                    console.log(item);
                if (parseInt(val.admAuditState) == 2){
                    item +=
                        "<td><span class='label label-success'>审核通过</span></td>"
                        +"<td>"+val.forDepName+"</td>"
                        +"<td><span class='label label-primary'>"+val.subSelectStatusName+"</span></td>"
                        +"<td>"+time+"</td>"
                        +"<td>" +
                        "<button onclick='subjectDetails("+val.id+")' class='btn btn-xs btn-info' style='margin-right: 5px'><i class='icon-pencil'></i>详情</button>" +
                        "</td>"
                        +"</tr>"
                    ;
                }else{
                    item+=
                        "<td><span class='label label-danger'>审核不通过</span></td>"
                        +"<td>"+val.forDepName+"</td>"
                        +"<td><span class='label label-primary'>"+val.subSelectStatusName+"</span></td>"
                        +"<td>"+time+"</td>"
                        +"<td>" +
                        "<button onclick='subjectDetails("+val.id+")' class='btn btn-xs btn-info' style='margin-right: 5px'><i class='icon-pencil'></i>详情</button>" +

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