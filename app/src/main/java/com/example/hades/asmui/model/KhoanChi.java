package com.example.hades.asmui.model;

public class KhoanChi {
    public int _id;
    public String ngaychi;
    public String tienchi;
    public String noidungchi;
    public int _idloaichi;

    public KhoanChi(int _id, String ngaychi, String tienchi, String noidungchi, int _idloaichi) {
        this._id = _id;
        this.ngaychi = ngaychi;
        this.tienchi = tienchi;
        this.noidungchi = noidungchi;
        this._idloaichi = _idloaichi;
    }

    public KhoanChi(String ngaychi, String tienchi, String noidungchi, int _idloaichi) {
        this.ngaychi = ngaychi;
        this.tienchi = tienchi;
        this.noidungchi = noidungchi;
        this._idloaichi = _idloaichi;
    }
}
