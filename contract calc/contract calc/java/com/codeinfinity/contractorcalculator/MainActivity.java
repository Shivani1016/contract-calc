package com.codeinfinity.contractorcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText labor, material;
    TextView subtotal, total, word;

    Button calculate;

    private static final String[] units = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final String[] teens = {"", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] tens = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        labor = findViewById(R.id.editLabor);
        material = findViewById(R.id.editMaterial);
        subtotal = findViewById(R.id.subtotalTxt);
        total = findViewById(R.id.totalTxt);
        word = findViewById(R.id.wordsTxt);

        calculate = findViewById(R.id.calculateBTn);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float laborInt = Float.parseFloat(labor.getText().toString());
                float materialInt = Float.parseFloat(material.getText().toString());
                float subtotalInt = laborInt + materialInt;
                subtotal.setText(String.valueOf(subtotalInt));
                double tax = subtotalInt * 0.05;
                double totalVal = tax + subtotalInt;
                total.setText(String.valueOf(totalVal));

                // Convert totalVal to words and update the word TextView
                String totalInWords = numberToText((int) totalVal);
                word.setText("(" + totalInWords + ")");
            }
        });
    }

    private static String numberToText(int number) {
        if (number == 0) {
            return "zero";
        }
        if (number < 0) {
            return "negative " + numberToText(-number);
        }
        if (number < 10) {
            return units[number];
        }
        if (number < 20) {
            return teens[number - 10];
        }
        if (number < 100) {
            return tens[number / 10] + ((number % 10 != 0) ? " " + units[number % 10] : "");
        }
        if (number < 1000) {
            return units[number / 100] + " hundred" + ((number % 100 != 0) ? " " + numberToText(number % 100) : "");
        }
        if (number < 1000000) {
            return numberToText(number / 1000) + " thousand" + ((number % 1000 != 0) ? " " + numberToText(number % 1000) : "");
        }

        // Add more cases for larger numbers as needed

        return "Number too large to convert";
    }
}
