package com.example.hw3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class QuestionTestActivity extends Activity {

    @
    Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_test);

        Question q = new Question();
        q.setQuestion("What is a test Question?");
        q.setCorrectAnswerIndex(0);
        q.setImageURL("");
        q.addAnswerChoice("first choice");
        q.addAnswerChoice("second choice");
        q.addAnswerChoice("third choice");

        String url = getResources().getString(R.string.get_all_url);

    }
}