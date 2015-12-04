package ca.mcgill.ecse321.scorekeep;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ca.mcgill.ecse321.scorekeeper.shared.model.DomainManager;
import ca.mcgill.ecse321.scorekeeper.shared.view.NewPlayerView;

/**
 * From this view, new players can be added to the model.
 */
public class NewPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText playerName = (EditText) findViewById(R.id.editText);
        final EditText playerPosition = (EditText) findViewById(R.id.editText2);
        final EditText playerNumber = (EditText) findViewById(R.id.editText3);



        Button fab = (Button) findViewById(R.id.button8);
        fab.setOnClickListener(new View.OnClickListener() {
            String name = "";
            String position = "";
            int number = 0;

            /**
             * When "Add Player" button clicked, add player with inputted data.
             * @param view
             */
            @Override
            public void onClick(View view) {
                name = playerName.getText().toString();
                position = playerPosition.getText().toString();
                number = Integer.parseInt(playerNumber.getText().toString());
                try {
                    NewPlayerView.receiveNewPlayer(name, position, number);
                } catch (Exception e) {
                    Log.v("Exception", e.toString());
                }
                Log.v("No. of players now:", "" + DomainManager.getInstance().getPlayers().size());
                playerName.setText("");
                playerPosition.setText("");
                playerNumber.setText("");
            }
        });




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
