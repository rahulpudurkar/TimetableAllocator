package com.example.hmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Scene;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ListView lvProgram;
    String [] DepartmentName = {"COMPUTER","IT","EXTC","MECHANICAL"};
    String [] DepartmentDesc = {"Department of Computer Engineering","Department of IT Engineering","Department of EXTC Engineering","Department of Mechanical Engineering"};
    int[] DepartmentImages = {R.drawable.computer,R.drawable.it,R.drawable.extc,R.drawable.mechanical};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvProgram=findViewById(R.id.lvProgram);
        ProgramAdapter programAdapter = new ProgramAdapter(this,DepartmentName,DepartmentImages,DepartmentDesc);
        lvProgram.setAdapter(programAdapter);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.schedule:
                        startActivity(new Intent(getApplicationContext(), SchedulePage.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.summary:
                        startActivity(new Intent(getApplicationContext(), SummaryPage.class));
                        overridePendingTransition(0, 0);
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