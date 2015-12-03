//package ca.mcgill.ecse321.scorekeep;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.Spinner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import ca.mcgill.ecse321.scorekeeper.shared.model.DomainManager;
//import ca.mcgill.ecse321.scorekeeper.shared.model.Player;
//import ca.mcgill.ecse321.scorekeeper.shared.model.Team;
//import ca.mcgill.ecse321.scorekeeper.shared.view.LiveEntryView;
//
//public class AddMatchActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_match);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//
//        final Spinner selectHomeTeam = (Spinner) findViewById(R.id.spinner2);
//        List<String> teams = new ArrayList<String>();
//        List<Team> modelTeams = DomainManager.getInstance().getTeams();
//
//        ArrayAdapter<Team> teamAdapter = new ArrayAdapter<Team>(this, android.R.layout.simple_spinner_item, modelTeams);
//        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        final Spinner selectAwayTeam = (Spinner) findViewById(R.id.spinner3);
//        selectAwayTeam.setAdapter(teamAdapter);
//        selectHomeTeam.setAdapter(teamAdapter);
//
//        Button addMatch = (Button) findViewById(R.id.button12);
//        addMatch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    LiveEntryView.createMatch((Team) selectHomeTeam.getSelectedItem(), (Team) selectAwayTeam.getSelectedItem());
//                } catch (Exception e){
//                    Log.v("exception: ", e.toString());
//                }
//                Log.v("number of teams is now", "" + DomainManager.getInstance().getTeams().size());
//                startActivity(new Intent(AddMatchActivity.this, LiveEntryActivity.class));
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//    }
//
//}
