package com.example.hmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Scene;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    //ListView lvProgram;
    FirebaseAuth fAuth;
    private Button logout;
    TextView comps;

   // String [] DepartmentName = {"COMPUTER","IT","EXTC","MECHANICAL"};
    //String [] DepartmentDesc = {"Department of Computer Engineering","Department of IT Engineering","Department of EXTC Engineering","Department of Mechanical Engineering"};
    //int[] DepartmentImages = {R.drawable.computer,R.drawable.it,R.drawable.extc,R.drawable.mechanical};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //lvProgram=findViewById(R.id.lvProgram);
        //ProgramAdapter programAdapter = new ProgramAdapter(this,DepartmentName,DepartmentImages,DepartmentDesc);
        fAuth = FirebaseAuth.getInstance();

        logout = (Button)findViewById(R.id.logout_button);
        comps =  (TextView) findViewById(R.id.FE);

        comps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Standards.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),phonenumber.class));

            }
        });
        //lvProgram.setAdapter(programAdapter);
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