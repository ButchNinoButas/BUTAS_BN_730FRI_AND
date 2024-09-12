package com.example.listview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.listview.adapter.ItemAdapter;
import com.example.listview.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Item> itemList;
    private ItemAdapter adapter;
    private EditText editText;
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editText);
        Button addButton = findViewById(R.id.addButton);

        itemList = new ArrayList<>();
        adapter = new ItemAdapter(this, itemList);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(v -> addItem());

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            // Pass the item details to the DetailActivity
            Item selectedItem = itemList.get(position);
            intent.putExtra("itemPosition", position);
            intent.putExtra("itemText", selectedItem.getText());
            intent.putExtra("itemImage", selectedItem.getImageResId());
            intent.putExtra("itemChecked", selectedItem.isChecked());
            // Start the DetailActivity
            startActivityForResult(intent, 1);
        });
    }

    private void addItem() {
        String text = editText.getText().toString();
        if (!text.isEmpty()) {
            itemList.add(new Item(text, R.drawable.download, false));
            adapter.notifyDataSetChanged();
            editText.setText("");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            int position = data.getIntExtra("itemPosition", -1);
            if (position != -1) {
                if (data.getBooleanExtra("delete", false)) {
                    // Remove the item from the list if deleted
                    itemList.remove(position);
                } else {
                    // Update the item with the new details
                    String updatedText = data.getStringExtra("updatedText");
                    boolean updatedChecked = data.getBooleanExtra("updatedChecked", false);
                    Item updatedItem = itemList.get(position);
                    updatedItem.setText(updatedText);
                    updatedItem.setChecked(updatedChecked);
                }
                // Notify the adapter to refresh the list
                adapter.notifyDataSetChanged();
            }
        }
    }
}
