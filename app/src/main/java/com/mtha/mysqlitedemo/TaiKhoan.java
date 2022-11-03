package com.mtha.mysqlitedemo;

import java.io.Serializable;

public class TaiKhoan implements Serializable {
    int id;
    String ten;
    String dienThoai;
    String email;

    public TaiKhoan(String ten, String dienThoai, String email) {
        this.ten = ten;
        this.dienThoai = dienThoai;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "id=" + id +
                ", ten='" + ten + '\'' +
                ", dienThoai='" + dienThoai + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public TaiKhoan(int id, String ten, String dienThoai, String email) {
        this.id = id;
        this.ten = ten;
        this.dienThoai = dienThoai;
        this.email = email;
    }
}
