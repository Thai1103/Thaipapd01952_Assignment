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
public class GioHang {
    private String Username;
    private String MaSPgh;
    private int SoLuong;

    public GioHang() {
    }

    public GioHang(String Username, String MaSPgh, int SoLuong) {
        this.Username = Username;
        this.MaSPgh = MaSPgh;
        this.SoLuong = SoLuong;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getMaSPgh() {
        return MaSPgh;
    }

    public void setMaSPgh(String MaSPgh) {
        this.MaSPgh = MaSPgh;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

}
