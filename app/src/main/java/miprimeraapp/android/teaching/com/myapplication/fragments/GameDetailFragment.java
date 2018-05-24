package miprimeraapp.android.teaching.com.myapplication.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import miprimeraapp.android.teaching.com.myapplication.R;
import miprimeraapp.android.teaching.com.myapplication.WebViewActivity;
import miprimeraapp.android.teaching.com.myapplication.interactors.GamesFirebaseInteractor;
import miprimeraapp.android.teaching.com.myapplication.interactors.GamesInteractor;
import miprimeraapp.android.teaching.com.myapplication.interactors.GamesInteractorCallback;
import miprimeraapp.android.teaching.com.myapplication.model.GameModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameDetailFragment extends Fragment {
    private GamesFirebaseInteractor gamesFirebaseInteractor;


    public GameDetailFragment() {
        // Required empty public constructor
    }
    public static GameDetailFragment newInstance(int gameId) {
        GameDetailFragment fragment = new GameDetailFragment();
        Bundle myBundle = new Bundle();
        myBundle.putInt("game_id", gameId);
        fragment.setArguments(myBundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_game_detail, container, false);

        final int gameID = getArguments().getInt("game_id", 0);

        gamesFirebaseInteractor = new GamesFirebaseInteractor();
        gamesFirebaseInteractor.getGames(new GamesInteractorCallback() {

            @Override
            public void onGamesAvailable() {
                final GameModel game = gamesFirebaseInteractor.getGameWithId(gameID);
                ImageView icono = getView().findViewById(R.id.game_icon);
                Glide.with(getView()).load(
                        game.getIcon())
                        .into(icono);

                ImageView background = getView().findViewById(R.id.Fondodetail);
                Glide.with(getView()).load(
                        game.getBackground())
                        .into(background);
                TextView description = getView().findViewById(R.id.Info);
                description.setText(game.getDescription());
                Button boton = getView().findViewById(R.id.website_button);
                boton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent webIntent = new Intent(getContext(), WebViewActivity.class);
                        webIntent.putExtra("urljuego", game.getOfficialWebsiteUrl());
                        startActivity(webIntent);
                    }
                });

            }
        });

        return fragmentView;
    }
}
