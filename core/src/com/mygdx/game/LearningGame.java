package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.util.AppPreferences;
import com.mygdx.game.util.MyAssetManager;
import com.mygdx.game.util.MyDataSet;
import com.mygdx.game.views.GameOverScreen;
import com.mygdx.game.views.GameScreen;
import com.mygdx.game.views.LoadingScreen;
import com.mygdx.game.views.MenuScreen;
import com.mygdx.game.views.PreferencesScreen;
import com.mygdx.game.views.EditSetScreen;
import com.mygdx.game.views.ChooseSetScreen;

import java.util.ArrayList;

public class LearningGame extends Game {
    private final String TAG = "Main";
    private LoadingScreen loadingScreen;
    private MenuScreen menuScreen;
    private PreferencesScreen preferencesScreen;
    private GameScreen gameScreen;
    private GameOverScreen gameOverScreen;
    private EditSetScreen editSetScreen;
    private ChooseSetScreen chooseSetScreen;

    public final static int LOADING = 0;
    public final static int MENU = 1;
    public final static int PREFERENCES = 2;
    public final static int GAMESCREEN = 3;
    public final static int ENDGAME = 4;
    public final static int CHOOSESET = 5;
    public final static int EDITSET = 6;
    public final static float R = 0.4f;
    public final static float G = 0.8f;
    public final static float B = 1f;
    public final static float A = 1f;
    public final static boolean DEBUG=false;
    public MyAssetManager myAssetManager = new MyAssetManager();
    private AppPreferences preferences = new AppPreferences();
    private int id;
    private boolean gameLost=false;
    @Override
    public void create() {
        Gdx.app.log(TAG, getPreferences().getJsonStringPreferences());
        if (getPreferences().getJsonStringPreferences() == "") {
//            Create new array list if it does not exist
            Gdx.app.error(TAG,"Could not find data, creating");
            getPreferences().createNewMainArrayList();
        }
        Gdx.app.log(TAG,"Current preferences are: "+getPreferences().getJsonStringPreferences());
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

    public String getTime() {
        return gameScreen.getLastTime();
    }

    public float getTimeDt() {
        return gameScreen.getLastTimeDt();
    }

    public String getLives() {
        return gameScreen.getCurrentLives();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public AppPreferences getPreferences() {
        return this.preferences;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGameLost() {
        return gameLost;
    }

    public void setGameLost(boolean gameLost) {
        this.gameLost = gameLost;
    }
}
