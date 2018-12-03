package com.grobo.miwok1;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Phrases extends AppCompatActivity {

    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti", R.raw.phrase_where_are_you_going));
        words.add(new Word("two", "otiiko", R.raw.phrase_what_is_your_name));
        words.add(new Word("three", "tolookosu", R.raw.phrase_my_name_is));
        words.add(new Word("four", "oyyisa", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("five", "massokka", R.raw.phrase_im_feeling_good));
        words.add(new Word("six", "temmokka", R.raw.phrase_are_you_coming));
        words.add(new Word("seven", "kenekaku", R.raw.phrase_yes_im_coming));
        words.add(new Word("eight", "kawinta", R.raw.phrase_im_coming));
        words.add(new Word("nine", "wo'e", R.raw.phrase_lets_go));
        words.add(new Word("ten", "na'aacha", R.raw.phrase_come_here));

        ListView numbersListView = (ListView) findViewById(R.id.numbersListView);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);
        numbersListView.setAdapter(adapter);

        numbersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word audio = words.get(position);
                mPlayer = MediaPlayer.create(Phrases.this, audio.getAudioResourceId());
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
