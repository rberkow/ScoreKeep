package ca.mcgill.ecse321.scorekeep;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.scorekeeper.shared.model.DomainManager;
import ca.mcgill.ecse321.scorekeeper.shared.model.Penalty;
import ca.mcgill.ecse321.scorekeeper.shared.model.PenaltyType;
import ca.mcgill.ecse321.scorekeeper.shared.model.Player;
import ca.mcgill.ecse321.scorekeeper.shared.view.LeagueView;
import ca.mcgill.ecse321.scorekeeper.shared.view.PlayerView;

/**
 * View player data in various sequences.
 */
public class PlayerViewActivity extends AppCompatActivity {

    ListView listPlayers;
    List<Player> players;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final PlayerView pv = new PlayerView();

        updateList();

        final TextView goalLabel = (TextView) findViewById(R.id.textView7);
        final TextView shotLabel = (TextView) findViewById(R.id.textView9);

        RadioButton chooseGoals = (RadioButton) findViewById(R.id.radioButton);
        RadioButton choosePenalties = (RadioButton)findViewById(R.id.radioButton2);
        chooseGoals.setOnClickListener(new View.OnClickListener() {
            /**
             * On selection of "Goals" radio button, display player shot data and sort players by goals scored.
             * @param view
             */
            @Override
            public void onClick(View view) {
                players = pv.orderedDataCollection(LeagueView.Ordering.ORDER_BY_GOALS);
                goalLabel.setText("Goals");
                shotLabel.setText("Shots");

                updateList();
                getGoalData();
                getShotData();
            }
        });
        choosePenalties.setOnClickListener(new View.OnClickListener() {
            /**
             * On selection of "Penalties" radio button, display player penalty data and sort players by total number of penalties.
             * @param view
             */
            @Override
            public void onClick(View view) {
                players = pv.orderedDataCollection(LeagueView.Ordering.ORDER_BY_PENALTIES);

                goalLabel.setText("Red cards");
                shotLabel.setText("Yellow cards");
                updateList();
                getRedsData();
                getYellowsData();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void updateList(){
        listPlayers = (ListView) findViewById(R.id.listView2);

        if (players == null){
            players = DomainManager.getInstance().getPlayers();
        }


        ArrayAdapter<Player> playerAdapter = new ArrayAdapter<Player>(this, android.R.layout.simple_spinner_item, players);
        listPlayers.setAdapter(playerAdapter);

    }

    private void getGoalData(){
        ListView listData = (ListView) findViewById(R.id.listView3);
        ArrayList<String> goals = new ArrayList<String>();
        for (Player p : players){
            goals.add("" + p.numberOfGoals());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, goals);
        listData.setAdapter(dataAdapter);
    }

    private void getShotData(){
        ListView listData = (ListView) findViewById(R.id.listView4);
        ArrayList<String> shots = new ArrayList<String>();
        for (Player p: players){
            shots.add("" + p.getShots().size());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, shots);
        listData.setAdapter(dataAdapter);
    }

    private void getRedsData(){
        ListView listData = (ListView) findViewById(R.id.listView3);
        ArrayList<String> reds = new ArrayList<String>();
        for (Player p: players){
            int redForPlayer = 0;
            for (Penalty pen : p.getPenalties()){
                if (pen.getType() == PenaltyType.RED){
                    redForPlayer++;
                }
            }
            reds.add("" + redForPlayer);

        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, reds);
        listData.setAdapter(dataAdapter);
    }
    private void getYellowsData(){
        ListView listData = (ListView) findViewById(R.id.listView4);
        ArrayList<String> yellows = new ArrayList<String>();
        for (Player p: players){
            int yellowForPlayer = 0;
            for (Penalty pen : p.getPenalties()){
                if (pen.getType() == PenaltyType.YELLOW){
                    yellowForPlayer++;
                }
            }
            yellows.add("" + yellowForPlayer);

        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yellows);
        listData.setAdapter(dataAdapter);
    }

}
