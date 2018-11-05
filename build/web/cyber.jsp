<%-- 
    Document   : cyber
    Created on : Nov 2, 2018, 4:05:26 PM
    Author     : royal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="fontawesome-free-5.4.1-web/css/all.css">
        <link rel="stylesheet" href="bootstrap-3.1.1-dist/css/bootstrap.min.css" />
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
                            <li><a href="#">Yêu cầu đặt chỗ</a></li>
                            <li><a href="#">Thông tin quán net</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#"><span class="glyphicon glyphicon-user"></span> <s:property value="%{#session.CYBER.username}"/></a></li>
                            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Đăng xuất</a></li>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="row">
                <h3 class="text-center">Yêu cầu đặt chỗ cần phê duyệt</h3>
                <s:if test="%{listRequestNeedToApprove != null}">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên</th>
                                <th>Thời gian đặt</th>
                                <th>Thời lượng chơi</th>
                                <th>Thời gian đến</th>
                                <th>Số chỗ</th>
                                <th>Tên phòng</th>
                                <th>Tên cấu hình</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="listRequestNeedToApprove" status="counter">
                            <td>
                                <s:property value="%{#counter.count}"/>
                            </td>
                            <td>
                                <s:property value="%{username}"/>
                            </td>
                            <td>
                                <s:date name="%{dateRequest}" format="hh:mm dd/MM/yyyy" />   
                            </td>
                            <td>
                                <s:property value="%{duration}"/>
                            </td>
                            <td>
                                <s:date name="%{goingDate}" format="hh:mm dd/MM/yyyy" />   
                            </td>
                            <td>
                                <s:property value="%{numberOfServiceSlot}"/>
                            </td>
                            <td>
                                <s:property value="%{roomname}"/>
                            </td>
                            <td>
                                <s:property value="%{configurationName}"/>
                            </td>
                            <td>
                                <s:form action="approve" theme="simple">
                                    <s:hidden name="requestId" value="%{id}"/>
                                    <button type="submit" class="btn btn-success">Chấp nhận</button>
                                </s:form>
                            </td>
                            </s:iterator>
                        </tbody>
                    </table>
                </s:if>
            </div>
        </div>

    </body>
</html>
