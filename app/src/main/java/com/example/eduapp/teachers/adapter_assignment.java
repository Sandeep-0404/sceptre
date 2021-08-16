package com.example.eduapp.teachers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduapp.R;

import java.util.ArrayList;

public class adapter_assignment extends RecyclerView.Adapter<adapter_assignment.viewwwh> {
    ArrayList<model_assignment>list;
    Context context;

    public adapter_assignment(ArrayList<model_assignment> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewwwh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_assignment,parent,false);
        return new viewwwh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewwwh holder, int position) {
        holder.tv.setText(list.get(position).getUrl());
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,webview.class);
                intent.putExtra("url",list.get(position).getUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewwwh extends RecyclerView.ViewHolder {
        TextView tv;
        public viewwwh(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.url);
        }
    }
}
