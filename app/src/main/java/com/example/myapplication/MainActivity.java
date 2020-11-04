package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    TextView resultText;
    TextInputLayout firstNumLayout, secondNumLayout, thirdNumLayout;
    EditText firstNum, secondNum, thirdNum;
    Button additionBtn, subtractionBtn, multiplicationBtn, divisionBtn;
    CheckBox firstNumCheckBox, secondNumCheckBox, thirdNumCheckBox;
    CheckBox[] checkBoxesArray;
    EditText[] editTextsArray;
    TextInputLayout[] textInputLayoutsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
    }

    @Override
    public void onClick(View view) {
        hideKeyboard(this);
        boolean isValid = true;
        ArrayList<Integer> numbers = new ArrayList<>();
        for(int i=0; i < checkBoxesArray.length; i++) {
            if (checkBoxesArray[i].isChecked()) {
                if (!editTextsArray[i].getText().toString().isEmpty()) {
                    numbers.add(Integer.parseInt(editTextsArray[i].getText().toString()));
                } else {
                    textInputLayoutsArray[i].setError("Please fill in the number");
                    isValid = false;
                }
            }
        }

        if (!isValid || numbers.size() <= 1) {
            Toast.makeText(this, "You should check at least 2 checkboxes and filled" +
                    "in the text field", Toast.LENGTH_SHORT).show();
        } else {
            firstNumLayout.setErrorEnabled(false);
            secondNumLayout.setErrorEnabled(false);
            thirdNumLayout.setErrorEnabled(false);
            int result = numbers.get(0);
            switch (view.getId()) {
                case R.id.additionButton:
                    for(int i=1;i < numbers.size();i++) {
                        result = result + numbers.get(i);
                    }
                    break;
                case R.id.substractionButton:
                    for(int i=1;i < numbers.size();i++) {
                        result = result - numbers.get(i);
                    }
                    break;
                case R.id.multiplicationButton:
                    for(int i=1;i < numbers.size();i++) {
                        result = result * numbers.get(i);
                    }
                    break;
                case R.id.divisionButton:
                    for(int i=1;i < numbers.size();i++) {
                        result = result / numbers.get(i);
                    }
                    break;
            }
            resultText.setText("The result is " + result);
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.firstNumber:
                firstNumLayout.setErrorEnabled(false);
                break;
            case R.id.secondNumber:
                secondNumLayout.setErrorEnabled(false);
                break;
            case R.id.thirdNumber:
                thirdNumLayout.setErrorEnabled(false);
                break;
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void initializeComponents() {
        resultText = findViewById(R.id.resultTextView);

        firstNumLayout = findViewById(R.id.firstNumberLayout);
        secondNumLayout = findViewById(R.id.secondNumberLayout);
        thirdNumLayout = findViewById(R.id.thirdNumberLayout);

        firstNum = findViewById(R.id.firstNumber);
        secondNum = findViewById(R.id.secondNumber);
        thirdNum = findViewById(R.id.thirdNumber);

        additionBtn = findViewById(R.id.additionButton);
        subtractionBtn = findViewById(R.id.substractionButton);
        multiplicationBtn = findViewById(R.id.multiplicationButton);
        divisionBtn = findViewById(R.id.divisionButton);

        firstNumCheckBox = findViewById(R.id.firstNumCheckBox);
        secondNumCheckBox = findViewById(R.id.secondNumCheckBox);
        thirdNumCheckBox = findViewById(R.id.thirdNumCheckBox);

        checkBoxesArray = new CheckBox[]{firstNumCheckBox, secondNumCheckBox, thirdNumCheckBox};
        editTextsArray = new EditText[]{firstNum, secondNum, thirdNum};
        textInputLayoutsArray = new TextInputLayout[]{firstNumLayout, secondNumLayout, thirdNumLayout};

        additionBtn.setOnClickListener(this);
        subtractionBtn.setOnClickListener(this);
        multiplicationBtn.setOnClickListener(this);
        divisionBtn.setOnClickListener(this);

        firstNum.setOnFocusChangeListener(this);
        secondNum.setOnFocusChangeListener(this);
        thirdNum.setOnFocusChangeListener(this);
    }
}