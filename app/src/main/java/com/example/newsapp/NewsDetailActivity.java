package com.example.newsapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.newsapp.Fragment.HeadlineFragment;
import com.example.newsapp.Fragment.NewsContentFragment;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            // Load the news content fragment and pass the selected headline and content
            String headline = getIntent().getStringExtra("headline");
            String content = getIntent().getStringExtra("content");

            NewsContentFragment contentFragment = NewsContentFragment.newInstance(headline, content);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_2, contentFragment)
                    .commit();
        }
    }
}