package com.cbitts.attendence_record;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class student_detail extends AppCompatActivity {
    //    ListView listView;
    DatabaseReference databaseReference;
    List<Student> studentList;
    private Context context = null;

    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        databaseReference = FirebaseDatabase.getInstance().getReference("details");
        studentList = new ArrayList<>();
        tableLayout = (TableLayout) findViewById(R.id.tablelayout);

        context = getApplicationContext();

        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        layoutParams.setMargins(10, 10, 30, 10);

        TableRow tableRow = new TableRow(context);

        TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParams);
        textView.setText("Roll No.");
        textView.setTextSize(20f);
        textView.setTextColor(Color.BLACK);
        tableRow.addView(textView, 0);

        TextView textView1 = new TextView(context);
        textView1.setLayoutParams(layoutParams);
        textView1.setText("Student Name");
        tableRow.addView(textView1, 1);

        TextView textView2 = new TextView(context);
        textView2.setText("Semester");
        textView2.setLayoutParams(layoutParams);
        tableRow.addView(textView2, 2);

        TextView textView3 = new TextView(context);
        textView3.setText("Department");
        textView3.setLayoutParams(layoutParams);
        tableRow.addView(textView3, 3);

        TextView textView4 = new TextView(context);
        textView4.setText("Subject");
        textView4.setLayoutParams(layoutParams);
        tableRow.addView(textView4, 4);

        TextView textView5 = new TextView(context);
        textView5.setText("Registration Date");
        textView5.setLayoutParams(layoutParams);
        tableRow.addView(textView5, 5);

        TextView textView6 = new TextView(context);
        textView6.setText("Contact No.");
        textView6.setLayoutParams(layoutParams);
        tableRow.addView(textView6, 6);

        TextView textView7 = new TextView(context);
        textView7.setText("Email");
        textView7.setLayoutParams(layoutParams);
        tableRow.addView(textView7, 7);

        tableLayout.addView(tableRow);

        fetchData();
    }

    public void setStudentData(Student s) {
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        layoutParams.setMargins(10, 5, 30, 5);

        TableRow tableRow = new TableRow(context);

        TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParams);
        textView.setText(s.getRollno());
        textView.setTextColor(Color.BLACK);
        tableRow.addView(textView, 0);

        TextView textView1 = new TextView(context);
        textView1.setLayoutParams(layoutParams);
        textView1.setText(s.getName());
        tableRow.addView(textView1, 1);

        TextView textView2 = new TextView(context);
        textView2.setText(s.getSemester());
        textView2.setLayoutParams(layoutParams);
        tableRow.addView(textView2, 2);

        TextView textView3 = new TextView(context);
        textView3.setText(s.getDept());
        textView3.setLayoutParams(layoutParams);
        tableRow.addView(textView3, 3);

        TextView textView4 = new TextView(context);
        textView4.setText(s.getSubject());
        textView4.setLayoutParams(layoutParams);
        tableRow.addView(textView4, 4);

        TextView textView5 = new TextView(context);
        textView5.setText(s.getDate());
        textView5.setLayoutParams(layoutParams);
        tableRow.addView(textView5, 5);

        TextView textView6 = new TextView(context);
        textView6.setText(s.getContact());
        textView6.setLayoutParams(layoutParams);
        tableRow.addView(textView6, 6);

        TextView textView7 = new TextView(context);
        textView7.setText(s.getMail());
        textView7.setLayoutParams(layoutParams);
        tableRow.addView(textView7, 7);

        tableLayout.addView(tableRow);
    }

    protected void fetchData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               studentList.clear();

                for (DataSnapshot studentSnapshot:dataSnapshot.getChildren())
                {
                    Student student=studentSnapshot.getValue(Student.class);
                    studentList.add(student);
                }


                for(int i=0;i<studentList.size();i++)
                {
                    for(int j=i+1;j<studentList.size();j++)
                    {
                        if (Integer.parseInt(studentList.get(i).getRollno()) > Integer.parseInt(studentList.get(j).getRollno())) {
                            Student s = studentList.get(j);
                            studentList.set(j, studentList.get(i));
                            studentList.set(i, s);
                        }
                    }
                }

                for(Student s : studentList)
                {
                    setStudentData(s);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}




