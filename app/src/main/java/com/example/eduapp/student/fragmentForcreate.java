package com.example.eduapp.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eduapp.databinding.FragmentForcreateBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class fragmentForcreate extends BottomSheetDialogFragment {
    EditText groupname;
    FragmentForcreateBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentForcreateBinding.inflate(inflater, container, false);


        binding.creategrupbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGrp(binding.grpName.getText().toString().trim());

            }
        });


        return binding.getRoot();

    }

    private void createGrp(String grpName) {

        if(grpName.isEmpty()){
            binding.grpName.setError("Give a group Name");
            binding.grpName.requestFocus();
            return;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String date1=formatter.format(date);

        String groupCode=getrandomstring(6);
        DatabaseReference red= FirebaseDatabase.getInstance().getReference("StudentGroup").child(groupCode);
        Map<String ,String > mp=new HashMap<>();


        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String uid= user.getUid();
        mp.put("uid",uid);
        red.push().setValue(mp);



        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("userowngroup").child(uid);
        Map<String ,String > mp1=new HashMap<>();
        mp1.put("invitecode",groupCode);
        mp1.put("groupname",grpName);
        mp1.put("date",date1);
        reference.push().setValue(mp1);


        DatabaseReference reference1=FirebaseDatabase.getInstance().getReference("group").child(groupCode);

        Map<String ,String > mp2=new HashMap<>();
        mp2.put("invitecode",groupCode);
        mp2.put("groupname",grpName);
        mp2.put("date",date1);

        reference1.child("groupdetails").setValue(mp2);
        reference1.child("members").push().child("uid").setValue(uid);


        Toast.makeText(getContext(),"Group Successfully created",Toast.LENGTH_SHORT).show();


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