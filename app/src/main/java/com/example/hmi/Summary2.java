package com.example.hmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import static android.widget.Toast.LENGTH_SHORT;

public class Summary2 extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    String[] list;
    Button back;
    TextView name,lec,req,por;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary2);
        back = (Button) findViewById(R.id.button_back);
        name = (TextView)  findViewById(R.id.textView1);
        lec = (TextView) findViewById(R.id.textView9);
        req = (TextView) findViewById(R.id.textView10);
        por = (TextView) findViewById(R.id.tvran);
        String name1 = getIntent().getStringExtra("name");

        name.setText("Name: " + name1);
        final int random = new Random().nextInt(20) + 15;
        String tmpStr = String.valueOf(random);
        lec.setText("Lecture Number:     " + tmpStr);
        final int random1 = new Random().nextInt(1) + 5;
        String tmpStr1 = String.valueOf(random1);
        req.setText("Required Lecture:   " + tmpStr1);
        final int random2 = new Random().nextInt(1) + 5;
        String tmpStr2 = String.valueOf(random1);
        por.setText("Portion Left:            " + tmpStr2 + "%");
        


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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }
}