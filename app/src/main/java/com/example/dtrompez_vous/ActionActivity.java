package com.example.dtrompez_vous;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class ActionActivity extends AppCompatActivity {

    private int type;
    private ActionsList actions;

    private TextView actionTextView;
    private Button okButton;
    private  TextView playerName;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        ActionsList actions = (ActionsList)getIntent().getSerializableExtra("actions");
        ElementsList elements = (ElementsList)getIntent().getSerializableExtra("elements");
        PlayersList players = (PlayersList)getIntent().getSerializableExtra("players");

        Bundle b = getIntent().getExtras();
        if (b != null){
            type = b.getInt("type");
        }

        playerName = (TextView) findViewById(R.id.activity_action_player_name);
        actionTextView = (TextView) findViewById(R.id.activity_action_text);
        okButton = (Button) findViewById(R.id.activity_action_ok_button);

        playerName.setText(players.getCurrentPlayer().getName());

        okButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                players.nextPlayer();
                Intent pickCardActivity = new Intent(ActionActivity.this, PickCardActivity.class);
                pickCardActivity.putExtra("actions", actions);
                pickCardActivity.putExtra("elements", elements);
                pickCardActivity.putExtra("players", players);
                startActivity(pickCardActivity);
                ActionActivity.this.finish();
            }
        });

        Action randomAction = actions.getRandomAction(type);
        actionTextView.setText(randomAction.getReadableAction(elements, players));
        randomAction.impactPlayers(players);
    }
}