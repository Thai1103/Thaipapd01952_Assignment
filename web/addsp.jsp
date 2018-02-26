<%-- 
    Document   : admin
    Created on : 06-Oct-2017, 19:04:39
    Author     : tgdd
--%>
<%@page import="Model.SanPhamDAO"%>
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
    <% SanPhamDAO spDAO = new SanPhamDAO();%>
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
                        <h1>Sản phẩm</h1>
                        <form action="SanPhamServlet" method="get" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="email">Mã sản phẩm:</label>
                                <input type="text" class="form-control" placeholder=" Mời nhập mã sản phẩm" name="maSP" value="${SANPHAM.get(0)}"/>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Tên sản phẩm:</label>
                            <input type="text" class="form-control" placeholder="Mời nhập tên sản phẩm" name="ten" value="${SANPHAM.get(1)}"/>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Số lượng:</label>
                            <input type="number" class="form-control" placeholder="Mời nhập số lượng sản phẩm" name="soluong" value="${SANPHAM.get(2)}"/>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Giá:</label>
                            <input type="number" class="form-control" placeholder="Mời nhập giá sản phẩm" name="gia" value="${SANPHAM.get(3)}"/>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Mã loại sản phẩm</label><br/>
                            <select name="maloaiSP"> 
                                <c:forEach  items="${LIST_LOAISANPHAM}" var="lsp">
                                    <option value="${lsp.getMaLoaiSP()}">${lsp.getTenLoaiSP()}</option>
                                </c:forEach>
                                
                            </select>
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

                    <h2>Danh sách sản phẩm</h2>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Mã sản phẩm</th>
                                <th>Tên sản phẩm</th>
                                <th>Số lượng</th>
                                <th>Giá</th>
                                <th>Hình ảnh</th>
                                <th>Mã loại sản phẩm</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (int i = 0; i < spDAO.getAllSanPham().size(); i++) {
                            %>
                            <tr>
                                <td><%=spDAO.getAllSanPham().get(i).getMaSP()%></td>
                                <td><%=spDAO.getAllSanPham().get(i).getTen()%></td>
                                <td><%=spDAO.getAllSanPham().get(i).getSoluong()%></td>
                                <td><%=spDAO.getAllSanPham().get(i).getGia()%></td>
                                <td><%=spDAO.getAllSanPham().get(i).getImg()%></td>
                                <td><%=spDAO.getAllSanPham().get(i).getMaLoaiSP()%></td>
                                <td>
                                    <a href='<%="SanPhamServlet?action=edit&maSP=" + spDAO.getAllSanPham().get(i).getMaSP()%>'><i class="fa fa-pencil" aria-hidden="true"></i></a>|
                                    <a href='<%="SanPhamServlet?action=del&maSP=" + spDAO.getAllSanPham().get(i).getMaSP()%>'><i class="fa fa-trash" aria-hidden="true"></i></a>
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
