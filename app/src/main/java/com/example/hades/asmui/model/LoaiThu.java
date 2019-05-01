package com.example.hades.asmui.model;

public class LoaiThu {
    public int _id;
    public String tenloaithu;

    public LoaiThu(int _id, String tenloaithu) {
        this._id = _id;
        this.tenloaithu = tenloaithu;
    }

    public LoaiThu(String tenloaithu) {
        this.tenloaithu = tenloaithu;
    }
}
