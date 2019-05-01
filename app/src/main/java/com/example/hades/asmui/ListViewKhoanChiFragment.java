package com.example.hades.asmui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hades.asmui.Adapter.AdapterKhoanChi;
import com.example.hades.asmui.Adapter.AdapterKhoanThu;
import com.example.hades.asmui.model.KhoanChi;
import com.example.hades.asmui.model.KhoanThu;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewKhoanChiFragment extends Fragment {
    ListView lvkhoanchi;
    ArrayList<KhoanChi> dskc=new ArrayList<KhoanChi>();
    public ListViewKhoanChiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_listviewkhoan,null);
        lvkhoanchi=view.findViewById(R.id.listviewkhoan);
        updateUI();
        return view;
    }
    public void updateUI(){
        Database database=new Database(getActivity());
        dskc=database.showkhoanchi();
        AdapterKhoanChi adapter=new AdapterKhoanChi(dskc, getActivity());
        lvkhoanchi.setAdapter(adapter);
    }

}
