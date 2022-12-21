package com.example.service_novigrad;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapterBranch extends RecyclerView.Adapter<adapterBranch.MyViewHolder> {

    ArrayList<modelBranch> list;
    Context context;

    public adapterBranch(ArrayList<modelBranch> list,Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.branch_card_holder,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.address.setText(list.get(position).getAddress());
        holder.phoneNumber.setText(list.get(position).getPhoneNumber());
        holder.name.setText(list.get(position).getName());
        holder.bReviewBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String branchName = holder.name.getText().toString();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
                Intent intent = new Intent(context,RateBranch.class);
                intent.putExtra("branch name", branchName);
                context.startActivity(intent);
            }
        });
        holder.bViewServices.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String branchName = holder.name.getText().toString();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
                Intent intent = new Intent(context,customerViewServices.class);
                intent.putExtra("branch name", branchName);
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView address, phoneNumber, name;
        Button bReviewBranch, bViewServices;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.address);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            name = itemView.findViewById(R.id.name);
            bReviewBranch = itemView.findViewById(R.id.bReviewBranch);
            bViewServices = itemView.findViewById(R.id.bViewServices);

        }
    }
}
