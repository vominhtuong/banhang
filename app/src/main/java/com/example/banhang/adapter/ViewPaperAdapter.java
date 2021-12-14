package com.example.banhang.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.banhang.FavoritePage;
import com.example.banhang.GioHangPage;
import com.example.banhang.HomePage;
import com.example.banhang.MePage;

public class ViewPaperAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 4;
    public ViewPaperAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomePage();
            case 1:
                return new FavoritePage();
            case 2:
                return new GioHangPage();
            case 3:
                return new MePage();
            default:
                return new HomePage();
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
