package com.mygdx.game.util;

import java.util.ArrayList;

public class MyDataSet {
    private String setName;
    private int setPB;
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

    public int getSetPB() {
        return setPB;
    }

    public void setSetPB(int setPB) {
        this.setPB = setPB;
    }

    public ArrayList getWordsArray() {
        return wordsArray;
    }

    public void setWordsArray(ArrayList wordsArray) {
        this.wordsArray = wordsArray;
    }

    public void addWordsToArray(MyTuple tuple) {
        wordsArray.add(tuple);
    }
}
