package com.example.banhang;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.banhang.fragment.FragmentAdmin;
import com.example.banhang.fragment.FragmentChuaDangNhap;
import com.example.banhang.fragment.FragmentDaDangNhap;

public class MePage extends Fragment {
    TextView txtXemTT,txtTTLH;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me, container, false);
        TextView txtDNDK = (TextView) view.findViewById(R.id.txtDNDK);
        if(savedInstanceState == null && MainActivity.dadangnhap == false) {
            FragmentChuaDangNhap fragment1 = new FragmentChuaDangNhap();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.framelayoutMe, fragment1);
            fragmentTransaction.commit();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(MainActivity.dadangnhap == true && MainActivity.tenLoai.equals("client")){
            FragmentDaDangNhap fragment2 = new FragmentDaDangNhap();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayoutMe,fragment2);
            fragmentTransaction.commit();
        }else if(MainActivity.dadangnhap == true && MainActivity.tenLoai.equals("admin")) {
            FragmentAdmin fragment3 = new FragmentAdmin();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayoutMe,fragment3);
            fragmentTransaction.commit();
        }
    }
}
