package com.example.hmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class facultyinformation extends AppCompatActivity {
    private Button back,whatsapp;
    TextView t1,t2,t3,t4,t5,t6,t7,p1,p2,p3,p4,p5,p6,p7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultyinformation);
        back = (Button)findViewById(R.id.button_back);
        whatsapp=(Button)findViewById(R.id.whatsapp);
        t1= (TextView)findViewById(R.id.t1);
        t2= (TextView)findViewById(R.id.t2);
        t3= (TextView)findViewById(R.id.t3);
        t4= (TextView)findViewById(R.id.t4);
        t5= (TextView)findViewById(R.id.t5);
        t6= (TextView)findViewById(R.id.t6);
        t7= (TextView)findViewById(R.id.t7);
        p1= (TextView)findViewById(R.id.p1);
        p2= (TextView)findViewById(R.id.p2);
        p3= (TextView)findViewById(R.id.p3);
        p4= (TextView)findViewById(R.id.p4);
        p5= (TextView)findViewById(R.id.p5);
        p6= (TextView)findViewById(R.id.p6);
        p7= (TextView)findViewById(R.id.p7);


        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9223370153"));
                startActivity(intent);
            }
        });

        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9975321741"));
                startActivity(intent);
            }
        });

        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9970284471"));
                startActivity(intent);
            }
        });

        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9594198786"));
                startActivity(intent);
            }
        });

        p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9833029544"));
                startActivity(intent);
            }
        });

        p6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9892524834"));
                startActivity(intent);
            }
        });

        p7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9820955452"));
                startActivity(intent);
            }
        });


        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            /*    Intent intent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                startActivity(intent);*/

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setPackage("com.whatsapp");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello Sir/Madam I need to Swap my Lecture with you today. Please Revert back asap.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        return true;
                    case R.id.summary:
                        startActivity(new Intent(getApplicationContext(), SummaryPage.class));
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        return true;
                }
                return false;
            }

        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }
}