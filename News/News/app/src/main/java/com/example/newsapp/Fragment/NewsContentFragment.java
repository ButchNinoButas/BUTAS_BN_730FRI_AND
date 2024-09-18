package com.example.newsapp.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsapp.R;

public class NewsContentFragment extends Fragment {

    private static final String ARG_HEADLINE = "headline";
    private static final String ARG_CONTENT = "content";

    private String headline;
    private String content;

    // Modify the newInstance method to accept both headline and content
    public static NewsContentFragment newInstance(String headline, String content) {
        NewsContentFragment fragment = new NewsContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HEADLINE, headline);
        args.putString(ARG_CONTENT, content);  // Pass the content as well
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            headline = getArguments().getString(ARG_HEADLINE);
            content = getArguments().getString(ARG_CONTENT);  // Retrieve the content
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_content, container, false);

        // Update the TextView with both headline and content
        TextView newsContent = view.findViewById(R.id.news_content);
        newsContent.setText("Headline: " + headline + "\n\nContent: " + content);  // Display headline and content

        return view;
    }

    // Update content in landscape mode
    public void updateNewsContent(String newHeadline, String newContent) {
        this.headline = newHeadline;
        this.content = newContent;  // Update the content as well
        TextView newsContent = getView().findViewById(R.id.news_content);
        newsContent.setText("Headline: " + headline + "\n\nContent: " + content);
    }
}
