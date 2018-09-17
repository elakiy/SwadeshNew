package com.wankys.www.swadeshurja.Activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;

import com.wankys.www.swadeshurja.Adapters.ViewPagerAdapter;
import com.wankys.www.swadeshurja.Fragments.Register_Employee;
import com.wankys.www.swadeshurja.Fragments.Regsiter_User;
import com.wankys.www.swadeshurja.R;

/**
 * Created by Elakiya on 5/4/2018.
 */

public class Register extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    AppCompatButton buttonConfirm;
    EditText editTextConfirmOtp;
    String employee = "Register as Employee";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().hide();

        tabLayout= (TabLayout) findViewById(R.id.tablayout_Id);
        viewPager= (ViewPager) findViewById(R.id.viewpager);

        addTabs(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setCurrentItem(0);
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Register_Employee(),employee.toLowerCase());
        adapter.addFrag(new Regsiter_User(),"Register as User");
        viewPager.setAdapter(adapter);
    }
}
