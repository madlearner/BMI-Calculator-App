package com.mad.bmicalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.ActivityChooserModel;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.InflaterInputStream;

import static android.R.*;

public class WelcomeActivity extends AppCompatActivity {

    TextView tvWelcome, tvHeight, tvInch, tvFeet, tvWeight;
    Button btnCalculate, btnView;
    EditText etWeight;
    Spinner spnFeet, spnInch;
    int answer;
    double a = 0.305, b = 0.0255;
    int f, in;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        tvHeight = (TextView) findViewById(R.id.tvHeight);
        tvInch = (TextView) findViewById(R.id.tvInch);
        tvFeet = (TextView) findViewById(R.id.tvFeet);
        tvWeight = (TextView) findViewById(R.id.tvWeight);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnView = (Button) findViewById(R.id.btnView);
        etWeight = (EditText) findViewById(R.id.etWeight);
        spnFeet = (Spinner) findViewById(R.id.spnFeet);
        spnInch = (Spinner) findViewById(R.id.spnInch);

        final DatabaseHandler db=new DatabaseHandler(this);

        final ArrayList<Integer> feet = new ArrayList<>();
        feet.add(1);
        feet.add(2);
        feet.add(3);
        feet.add(4);
        feet.add(5);
        feet.add(6);
        feet.add(7);
        feet.add(8);

        final ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, feet);
        spnFeet.setAdapter(adapter);

        final ArrayList<Integer> inch = new ArrayList<>();
        inch.add(0);
        inch.add(1);
        inch.add(2);
        inch.add(3);
        inch.add(4);
        inch.add(5);
        inch.add(6);
        inch.add(7);
        inch.add(8);
        inch.add(9);
        inch.add(10);
        inch.add(11);

        final ArrayAdapter<Integer> adapter1 = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, inch);
        spnInch.setAdapter(adapter1);

        spnFeet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                f = Integer.parseInt(parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spnInch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                in = Integer.parseInt(parent.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sp=getSharedPreferences("MyP1", MODE_PRIVATE);
        String msg = sp.getString("name","");
        tvWelcome.setText("Welcome " + msg);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etWeight.getText().toString().length()==0)
                {
                    etWeight.setError("Enter your Weight");
                    etWeight.requestFocus();
                    return;
                }

                double w= Double.parseDouble(etWeight.getText().toString());
                double ht = (f*a)+(in*b);                       //a=0.305 b=0.0255
                double result= w/(ht*ht);
                final String resul= String.valueOf(Double.parseDouble(String.valueOf(result)));

                AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                builder.setMessage("Your BMI is: "+result);
                builder.setCancelable(false);

                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent ii= new Intent(WelcomeActivity.this, ResultActivity.class);
                        ii.putExtra("resbmi",resul);
                        startActivity(ii);
                    }

                });

                AlertDialog alert = builder.create();
                alert.setTitle("Calculated BMI");
                alert.show();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent his=new Intent(WelcomeActivity.this, HistoryActivity.class);
                startActivity(his);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.about:
                Toast.makeText(this, "App developed by Madhavi Soni", Toast.LENGTH_SHORT).show();
                break;

            case R.id.website:
                Intent br = new Intent(Intent.ACTION_VIEW);
                br.setData(Uri.parse("http://madmandalic.blogspot.in/"));
                startActivity(br);
                break;
        }

        return super.onOptionsItemSelected(item);

    }

//    private double ResultBmi(double wg, double ht) {
//        return (wg / (ht * ht));
//    }

}



