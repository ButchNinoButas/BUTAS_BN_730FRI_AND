package com.example.bottomnavigation.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.R;

public class CalculatorFragment extends Fragment {
    private EditText number1EditText;
    private EditText number2EditText;
    private TextView resultTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        // Initialize views
        number1EditText = view.findViewById(R.id.number1);
        number2EditText = view.findViewById(R.id.number2);
        resultTextView = view.findViewById(R.id.result);

        Button addButton = view.findViewById(R.id.add_button);
        Button subtractButton = view.findViewById(R.id.subtract_button);
        Button multiplyButton = view.findViewById(R.id.multiply_button);
        Button divideButton = view.findViewById(R.id.divide_button);

        addButton.setOnClickListener(this::performOperation);
        subtractButton.setOnClickListener(this::performOperation);
        multiplyButton.setOnClickListener(this::performOperation);
        divideButton.setOnClickListener(this::performOperation);

        return view;
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