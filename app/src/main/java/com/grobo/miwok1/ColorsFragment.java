package com.grobo.miwok1;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {


    public ColorsFragment() {
        // Required empty public constructor
    }

    private MediaPlayer mPlayer;
    private AudioManager aManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti", R.raw.color_red, R.drawable.color_red));
        words.add(new Word("two", "otiiko", R.raw.color_green, R.drawable.color_green));
        words.add(new Word("three", "tolookosu", R.raw.color_brown, R.drawable.color_brown));
        words.add(new Word("four", "oyyisa", R.raw.color_gray, R.drawable.color_gray));
        words.add(new Word("five", "massokka", R.raw.color_black, R.drawable.color_black));
        words.add(new Word("six", "temmokka", R.raw.color_white, R.drawable.color_white));
        words.add(new Word("seven", "kenekaku", R.raw.color_dusty_yellow, R.drawable.color_dusty_yellow));
        words.add(new Word("eight", "kawinta", R.raw.color_mustard_yellow, R.drawable.color_mustard_yellow));

        ListView numbersListView = (ListView) rootView.findViewById(R.id.numbersListView);

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);
        numbersListView.setAdapter(adapter);

        aManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        numbersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word audio = words.get(position);

                int result = aManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    releaseMediaPlayer();
                    mPlayer = MediaPlayer.create(getActivity(), audio.getAudioResourceId());
                    mPlayer.start();

                    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                        }
                    });

                }

            }
        });

        return rootView;
    }


    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mPlayer.pause();
                mPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };

    private void releaseMediaPlayer() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;

            aManager.abandonAudioFocus(afChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
