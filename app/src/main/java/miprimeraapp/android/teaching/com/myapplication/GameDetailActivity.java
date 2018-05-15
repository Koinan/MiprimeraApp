package miprimeraapp.android.teaching.com.myapplication;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import miprimeraapp.android.teaching.com.myapplication.View.GameDetailView;
import miprimeraapp.android.teaching.com.myapplication.model.GameModel;
import miprimeraapp.android.teaching.com.myapplication.presenter.GameDetailPresenter;

public class GameDetailActivity extends AppCompatActivity
    implements GameDetailView {
    private GameDetailPresenter presenter;
    private int currentGameId;
    private String currentGameWebsite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        Toolbar myToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.leftarrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(R.string.GameDetail);
        presenter = new GameDetailPresenter();
        currentGameId = getIntent().getIntExtra("iddeljuego", 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.startPresenting(this);
        presenter.loadGameWithId(currentGameId);
    }

    public void Website(View view) {
        String url = "https://eu.battle.net/forums/en/wow/";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void onGameLoaded(GameModel game) {
        ImageView icono = findViewById(R.id.game_icon);
        ImageView background = findViewById(R.id.Fondodetail);
        TextView description = findViewById(R.id.Info);


        description.setText(game.getDescription());
        background.setImageResource(game.getBackgroundDrawable());
        icono.setImageResource(game.getIconDrawable());
        getSupportActionBar().setTitle(game.getName());
        this.currentGameWebsite = game.getOfficialWebsiteUrl();

    }
    public void goToWebsite (View view) {
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(currentGameWebsite));
        startActivity(websiteIntent);
    }
}
