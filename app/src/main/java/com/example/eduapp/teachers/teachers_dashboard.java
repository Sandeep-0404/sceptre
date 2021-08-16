package com.example.eduapp.teachers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.eduapp.R;
import com.example.eduapp.databinding.ActivityTeachersDashboardBinding;
import com.example.eduapp.student.assignment;
import com.example.eduapp.student.bottomFragmentJoin;
import com.example.eduapp.student.fragmentForcreate;
import com.example.eduapp.student.home;
import com.example.eduapp.student.result;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class teachers_dashboard extends AppCompatActivity {
    ActivityTeachersDashboardBinding binding;
    String username = "jkjkjk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeachersDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.chipteacher.setItemSelected(R.id.home_teacher,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerteacher, new techer_home()).commit();
        binding.chipteacher.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.home_teacher:
                        fragment = new techer_home();
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerteacher, fragment).commit();
                        break;

                    case R.id.create_teacher:
                        fragmentForcreate fragmentForcreate=new fragmentForcreate();
                        fragmentForcreate.show(getSupportFragmentManager(),fragmentForcreate.getTag());
                        break;

                    case R.id.join_teacher:
                      bottomFragmentJoin bottomFragmentJoin=new bottomFragmentJoin();
                        bottomFragmentJoin.show(getSupportFragmentManager(),bottomFragmentJoin.getTag());
                        break;

                }
            }
        });



    }

}

