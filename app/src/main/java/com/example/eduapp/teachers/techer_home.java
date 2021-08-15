package com.example.eduapp.teachers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eduapp.R;
import com.example.eduapp.student.allGrpModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class techer_home extends Fragment {

    RecyclerView rcv;
    teacher_home_adapter adapter;
    ArrayList<allGroupModel2> data;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_techer_home, container, false);

        rcv=v.findViewById(R.id.teacherallgroupsrecv);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        data = new ArrayList<>();
        adapter=new teacher_home_adapter(data,getContext());
        rcv.setAdapter(adapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        getAllGrps(uid);




        return v;
    }
    private void getAllGrps(String uid) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("userowngroup").child(uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot s : snapshot.getChildren()) {

                    Map<String, String> mp = (Map<String, String>) s.getValue();
                    allGroupModel2 allGrpModel = new allGroupModel2();

                    allGrpModel.setGrpName(mp.get("groupname"));
                    allGrpModel.setDate(mp.get("date"));
                    allGrpModel.setInvitecode(mp.get("invitecode"));
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