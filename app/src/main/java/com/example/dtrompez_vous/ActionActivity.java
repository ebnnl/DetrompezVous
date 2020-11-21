package com.example.dtrompez_vous;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.example.dtrompez_vous.R.*;
import static com.example.dtrompez_vous.R.layout.timer_popup;

public class ActionActivity extends AppCompatActivity {

    private int type;

    private TextView actionTextView;
    private Button okButton;
    private  TextView playerName;
    private Button precisionButton;

    private ActionsList actions;
    private  ElementsList elements;
    private PlayersList players;

    private int timer = 0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_action);

        actions = (ActionsList)getIntent().getSerializableExtra("actions");
        elements = (ElementsList)getIntent().getSerializableExtra("elements");
        players = (PlayersList)getIntent().getSerializableExtra("players");

        Bundle b = getIntent().getExtras();
        if (b != null){
            type = b.getInt("type");
        }

        playerName = (TextView) findViewById(id.activity_action_player_name);
        actionTextView = (TextView) findViewById(id.activity_action_text);
        okButton = (Button) findViewById(id.activity_action_ok_button);
        precisionButton = (Button) findViewById(id.activity_action_precision_button);

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
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                ActionActivity.this.finish();
            }
        });



        Action randomAction = actions.getRandomAction(type);
        actionTextView.setText(randomAction.getReadableAction(elements, players));
        randomAction.impactPlayers(players);

        if (randomAction.getImpact() >= 18){
            timer = randomAction.getImpact()-10;
            startTimer();
        }

        precisionButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(ActionActivity.this)
                        .setTitle("Précisions")
                        .setMessage(randomAction.getPrecision())
                        .setPositiveButton("Compris !", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }
        });
    }

    private void startTimer(){
        okButton.setText("Je suis prêt(e) !");
        okButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(ActionActivity.this)
                        .setTitle("Vous avez 15 secondes !")
                        .setMessage("Dépêchez-vous !")
                        .setPositiveButton("Fait !", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                players.nextPlayer();
                                Intent pickCardActivity = new Intent(ActionActivity.this, PickCardActivity.class);
                                pickCardActivity.putExtra("actions", actions);
                                pickCardActivity.putExtra("elements", elements);
                                pickCardActivity.putExtra("players", players);
                                startActivity(pickCardActivity);
                                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                ActionActivity.this.finish();
                            }
                        })
                        .create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    private final int AUTO_DISMISS_MILLIS = timer*1000;
                    @Override
                    public void onShow(final DialogInterface dialog) {
                        //final Button defaultButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                        //final CharSequence negativeButtonText = defaultButton.getText();
                        new CountDownTimer(AUTO_DISMISS_MILLIS, 100) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                ((AlertDialog) dialog).setTitle("Vous avez "+Long.toString(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)+1)+ " secondes.");
                            }
                            @Override
                            public void onFinish() {
                                if (((AlertDialog) dialog).isShowing()) {
                                    // Todo : Add sound
                                    dialog.dismiss();
                                    players.nextPlayer();
                                    Intent pickCardActivity = new Intent(ActionActivity.this, PickCardActivity.class);
                                    pickCardActivity.putExtra("actions", actions);
                                    pickCardActivity.putExtra("elements", elements);
                                    pickCardActivity.putExtra("players", players);
                                    startActivity(pickCardActivity);
                                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                    ActionActivity.this.finish();
                                }
                            }
                        }.start();
                    }
                });
                dialog.show();
            }
        });


    }


}