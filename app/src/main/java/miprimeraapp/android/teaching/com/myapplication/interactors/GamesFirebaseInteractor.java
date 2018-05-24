package miprimeraapp.android.teaching.com.myapplication.interactors;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import miprimeraapp.android.teaching.com.myapplication.model.GameModel;

public class GamesFirebaseInteractor {
    private  ArrayList<GameModel> games = new ArrayList<>();
    public ArrayList<GameModel> getGames() {
        return games;
    }

    public GameModel getGameWithId(int id) {
        for (GameModel game : games) {
            if (game.getId() == id) {
                return game;}
        }
        return null;
    }


    public void getGames(final GamesInteractorCallback callback) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gamesDatabaseReference = database.getReference("games");
        gamesDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot gameSnapshot:dataSnapshot.getChildren())
                {
                    GameModel value = gameSnapshot.getValue(GameModel.class);
                    games.add(value);
                    Log.d("Listadejuegos", "resultado: " + value.getName());

                }
                callback.onGamesAvailable();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public GamesFirebaseInteractor() {


    }



}
