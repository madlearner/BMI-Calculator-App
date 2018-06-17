package com.mad.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    TextView tvHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        tvHistory= (TextView) findViewById(R.id.tvHistory);

        DatabaseHandler db= new DatabaseHandler(this);
       String r= db.getBmi();
        tvHistory.setText(r);
    }

}
