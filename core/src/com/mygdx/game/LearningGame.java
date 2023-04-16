package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.util.B2dAssetManager;
import com.mygdx.game.views.GameOverScreen;
import com.mygdx.game.views.GameScreen;
import com.mygdx.game.views.LoadingScreen;
import com.mygdx.game.views.MenuScreen;
import com.mygdx.game.views.PreferencesScreen;
import com.mygdx.game.views.UserDictionaryAddScreen;

public class LearningGame extends Game {
    private LoadingScreen loadingScreen;
    private MenuScreen menuScreen;
    private PreferencesScreen preferencesScreen;

    private GameScreen gameScreen;
    private GameOverScreen gameOverScreen;
    private UserDictionaryAddScreen userDictionaryAddScreen;

    public final static int LOADING = 0;
    public final static int MENU = 1;
    public final static int PREFERENCES = 2;
    public final static int GAMESCREEN = 3;
    public final static int ENDGAME = 4;
    private final String TAG = "Main";

    public B2dAssetManager b2dAssetManager = new B2dAssetManager();


    @Override
    public void create() {
        changeScreen(LOADING);
    }


    public void changeScreen(int screen) {
        switch (screen) {
            case LOADING:
                if (loadingScreen == null) loadingScreen = new LoadingScreen(this);
                this.setScreen(loadingScreen);
                break;
            case MENU:
                if (menuScreen == null) menuScreen = new MenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case PREFERENCES:
                if (preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
                this.setScreen(preferencesScreen);
                break;
            case GAMESCREEN:
                if (gameScreen == null) gameScreen = new GameScreen(this);
                this.setScreen(gameScreen);
                break;
            case ENDGAME:
                if (gameOverScreen == null) gameOverScreen = new GameOverScreen(this);
                this.setScreen(gameOverScreen);
                break;
        }
    }

    public void restartGame() {
        try {

            changeScreen(ENDGAME);
//            gameScreen.dispose();
//            gameScreen.gameStart();
        } catch (Exception e){
            Gdx.app.error(TAG, "e.getMessage()");
            Gdx.app.error(TAG, e.getMessage());
        }

    }

    public String getTime(){
        return gameScreen.getLastTime();
    }
    public float getTimeDt(){
        return gameScreen.getLastTimeDt();
    }
    public String getLives(){
        return gameScreen.getCurrentLives();
    }
    @Override
    public void dispose() {
        super.dispose();
    }
}
