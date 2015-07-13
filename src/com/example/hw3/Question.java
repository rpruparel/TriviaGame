package com.example.hw3;

import java.util.ArrayList;

import android.util.Log;

public class Question {
    private String imageURL;
    private String question;
    private ArrayList < String > answerChoices = new ArrayList < String > ();
    private int correctAnswerIndex = -1; // initial unchosen value

    public Question(String line) {
        if (rawStringHasValidQuestionFormat(line)) {
            String[] blocks = line.split(";");

            this.question = blocks[0];
            this.imageURL = blocks[blocks.length - 2];
            this.correctAnswerIndex = Integer
                .parseInt(blocks[blocks.length - 1]);
            for (int i = 1; i < blocks.length - 2; i++) {
                answerChoices.add(blocks[i]);
            }
        }
    }

    public Question() {

    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList < String > getAnswerChoices() {
        return answerChoices;
    }

    public void addAnswerChoice(String answerChoice) {
        this.answerChoices.add(answerChoice);
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // question
        sb.append(this.getQuestion());
        sb.append(";");

        // answers
        for (String answer: answerChoices) {
            sb.append(answer);
            sb.append(";");
        }

        // image
        sb.append(imageURL);
        sb.append(";");

        // correct index
        sb.append(correctAnswerIndex);


        return sb.toString();
    }

    static public boolean rawStringHasValidQuestionFormat(String s) {

        String[] questionBlocks;

        // string is null
        if (s == null) {
            // Log.d("validation", "null string");
            return false;
        } else if (!s.contains(";")) {

            return false;
        } else {
            questionBlocks = s.split(";");

            if (questionBlocks.length < 5) {
                return false;
            } else {
                String lastBlock = questionBlocks[questionBlocks.length - 1];

                try {
                    Integer.parseInt(lastBlock);
                } catch (NumberFormatException e) {

                    return false;
                }

                int answerSize = questionBlocks.length - 3;
                int ansIndex = Integer.parseInt(lastBlock);
                if (ansIndex < 0 || ansIndex >= answerSize) {
                    String logString = "ans index: " + ansIndex + "\t answersize: " + answerSize;
                    return false;
                }

                // only second to last block can be null
                for (int i = 0; i < questionBlocks.length; i++) {
                    if ((questionBlocks[i] == null || questionBlocks[i]
                            .equals("")) && i != (questionBlocks.length - 2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}