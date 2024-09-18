package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.newsapp.Fragment.HeadlineFragment;
import com.example.newsapp.Fragment.NewsContentFragment;

public class MainActivity extends AppCompatActivity implements HeadlineFragment.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout based on orientation
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            // Load the headline list fragment
            Fragment headlineFragment = new HeadlineFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_1, headlineFragment)
                    .commit();

            // If in landscape mode, load the news content fragment as well
            if (findViewById(R.id.fragment_container_2) != null) {
                Fragment contentFragment = new NewsContentFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_2, contentFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onHeadlineSelected(String headline, String content) {
        // Check if in portrait mode (no second fragment container)
        if (findViewById(R.id.fragment_container_2) == null) {
            // Start a new activity with both fragments in portrait mode
            Intent intent = new Intent(this, NewsDetailActivity.class);
            intent.putExtra("headline", headline);
            intent.putExtra("content", content);
            startActivity(intent);
        } else {
            // In landscape mode, update the news content fragment
            NewsContentFragment contentFragment = (NewsContentFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_container_2);
            if (contentFragment != null) {
                contentFragment.updateNewsContent(headline, content);
            }
        }
    }
}