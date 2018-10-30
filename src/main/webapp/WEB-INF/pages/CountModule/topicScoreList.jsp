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
            <h2 class="pull-left"><i class="icon-home"></i> 选题成绩列表</h2>
            <!-- Breadcrumb -->
            <div class="bread-crumb pull-right">
                <a href="#"><i class="icon-home"></i> 报表统计</a>
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

                        <div class="form-group " style="position: relative;margin-right: 3px">
                            <input type="text" class="form-control" size="10" id="search" name="search" placeholder="题目名称/届别">
                            <span onclick="search()" style="position: absolute;left: 90px;top: 6px;cursor: pointer"><i class="icon-search" ></i></span>
                        </div>

                        <div class="form-group " style="margin-right: 3px">
                            <select  class="form-control" name="teaId">
                                <option value="" selected>教师名</option>
                                <c:forEach var="tea" items="${requestScope.teaList}">
                                    <option value="${tea.id}">${tea.userName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <%--<div class="form-group " style="margin-right: 3px">--%>
                            <%--<select  class="form-control" name="stuId">--%>
                                <%--<option value="" selected>学生名</option>--%>
                                <%--<c:forEach var="stu" items="${requestScope.stuList}">--%>
                                    <%--<option value="${stu.id}">${stu.userName}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                        <div class="form-group " style="margin-right: 3px">
                            <select  class="form-control"  name="teaAuditState">
                                <option value="" selected>审核状态</option>
                                <option value="0">未处理</option>
                                <option value="1">审核不通过</option>
                                <option value="2">审核通过</option>
                            </select>
                        </div>

                        <div class="form-group " style="margin-right: 3px">
                            <select  class="form-control" name="subState">
                                <option value="" selected>是否结题</option>
                                <option value="2">未结题</option>
                                <option value="3">已结题</option>
                            </select>
                        </div>

                        <div class="form-group " style="margin-right: 3px">
                            <select  class="form-control" name="forDepId" id="forDepId" onchange="initMajor()">
                                <option value="" selected>面向系别</option>
                                <c:forEach var="dep" items="${requestScope.depList}">
                                    <option value="${dep.id}">${dep.depName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group " style="margin-right: 3px">
                            <select  class="form-control" name="stuMajorId" id="stuMajorId" onchange="initClass()">
                                <option value="" selected>专业</option>
                            </select>
                        </div>

                        <div class="form-group " style="margin-right: 3px">
                            <select  class="form-control" name="stuClass" id="stuClass">
                                <option value="" selected>班级</option>
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

                            <div class="widget-head" style="position: relative">
                                <div class="pull-left">选题列表</div>
                                <div class="widget-icons pull-right">
                                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                </div>
                                <div class="row navbar-form " style="position: absolute; top: -5px; right: 50px">
                                    <button type="button" onclick="exportScoreInfo()" class="btn btn-info pull-left " style="margin-right: 10px"><i class="icon-remove"></i>导出报表</button>
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
                                        <th>教师电话</th>
                                        <th>学生姓名</th>
                                        <th>学生电话</th>
                                        <th>指导老师评分</th>
                                        <th>评阅老师评分</th>
                                        <th>答辩的分</th>
                                        <th>最终得分</th>
                                        <th>题目届别</th>
                                        <th>是否结题</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="items">
                                    <c:choose>
                                        <c:when test="${empty requestScope.topicList }">
                                            <tr><td colspan='14' class='text-center'> 😑 暂无数据！</td></tr>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="topic" items="${requestScope.topicList}" varStatus="index">
                                                <tr>
                                                    <td  class=" text-center"><input type="checkbox" name="ids" value="${topic.id}" /></td>
                                                    <td>${index.count}</td>
                                                    <td>${topic.subName}</td>
                                                    <td>${topic.teaName}</td>
                                                    <td>${topic.teaPhone}</td>
                                                    <td>${topic.stuName}</td>
                                                    <td>${topic.stuPhone}</td>
                                                    <td>${topic.tutorScore}</td>
                                                    <td>${topic.judgeScore}</td>
                                                    <td>${topic.defenceScore}</td>
                                                    <td><b style="color: red">${topic.finalTotalScore}</b></td>

                                                    <td>${topic.topicYear}级</td>

                                                    <td>
                                                        <c:set var="subState" value="${topic.subSelectStatus}"/>
                                                        <c:choose>
                                                            <c:when test="${subState eq 3}">
                                                                <span class="label label-warning">已结题</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="label label-primary">未结题</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td><fmt:formatDate value="${topic.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    <td>
                                                        <button class="btn btn-xs btn-info" onclick="topicDetails(${topic.id})"><i class="icon-pencil"></i>详情</button>
                                                        <c:if test="${sessionScope.userType eq 3}">
                                                            <c:if test="${topic.teaAuditState eq 1}">
                                                                <button  type="button" class="btn  btn-xs  btn-danger" onclick="topicDel(${topic.id})"><i class="icon-warning-sign"></i>删除</button>
                                                            </c:if>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>


                                    </tbody>
                                </table>

                                <div class="widget-foot center">
                                    <c:choose>
                                        <c:when test="${sessionScope.userType eq 3}">
                                            <ul class="pagination ">
                                                <c:if test="${page.current-1 eq 0}">
                                                    <li><a href="#" class="btn  disabled">上一页</a></li>
                                                </c:if>
                                                <c:if test="${page.current-1 > 0}">
                                                    <li><a class="disabled" href="${ctx}/selectTopic/topicScoreList?stuId=${sessionScope.sessionUser.id}&page=${page.current-1}">上一页</a></li>
                                                    <li><a href="${ctx}/selectTopic/topicScoreList?stuId=${sessionScope.sessionUser.id}&page=${page.current-1}">${page.current-1}</a></li>
                                                </c:if>


                                                <li><a href="${ctx}/selectTopic/topicScoreList?stuId=${sessionScope.sessionUser.id}&page=${page.current}">${page.current}</a></li>

                                                <c:if test="${page.current+1 <= page.pages}">
                                                    <li><a href="${ctx}/selectTopic/topicScoreList?stuId=${sessionScope.sessionUser.id}&page=${page.current+1}">${page.current+1}</a></li>
                                                </c:if>
                                                <c:if test="${page.current+2 <= page.pages}">
                                                    <li><a href="${ctx}/selectTopic/topicScoreList?stuId=${sessionScope.sessionUser.id}&page=${page.current+2}">${page.current+2}</a></li>
                                                </c:if>
                                                <c:if test="${page.current+1 <= page.pages}">
                                                    <li><a href="${ctx}/selectTopic/topicScoreList?stuId=${sessionScope.sessionUser.id}&page=${page.current+1}">下一页</a></li>
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
                                                    <li><a class="disabled" href="${ctx}/selectTopic/topicScoreList?page=${page.current-1}">上一页</a></li>
                                                    <li><a href="${ctx}/selectTopic/topicScoreList?page=${page.current-1}">${page.current-1}</a></li>
                                                </c:if>


                                                <li><a href="${ctx}/selectTopic/topicScoreList?page=${page.current}">${page.current}</a></li>

                                                <c:if test="${page.current+1 <= page.pages}">
                                                    <li><a href="${ctx}/selectTopic/topicScoreList?page=${page.current+1}">${page.current+1}</a></li>
                                                </c:if>
                                                <c:if test="${page.current+2 <= page.pages}">
                                                    <li><a href="${ctx}/selectTopic/topicScoreList?page=${page.current+2}">${page.current+2}</a></li>
                                                </c:if>
                                                <c:if test="${page.current+1 <= page.pages}">
                                                    <li><a href="${ctx}/selectTopic/topicScoreList?page=${page.current+1}">下一页</a></li>
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


    /***
     * 根据系别生成专业
     */
    function initMajor() {
        $.ajax({
            type: "post",
            url: "${ctx}/selectUserBase/initMajor",
            data:{"forDepId":$("#forDepId").val()},
            dataType:"json",
            success:function(object){
                var majorList = object.majorList;
                if (jQuery.isEmptyObject(majorList)){
                    $("#stuMajorId").html(null);
                    $("#stuMajorId").append( "<option value='' selected>专业</option>" );

                }else {
                    $("#stuMajorId").html(null);
                    $("#stuMajorId").append( "<option value='' selected>专业</option>" );
                    $(majorList).each(function (index) {
                        var val = majorList[index];
                        $("#stuMajorId").append( "<option value="+val.id+">"+val.majName+"</option>" );
                    });
                }
            },//end success
            error: function(e) {
                alert(" 😥 系统异常，请与我们的工程师联系！");
            }
        });
    }  /***
     * 根据专业查询并生成班级下拉
     */
    function initClass() {
        $.ajax({
            type: "post",
            url: "${ctx}/selectUserBase/initClass",
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




    sessionStorage.setItem("userType",${sessionScope.userType});

    var manType = sessionStorage.getItem("userType");

    $("#search").keydown(function (e) {
        if(event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码
            search()
        }

    });

    function search() {
        var url = "${ctx}/selectTopic/topicCountAjaxList?teaAuditState=2";
        if (manType == 3){
            url="${ctx}/selectTopic/stuTopicAjaxList?stuId=${sessionScope.sessionUser.id}&subState=3";
        }
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
        var url = "${ctx}/selectTopic/topicCountAjaxList?teaAuditState=2";
        if (manType == 3){
            url="${ctx}/selectTopic/stuTopicAjaxList?stuId=${sessionScope.sessionUser.id}&subState=3";
        }
        $.ajax({
            type: "post",
            url: url,
            data:{"page":page,
                "search":$(" input[ name='search' ] ").val(),
                "teaId":$(" select[ name='teaId' ] ").val(),
                "subState":$(" select[ name='subState' ] ").val(),
                "teaAuditState":$(" select[ name='teaAuditState' ] ").val(),
                "forDepId":$(" select[ name='forDepId' ] ").val(),
                "stuMajorId":$(" select[ name='stuMajorId' ] ").val(),
                "searchBgnTime":$(" input[ name='searchBgnTime' ] ").val(),
                "searchEndTime":$(" input[ name='searchEndTime' ] ").val()
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




    function exportScoreInfo() {

        var search=$(" input[ name='search' ] ").val();
        var teaId=$(" select[ name='teaId' ] ").val();
        var subState=$(" select[ name='subState' ] ").val();
        var forDepId=$(" select[ name='forDepId' ] ").val();
        var stuMajorId=$(" select[ name='stuMajorId' ] ").val();
        var stuClass=$(" select[ name='stuClass' ] ").val();
        var teaAuditState=$(" select[ name='teaAuditState' ] ").val();
        var searchBgnTime=$(" input[ name='searchBgnTime' ] ").val();
        var searchEndTime=$(" input[ name='searchEndTime' ] ").val();


        window.location.href="${ctx}/selectTopic/exportScore?search="+search+"&teaId="+teaId+"&subState="+subState+"&forDepId="+forDepId+
            "&stuMajorId="+stuMajorId+"&stuClass="+stuClass+"&teaAuditState="+teaAuditState+"&searchBgnTime="+searchBgnTime+"&searchEndTime="+searchEndTime;
    }


    $("#searchSubmit").click(function(){
        var url = "${ctx}/selectTopic/topicCountAjaxList";
        if (manType == 3){
            url="${ctx}/selectTopic/stuTopicAjaxList?stuId=${sessionScope.sessionUser.id}&subState=3";
        }
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



    function topicDel(id){
        confirm(" 😲 确认删除吗？","",function (isConfirm) {
            if (isConfirm){
                $.ajax({
                    type:"GET",
                    url:"${ctx}/selectTopic/topicDel",
                    data:{"id":id},
                    dataType:"json",
                    success:function(msg){
                        if("OK"!=msg){
                            alert(" 😅 "+msg);
                        }else {
                            alert(" 😋 删除成功","",function () {
                                location.href="${ctx}/selectTopic/topicList?stuId=${sessionScope.sessionUser.id}";
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

    function topicDetails(id) {
        window.location.href="${ctx}/selectTopic/topicDetails?id="+id;
    }

    function teaUpdate(id) {
        window.location.href='${ctx}/selectUserBase/teaInitUpdate?id='+id;
    }





    function initTeaPage(obj) {
//        var obj =JSON.parse(objects);
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
                    +"<td>"+val.teaPhone+"</td>"
                    +"<td>"+val.stuName+"</td>"
                    +"<td>"+val.stuPhone+"</td>"
                    +"<td>"+val.tutorScore+"</td>"
                    +"<td>"+val.judgeScore+"</td>"
                    +"<td>"+val.defenceScore+"</td>"
                    +"<td><b style='color: red'>"+val.finalTotalScore+"</b> </td>"
                    +"<td>"+val.topicYear+"级</td>";
                    if (parseInt(val.subSelectStatus) == 3){
                        item +="<td><span class='label label-warning'>已结题</span></td>";
                    } else {
                        item +="<td><span class='label label-primary'>未结题</span></td>";
                    }
                    item+="<td>"+time+"</td>"
                    +"<td>" +
                    "<button onclick='topicDetails("+val.id+")' class='btn btn-xs btn-info' style='margin-right: 5px'><i class='icon-pencil'></i>详情</button>" +
                    "</td>"
                    +"</tr>"
                ;
//
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