package com.example.nguynqucvit.learnmoregamever2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nguynqucvit.learnmoregamever2.fragment.ActionGameFragment;
import com.example.nguynqucvit.learnmoregamever2.fragment.FpsGameFragment;
import com.example.nguynqucvit.learnmoregamever2.fragment.OpenWorldGameFragment;
import com.example.nguynqucvit.learnmoregamever2.fragment.SurvivalGameFragment;
import com.example.nguynqucvit.learnmoregamever2.fragment.TpsGameFragment;

/**
 * Created by Nguyễn Quốc Việt on 18/07/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    ActionGameFragment actionGameFragment;
    FpsGameFragment fpsGameFragment;
    OpenWorldGameFragment openWorldGameFragment;
    SurvivalGameFragment survivalGameFragment;
    TpsGameFragment tpsGameFragment;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        actionGameFragment = ActionGameFragment.newInstance();
        openWorldGameFragment = OpenWorldGameFragment.newInstance();
        fpsGameFragment = FpsGameFragment.newInstance();
        survivalGameFragment = SurvivalGameFragment.newInstance();
        tpsGameFragment = TpsGameFragment.newInstance();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return actionGameFragment;
            case 1:
                return fpsGameFragment;
            case 2:
                return openWorldGameFragment;
            case 3:
                return survivalGameFragment;
            case 4:
                return tpsGameFragment;
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Action";
            case 1:
                return "Fps";
            case 2:
                return "Open world";
            case 3:
                return "Survival";
            case 4:
                return "Tps";
            default:
                break;

        }
        return super.getPageTitle(position);
    }
}
