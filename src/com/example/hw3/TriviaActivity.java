package com.example.hw3;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TriviaActivity extends Activity implements
GetQuestionsAsyncTask.QuestionData {
    ArrayList < Question > questionList;
    ProgressDialog pd;
    ProgressBar pb;
    Button bt;
    TextView tv;
    ArrayList < String > answerChoices;
    RadioButton rd, rdNew;
    RadioGroup rg;
    LinearLayout linearLayout;
    LinearLayout ll;
    int currentQuestionId = 0;
    Button q, n;
    int noOfQues, correctAns = 0;
    ImageView iv;
    private CountDownTimer countDownTimer;
    boolean timerHasStarted = false;
    private final long startTime = 24 * 1000;
    private final long interval = 1 * 1000;
    CharSequence timerSec = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        getActionBar().setIcon(new ColorDrawable(android.R.color.transparent));


        new GetQuestionsAsyncTask(this).execute(getResources().getString(
            R.string.get_all_url));

        // log questions as test
        countDownTimer = new MyCountDownTimer(startTime, interval);

        timerSec = String.valueOf(startTime / 1000);

        bt = (Button) findViewById(R.id.button2);
        bt.setText(" Time Left: " + timerSec + " Seconds");

        q = (Button) findViewById(R.id.button3);
        q.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });

        n = (Button) findViewById(R.id.button4);
        n.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (rg.getCheckedRadioButtonId() != -1) {
                    Question question = questionList.get(currentQuestionId);

                    if (rg.getCheckedRadioButtonId() == question
                        .getCorrectAnswerIndex()) {
                        correctAns++;
                    }

                }

                timerHasStarted = false;
                if (currentQuestionId < questionList.size() - 1) {
                    setTriviaView(++currentQuestionId);
                } else {
                    Intent intent = new Intent(TriviaActivity.this,
                        ResultActivity.class);
                    intent.putExtra("noOfQues", noOfQues);
                    intent.putExtra("correctAns", correctAns);
                    countDownTimer.cancel();
                    timerHasStarted = false;
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    public void setTriviaView(int id) {

        Question question = questionList.get(id);

        if (question.getImageURL() != null && question.getImageURL().length() > 0) {
            new ImageDownload(this).execute(question.getImageURL());
        } else {

            iv = (ImageView) findViewById(R.id.imageView1);
            iv.setImageDrawable(getResources().getDrawable(
                R.drawable.no_image_available));

        }

        bt = (Button) findViewById(R.id.button1);
        bt.setText("Q" + (id + 1));
        bt = (Button) findViewById(R.id.button2);
        tv = (TextView) findViewById(R.id.textView1);
        tv.setText(question.getQuestion());

        answerChoices = question.getAnswerChoices();

        rg = (RadioGroup) findViewById(R.id.radioGroup1);

        rg.removeAllViews();
        rg.clearCheck();

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout4);
        for (int i = 0; i < answerChoices.size(); i++) {
            rdNew = new RadioButton(this);
            rdNew.setText(answerChoices.get(i));
            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            rdNew.setLayoutParams(buttonLayoutParams);
            rdNew.setId(i);
            rg.addView(rdNew);
        }
        ll = (LinearLayout) findViewById(R.id.LinearLayout1);
        ll.setVisibility(View.VISIBLE);

        if (!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;

        } else {
            countDownTimer.cancel();
            timerHasStarted = false;
        }
    }

    @Override
    public void getQuestions(ArrayList < Question > questions) {
        this.questionList = questions;
        for (Question q: questionList) {
        }
        noOfQues = questions.size();
        setTriviaView(currentQuestionId);
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            n = (Button) findViewById(R.id.button4);
            n.performClick();

        }

        @Override
        public void onTick(long millisUntilFinished) {
            bt.setText("  Time Left: " + (millisUntilFinished / 1000) + " Seconds  ");
        }
    }
}