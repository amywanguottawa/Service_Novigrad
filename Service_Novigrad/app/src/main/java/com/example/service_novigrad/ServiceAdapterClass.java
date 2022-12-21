package com.example.service_novigrad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ServiceAdapterClass extends RecyclerView.Adapter<ServiceAdapterClass.ViewHolder>{

    List<ServiceModelClass> service;
    Context context;
    ServiceHelperClass serviceHelperClass;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    public ServiceAdapterClass(List<ServiceModelClass> service, Context context) {
        this.service = service;
        this.context = context;
        serviceHelperClass = new ServiceHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.service_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ServiceModelClass serviceModelClass = service.get(position);

        holder.tvId.setText(Integer.toString(serviceModelClass.getId()));
        holder.etServiceName.setText(serviceModelClass.getName());

        //Editing a form of a given service
        holder.btEditService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = holder.etServiceName.getText().toString();

                serviceHelperClass.updateService(new ServiceModelClass(serviceModelClass.getId(), stringName));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());

                if (stringName.equals("Health Card")) {
                    Intent intent = new Intent(context, FormEditor.class);
                    intent.putExtra("form type",stringName);
                    context.startActivity(intent);
                } else if (stringName.equals("Drivers License")) {
                    Intent intent = new Intent(context, FormEditor.class);
                    intent.putExtra("form type",stringName);
                    context.startActivity(intent);
                } else if (stringName.equals("Photo Id")) {
                    Intent intent = new Intent(context, FormEditor.class);
                    intent.putExtra("form type",stringName);
                    context.startActivity(intent);
                }

            }
        });

        //Deleting a service from the offered services list
        holder.btDeleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceHelperClass.deleteService(serviceModelClass.getId());
                service.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.bAdd.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v){
                String stringName = holder.etServiceName.getText().toString();
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("branch_services");
                reference.child(UserTracker.getUser()).push().setValue(stringName);
                Toast.makeText(context.getApplicationContext(),"Service added!",Toast.LENGTH_SHORT).show();



            }
        });



    }

    @Override
    public int getItemCount() {
        return service.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvId;
        EditText etServiceName;
        Button btEditService, btDeleteService, bAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tvID);
            etServiceName = itemView.findViewById(R.id.etServiceName);
            btEditService = itemView.findViewById(R.id.btEditService);
            btDeleteService = itemView.findViewById(R.id.btDeleteService);
            bAdd = itemView.findViewById(R.id.bAdd);
            if (UserTracker.role.equals("Administrator") ){
                bAdd.setVisibility(View.GONE);
            }
            if (UserTracker.role.equals("Employee") ){
                btEditService.setVisibility(View.GONE);
            }
        }
    }


}
