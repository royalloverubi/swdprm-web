<%-- 
    Document   : login
    Created on : Nov 2, 2018, 3:48:21 PM
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
        <title>Login Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div class="card card-signin my-5">
                        <div class="card-body">
                            <h2 class="card-title text-center">Sign In</h2>
                            <form class="form-signin" action="login" method="POST">
                                <div class="form-label-group">
                                    <label for="inputEmail">Username</label>
                                    <input name="username" value="${username}" type="username" id="inputEmail" class="form-control" placeholder="Username" required autofocus>
                                </div>

                                <div class="form-label-group">
                                    <label for="inputPassword">Password</label>
                                    <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                                </div>
                                <hr class="my-4">
                                <p style="color: red">${messageErr}</p>
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign in</button>
                                <a href="signup.html">Signup now for start free forever</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
