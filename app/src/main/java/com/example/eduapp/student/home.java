package com.example.eduapp.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eduapp.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class home extends Fragment {

    FragmentHomeBinding binding;
    homeAdapter adapter;
    ArrayList<allGrpModel> data;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.allgrpRV.setLayoutManager(new LinearLayoutManager(getContext()));

        data = new ArrayList<>();
        adapter = new homeAdapter(data, getContext());

        binding.allgrpRV.setAdapter(adapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        getAllGrps(uid);


        return binding.getRoot();

    }

    private void getAllGrps(String uid) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("userowngroup").child(uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot s : snapshot.getChildren()) {

                    Map<String, String> mp = (Map<String, String>) s.getValue();
                    allGrpModel allGrpModel = new allGrpModel();
                    allGrpModel.setGrpName(mp.get("groupname"));
                    allGrpModel.setDate(mp.get("date"));
                    data.add(allGrpModel);


                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}