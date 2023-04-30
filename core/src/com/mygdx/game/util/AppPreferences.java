package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

import netscape.javascript.JSObject;

public class AppPreferences {
    //    Json {{Custom set name, pb time,{{word,translation},{word,translation}}},{{Custom set name, pb time,{{word,translation},{word,translation}}}}
    private static final String PREFS_NAME = "learning_game_prefs";
    private static final String GAME_SPEED = "game_speed";
    private static final String SPAWN_TIMER = "spawn_timer";
    private static final String LIVES_NUMBER = "lives_number";
    private static final String JSON_STRING = "json_string";
    private static final String TAG = "AppPreferences";
    //    Add placeholder here
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
    public void setJsonStringPreferences(ArrayList<MyDataSet> arrayList) {
        Gdx.app.log(TAG,convertArrayListToString(arrayList));
        getPrefs().putString(JSON_STRING, convertArrayListToString(arrayList));
        getPrefs().flush();
    }

    public String getJsonStringPreferences() {
        return getPrefs().getString(JSON_STRING, placeholderJson);
    }


    public String convertArrayListToString(ArrayList<MyDataSet> setArrayList) {
        Json json = new Json();
        return json.toJson(setArrayList);
    }

    public ArrayList<MyDataSet> getMyDataSet() {
        Json json = new Json();
        return json.fromJson(ArrayList.class, getJsonStringPreferences());
    }

    public MyDataSet createDataSet(String setName) {
        MyDataSet dataSet = new MyDataSet();

        dataSet.setSetName(setName);
        dataSet.setSetPB(0);

        return dataSet;
    }

    public ArrayList<MyTuple> getMyTuple(int index) {
        setJsonStringPreferences(createTestData());
        ArrayList<MyDataSet> myDataSets = getMyDataSet();
        return myDataSets.get(index).getWordsArray();
    }
    public ArrayList<MyDataSet> createTestData() {
        ArrayList<MyDataSet> arrayList=new ArrayList<>();

        MyDataSet dataSet = new MyDataSet();
        dataSet.setSetName("arr1");
        dataSet.setSetPB(0);
        dataSet.addWordsToArray(new MyTuple("arr1 word1", "arr1 translation"));
        dataSet.addWordsToArray(new MyTuple("arr1 word2", "arr1 translation"));
        dataSet.addWordsToArray(new MyTuple("arr1 word3", "arr1 translation"));
        dataSet.addWordsToArray(new MyTuple("arr1 word4", "arr1 translation"));
        dataSet.addWordsToArray(new MyTuple("arr1 word5", "arr1 translation"));

        MyDataSet dataSet2 = new MyDataSet();
        dataSet2.setSetName("arr2");
        dataSet2.setSetPB(100000);
        dataSet2.addWordsToArray(new MyTuple("arr2 word1", "arr2 translation"));
        dataSet2.addWordsToArray(new MyTuple("arr2 word2", "arr2 translation"));
        dataSet2.addWordsToArray(new MyTuple("arr2 word3", "arr2 translation"));
        dataSet2.addWordsToArray(new MyTuple("arr2 word4", "arr2 translation"));
        dataSet2.addWordsToArray(new MyTuple("arr2 word5", "arr2 translation"));

        MyDataSet dataSet3 = new MyDataSet();
        dataSet3.setSetName("arr3");
        dataSet3.setSetPB(99999999);
        dataSet3.addWordsToArray(new MyTuple("arr3 word1", "arr3 translation"));
        dataSet3.addWordsToArray(new MyTuple("arr3 word2", "arr3 translation"));
        dataSet3.addWordsToArray(new MyTuple("arr3 word3", "arr3 translation"));
        dataSet3.addWordsToArray(new MyTuple("arr3 word4", "arr3 translation"));
        dataSet3.addWordsToArray(new MyTuple("arr3 word5", "arr3 translation"));

        arrayList.add(dataSet);
        arrayList.add(dataSet2);
        arrayList.add(dataSet3);
        return arrayList;
    }

//    Test

//    public String makeJsonString(ArrayList allDataSets) {
//        Json json = new Json();
//
////        Test
//        MyDataSet data = new MyDataSet();
//        data.setSetName("Test set");
//        data.setSetPB(0);
//        data.addWordsToArray(new MyTuple("arr1 word", "arr1 translation"));
//        data.addWordsToArray(new MyTuple("arr2 word", "arr2 translation"));
//        data.addWordsToArray(new MyTuple("arr3 word", "arr3 translation"));
//
//        MyDataSet data2 = new MyDataSet();
//        data2.setSetName("Test set 2");
//        data2.setSetPB(0);
//        data2.addWordsToArray(new MyTuple("arr1 word", "arr1 translation"));
//        data2.addWordsToArray(new MyTuple("arr2 word", "arr2 translation"));
//        data2.addWordsToArray(new MyTuple("arr3 word", "arr3 translation"));
//
////        ArrayList allDataSets = new ArrayList<>();
//        allDataSets.add(data);
//        allDataSets.add(data2);
//
//        Gdx.app.log(TAG, json.prettyPrint(allDataSets));
//        return json.toJson(allDataSets);
//    }
//
//
//    public void readJsonString() {
//        Json json = new Json();
//        try {
//            ArrayList<MyDataSet> varList = json.fromJson(ArrayList.class, getJsonString());
////        for (ArrayList<MyTuple> arr : var.getWordsArray())
//            for (MyDataSet set : varList) {
//                Gdx.app.log(TAG, set.getSetName());
//            }
//        } catch (Exception e) {
//            Gdx.app.error(TAG, e.toString());
//        }
//
//
////        return json.fromJson(MyDataSet.class,getJsonString());
//    }
}
