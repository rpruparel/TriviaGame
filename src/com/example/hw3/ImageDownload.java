package com.example.hw3;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

class ImageDownload extends AsyncTask < String, Void, Bitmap > {
    ImageView iv;
    InputStream in ;
    TriviaActivity activity;
    ProgressBar pb;
    TextView tv;

    public ImageDownload(TriviaActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub

        super.onPreExecute();
        iv = (ImageView) activity.findViewById(R.id.imageView1);
        iv.setImageDrawable(null);
        pb = new ProgressBar(activity);
        pb = (ProgressBar) activity.findViewById(R.id.spinner);
        pb.setVisibility(View.VISIBLE);
        tv = new TextView(activity);
        tv = (TextView) activity.findViewById(R.id.LoadingText);
        tv.setVisibility(View.VISIBLE);
    }

    @Override
    protected Bitmap doInBackground(String...params) {
        // TODO Auto-generated method stub

        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET"); in = con.getInputStream();
            Bitmap image = BitmapFactory.decodeStream( in );

            return image;
        } catch (MalformedURLException e) {
            // TODO: handle exception
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if ( in != null) {
                try { in .close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        // TODO Auto-generated method stub
        pb = new ProgressBar(activity);
        pb = (ProgressBar) activity.findViewById(R.id.spinner);
        pb.setVisibility(View.INVISIBLE);
        tv = new TextView(activity);
        tv = (TextView) activity.findViewById(R.id.LoadingText);
        tv.setVisibility(View.INVISIBLE);
        if (result != null) {
            iv = new ImageView(activity);
            iv = (ImageView) activity.findViewById(R.id.imageView1);
            iv.setImageBitmap(result);

        } else {
        }
    }

}