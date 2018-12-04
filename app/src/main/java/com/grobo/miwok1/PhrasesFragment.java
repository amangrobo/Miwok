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
public class PhrasesFragment extends Fragment {


    public PhrasesFragment() {
        // Required empty public constructor
    }


    private MediaPlayer mPlayer;
    private AudioManager aManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

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

        ListView numbersListView = (ListView) rootView.findViewById(R.id.numbersListView);

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
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
