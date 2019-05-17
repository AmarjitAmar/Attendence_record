package com.cbitts.attendence_record;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class marks_layout extends ArrayAdapter<Student>
{
    private Activity context;
    List<Student> marks;
    CheckBox checkBox;
    EditText editText;

    public marks_layout(Activity context, List<Student> marks)
    {
        super(context,R.layout.marks_layout,marks);
        this.context=context;
        this.marks=marks;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();


        View listViewItem=inflater.inflate(R.layout.marks_layout,null,true);

        TextView T1=(TextView)listViewItem.findViewById(R.id.textView1);
        TextView T2=(TextView)listViewItem.findViewById(R.id.textView2);
        checkBox=(CheckBox)listViewItem.findViewById(R.id.checkbox);
        editText=(EditText)listViewItem.findViewById(R.id.marks);
        //FloatingActionButton floatingActionButton=(FloatingActionButton)listViewItem.findViewById(R.id.fab);

        //floatingActionButton.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //public void onClick(View v) {
        //  Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
        //}
        //});
        Student student=marks.get(position);
        T1.setText(student.getRollno());
        T2.setText(student.getName());
        return listViewItem;
    }
}