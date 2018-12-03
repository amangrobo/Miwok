package com.grobo.miwok1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti", R.raw.family_father, R.drawable.family_father));
        words.add(new Word("two", "otiiko", R.raw.family_mother, R.drawable.family_mother));
        words.add(new Word("three", "tolookosu", R.raw.family_son, R.drawable.family_son));
        words.add(new Word("four", "oyyisa", R.raw.family_daughter, R.drawable.family_daughter));
        words.add(new Word("five", "massokka", R.raw.family_older_brother, R.drawable.family_older_brother));
        words.add(new Word("six", "temmokka", R.raw.family_younger_brother, R.drawable.family_younger_brother));
        words.add(new Word("seven", "kenekaku", R.raw.family_older_sister, R.drawable.family_older_sister));
        words.add(new Word("eight", "kawinta", R.raw.family_younger_sister, R.drawable.family_younger_sister));
        words.add(new Word("nine", "wo'e", R.raw.family_grandfather, R.drawable.family_grandfather));
        words.add(new Word("ten", "na'aacha", R.raw.family_grandmother, R.drawable.family_grandmother));

        ListView numbersListView = (ListView) findViewById(R.id.numbersListView);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);
        numbersListView.setAdapter(adapter);
    }
}
