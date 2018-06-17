package com.mad.bmicalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Date;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.mad.bmicalculator.R.drawable.bmi;

public class ResultActivity extends AppCompatActivity {

    TextView tvResult, tvDetails;
    Button btnBack, btnSave, btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResult = (TextView) findViewById(R.id.tvResult);
        tvDetails = (TextView) findViewById(R.id.tvDetails);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnShare = (Button) findViewById(R.id.btnShare);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to Exit?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
               // startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(startMain);
                finish();
            }

        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        final DatabaseHandler db =new DatabaseHandler(this);

        Calendar c= Calendar.getInstance();
        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String formattedDate =df.format(c.getTime());

        Intent ri= getIntent();
        String rest = ri.getStringExtra("resbmi");
        final Double res= Double.parseDouble(rest);

        if (res <  18.5){
            tvResult.setText("Your BMI is: "+res+"\n You are Underweight");
        }
        else if (res==18.5 || res<25){
            tvResult.setText("Your BMI is: "+res+"\n You are Normal");
        }
        else if (25==res || res<30){
            tvResult.setText("Your BMI is: "+res+"\n You are Overweight");
        }
        else if (res>=30){
              tvResult.setText("Your BMI is: "+rest+"\n You are Obese");
         }

          tvDetails.setText("Below 18.5 is Underweight\nBetween 18.5 to 25 is Normal \nBetween 25 to 30 is Overweight \nMore than 30 is Obese");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addBmi(res,formattedDate);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent bi = new Intent(ResultActivity.this,WelcomeActivity.class);
                startActivity(bi);

//                AlertDialog alert = builder.create();
//                alert.setTitle("Exit");
//                alert.show();
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent si = new Intent(Intent.ACTION_SEND);
                String shareBody = tvResult.getText().toString();
                si.putExtra(Intent.EXTRA_TEXT, shareBody);
                si.setType("text/plain");
                startActivity(si);
            }
        });
    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }


        @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to Exit?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.setTitle("Exit");
        alert.show();


    }
    }

























