/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AccountDAO;
import Model.GioHangDAO;
import Model.LoaiSanPham;
import Model.LoaiSanPhamDAO;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author tgdd
 */
public class LoaiSPServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     */
    LoaiSanPhamDAO LoaiSPDAO = new LoaiSanPhamDAO();
    AccountDAO acDAO = new AccountDAO();
    GioHangDAO ghDAO = new GioHangDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String maloaiSP = request.getParameter("maloaiSP");
            String ten = request.getParameter("ten");
            String soluong = request.getParameter("soluong");
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("login")) {
                if (acDAO.login(user, pass) != null) {
                    if (acDAO.login(user, pass).getChucVu().equals("admin")) {
                        HttpSession session = request.getSession();
                        session.setAttribute("USERNAME", user);
                        request.getRequestDispatcher("admin.jsp").forward(request, response);
                    } else {
                        HttpSession session = request.getSession();
                        session.setAttribute("USERNAME", user);
                        request.getRequestDispatcher("index1.jsp").forward(request, response);
                    }
                } else {
                    response.sendRedirect("login.jsp");
                }
            }
            switch (action) {
                case "getloaisp":
                         request.setAttribute("LIST_LOAISANPHAM", LoaiSPDAO.getAllLoaiSanPham());
                         request.getRequestDispatcher("addsp.jsp").forward(request, response);
                    break;
                    
                case "giohang":
                    request.setAttribute("LIST_LOAISANPHAM", LoaiSPDAO.getAllLoaiSanPham());
                    Vector t = LoaiSPDAO.getLoaiSanPhamByID(maloaiSP);
                    request.setAttribute("GIOHANG", t);
                    request.getRequestDispatcher("index1.jsp").forward(request, response);
                    break;

                case "add":
                    HttpSession ss = request.getSession();
                    if (ss.getAttribute("USERNAME") != null) {
                        LoaiSanPham sp = new LoaiSanPham(maloaiSP, ten, Integer.parseInt(soluong));
                        LoaiSPDAO.addLoaiSanPham(sp);
                        request.setAttribute("LIST_LOAISANPHAM", LoaiSPDAO.getAllLoaiSanPham());
                        request.getRequestDispatcher("addloaiSP.jsp").forward(request, response);
                        break;
                    } else {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }

                case "edit":
                    HttpSession ss1 = request.getSession();
                    if (ss1.getAttribute("USERNAME") != null) {
                        request.setAttribute("LIST_LOAISANPHAM", LoaiSPDAO.getAllLoaiSanPham());
                        Vector a = LoaiSPDAO.getLoaiSanPhamByID(maloaiSP);
                        request.setAttribute("LOAISANPHAM", a);
                        request.getRequestDispatcher("addloaiSP.jsp").forward(request, response);
                        break;
                    } else {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                case "update":
                    HttpSession ss2 = request.getSession();
                    if (ss2.getAttribute("USERNAME") != null) {
                        LoaiSanPham sp1 = new LoaiSanPham(maloaiSP, ten, Integer.parseInt(soluong));
                        if (LoaiSPDAO.updateLoaiSanPham(sp1) == 1) {
                            request.setAttribute("LIST_LOAISANPHAM", LoaiSPDAO.getAllLoaiSanPham());
                            request.getRequestDispatcher("addloaiSP.jsp").forward(request, response);
                        }
                        break;
                    } else {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                case "del":
                    HttpSession ss3 = request.getSession();
                    if (ss3.getAttribute("USERNAME") != null) {
                        if (LoaiSPDAO.delLoaiSanPham(maloaiSP) == 1) {
                            request.setAttribute("LIST_LOAISANPHAM", LoaiSPDAO.getAllLoaiSanPham());
                            request.getRequestDispatcher("addloaiSP.jsp").forward(request, response);
                        }
                        break;
                    } else {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                case "hienthi":
                    HttpSession ss4 = request.getSession();
                    if (ss4.getAttribute("USERNAME") != null) {
                        request.setAttribute("LIST_LOAISANPHAM", LoaiSPDAO.getAllLoaiSanPham());
                        request.getRequestDispatcher("addloaiSP.jsp").forward(request, response);
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
        } catch (ParseException ex) {
            Logger.getLogger(LoaiSPServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSPServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoaiSPServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSPServlet.class.getName()).log(Level.SEVERE, null, ex);
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
