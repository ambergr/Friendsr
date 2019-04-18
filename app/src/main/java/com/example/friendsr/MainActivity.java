package com.example.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Friend>  friends = new ArrayList<>(12);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //connect this listener to the GridView.
        GridView gv = findViewById(R.id.grid);
        gv.setOnItemClickListener(new GridItemClickListener());

        // two list wich contains the names and all the io of the friends
        String[] names = { "banzai", "ed", "mufasa", "nala", "pumba", "rafiki", "sarafina", "scar", "shenzi", "simba", "timon", "zazu" };
        String[] bio = { "HIHIHIHI", "HIGTIRHIDSCHI", "Look Simba, Everything the light touches \n" +
                "is our kingdom.", "He's holding back, he's hiding But what, I can't decide", "Hakuna Matata!\n" +
                "Aint no passing craze", "Asante sana Squash banana, Wewe nugu mimi hapana", "nala's \n" +
                "mom", "Long, live the king", "HIGHIHGIHI", "I just can't wait to be king", "Hakuna Matata!\n" +
                "What a wonderful phrase", "Well I've never seen a king of beasts, With quite so little hair" };


        // loop through the list so you can add the name and the bio of each friend to the arraylost Friend
        for (int i = 0; i < names.length; i++){
            String name = names[i];
            int draw= getResources().getIdentifier(name, "drawable", getPackageName());
            friends.add(new Friend(name, bio[i], draw));
        }

        // Connecting the adapter to the grid view and the list
        FriendsAdapter adapter = new FriendsAdapter(this,  R.layout.grid_item, friends);
        gv.setAdapter(adapter);


    }
    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        @Override

        // extrakt op welk plaatje er geklikt wordt
        //  we gebruiken een intent zodat dit ook op andere plekken gebruikt kan worden
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Friend clickedFriend = (Friend) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("clicked_friend", clickedFriend);
                startActivity(intent);
        }

    }
}
