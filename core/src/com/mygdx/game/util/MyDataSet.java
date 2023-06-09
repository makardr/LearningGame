package com.mygdx.game.util;

import java.util.ArrayList;

public class MyDataSet {
    private String setName;
    private float setPB;
    private ArrayList wordsArray;

    public MyDataSet() {
        wordsArray = new ArrayList<>();
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public float getSetPB() {
        return setPB;
    }

    public void setSetPB(float setPB) {
        this.setPB = setPB;
    }

    public ArrayList<MyTuple> getWordsArray() {
        return wordsArray;
    }

    public void setWordsArray(ArrayList wordsArray) {
        this.wordsArray = wordsArray;
    }

    public void addWordsToArray(MyTuple tuple) {
        wordsArray.add(tuple);
    }
}
