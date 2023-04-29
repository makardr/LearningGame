package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;

public class AppPreferences {
    //    Json {{Custom set name, pb time,{{word,translation},{word,translation}}},{{Custom set name, pb time,{{word,translation},{word,translation}}}}
    private static final String PREFS_NAME = "learning_game_prefs";
    private static final String GAME_SPEED = "game_speed";
    private static final String SPAWN_TIMER = "spawn_timer";
    private static final String LIVES_NUMBER = "lives_number";
    private static final String JSON_STRING = "json_string";

    protected Preferences getPrefs() {
        return Gdx.app.getPreferences(PREFS_NAME);
    }

    public void setGameSpeed(Float gameSpeed) {
        getPrefs().putFloat(GAME_SPEED, gameSpeed);
        getPrefs().flush();
    }

    public float getGameSpeed() {
        return getPrefs().getFloat(GAME_SPEED, 1.0f);
    }

    public void setSpawnTimer(int timer) {
        getPrefs().putInteger(SPAWN_TIMER, timer);
        getPrefs().flush();
    }

    public int getSpawnTimer() {
        return getPrefs().getInteger(SPAWN_TIMER, 5);
    }

    public void setLivesNumber(int livesNumber) {
        getPrefs().putInteger(LIVES_NUMBER, livesNumber);
        getPrefs().flush();
    }

    public int getLivesNumber() {
        return getPrefs().getInteger(LIVES_NUMBER, 10);
    }

    public void setJsonString(String jsonString) {
        getPrefs().putString(JSON_STRING, jsonString);
        getPrefs().flush();
    }

    public String getJsonString() {
        return getPrefs().getString(JSON_STRING, "");
    }

    public Array<MyTuple> getMyTupleArray() {
        String json = getJsonString();
        Array<MyTuple> arr = new Array<>(new MyTuple[]{new MyTuple("Karu1", "Медведь1"), new MyTuple("Kevad2", "Весна2"), new MyTuple("Suvi3", "Лето3"), new MyTuple("Sügis4", "Осень4"), new MyTuple("Talv5", "Зима5"), new MyTuple("Talv6", "Зима6"), new MyTuple("Talv7", "Зима7")});
        return arr;
    }
}
