package com.sujanreddy.medicinereminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.DatePickerDialog;
import android.content.Context;
import java.text.SimpleDateFormat;
import android.text.InputType;
import android.view.View.OnClickListener;
import android.view.View;
import java.util.Date;

/**
 * Created by sujanreddy on 7/5/15.
 */
public class AddProfile extends Activity implements OnClickListener{
    Button save,clear,addpic,cancel;
    EditText firstname,lastname;
    String FIRSTNAME,LASTNAME,DOB,EMAIL,ADDRESS;
    String PHONE1,PHONE2;
    Character GENDER;
    RadioGroup gender;
    DBHelper dbh;
    DatePickerDialog dpd;
    EditText dob,phone1,phone2,email,address;
    SimpleDateFormat sdf;
    private final Context myContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_profile);
        save = (Button) findViewById(R.id.save);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        gender = (RadioGroup) findViewById(R.id.gender);
        clear= (Button) findViewById(R.id.clearfield);
        dob= (EditText) findViewById(R.id.dob);
        phone1= (EditText) findViewById(R.id.phone);
        phone2= (EditText) findViewById(R.id.phone1);
        email= (EditText) findViewById(R.id.email);
        address= (EditText) findViewById(R.id.address);
        addpic= (Button) findViewById(R.id.addpic);
        cancel= (Button) findViewById(R.id.cancel);
        dob.setInputType(InputType.TYPE_NULL);
        dob.requestFocus();
        sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar newCalendar = Calendar.getInstance();
        clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dob.setText("");
            }
        });
        dpd = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dob.setText(sdf.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        dob.setOnClickListener(this);
        addpic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FIRSTNAME = firstname.getText().toString();
                LASTNAME = lastname.getText().toString();
                DOB=dob.getText().toString();
                PHONE1= phone1.getText().toString();
                PHONE2=phone2.getText().toString();
                EMAIL=email.getText().toString();
                ADDRESS=address.getText().toString();
                dbh = new DBHelper(myContext);
                dbh.insertdata_profile(FIRSTNAME, LASTNAME, GENDER, DOB, PHONE1, PHONE2, EMAIL, ADDRESS, null);
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
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddProfile.this,Main.class);
                AddProfile.this.startActivity(intent);
            }
        });

    }
    public void onClick(View view) {
        if (view == dob) {
            dpd.show();
        }
    }


}
