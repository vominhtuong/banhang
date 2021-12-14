package com.example.banhang;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class GioHangPage extends Fragment {
    public ImageView getTxtChecked() {
        return txtChecked;
    }

    public void setTxtChecked(ImageView txtChecked) {
        this.txtChecked = txtChecked;
    }

    public ImageView getTxtUnchecked() {
        return txtUnchecked;
    }

    public void setTxtUnchecked(ImageView txtUnchecked) {
        this.txtUnchecked = txtUnchecked;
    }

    public static ImageView txtChecked,txtUnchecked;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.giohang, container , false);
        txtChecked = view.findViewById(R.id.txtChecked);
        txtUnchecked = view.findViewById(R.id.txtUnchecked);
        if (savedInstanceState == null){
            gioHangTinhTien fragment = new gioHangTinhTien();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.framelayoutGioHang,fragment);
            fragmentTransaction.commit();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(MainActivity.listGH.size() == 0){
            txtChecked.setImageDrawable(getResources().getDrawable(R.drawable.circle));
            txtUnchecked.setImageDrawable(getResources().getDrawable(R.drawable.circle2));
            gioHangTinhTien fragment = new gioHangTinhTien();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.framelayoutGioHang,fragment);
            fragmentTransaction.commit();
        }
    }
}
