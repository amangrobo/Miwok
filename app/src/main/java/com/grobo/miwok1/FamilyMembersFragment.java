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
public class FamilyMembersFragment extends Fragment {


    public FamilyMembersFragment() {
        // Required empty public constructor
    }


    private MediaPlayer mPlayer;
    private AudioManager aManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> words = new ArrayList<Word>();
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

        ListView numbersListView = (ListView) rootView.findViewById(R.id.numbersListView);

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);
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
