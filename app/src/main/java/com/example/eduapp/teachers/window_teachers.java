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
        binding = ActivityWindowTeachersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        invitecode = intent.getStringExtra("invitecode");
       binding.AssignmentTeacher.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Bundle bundle=new Bundle();
               bundle.putString("ic",invitecode);
               post_assignment postAssignment=new post_assignment();
               postAssignment.setArguments(bundle);
               getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.relativeframe2,postAssignment).commit();
           }
       });
        // binding.vp2.setCurrentItem(1);
        binding.attendenceTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.relativeframe2,new attendence()).commit();
            }
        });
        binding.chatsTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.relativeframe2,new chats()).commit();
            }
        });
    }
}