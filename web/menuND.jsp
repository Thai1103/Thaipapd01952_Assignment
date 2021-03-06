<%-- 
    Document   : menu
    Created on : 06-Oct-2017, 10:26:49
    Author     : tgdd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Yamaha Motor VietNam</title>
    </head>
    <%
        if (session != null) {
            if (session.getAttribute("USERNAME") == null) {
                response.sendRedirect("login.jsp");
            }
        }
    %>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index1.jsp">Yamaha</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="index1.jsp"><i class="fa fa-home" aria-hidden="true"></i> Trang chủ</a></li>
                    <li><a href="#">Giới thiệu</a></li>
                    <li><a href="#">Sản phẩm</a></li>
                    <li><a href="#">Thông tin</a></li>
                    <li><a href="#">Hình ảnh</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="GioHangServlet?action=CartiD&user=${USERNAME}" class="glyphicon glyphicon-shopping-cart">Giỏ hàng ${TONGSL}</a></li>
                    <li><a href="LogoutServlet" class="glyphicon glyphicon-log-out">Logout</a></li>
                </ul>
            </div>
        </nav>
    </body>
</html>
