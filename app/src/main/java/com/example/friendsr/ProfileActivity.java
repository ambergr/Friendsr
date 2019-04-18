package com.example.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private Friend retrievedFriend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // intent zodat we kunnen gebuiken op welke plaatje geklikt is
        Intent intent = getIntent();
        retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        // krijgt verschillende images bij id
        ImageView image = findViewById(R.id.imageView2);
        TextView text_name = findViewById(R.id.textView2);
        TextView text_bio = findViewById(R.id.textView3);
        RatingBar star = findViewById(R.id.ratingBar);

        // krijg de naam/bio of het plaatje
        String name_friend = retrievedFriend.getName();
        String bio_friend = retrievedFriend.getBio();
        image.setImageResource(retrievedFriend.getDrawableId());

        // link star en de OnRatingBarChangeListener
        star.setOnRatingBarChangeListener(new OnRatingBarChangeListener());

        text_name.setText(name_friend);
        text_bio.setText(bio_friend);

        // Op prefs zijn de waarde van de sterren met de naam opgeslagen, die opgeroepen
        // en toegevoegd aan aStoredFloat en dan wordt de star geset naar die waarde
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        Float aStoredFloat = prefs.getFloat(retrievedFriend.getName(), 0);
        star.setRating(aStoredFloat);

    }
    private class OnRatingBarChangeListener  implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            // sharedprefence is een plek om de sterren op te slaan
            SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
            //  het opslaan van de sterren, met de naam van de vriend
            editor.putFloat(retrievedFriend.getName() ,rating);
            // Echt uitvoeren/opslaan
            editor.apply();

        }
    }

}
