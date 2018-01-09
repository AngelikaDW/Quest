package nl.aleaf.quest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class MissionActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        final Context context = this;

        /*Get Tournumber from SelectTourActivity*/
        SharedPreferences tourselected = getSharedPreferences(SelectTourActivity.PREFS_NAME, Context.MODE_PRIVATE);
        int numberTour = tourselected.getInt("Tournumber", MODE_PRIVATE);
        Log.i("TourNbr MainActivity", String.valueOf(numberTour));



        //Get data to display
        final ArrayList<Stone> stoneList = Stone.getStonesFromFile("quest.json", this);

        // Create adapter
        StoneAdapter adapter = new StoneAdapter(this, stoneList);

        // Create list view
        mListView = (ListView) findViewById(R.id.stones_list_view);
        mListView.setAdapter(adapter);



        mListView = (ListView) findViewById(R.id.stones_list_view);

        // Set what happens when a list view item is clicked
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Stone selectedStone = stoneList.get(position);

                Intent detailIntent = new Intent(context, DetailActivity.class);
                detailIntent.putExtra("name", selectedStone.name);
                detailIntent.putExtra("id", selectedStone.id);

                startActivity(detailIntent);
            }

        });


    }
}
