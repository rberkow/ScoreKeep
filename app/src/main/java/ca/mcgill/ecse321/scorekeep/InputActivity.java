package ca.mcgill.ecse321.scorekeep;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * From this view, all the different ways to input data can be accessed.
 */
public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button fab = (Button) findViewById(R.id.button3);
        fab.setOnClickListener(new View.OnClickListener() {
            /**
             * Switch to Batch Entry view on click of "Batch Entry" button.
             * @param view
             */
            @Override
            public void onClick(View view) {
                //TODO: implement Batch Entry
                Snackbar.make(view, "Input data", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button liveButton = (Button) findViewById(R.id.button4);
        liveButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Switch to Live Entry view on click of "Live Entry" button.
             * @param view
             */
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InputActivity.this, LiveEntryActivity.class));
            }
        });

        Button newPlayerButton = (Button) findViewById(R.id.button7);
        newPlayerButton.setOnClickListener(new View.OnClickListener(){
            /**
             * Switch to New Player view on click of "New Player" button.
             * @param view
             */
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InputActivity.this, NewPlayerActivity.class));
            }
        });

        Button newTeamButton = (Button) findViewById(R.id.button6);
        newTeamButton.setOnClickListener(new View.OnClickListener(){
            /**
             * Switch to New Team view on click of "New Team" button.
             * @param view
             */
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InputActivity.this, NewTeamActivity.class));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
