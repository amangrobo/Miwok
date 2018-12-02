package com.grobo.miwok1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        TextView numbers = (TextView) findViewById(R.id.numbers);
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersIntent = new Intent(MainActivity.this, Numbers.class);
                startActivity(numbersIntent);
            }
        });

        TextView colors = (TextView) findViewById(R.id.colors);     /* Color Text View */
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent colorsIntent = new Intent(MainActivity.this, Colors.class);
                startActivity(colorsIntent);
            }
        });

        TextView phrases = (TextView) findViewById(R.id.phrases);
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phrasesIntent = new Intent(MainActivity.this, Phrases.class);
                startActivity(phrasesIntent);
            }
        });

        TextView familyMembers = (TextView) findViewById(R.id.family);
        familyMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent familyIntent = new Intent(MainActivity.this, FamilyMembers.class);
                startActivity(familyIntent);
            }
        });
    }

}
