package com.example.hw3;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.xml.sax.SAXException;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView.FindListener;
import android.widget.ProgressBar;

public class GetQuestionsAsyncTask extends
AsyncTask < String, Void, ArrayList < Question >> {
    ArrayList < Question > questionList;
    TriviaActivity triviaActivity;
    ProgressDialog pd;

    public GetQuestionsAsyncTask(TriviaActivity activity) {
        triviaActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(triviaActivity);
        pd.setTitle("Loading Questions");
        pd.setMessage("Loading");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected ArrayList < Question > doInBackground(String...params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            // get status code
            int statusCode = con.getResponseCode();

            // check status code and get question input stream
            if (statusCode == HttpURLConnection.HTTP_OK) {
                InputStream in = con.getInputStream();
                questionList = QuestionStreamParser.parseQuestion( in );
                return questionList;
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

    // send question list back to trivia
    @Override
    protected void onPostExecute(ArrayList < Question > result) {
        triviaActivity.getQuestions(questionList);
        super.onPostExecute(result);
        pd.dismiss();
    }

    @Override
    protected void onProgressUpdate(Void...values) {
        // TODO Auto-generated method stub
        super.onProgressUpdate(values);
    }

    static public interface QuestionData {
        public void getQuestions(ArrayList < Question > questions);
    }

}