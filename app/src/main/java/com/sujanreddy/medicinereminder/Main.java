package com.sujanreddy.medicinereminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.navdrawer.SimpleSideDrawer;
import android.app.ActionBar;
import android.view.*;
import android.widget.*;

public class Main extends Activity{

    SimpleSideDrawer slide_me;
    Button addprof,addmed,adddoc,addapt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slide_me = new SimpleSideDrawer(this);
        slide_me.setLeftBehindContentView(R.layout.left_menu);
        slide_me.setRightBehindContentView(R.layout.right_menu);
        ActionBar mActionBar = getActionBar();
       mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.main_actionbar, null);
        TextView title= (TextView) mCustomView.findViewById(R.id.title);
        title.setText("Medicine Reminder");
        ImageButton ro = (ImageButton) mCustomView.findViewById(R.id.optionsright);
        ImageButton lo = (ImageButton) mCustomView.findViewById(R.id.optionsleft);
        ro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                slide_me.toggleRightDrawer();
            }
        });
        lo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                slide_me.toggleLeftDrawer();

            }
        });
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        addprof= (Button) findViewById(R.id.addprof);
        addmed= (Button) findViewById(R.id.addmed);
        adddoc= (Button) findViewById(R.id.adddoc);
        addapt= (Button) findViewById(R.id.addapt);
        addprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main.this,AddProfile.class);
                Main.this.startActivity(intent);
            }
        });
        addmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main.this,AddMedicine.class);
                Main.this.startActivity(intent);
            }
        });
        adddoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main.this,AddDoctor.class);
                Main.this.startActivity(intent);
            }
        });
        addapt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main.this,AddAppointment.class);
                Main.this.startActivity(intent);
            }
        });
    }

}
