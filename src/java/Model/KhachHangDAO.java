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
public class KhachHangDAO {
       Connection conn = null;
    PreparedStatement pre1, pre2, pre3, pre4;
    ConnectDB con = new ConnectDB();

    public int addKhachHang(KhachHang kh) {
        conn = new ConnectDB().getConnect();
        try {
            pre1 = conn.prepareStatement("INSERT INTO `khachhang` (`IDKH`, `TenKH`, `Email`, `SDT`, `DiaChi`) VALUES (?, ?, ?, ?, ?);");
            pre1.setString(1, kh.getIdKH());
            pre1.setString(2, kh.getTenKH());
            pre1.setString(3, kh.getEmail());
            pre1.setInt(4, kh.getSDT());
             pre1.setString(5, kh.getDiaChi());
            if (pre1.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public List<KhachHang> getAllKhachHang() throws ParseException, SQLException {
        List<KhachHang> listsp = new ArrayList<>();
        conn = con.getConnect();
        Statement sm;
        try {
            sm = conn.createStatement();
            ResultSet rs = sm.executeQuery("SELECT * FROM `khachhang`");
            while (rs.next()) {
                KhachHang nv = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                listsp.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.close();
        return listsp;
    }

    public Vector getKhachHang(String id) {
        Vector kq = new Vector();
        conn = con.getConnect();
        try {
            pre2 = conn.prepareStatement("SELECT * FROM `khachhang` WHERE `IDKH`=?");
            pre2.setString(1, id);
            ResultSet rs = pre2.executeQuery();
            while (rs.next()) {
                kq.add(rs.getString(1));
                kq.add(rs.getString(2));
                kq.add(rs.getString(3));
                kq.add(rs.getInt(4));
                kq.add(rs.getString(5));
                
            }
            rs.close();
            pre2.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    public int updateKhachHang(KhachHang KH) throws SQLException {
        conn = new ConnectDB().getConnect();
        try {
            pre3 = conn.prepareStatement("UPDATE `khachhang` SET `TenKH` = ?, `Email` = ?, `SDT` = ?, `DiaChi` = ? WHERE `khachhang`.`IDKH` = ?;");
            pre3.setString(1, KH.getTenKH());
            pre3.setString(2, KH.getEmail());
            pre3.setInt(3, KH.getSDT());
            pre3.setString(4, KH.getDiaChi());
            pre3.setString(5, KH.getIdKH());
            if (pre3.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return -1;
    }

    public int delNhanVien(String IDKH) {
        conn = new ConnectDB().getConnect();
        try {
            pre4 = conn.prepareStatement("DELETE FROM `khachhang` WHERE `khachhang`.`IDKH` = ?");
            pre4.setString(1, IDKH);
            if (pre4.executeUpdate() != 0) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }
}
