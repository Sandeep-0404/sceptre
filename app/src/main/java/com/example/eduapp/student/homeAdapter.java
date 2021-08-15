package com.example.eduapp.student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduapp.R;

import java.util.ArrayList;

public class homeAdapter extends RecyclerView.Adapter<homeAdapter.viewholder> {

    ArrayList<allGrpModel> data;
    Context context;

    public homeAdapter(ArrayList<allGrpModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.studentsinglerow,parent,false);
       return new homeAdapter.viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        holder.grpName.setText(data.get(position).getGrpName());
        holder.date.setText(data.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView grpName,date;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            grpName=itemView.findViewById(R.id.grpName);
            date=itemView.findViewById(R.id.dategrpCreated);
        }
    }
}
