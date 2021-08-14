package com.example.eduapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.eduapp.R;
import com.example.eduapp.teachers.teachers_dashboard;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // commit
        int a;
        String saurav;
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("ghghg");
        reference.push().setValue("dfg");
    }


}