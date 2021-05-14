package com.example.hmi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgramViewHolder {

    ImageView DepartmentImages;
    TextView DepartmentName;
    TextView DepartmentDesc;
    ProgramViewHolder(View v)
    {
        DepartmentImages = v.findViewById(R.id.imageView);
        DepartmentName = v.findViewById(R.id.textView1);
        DepartmentDesc = v.findViewById(R.id.textView2);

    }
}
