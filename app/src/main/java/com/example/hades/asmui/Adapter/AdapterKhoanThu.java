package com.example.hades.asmui.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hades.asmui.R;
import com.example.hades.asmui.model.KhoanThu;

import java.util.ArrayList;

public class AdapterKhoanThu extends BaseAdapter {
    ArrayList<KhoanThu> dskt=new ArrayList<KhoanThu>();
    Context c;

    public AdapterKhoanThu(ArrayList<KhoanThu> dskt, Context c) {
        this.dskt = dskt;
        this.c = c;
    }

    @Override
    public int getCount() {
        return dskt.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //gan layout
        LayoutInflater inflater=((Activity)c).getLayoutInflater();
        convertView=inflater.inflate(R.layout.view_holder_khoan,null);
        TextView tv_ngay=convertView.findViewById(R.id.textNgay);
        TextView tv_makhoan=convertView.findViewById(R.id.textMakhoan);
        TextView tv_tien=convertView.findViewById(R.id.textTien);
        TextView tv_maloai=convertView.findViewById(R.id.textLoai);
        TextView tv_noidung=convertView.findViewById(R.id.textNoidung);
        KhoanThu khoanThu=dskt.get(position);
        tv_ngay.setText(khoanThu.ngaythu);
        tv_makhoan.setText("Mã khoản: "+khoanThu._id);
        tv_tien.setText("Tiền thu: "+khoanThu.tienthu);
        tv_maloai.setText("Mã loại: "+khoanThu._idloaithu);
        tv_noidung.setText("Nội dung: \n"+khoanThu.noidungthu);
        return convertView;
    }
}
