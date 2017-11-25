package com.example.star.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class DetailFragment extends Fragment {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sample, container, false);
        //new GetStore(getActivity()).execute();
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new sliderAdapter(getChildFragmentManager()));
        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });


        return view;
    }
    private class sliderAdapter extends FragmentPagerAdapter {

        final  String tabs[]={"정보", "리뷰", "사진"};
        public sliderAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:return new InfoFragment();
                case 1:return new contentFragment();
                case 2:return new contentFragment();
            }
                return null;
        }

        @Override
        public int getCount() {
            return 3; // 탭의 개수
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }
}