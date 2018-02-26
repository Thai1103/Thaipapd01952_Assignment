<%-- 
    Document   : admin
    Created on : 06-Oct-2017, 19:04:39
    Author     : tgdd
--%>
<%@page import="Model.LoaiSanPhamDAO"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Thống kê - Yamaha Motor VietNam</title>
    </head>
    <% LoaiSanPhamDAO LoaiSPDAO = new LoaiSanPhamDAO();%>
    <body>
        <%
            if (session != null) {
                if (session.getAttribute("USERNAME") == null) {
                    response.sendRedirect("login.jsp");
                }
            }
        %>
        <jsp:include page="menu.jsp"></jsp:include>
            <div class="col-sm-12">
                <div class="col-sm-3">
                <jsp:include page="admin.jsp"></jsp:include>
                </div>
                <div class="container">
                    <div class="col-sm-9">
                        <h1>Loại sản phẩm</h1>
                        <form action="LoaiSPServlet" method="get" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="email">Mã loại sản phẩm:</label>
                                <input type="text" class="form-control" placeholder=" Mời nhập mã loại sản phẩm" name="maloaiSP" value="${LOAISANPHAM.get(0)}"/>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Tên loại sản phẩm:</label>
                            <input type="text" class="form-control" placeholder="Mời nhập tên loại sản phẩm" name="ten" value="${LOAISANPHAM.get(1)}"/>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Số lượng:</label>
                            <input type="number" class="form-control" placeholder="Mời nhập số lượng loại sản phẩm" name="soluong" value="${LOAISANPHAM.get(2)}"/>
                        </div>
                        <button type="submit" class="btn btn-default" name="action" value="add">Add</button>
                        <button type="submit" class="btn btn-default" name="action" value="update">Update</button>
                        <script>
                            function readURL(input) {
                                if (input.files && input.files[0]) {
                                    var reader = new FileReader();
                                    reader.onload = function (e) {
                                        document.getElementById('blah').src = e.target.result;
                                    }
                                    reader.readAsDataURL(input.files[0]);
                                }
                            }
                        </script>
                    </form>

                    <h2>Danh sách loại sản phẩm</h2>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Mã loại sản phẩm</th>
                                <th>Tên sản phẩm</th>
                                <th>Số lượng</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (int i = 0; i < LoaiSPDAO.getAllLoaiSanPham().size(); i++) {
                            %>
                            <tr>
                                <td><%=LoaiSPDAO.getAllLoaiSanPham().get(i).getMaLoaiSP() %></td>
                                <td><%=LoaiSPDAO.getAllLoaiSanPham().get(i).getTenLoaiSP() %></td>
                                <td><%=LoaiSPDAO.getAllLoaiSanPham().get(i).getSoLuong() %></td>
                                <td>
                                    <a href='<%="LoaiSPServlet?action=edit&maloaiSP=" + LoaiSPDAO.getAllLoaiSanPham().get(i).getMaLoaiSP() %>'><i class="fa fa-pencil" aria-hidden="true"></i></a>|
                                    <a href='<%="LoaiSPServlet?action=del&maloaiSP=" + LoaiSPDAO.getAllLoaiSanPham().get(i).getMaLoaiSP() %>'><i class="fa fa-trash" aria-hidden="true"></i></a>
                                </td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
