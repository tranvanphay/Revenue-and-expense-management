package com.example.hades.asmui.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hades.asmui.R;
import com.example.hades.asmui.model.KhoanChi;

import java.util.ArrayList;

public class AdapterKhoanChi extends BaseAdapter {
    ArrayList<KhoanChi> dskc=new ArrayList<KhoanChi>();
    Context c;

    public AdapterKhoanChi(ArrayList<KhoanChi> dskc, Context c) {
        this.dskc = dskc;
        this.c = c;
    }

    @Override
    public int getCount() {

        return dskc.size();
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
        //gawn layout
        LayoutInflater inflater=((Activity)c).getLayoutInflater();
        convertView=inflater.inflate(R.layout.view_holder_khoan,null);
        TextView tv_date=convertView.findViewById(R.id.textNgay);
        TextView tv_makhoanchi=convertView.findViewById(R.id.textMakhoan);
        TextView tv_tien=convertView.findViewById(R.id.textTien);
        TextView tv_loai=convertView.findViewById(R.id.textLoai);
        TextView tv_noidung=convertView.findViewById(R.id.textNoidung);
        KhoanChi khoanChi=dskc.get(position);
        tv_date.setText(khoanChi.ngaychi);
        tv_makhoanchi.setText("Mã khoản: "+khoanChi._id);
        tv_tien.setText("Tiền chi: "+ khoanChi.tienchi);
        tv_loai.setText("Mã loại: "+khoanChi._idloaichi);
        tv_noidung.setText("Nội dung: \n" );


        return convertView;
    }
}
