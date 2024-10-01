package com.example.calculator1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Calculator extends AppCompatActivity {
    private EditText number1EditText;
    private EditText number2EditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        number1EditText = findViewById(R.id.number1);
        number2EditText = findViewById(R.id.number2);
        resultTextView = findViewById(R.id.result);

        Button addButton = findViewById(R.id.add_button);
        Button subtractButton = findViewById(R.id.subtract_button);
        Button multiplyButton = findViewById(R.id.multiply_button);
        Button divideButton = findViewById(R.id.divide_button);

        addButton.setOnClickListener(this::performOperation);
        subtractButton.setOnClickListener(this::performOperation);
        multiplyButton.setOnClickListener(this::performOperation);
        divideButton.setOnClickListener(this::performOperation);
    }

    private void performOperation(View view) {
        try {
            double num1 = Double.parseDouble(number1EditText.getText().toString());
            double num2 = Double.parseDouble(number2EditText.getText().toString());

            double result = 0;
            if (view.getId() == R.id.add_button) {
                result = num1 + num2;
            } else if (view.getId() == R.id.subtract_button) {
                result = num1 - num2;
            } else if (view.getId() == R.id.multiply_button) {
                result = num1 * num2;
            } else if (view.getId() == R.id.divide_button) {
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    resultTextView.setText("Error: Division by zero");
                    return;
                }
            } else {
                resultTextView.setText("Error: Unknown operation");
                return;
            }

            resultTextView.setText(String.format("Result: %.2f", result));
        } catch (NumberFormatException e) {
            resultTextView.setText("Error: Invalid input");
        }
    }
}