package com.example.hades.asmui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hades.asmui.Adapter.AdapterKhoanThu;
import com.example.hades.asmui.Adapter.AdapterLoaichi;
import com.example.hades.asmui.Adapter.AdapterLoaithu;
import com.example.hades.asmui.model.LoaiChi;
import com.example.hades.asmui.model.LoaiThu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigation;
    ArrayList<LoaiThu> dslt=new ArrayList<LoaiThu>();
    ArrayList<LoaiChi> dslc=new ArrayList<LoaiChi>();
    ListView lv,lv1;
Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.listviewloai);
        lv1=findViewById(R.id.listviewloai);
        drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        navigation=(NavigationView)findViewById(R.id.nvView);
        setSupportActionBar(toolbar);
        database=new Database(this);
        ActionBar ab=getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                xulychonmenu(menuItem);

                return false;
            }




        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.nav_first_fragment) {
            return true;
        }
        if(id==android.R.id.home)
            drawer.openDrawer(GravityCompat.START);

        return super.onOptionsItemSelected(item);
    }
    void xulychonmenu(MenuItem menuItem)
    {
        int id=menuItem.getItemId();
        Fragment fragment=null;
        Class classfragment=null;
        if(id==R.id.nav_first_fragment){
            classfragment=Fragment1.class;
        process();}
        if(id==R.id.nav_second_fragment){
            classfragment=Fragment2.class;
        process();}
        if(id==R.id.nav_third_fragment)
            classfragment=Fragment3.class;
        if(id==R.id.nav_four_fragment)
            classfragment=Fragment4.class;
        if(id==R.id.nav_five_fragment) {
            final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Thông báo!");
            builder.setIcon(R.drawable.alert);
            builder.setMessage("Bạn thật sự muốn thoát?");
            builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
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
        try {
            fragment=(Fragment)classfragment.newInstance();

            FragmentManager fmanager= getSupportFragmentManager();
            fmanager.beginTransaction()
                    .replace(R.id.flContent,fragment)
                    .commit();

            menuItem.setChecked(true);
            setTitle(menuItem.getTitle());
            drawer.closeDrawer(GravityCompat.START);
        }catch(Exception e) {

        }
    }
    void process(){
        final ProgressDialog pg=ProgressDialog.show(MainActivity.this,"Đợi tí","Đang lấy thông tin từ Database...");
        CountDownTimer timer=new CountDownTimer(2000,500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                pg.dismiss();
            }
        }.start();

    }
    public void capnhatlt(){
        Database database=new Database(MainActivity.this);
        dslt=database.showloaithu();
        lv=findViewById(R.id.listviewloai);
        AdapterLoaithu adapter=new AdapterLoaithu(dslt, MainActivity.this);
        lv.setAdapter(adapter);
    }
    public void capnhatlc(){
        Database database=new Database(MainActivity.this);
        dslc=database.showloaichi();
        lv1=findViewById(R.id.listviewloai);
        AdapterLoaichi adapter=new AdapterLoaichi(dslc, MainActivity.this);
        lv1.setAdapter(adapter);

    }


}

