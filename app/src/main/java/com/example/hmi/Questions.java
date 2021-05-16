package com.example.hmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

public class Questions extends AppCompatActivity {
    TextInputEditText q1,q2, q3,q4,q5,q6;
    DatabaseReference databaseReference;
    Button back_button,save,edit,submit;
    String ques1,ques2,ques3,ques4,ques5,ques6;
    Java_Questions java_questions;
    long maxid = 0;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        q1 = findViewById(R.id.q1);
        q2 = findViewById(R.id.q2);
        q3= findViewById(R.id.q3);
        q4= findViewById(R.id.q4);
        q5= findViewById(R.id.q5);
        q6= findViewById(R.id.q6);
        save = findViewById(R.id.save);
        edit=findViewById(R.id.edit);
        submit=findViewById(R.id.submit);
        back_button = findViewById(R.id.button_back);
       // awesomeValidation.addValidation(this, R.id.q1, "[a-zA-Z\\s]+", R.string.invalid_name);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(uid).child("Questions");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ques1 = q1.getText().toString();
                ques2 = q2.getText().toString();
                ques3 = q3.getText().toString();
                ques4 = q4.getText().toString();
                ques5 = q5.getText().toString();
                ques6 = q6.getText().toString();


                Java_Questions n = new Java_Questions(ques1,ques2,ques3,ques4,ques5,ques6);
                databaseReference.setValue(n);
                Toast.makeText(getApplicationContext(), "Data inserted Successfully", Toast.LENGTH_LONG).show();
                finish();

            }
        });
/*        databaseReference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,String> hash = new HashMap<>();
                hash.put("q1",snapshot.child("q1").getValue(String.class));
                hash.put("q2",snapshot.child("q2").getValue(String.class));
                hash.put("q3",snapshot.child("q3").getValue(String.class));
                hash.put("q4",snapshot.child("q4").getValue(String.class));
                hash.put("q5",snapshot.child("q5").getValue(String.class));
                hash.put("q6",snapshot.child("q6").getValue(String.class));

                q1.setText(hash.get("q1"));
                q2.setText(hash.get("q2"));
                q3.setText(hash.get("q3"));
                q4.setText(hash.get("q4"));
                q5.setText(hash.get("q5"));
                q6.setText(hash.get("q6"));

                databaseReference.keepSynced(true);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Something went wrong please try again",LENGTH_SHORT).show();

            }
        });*/

  /*      edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save = (Button) findViewById(R.id.save);
                save.setVisibility(View.VISIBLE);
                q1.setFocusableInTouchMode(false);
                q1.setCursorVisible(false);
                q1.setFocusable(false);
                q2.setFocusableInTouchMode(false);
                q2.setCursorVisible(false);
                q2.setFocusable(false);
                q3.setFocusableInTouchMode(false);
                q3.setCursorVisible(false);
                q3.setFocusable(false);
                q4.setFocusableInTouchMode(false);
                q4.setCursorVisible(false);
                q4.setFocusable(false);
                q5.setFocusableInTouchMode(false);
                q5.setCursorVisible(false);
                q5.setFocusable(false);
                q6.setFocusableInTouchMode(false);
                q6.setCursorVisible(false);
                q6.setFocusable(false);

            }
        });*/

/*        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                    Toast.makeText(getApplicationContext(),"Successful Operation", LENGTH_SHORT).show();
                    uploadData();



                Button save = (Button) v ;
                save.setVisibility(View.INVISIBLE);
                q1.setFocusableInTouchMode(false);
                q1.setCursorVisible(false);
                q1.setFocusable(false);
                q2.setFocusableInTouchMode(false);
                q2.setCursorVisible(false);
                q2.setFocusable(false);
                q3.setFocusableInTouchMode(false);
                q3.setCursorVisible(false);
                q3.setFocusable(false);
                q4.setFocusableInTouchMode(false);
                q4.setCursorVisible(false);
                q4.setFocusable(false);
                q5.setFocusableInTouchMode(false);
                q5.setCursorVisible(false);
                q5.setFocusable(false);
                q6.setFocusableInTouchMode(false);
                q6.setCursorVisible(false);
                q6.setFocusable(false);






            }



            private void uploadData()
            {


                if(!TextUtils.isEmpty(q1.getText().toString()) || !TextUtils.isEmpty(q2.getText().toString()) || !TextUtils.isEmpty(q3.getText().toString()) || !TextUtils.isEmpty(q4.getText().toString())|| !TextUtils.isEmpty(q5.getText().toString()) || !TextUtils.isEmpty(q6.getText().toString()))
                {
                    Java_Questions obj = new Java_Questions(q1.getText().toString(),q2.getText().toString(),q3.getText().toString(),q4.getText().toString(),q5.getText().toString(),q6.getText().toString());
                    Map<String,Object > profile = new HashMap<>();
                    profile.put("q1",obj.getQ1());
                    profile.put("q2",obj.getQ2());
                    profile.put("q3",obj.getQ3());
                    profile.put("q4",obj.getQ4());
                    profile.put("q5",obj.getQ5());
                    profile.put("q6",obj.getQ6());

                    databaseReference.child(uid).updateChildren(profile);
                    Toast.makeText(getApplicationContext(),"Your Profile is Updated", LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please provide correct Information", LENGTH_SHORT).show();

                }

            }


        });*/


        back_button.setOnClickListener(new View.OnClickListener() {
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