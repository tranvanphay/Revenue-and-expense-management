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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hades.asmui.Database;
import com.example.hades.asmui.MainActivity;
import com.example.hades.asmui.R;
import com.example.hades.asmui.model.LoaiThu;

import java.util.ArrayList;

public class AdapterLoaithu extends BaseAdapter {
    ArrayList<LoaiThu> dslt=new ArrayList<LoaiThu>();
    Context c;
    ListView lv1;
    Dialog dialog;
    EditText etLoai;

    public AdapterLoaithu(ArrayList<LoaiThu> dslt, Context c) {
        this.dslt = dslt;
        this.c = c;
    }

    @Override
    public int getCount() {
        return dslt.size();
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
        //Gan layout
        LayoutInflater inf=((Activity)c).getLayoutInflater();
        convertView=inf.inflate(R.layout.view_holder_loai,null);
        TextView tv_tenloai=convertView.findViewById(R.id.textTenLoai);
        ImageView img_edit=convertView.findViewById(R.id.imageedit);
        ImageView img_delete=convertView.findViewById(R.id.imagedelete);
        lv1=convertView.findViewById(R.id.listviewloai);
        final LoaiThu loaiThu=dslt.get(position);
        tv_tenloai.setText("ID: "+loaiThu._id + "\n" +"Loại: "+loaiThu.tenloaithu);
        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiThu lt=dslt.get(position);
                dialog=new Dialog(c);
                dialog.setContentView(R.layout.dialog_loai);
                dialog.show();
                etLoai=dialog.findViewById(R.id.etLoai);
                etLoai.setText(lt.tenloaithu);
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
                        String tenloaithu=etLoai.getText().toString();
                        LoaiThu loaithu=new LoaiThu(tenloaithu);
                        Database database=new Database(c);
                        database.updateloaithu(loaithu);
                        ((MainActivity)c).capnhatlt();
                        dialog.dismiss();

                    }
                });
                Toast.makeText(c, "edit ID"+loaiThu._id+" nè", Toast.LENGTH_SHORT).show();
            }
        });
        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(c);
                builder.setTitle("Chú ý");
                builder.setIcon(R.drawable.alert);
                builder.setMessage("Bạn muốn xóa "+loaiThu.tenloaithu+"?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoaiThu loaiThu1=dslt.get(position);
                        int id=loaiThu1._id;
                        Database database=new Database(c);
                        database.deleteloaithu(id);
                        ((MainActivity)c).capnhatlt();
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

//                Toast.makeText(c, "Xóa ID"+loaiThu._id+" nè", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

}
