package com.example.nguynqucvit.learnmoregamever2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nguynqucvit.learnmoregamever2.fragment.ActionGameFragment;
import com.example.nguynqucvit.learnmoregamever2.fragment.FpsGameFragment;
import com.example.nguynqucvit.learnmoregamever2.fragment.OpenWorldGameFragment;
import com.example.nguynqucvit.learnmoregamever2.fragment.SurvivalGameFragment;
import com.example.nguynqucvit.learnmoregamever2.fragment.TpsGameFragment;

import org.jsoup.Jsoup;

/**
 * Created by Nguyễn Quốc Việt on 18/07/2017.
 */

public class GameViewPagerAdapter extends FragmentStatePagerAdapter {
    private ActionGameFragment mActionGameFragment;
    private FpsGameFragment mFpsGameFragment;
    private OpenWorldGameFragment mOpenWorldGameFragment;
    private SurvivalGameFragment mSurvivalGameFragment;
    private TpsGameFragment mTpsGameFragment;

    public GameViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mActionGameFragment = ActionGameFragment.newInstance();
        mOpenWorldGameFragment = OpenWorldGameFragment.newInstance();
        mFpsGameFragment = FpsGameFragment.newInstance();
        mSurvivalGameFragment = SurvivalGameFragment.newInstance();
        mTpsGameFragment = TpsGameFragment.newInstance();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mActionGameFragment;
            case 1:
                return mFpsGameFragment;
            case 2:
                return mOpenWorldGameFragment;
            case 3:
                return mSurvivalGameFragment;
            case 4:
                return mTpsGameFragment;
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
