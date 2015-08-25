package com.sujanreddy.medicinereminder;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MedicineReminder";


    // Contacts table name
    private static final String TABLE_NAME = "Profiles";
    private static final String TABLE_NAME1 = "Medicines";
    private static final String TABLE_NAME2 = "Doctors";
    private static final String TABLE_NAME3 = "Appointments";
    int rows, cols;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(id INTEGER PRIMARY KEY,FIRSTNAME VARCHAR,LASTNAME VARCHAR,GENDER CHAR,DOB DATETIME,PHONE INT(10),PHONE2 INT(10),EMAIL VARCHAR,ADDRESS VARCHAR,PROFILEPIC BLOB);");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME1 +
                "(id INTEGER PRIMARY KEY,MEDICINE_NAME VARCHAR,REMINDER_TIME VARCHAR,DURATION INT(10),DAYS VARCHAR,DOSAGE VARCHAR,QUANTITY REAL,INSTRUCTIONS VARCHAR,REFILL_REMINDER INT(10));");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME2 +
                "(id INTEGER PRIMARY KEY,DOCTOR_NAME VARCHAR,GENDER CHAR,PHONE INT(10),PHONE2 INT(10),SPECIALITY VARCHAR,EMAIL VARCHAR,ADDRESS VARCHAR,PROFILEPIC BLOB);");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME3 +
                "(id INTEGER PRIMARY KEY,TITLE VARCHAR,DATE DATETIME,TIME DATETIME,DOCTOR VARCHAR,REMINDER VARCHAR,NOTES VARCHAR);");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        // Create tables again
        onCreate(db);
    }
    public void insertdata_profile(String FIRSTNAME, String LASTNAME, Character GENDER, String DOB, String PHONE, String PHONE2, String EMAIL, String ADDRESS, Blob PROFILEPIC) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_NAME + " Values (null,'" + FIRSTNAME + "','" + LASTNAME + "','" + GENDER + "','" + DOB + "','" + PHONE + "','" + PHONE2 + "','" + EMAIL + "','" + ADDRESS + "','" + PROFILEPIC + "');");
    }
    public void insertdata_medicine(String MEDICINE_NAME, String REMINDER_TIME, String DURATION, String DAYS, String DOSAGE,String QUANTITY, String INSTRUCTIONS, String REFILL_REMINDER) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_NAME1 + " Values (null,'" + MEDICINE_NAME + "','" + REMINDER_TIME + "','" + DURATION + "','" + DAYS + "','" + DOSAGE +"','"+QUANTITY+ "','" + INSTRUCTIONS + "','" + REFILL_REMINDER +"');");
    }
    public void insertdata_doctor(String DOCTOR_NAME,Character GENDER,String PHONE1, String PHONE2,String SPECIALITY, String EMAIL, String ADDRESS,Blob PROFILEPIC) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_NAME2 + " Values (null,'" + DOCTOR_NAME + "','" +GENDER+ "','" + PHONE1 +"','"+PHONE2+ "','"+SPECIALITY+"','" +EMAIL+ "','" +ADDRESS+"','" + PROFILEPIC +"');");
    }
    public void insertdata_Appointment(String TITLE,String DATE,String TIME, String DOCTOR,String REMINDER, String NOTES) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_NAME3 + " Values (null,'" + TITLE + "','" +DATE+ "','" + TIME +"','"+DOCTOR+ "','"+REMINDER+"','" +NOTES+"');");
    }
}