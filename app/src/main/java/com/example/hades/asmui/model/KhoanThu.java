package com.example.hades.asmui.model;

public class KhoanThu {
    public int _id;
    public  String ngaythu;
    public  String tienthu;
    public String noidungthu;
    public int _idloaithu;

    public KhoanThu(int _id, String ngaythu, String tienthu, String noidungthu, int _idloaithu) {
        this._id = _id;
        this.ngaythu = ngaythu;
        this.tienthu = tienthu;
        this.noidungthu = noidungthu;
        this._idloaithu = _idloaithu;
    }

    public KhoanThu(String ngaythu, String tienthu, String noidungthu, int _idloaithu) {
        this.ngaythu = ngaythu;
        this.tienthu = tienthu;
        this.noidungthu = noidungthu;
        this._idloaithu = _idloaithu;
    }
}
