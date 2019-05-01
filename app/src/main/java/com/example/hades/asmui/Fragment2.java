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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hades.asmui.Adapter.AdapterKhoanChi;
import com.example.hades.asmui.Adapter.AdapterLoaichi;
import com.example.hades.asmui.Adapter.KhoangChiSpinnerAdapter;
import com.example.hades.asmui.Adapter.KhoangThuSpinnerLoaiThuAdapter;
import com.example.hades.asmui.model.KhoanChi;
import com.example.hades.asmui.model.LoaiChi;
import com.example.hades.asmui.model.LoaiThu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment2 extends Fragment {
    ViewPager pager1;
    TabLayout tab1;
    FloatingActionButton flbt;
    Dialog dialog;
    DatePickerDialog.OnDateSetListener datepick;
    Button btd;
    ListView lv,lv1;
    Spinner spnloaichi;
    TextView tvdate;
    ArrayList<LoaiChi> dslc=new ArrayList<LoaiChi>();
    ArrayList<KhoanChi> dskc=new ArrayList<KhoanChi>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment2_layout,container,false);
        pager1= view.findViewById(R.id.viewpager1);
        tab1=view.findViewById(R.id.tablayout1);
        MyFragmentAdapter adapter=new MyFragmentAdapter(getChildFragmentManager());
        pager1.setAdapter(adapter);
        lv=view.findViewById(R.id.listviewloai);
        lv1=view.findViewById(R.id.listviewkhoan);
        tab1.addTab(tab1.newTab().setText("Khoản chi"));
        tab1.addTab(tab1.newTab().setText("Loại chi"));
        flbt=view.findViewById(R.id.fb);
        Timer timer=new Timer();
        timer.schedule(new autoSwipe(),1);
        pager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab1));
        tab1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab1) {
                pager1.setCurrentItem(tab1.getPosition());
                flbt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(pager1.getCurrentItem()==0){
                            Database database=new Database(getActivity());
                            dialog=new Dialog(getActivity());
                            dialog.setContentView(R.layout.dialog_khoan);
                            dialog.show();
                            spnloaichi=dialog.findViewById(R.id.spnLoai);
                            dslc=database.showloaichi();
                            KhoangChiSpinnerAdapter adapter1=new KhoangChiSpinnerAdapter(getActivity(),dslc);
                            spnloaichi.setAdapter(adapter1);
                            tvdate=dialog.findViewById(R.id.tvNgay);
                            Button bthuy=dialog.findViewById(R.id.bthuy);
                            Button btthem=dialog.findViewById(R.id.btthem);
                            btd=dialog.findViewById(R.id.btchonngay);
                            btd.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Calendar calendar=Calendar.getInstance();
                                    int nam=calendar.get(Calendar.YEAR);
                                    int thang=calendar.get(Calendar.MONTH);
                                    int ngay=calendar.get(Calendar.DAY_OF_MONTH);
                                    DatePickerDialog dpd=new DatePickerDialog(getActivity(),
                                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                                            datepick,nam,thang,ngay);
                                    dpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    dpd.show();
                                }
                            });
                            datepick=new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    month=month+1;
                                    String date=dayOfMonth+"/"+month+"/"+year;
                                    tvdate.setText(date);
                                }
                            };
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
                                    TextView tvngay;
                                    EditText etTien,etNoidung;
                                    tvngay=dialog.findViewById(R.id.tvNgay);
                                    etTien=dialog.findViewById(R.id.etTien);
                                    etNoidung=dialog.findViewById(R.id.etNoidung);
                                    String ngay=tvngay.getText().toString();
                                    String tien=etTien.getText().toString();
                                    String noidung=etNoidung.getText().toString();
                                    int spn=spnloaichi.getSelectedItemPosition();
                                    int idloaichi= dslc.get(spn)._id;
                                    if(tvngay.getText().toString().equals("Thời gian")
                                            || etTien.getText().toString().equals("")
                                            ||etNoidung.getText().toString().equals("")){
                                        Toast.makeText(getActivity(), "Không thành công", Toast.LENGTH_SHORT).show();
                                    }else {
                                        process();
                                        KhoanChi khoanChi = new KhoanChi(ngay, tien, noidung, idloaichi);
                                        database.addKhoanchi(khoanChi);
                                        dialog.dismiss();
                                        capnhatlistviewkhoanchi();
                                    }
                                }
                            });

                        }
                        if (pager1.getCurrentItem()==1){
                            dialog=new Dialog(getActivity());
                            dialog.setTitle("Loai chi");
                            dialog.setContentView(R.layout.dialog_loai);
                            dialog.show();
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
                                    Database database=new Database(getActivity());
                                    EditText etLoai;
                                    etLoai=dialog.findViewById(R.id.etLoai);
                                    String tenloaichi = etLoai.getText().toString();
                                    if(etLoai.getText().toString().equals("")){
                                        Toast.makeText(getActivity(), "Không thành công", Toast.LENGTH_SHORT).show();
                                    }else {
                                        process();
                                        LoaiChi loaiChi = new LoaiChi(tenloaichi);
                                        database.addLoaichi(loaiChi);
                                        dialog.dismiss();
                                        capnhatlistviewloaichi();
                                    }
                                }
                            });
                        }
                    }
                });
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab1) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab1) {

            }
        });
        return view;//super.onCreateView(inflater, container, savedInstanceState);
    }
    public void capnhatlistviewloaichi(){
        Database database=new Database(getActivity());
        lv=getView().findViewById(R.id.listviewloai);
        dslc=database.showloaichi();
        AdapterLoaichi adapterLoaichi=new AdapterLoaichi(dslc,getActivity());
        lv.setAdapter(adapterLoaichi);


    }
    public  void  capnhatlistviewkhoanchi(){
        Database database=new Database(getActivity());
        lv1=getView().findViewById(R.id.listviewkhoan);
        dskc=database.showkhoanchi();
        AdapterKhoanChi adapterKhoanChi=new AdapterKhoanChi(dskc,getActivity());
        lv1.setAdapter(adapterKhoanChi);
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
                    fragment=new ListViewKhoanChiFragment();
                    break;
                case 1:
                    fragment=new ListViewLoaiChiFragment();
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
                    if(pager1.getCurrentItem()==0){
                        pager1.setCurrentItem(1);
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