package ca.mcgill.ecse321.scorekeep;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import ca.mcgill.ecse321.scorekeeper.shared.model.Team;
import ca.mcgill.ecse321.scorekeeper.shared.view.LeagueView;

public class LeagueViewActivity extends AppCompatActivity {

    List<Team> teams;
    ListView listTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView goalLabel = (TextView) findViewById(R.id.textView14);
        final TextView shotLabel = (TextView) findViewById(R.id.textView16);
        shotLabel.setText("");


        final LeagueView lv = new LeagueView();

        RadioButton chooseGoals = (RadioButton) findViewById(R.id.radioButton3);
        RadioButton choosePenalties = (RadioButton) findViewById(R.id.radioButton4);
        RadioButton choosePoints = (RadioButton) findViewById(R.id.radioButton5);

        choosePoints.toggle();

        chooseGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teams = lv.orderedDataCollection(LeagueView.Ordering.ORDER_BY_GOALS);
                goalLabel.setText("Goals");

                updateList();
                getGoalData();
            }
        });

        choosePenalties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teams = lv.orderedDataCollection(LeagueView.Ordering.ORDER_BY_PENALTIES);
                goalLabel.setText("Penalties");

                updateList();
                getPenaltiesData();
            }
        });

        choosePoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teams = lv.orderedDataCollection(LeagueView.Ordering.ORDER_BY_POINTS);
                goalLabel.setText("Points");

                updateList();
                getPointData();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void updateList(){
        listTeams = (ListView) findViewById(R.id.listView2);

        if (teams == null){
            teams = DomainManager.getInstance().getTeams();
        }


        ArrayAdapter<Team> teamAdapter = new ArrayAdapter<Team>(this, android.R.layout.simple_spinner_item, teams);
        listTeams.setAdapter(teamAdapter);

    }

    private void getGoalData(){
        ListView listData = (ListView) findViewById(R.id.listView3);
        ArrayList<String> goals = new ArrayList<String>();
        for (Team t : teams){
            goals.add("" + t.numberOfGoals());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, goals);
        listData.setAdapter(dataAdapter);
    }

    private void getPointData(){
        ListView listData = (ListView) findViewById(R.id.listView4);
        ArrayList<String> points = new ArrayList<String>();
        for (Team t: teams){
            points.add("" + t.numberOfPoints());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, points);
        listData.setAdapter(dataAdapter);
    }

    private void getPenaltiesData(){
        ListView listData = (ListView) findViewById(R.id.listView3);
        ArrayList<String> reds = new ArrayList<String>();
        for (Team t: teams){
            reds.add("" + t.numberOfPenalties());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, reds);
        listData.setAdapter(dataAdapter);
    }

}
