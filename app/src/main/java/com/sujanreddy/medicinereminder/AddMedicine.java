package com.sujanreddy.medicinereminder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.*;
import android.app.AlertDialog;
import java.util.ArrayList;
import java.util.List;
import android.content.DialogInterface;
import android.widget.AdapterView.OnItemSelectedListener;

import javax.xml.datatype.Duration;

/**
 * Created by sujanreddy on 7/5/15.
 */
public class AddMedicine extends Activity{
    EditText medicinename,dosage,othinst,quantity;
    EditText reminder1,reminder2,reminder3,reminder4,reminder5,reminder6,reminder7,reminder8,reminder9,reminder10,reminder11,reminder12,reminder13,reminder14,refill1,refill2;
    EditText reminder15,reminder16,reminder17,reminder18,reminder19,reminder20,reminder21,reminder22,reminder23,reminder24,starttime,endtime,nod,dayintervalnod;
    RadioGroup duration,days,instructions,refill_reminder;
    RadioButton frequency,interval,noofdaysrefill,noofpills;
    Spinner reminder,adddoctor;
    Button cancel,save;
    String MEDICINE_NAME,REMINDER_TIME,DURATION,DAYS,DOSAGE,INSTRUCTIONS,REFILL_REMINDER,QUANTITY;
    DBHelper dbh;
    private final Context myContext = this;
   List<String> list,list1,list3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_medicine);
        medicinename= (EditText) findViewById(R.id.medicine_name);
        dosage= (EditText) findViewById(R.id.dosage);
        othinst= (EditText) findViewById(R.id.othinst);
        frequency= (RadioButton) findViewById(R.id.frequency);
        interval= (RadioButton) findViewById(R.id.interval);
        reminder= (Spinner) findViewById(R.id.reminder);
        adddoctor= (Spinner) findViewById(R.id.adddoctor);
        duration= (RadioGroup) findViewById(R.id.duration);
        days= (RadioGroup) findViewById(R.id.days);
        noofdaysrefill= (RadioButton) findViewById(R.id.noofdaysrefill);
        noofpills= (RadioButton) findViewById(R.id.noofpills);
        instructions= (RadioGroup) findViewById(R.id.instructions);
        refill_reminder= (RadioGroup) findViewById(R.id.instructions);
        cancel= (Button) findViewById(R.id.cancel);
        save= (Button) findViewById(R.id.save);
        quantity= (EditText) findViewById(R.id.quantity);
        dayintervalnod= (EditText) findViewById(R.id.dayintervalnod);
        nod= (EditText) findViewById(R.id.nod);
        refill1= (EditText) findViewById(R.id.refill1);
        refill2= (EditText) findViewById(R.id.refill2);
        reminder1= (EditText) findViewById(R.id.reminder1);
        reminder2= (EditText) findViewById(R.id.reminder2);
        reminder3= (EditText) findViewById(R.id.reminder3);
        reminder4= (EditText) findViewById(R.id.reminder4);
        reminder5= (EditText) findViewById(R.id.reminder5);
        reminder6= (EditText) findViewById(R.id.reminder6);
        reminder7= (EditText) findViewById(R.id.reminder7);
        reminder8= (EditText) findViewById(R.id.reminder8);
        reminder9= (EditText) findViewById(R.id.reminder9);
        reminder10= (EditText) findViewById(R.id.reminder10);
        reminder11= (EditText) findViewById(R.id.reminder11);
        reminder12= (EditText) findViewById(R.id.reminder12);
        reminder13= (EditText) findViewById(R.id.reminder13);
        reminder14= (EditText) findViewById(R.id.reminder14);
        reminder15= (EditText) findViewById(R.id.reminder15);
        reminder16= (EditText) findViewById(R.id.reminder16);
        reminder17= (EditText) findViewById(R.id.reminder17);
        reminder18= (EditText) findViewById(R.id.reminder18);
        reminder19= (EditText) findViewById(R.id.reminder19);
        reminder20= (EditText) findViewById(R.id.reminder20);
        reminder21= (EditText) findViewById(R.id.reminder21);
        reminder22= (EditText) findViewById(R.id.reminder22);
        reminder23= (EditText) findViewById(R.id.reminder23);
        reminder24= (EditText) findViewById(R.id.reminder24);
        starttime= (EditText) findViewById(R.id.start_time);
        endtime= (EditText) findViewById(R.id.end_time);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMedicine.this, Main.class);
                AddMedicine.this.startActivity(intent);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MEDICINE_NAME = medicinename.getText().toString();
                DOSAGE = dosage.getText().toString() + " mg";
                REMINDER_TIME=String.valueOf(reminder.getSelectedItem());
                if(REMINDER_TIME.matches("select frequency")|| REMINDER_TIME.matches("select interval")||REMINDER_TIME.matches(""))
                {
                    REMINDER_TIME="";
                }
                dbh = new DBHelper(myContext);
                dbh.insertdata_medicine(MEDICINE_NAME, REMINDER_TIME, DURATION, DAYS, DOSAGE, QUANTITY, INSTRUCTIONS, REFILL_REMINDER);
            }
        });
        duration.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.continuos) {
                    DURATION = "continuos";
                } else if (checkedId == R.id.noofdays) {
                    nod.setVisibility(View.VISIBLE);
                    DURATION = nod.getText().toString();
                }
            }
        });
        days.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.everyday) {
                    DAYS = "everyday";
                } else if (checkedId == R.id.specificdays) {
                    DAYS = "specificdays";
                } else if (checkedId == R.id.daysinterval) {
                    DAYS = dayintervalnod.getText().toString();
                }
            }
        });
        instructions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.beforefood)
                {
                    INSTRUCTIONS="before food";
                }
                else if(checkedId==R.id.afterfood)
                {
                    INSTRUCTIONS="after food";
                }
                else if(checkedId==R.id.withfood)
                {
                    INSTRUCTIONS="with food";
                }
                else  if(checkedId==R.id.none)
                {
                    INSTRUCTIONS="";
                }
                else
                {
                    INSTRUCTIONS=othinst.getText().toString();
                }
            }
        });
        noofdaysrefill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refill1.setVisibility(View.VISIBLE);
                refill2.setVisibility(View.VISIBLE);
                refill1.setHint("remindes you to refill in");
                refill2.setHint("remindes every");
                refill1.getText().toString();
                refill2.getText().toString();
                REFILL_REMINDER = refill1 + " " + refill2;
            }
        });
        noofpills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refill1.setVisibility(View.VISIBLE);
                refill2.setVisibility(View.VISIBLE);
                refill1.setHint("i have");
                refill2.setHint("remind me every");
                refill1.getText().toString();
                refill2.getText().toString();
                REFILL_REMINDER=refill1+" "+refill2;
            }
        });
        list3=new ArrayList<String>();
        list3.add("Select Doctor");
        dbh=new DBHelper(myContext);
        SQLiteDatabase db = dbh.getReadableDatabase();
        final Cursor c = db.rawQuery("SELECT DOCTOR_NAME FROM Doctors", null);
        c.moveToFirst();
        if (c.isFirst()) {
            do {
                String doctorname = c.getString(0);
                list3.add(doctorname);
            } while (c.moveToNext());
        }
        list3.add("ADD DOCTOR");
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list3);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adddoctor.setAdapter(dataAdapter3);
        list=new ArrayList<String>();
        list1=new ArrayList<String>();
       frequency.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               reminder.setVisibility(View.VISIBLE);
               list.add("select frequency");
               list.add("once a day");
               list.add("twice a day");
               list.add("3 times a day");
               list.add("4 times a day");
               list.add("5 times a day");
               list.add("6 times a day");
               list.add("7 times a day");
               list.add("8 times a day");
               list.add("9 times a day");
               list.add("10 times a day");
               list.add("11 times a day");
               list.add("12 times a day");
               ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(myContext,
                       android.R.layout.simple_spinner_item, list);
               dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               reminder.setAdapter(dataAdapter1);
           }
       });
        interval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminder.setVisibility(View.VISIBLE);
                list1.add("select interval");
                list1.add("Every 1 hour");
                list1.add("Every 2 hours");
                list1.add("Every 3 hours");
                list1.add("Every 4 hours");
                list1.add("Every 6 hours");
                list1.add("Every 8 hours");
                list1.add("Every 12 hours");
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(myContext,
                        android.R.layout.simple_spinner_item, list1);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                reminder.setAdapter(dataAdapter);
            }
        });
       reminder.setOnItemSelectedListener(new OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               REMINDER_TIME = parent.getItemAtPosition(position).toString();
               if (REMINDER_TIME == "once a day") {
                   reminder1.setVisibility(View.VISIBLE);
               }
               if (REMINDER_TIME == "twice a day") {
                   reminder1.setVisibility(View.VISIBLE);
                   reminder2.setVisibility(View.VISIBLE);
               }
               if (REMINDER_TIME == "3 times a day") {
                   reminder1.setVisibility(View.VISIBLE);
                   reminder2.setVisibility(View.VISIBLE);
                   reminder3.setVisibility(View.VISIBLE);
               }
               if (REMINDER_TIME == "4 times a day") {
                   reminder1.setVisibility(View.VISIBLE);
                   reminder2.setVisibility(View.VISIBLE);
                   reminder3.setVisibility(View.VISIBLE);
                   reminder4.setVisibility(View.VISIBLE);
               }
               if (REMINDER_TIME == "5 times a day") {
                   reminder1.setVisibility(View.VISIBLE);
                   reminder2.setVisibility(View.VISIBLE);
                   reminder3.setVisibility(View.VISIBLE);
                   reminder4.setVisibility(View.VISIBLE);
                   reminder5.setVisibility(View.VISIBLE);
               }
               if (REMINDER_TIME == "6 times a day") {
                   reminder1.setVisibility(View.VISIBLE);
                   reminder2.setVisibility(View.VISIBLE);
                   reminder3.setVisibility(View.VISIBLE);
                   reminder4.setVisibility(View.VISIBLE);
                   reminder5.setVisibility(View.VISIBLE);
                   reminder6.setVisibility(View.VISIBLE);
               }
               if (REMINDER_TIME == "7 times a day") {
                   reminder1.setVisibility(View.VISIBLE);
                   reminder2.setVisibility(View.VISIBLE);
                   reminder3.setVisibility(View.VISIBLE);
                   reminder4.setVisibility(View.VISIBLE);
                   reminder5.setVisibility(View.VISIBLE);
                   reminder6.setVisibility(View.VISIBLE);
                   reminder7.setVisibility(View.VISIBLE);
               }
               if (REMINDER_TIME == "8 times a day") {

                   reminder1.setVisibility(View.VISIBLE);
                   reminder2.setVisibility(View.VISIBLE);
                   reminder3.setVisibility(View.VISIBLE);
                   reminder4.setVisibility(View.VISIBLE);
                   reminder5.setVisibility(View.VISIBLE);
                   reminder6.setVisibility(View.VISIBLE);
                   reminder7.setVisibility(View.VISIBLE);
                   reminder8.setVisibility(View.VISIBLE);
               }
               if (REMINDER_TIME == "9 times a day") {
                   reminder1.setVisibility(View.VISIBLE);
                   reminder2.setVisibility(View.VISIBLE);
                   reminder3.setVisibility(View.VISIBLE);
                   reminder4.setVisibility(View.VISIBLE);
                   reminder5.setVisibility(View.VISIBLE);
                   reminder6.setVisibility(View.VISIBLE);
                   reminder7.setVisibility(View.VISIBLE);
                   reminder8.setVisibility(View.VISIBLE);
                   reminder9.setVisibility(View.VISIBLE);
               }
               if (REMINDER_TIME == "10 times a day") {
                   reminder1.setVisibility(View.VISIBLE);
                   reminder2.setVisibility(View.VISIBLE);
                   reminder3.setVisibility(View.VISIBLE);
                   reminder4.setVisibility(View.VISIBLE);
                   reminder5.setVisibility(View.VISIBLE);
                   reminder6.setVisibility(View.VISIBLE);
                   reminder7.setVisibility(View.VISIBLE);
                   reminder8.setVisibility(View.VISIBLE);
                   reminder9.setVisibility(View.VISIBLE);
                   reminder10.setVisibility(View.VISIBLE);
               }
               if (REMINDER_TIME == "11 times a day") {
                   reminder1.setVisibility(View.VISIBLE);
                   reminder2.setVisibility(View.VISIBLE);
                   reminder3.setVisibility(View.VISIBLE);
                   reminder4.setVisibility(View.VISIBLE);
                   reminder5.setVisibility(View.VISIBLE);
                   reminder6.setVisibility(View.VISIBLE);
                   reminder7.setVisibility(View.VISIBLE);
                   reminder8.setVisibility(View.VISIBLE);
                   reminder9.setVisibility(View.VISIBLE);
                   reminder10.setVisibility(View.VISIBLE);
                   reminder11.setVisibility(View.VISIBLE);
               }
               if (REMINDER_TIME == "12 times a day") {
                   reminder1.setVisibility(View.VISIBLE);
                   reminder2.setVisibility(View.VISIBLE);
                   reminder3.setVisibility(View.VISIBLE);
                   reminder4.setVisibility(View.VISIBLE);
                   reminder5.setVisibility(View.VISIBLE);
                   reminder6.setVisibility(View.VISIBLE);
                   reminder7.setVisibility(View.VISIBLE);
                   reminder8.setVisibility(View.VISIBLE);
                   reminder9.setVisibility(View.VISIBLE);
                   reminder10.setVisibility(View.VISIBLE);
                   reminder11.setVisibility(View.VISIBLE);
                   reminder12.setVisibility(View.VISIBLE);
               }


           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

    }


}
