/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Common.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tgdd
 */
public class SanPhamDAO {

    Connection conn = null;
    PreparedStatement pre1, pre2, pre3, pre4;
    ConnectDB con = new ConnectDB();

    public int addSanPham(SanPham sanpham) {
        conn = con.getConnect();
        try {
            pre1 = conn.prepareStatement("INSERT INTO `sanpham` (`MaSP`, `TenSP`, `SoLuong`, `Gia`, `Img`, `MaLoaiSP`) VALUES (?, ?, ?, ?, ?, ?);");
            pre1.setString(1, sanpham.getMaSP());
            pre1.setString(2, sanpham.getTen());
            pre1.setInt(3, sanpham.getSoluong());
            pre1.setInt(4, sanpham.getGia());
            pre1.setString(5, sanpham.getImg());
            pre1.setString(6, sanpham.getMaLoaiSP());
            if (pre1.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public List<SanPham> getAllSanPham() throws ParseException, SQLException {
        List<SanPham> listsp = new ArrayList<>();
        conn = con.getConnect();
        Statement sm;
        try {
            sm = conn.createStatement();
            ResultSet rs = sm.executeQuery("SELECT * FROM `sanpham`");
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6));
                listsp.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.close();
        return listsp;
    }

    public Vector getSanPhamByID(String id) {
        Vector kq = new Vector();
        conn = con.getConnect();
        try {
            pre2 = conn.prepareStatement("SELECT `MaSP`, `TenSP`, `SoLuong`, `Gia`, `Img`, `MaLoaiSP`  FROM `sanpham` WHERE `MaSP`=?");
            pre2.setString(1, id);
            ResultSet rs = pre2.executeQuery();
            while (rs.next()) {
                kq.add(rs.getString(1));
                kq.add(rs.getString(2));
                kq.add(rs.getInt(3));
                kq.add(rs.getInt(4));
                kq.add(rs.getString(5));
                kq.add(rs.getString(6));
            }
            rs.close();
            pre2.close();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    public int updateSanPham(SanPham sanpham) throws SQLException {
        conn = new ConnectDB().getConnect();
        try {
            pre3 = conn.prepareStatement("UPDATE `sanpham` SET `TenSP` = ?, `SoLuong` = ?, `Gia` = ? WHERE `sanpham`.`MaSP` = ?;");
            pre3.setString(1, sanpham.getTen());
            pre3.setInt(2, sanpham.getSoluong());
            pre3.setInt(3, sanpham.getGia());
            pre3.setString(4, sanpham.getMaSP());
            if (pre3.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return -1;
    }

    public int delSanPham(String maSP) {
        conn = new ConnectDB().getConnect();
        try {
            pre4 = conn.prepareStatement("DELETE FROM `sanpham` WHERE `MaSP`=?");
            pre4.setString(1, maSP);
            if (pre4.executeUpdate() != 0) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

//    public static void main(String[] args) throws ParseException, SQLException {
//        SanPhamDAO spDAO = new SanPhamDAO();
//        SanPham sp = new SanPham("SP01", "R1", 5, 320000000, "SP01_anh.jpg", "a");
//        System.out.println(spDAO.addSanPham(sp));
//    }
}
