package com.example.hades.asmui;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hades.asmui.Adapter.AdapterLoaithu;
import com.example.hades.asmui.model.KhoanThu;
import com.example.hades.asmui.model.LoaiThu;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewLoaiThuFragment extends Fragment {
    ListView lv;
    ArrayList<LoaiThu> dslt=new ArrayList<LoaiThu>();
    public ListViewLoaiThuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listviewloai,null);
        lv = (ListView) view.findViewById(R.id.listviewloai);
        updateUI();
        return view;
    }
    public void updateUI(){
        Database database=new Database(getActivity());
        dslt=database.showloaithu();
        AdapterLoaithu adapter=new AdapterLoaithu(dslt, getActivity());
        lv.setAdapter(adapter);
    }

}
