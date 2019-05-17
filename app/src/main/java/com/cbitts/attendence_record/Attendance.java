package com.cbitts.attendence_record;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Attendance extends ArrayAdapter<Student>
{
    private Activity context;
    List<Student> attendance;
    //CheckBox checkBox;

    public Attendance(Activity context, List<Student> attendance)
    {
        super(context,R.layout.attendance,attendance);
        this.context=context;
        this.attendance=attendance;


    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();


        View listViewItem=inflater.inflate(R.layout.attendance,null,true);

        TextView T1=(TextView)listViewItem.findViewById(R.id.textView1);
        TextView T2=(TextView)listViewItem.findViewById(R.id.textView2);
      //  checkBox=(CheckBox)listViewItem.findViewById(R.id.checkbox);
        //FloatingActionButton floatingActionButton=(FloatingActionButton)listViewItem.findViewById(R.id.fab);

        //floatingActionButton.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
            //}
        //});



        Student student=attendance.get(position);
        T1.setText(student.getRollno());
        T2.setText(student.getName());




        return listViewItem;
    }
}

