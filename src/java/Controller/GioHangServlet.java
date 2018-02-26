/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.GioHang;
import Model.GioHangDAO;
import Model.SanPham;
import Model.SanPhamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tgdd
 */
public class GioHangServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    GioHangDAO ghDAO = new GioHangDAO();
    SanPhamDAO spDAO = new SanPhamDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String user = request.getParameter("user");
            String maSP = request.getParameter("maSP");
            String action = request.getParameter("action");
            switch (action) {
                case "addgh":
                    GioHang gh = new GioHang(user, maSP, 1);
                    SanPham sp = new SanPham(maSP, "", 0, 0, "", "");
                    if (ghDAO.addGioHang(gh) == 1) {
                        ghDAO.addGioHang1(sp);
                        request.getRequestDispatcher("index1.jsp").forward(request, response);
                    } else {
                        ghDAO.addGioHang1(sp);
                        request.getRequestDispatcher("index1.jsp").forward(request, response);
                    }
                    break;

                case "del":
                    if (ghDAO.delGioHang(maSP) == 1) {
                        request.setAttribute("LIST_GIOHANG", ghDAO.getAllGioHang(user));
                        int tongSL = 0;
                        int TongTien = 0;
                        for (int i = 0; i < ghDAO.getAllGioHang(user).size(); i++) {
                            Vector t = (Vector) ghDAO.getAllGioHang(user).get(i);
                            tongSL = tongSL + Integer.parseInt(t.get(3).toString());
                            TongTien = TongTien + Integer.parseInt(t.get(5).toString());
                        }
                        request.setAttribute("TONGSL", tongSL);
                        request.setAttribute("TONGTIEN", TongTien);
                    } else {
                        request.setAttribute("LIST_GIOHANG", ghDAO.getAllGioHang(user));
                        int tongSL = 0;
                        int TongTien = 0;
                        for (int i = 0; i < ghDAO.getAllGioHang(user).size(); i++) {
                            Vector t = (Vector) ghDAO.getAllGioHang(user).get(i);
                            tongSL = tongSL + Integer.parseInt(t.get(3).toString());
                            TongTien = TongTien + Integer.parseInt(t.get(5).toString());
                        }
                        request.setAttribute("TONGSL", tongSL);
                        request.setAttribute("TONGTIEN", TongTien);
                    }
                    request.getRequestDispatcher("giohang.jsp").forward(request, response);
                    break;

                case "CartiD":
                    request.setAttribute("LIST_GIOHANG", ghDAO.getAllGioHang(user));
                    int tongSL = 0;
                    int TongTien = 0;
                    for (int i = 0; i < ghDAO.getAllGioHang(user).size(); i++) {
                        Vector t = (Vector) ghDAO.getAllGioHang(user).get(i);
                        tongSL = tongSL + Integer.parseInt(t.get(3).toString());
                        TongTien = TongTien + Integer.parseInt(t.get(5).toString());
                    }
                    request.setAttribute("TONGSL", tongSL);
                    request.setAttribute("TONGTIEN", TongTien);
                    request.getRequestDispatcher("giohang.jsp").forward(request, response);
                    break;

                case "thanhtoan":
                    if (ghDAO.thanhToan() == 1) {
                        request.getRequestDispatcher("giohang.jsp").forward(request, response);
                    }

            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(GioHangServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GioHangServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(GioHangServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GioHangServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
