package com.example.hmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SummaryPage extends AppCompatActivity {
    private Button back;
    TextView t1,t2,t3,t4,t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_page);
        back = findViewById(R.id.button_back);
        t1=findViewById(R.id.FE);
        t2=findViewById(R.id.SE);
        t3=findViewById(R.id.TE);
        t4=findViewById(R.id.BE);
        t5=findViewById(R.id.GE);
        back = (Button)findViewById(R.id.button_back);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finalname = "Dipti Jahdav";
                Intent intent = new Intent(SummaryPage.this,Summary2.class);
                intent.putExtra("name",finalname);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finalname = "Phiroj Shaikh";
                Intent intent = new Intent(SummaryPage.this,Summary2.class);
                intent.putExtra("name",finalname);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finalname = "Amiya Kumar";
                Intent intent = new Intent(SummaryPage.this,Summary2.class);
                intent.putExtra("name",finalname);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String finalname = "Phiroj Shaikh";
                Intent intent = new Intent(SummaryPage.this,NotFound.class);
                //intent.putExtra("name",finalname);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String finalname = "Phiroj Shaikh";
                Intent intent = new Intent(SummaryPage.this,NotFound.class);
                //intent.putExtra("name",finalname);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

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
        bottomNavigationView.setSelectedItemId(R.id.summary);
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