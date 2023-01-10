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
                    <h4 class="page-title">Chi tiết thành viên</h4>
                </div>
            </div>
            <!-- /.row -->
            <!-- .row -->
            <div class="row">
                <div class="col-md-4 col-xs-12">
                    <div style="height:200px;padding:0;" class="white-box">
                        <div style="height:100%;margin:0;" class="user-bg">
                            <img style="height:100%;width:100%;object-fit:cover;" alt="user"
                                 src="${usersModel.avatar}"
                                 onerror="this.src='plugins/images/users-default/user-bg-default.jpg'">
                            <div class="overlay-box">
                                <div style="margin-top:10px;" class="user-content">
                                    <a href="javascript:void(0)">
                                        <img src="${usersModel.avatar}"
                                             onerror="this.src='plugins/images/users-default/users-default.jpg'"
                                             class="thumb-lg img-circle" alt="img">
                                    </a>
                                    <h4 class="text-white">${usersModel.fullname}</h4>
                                    <h5 class="text-white">${usersModel.email}</h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-8 col-xs-12">
                    <!-- BEGIN THỐNG KÊ -->
                    <div class="row">
                        <!--col -->
                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                            <div style="height:200px;" class="white-box">
                                <div class="col-in row">
                                    <div class="col-xs-12">
                                        <h3 class="counter text-right m-t-15 text-danger">${numberOfNewTask}</h3>
                                    </div>
                                    <div class="col-xs-12">
                                        <i data-icon="E" class="linea-icon linea-basic"></i>
                                        <h5 class="text-muted vb text-center">CHƯA BẮT ĐẦU</h5>
                                    </div>
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-danger" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: ${(numberOfNewTask/numberOfAllTask)*100}%"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.col -->
                        <!--col -->
                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                            <div style="height:200px;" class="white-box">
                                <div class="col-in row">
                                    <div class="col-xs-12">
                                        <h3 class="counter text-right m-t-15 text-megna">${numberOfProgressTask}</h3>
                                    </div>
                                    <div class="col-xs-12">
                                        <i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>
                                        <h5 class="text-muted vb text-center">ĐANG THỰC HIỆN</h5>
                                    </div>
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-megna" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: ${(numberOfProgressTask/numberOfAllTask)*100}%"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.col -->
                        <!--col -->
                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                            <div style="height:200px;" class="white-box">
                                <div class="col-in row">
                                    <div class="col-xs-12">
                                        <h3 class="counter text-right m-t-15 text-primary">${numberOfCompleledTask}</h3>
                                    </div>
                                    <div class="col-xs-12">
                                        <i class="linea-icon linea-basic" data-icon="&#xe00b;"></i>
                                        <h5 class="text-muted vb text-center">HOÀN THÀNH</h5>
                                    </div>
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-primary" role="progressbar"
                                                 aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                 style="width: ${(numberOfCompleledTask/numberOfAllTask)*100}%"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- END THỐNG KÊ -->
                </div>
            </div>
            <!-- /.row -->
            <!-- BEGIN DANH SÁCH CÔNG VIỆC -->
            <h4>DANH SÁCH CÔNG VIỆC</h4>
            <div class="row">
                <div class="col-md-4">
                    <div class="white-box">
                        <h3 class="box-title">Chưa thực hiện</h3>
                        <div class="message-center">
                            <c:forEach var="tasksModel" items="${tasksModelList}">
                                <c:if test="${tasksModel.statusModel.id == 1}">
                                    <a>
                                        <div class="mail-contnet">
                                            <h5>${tasksModel.name}</h5>
                                            <span class="mail-desc"></span>
                                            <span class="time">Bắt đầu: ${tasksModel.startDate}</span>
                                            <span class="time">Kết thúc: ${tasksModel.endDate}</span>
                                        </div>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="white-box">
                        <h3 class="box-title">Đang thực hiện</h3>
                        <div class="message-center">
                            <c:forEach var="tasksModel" items="${tasksModelList}">
                                <c:if test="${tasksModel.statusModel.id == 2}">
                                    <a>
                                        <div class="mail-contnet">
                                            <h5>${tasksModel.name}</h5>
                                            <span class="mail-desc"></span>
                                            <span class="time">Bắt đầu: ${tasksModel.startDate}</span>
                                            <span class="time">Kết thúc: ${tasksModel.endDate}</span>
                                        </div>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="white-box">
                        <h3 class="box-title">Đã hoàn thành</h3>
                        <div class="message-center">
                            <c:forEach var="tasksModel" items="${tasksModelList}">
                                <c:if test="${tasksModel.statusModel.id == 3}">
                                    <a>
                                        <div class="mail-contnet">
                                            <h5>${tasksModel.name}</h5>
                                            <span class="mail-desc"></span>
                                            <span class="time">Bắt đầu: ${tasksModel.startDate}</span>
                                            <span class="time">Kết thúc: ${tasksModel.endDate}</span>
                                        </div>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END DANH SÁCH CÔNG VIỆC -->
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
</body>

</html>