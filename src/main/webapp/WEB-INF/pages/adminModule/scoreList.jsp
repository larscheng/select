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
            <h2 class="pull-left"><i class="icon-home"></i> 成绩比例划分</h2>

            <!-- Breadcrumb -->
            <div class="bread-crumb pull-right">
                <a href="#"><i class="icon-home"></i> 成绩比例管理</a>
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
                                <div class="pull-left">成绩比例</div>
                                <div class="widget-icons pull-right">
                                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                </div>
                                <div class="row navbar-form " style="position: absolute; top: -5px; right: 50px">
                                        <%--<button type="button" style="margin-right: 10px"  class="btn btn-info pull-left" onclick="deleteAllScore()"><i class="icon-remove"> </i>批量删除</button>--%>
                                        <%--<button type="button" style="margin-right: 10px" onclick="window.location.href='/selectScorePer/addInitScore';" class="btn btn-success pull-left"><i class="icon-edit"> </i>添加</button>--%>
                                </div>
                                <div class="clearfix"></div>
                            </div>

                            <div class="widget-content">

                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th class=" text-center"><input type="checkbox" id="selectAll"></th>
                                        <th>序号</th>
                                        <th>成绩组成模块</th>
                                        <th>所占百分比</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="score" items="${requestScope.scoreList}" varStatus="index">
                                        <tr>
                                            <td class=" text-center">
                                                <input name="ids" type="checkbox" value="${score.id}"/>
                                            </td>
                                            <td>${index.count}</td>
                                            <td>${score.scoreName}</td>
                                            <td>${score.scorePer}%</td>
                                            <td><fmt:formatDate value="${score.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

                                            <td>

                                                <button class="btn btn-xs btn-warning" onclick="window.location.href='/selectScorePer/updateInitScore?id=${score.id}';"><i class="icon-pencil">编辑</i>
                                                </button>
                                                <%--<button class="btn btn-xs btn-danger" onclick="delScore('${score.id}')"><i class="icon-remove">删除</i>--%>
                                                </button>

                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>

                                <div class="widget-foot center">
                                   <b style="color: red">注意：毕业论文（设计）的成绩由指导教师评分、评阅人评分和答辩评分3部分组成，每部分均采用百分制，原则上分别占总成绩的30%、20%、50%。也可由各系根据实际情况自行划分。
                                   </b>


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

        function deleteAllScore(){
            var arrayid = new Array();
            $('input[name="ids"]:checked').each(function(){arrayid.push($(this).val());});
            if(arrayid.length==0){
                alert(" 🙋 无实例选中");
                event.preventDefault(); // 兼容标准浏览器
                window.event.returnValue = false; // 兼容IE6~8
            }else{
                confirm(" 🙋 确认删除?","", function (isConfirm) {
                    if (isConfirm) {
                        //after click the confirm

                        $.ajax({
                            type:"POST",
                            url:"/selectScorePer/delAllScore",
                            data: { "selectedIDs": arrayid },
                            dataType:"json",
                            traditional: true,
                            success:function(msg){
                                if("OK"!=msg){
                                    alert(" 🔞 "+msg);
                                }else{
                                    alert(" 👌 删除成功","", function () {
                                        location.href="/selectScorePer/scoreList";
                                    }, {type: 'success', confirmButtonText: '好的'});
                                }
                            },
                            error:function(e){
                                alert(" 🔞 系统异常，请与我们程序员小哥哥联系！");
                            }
                        });
                    }
                }, {confirmButtonText: '❌ 删除', cancelButtonText: '🚫 取消', width: 400});
            }
        }



        function delScore(id){
            confirm(" ❓ 确认删除?","", function (isConfirm) {
                if (isConfirm) {
                    $.ajax({
                        type:"POST",
                        url:"/selectScorePer/delScore",
                        data:{"id":id},
                        dataType:"json",
                        success:function(msg){
                            if("OK"!=msg){
                                alert(" 😝 "+msg);
                            }else{
                                alert(" 👍 删除成功","", function () {
                                    location.href="/selectScorePer/scoreList";
                                }, {type: 'success', confirmButtonText: '好的'});
                            }
                        },
                        error:function(e){
                            alert(" 🔞 系统异常，请与我们程序员小哥哥联系！");
                        }
                    })
                }
            }, {confirmButtonText: '删除', cancelButtonText: '取消', width: 400});





        }
    </script>
<%@include file="/WEB-INF/pages/common/macDownCommon.jsp" %>
</body>
</html>