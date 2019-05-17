package com.cbitts.attendence_record;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Take_Attendance extends AppCompatActivity {
    //FloatingActionButton fab;
    ListView listView;
    EditText editText;
    DatePickerDialog datePickerDialog;
   // Toolbar toolbar;
    DatabaseReference databaseReference;
    List<Student> attendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take__attendance);
       // fab=(FloatingActionButton)findViewById(R.id.fab);
        databaseReference = FirebaseDatabase.getInstance().getReference("details");
        listView=(ListView)findViewById(R.id.listview);
        editText=(EditText)findViewById(R.id.date);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(calendar.YEAR);
                int month=calendar.get(calendar.MONTH);
                final int day=calendar.get(calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Take_Attendance.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editText.setText(day+"-"+(month+1)+"-"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
     //   toolbar=(Toolbar)findViewById(R.id.Toolbar);
       // toolbar.setTitle("Take Attendance");
        //toolbar.setTitleTextColor(Color.WHITE);
       // toolbar.setBackgroundColor(Color.BLUE);
                attendance=new ArrayList<>();
    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                attendance.clear();

                for (DataSnapshot studentSnapshot:dataSnapshot.getChildren())
                {
                    Student student=studentSnapshot.getValue(Student.class);
                    attendance.add(student);
                }
                Attendance adapter=new Attendance(Take_Attendance.this,attendance);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}




