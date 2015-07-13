package com.example.hw3;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.os.AsyncTask;
import android.util.Log;

public class CreateQuestionAsyncTask extends AsyncTask < String, Void, Void > {
    @Override
    protected Void doInBackground(String...params) {
        try {
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    private String getEncodedParams(String[] params)
    throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();

        sb.append("gid=");
        sb.append(URLEncoder.encode(params[2], "UTF_8"));
        sb.append("&");

        sb.append("q=");
        sb.append(URLEncoder.encode(params[3], "UTF_8"));

        return sb.toString();
    }
}