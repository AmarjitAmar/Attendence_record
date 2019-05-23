package com.cbitts.attendence_record;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


import java.util.List;

public class Attendance extends BaseAdapter {
    public Context mcontext;
    List<Custom> attendance;


    private static class ViewHolder {
        TextView t1, t2;
        CheckBox checkBox;
    }


    public Attendance(Context mcontext, List<Custom> attendance) {
        this.mcontext = mcontext;
        this.attendance = attendance;
    }


    @Override
    public int getCount() {

        return attendance.size();
    }

    public Custom getItem(int position) {
        return attendance.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance, parent, false);
            viewHolder.t1 = (TextView) convertView.findViewById(R.id.textView1);
            viewHolder.t2 = (TextView) convertView.findViewById(R.id.textView2);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);

            result=convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Custom c = attendance.get(position);

        viewHolder.t1.setText(c.getRollno());
        viewHolder.t2.setText(c.getName());

        viewHolder.checkBox.setChecked(c.present);

        return result;
    }
}
