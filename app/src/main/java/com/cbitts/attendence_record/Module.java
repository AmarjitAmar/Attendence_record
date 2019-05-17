package com.cbitts.attendence_record;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;


public class Module extends AppCompatActivity {

    GridView gv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        gv = (GridView) findViewById(R.id.grid);

        gv.setAdapter(new Grid1(Module.this));

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(Module.this, Registration.class));
                        break;
                    case 1:
                        startActivity(new Intent(Module.this, Take_Attendance.class));
                        break;
                    case 2:
                        startActivity(new Intent(Module.this, Assignment.class));
                        break;
                    case 3:
                        startActivity(new Intent(Module.this, Marks.class));
                        break;
                }
            }
        });


    }
}