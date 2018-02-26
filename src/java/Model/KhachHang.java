/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author tgdd
 */
public class KhachHang {
    private String idKH;
    private String TenKH;
    private String Email;
    private int SDT;
    private String DiaChi;

    public KhachHang() {
    }

    public KhachHang(String idKH, String TenKH, String Email, int SDT, String DiaChi) {
        this.idKH = idKH;
        this.TenKH = TenKH;
        this.Email = Email;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
    }

    public String getIdKH() {
        return idKH;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }
    
}
