<%-- 
    Document   : nhanvien
    Created on : 18-Oct-2017, 10:39:22
    Author     : tgdd
--%>

<%@page import="Model.NhanVienDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Nhân viên - Yamaha Motor VietNam</title>
    </head>
    <% NhanVienDAO nvDAO = new NhanVienDAO();%>
    <body>
        <%
            if (session != null) {
                if (session.getAttribute("USERNAME") == null) {
                    response.sendRedirect("login.jsp");
                }
            }
        %>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>
            <div class="col-sm-12">
                <div class="col-sm-3">
                <jsp:include page="admin.jsp"></jsp:include>
                </div>
                <div class="container">
                    <div class="col-sm-9">
                        <h1>Nhân viên</h1>
                        <form action="NVServlet" method="post">
                            <div class="form-group">
                                <label for="email">Mã nhân viên:</label>
                                <input type="text" class="form-control" placeholder=" Mời nhập mã nhân viên" name="MaNV" value="${NHANVIEN.get(0)}"/>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Tên nhân viên:</label>
                            <input type="text" class="form-control" placeholder="Mời nhập tên nhân viên" name="TenNV" value="${NHANVIEN.get(1)}"/>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Địa chỉ:</label>
                            <input type="text" class="form-control" placeholder="Mời nhập địa chỉ nhân viên" name="DiaChi" value="${NHANVIEN.get(2)}"/>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Emai:</label>
                            <input type="text" class="form-control" placeholder="Mời nhập Email nhân viên" name="Email" value="${NHANVIEN.get(3)}"/>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Số điện thoại:</label>
                            <input type="number" class="form-control" placeholder="Mời nhập số điện thoại nhân viên" name="SoDienThoai" value="${NHANVIEN.get(4)}"/>
                        </div>
                        <button type="submit" class="btn btn-default" name="action" value="add">Add</button>
                        <button type="submit" class="btn btn-default" name="action" value="update">Update</button>
                    </form>
                    <h2>Danh sách sản phẩm</h2>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Mã nhân viên</th>
                                <th>Tên nhân viên</th>
                                <th>Địa chỉ</th>
                                <th>Email</th>
                                <th>Số điện thoại</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (int i = 0; i < nvDAO.getAllNhanVien().size(); i++) {
                            %>
                            <tr>
                                <td><%=nvDAO.getAllNhanVien().get(i).getMaNV()%></td>
                                <td><%=nvDAO.getAllNhanVien().get(i).getTenNV()%></td>
                                <td><%=nvDAO.getAllNhanVien().get(i).getDiaChi()%></td>
                                <td><%=nvDAO.getAllNhanVien().get(i).getEmail()%></td>
                                <td><%=nvDAO.getAllNhanVien().get(i).getSoDienThoai()%></td>
                                <td>
                                    <a href='<%="NVServlet?action=edit&MaNV=" + nvDAO.getAllNhanVien().get(i).getMaNV()%>'><i class="fa fa-pencil" aria-hidden="true"></i></a>|
                                    <a href='<%="NVServlet?action=del&MaNV=" + nvDAO.getAllNhanVien().get(i).getMaNV()%>'><i class="fa fa-trash" aria-hidden="true"></i></a>
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
