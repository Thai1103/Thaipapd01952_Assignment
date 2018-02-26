<%-- 
    Document   : body
    Created on : 06-Oct-2017, 17:52:51
    Author     : tgdd
--%>

<%@page import="Model.SanPhamDAO"%>
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
    <% SanPhamDAO spDAO = new SanPhamDAO(); %>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-8">
                    <video width="730" height="430" controls>
                        <source src="img/Yamaha- Motorcycles for Life.mp4" type="video/mp4">
                        Your browser does not support the <code>video</code> tag.
                    </video>
                </div><br/>
                <div class="col-sm-4">
                    <h2 style="background-color: #000;color: #ffffff;width: 340px;height: 54px; margin-top: 0px">Thông tin mới</h2>
                    <div class="list-group">
                        <a href="" class="list-group-item"><i class="fa fa-motorcycle" aria-hidden="true"></i> Thông tin sản phẩm</a><br/>
                        <a href="" class="list-group-item"><i class="fa fa-motorcycle" aria-hidden="true"></i> Các sản phẩm nhập chính hãng tại Việt Nam</a><br/>
                        <a href="" class="list-group-item"><i class="fa fa-motorcycle" aria-hidden="true"></i> Bảng giá các loại xe PKL</a><br/>
                        <a href="" class="list-group-item"><i class="fa fa-motorcycle" aria-hidden="true"></i> Top các dòng xe được người dùng yêu thích</a><br/>
                        <a href="" class="list-group-item"><i class="fa fa-motorcycle" aria-hidden="true"></i> Thông tin về các dòng xe nhập chính hãng</a><br/>    
                    </div>
                </div><br/>
                <div class="col-sm-12">
                    <% for (int i = 0; i < spDAO.getAllSanPham().size(); i++) {%>
                    <div class="col-sm-4">
                        <img src="img/<%=spDAO.getAllSanPham().get(i).getImg()%>" alt="" width="360" height="250"/>
                        <h2 style="color: firebrick"><%=spDAO.getAllSanPham().get(i).getTen()%></h2>
                        <i class="fa fa-motorcycle" aria-hidden="true"></i> <strong>Mã sản phẩm: </strong><em style="color: firebrick"><%=spDAO.getAllSanPham().get(i).getMaSP()%></em><br>
                        <i class="fa fa-motorcycle" aria-hidden="true"></i> <strong>Số lượng: </strong><em style="color: firebrick"><%=spDAO.getAllSanPham().get(i).getSoluong()%> Chiếc</em><br>
                        <i class="fa fa-motorcycle" aria-hidden="true"></i> <strong>Giá sản phẩm: </strong><em style="color: firebrick"><%=spDAO.getAllSanPham().get(i).getGia()%> VND</em><br><br>
                            <strong><a style="padding-top: 10px;padding-bottom: 10px;padding-left: 125px;padding-right: 125px;color: #000;background-color: skyblue;border-radius: 15px;" href='<%="GioHangServlet?action=addgh&maSP=" + spDAO.getAllSanPham().get(i).getMaSP()%>&user=${USERNAME}'>Thêm giỏ hàng</a></strong>
                        <br><hr>
                    </div>
                    <%}%>
                </div><br/><br><br><hr/>

                <div class="col-sm-12">
                    <div class="col-sm-4">
                        <h3><strong>Tin tức</strong></h3>
                        <ul>
                            <li><a href="">Tuần lễ sự kiện Yamaha.</a></li>
                            <li><a href="">Thay thế dầu nhớt miễn phí.</a></li>
                            <li><a href="">Các phụ kiện, phụ tùng chính hãng.</a></li>
                            <li><a href="">Triển lãm Motorshow tại Đà Nẵng.</a></li>
                        </ul>
                    </div>
                    <div class="col-sm-4">
                        <h3><strong>Các đại lý toàn quốc</strong></h3>
                        <ul>
                            <li><a href="">Khu vực Đà Nẵng.</a></li>
                            <li><a href="">Khu vực Hà Nội.</a></li>
                            <li><a href="">Khu vực TP.Hồ Chí Minh.</a></li>
                            <li><a href="">Khu vực Kon Tum.</a></li>
                        </ul>
                    </div>
                    <div class="col-sm-4">
                        <h3><strong>Những điều cần biết</strong></h3>
                        <ul>
                            <li><a href="">Lịch thay thế dầu nhớt định kỳ.</a></li>
                            <li><a href="">Cách kiểm tra xe.</a></li>
                            <li><a href="">Thời gian bảo dưỡng định kỳ.</a></li>
                            <li><a href="">Hướng dẫn chạy xe an toàn.</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
