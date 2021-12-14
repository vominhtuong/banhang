package com.example.banhang.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.banhang.fragment.FragmentDangKy;
import com.example.banhang.fragment.FragmentDangNhap;

import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> mFragmentList;
    ArrayList<String> mFragmentTittleList;
    private String[] tabTitles = new String[]{"Đăng Nhập", "Đăng Ký"};
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    public PageAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList = new ArrayList<>();
        mFragmentTittleList = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentDangNhap();
            case 1:
                return new FragmentDangKy();
            default:
                throw new RuntimeException("Invalid tab position");
        }
    }

    @Override
    public int getCount() {
        return mFragmentTittleList.size();
    }

    public void addFragment(Fragment fragment,String title){
        mFragmentList.add(fragment);
        mFragmentTittleList.add(title);
    }

    //------- this method is precaution for AdroidX ---------
    //------- while tabs text not displaying android 9+ ---------

    @NonNull

    public String getPageTittle(int position){
        return tabTitles[position];
    }
}
