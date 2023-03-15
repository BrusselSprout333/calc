package com.nastya.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultField;
    EditText numberField;
    private String operator;
    private double num1, num2;
    private double result;
    double scale = Math.pow(10, 3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // получаем все поля по id из activity_main.xml
        initViews();
    }

    private void initViews() {
        resultField = findViewById(R.id.resultField);
        numberField = findViewById(R.id.numberField);
    }

    @Override
    public void onClick(View view) {
        //getScreenOrientation();
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        numberField.append(button.getText());
    }

    public void onOperationClick(View view) {
        Button button = (Button) view;
        operator = button.getText().toString();

        if (button == findViewById(R.id.button_clear)) {
            numberField.setText("");
            resultField.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
            return;
        }

        num1 = Math.ceil(Double.parseDouble(numberField.getText().toString()) * scale) / scale;
        resultField.setText(numberField.getText());
        resultField.append(button.getText());
        numberField.setText("");
    }

    public void onUnarOperationClick(View view) {
        Button button = (Button) view;
        operator = button.getText().toString();
        num1 = Math.ceil(Double.parseDouble(numberField.getText().toString()) * scale) / scale;

        switch (operator) {
            case "1/x":
                result = 1 / num1;
                resultField.setText("1/");
                break;
            case "√":
                result = Math.sqrt(num1);
                resultField.setText(operator);
                break;
            case "±":
                result = -num1;
                resultField.setText("-");
                break;
            case "%":
                result = num1 * 0.01;
                resultField.setText(operator);
                break;
            default:
                result = 0;
        }

        result = Math.ceil(result * scale) / scale;
        resultField.append(numberField.getText()+"=");
        numberField.setText(String.valueOf(result));
    }

    public void countClick(View view) {
        num2 = Math.ceil(Double.parseDouble(numberField.getText().toString()) * scale) / scale;
        resultField.append(numberField.getText() + "=");

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    result = 0;
                    resultField.setText("Error");
                    return;
                }
                result = num1 / num2;
                break;
            default:
                result = 0;
        }

        result = Math.ceil(result * scale) / scale;
        numberField.setText(String.valueOf(result));
    }
}