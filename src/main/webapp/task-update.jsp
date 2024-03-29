<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">
    <title>CRM</title>
    <!-- Bootstrap Core CSS -->
    <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <!-- animation CSS -->
    <link href="css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="css/colors/blue-dark.css" id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <![endif]-->
</head>

<body>
<!-- Preloader -->
<div class="preloader">
    <div class="cssload-speeding-wheel"></div>
</div>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top m-b-0">
        <div class="navbar-header">
            <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse"
               data-target=".navbar-collapse">
                <i class="fa fa-bars"></i>
            </a>
            <div class="top-left-part">
                <a class="logo" href="/crm/dashboard">
                    <b>
                        <img src="plugins/images/pixeladmin-logo.png" alt="home"/>
                    </b>
                    <span class="hidden-xs">
                            <img src="plugins/images/pixeladmin-text.png" alt="home"/>
                        </span>
                </a>
            </div>
            <ul class="nav navbar-top-links navbar-left m-l-20 hidden-xs">
                <li>
                    <form role="search" class="app-search hidden-xs">
                        <input type="text" placeholder="Search..." class="form-control">
                        <a href="">
                            <i class="fa fa-search"></i>
                        </a>
                    </form>
                </li>
            </ul>
            <ul class="nav navbar-top-links navbar-right pull-right">
                <li>
                    <div class="dropdown">
                        <a class="profile-pic dropdown-toggle" data-toggle="dropdown" href="#">
                            <img src="${sessionScope.usersModel.avatar}"
                                 onerror="this.src='plugins/images/users-default/users-default.jpg'"
                                 alt="user-img" width="36" class="img-circle"/>
                            <b class="hidden-xs">${sessionScope.usersModel.fullname}
                                | ${sessionScope.usersModel.rolesModel.name}</b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/crm/profile?id=${sessionScope.usersModel.id}">Thống kê công việc</a></li>
                            <li class="divider"></li>
                            <li><a href="/crm/logout">Đăng xuất</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
        <!-- /.navbar-header -->
        <!-- /.navbar-top-links -->
        <!-- /.navbar-static-side -->
    </nav>
    <!-- Left navbar-header -->
    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse slimscrollsidebar">
            <ul class="nav" id="side-menu">
                <li style="padding: 10px 0 0;">
                    <a href="/crm/dashboard" class="waves-effect"><i class="fa fa-clock-o fa-fw"
                                                                     aria-hidden="true"></i><span class="hide-menu">Tổng quan</span></a>
                </li>
                <li>
                    <a href="/crm/user" class="waves-effect"><i class="fa fa-user fa-fw"
                                                                aria-hidden="true"></i><span class="hide-menu">Thành viên</span></a>
                </li>
                <li>
                    <a href="/crm/role" class="waves-effect"><i class="fa fa-modx fa-fw"
                                                                aria-hidden="true"></i><span
                            class="hide-menu">Quyền</span></a>
                </li>
                <li>
                    <a href="/crm/groupwork" class="waves-effect"><i class="fa fa-table fa-fw"
                                                                     aria-hidden="true"></i><span class="hide-menu">Dự án</span></a>
                </li>
                <li>
                    <a href="/crm/task" class="waves-effect"><i class="fa fa-table fa-fw"
                                                                aria-hidden="true"></i><span
                            class="hide-menu">Công việc</span></a>
                </li>
            </ul>
        </div>
    </div>
    <!-- Left navbar-header end -->
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Sửa công việc</h4>
                </div>
            </div>
            <!-- /.row -->
            <!-- .row -->
            <div class="row">
                <div class="col-md-2 col-12"></div>
                <div class="col-md-8 col-xs-12">
                    <div class="white-box">
                        <div class="form-horizontal form-material">
                            <div class="form-group">
                                <label class="col-md-12">Dự án</label>
                                <div class="col-md-12">
                                    <select id="input-job" class="form-control form-control-line">
                                        <c:forEach var="jobsModel" items="${jobsModelList}">
                                            <c:choose>
                                                <c:when test="${jobsModel.id == tasksModel.jobsModel.id}">
                                                    <option selected id="${jobsModel.id}">${jobsModel.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option id="${jobsModel.id}">${jobsModel.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Tên công việc</label>
                                <div class="col-md-12">
                                    <input id="input-name" type="text"
                                           value="${tasksModel.name}" placeholder="Tên công việc"
                                           class="form-control form-control-line">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Người thực hiện</label>
                                <div class="col-md-12">
                                    <select id="input-user" class="form-control form-control-line">
                                        <c:forEach var="usersModel" items="${usersModelList}">
                                            <c:choose>
                                                <c:when test="${usersModel.id == tasksModel.usersModel.id}">
                                                    <option selected
                                                            id="${usersModel.id}">${usersModel.fullname}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option id="${usersModel.id}">${usersModel.fullname}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ngày bắt đầu</label>
                                <div class="col-md-12">
                                    <input id="input-startdate" type="text"
                                           value="${tasksModel.startDate}" placeholder="dd/MM/yyyy"
                                           class="form-control form-control-line">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ngày kết thúc</label>
                                <div class="col-md-12">
                                    <input id="input-enddate" type="text"
                                           value="${tasksModel.endDate}" placeholder="dd/MM/yyyy"
                                           class="form-control form-control-line">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Trạng thái</label>
                                <div class="col-md-12">
                                    <select id="input-status" class="form-control form-control-line">
                                        <c:forEach var="statusModel" items="${statusModelList}">
                                            <c:choose>
                                                <c:when test="${statusModel.id == tasksModel.statusModel.id}">
                                                    <option selected id="${statusModel.id}">${statusModel.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option id="${statusModel.id}">${statusModel.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <button class="btn btn-success" onclick="updateTask(${tasksModel.id})">Cập nhật
                                    </button>
                                    <a href="/crm/task" class="btn btn-primary">Quay lại</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-2 col-12"></div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
        <footer class="footer text-center"> 2018 &copy; myclass.com</footer>
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<!-- jQuery -->
<script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Menu Plugin JavaScript -->
<script src="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
<!--slimscroll JavaScript -->
<script src="js/jquery.slimscroll.js"></script>
<!--Wave Effects -->
<script src="js/waves.js"></script>
<!-- Custom Theme JavaScript -->
<script src="js/custom.min.js"></script>
<script>
    function updateTask(id) {
        var name = document.querySelector("#input-name").value;
        var startDate = document.querySelector("#input-startdate").value;
        var endDate = document.querySelector("#input-enddate").value;
        var userId = $("#input-user option:selected").attr("id");
        var jobId = $("#input-job option:selected").attr("id");
        var statusId = $("#input-status option:selected").attr("id");

        $.ajax({
            url: "/crm/api/task-update",
            type: "post",
            data: {
                id: id,
                name: name,
                startDate: startDate,
                endDate: endDate,
                userId: userId,
                jobId: jobId,
                statusId: statusId
            },
            success: function (response) {
                if (response.success) {
                    alert("SUCCESSFUL");

                    window.location.href = "/crm/task";
                } else {
                    if (response.message == "NOT PERMISSION") {
                        alert("YOU ARE NOT PERMISSION");

                        window.location.href = "/crm/dashboard";
                    } else {
                        alert("UNSUCCESSFUL");
                    }
                }
            },
            error: function () {
                alert("ERROR");
            }
        });
    }
</script>
</body>

</html>