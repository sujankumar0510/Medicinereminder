package com.sujanreddy.medicinereminder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.content.Context;
import android.app.TimePickerDialog;
import android.widget.TimePicker;
import java.util.*;
import android.widget.ArrayAdapter;


/**
 * Created by sujanreddy on 7/5/15.
 */
public class AddAppointment extends Activity implements OnClickListener {
    Button save,cancel,clear1,clear2;
    EditText apttitle,date,time,notes;
    Spinner select_doctor,select_reminder;
    String TITLE,DATE,TIME,DOCTOR,REMINDER,NOTES;
    SimpleDateFormat sdf;
    DatePickerDialog dpd;
    DBHelper dbh;
    private final Context myContext = this;
    List<String> list;
    List<String> list2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_appointment);
        apttitle= (EditText) findViewById(R.id.apttitle);
        date= (EditText) findViewById(R.id.date);
        time= (EditText) findViewById(R.id.time);
        notes= (EditText) findViewById(R.id.notes);
        select_doctor= (Spinner) findViewById(R.id.select_doctor);
        select_reminder= (Spinner) findViewById(R.id.select_reminder);
        save= (Button) findViewById(R.id.save);
        cancel= (Button) findViewById(R.id.cancel);
        clear1= (Button) findViewById(R.id.clearfield1);
        clear2= (Button) findViewById(R.id.clearfield2);
        date.setInputType(InputType.TYPE_NULL);
        time.setInputType(InputType.TYPE_NULL);
        date.requestFocus();
        time.requestFocus();
        sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar newCalendar = Calendar.getInstance();
        clear1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                date.setText("");
            }
        });
        clear2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setText("");
            }
        });
        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                date.setText(sdf.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        date.setOnClickListener(this);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddAppointment.this,Main.class);
                AddAppointment.this.startActivity(intent);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TITLE=apttitle.getText().toString();
                DATE=date.getText().toString();
                TIME=time.getText().toString();
                NOTES=notes.getText().toString();
                DOCTOR=String.valueOf(select_doctor.getSelectedItem());
                REMINDER=String.valueOf(select_reminder.getSelectedItem());
                if(DOCTOR.matches("Select Doctor"))
                {
                    DOCTOR=null;
                }
                else
                {
                    DOCTOR=String.valueOf(select_doctor.getSelectedItem());
                }
                if(REMINDER.matches("Select Reminder"))
                {
                    REMINDER=null;
                }
                else
                {
                    REMINDER=String.valueOf(select_reminder.getSelectedItem());
                }
                dbh=new DBHelper(myContext);
                dbh.insertdata_Appointment(TITLE, DATE, TIME, DOCTOR, REMINDER, NOTES);
            }
        });
        time.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddAppointment.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        list = new ArrayList<String>();
        list2=new ArrayList<String>();
        list.add("Select Doctor");
        dbh=new DBHelper(myContext);
        SQLiteDatabase db = dbh.getReadableDatabase();
        final Cursor c = db.rawQuery("SELECT DOCTOR_NAME FROM Doctors", null);
        c.moveToFirst();
        if (c.isFirst()) {
            do {
                String doctorname = c.getString(0);
                list.add(doctorname);
            } while (c.moveToNext());
        }
        list.add("ADD DOCTOR");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_doctor.setAdapter(dataAdapter);
        list2.add("Select Reminder");
        list2.add("before 10minutes");
        list2.add("before 30minutes");
        list2.add("before oneday");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list2);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_reminder.setAdapter(dataAdapter1);

    }


    @Override
    public void onClick(View view) {
        if (view == date) {
            dpd.show();
        }
    }
}
