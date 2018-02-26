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
public class NhanVienDAO {

    Connection conn = null;
    PreparedStatement pre1, pre2, pre3, pre4;
    ConnectDB con = new ConnectDB();

    public int addNhanVien(NhanVien nhanvien) {
        conn = new ConnectDB().getConnect();
        try {
            pre1 = conn.prepareStatement("INSERT INTO `nhanvien` (`MaNV`, `TenNV`, `DiaChi`, `Email`, `SoDienThoai`) VALUES (?, ?, ?, ?, ?);");
            pre1.setString(1, nhanvien.getMaNV());
            pre1.setString(2, nhanvien.getTenNV());
            pre1.setString(3, nhanvien.getDiaChi());
            pre1.setString(4, nhanvien.getEmail());
             pre1.setInt(5, nhanvien.getSoDienThoai());
            if (pre1.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public List<NhanVien> getAllNhanVien() throws ParseException, SQLException {
        List<NhanVien> listsp = new ArrayList<>();
        conn = con.getConnect();
        Statement sm;
        try {
            sm = conn.createStatement();
            ResultSet rs = sm.executeQuery("SELECT * FROM `nhanvien`");
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                listsp.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.close();
        return listsp;
    }

    public Vector getNhanVienD(String id) {
        Vector kq = new Vector();
        conn = con.getConnect();
        try {
            pre2 = conn.prepareStatement("SELECT * FROM `nhanvien` WHERE `MaNV`=?");
            pre2.setString(1, id);
            ResultSet rs = pre2.executeQuery();
            while (rs.next()) {
                kq.add(rs.getString(1));
                kq.add(rs.getString(2));
                kq.add(rs.getString(3));
                kq.add(rs.getString(4));
                kq.add(rs.getInt(5));
            }
            rs.close();
            pre2.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    public int updateNhanVien(NhanVien nv) throws SQLException {
        conn = new ConnectDB().getConnect();
        try {
            pre3 = conn.prepareStatement("UPDATE `nhanvien` SET `TenNV` = ?, `DiaChi` = ?, `Email` = ?, `SoDienThoai` = ? WHERE `nhanvien`.`MaNV` = ?;");
            pre3.setString(1, nv.getTenNV());
            pre3.setString(2, nv.getDiaChi());
            pre3.setString(3, nv.getEmail());
            pre3.setInt(4, nv.getSoDienThoai());
            pre3.setString(5, nv.getMaNV());
            if (pre3.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return -1;
    }

    public int delNhanVien(String MaNV) {
        conn = new ConnectDB().getConnect();
        try {
            pre4 = conn.prepareStatement("DELETE FROM `nhanvien` WHERE `nhanvien`.`MaNV` = ?");
            pre4.setString(1, MaNV);
            if (pre4.executeUpdate() != 0) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }
}
