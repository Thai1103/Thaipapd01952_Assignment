<%-- 
    Document   : login
    Created on : 06-Oct-2017, 18:46:02
    Author     : tgdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="demo.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Yamaha Motor VietNam</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include><br><hr>
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-md-4 col-md-offset-4">
                        <h1 class="text-center login-title">Login to Yamaha Motor VietNam</h1>
                        <div class="account-wall">
                            <img class="profile-img" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
                                 alt="">
                            <form class="form-signin" action="SanPhamServlet" method="POST">
                                <input type="text" class="form-control" placeholder="Username" name="user" required autofocus>
                                <input type="password" class="form-control" placeholder="Password" name="pass" required>
                                <label class="checkbox pull-left">
                                    <input type="checkbox" value="remember-me">
                                    Remember me
                                </label>
                                <a href="#" class="pull-right need-help">Need help? </a><span class="clearfix"></span>
                                <button class="btn btn-lg btn-primary btn-block" type="submit" name="action" value="login">Login</button>
                            </form>
                            <center>
                            <form action="TaiKhoanServlet" method="post">
                                <button type="button" class="btn btn-lg btn-primary btn-block" data-toggle="modal" data-target="#myModal" style="width: 300px">Sign up</button>
                                <div class="modal fade" id="myModal" role="dialog">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Đăng ý tài khoản khách hàng</h4>
                                            </div>
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <label for="email">Username:</label>
                                                    <input type="text" class="form-control" placeholder=" Mời nhập Username" name="user"/>
                                                </div>
                                                <div class="form-group">
                                                    <label for="pwd">Password:</label>
                                                    <input type="password" class="form-control" placeholder="Mời nhập Password" name="pass"/>
                                                </div>
                                                 <div class="form-group">
                                                    <label for="pwd">Name:</label>
                                                    <input type="text" class="form-control" placeholder="Mời nhập tên" name="name"/>
                                                </div>
                                                <div class="form-group">
                                                    <label for="pwd">Địa chỉ:</label>
                                                    <input type="text" class="form-control" placeholder="Mời nhập địa chỉ" name="dc"/>
                                                </div>
                                                <div class="form-group">
                                                    <label for="pwd">Số điện thoại:</label>
                                                    <input type="number" class="form-control" placeholder="Mời nhập số điện thoại" name="sdt"/>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="submit" class="btn btn-default" name="action" value="add">Đăng ký</button>
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            </center>
                        </div>
                    </div>
                </div>
            </div><br><br><br><hr><br><br><br>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
