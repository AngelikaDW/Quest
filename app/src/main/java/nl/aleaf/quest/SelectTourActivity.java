package nl.aleaf.quest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class SelectTourActivity extends AppCompatActivity {
public static final String PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tour);

        //Get language set on device
        final String displayLanguage = Locale.getDefault().getLanguage();
        Log.i("SelectActi language", displayLanguage);

        final Button button_stones = (Button) findViewById(R.id.btn_tour1);
        button_stones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Pressing Button opens Mission Screen of Tour selected
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("tour", "tour1");
                editor.putInt("Tournumber", 1);
                editor.putString("displayLanguage", displayLanguage);
                editor.commit();

                Intent tourIntent = new Intent(SelectTourActivity.this, MissionActivity.class);
                tourIntent.putExtra("Tour", 1);
                startActivity(tourIntent);
            }
        });

        final Button button_stones_center = (Button) findViewById(R.id.btn_tour2);
        button_stones_center.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Pressing Button opens Mission Screen of Tour selected
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("tour", "tour2");
                editor.putInt("Tournumber", 2);
                editor.putString("displayLanguage", displayLanguage);
                editor.commit();

                Intent tourIntent = new Intent(SelectTourActivity.this,MissionActivity.class);
                tourIntent.putExtra("Tour", 2);
                startActivity(tourIntent);
            }
        });
    }
}
