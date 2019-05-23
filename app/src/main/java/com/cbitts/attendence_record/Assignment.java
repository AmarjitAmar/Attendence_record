package com.cbitts.attendence_record;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Assignment extends AppCompatActivity {
    ListView listView;
    EditText editText;
    DatePickerDialog datePickerDialog;
    FloatingActionButton floatingActionButton;

    DatabaseReference databaseReference;
    List<Student> assignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        databaseReference = FirebaseDatabase.getInstance().getReference("details");
        listView=(ListView)findViewById(R.id.listview);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Assignment.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date=editText.getText().toString().trim();

                if(!TextUtils.isEmpty(date))
                {

                    Toast.makeText(Assignment.this, "Saved", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(Assignment.this, "Required Field", Toast.LENGTH_SHORT).show();
                }

            }
        });
        editText=(EditText)findViewById(R.id.date);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(calendar.YEAR);
                int month=calendar.get(calendar.MONTH);
                final int day=calendar.get(calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Assignment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editText.setText(day+"-"+(month+1)+"-"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        assignment=new ArrayList<>();
    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                assignment.clear();

                for (DataSnapshot studentSnapshot:dataSnapshot.getChildren())
                {
                    Student student=studentSnapshot.getValue(Student.class);
                    assignment.add(student);
                }
                assignment_layout adapter=new assignment_layout(Assignment.this,assignment);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

