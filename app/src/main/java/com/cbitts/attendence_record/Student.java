package com.cbitts.attendence_record;

public class Student {
    private String id;
    private String semester;
    private String dept;
    private String name;
    private String subject;
    private  String rollno;
    private  String date;
    private String contact;
    private String mail;
   
   // boolean present= false;
   // private String address;


    public Student(String id,String semester,String dept,String name,String subject,String rollno,String date,String contact,String mail)
    {
        this.id=id;
        this.semester=semester;
        this.dept=dept;
        this.name=name;
        this.subject=subject;
        this.rollno=rollno;
        this.date=date;
        this.contact=contact;
        this.mail=mail;
     //   this.present=present;
        


        // this.address=address;
    }



    public  String getId()
    {return id;}
    public String getSemester()
    {return semester; }
    public String getDept()
    {return dept; }
    public String getName()
    {return name;}
    public String getSubject()
    {return subject; }
    public String getRollno()
    {return rollno;}
    public String getDate()
    {return date;}
    public String getContact()
    {return contact;}
    public String getMail()
    {return mail;}

    
   // public String getAddress()
    //{return address;}

}
