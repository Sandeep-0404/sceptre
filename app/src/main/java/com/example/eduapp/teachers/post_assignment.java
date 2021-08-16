package com.example.eduapp.teachers;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eduapp.R;
import com.example.eduapp.databinding.FragmentPostAssignmentBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class post_assignment extends Fragment {
FragmentPostAssignmentBinding binding;
adapter_assignment adapter;
ArrayList<model_assignment>list;
    String ic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentPostAssignmentBinding.inflate(inflater,container,false);
ic=this.getArguments().getString("ic");
binding.recvoip.setLayoutManager(new LinearLayoutManager(getContext()));
list=new ArrayList<>();
adapter=new adapter_assignment(list,getContext());
adddata();
binding.recvoip.setAdapter(adapter);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent intent=new Intent(getContext(),Ques_assignment.class);
intent.putExtra("ic",ic);
startActivity(intent);
            }
        });






        return binding.getRoot();
    }

    private void adddata() {
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("group").child(ic).child("assignment");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot s:snapshot.getChildren()){
                    String s1=s.getValue(String.class);
                    model_assignment model_assignment=new model_assignment();
                    model_assignment.setUrl(s1);
                    list.add(model_assignment);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}