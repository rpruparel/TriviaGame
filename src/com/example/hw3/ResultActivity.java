package com.example.hw3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ResultActivity extends Activity {

    Button b;
    ProgressBar pb;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getActionBar().setIcon(new ColorDrawable(android.R.color.transparent));

        TextView percent = (TextView) findViewById(R.id.percent);

        Intent intent = getIntent();

        int noOfQues = intent.getIntExtra("noOfQues", 1);
        int correctAns = intent.getIntExtra("correctAns", 1);
        Float ratio = (float)((correctAns * 100.0) / noOfQues);

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setProgress(ratio.intValue());

        percent.setText(ratio.intValue() + "" + "%");

        b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();
            }
        });
        b = (Button) findViewById(R.id.button2);

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                    TriviaActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        finish();
    }
}