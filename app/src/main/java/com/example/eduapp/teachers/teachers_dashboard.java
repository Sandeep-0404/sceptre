package com.example.eduapp.teachers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.eduapp.R;
import com.example.eduapp.databinding.ActivityTeachersDashboardBinding;

public class teachers_dashboard extends AppCompatActivity {
ActivityTeachersDashboardBinding binding;
String username="jkjkjk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityTeachersDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


binding.create.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Bundle bundle=new Bundle();
        bundle.putString("username",username);

        frag_for_create frag_for_create=new frag_for_create();
        frag_for_create.setArguments(bundle);
        frag_for_create.show(getSupportFragmentManager(),frag_for_create.getTag());

    }
});





    }
}