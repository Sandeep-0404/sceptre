package com.example.eduapp.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.eduapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class studentHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("ghghg");
        reference.push().setValue("dfg");
    }
}