package com.example.hmi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class ProgramAdapter extends ArrayAdapter<String> {
    Context context;
    int[] DepartmentImages;
    String[] DepartmentName;
    String[] DepartmentDesc;

    public ProgramAdapter(Context context, String[] DepartmentName,int[] DepartmentImages,String[] DepartmentDesc) {
        super(context, R.layout.single_item,R.id.textView1,DepartmentName);
        this.context = context;
        this.DepartmentName = DepartmentName;
        this.DepartmentImages = DepartmentImages;
        this.DepartmentDesc = DepartmentDesc;
    }


    @Override
    public View getView(int position,  View convertView,ViewGroup parent) {
        View singleItem = convertView;
        ProgramViewHolder holder = null;
        if(singleItem == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.single_item, parent , false);
            holder = new ProgramViewHolder(singleItem);
            singleItem.setTag(holder);
        }
        else
        {
            holder = (ProgramViewHolder) singleItem.getTag();

        }
        holder.DepartmentImages.setImageResource(DepartmentImages[position]);
        holder.DepartmentName.setText(DepartmentName[position]);
        holder.DepartmentDesc.setText(DepartmentDesc[position]);
        singleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context.getApplicationContext(),Standards.class));

            }


        });

        return singleItem;
    }
}
