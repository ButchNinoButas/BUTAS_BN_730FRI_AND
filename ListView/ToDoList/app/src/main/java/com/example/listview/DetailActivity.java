package com.example.listview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private int itemPosition;
    private EditText detailText;
    private CheckBox detailCheckBox;
    private Button editButton, deleteButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView detailImage = findViewById(R.id.detailImage);
        detailText = findViewById(R.id.detailText); // Ensure this is an EditText
        detailCheckBox = findViewById(R.id.detailCheckBox);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);

        Intent intent = getIntent();
        if (intent != null) {
            itemPosition = intent.getIntExtra("itemPosition", -1);
            String itemText = intent.getStringExtra("itemText");
            int itemImage = intent.getIntExtra("itemImage", R.drawable.download);
            boolean itemChecked = intent.getBooleanExtra("itemChecked", false);

            if (itemText == null) itemText = "";

            detailText.setText(itemText);
            detailImage.setImageResource(itemImage);
            detailCheckBox.setChecked(itemChecked);

            editButton.setOnClickListener(v -> editItem());
            deleteButton.setOnClickListener(v -> deleteItem());
        } else {
            finish(); // Finish the activity if intent is null
        }
    }

    private void editItem() {
        String updatedText = detailText.getText().toString();
        boolean updatedChecked = detailCheckBox.isChecked();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("updatedText", updatedText);
        resultIntent.putExtra("updatedChecked", updatedChecked);
        resultIntent.putExtra("itemPosition", itemPosition);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void deleteItem() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("itemPosition", itemPosition);
        resultIntent.putExtra("delete", true);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}