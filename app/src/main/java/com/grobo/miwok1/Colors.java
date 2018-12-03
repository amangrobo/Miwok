package com.grobo.miwok1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Colors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti", R.drawable.color_red));
        words.add(new Word("two", "otiiko", R.drawable.color_green));
        words.add(new Word("three", "tolookosu", R.drawable.color_brown));
        words.add(new Word("four", "oyyisa", R.drawable.color_gray));
        words.add(new Word("five", "massokka", R.drawable.color_black));
        words.add(new Word("six", "temmokka", R.drawable.color_white));
        words.add(new Word("seven", "kenekaku", R.drawable.color_dusty_yellow));
        words.add(new Word("eight", "kawinta", R.drawable.color_mustard_yellow));

        ListView numbersListView = (ListView) findViewById(R.id.numbersListView);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);
        numbersListView.setAdapter(adapter);
    }
}
