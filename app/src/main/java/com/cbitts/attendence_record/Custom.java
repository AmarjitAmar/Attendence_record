package com.cbitts.attendence_record;

import android.widget.ArrayAdapter;

public class Custom   {
    private  String id;
    private  String rollno;
    private  String name;
 //   private  String date;
    boolean present=false;

   // public Custom() {
    //}

    public Custom(String id, String rollno, String name, boolean present) {
        this.id = id;
        this.rollno = rollno;
        this.name = name;
   //     this.date = date;
        this.present = present;
    }

    public String getId() {
        return id;
    }

    public String getRollno() {
        return rollno;
    }

    public String getName() {
        return name;
    }

   // public  String getDate()
    //{
      //  return  date;
    //}

    public boolean isPresent() {
        return present;
    }
}
