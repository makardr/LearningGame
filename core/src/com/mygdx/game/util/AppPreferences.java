package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;


public class AppPreferences {
    //    Json {{Custom set name, pb time,{{word,translation},{word,translation}}},{{Custom set name, pb time,{{word,translation},{word,translation}}}}
    private static final String PREFS_NAME = "learning_game_prefs";
    private static final String GAME_SPEED = "game_speed";
    private static final String SPAWN_TIMER = "spawn_timer";
    private static final String LIVES_NUMBER = "lives_number";
    private static final String JSON_STRING = "json_string";
    private static final String TAG = "AppPreferences";
    //    Add placeholder here
//    private String placeholderJson = convertArrayListToString(createPlaceholderData());
    private String placeholderJson = "";

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


    //    Json
//    Save main ArrayList in preferences
    public void saveMainArrayListPreferences(ArrayList<MyDataSet> arrayList) {
        Gdx.app.log(TAG, "Saving: " + convertArrayListToString(arrayList));
        getPrefs().putString(JSON_STRING, convertArrayListToString(arrayList));
        getPrefs().flush();
    }

    //    Get main ArrayList from preferences in string or "" if it does not exist yet
    public String getJsonStringPreferences() {
        return getPrefs().getString(JSON_STRING, placeholderJson);
    }

    //      Convert main ArrayList to json string to later store in preferences
    public String convertArrayListToString(ArrayList<MyDataSet> setArrayList) {
        Json json = new Json();
        return json.toJson(setArrayList);
    }

    //     Convert main ArrayList from string to ArrayList and return it
    public ArrayList<MyDataSet> getArrayList() {
        Gdx.app.log(TAG, "Loading main ArrayList");
        Json json = new Json();
        return json.fromJson(ArrayList.class, getJsonStringPreferences());
    }

    //    Create empty default MyDataSet
    public MyDataSet createNewMyDataSet(String setName) {
        MyDataSet dataSet = new MyDataSet();
        dataSet.setSetName(setName);
        dataSet.setSetPB(999999999);
        return dataSet;
    }

    //    Used if dataset is missing on first startup, saves empty array list
    public void createNewMainArrayList() {
        ArrayList<MyDataSet> arrayList = new ArrayList<>();
        saveMainArrayListPreferences(arrayList);
    }

    //    From globally saved main ArrayList get set ArrayList and from that get MyTuple
    public ArrayList<MyTuple> getMyTuple(int index) {
        ArrayList<MyDataSet> myDataSets = getArrayList();
        return myDataSets.get(index).getWordsArray();
    }
    public MyDataSet getMyDataSet(int index) {
        ArrayList<MyDataSet> myDataSets = getArrayList();
        return myDataSets.get(index);
    }
    public void setPb(float pb,int index){
        ArrayList<MyDataSet> myDataSets = getArrayList();
        MyDataSet dataSet = myDataSets.get(index);
        dataSet.setSetPB(pb);
        saveMainArrayListPreferences(myDataSets);
    }
    //    Remove set ArrayList from global ArrayList
    public void removeArrayList(int index) {
        Gdx.app.log(TAG, "Removing index " + index + " from array list");
        ArrayList<MyDataSet> tlist = getArrayList();
        tlist.remove(index);
        saveMainArrayListPreferences(tlist);
        Gdx.app.log(TAG, "Main ArrayList is now " + getArrayList());
    }

//    //    Test data set
//    public ArrayList<MyDataSet> createPlaceholderData() {
//        ArrayList<MyDataSet> arrayList = new ArrayList<>();
//
//        MyDataSet dataSet = new MyDataSet();
//        dataSet.setSetName("arr1");
//        dataSet.setSetPB(0);
//        dataSet.addWordsToArray(new MyTuple("arr1 word1", "arr1 translation"));
//        dataSet.addWordsToArray(new MyTuple("arr1 word2", "arr1 translation"));
//        dataSet.addWordsToArray(new MyTuple("arr1 word3", "arr1 translation"));
//        dataSet.addWordsToArray(new MyTuple("arr1 word4", "arr1 translation"));
//        dataSet.addWordsToArray(new MyTuple("arr1 word5", "arr1 translation"));
//
//        MyDataSet dataSet2 = new MyDataSet();
//        dataSet2.setSetName("arr2");
//        dataSet2.setSetPB(100000);
//        dataSet2.addWordsToArray(new MyTuple("arr2 word1", "arr2 translation"));
//        dataSet2.addWordsToArray(new MyTuple("arr2 word2", "arr2 translation"));
//        dataSet2.addWordsToArray(new MyTuple("arr2 word3", "arr2 translation"));
//        dataSet2.addWordsToArray(new MyTuple("arr2 word4", "arr2 translation"));
//        dataSet2.addWordsToArray(new MyTuple("arr2 word5", "arr2 translation"));
//
//        MyDataSet dataSet3 = new MyDataSet();
//        dataSet3.setSetName("arr3");
//        dataSet3.setSetPB(99999999);
//        dataSet3.addWordsToArray(new MyTuple("arr3 word1", "arr3 translation"));
//        dataSet3.addWordsToArray(new MyTuple("arr3 word2", "arr3 translation"));
//        dataSet3.addWordsToArray(new MyTuple("arr3 word3", "arr3 translation"));
//        dataSet3.addWordsToArray(new MyTuple("arr3 word4", "arr3 translation"));
//        dataSet3.addWordsToArray(new MyTuple("arr3 word5", "arr3 translation"));
//
//        arrayList.add(dataSet);
//        arrayList.add(dataSet2);
//        arrayList.add(dataSet3);
//
//        saveMainArrayListPreferences(arrayList);
//        return arrayList;
//    }
}
