package com.example.hades.asmui.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hades.asmui.Database;
import com.example.hades.asmui.MainActivity;
import com.example.hades.asmui.R;
import com.example.hades.asmui.model.LoaiChi;
import com.example.hades.asmui.model.LoaiThu;

import java.util.ArrayList;

public class AdapterLoaichi extends BaseAdapter {
    ArrayList<LoaiChi> dslc=new ArrayList<LoaiChi>();
    Context c;
    Dialog dialog;
    EditText etLoai;

    public AdapterLoaichi(ArrayList<LoaiChi> dslc, Context c) {
        this.dslc = dslc;
        this.c = c;
    }

    @Override
    public int getCount() {
        return dslc.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=((Activity)c).getLayoutInflater();
        convertView=inflater.inflate(R.layout.view_holder_loaichi,null);
        TextView tv_tenloai=convertView.findViewById(R.id.textTenLoai1);
        ImageView img_edit=convertView.findViewById(R.id.imageedit1);
        ImageView img_delete=convertView.findViewById(R.id.imagedelete1);
        final LoaiChi loaiChi=dslc.get(position);
        tv_tenloai.setText("ID: "+loaiChi._id + "\n" +"Loại: "+loaiChi.tenloaichi);
        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiChi lc=dslc.get(position);
                dialog=new Dialog(c);
                dialog.setContentView(R.layout.dialog_loai);
                dialog.show();
                etLoai=dialog.findViewById(R.id.etLoai);
                etLoai.setText(lc.tenloaichi);
                Button bt6=dialog.findViewById(R.id.button6);
                bt6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                Button bt5=dialog.findViewById(R.id.button5);
                bt5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenloaichi=etLoai.getText().toString();
                        LoaiChi loaichi=new LoaiChi(tenloaichi);
                        Database database=new Database(c);
                        database.updateloaichi(loaichi);
                        ((MainActivity)c).capnhatlc();
                        dialog.dismiss();

                    }
                });
                Toast.makeText(c, "edit ID"+loaiChi._id+" nè", Toast.LENGTH_SHORT).show();
            }
        });
        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(c);
                builder.setTitle("Chú ý");
                builder.setIcon(R.drawable.alert);
                builder.setMessage("Bạn muốn xóa "+loaiChi.tenloaichi+"?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoaiChi loaiChi1=dslc.get(position);
                        int id=loaiChi1._id;
                        Database database=new Database(c);
                        database.deleteloaichi(id);
                        ((MainActivity)c).capnhatlc();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
        return convertView;
    }
}
