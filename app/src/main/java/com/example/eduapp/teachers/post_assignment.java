package com.example.eduapp.teachers;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eduapp.R;
import com.example.eduapp.databinding.FragmentPostAssignmentBinding;


public class post_assignment extends Fragment {
FragmentPostAssignmentBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentPostAssignmentBinding.inflate(inflater,container,false);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

startActivity(new Intent(getContext(),Ques_assignment.class));
            }
        });






        return binding.getRoot();
    }
}