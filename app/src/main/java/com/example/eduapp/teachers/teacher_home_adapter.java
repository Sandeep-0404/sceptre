package com.example.eduapp.teachers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduapp.R;
import com.example.eduapp.student.allGrpModel;
import com.example.eduapp.student.homeAdapter;

import java.util.ArrayList;

public class teacher_home_adapter extends RecyclerView.Adapter<teacher_home_adapter.tech_viewholder> {
    ArrayList<allGroupModel2> data;
    Context context;

    public teacher_home_adapter(ArrayList<allGroupModel2> data, Context context) {
        this.data = data;
        this.context = context;

    }

    @NonNull
    @Override
    public tech_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.teachersinglerow,parent,false);
        return new teacher_home_adapter.tech_viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull tech_viewholder holder, int position) {

        holder.grpName.setText(data.get(position).getGrpName());
        holder.date.setText(data.get(position).getDate());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,window_teachers.class);
               intent.putExtra("invitecode",data.get(position).getInvitecode());
             context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class tech_viewholder extends RecyclerView.ViewHolder {
        TextView grpName,date;
        CardView cardView;
        public tech_viewholder(@NonNull View itemView) {
            super(itemView);
            grpName=itemView.findViewById(R.id.grpName_teachers);
            date=itemView.findViewById(R.id.dategrpCreated_teachers);
            cardView=itemView.findViewById(R.id.cardviewonclickteacher);

        }
    }
}
