package com.example.hades.asmui.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.hades.asmui.R;
import com.example.hades.asmui.model.LoaiChi;
import com.example.hades.asmui.model.LoaiThu;

import java.util.ArrayList;

public class KhoangChiSpinnerAdapter extends BaseAdapter {
    ArrayList<LoaiChi> dslc=new ArrayList<LoaiChi>();
    Context c;
    public KhoangChiSpinnerAdapter(Context c ,ArrayList<LoaiChi> dslc){
        this.c=c;
        this.dslc=dslc;
    }

    @Override
    public int getCount() {
        return dslc.size();
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
        LoaiChi lc= dslc.get(i);
        tv_tenloai.setText(lc.tenloaichi);


        return view;
    }
}
