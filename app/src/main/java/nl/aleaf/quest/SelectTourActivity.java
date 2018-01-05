package nl.aleaf.quest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectTourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tour);

        final Button button_stones = (Button) findViewById(R.id.btn_tour1);
        button_stones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Pressing Button opens Mission Screen of Tour selected
                Intent tourIntent = new Intent(SelectTourActivity.this, MissionActivity.class);
                tourIntent.putExtra("Tour", 1);
                startActivity(tourIntent);
            }
        });

        final Button button_stones_center = (Button) findViewById(R.id.btn_tour2);
        button_stones_center.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Pressing Button opens Mission Screen of Tour selected
                Intent tourIntent = new Intent(SelectTourActivity.this,MissionActivity.class);
                tourIntent.putExtra("Tour", 2);
                startActivity(tourIntent);
            }
        });
    }
}
