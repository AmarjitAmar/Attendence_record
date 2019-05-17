package com.cbitts.attendence_record;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentList extends ArrayAdapter<Student>
{
    private Activity context;
    List<Student> studentList;

    public  StudentList(Activity context, List<Student> studentList)
    {
        super(context,R.layout.list_layout,studentList);
        this.context=context;
        this.studentList=studentList;

    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View listViewItem=inflater.inflate(R.layout.list_layout,null,true);

        TextView T1=(TextView)listViewItem.findViewById(R.id.textView1);
        TextView T2=(TextView)listViewItem.findViewById(R.id.textView2);
        TextView T3=(TextView)listViewItem.findViewById(R.id.textView3);
        TextView T4=(TextView)listViewItem.findViewById(R.id.textView4);
        TextView T5=(TextView)listViewItem.findViewById(R.id.textView5);

        TextView T6=(TextView)listViewItem.findViewById(R.id.textView6);
        TextView T7=(TextView)listViewItem.findViewById(R.id.textView7);
        TextView T8=(TextView)listViewItem.findViewById(R.id.textView8);

        Student student=studentList.get(position);
        T1.setText(student.getRollno());
        T2.setText(student.getName());
        T3.setText(student.getSemester());
        T4.setText(student.getDept());
        T5.setText(student.getSubject());
        T6.setText(student.getDate());
        T7.setText(student.getContact());
        T8.setText(student.getMail());



        return listViewItem;
    }
}

