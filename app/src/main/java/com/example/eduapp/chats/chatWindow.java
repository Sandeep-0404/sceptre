package com.example.eduapp.chats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eduapp.databinding.ActivityChatWindowBinding;
import com.example.eduapp.student.grpmsgAdapter;
import com.example.eduapp.student.ic_model1;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class chatWindow extends AppCompatActivity {

    ActivityChatWindowBinding binding;
    String invitecode;
    ArrayList<ic_model1> grpList;
    grpmsgAdapter grpmsgAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatWindowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String grpName = intent.getStringExtra("grpName");
        invitecode = intent.getStringExtra("invitecode");

        binding.grpName.setText(grpName);

        grpList = new ArrayList<>();
        grpmsgAdapter = new grpmsgAdapter(grpList, this);
        binding.grprecview.setLayoutManager(new LinearLayoutManager(this));
        binding.grprecview.setAdapter(grpmsgAdapter);

        fetchChats();


    }

    private void fetchChats() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("groupChat").child(invitecode);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                grpList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Map<String, String> map = (Map) snapshot1.getValue();

                    String msg = map.get("msg");
                    String userName = map.get("username");
                    String uid = map.get("uid");


                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String uids = user.getUid();


                    ic_model1 ic_model1 = new ic_model1();
                    ic_model1.setName(userName);
                    ic_model1.setMsg(msg);
                    if (uids.equals(uid)) ic_model1.setViewtype(0);
                    else ic_model1.setViewtype(1);

                    grpList.add(ic_model1);

                }

                grpmsgAdapter.notifyDataSetChanged();
                binding.grprecview.smoothScrollToPosition(binding.grprecview.getAdapter().getItemCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void send(View view) {

        if (binding.edtmsggrp.getText().toString().trim().isEmpty()) return;


        String grpMsg = binding.edtmsggrp.getText().toString().trim();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Map<String, String> map = (Map) snapshot.getValue();
                if (map != null) {
                    String userNAme = map.get("username");

                    Map<String, String> mp = new HashMap<>();
                    mp.put("msg", grpMsg);
                    mp.put("uid", uid);
                    mp.put("username", userNAme);

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("groupChat").child(invitecode);
                    reference.push().setValue(mp);
                    binding.edtmsggrp.setText("");
                    //  fetchChats();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}

