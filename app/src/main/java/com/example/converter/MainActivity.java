/* Name: Tat Kong Chan
    CS 442 Assignment 1
 */


package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";




    private RadioButton ftoc;
    private RadioButton ctof;
    private EditText inputDegree;
    private TextView result;
    private TextView displayInput1;
    private TextView displayInput2;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ftoc = (RadioButton) findViewById(R.id.ftocButton);
        ctof = (RadioButton) findViewById(R.id.ctofButton);
        inputDegree = (EditText) findViewById(R.id.degreeEntry);
        result = (TextView) findViewById(R.id.result);
        displayInput1 = (TextView) findViewById(R.id.degreeDisplay1);
        displayInput2 = (TextView) findViewById(R.id.degreeDisplay2);

        output = (TextView) findViewById(R.id.multiLineScroll);
        output.setMovementMethod(new ScrollingMovementMethod());
    }

    // switch between the display words of fahrenheit and celsius degree
    public void switchText(View v) {

        if (ftoc.isChecked()) {
            displayInput1.setText(getResources().getString(R.string.fahDegree));
            displayInput2.setText(getResources().getString(R.string.celDegree));
        }
        else {
            displayInput1.setText(getResources().getString(R.string.celDegree));
            displayInput2.setText(getResources().getString(R.string.fahDegree));
        }
    }

    // calculate the conversion of degree and display result
    public void calculate(View v) {

        // user enters input
        if (inputDegree.getText().length() != 0) {
            String userInput, stringRes, newDegree, historyDegree;
            userInput = inputDegree.getText().toString();
            double rd = Double.parseDouble(inputDegree.getText().toString());
            double res;

            // user chooses to convert fahrenheit to celsius
            if (ftoc.isChecked()) {
                res = (rd - 32.0) / 1.8;

                stringRes = String.format("%,.1f", res);
                result.setText(stringRes);
                newDegree = result.getText().toString();
                historyDegree = output.getText().toString();
                output.setText(userInput + " F ==> " + newDegree + " C" + "\n" + historyDegree);
            }
            // user chooses to convert celsius to fahrenheit
            else {
                res = (rd * 1.8) + 32;

                stringRes = String.format("%,.1f", res);
                result.setText(stringRes);
                newDegree = result.getText().toString();
                historyDegree = output.getText().toString();
                output.setText(userInput + " C ==> " + newDegree + " F" + "\n" + historyDegree);
            }
            // reset input EditText
            inputDegree.setText("");
        }
    }

    // clear all data in conversion history
    public void clearHistory(View v) {

        output.setText("");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        // save result and output
        outState.putString("RESULT", result.getText().toString());
        outState.putString("OUTPUT", output.getText().toString());

        // Call super last
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Call super first
        super.onRestoreInstanceState(savedInstanceState);

        // retrieve result and output
        result.setText(savedInstanceState.getString("RESULT"));
        output.setText(savedInstanceState.getString("OUTPUT"));
    }

}
