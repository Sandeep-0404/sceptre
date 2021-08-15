package com.example.eduapp.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.eduapp.databinding.FragmentBottomJoinBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class bottomFragmentJoin extends BottomSheetDialogFragment {

    FragmentBottomJoinBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBottomJoinBinding.inflate(inflater, container, false);

        binding.joinGrp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                joinGroup(binding.groupCode.getText().toString().trim());
            }
        });

        return binding.getRoot();

    }

    private void joinGroup(String grpCode) {


        if (grpCode.isEmpty()) {
            binding.groupCode.setError("Can't be Empty");
            binding.groupCode.requestFocus();
            return;
        }


        DatabaseReference red = FirebaseDatabase.getInstance().getReference("StudentGroup").child(grpCode);
        Map<String, String> mp = new HashMap<>();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mp.put("uid", uid);
        red.push().setValue(mp);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("group").child(grpCode).child("groupdetails");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Map<String, String> map = (Map<String, String>) snapshot.getValue();

                String date = map.get("date");
                String groupname = map.get("groupname");
                String invitecode = grpCode;

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("userowngroup").child(uid);
                Map<String, String> mp1 = new HashMap<>();
                mp1.put("invitecode", invitecode);
                mp1.put("groupname", groupname);
                mp1.put("date", date);
                reference.push().setValue(mp1);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Toast.makeText(getContext(), "Group Successfully Joined", Toast.LENGTH_SHORT).show();
    }


}
