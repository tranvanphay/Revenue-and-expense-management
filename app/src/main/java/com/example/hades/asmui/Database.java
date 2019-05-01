package com.example.hades.asmui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hades.asmui.model.KhoanChi;
import com.example.hades.asmui.model.KhoanThu;
import com.example.hades.asmui.model.LoaiChi;
import com.example.hades.asmui.model.LoaiThu;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    //database
    public static final String tendatabase="quanlithuchi.db";
    //khoan thu
    public static final String khoanthu_table="khoanthu_table";

            public static final String idkhoanthu="id_khoanthu";
            public static final String ngaythu="ngaythu";
            public static final String tienthu="tienthu";
            public static final String noidungthu="noidung";
    //khoan chi
    public static final String khoanchi_table="khoanchi_table";

            public static final String idkhoanchi="id_khoanchi";
            public static final String ngaychi="ngaychi";
            public static final String tienchi="tienchi";
            public static final String noidungchi="noidung";
    //Loai thu
    public static final String loaithu_table="loaithu_table";

            public static final String idloaithu="id_loaithu";
            public static final String tenloaithu="tenloaithu";
    //Loai chi
    public static final String loaichi_table="loaichi_table";

            public static final String idloaichi="id_loaichi";
            public static final String tenloaichi="tenloaichi";

    public Database(Context context) {
        super(context, tendatabase, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+khoanthu_table+"("+idkhoanthu+" integer primary key autoincrement, "+ngaythu+" text, "+tienthu+" text,"+noidungthu+" text,"+idloaithu+" integer)" );
        db.execSQL("create table "+loaithu_table+"("+idloaithu+" integer primary key autoincrement, "+tenloaithu+" text)" );
        db.execSQL("create table "+khoanchi_table+"("+idkhoanchi+" integer primary key autoincrement, "+ngaychi+" text, "+tienchi+" text,"+noidungchi+" text,"+idloaichi+" integer)" );
        db.execSQL("create table "+loaichi_table+"("+idloaichi+" integer primary key autoincrement, "+tenloaichi+" text)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+khoanthu_table);
        db.execSQL("drop table if exists "+loaithu_table);
        db.execSQL("drop table if exists "+khoanchi_table);
        db.execSQL("drop table if exists "+loaichi_table);
        onCreate(db);
    }
//    public Database(Context context) {
//        super(context, "quanlithuchi", null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        /*
//        create table khoanthu(
//        _id integer primary key autoincrement,
//        ngaythu text,
//        tienthu integer,
//        noidungthu text
//        )
//        create table loaithu(
//        _id integer primary key autoincrement,
//        tenloaithu text
//        )
//        create table khoanchi(
//        _id integer primary key autoincrement,
//        ngaychi text,
//        tienchi integer,
//        noidung text
//        )
//        create table loaichi(
//        _id integer primary key autoincrement,
//        tenloaichi text
//        )
//        */
//        String khoanthu = "create table khoanthu" + "("
//               + "_id integer primary key autoincrement,"
//                + "ngaythu text,"
//                + "tienthu integer,"
//                + "noidungthu text,"
//                + "_idloaithu integer"
//                + ")" ;
//        String loaithu = "create table loaithu" +
//                "(" +
//                "_id integer primary key autoincrement," +
//                "tenloaithu text" +
//                ")" ;
//        String khoanchi = "create table khoanchi" + "(" +
//                "_id integer primary key autoincrement," +
//                "ngaychi text," +
//                "tienchi integer," +
//                "noidungchi text," +
//                "_idloaichi integer" +
//                ")" ;
//        String loaichi = "create table loaichi" + "(" +
//                "_id integer primary key autoincrement," +
//                "tenloaichi text" +
//                ")" ;
//        sqLiteDatabase.execSQL(khoanthu);
//        sqLiteDatabase.execSQL(loaithu);
//        sqLiteDatabase.execSQL(khoanchi);
//        sqLiteDatabase.execSQL(loaichi);
//
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        sqLiteDatabase.execSQL("drop table if exists khoanthu");
//        sqLiteDatabase.execSQL("drop table if exists loaichi");
//        sqLiteDatabase.execSQL("drop table if exists khoanchi");
//        sqLiteDatabase.execSQL("drop table if exists loaichi");
//        onCreate(sqLiteDatabase);
//    }

    //ADD khoanthu
    public void addKhoanthu(KhoanThu khoanThu){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ngaythu,khoanThu.ngaythu);
        values.put(tienthu,khoanThu.tienthu);
        values.put(noidungthu,khoanThu.noidungthu);
        values.put(idloaithu,khoanThu._idloaithu);
        sqLiteDatabase.insert(khoanthu_table,null,values);

    }
    //ADD loaithu
    public void addLoaithu(LoaiThu loaiThu){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(tenloaithu,loaiThu.tenloaithu);
        sqLiteDatabase.insert(loaithu_table,null,values);

    }
    //ADD khoanchi
    public void addKhoanchi(KhoanChi khoanChi){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ngaychi,khoanChi.ngaychi);
        values.put(tienchi,khoanChi.tienchi);
        values.put(noidungchi,khoanChi.noidungchi);
        values.put(idloaichi,khoanChi._idloaichi);
        sqLiteDatabase.insert(khoanchi_table,null,values);
    }
    //ADD loaichi
    public void addLoaichi(LoaiChi loaiChi){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(tenloaichi,loaiChi.tenloaichi);
        sqLiteDatabase.insert(loaichi_table,null,values);

    }
    //Show khoanthu
    public ArrayList<KhoanThu> showkhoanthu(){
        ArrayList<KhoanThu> dskt=new ArrayList<KhoanThu>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor c=sqLiteDatabase.query(khoanthu_table,null,null,null,null,null,null);
        if(c.moveToFirst()){
            do {
                int _id=c.getInt(0);
                String ngaythu=c.getString(1);
                String tienthu=c.getString(2);
                String noidungthu=c.getString(3);
                int _idloaithu=c.getInt(4);
                KhoanThu khoanThu=new KhoanThu(_id,ngaythu,tienthu,noidungthu,_idloaithu);
                dskt.add(khoanThu);
            }
            while (c.moveToNext());
        }
        return dskt;
    }
    //Show loaithu
    public ArrayList<LoaiThu> showloaithu(){
        ArrayList<LoaiThu> dslt=new ArrayList<LoaiThu>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor c=sqLiteDatabase.query(loaithu_table,null,null,null,null,null,null);
        if (c.moveToFirst()){
            do {
                int _id=c.getInt(0);
                String tenloaithu=c.getString(1);
                LoaiThu loaiThu=new LoaiThu(_id,tenloaithu);
                dslt.add(loaiThu);
            }
            while (c.moveToNext());
        }
        return dslt;
    }
    //Show khoanchi
    public ArrayList<KhoanChi> showkhoanchi(){
        ArrayList<KhoanChi> dskc=new ArrayList<KhoanChi>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor c=sqLiteDatabase.query(khoanchi_table,null,null,null,null,null,null);
        if (c.moveToFirst()){
            do {
                int _id=c.getInt(0);
                String ngaychi=c.getString(1);
                String tienchi=c.getString(2);
                String noidungchi=c.getString(3);
                int _idloaichi=c.getInt(4);
                KhoanChi khoanChi=new KhoanChi(_id,ngaychi,tienchi,noidungchi,_idloaichi);
                dskc.add(khoanChi);
            }
            while (c.moveToNext());
        }
        return dskc;
    }
    //showloaichi
    public ArrayList<LoaiChi> showloaichi(){
        ArrayList<LoaiChi> dslc=new ArrayList<LoaiChi>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor c=sqLiteDatabase.query(loaichi_table,null,null,null,null,null,null);
        if (c.moveToFirst()){
            do {
                int _id=c.getInt(0);
                String tenloaichi=c.getString(1);
                LoaiChi loaiChi=new LoaiChi(_id,tenloaichi);
                dslc.add(loaiChi);
            }
            while (c.moveToNext());
        }
        return dslc;
    }
    //delete loaithu
    public void deleteloaithu(int _id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(loaithu_table, idloaithu + " = ?", new String[] { String.valueOf(_id) });
        db.close();

    }
    //delete loaichi
    public void deleteloaichi(int _id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(loaichi_table, idloaichi + " = ?", new String[] { String.valueOf(_id) });
        db.close();
    }
    //update loaithu
    public void updateloaithu(LoaiThu loaiThu){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(tenloaithu,loaiThu.tenloaithu);
        db.update(loaithu_table,values,idloaithu+" = ?",new String[]{loaiThu._id+""});
    }
    public void updateloaichi(LoaiChi loaiChi){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(tenloaichi,loaiChi.tenloaichi);
        db.update(loaichi_table,values,idloaichi+" = ?",new String[]{loaiChi._id+""});
    }



}
