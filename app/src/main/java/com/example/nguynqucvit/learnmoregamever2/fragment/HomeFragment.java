package com.example.nguynqucvit.learnmoregamever2.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguynqucvit.learnmoregamever2.R;
import com.example.nguynqucvit.learnmoregamever2.adapter.GameViewPagerAdapter;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        //...
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //TODO:
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        return view;
    }
    private void initViews(View view){
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        GameViewPagerAdapter viewPagerAdapter = new GameViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(5);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setTabTextColors(Color.WHITE,Color.WHITE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
    }

}
