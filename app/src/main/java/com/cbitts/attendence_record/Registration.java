package com.cbitts.attendence_record;


import android.app.DatePickerDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;
import hotchemi.android.rate.AppRate;

public class Registration extends AppCompatActivity {

    Spinner semester, department;
    EditText name, rollno, date, subject, email, phone;
    FloatingActionButton floatingActionButton;
    DatabaseReference databaseReference;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        databaseReference = FirebaseDatabase.getInstance().getReference("details");


        AppRate.with(this)
                .setInstallDays(0)
                .setLaunchTimes(3)
                .setRemindInterval(2)
                .monitor();
        AppRate.showRateDialogIfMeetsConditions(this);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_details();
            }
        });


        semester = (Spinner) findViewById(R.id.spinner);
        department = (Spinner) findViewById(R.id.spi);
        name = (EditText) findViewById(R.id.sname);
        subject = (EditText) findViewById(R.id.subject);
        rollno = (EditText) findViewById(R.id.roll);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.number);
        date = (EditText) findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(calendar.YEAR);
                int month = calendar.get(calendar.MONTH);
                final int day = calendar.get(calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Registration.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(day + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void add_details() {
        String sp1 = semester.getSelectedItem().toString().trim();
        String sp2 = department.getSelectedItem().toString().trim();
        String sname = name.getText().toString().trim();
        String subname = subject.getText().toString().trim();
        String time = date.getText().toString().trim();
        String roll = rollno.getText().toString().trim();
        String mail = email.getText().toString().trim();
        String contact = phone.getText().toString().trim();

        if (!TextUtils.isEmpty(sname)) {
            if (!TextUtils.isEmpty(sp1)) {
                if (!TextUtils.isEmpty(sp2)) {
                    if (!TextUtils.isEmpty(subname)) {
                        if (!TextUtils.isEmpty(time)) {
                            if (!TextUtils.isEmpty(roll)) {
                                if (!TextUtils.isEmpty(mail)) {
                                    if (!TextUtils.isEmpty(contact)) {
                                        String Id = databaseReference.push().getKey();

                                        Student student = new Student(Id, sp1, sp2, sname, subname, roll, time, contact, mail);

                                        databaseReference.child(Id).setValue(student);

                                        Toast.makeText(this, "saved data", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else {
            Toast.makeText(this, "Required field", Toast.LENGTH_SHORT).show();
        }
    }
}


