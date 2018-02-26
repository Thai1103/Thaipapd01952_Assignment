/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AccountDAO;
import Model.KhachHang;
import Model.KhachHangDAO;
import Model.NhanVien;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author tgdd
 */
public class KhachHangServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
      AccountDAO acDAO = new AccountDAO();
      KhachHangDAO khDAO = new KhachHangDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String idkh = request.getParameter("idkh");
            String tenkh = request.getParameter("tenkh");
            String email = request.getParameter("email");
            String sdt = request.getParameter("sdt");
            String dc = request.getParameter("dc");
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            String action = request.getParameter("action");
            acDAO = new AccountDAO();
            if (action.equalsIgnoreCase("login")) {
                if (acDAO.login(user, pass) != null) {
                    if (acDAO.login(user, pass).getChucVu().equals("admin")) {
                        HttpSession session = request.getSession();
                        session.setAttribute("USERNAME", user);
                        response.sendRedirect("admin.jsp");
                    }else{
                        response.sendRedirect("index1.jsp");
                    }
                } else {
                    response.sendRedirect("login.jsp");
                }
            }
            switch (action) {

                case "add":
                    HttpSession ss = request.getSession();
                    if (ss.getAttribute("USERNAME") != null) {
                        KhachHang kh = new KhachHang(idkh, tenkh, email, Integer.parseInt(sdt), dc);
                        khDAO.addKhachHang(kh);
                        request.setAttribute("LIST_NHANVIEN", khDAO.getAllKhachHang());
                        request.getRequestDispatcher("khachhang.jsp").forward(request, response);
                        break;
                    } else {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }

                case "edit":
                    HttpSession ss1 = request.getSession();
                    if (ss1.getAttribute("USERNAME") != null) {
                        request.setAttribute("LIST_NHANVIEN", khDAO.getAllKhachHang());
                        Vector a = khDAO.getKhachHang(idkh);
                        request.setAttribute("NHANVIEN", a);
                        request.getRequestDispatcher("khachhang.jsp").forward(request, response);
                        break;
                    } else {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                case "update":
                    HttpSession ss2 = request.getSession();
                    if (ss2.getAttribute("USERNAME") != null) {
                        KhachHang kh1 = new KhachHang(idkh, tenkh, email, Integer.parseInt(sdt), dc);
                        if (khDAO.updateKhachHang(kh1) == 1) {
                            request.setAttribute("LIST_NHANVIEN", khDAO.getAllKhachHang());
                            request.getRequestDispatcher("khachhang.jsp").forward(request, response);
                        }
                        break;
                    } else {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                case "del":
                    HttpSession ss3 = request.getSession();
                    if (ss3.getAttribute("USERNAME") != null) {
                        if (khDAO.delNhanVien(idkh) == 1) {
                            request.setAttribute("LIST_NHANVIEN", khDAO.getAllKhachHang());
                            request.getRequestDispatcher("khachhang.jsp").forward(request, response);
                        }
                        break;
                    } else {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                case "hienthi":
                    HttpSession ss4 = request.getSession();
                    if (ss4.getAttribute("USERNAME") != null) {
                        request.setAttribute("LIST_NHANVIEN", khDAO.getAllKhachHang());
                        request.getRequestDispatcher("khachhang.jsp").forward(request, response);
                        break;
                    } else {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
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
          } catch (SQLException ex) {
              Logger.getLogger(KhachHangServlet.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ParseException ex) {
              Logger.getLogger(KhachHangServlet.class.getName()).log(Level.SEVERE, null, ex);
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
          } catch (SQLException ex) {
              Logger.getLogger(KhachHangServlet.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ParseException ex) {
              Logger.getLogger(KhachHangServlet.class.getName()).log(Level.SEVERE, null, ex);
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
