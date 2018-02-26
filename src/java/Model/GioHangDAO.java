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
import java.sql.ResultSetMetaData;
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
public class GioHangDAO {

    Connection conn = null;
    PreparedStatement pre1, pre2, pre3, pre4, pre5, pre6, pre7;
    ConnectDB con = new ConnectDB();

//    public Vector getGia(String MaSP) {
//        Vector kq = new Vector();
//        conn = new ConnectDB().getConnect();
//        try {
//            pre4 = conn.prepareStatement("SELECT `Gia` FROM `sanpham` WHERE `MaSP` = ?");
//            pre4.setString(1, MaSP);
//            ResultSet rs = pre4.executeQuery();
//            while (rs.next()) {
//                kq.add(rs.getString(1));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(GioHangDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return kq;
//    }
    public int addGioHang1(SanPham sp) {
        conn = new ConnectDB().getConnect();
        try {
            pre7 = conn.prepareStatement("SELECT `MaSP`, `TenSP`, `SoLuong`, `Gia` FROM `sanpham` WHERE `MaSP`=?");
            pre7.setString(1, sp.getMaSP());
            ResultSet rs = pre7.executeQuery();
            if (rs.next()) {
                pre6 = conn.prepareStatement("UPDATE `sanpham` SET `SoLuong` = ? WHERE `sanpham`.`MaSP` = ?;");
                pre6.setInt(1, (rs.getInt(3) - 1));
                pre6.setString(2, sp.getMaSP());
                pre6.executeUpdate();
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GioHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int addGioHang(GioHang gh) {
        conn = new ConnectDB().getConnect();
        try {
            pre5 = conn.prepareStatement("SELECT * FROM `giohang` WHERE `Username`=? AND `MaSP`=?");
            pre5.setString(1, gh.getUsername());
            pre5.setString(2, gh.getMaSPgh());
            ResultSet rs = pre5.executeQuery();
            if (rs.next()) {
                pre6 = conn.prepareStatement("UPDATE `giohang` SET `SoLuong`=? WHERE `Username`=? AND `MaSP`=?");
                pre6.setInt(1, rs.getInt(3)+ 1);
                pre6.setString(2, gh.getUsername());
                pre6.setString(3, gh.getMaSPgh());
                pre6.executeUpdate();
                return 2;
            } else {
                pre1 = conn.prepareStatement("INSERT INTO `giohang` (`Username`, `MaSP`, `SoLuong`) VALUES (?, ?, ?);");
                pre1.setString(1, gh.getUsername());
                pre1.setString(2, gh.getMaSPgh());
                pre1.setInt(3, gh.getSoLuong());
                pre1.executeUpdate();

                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GioHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    

    public Vector getThanhToan(String user) {
        conn = new ConnectDB().getConnect();
        Vector kq = new Vector();
        try {
            pre4 = conn.prepareStatement("SELECT taikhoan.name, taikhoan.diachi, taikhoan.sodienthoai, sanpham.TenSP, sanpham.SoLuong, sanpham.Gia FROM `giohang` INNER JOIN taikhoan ON taikhoan.Username=giohang.Username INNER JOIN sanpham ON sanpham.MaSP=giohang.MaSP WHERE giohang.Username=?");
            pre4.setString(1, user);
            ResultSet rs = pre4.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                Vector t = new Vector();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    t.add(rs.getString(i));
                }
                kq.add(t);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(GioHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    public Vector getAllGioHang(String user) {
        conn = new ConnectDB().getConnect();
        Vector kq = new Vector();
        try {
            pre4 = conn.prepareStatement("SELECT `Username`, giohang.`MaSP`, `TenSP`, giohang.`SoLuong`, `Gia`, `Gia`*giohang.SoLuong AS TongTien FROM `giohang` INNER JOIN sanpham ON sanpham.MaSP=giohang.MaSP WHERE `Username`=?");
            pre4.setString(1, user);
            ResultSet rs = pre4.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                Vector t = new Vector();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    t.add(rs.getString(i));
                }
                kq.add(t);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(GioHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    public int delGioHang(String maSP) {
        conn = new ConnectDB().getConnect();
        try {
            pre3 = conn.prepareStatement("DELETE FROM `giohang` WHERE `MaSP` = ?");
            pre3.setString(1, maSP);
            if (pre3.executeUpdate() != 0) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GioHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public Vector getGioHangByID(String id) {
        Vector kq = new Vector();
        conn = con.getConnect();
        try {
            pre2 = conn.prepareStatement("SELECT * FROM `giohang` WHERE `MaSP`=?");
            pre2.setString(1, id);
            ResultSet rs = pre2.executeQuery();
            while (rs.next()) {
                kq.add(rs.getString(1));
                kq.add(rs.getString(2));
            }
            rs.close();
            pre2.close();
        } catch (SQLException ex) {
            Logger.getLogger(GioHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
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

    public int thanhToan() {
        conn = new ConnectDB().getConnect();
        try {
            pre3 = conn.prepareStatement("DELETE FROM `giohang`");
            if (pre3.executeUpdate() != 0) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GioHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

//    public static void main(String[] args) {
//        GioHangDAO ghDAO = new GioHangDAO();
//        GioHang gh = new GioHang("AnhThai", "SP07", 1);
//        System.out.println(ghDAO.addGioHang(gh));
//        System.out.println(ghDAO.getAllGioHang("AnhThai"));
//    }
}
