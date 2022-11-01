package com.example.appmobile.Vue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PageAdapt extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentArrayList =new ArrayList<>();
    ArrayList<String> titres =new ArrayList<>();

    public PageAdapt(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
    public void addfragment(Fragment fragment){
        fragmentArrayList.add(fragment);

    }
    public void addtitle(String titre){
        titres.add(titre);

    }

    @Nullable
    @Override

    public CharSequence getPageTitle(int position) {
        return titres.get(position);
    }
}
