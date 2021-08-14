package com.example.eduapp.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eduapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Random;


public class fragmentForcreate extends BottomSheetDialogFragment {
    EditText groupname;
    String pic = "notuploadeimg";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_forcreate, container, false);

        TextView usernamef = v.findViewById(R.id.fragusername);
        ImageView cgprofilepic = v.findViewById(R.id.cgprofpic);






        //groupname= v.findViewById(R.id.cggrupname);

        v.findViewById(R.id.cgprofpic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        v.findViewById(R.id.creategrup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (groupname.getText().toString().trim().isEmpty()) {
                    groupname.setError("Group name can't be empty");
                    groupname.requestFocus();
                    return;
                }


                // dismiss();


            }
        });
        return v;

    }


    public static String getrandomstring(int i) {
        final String chaaracters = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJklMNOPQRSTUV";
        StringBuilder result = new StringBuilder();
        while (i > 0) {
            Random rand = new Random();
            result.append(chaaracters.charAt(rand.nextInt(chaaracters.length())));
            i--;
        }
        return result.toString();
    }
}