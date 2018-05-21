package miprimeraapp.android.teaching.com.myapplication.presenter;

import java.util.ArrayList;

import miprimeraapp.android.teaching.com.myapplication.View.GameDetailView;
import miprimeraapp.android.teaching.com.myapplication.interactors.GamesInteractor;
import miprimeraapp.android.teaching.com.myapplication.model.GameModel;

public class GameDetailPresenter {
    private GamesInteractor interactor;
    private GameDetailView view;
    public void startPresenting(GameDetailView view) {
        this.view = view;
        interactor = new GamesInteractor();


    }
    public void loadGameWithId(int id) {
       GameModel game = interactor.getGameWithId(id);
       view.onGameLoaded(game);
    }
    public ArrayList<GameModel> getGames() {
        return interactor.getGames();
    }
}
