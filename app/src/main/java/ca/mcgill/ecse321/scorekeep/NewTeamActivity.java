package ca.mcgill.ecse321.scorekeep;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.scorekeeper.shared.model.DomainManager;
import ca.mcgill.ecse321.scorekeeper.shared.model.Player;
import ca.mcgill.ecse321.scorekeeper.shared.view.LiveEntryView;
import ca.mcgill.ecse321.scorekeeper.shared.view.ManageTeamsView;
import ca.mcgill.ecse321.scorekeeper.shared.view.NewTeamView;

/**
 * Add new teams to database.
 */
public class NewTeamActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText teamName = (EditText) findViewById(R.id.editText4);

        final ListView selectPlayer = (ListView) findViewById(R.id.listView);
        final List<Player> modelPlayers = DomainManager.getInstance().getPlayers();
        ArrayAdapter<Player> dataAdapter = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_multiple_choice, modelPlayers);

        // attaching data adapter to spinner
        selectPlayer.setAdapter(dataAdapter);
        selectPlayer.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Button fab = (Button) findViewById(R.id.button9);
        fab.setOnClickListener(new View.OnClickListener() {
            String name;

            /**
             * When "Add Team" clicked, create team and associate selected players to the team.
             * @param view
             */
            @Override
            public void onClick(View view) {
                name = teamName.getText().toString();
                int teamID = 0;
                try {
                    teamID = NewTeamView.receiveNewTeam(name, "Dummy Coach");
                    Log.v("teamID :", "" + teamID);
                } catch (Exception e){
                    Log.v("Exception:", e.toString());
                }
                for(int i = 0; i < modelPlayers.size(); i++){
                    if(selectPlayer.getCheckedItemPositions().get(i)){
                        try {
                            ManageTeamsView.receiveAssignment(modelPlayers.get(i), DomainManager.getInstance().getTeamById(teamID));
                        } catch (Exception e) {
                            Log.v("Exception: ", e.toString());
                        }
                    }
                }
                Log.v("Number of teams: ", "" + DomainManager.getInstance().getTeams().size());
                Log.v("Number of players: ", "" + DomainManager.getInstance().getTeamById(teamID).getPlayers().size());
                Log.v("Team ID: ", "" + teamID);

                startActivity(new Intent(NewTeamActivity.this, InputActivity.class));

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
