package com.example.hades.asmui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hades.asmui.Adapter.AdapterKhoanChi;
import com.example.hades.asmui.Adapter.AdapterKhoanThu;
import com.example.hades.asmui.Adapter.AdapterLoaithu;
import com.example.hades.asmui.Adapter.KhoangThuSpinnerLoaiThuAdapter;
import com.example.hades.asmui.model.KhoanThu;
import com.example.hades.asmui.model.LoaiThu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment1 extends Fragment {
    ViewPager pager;
    TabLayout tab;
    FloatingActionButton flb;
    Dialog dialog;
    Button bt5, bt6, bthuy,btthem, btdate;
    TextView tv9,etNoidung;
    ListView lv,lv1;
    Spinner spnloaithu;
    ArrayList<KhoanThu> dskt=new ArrayList<>();
    ArrayList<LoaiThu> dslt=new ArrayList<>();
    private DatePickerDialog.OnDateSetListener datepicker;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1_layout,container,false);
        pager= view.findViewById(R.id.viewpager);
        tab=view.findViewById(R.id.tablayout);
        lv=view.findViewById(R.id.listviewloai);
        lv1=view.findViewById(R.id.listviewkhoan);
        Timer time=new Timer();
        time.schedule(new autoSwipe(),1);
        flb=view.findViewById(R.id.flb);
        final MyFragmentAdapter adapter=new MyFragmentAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);
        tab.addTab(tab.newTab().setText("Khoản thu"));
        tab.addTab(tab.newTab().setText("Loại thu"));
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
                flb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(pager.getCurrentItem()==0){
                            Database database=new Database(getActivity());
                            dialog=new Dialog(getActivity());
                            dialog.setContentView(R.layout.dialog_khoan);
                            dialog.show();
                            btdate=dialog.findViewById(R.id.btchonngay);
                            tv9=dialog.findViewById(R.id.tvNgay);
                            etNoidung=dialog.findViewById(R.id.etNoidung);
                            spnloaithu=dialog.findViewById(R.id.spnLoai);

                            dslt=database.showloaithu();
                            KhoangThuSpinnerLoaiThuAdapter adapter1=new KhoangThuSpinnerLoaiThuAdapter(getActivity(),dslt);
                            spnloaithu.setAdapter(adapter1);
                            btdate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Calendar cal= Calendar.getInstance();
                                    int year=cal.get(Calendar.YEAR);
                                    int month=cal.get(Calendar.MONTH);
                                    int day=cal.get(Calendar.DAY_OF_MONTH);

                                    DatePickerDialog dl=new DatePickerDialog(getActivity(),
                                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                                            datepicker,year,month,day);
                                    dl.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    dl.show();
                                }
                            });
                            datepicker=new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    month=month+1;
                                    String date= dayOfMonth+"/"+month+"/"+year;
                                    tv9.setText(date);
                                }
                            };
                            bthuy=dialog.findViewById(R.id.bthuy);
                            btthem=dialog.findViewById(R.id.btthem);
                            bthuy.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.cancel();
                                }
                            });
                            btthem.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Database database=new Database(getActivity());
                                    TextView tvNgay;
                                    EditText etTien,etNoidung;
                                    tvNgay=dialog.findViewById(R.id.tvNgay);
                                    etTien=dialog.findViewById(R.id.etTien);
                                    etNoidung=dialog.findViewById(R.id.etNoidung);
                                    String ngay=tvNgay.getText().toString();
                                    String tienthu=etTien.getText().toString();
                                    String noidung=etNoidung.getText().toString();
                                    int index= spnloaithu.getSelectedItemPosition();
                                    int idloaithu=dslt.get(index)._id;
                                    if(tvNgay.getText().toString().equals("Thời gian")
                                            ||etTien.getText().toString().equals("")
                                            ||etNoidung.getText().toString().equals("")){
                                        Toast.makeText(getActivity(), "Không thành công", Toast.LENGTH_SHORT).show();
                                    }else {

                                        KhoanThu khoanThu = new KhoanThu(ngay, tienthu, noidung, idloaithu);
                                        database.addKhoanthu(khoanThu);
                                        process();
                                        dialog.dismiss();
                                        capnhatlistviewkhoanthu();
                                    }
                                }
                            });

                        }
                        if (pager.getCurrentItem()==1){
                            dialog = new Dialog(getActivity());
                            dialog.setContentView(R.layout.dialog_loai);
                            dialog.show();
                            bt5=dialog.findViewById(R.id.button5);
                            bt6=dialog.findViewById(R.id.button6);
                            bt6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.cancel();
                                }
                            });
                            bt5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Database database=new Database(getActivity());
                                    EditText etLoai;
                                    etLoai=dialog.findViewById(R.id.etLoai);
                                    String tenloaithu= etLoai.getText().toString();
                                    if(etLoai.getText().toString().equalsIgnoreCase("")){
                                        Toast.makeText(getActivity(), "Không thành công", Toast.LENGTH_SHORT).show();
                                    }else{
                                    LoaiThu loaiThu=new LoaiThu(tenloaithu);
                                    database.addLoaithu(loaiThu);
                                    process();
                                    dialog.dismiss();
                                    capnhatlistviewloaithu();
                                    }
                                }
                            });
                        }
                    }
                });


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;//super.onCreateView(inflater, container, savedInstanceState);
    }
    public void capnhatlistviewloaithu(){
        Database database=new Database(getActivity());
        lv=getView().findViewById(R.id.listviewloai);
        dslt=database.showloaithu();
        AdapterLoaithu adapter=new AdapterLoaithu(dslt, getActivity());
        lv.setAdapter(adapter);

    }
    public void capnhatlistviewkhoanthu(){
        Database database=new Database(getActivity());
        lv1=getView().findViewById(R.id.listviewkhoan);
        dskt=database.showkhoanthu();
        AdapterKhoanThu adapterKhoanThu=new AdapterKhoanThu(dskt,getActivity());
        lv1.setAdapter(adapterKhoanThu);

    }

    class  MyFragmentAdapter extends FragmentStatePagerAdapter {

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position){
                case 0:
                    fragment=new ListViewKhoanThuFragment();
                    break;
                case 1:
                    fragment=new ListViewLoaiThuFragment();
                    break;
                default:
                    return null;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

    }
    public class autoSwipe extends TimerTask{

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(pager.getCurrentItem()==0){
                        pager.setCurrentItem(1);
                    }
                }
            });
        }
    }
    void process(){
        final ProgressDialog pg=ProgressDialog.show(getActivity(),"Đợi tí","Đang thêm vào Database...");
        CountDownTimer timer=new CountDownTimer(1000,500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                pg.dismiss();
            }
        }.start();
        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
    }
}

