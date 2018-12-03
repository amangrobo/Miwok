package com.grobo.miwok1;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int colorResource;
    MediaPlayer mPlayer;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorInput){
        super(context, 0, words);
        colorResource = colorInput;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        final Word currentWord = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        ImageView imageDisplayView = (ImageView) listItemView.findViewById(R.id.image_display);
        if(currentWord.hasImage()) {
            imageDisplayView.setImageResource(currentWord.getImageResourceId());
            imageDisplayView.setVisibility(View.VISIBLE);
        } else  imageDisplayView.setVisibility(View.GONE);

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), colorResource);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }

}
