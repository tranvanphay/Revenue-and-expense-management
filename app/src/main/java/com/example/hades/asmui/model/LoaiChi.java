package com.example.hades.asmui.model;

public class LoaiChi {
    public int _id;
    public String tenloaichi;

    public LoaiChi(int _id, String tenloaichi) {
        this._id = _id;
        this.tenloaichi = tenloaichi;
    }

    public LoaiChi(String tenloaichi) {
        this.tenloaichi = tenloaichi;
    }
}
