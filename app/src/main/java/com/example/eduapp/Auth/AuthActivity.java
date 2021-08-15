package com.example.eduapp.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.eduapp.Adapters.ViewPagerAdapter;
import com.example.eduapp.Fragments.FragmentLogin;
import com.example.eduapp.Fragments.FragmentSignup;
import com.example.eduapp.R;
import com.example.eduapp.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {

    ActivityAuthBinding activityAuthBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAuthBinding=ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(activityAuthBinding.getRoot());
        getSupportActionBar().hide();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.add(new FragmentLogin(), "Login");
        adapter.add(new FragmentSignup(), "Signup");
        activityAuthBinding.viewPager.setAdapter(adapter);
        activityAuthBinding.tabLayout.setupWithViewPager(activityAuthBinding.viewPager);

    }
}