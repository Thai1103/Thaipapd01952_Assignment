<%-- 
    Document   : giohang
    Created on : 20-Oct-2017, 00:20:51
    Author     : tgdd
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.GioHangDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Giỏ hàng - Yamaha Motor VietNam</title>
    </head>
    <%
        if (session != null) {
            if (session.getAttribute("USERNAME") == null) {
                response.sendRedirect("login.jsp");
            }
        }
    %>
    <% GioHangDAO ghDAO = new GioHangDAO();%>
    <body>
        <jsp:include page="menuND.jsp"></jsp:include>
            <div class="container" style="min-height:  700px">
                <div class="col-sm-8">
                    <h2 style="color: firebrick"><strong>Giỏ hàng của khách hàng</strong></h2>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Username</th>
                                <th>Mã sản phẩm</th>
                                <th>Tên sản phẩm</th>
                                <th>Số lượng</th>
                                <th>Giá sản phẩm</th>
                                <th>Xóa</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="sp" items="${LIST_GIOHANG}">
                            <tr>
                                <td>${sp.get(0)}</td>
                                <td>${sp.get(1)}</td>
                                <td>${sp.get(2)}</td>
                                <td>${sp.get(3)}</td>
                                <td>${sp.get(4)}</td> 
                                <td>
                        <center>
                            <a href="GioHangServlet?action=del&maSP=${sp.get(1)}&user=${sp.get(0)}"><i class="fa fa-trash" aria-hidden="true"></i></a>
                        </center>
                        </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="container">
                <div class="col-sm-3">
                    <h2 style="color: firebrick"><strong>Thanh toán</strong></h2>
                    <strong>Tổng số lượng: <em style="color: darkred">${TONGSL}</em> Sản phẩm</strong><br>
                    <strong>Tổng tiền: <em style="color: darkred">${TONGTIEN}</em> VND</strong><br><hr>


                    <form action="GioHangServlet" method="post">
                        <button type="button" class="btn btn-lg btn-primary btn-block" data-toggle="modal" data-target="#myModal" style="width: 300px">Thanh toán</button>
                        <div class="modal fade" id="myModal" role="dialog">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Thanh toán tiền mua xe cho khách hàng</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label for="pwd">Tổng số lượng:</label>
                                            <input type="number" class="form-control" name="pass" value="${TONGSL}"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="pwd">Tổng tiền thanh toán:</label>
                                            <input type="number" class="form-control" name="name" value="${TONGTIEN}"/>
                                        </div>
                                        <div class="modal-footer">
                                            <button style="background-color: lightsalmon" type="submit" class="btn btn-default" name="action" value="thanhtoan">Đồng ý</button>
                                            <button style="background-color: lightsalmon" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>


                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
