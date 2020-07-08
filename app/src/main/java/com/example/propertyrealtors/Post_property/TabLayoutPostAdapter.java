package com.example.propertyrealtors.Post_property;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.propertyrealtors.SessionManager;

import java.util.HashMap;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

public class TabLayoutPostAdapter extends FragmentPagerAdapter {
    Context myContext;
    int totalTabs;


    public TabLayoutPostAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }
    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position) {
            case 0:
                fragment = new rent_or_lease_residential();
                break;
            case 1:
                fragment = new sell_residential();
                break;
            case 2:
                fragment = new rent_or_lease_commercial();
                break;
            case 3:
                fragment = new sell_commercial();
                break;
            default:
                fragment=null;
                break;
        }
        return fragment;
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Rent/lease\nResidential";
        }
        else if (position == 1)
        {
            title = "Sell\nResidential";
        }
        else if (position == 2)
        {
            title = "Rent/lease\nCommercial";
        }
        else if (position == 3)
        {
            title = "Sell\nCommercial";
        }
        return title;
    }

}
