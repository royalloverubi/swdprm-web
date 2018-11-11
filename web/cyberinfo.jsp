<%-- 
    Document   : cyberinfo
    Created on : Nov 11, 2018, 11:35:57 PM
    Author     : royal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="fontawesome-free-5.4.1-web/css/all.css">
        <link rel="stylesheet" href="bootstrap-3.1.1-dist/css/bootstrap.min.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Cyber Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <hr/>
            </div>
            <div class="row">
                <nav class="navbar navbar-inverse">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <p class="navbar-brand">Quản lí quán net</p>
                        </div>
                        <ul class="nav navbar-nav">
                            <li><a href="cyber">Yêu cầu đặt chỗ</a></li>
                            <li><a href="cyberInfo">Thông tin quán net</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#"><span class="glyphicon glyphicon-user"></span> <s:property value="%{#session.CYBER.username}"/></a></li>
                            <li><a href="logout"><span class="glyphicon glyphicon-log-in"></span> Đăng xuất</a></li>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="row">
                <h3 class="text-center">Thông tin cơ bản</h3>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <img src="<s:property value="%{cyberDetail.logo}"/>" width="100%">
                </div>
                <div class="col-md-5">
                    <h4><b>Tên quán :</b> <s:property value="%{cyberDetail.name}"/></h4>
                    <h4><b>Địa chỉ :</b> <s:property value="%{cyberDetail.address}"/></h4>
                    <h4><b>Đánh giá người dùng : </b><span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star"></span></h4>
                </div>
                <div class="col-md-5">
                    <h4 class="text-left"">Giá : <span style="color: green; font-weight: bold;">5.000 vnd/h </span><button type="button" class="btn"><i class="far fa-edit"></i></button> </h4>
                    <h4 class="text-left"">Tổng số máy : <span style="color: red; font-weight: bold;">28 máy </span> <button type="button" class="btn"><i class="far fa-edit"></i></button></h4>
                </div>
            </div>
        </div>

    </body>
</html>
