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

import org.w3c.dom.Text;

public class PickCardActivity extends AppCompatActivity {

    PlayersList players;
    ElementsList elements;
    ActionsList actions;

    TextView currentPlayerName;
    TextView reminderText;
    Button greenActionButton;
    Button orangeActionButton;
    Button redActionButton;
    Button noActionButton;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_card);

        players = (PlayersList)getIntent().getSerializableExtra("players");
        elements = (ElementsList)getIntent().getSerializableExtra("elements");
        actions = (ActionsList)getIntent().getSerializableExtra("actions");

        currentPlayerName = (TextView) findViewById(R.id.activity_pick_card_name_text);
        reminderText = (TextView) findViewById(R.id.activity_pick_card_reminder_text);
        greenActionButton = (Button) findViewById(R.id.activity_pick_card_green_action);
        orangeActionButton = (Button) findViewById(R.id.activity_pick_card_orange_action);
        redActionButton = (Button) findViewById(R.id.activity_pick_card_red_action);
        noActionButton = (Button) findViewById(R.id.activity_pick_card_no_action);

        update();

        greenActionButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActionActivity(Constants.GREEN_ACTION);
            }

        });

        orangeActionButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActionActivity(Constants.ORANGE_ACTION);
            }

        });

        redActionButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActionActivity(Constants.RED_ACTION);
            }

        });

        noActionButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Next Player
                players.nextPlayer();
                update();
            }

        });
    }

    public void update(){
        Player currentPlayer = players.getCurrentPlayer();
        currentPlayerName.setText(currentPlayer.getName());
        String reminder = "";
        if (currentPlayer.hasDeEnMoins()){
            reminder = reminder + "Vous avez un dé en moins ! ";
            Log.d("**", "Reminder dé en moins");
        }
        if (currentPlayer.hasLancerEnPlus()){
            reminder = reminder + "Vous avez un lancer en plus ! ";
        }
        if (currentPlayer.hasLancerEnMoins()){
            reminder = reminder + "Vous avez un lancer en moins ! ";
        }
        reminderText.setText(reminder);
    }

    public void openActionActivity(int type){
        Intent actionActivity = new Intent(PickCardActivity.this, ActionActivity.class);
        Bundle b = new Bundle();
        b.putInt("type", type);
        actionActivity.putExtra("actions", actions);
        actionActivity.putExtra("elements", elements);
        actionActivity.putExtra("players", players);
        actionActivity.putExtras(b);
        startActivity(actionActivity);
        PickCardActivity.this.finish();
    }
}