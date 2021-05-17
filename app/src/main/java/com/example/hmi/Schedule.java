package com.example.hmi;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Schedule extends LinearLayout implements AdapterView.OnItemSelectedListener {

    ImageButton NextButton, PreviousButton;
    TextView CurrentDate;
    GridView gridView;
    ProgressBar progressBar;
    private static final int MAX_CALENDAR_DAYS = 42;
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    Context context;
    AlertDialog alertDialog;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    SimpleDateFormat eventDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    MyGridAdapter myGridAdapter;
    EventsRecyclerAdapter eventsRecyclerAdapter;


    List<Users> usersList = new ArrayList<>();
    List<String> assignedDate = new ArrayList<>();
    List<Date> dates = new ArrayList<>();
    Users users = new Users();
    ArrayList<Users> staffAssignList = new ArrayList<>();
    ArrayList<Users> arrayList = new ArrayList<>();
    String[] shiftsArrayString = {"10-11 AM", "11-12 AM", "12-1 PM", "1-2 PM"};
    Boolean itemSelected = false;
    Boolean assigned = false;
    String[] staffDataList;
    HashMap<String,String> staffMap = new HashMap<>();

    public Schedule(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Schedule(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        InitializeLayout();

        getStaff();


        NextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                SetUpCalendar();
            }
        });

        PreviousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                SetUpCalendar();
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String date = eventDateFormat.format(dates.get(position));
                progressBar.setVisibility(VISIBLE);
                //View showView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_layout, null);
                newMethod(date);
                itemSelected = true;
            }
        });
    }

    public Schedule(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void InitializeLayout() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = null;
        if (inflater != null) {
            view = inflater.inflate(R.layout.calendar_layout, this);

            NextButton = view.findViewById(R.id.nextBtn);
            PreviousButton = view.findViewById(R.id.prevBtn);
            progressBar = view.findViewById(R.id.progressBar);
            CurrentDate = view.findViewById(R.id.current_date);
            gridView = view.findViewById(R.id.calendar_grid);

        } else
            Log.d("Inflater", "Infalter Error");
    }

    public void SetUpCalendar() {
        String currentDate = dateFormat.format(calendar.getTime());
        CurrentDate.setText(currentDate);

        dates.clear();
        Calendar monthCalendar = (Calendar) calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1);

        int firstDayOfMonth = monthCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        monthCalendar.set(Calendar.DAY_OF_MONTH, -firstDayOfMonth);
        //CollectEventsPerMonth(monthFormat.format(calendar.getTime()), yearFormat.format(calendar.getTime()));

        while (dates.size() < MAX_CALENDAR_DAYS) {
            dates.add(monthCalendar.getTime());
            String date = eventDateFormat.format(dates.get(dates.size() - 1));
            //System.out.println(date);
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
            progressBar.setVisibility(VISIBLE);
            newMethod(date);
            /*If assigned, don't assignStaff else continue*/
            if (!assigned) {
                assignStaff(date);
                assigned = false;
            }
        }

        myGridAdapter = new MyGridAdapter(context, dates, calendar);
        gridView.setAdapter(myGridAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Get all staff present
    void getStaff() {
        progressBar.setVisibility(VISIBLE);
        FirebaseDatabase.getInstance().getReference("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                arrayList.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    String nKey = d.getKey();
                    String userName = d.child("name").getValue(String.class);
                    Users user = d.getValue(Users.class);
                    arrayList.add(user);
                }

                staffDataList = new String[arrayList.size()];
                for (i = 0; i < arrayList.size(); i++) {
                    staffDataList[i] = arrayList.get(i).getNAME();
                    staffMap.put(staffDataList[i], arrayList.get(i).getSUBJECT());
                }
                progressBar.setVisibility(GONE);
                SetUpCalendar();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Assign shifts to all staffs per day
    void assignStaff(String date) {
        staffAssignList.clear();
        int k = 0;

        for (String s : staffDataList) {
            if (k >= shiftsArrayString.length)
                break;

            Users user = new Users(s, shiftsArrayString[k], staffMap.get(s), date);
            staffAssignList.add(user);
            k++;
        }

        assignedDate.add(date);

        assigned = false;
        FirebaseDatabase.getInstance().getReference("schedule").child(date).setValue(staffAssignList);

        Collections.shuffle(Arrays.asList(staffDataList));
//        Collections.shuffle(Arrays.asList(shiftsArrayString));
    }

    //Return staffs' shifts on particular day
    void newMethod(final String date) {
        progressBar.setVisibility(VISIBLE);
        assigned = false;
        FirebaseDatabase.getInstance().getReference("schedule").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    String dKey = d.getKey();
                    if (dKey.equals(date)) {
                        for (DataSnapshot ds : d.getChildren()) {
                            String name = ds.child("name").getValue(String.class);
                            String shift = ds.child("shift").getValue(String.class);
                            String subject = ds.child("subject").getValue(String.class);
                            String date = ds.child("date").getValue(String.class);

                            Users user = new Users(name, shift, subject, date);

                            assigned = true;
                            arrayList.add(user);
                        }
                        break;
                    }
                }
                if (itemSelected)
                    setEvent();
                progressBar.setVisibility(GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void setEvent() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        View showView = LayoutInflater.from(getContext()).inflate(R.layout.show_events_layout, null);
        RecyclerView recyclerView = showView.findViewById(R.id.eventsRV);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(showView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        eventsRecyclerAdapter = new EventsRecyclerAdapter(showView.getContext(), arrayList);
        recyclerView.setAdapter(eventsRecyclerAdapter);
        eventsRecyclerAdapter.notifyDataSetChanged();

        builder.setView(showView);
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(true);
        progressBar.setVisibility(GONE);
        itemSelected = false;
    }
}
