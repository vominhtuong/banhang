package com.example.banhang.Activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.banhang.fragment.Fragment_CapNhatSanPham;
import com.example.banhang.fragment.Fragment_CapNhatThuongHieu;
import com.example.banhang.R;
import com.example.banhang.adapter.capnhatViewPaperAdapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class activity_capnhat extends AppCompatActivity {

    ImageButton imgBack;
    TabLayout tabLayout;
    ViewPager viewPager;
    capnhatViewPaperAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capnhatsanpham);
        anhxa();
        adapter = new capnhatViewPaperAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_CapNhatSanPham(),"Sản Phẩm");
        adapter.addFragment(new Fragment_CapNhatThuongHieu(),"Thương Hiệu");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        imgBack = findViewById(R.id.imgBack);
        tabLayout = findViewById(R.id.tablayout_capnhatsanpham);
        viewPager = findViewById(R.id.viewpaper_capnhatsanpham);
    }


    @Override
    protected void onStart() {
        super.onStart();
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.aqua));
        }
    }
}
