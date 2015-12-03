package ca.mcgill.ecse321.scorekeep;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.scorekeeper.shared.model.DomainManager;
import ca.mcgill.ecse321.scorekeeper.shared.model.PenaltyType;
import ca.mcgill.ecse321.scorekeeper.shared.model.Player;
import ca.mcgill.ecse321.scorekeeper.shared.model.Team;
import ca.mcgill.ecse321.scorekeeper.shared.view.LeagueView;
import ca.mcgill.ecse321.scorekeeper.shared.view.LiveEntryView;
import ca.mcgill.ecse321.scorekeeper.shared.view.NewPlayerView;
import ca.mcgill.ecse321.scorekeeper.shared.view.PlayerView;

public class LiveEntryActivity extends AppCompatActivity{

    Player selectedPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_live_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner selectPlayer = (Spinner) findViewById(R.id.spinner);
        final List<Player> modelPlayers = DomainManager.getInstance().getPlayers();

        ArrayAdapter<Player> dataAdapter = new ArrayAdapter<Player>(this, android.R.layout.simple_spinner_item, modelPlayers);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        selectPlayer.setAdapter(dataAdapter);

        final TextView awayGoals = (TextView) findViewById(R.id.textView11);
        final TextView homeGoals = (TextView) findViewById(R.id.textView10);
        awayGoals.setText("0");
        homeGoals.setText("0");

        final Spinner selectHomeTeam = (Spinner) findViewById(R.id.spinner4);
        final Spinner selectAwayTeam = (Spinner) findViewById(R.id.spinner2);

        final List<Team> modelTeams = DomainManager.getInstance().getTeams();

        ArrayAdapter<Team> teamAdapter = new ArrayAdapter<Team>(this, android.R.layout.simple_spinner_item, modelTeams);
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        selectAwayTeam.setAdapter(teamAdapter);
        selectHomeTeam.setAdapter(teamAdapter);

        final CheckBox shotTaken = (CheckBox) findViewById(R.id.checkBox);
        final CheckBox redCard = (CheckBox) findViewById(R.id.checkBox3);
        final CheckBox shotScored = (CheckBox) findViewById(R.id.checkBox2);
        final CheckBox yellowCard = (CheckBox) findViewById(R.id.checkBox4);

        Button submitData = (Button) findViewById(R.id.button5);
        submitData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Team awayTeam = modelTeams.get(selectAwayTeam.getSelectedItemPosition());
                Team homeTeam = modelTeams.get(selectHomeTeam.getSelectedItemPosition());
                Log.v("away team players: ", "" + awayTeam.getPlayers().size());

                try {
                    LiveEntryView.createMatch(awayTeam, homeTeam);
                } catch (Exception e){
                    Log.v("exception: ", e.toString());
                }
                boolean isShotScored = shotScored.isChecked();
                boolean isShotTaken = shotTaken.isChecked();
                boolean isRedCard = redCard.isChecked();
                boolean isYellowCard = yellowCard.isChecked();
                if (isShotScored || isShotTaken) {
                    try {
                        LiveEntryView.receiveShot(selectedPlayer, isShotScored, DomainManager.getInstance().getPlayerById(0));
                    } catch (Exception e){
                        Log.v("exception: ", e.toString());
                    }
                    if (isShotScored){
                        if (homeTeam.getPlayers().contains(selectedPlayer)){

                            int prev = Integer.parseInt((String) homeGoals.getText()) + 1;
                            homeGoals.setText("" + prev);
                        }else {

                            int goals = Integer.parseInt((String) awayGoals.getText()) + 1;
                            awayGoals.setText("" + goals);
                        }

                    }
                }
                if (isRedCard || isYellowCard) {
                    PenaltyType type;
                    if (isRedCard){
                        type = PenaltyType.RED;
                    }else{
                        type = PenaltyType.YELLOW;
                    }
                    try{
                        LiveEntryView.receivePenalty(selectedPlayer, type);
                    } catch (Exception e) {
                        Log.v("Exception", e.toString());
                    }
                }
            }
        });

        Button submit = (Button) findViewById(R.id.button13);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                try {
                    LiveEntryView.closeMatch();
                }catch(Exception e){
                    Log.v("Exception: ", e.toString());
                }
                startActivity(new Intent(LiveEntryActivity.this, InputActivity.class));
            }

        });

        Log.v("Player goals", "" + DomainManager.getInstance().getPlayers().get(0).numberOfGoals());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
