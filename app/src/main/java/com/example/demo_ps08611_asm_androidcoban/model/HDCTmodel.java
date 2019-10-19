package com.example.demo_ps08611_asm_androidcoban.model;

public class HDCTmodel {
    private int maHDCT;
    private HoaDonModel hoaDon;
    private Bookmodel sach;
    private int soLuongMua;

    public HDCTmodel() {
    }

    public HDCTmodel(int maHDCT, HoaDonModel hoaDon, Bookmodel sach, int soLuongMua) {
        this.maHDCT = maHDCT;
        this.hoaDon = hoaDon;
        this.sach = sach;
        this.soLuongMua = soLuongMua;
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public HoaDonModel getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDonModel hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Bookmodel getSach() {
        return sach;
    }

    public void setSach(Bookmodel sach) {
        this.sach = sach;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" + "maHDCT=" + maHDCT + ", hoaDon=" + hoaDon.toString() + ", sach=" + sach.toString() + ", soLuongMua=" + soLuongMua + '}';
    }
}
