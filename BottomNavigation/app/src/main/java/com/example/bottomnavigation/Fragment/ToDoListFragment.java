package com.example.bottomnavigation.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.example.bottomnavigation.DetailActivity;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.adapter.ItemAdapter;
import com.example.bottomnavigation.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ToDoListFragment extends Fragment {

    private static final int REQUEST_CODE_DETAIL = 1;

    private List<Item> itemList;
    private ItemAdapter adapter;
    private EditText editText;
    private ListView listView;

    public ToDoListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listView);
        editText = view.findViewById(R.id.editText);
        Button addButton = view.findViewById(R.id.addButton);

        itemList = new ArrayList<>();
        adapter = new ItemAdapter(getContext(), itemList);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(v -> addItem());

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            // Pass the item details to the DetailActivity
            Item selectedItem = itemList.get(position);
            intent.putExtra("itemPosition", position);
            intent.putExtra("itemText", selectedItem.getText());
            intent.putExtra("itemImage", selectedItem.getImageResId());
            intent.putExtra("itemChecked", selectedItem.isChecked());
            // Start the DetailActivity
            startActivityForResult(intent, REQUEST_CODE_DETAIL);
        });
    }

    private void addItem() {
        String text = editText.getText().toString();
        if (!text.isEmpty()) {
            itemList.add(new Item(text, R.drawable.ic_launcher_background, false));
            adapter.notifyDataSetChanged();
            editText.setText("");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_DETAIL && resultCode == Activity.RESULT_OK && data != null){
            int position = data.getIntExtra("itemPosition", -1);
            if(position != -1){
                if(data.getBooleanExtra("delete", false)){
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
