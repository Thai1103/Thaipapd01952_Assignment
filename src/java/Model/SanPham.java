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
public class SanPham {
    private String maSP;
    private String ten;
    private int soluong;
    private int gia;
    private String img;
    private String MaLoaiSP;

    public SanPham() {
    }

    public SanPham(String maSP, String ten, int soluong, int gia, String img, String MaLoaiSP) {
        this.maSP = maSP;
        this.ten = ten;
        this.soluong = soluong;
        this.gia = gia;
        this.img = img;
        this.MaLoaiSP = MaLoaiSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMaLoaiSP() {
        return MaLoaiSP;
    }

    public void setMaLoaiSP(String MaLoaiSP) {
        this.MaLoaiSP = MaLoaiSP;
    }


}
