package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.util.AppPreferences;
import com.mygdx.game.util.MyAssetManager;
import com.mygdx.game.views.GameOverScreen;
import com.mygdx.game.views.GameScreen;
import com.mygdx.game.views.LoadingScreen;
import com.mygdx.game.views.MenuScreen;
import com.mygdx.game.views.PreferencesScreen;
import com.mygdx.game.views.EditSetScreen;
import com.mygdx.game.views.ChooseSetScreen;

public class LearningGame extends Game {
    private final String TAG = "Main";
    private LoadingScreen loadingScreen;
    private MenuScreen menuScreen;
    private PreferencesScreen preferencesScreen;
    private GameScreen gameScreen;
    private GameOverScreen gameOverScreen;
    private EditSetScreen editSetScreen;
    private ChooseSetScreen chooseSetScreen;
    private String currentSet;

    public final static int LOADING = 0;
    public final static int MENU = 1;
    public final static int PREFERENCES = 2;
    public final static int GAMESCREEN = 3;
    public final static int ENDGAME = 4;
    public final static int CHOOSESET = 5;
    public final static int EDITSET = 6;

    public MyAssetManager myAssetManager = new MyAssetManager();
    private AppPreferences preferences = new AppPreferences();


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
            case CHOOSESET:
                if (chooseSetScreen == null) chooseSetScreen = new ChooseSetScreen(this);
                this.setScreen(chooseSetScreen);
                break;
            case EDITSET:
                if (editSetScreen == null) editSetScreen = new EditSetScreen(this);
                this.setScreen(editSetScreen);
                break;
        }
    }

//    public void restartGame() {
//        try {
//            changeScreen(ENDGAME);
//            gameScreen.restartGame();
//        } catch (Exception e){
//            Gdx.app.error(TAG, "e.getMessage()");
//            Gdx.app.error(TAG, e.getMessage());
//        }
//
//    }

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
    public String getCurrentSet() {
        return currentSet;
    }

    public void setCurrentSet(String currentSet) {
        this.currentSet = currentSet;
    }

    public AppPreferences getPreferences() {
        return this.preferences;
    }

}
