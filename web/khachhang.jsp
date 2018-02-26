<%-- 
    Document   : nhanvien
    Created on : 18-Oct-2017, 10:39:22
    Author     : tgdd
--%>

<%@page import="Model.KhachHangDAO"%>
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
    <% KhachHangDAO khDAO = new KhachHangDAO();%>
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
                        <form action="KhachHangServlet" method="post">
                            <div class="form-group">
                                <label for="email">Mã khách hàng:</label>
                                <input type="text" class="form-control" placeholder=" Mời nhập mã khách hàng" name="idkh" value="${NHANVIEN.get(0)}"/>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Tên khách hàng:</label>
                            <input type="text" class="form-control" placeholder="Mời nhập tên khách hàng" name="tenkh" value="${NHANVIEN.get(1)}"/>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Email:</label>
                            <input type="text" class="form-control" placeholder="Mời nhập email nkhách hàng" name="email" value="${NHANVIEN.get(2)}"/>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Số điện thoại:</label>
                            <input type="number" class="form-control" placeholder="Mời nhập số điện thoại khách hàng" name="sdt" value="${NHANVIEN.get(3)}"/>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Địa chỉ:</label>
                            <input type="test" class="form-control" placeholder="Mời nhập địa chỉ khách hàng" name="dc" value="${NHANVIEN.get(4)}"/>
                        </div>
                        <button type="submit" class="btn btn-default" name="action" value="add">Add</button>
                        <button type="submit" class="btn btn-default" name="action" value="update">Update</button>
                    </form>
                    <h2>Danh sách khách hàng</h2>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Mã khách hàng</th>
                                <th>Tên khách hàng</th>
                                <th>Email</th>
                                <th>Số điện thoại</th>
                                <th>Địa chỉ</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (int i = 0; i < khDAO.getAllKhachHang().size(); i++) {
                            %>
                            <tr>
                                <td><%=khDAO.getAllKhachHang().get(i).getIdKH()%></td>
                                <td><%=khDAO.getAllKhachHang().get(i).getTenKH()%></td>
                                <td><%=khDAO.getAllKhachHang().get(i).getEmail()%></td>
                                <td><%=khDAO.getAllKhachHang().get(i).getSDT()%></td>
                                <td><%=khDAO.getAllKhachHang().get(i).getDiaChi()%></td>
                                <td>
                                    <a href='<%="KhachHangServlet?action=edit&idkh=" + khDAO.getAllKhachHang().get(i).getIdKH()%>'><i class="fa fa-pencil" aria-hidden="true"></i></a>|
                                    <a href='<%="KhachHangServlet?action=del&idkh=" + khDAO.getAllKhachHang().get(i).getIdKH()%>'><i class="fa fa-trash" aria-hidden="true"></i></a>
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
