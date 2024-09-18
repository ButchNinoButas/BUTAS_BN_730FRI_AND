package com.example.newsapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.newsapp.R;

import java.util.HashMap;

public class HeadlineFragment extends Fragment {

    private OnHeadlineSelectedListener callback;

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        void onHeadlineSelected(String headline, String content);
    }

    private HashMap<String, String> newsData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headline, container, false);

        ListView listView = view.findViewById(R.id.headline_list);

        // Sample headlines and their corresponding content
        newsData = new HashMap<>();
        newsData.put("News 1", "This is the content for News 1.");
        newsData.put("News 2", "This is the content for News 2.");
        newsData.put("News 3", "This is the content for News 3.");
        newsData.put("News 4", "This is the content for News 4.");

        String[] headlines = newsData.keySet().toArray(new String[0]);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, headlines);
        listView.setAdapter(adapter);

        // Handle the click on each headline
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedHeadline = headlines[position];
                String selectedContent = newsData.get(selectedHeadline);
                callback.onHeadlineSelected(selectedHeadline, selectedContent);
            }
        });

        return view;
    }
}