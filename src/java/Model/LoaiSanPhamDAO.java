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
public class LoaiSanPhamDAO {

    Connection conn = null;
    PreparedStatement pre1, pre2, pre3, pre4;
    ConnectDB con = new ConnectDB();

    public int addLoaiSanPham(LoaiSanPham LoaiSP) {
        conn = con.getConnect();
        try {
            pre1 = conn.prepareStatement("INSERT INTO `loaisanpham` (`MaLoaiSP`, `TenLoaiSP`, `SoLuong`) VALUES (?, ?, ?);");
            pre1.setString(1, LoaiSP.getMaLoaiSP());
            pre1.setString(2, LoaiSP.getTenLoaiSP());
            pre1.setInt(3, LoaiSP.getSoLuong());
            if (pre1.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public List<LoaiSanPham> getAllLoaiSanPham() throws ParseException, SQLException {
        List<LoaiSanPham> listsp = new ArrayList<>();
        conn = con.getConnect();
        Statement sm;
        try {
            sm = conn.createStatement();
            ResultSet rs = sm.executeQuery("SELECT * FROM `loaisanpham`");
            while (rs.next()) {
                LoaiSanPham sp = new LoaiSanPham(rs.getString(1), rs.getString(2), rs.getInt(3));
                listsp.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.close();
        return listsp;
    }

    public Vector getLoaiSanPhamByID(String id) {
        Vector kq = new Vector();
        conn = con.getConnect();
        try {
            pre2 = conn.prepareStatement("SELECT `MaLoaiSP`, `TenLoaiSP`, `SoLuong`  FROM `loaisanpham` WHERE `MaLoaiSP`=?");
            pre2.setString(1, id);
            ResultSet rs = pre2.executeQuery();
            while (rs.next()) {
                kq.add(rs.getString(1));
                kq.add(rs.getString(2));
                kq.add(rs.getInt(3));
            }
            rs.close();
            pre2.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    public int updateLoaiSanPham(LoaiSanPham LoaiSP) throws SQLException {
        conn = new ConnectDB().getConnect();
        try {
            pre3 = conn.prepareStatement("UPDATE `loaisanpham` SET `TenLoaiSP` = ?, `SoLuong` = ? WHERE `loaisanpham`.`MaLoaiSP` = ?;");
            pre3.setString(1, LoaiSP.getTenLoaiSP());
            pre3.setInt(2, LoaiSP.getSoLuong());
            pre3.setString(3, LoaiSP.getMaLoaiSP());
            if (pre3.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return -1;
    }

    public int delLoaiSanPham(String maloaiSP) {
        conn = new ConnectDB().getConnect();
        try {
            pre4 = conn.prepareStatement("DELETE FROM `loaisanpham` WHERE `MaLoaiSP`=?");
            pre4.setString(1, maloaiSP);
            if (pre4.executeUpdate() != 0) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

//    public static void main(String[] args) throws ParseException, SQLException {
//        SanPhamDAO spDAO = new SanPhamDAO();
//        SanPham sp = new SanPham("SP01", "R1", 5, 320000000, "SP01_anh.jpg");
//        System.out.println(spDAO.updateSanPham(sp));
//    }
}
