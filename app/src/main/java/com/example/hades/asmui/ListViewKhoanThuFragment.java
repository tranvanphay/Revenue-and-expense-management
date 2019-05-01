package com.example.hades.asmui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hades.asmui.Adapter.AdapterKhoanThu;
import com.example.hades.asmui.Adapter.AdapterLoaithu;
import com.example.hades.asmui.model.KhoanThu;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewKhoanThuFragment extends Fragment {
ListView lvkhoanthu;
ArrayList<KhoanThu> dskt=new ArrayList<KhoanThu>();
    public ListViewKhoanThuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                View view=inflater.inflate(R.layout.fragment_listviewkhoan,null);
                lvkhoanthu=view.findViewById(R.id.listviewkhoan);
                updateUI();
                return view;
    }
    public void updateUI(){
        Database database=new Database(getActivity());
        dskt=database.showkhoanthu();
        AdapterKhoanThu adapter=new AdapterKhoanThu(dskt, getActivity());
        lvkhoanthu.setAdapter(adapter);
    }

}
