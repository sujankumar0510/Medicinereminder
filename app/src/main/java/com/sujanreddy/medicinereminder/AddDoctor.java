package com.sujanreddy.medicinereminder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Button;

/**
 * Created by sujanreddy on 7/5/15.
 */
public class AddDoctor extends Activity {
    String DOCTOR_NAME,PHONE1,PHONE2,SPECIALITY,EMAIL,ADDRESS;
    Character GENDER;
    EditText doctorname,phone1,phone2,speciality,email,address;
    RadioGroup gender;
    Button save,cancel,addpic;
    DBHelper dbh;
    private final Context myContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_doctor);
        doctorname= (EditText) findViewById(R.id.doctorname);
        phone1= (EditText) findViewById(R.id.phone1);
        phone2= (EditText) findViewById(R.id.phone2);
        speciality= (EditText) findViewById(R.id.speciality);
        email= (EditText) findViewById(R.id.email);
        address= (EditText) findViewById(R.id.address);
        gender= (RadioGroup) findViewById(R.id.gender);
        save= (Button) findViewById(R.id.save);
        cancel= (Button) findViewById(R.id.cancel);
        addpic= (Button) findViewById(R.id.addpic);
        gender.requestFocus();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DOCTOR_NAME=doctorname.getText().toString();
                PHONE1=phone1.getText().toString();
                PHONE2=phone2.getText().toString();
                SPECIALITY=speciality.getText().toString();
                EMAIL=email.getText().toString();
                ADDRESS=address.getText().toString();
               dbh=new DBHelper(myContext);
                dbh.insertdata_doctor(DOCTOR_NAME,GENDER,PHONE1,PHONE2,SPECIALITY,EMAIL,ADDRESS,null);
            }
        });
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.female) {
                    GENDER = 'f';
                } else {
                    GENDER = 'm';
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddDoctor.this,Main.class);
                AddDoctor.this.startActivity(intent);
            }
        });
    }
}
