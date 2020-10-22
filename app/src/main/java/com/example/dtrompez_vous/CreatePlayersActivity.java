package com.example.dtrompez_vous;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

public class CreatePlayersActivity extends AppCompatActivity {

    private Spinner nbPlayersSpinner;
    private Button playButton;
    private LinearLayout inputLayout;
    private int nbPlayers = 0;
    private PlayersList players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_players);

        ElementsList elements = (ElementsList)getIntent().getSerializableExtra("elements");
        ActionsList actions = (ActionsList)getIntent().getSerializableExtra("actions");

        setTitle("Joueurs");

        nbPlayersSpinner = (Spinner) findViewById(R.id.activity_create_players_spinner);
        playButton = (Button) findViewById(R.id.activity_create_players_play_button);
        inputLayout = (LinearLayout) findViewById(R.id.activity_create_players_input_layout);

        final List<Integer> nbPlayersList = Arrays.asList(2,3,4, 5);
        ArrayAdapter adapterNbPlayers = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nbPlayersList);
        adapterNbPlayers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nbPlayersSpinner.setAdapter(adapterNbPlayers);

        nbPlayersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nbPlayers = (Integer) nbPlayersSpinner.getItemAtPosition(position);
                updatePlayersInput();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        playButton.setEnabled(false);
        playButton.setVisibility(View.GONE);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickCardActivity = new Intent(CreatePlayersActivity.this, PickCardActivity.class);
                pickCardActivity.putExtra("actions", actions);
                pickCardActivity.putExtra("elements", elements);
                pickCardActivity.putExtra("players", players);
                startActivity(pickCardActivity);
                CreatePlayersActivity.this.finish();
            }
        });
    }

    public void updatePlayersInput(){
        inputLayout.removeAllViews();
        players = new PlayersList(nbPlayers);

        for(int i = 0; i<nbPlayers; i++){
            final EditText playerEdit = new EditText(this);
            final int j = i;
            playerEdit.setHint("Joueur " + Integer.toString(i));
            playerEdit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    playButton.setEnabled(true);
                    playButton.setVisibility(View.VISIBLE);
                    String name = playerEdit.getText().toString();
                    Player player = new Player(name, j);
                    players.set(j, player);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            inputLayout.addView(playerEdit);
        }
    }

}