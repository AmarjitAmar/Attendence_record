package com.cbitts.attendence_record;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Marks extends AppCompatActivity {
    ListView listView;
    EditText editText;
    DatePickerDialog datePickerDialog;
    // Toolbar toolbar;
    DatabaseReference databaseReference;
    List<Student> marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);
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
                datePickerDialog = new DatePickerDialog(Marks.this, new DatePickerDialog.OnDateSetListener() {
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
        marks=new ArrayList<>();
    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                marks.clear();

                for (DataSnapshot studentSnapshot:dataSnapshot.getChildren())
                {
                    Student student=studentSnapshot.getValue(Student.class);
                    marks.add(student);
                }
                marks_layout adapter=new marks_layout(Marks.this,marks);
                listView.setAdapter( adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}