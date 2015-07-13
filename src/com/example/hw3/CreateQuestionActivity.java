package com.example.hw3;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateQuestionActivity extends Activity {

    ImageButton optionButton;
    Button submitButton;
    RadioGroup optionGroup;
    EditText optionText;
    EditText questionText;
    EditText imageText;
    Question newQuestion;
    LinearLayout optionLayout;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

        // set title and remove icon
        setTitle(R.string.create_title);
        getActionBar().setIcon(new ColorDrawable(android.R.color.transparent));

        // edit texts
        questionText = (EditText) findViewById(R.id.editText1);
        optionText = (EditText) findViewById(R.id.editText2);
        imageText = (EditText) findViewById(R.id.editText3);

        // buttons
        optionButton = (ImageButton) findViewById(R.id.imageButton1);
        submitButton = (Button) findViewById(R.id.button1);
        optionGroup = (RadioGroup) findViewById(R.id.radioGroup1);

        newQuestion = new Question();

        optionButton.setOnClickListener(new View.OnClickListener() {
			@Override
            public void onClick(View v) {
                String ans = optionText.getText().toString();
                if (!ans.equals("") && !newQuestion.getAnswerChoices().contains(ans)) {

                    rb = new RadioButton(CreateQuestionActivity.this);
                    rb.setText(optionText.getText().toString());
                    optionGroup.addView(rb);

                    newQuestion
                        .addAnswerChoice(optionText.getText().toString());

                    optionText.setText("");

                }

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                newQuestion.setImageURL(imageText.getText().toString());
                newQuestion.setQuestion(questionText.getText().toString());

                for (int i = 0; i < optionGroup.getChildCount(); i++) {
                    rb = (RadioButton) optionGroup.getChildAt(i);
                    if (rb.isChecked()) {
                        newQuestion.setCorrectAnswerIndex(i);
                    }
                }

                if (Question.rawStringHasValidQuestionFormat(newQuestion
                        .toString())) {


                    String url = getResources().getString(R.string.save_url);
                    String method = getResources().getString(
                        R.string.method_POST);
                    String id = getResources().getString(R.string.group_id);
                    new CreateQuestionAsyncTask().execute(url, method, id,
                        newQuestion.toString());

                    finish();

                } else if (questionText.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Please enter a question",
                        Toast.LENGTH_LONG).show();
                } else if (optionGroup.getChildCount() < 2) {
                    Toast.makeText(getBaseContext(),
                        "Please enter two or more answers",
                        Toast.LENGTH_LONG).show();
                } else if (optionGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getBaseContext(),
                            "Please select a correct answer", Toast.LENGTH_LONG)
                        .show();
                }
            }
        });

    }
}