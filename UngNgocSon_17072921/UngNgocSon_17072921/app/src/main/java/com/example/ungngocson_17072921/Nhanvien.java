package com.example.ungngocson_17072921;

public class Nhanvien {
    private int maso;
    private String tennv;
    private String gioitinh;
    private String phongban;

    public Nhanvien() {
        this.maso = 0;
        this.tennv = null;
        this.gioitinh = null;
        this.phongban = null;
    }

    public Nhanvien(int maso, String tennv, String gioitinh, String phongban) {
        this.maso = maso;
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.phongban = phongban;
    }

    public int getMaso() {
        return maso;
    }

    public void setMaso(int maso) {
        this.maso = maso;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getPhongban() {
        return phongban;
    }

    public void setPhongban(String phongban) {
        this.phongban = phongban;
    }

}
