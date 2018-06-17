package com.mad.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge, etEmail;
    Button btnSave;
    SharedPreferences sp;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnSave = (Button) findViewById(R.id.btnSave);
        sp = getSharedPreferences("MyP1", MODE_PRIVATE);

        String s = sp.getString("name", "");
        if (!s.equals("")) {
            Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
//            i.putExtra("name", name);
            startActivity(i);
            finish();
        } else {
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String n = etName.getText().toString();
                    String age = (etAge.getText().toString());
                    String email = etEmail.getText().toString();

                    if (n.length() == 0) {
                        etName.setError("Please Enter Name");
                        etName.requestFocus();
                        return;
                    }

                    if (age.length() == 0) {
                        etAge.setError("Please Enter Age");
                        etAge.requestFocus();
                        return;
                    }
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        etEmail.setError("Please Enter Valid EmailId");
                        etEmail.requestFocus();
                        return;
                    }

                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("name", n);
                    editor.putString("a", age);
                    editor.putString("e", email);
                    editor.commit();

                    Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
                    startActivity(i);
                    finish();
                }
            });
        }
    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Intent si= new Intent(MainActivity.this, WelcomeActivity.class);
//        startActivity(si);
//        finish();
//    }


}



















