package com.example.eduapp.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.eduapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class bottomFragmentJoin extends BottomSheetDialogFragment {


    EditText invitecode;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bottom_join, container, false);


        return v;


    }


}
