package com.example.hades.asmui.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.hades.asmui.R;
import com.example.hades.asmui.model.LoaiThu;

import java.util.ArrayList;

public class KhoangThuSpinnerLoaiThuAdapter extends BaseAdapter {
    ArrayList<LoaiThu> dslt=new ArrayList<LoaiThu>();
    Context c;
    public KhoangThuSpinnerLoaiThuAdapter(Context c ,ArrayList<LoaiThu> dslt){
        this.c=c;
        this.dslt=dslt;
    }

    @Override
    public int getCount() {
        return dslt.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //gan layout
        LayoutInflater inf=((Activity)c).getLayoutInflater();
        view=inf.inflate(R.layout.spinnerloai,null);
        TextView tv_tenloai=view.findViewById(R.id.tv_spinner);
        LoaiThu lt= dslt.get(i);
        tv_tenloai.setText( lt.tenloaithu);


        return view;
    }
}
