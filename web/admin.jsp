<%-- 
    Document   : admin
    Created on : 08-Oct-2017, 22:17:40
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
        <title>Admin - Yamaha Motor VietNam</title>
    </head>
    <%
        if (session != null) {
            if (session.getAttribute("USERNAME") == null) {
                response.sendRedirect("login.jsp");
            }
        }
    %>
    <style>
        /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
        .row.content {height: 1500px}

        /* Set gray background color and 100% height */
        .sidenav {
            background-color: #f1f1f1;
            height: 100%;
        }

        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }

        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }
            .row.content {height: auto;} 
        }
    </style>
    <body>
        <div class="container-fluid">
            <div class="row content">
                <div class="col-sm-12 sidenav">
                    <h4>Quản lý</h4>
                    <ul class="nav nav-pills nav-stacked">
                        <li><a href="admin.jsp" >Trang chủ</a></li>
                        <li><a href="LoaiSPServlet?action=getloaisp" >Sản phẩm</a></li>
                        <li><a href="addloaiSP.jsp" >Loại sản phẩm</a></li>
                        <li><a href="">Kế toán</a></li>
                        <li><a href="">Doanh thu</a></li>
                        <li><a href="khachhang.jsp">Khách hàng</a></li>
                        <li><a href="nhanvien.jsp">Nhân viên</a></li>
                        <li><a href="LogoutServlet">Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>
