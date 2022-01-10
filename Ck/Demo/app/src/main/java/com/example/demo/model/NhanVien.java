package com.example.demo.model;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private String maNV;
    private String tenNV;
    private boolean isNam;
    private String phongBan;

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public boolean isNam() {
        return isNam;
    }

    public void setNam(boolean nam) {
        this.isNam = nam;
    }

    public String getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }

    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, boolean isNam, String phongBan) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.isNam = isNam;
        this.phongBan = phongBan;
    }

    @Override
    public String toString() {
        return maNV + tenNV + (isNam == true ? "Nam" : "Nữ") + phongBan;
    }
}
