package com.example.hw3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    ProgressDialog deleteProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().hide();

        // start button
        Button b = (Button) findViewById(R.id.buttonStart);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(),
                    TriviaActivity.class);
                startActivity(intent);

            }
        });

        // create button
        b = (Button) findViewById(R.id.buttonCreate);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Disabled for security purposes.", Toast.LENGTH_LONG).show();
            }
        });

        b = (Button) findViewById(R.id.buttonDelete);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                deleteAlert();
            }
        });

        // exit button
        b = (Button) findViewById(R.id.buttonExit);
        b.setOnClickListener(new View.OnClickListener() {

            @
            Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void deleteAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(
            getResources().getString(R.string.delete_dialog_yes),
            new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    String url = getResources().getString(
                        R.string.delete_all_url);
                    String method = getResources().getString(
                        R.string.method_POST);
                    String id = getResources().getString(R.string.group_id);

                    new DeleteQuestionAsyncTask(MainActivity.this).execute(
                        url, method, id);

                }
            });

        builder.setNegativeButton(
            getResources().getString(R.string.delete_dialog_no),
            new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

        builder.setTitle(getResources().getString(R.string.delete_dialog_title));
        builder.setMessage(getResources().getString(
            R.string.delete_dialog_confirm));
        builder.setCancelable(false);

        AlertDialog alert = builder.create();

        alert.show();
    }
}