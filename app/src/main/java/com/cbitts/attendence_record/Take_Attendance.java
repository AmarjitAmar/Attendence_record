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

public class Take_Attendance extends AppCompatActivity {
    FloatingActionButton fab;
    ListView listView;
    EditText editText;
    Attendance adapter;
    DatePickerDialog datePickerDialog;
    DatabaseReference databaseReference;
    List<Student> attendance;
    List<Custom> present;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take__attendance);

        fab=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        listView=(ListView)findViewById(R.id.listview_attendance);
        editText=(EditText)findViewById(R.id.date);

        attendance=new ArrayList<>();
        present = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("details");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String time=editText.getText().toString().trim();

               if(TextUtils.isEmpty(time))
               {
                   Toast.makeText(Take_Attendance.this, "fill the date", Toast.LENGTH_SHORT).show();
                   return;
               }

                StringBuilder sb = new StringBuilder();

                Toast.makeText(Take_Attendance.this, ""+attendance.size(), Toast.LENGTH_SHORT).show();

                for(int i=0;i<attendance.size();i++)
                {
                    if(present.get(i).isPresent())
                    {
                        sb.append(attendance.get(i).getName()+"\n");
                    }
                }

                Toast.makeText(Take_Attendance.this, sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });


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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student dataModel = attendance.get(position);
                dataModel.present = !dataModel.present;
                adapter.notifyDataSetChanged();

                Toast.makeText(Take_Attendance.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                attendance.clear();
                present.clear();
                for (DataSnapshot studentSnapshot:dataSnapshot.getChildren())
                {
                    Student student=studentSnapshot.getValue(Student.class);
                    attendance.add(student);
                    Custom p = new Custom(student.getId(), student.getRollno(), student.getName(), false);
                    present.add(p);
                }

                adapter=new Attendance(Take_Attendance.this, present);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}