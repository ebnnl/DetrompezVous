package com.example.dtrompez_vous;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ElementsList elements = new ElementsList();
        elements.addElement(new Element("une défense"));
        elements.addElement(new Element("une patte"));
        elements.addElement(new Element("une trompe"));
        elements.addElement(new Element("une oreille"));


        // TODO : ajuster les probabilités
        ActionsList actions = new ActionsList();
        actions.addAction(new Action("Prendre [element] à [joueur]", Constants.NO_IMPACT, 1), Constants.GREEN_ACTION);
        actions.addAction(new Action("Rejouer", Constants.REPLAY_IMPACT, 1), Constants.GREEN_ACTION);
        actions.addAction(new Action("Vous avez un lancer en plus pour les 2 prochains tours", Constants.LANCER_EN_PLUS_IMPACT, 1), Constants.GREEN_ACTION);
        actions.addAction(new Action("Echanger avec l'éléphant le plus garni", Constants.NO_IMPACT, 1), Constants.GREEN_ACTION);
        actions.addAction(new Action("Prendre [element] au joueur de votre choix", Constants.NO_IMPACT, 1), Constants.GREEN_ACTION);
        actions.addAction(new Action("Prendre [element] à la pioche", Constants.NO_TROMPE, 1), Constants.GREEN_ACTION);
        actions.addAction(new Action("Prendre en élément à [joueur]", Constants.NO_IMPACT, 1), Constants.GREEN_ACTION);


        actions.addAction(new Action("Echanger avec l'éléphant de [joueur]", Constants.NO_IMPACT, 1), Constants.ORANGE_ACTION);
        actions.addAction(new Action("Aller sur la case verte la plus proche (et tirer une action verte)", Constants.REPLAY_IMPACT, 1), Constants.ORANGE_ACTION);
        actions.addAction(new Action("Aller sur la case rouge la plus proche (et tirer une action rouge)", Constants.REPLAY_IMPACT, 1), Constants.ORANGE_ACTION);
        actions.addAction(new Action("Avancer de [n] cases et effectuer l'action correspondante", Constants.REPLAY_IMPACT, 1), Constants.ORANGE_ACTION);
        actions.addAction(new Action("Reculer de [n] cases et effectuer l'action correspondante", Constants.REPLAY_IMPACT, 1), Constants.ORANGE_ACTION);
        actions.addAction(new Action("[joueur] donne [element] au joueur de son choix", Constants.NO_IMPACT, 1), Constants.ORANGE_ACTION);


        actions.addAction(new Action("Donner [element] à [joueur]", Constants.NO_IMPACT, 1), Constants.RED_ACTION);
        actions.addAction(new Action("Laisser un élément à la pioche", Constants.NO_IMPACT, 1), Constants.RED_ACTION);
        actions.addAction(new Action("Vous avez un dé en moins pour les 3 prochains tours", Constants.DE_EN_MOINS_IMPACT, 1), Constants.RED_ACTION);
        actions.addAction(new Action("Echanger avec l'éléphant le moins garni", Constants.NO_IMPACT, 1), Constants.RED_ACTION);
        actions.addAction(new Action("Donner un élément de votre choix à [joueur]", Constants.NO_IMPACT, 1), Constants.RED_ACTION);
        actions.addAction(new Action("Donner un élément à [joueur] ([joueur] choisit l'élément)", Constants.NO_IMPACT, 1), Constants.RED_ACTION);
        actions.addAction(new Action("Donner un élément à chaque joueur", Constants.NO_IMPACT, 1), Constants.RED_ACTION);
        actions.addAction(new Action("Passer votre prochain tour", Constants.SKIP_IMPACT, 1), Constants.RED_ACTION);
        actions.addAction(new Action("Vous avez un lancer en moins pour les 2 prochains tours", Constants.LANCER_EN_MOINS_IMPACT, 1), Constants.RED_ACTION);


        mPlayButton = (Button) findViewById(R.id.activity_main_play_button);
        mPlayButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent createPlayersActivity = new Intent(MainActivity.this, CreatePlayersActivity.class);
                createPlayersActivity.putExtra("elements", elements);
                createPlayersActivity.putExtra("actions", actions);
                startActivity(createPlayersActivity);
            }
        });
    }
}