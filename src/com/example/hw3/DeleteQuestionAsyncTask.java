package com.example.hw3;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class DeleteQuestionAsyncTask extends AsyncTask < String, Void, Void > {
    MainActivity mainActivity;
    ProgressDialog progress;
    AlertDialog alert;

    public DeleteQuestionAsyncTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected Void doInBackground(String...params) {
        try {

            // get base url
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(params[1]);
			
            OutputStreamWriter writer = new OutputStreamWriter(
                con.getOutputStream());
            writer.write(getEncodedParams(params));
            writer.flush();

            con.connect();

            int responseCode = con.getResponseCode();
            if (responseCode == con.HTTP_OK) {
            } else {
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {

        progress = new ProgressDialog(mainActivity);
        progress.setTitle(mainActivity.getResources().getString(
            R.string.delete_dialog_as_deleting));
        progress.setMessage(mainActivity.getResources().getString(
            R.string.delete_dialog_as_del_msg));
        progress.setCancelable(false);

        progress.show();

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void result) {

        progress.cancel();

        super.onPostExecute(result);
    }

    private String getEncodedParams(String[] params)
    throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();

        sb.append("gid=");
        sb.append(URLEncoder.encode(params[2], "UTF_8"));

        return sb.toString();
    }

}