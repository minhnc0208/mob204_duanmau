package com.example.demo_ps08611_asm_androidcoban.model;

import java.util.Date;

public class HoaDonModel {
    private String maHoaDon;
    private Date ngayMua;

    public HoaDonModel() {
    }

    public HoaDonModel(String maHoaDon, Date ngayMua) {
        this.maHoaDon = maHoaDon;
        this.ngayMua = ngayMua;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }
}
