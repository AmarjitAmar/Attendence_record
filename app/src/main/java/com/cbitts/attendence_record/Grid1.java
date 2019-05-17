package com.cbitts.attendence_record;

import android.content.Context;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Grid1 extends BaseAdapter {

    int[] images = {
            R.drawable.regis,
            R.drawable.attend,
            R.drawable.assignment,
            R.drawable.marks,
    };
    String[] arr = {"Registration", "Attendance", "Assignment", "Marks"


    };
    Context cn;


    Grid1(Context cn) {
        this.cn = cn;
    }

    @Override
    public int getCount() {
        return images.length;

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lv = (LayoutInflater) cn.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = lv.inflate(R.layout.grid1, null);
        ImageView iv=(ImageView)convertView.findViewById(R.id.imageView);
        TextView tx=(TextView)convertView.findViewById(R.id.textView);
        iv.setImageResource(images[position]);
        tx.setText(arr[position]);
        return convertView;

    }
}
