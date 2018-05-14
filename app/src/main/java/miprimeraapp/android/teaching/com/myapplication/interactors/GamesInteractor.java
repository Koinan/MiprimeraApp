package miprimeraapp.android.teaching.com.myapplication.interactors;

import java.util.ArrayList;

import miprimeraapp.android.teaching.com.myapplication.R;
import miprimeraapp.android.teaching.com.myapplication.model.GameModel;

public class GamesInteractor {
    private ArrayList<GameModel> games;

    public GamesInteractor() {
        if (games == null) {
            GameModel WoWGameModel = new GameModel(0,"World of Warcraft", "Descripcion WoW",
                    "https://worldofwarcraft.com", R.drawable.wowbutton, R.drawable.imagen1);
            GameModel SCGameModel = new GameModel(1, "Starcraft", "Descripcion SC",
                    "https://starcraft.com", R.drawable.sc1, R.drawable.descarga);

            games = new ArrayList<>();
            games.add(WoWGameModel);
            games.add(SCGameModel);

        }
    }

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
}
