package com.example.nhung.bmicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView ans;
    EditText weightBox;
    EditText heightBox;

    RadioGroup weightgroup;
    RadioGroup heightgroup;

    RadioButton weightSelected;
    RadioButton heightSelected;

    float feet;
    float weight;

    ImageButton info;
    Button calculator;

    double bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightBox = (EditText) findViewById(R.id.weightBox);
        heightBox = (EditText) findViewById(R.id.heightBox);

        ans = (TextView) findViewById(R.id.ansBox);
        ans.setKeyListener(null);
        calculator = (Button) findViewById(R.id.calBut);


        //radio group
        weightgroup = (RadioGroup) findViewById(R.id.weightgroup);
        heightgroup = (RadioGroup) findViewById(R.id.heightgroup);

        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get selected radio button from weightgroup
                int selectedWeight = weightgroup.getCheckedRadioButtonId();
                int selectedHeight = heightgroup.getCheckedRadioButtonId();

                //find the button by returned ID
                weightSelected = (RadioButton) findViewById(selectedWeight);
                heightSelected = (RadioButton) findViewById(selectedHeight);

                String weighttype = weightSelected.getText().toString();
                String heighttype = heightSelected.getText().toString();


                //calculating
                if (heightBox.getText().toString().matches("") || heightBox.getText().toString().matches("0") ) {
                    feet = 1;
                } else {
                    feet = Float.parseFloat(heightBox.getText().toString());

                }

                if (weightBox.getText().toString().matches("")) {
                    weight = 0;
                } else {
                    weight = Float.parseFloat(weightBox.getText().toString());

                }

                converter(weighttype, heighttype);

                bmi = (weight * 703) / Math.pow((feet * 12), 2);
                ans.setText(String.format("%.1f", bmi));



            }
        });




        //go to new page from image button
        info = (ImageButton) findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to new activity
                Intent showInfo = new Intent(MainActivity.this, InfoPage.class);
                showInfo.putExtra("bmi", bmi); //passing value
                MainActivity.this.startActivity(showInfo);
            }
        });

    }





    public void converter(String weighttype, String heighttype) {
        if (weighttype.equals("kg")) {
            weight = (float) (weight * 2.20462262185); //convert kg to lbs
        }

        if (heighttype.equals("cm")) {
            feet = (float) (feet / 30.48); //convert cm to ft
        }
    }
}
