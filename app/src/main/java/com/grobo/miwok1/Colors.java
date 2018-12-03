package com.grobo.miwok1;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Colors extends AppCompatActivity {

    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti", R.raw.color_red, R.drawable.color_red));
        words.add(new Word("two", "otiiko", R.raw.color_green, R.drawable.color_green));
        words.add(new Word("three", "tolookosu", R.raw.color_brown, R.drawable.color_brown));
        words.add(new Word("four", "oyyisa", R.raw.color_gray, R.drawable.color_gray));
        words.add(new Word("five", "massokka", R.raw.color_black, R.drawable.color_black));
        words.add(new Word("six", "temmokka", R.raw.color_white, R.drawable.color_white));
        words.add(new Word("seven", "kenekaku", R.raw.color_dusty_yellow, R.drawable.color_dusty_yellow));
        words.add(new Word("eight", "kawinta", R.raw.color_mustard_yellow, R.drawable.color_mustard_yellow));

        ListView numbersListView = (ListView) findViewById(R.id.numbersListView);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);
        numbersListView.setAdapter(adapter);

        numbersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word audio = words.get(position);
                mPlayer = MediaPlayer.create(Colors.this, audio.getAudioResourceId());
                mPlayer.start();

                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releaseMediaPlayer();
                    }
                });

            }
        });

    }

    private void releaseMediaPlayer() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

}
