package com.cbitts.attendence_record;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    EditText name, rollno, date, subject, email,
    //address,
    phone;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.res_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.save) {
            add_details();

        }

       if (id == R.id.delete) {
          // Student student=new Student();

           //showUpdateDeleteDialog(id,semester,department,name,subject,rollno,date,phone,email);



      }
      if (id == R.id.update) {
//Student student=new Student();
  //      showUpdateDeleteDialog(student.getId(),student.getSemester(),student.getDept(),student.getName(),student.getRollno(),student.getDate(),student.getContact(),student.getMail());
      }
        return super.onOptionsItemSelected(item);
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
            String Id = databaseReference.push().getKey();

            Student student = new Student(Id, sp1, sp2, sname, subname, roll, time, contact, mail);

            databaseReference.child(Id).setValue(student);

            Toast.makeText(this, "saved data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Required field", Toast.LENGTH_SHORT).show();
        }
    }
  /*  private void showUpdateDeleteDialog(final String id, String semester,String dept,String name,String subject,String rollno,String date,String contact,String mail)
     {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);
       final Spinner  semes = (Spinner) findViewById(R.id.Semester);
       final  Spinner department = (Spinner) findViewById(R.id.Department);
        final EditText sname = (EditText) findViewById(R.id.Name);
        final EditText sub = (EditText) findViewById(R.id.Subject);
         final  EditText roll = (EditText) findViewById(R.id.Roll);
        final EditText email = (EditText) findViewById(R.id.Email);
         final EditText phone = (EditText) findViewById(R.id.Number);
         final EditText Date = (EditText) findViewById(R.id.Date);
         Date.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Calendar calendar = Calendar.getInstance();
                 int year = calendar.get(calendar.YEAR);
                 int month = calendar.get(calendar.MONTH);
                 final int day = calendar.get(calendar.DAY_OF_MONTH);
                 datePickerDialog = new DatePickerDialog(Registration.this, new DatePickerDialog.OnDateSetListener() {
                     @Override
                     public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                         Date.setText(day + "-" + (month + 1) + "-" + year);
                     }
                 }, year, month, day);
                 datePickerDialog.show();
             }
         });



         final Button buttonUpdate = (Button) dialogView.findViewById(R.id.update);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.delete);

        //dialogBuilder.setTitle("details");
        //final AlertDialog b = dialogBuilder.create();
        //b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // String sp1 = semes.getSelectedItem().toString().trim();
                //String sp2 = department.getSelectedItem().toString().trim();
                //String name = sname.getText().toString().trim();
              //  String subname = sub.getText().toString().trim();
               // String time = Date.getText().toString().trim();
            //  String rollno = roll.getText().toString().trim();
          //      String mail = email.getText().toString().trim();
        //        String contact = phone.getText().toString().trim();
      //          if(!TextUtils.isEmpty(name)) {
                 //   update_data(id, sp1, sp2, name, subname, rollno, time, contact, mail);
                   // b.dismiss();
                }
           // }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                 * we will code this method to delete the artist
                 * */
  //              delete_data(id);
    //            b.dismiss();
            }
    /*    });
    }


        // Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();


  //  private  boolean delete_data(String Id) {
        //getting the specified artist referen
    //    DatabaseReference dR = FirebaseDatabase.getInstance().getReference("details").child(Id);
        //removing artist
      //  dR.removeValue();
        //getting the tracks reference for the specified artist
        //Toast.makeText(getApplicationContext(), "Data Deleted", Toast.LENGTH_LONG).show();
        //return true;
    //}
    //private boolean update_data(String Id, String sp1, String sp2,String name,String subname,String rollno,String time,String contact,String mail) {
        //getting the specified artist reference
      //  DatabaseReference dR = FirebaseDatabase.getInstance().getReference("details").child(Id);
        //updating artist
        //Student student = new Student(Id, sp1, sp2, name, subname, rollno, time, contact, mail);
        //dR.setValue(student);
        //Toast.makeText(getApplicationContext(), "Artist Updated", Toast.LENGTH_LONG).show();
        //return true;
    }


}
*/




