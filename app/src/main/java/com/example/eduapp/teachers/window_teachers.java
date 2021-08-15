package com.example.eduapp.teachers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.eduapp.R;
import com.example.eduapp.databinding.ActivityWindowTeachersBinding;
import com.google.android.material.tabs.TabLayout;

public class window_teachers extends AppCompatActivity {
    ActivityWindowTeachersBinding binding;
    frag_windows_adapter fragmentadapter;
    String invitecode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWindowTeachersBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());
        Intent intent=getIntent();
       invitecode=intent.getStringExtra("invitecode");
       Bundle bundle=new Bundle();
       bundle.putString("ic",invitecode);
       post_assignment postAssignment=new post_assignment();
       postAssignment.setArguments(bundle);
      binding.vp2.setCurrentItem(1);
        fragmentadapter=new frag_windows_adapter(getSupportFragmentManager(),getLifecycle());
        binding.vp2.setAdapter(fragmentadapter);
        binding.tblayout.addTab(binding.tblayout.newTab().setText("Chats"));
        binding.tblayout.addTab(binding.tblayout.newTab().setText("Assignments"));
        binding.tblayout.addTab(binding.tblayout.newTab().setText("Attendence"));
        binding.tblayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.vp2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tblayout.selectTab(binding.tblayout.getTabAt(position));
            }


        });
    }
    public String method(){
        return invitecode;
    }


}