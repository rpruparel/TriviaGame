package com.example.hw3;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;
import android.util.Xml;
import android.util.Xml.Encoding;
import android.widget.ArrayAdapter;

public class QuestionStreamParser {
    static private Scanner reader;
    static private ArrayList < Question > questionList;

    static public ArrayList < Question > parseQuestion(InputStream in ) {
        questionList = new ArrayList < Question > ();

        reader = new Scanner( in );
        reader.useDelimiter(";");

        while (reader.hasNext()) {
            Question q = parseLine(reader.nextLine());

            if (q != null) {
                questionList.add(q);
            }
        }

        return questionList;
    }

    static private Question parseLine(String line) {
        if (!Question.rawStringHasValidQuestionFormat(line)) {
            return null;
        } else {
            // NEED TO CHANGE QUESTION CONSTRUCTOR
            return new Question(line);
        }
    }

}