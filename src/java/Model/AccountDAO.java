/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Common.ConnectDB;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tgdd
 */
public class AccountDAO {

    Connection conn = null;
    PreparedStatement pre1, pre2;
    ConnectDB con = new ConnectDB();

    public int addTaiKhoan(Account tk) {
        conn = new ConnectDB().getConnect();
        try {
            pre1 = (PreparedStatement) conn.prepareStatement("INSERT INTO `taikhoan` (`Username`, `Password`, `name`, `diachi`, `sodienthoai`, `ChucVu`) VALUES (?, ?, ?, ?, ?, ?);");
            pre1.setString(1, tk.getUser());
            pre1.setString(2, tk.getPass());
            pre1.setString(3, tk.getName());
            pre1.setString(4, tk.getDc());
            pre1.setInt(5, tk.getSdt());
            pre1.setString(6, tk.getChucVu());
            if (pre1.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public Account login(String user, String pass) {
        Account ac = null;
        conn = con.getConnect();
        try {
            pre2 = (PreparedStatement) conn.prepareStatement("SELECT * FROM `taikhoan` WHERE `Username` = ? AND `Password` = ?");
            pre2.setString(1, user);
            pre2.setString(2, pass);
            ResultSet rs = pre2.executeQuery();
            if (rs.next()) {
                ac = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6));
            } else {
                ac = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ac;
    }

    public static void main(String[] args) {
        AccountDAO ac = new AccountDAO();
        Account tk = new Account("chuthai", "0000", "Thái", "Kon Tum", 01655365213, "nguoidung");
        System.out.println(ac.login("admin", "admin"));
    }
}
