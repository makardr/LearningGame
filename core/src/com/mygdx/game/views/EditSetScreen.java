package com.mygdx.game.views;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.LearningGame;
import com.mygdx.game.util.MyDataSet;
import com.mygdx.game.util.MyTuple;

import java.util.ArrayList;

public class EditSetScreen implements Screen {
    private final String TAG = "EditSetScreen";
    private final LearningGame main;
    private final BitmapFont font;
    private int multiplier=1;
    private Stage stage;
    private Skin skin;
    private TextButton saveToPreferencesButton;
    private Label warningLabel;
    private TextField setName;
    private TextButton createDictionaryButton;
    private MyDataSet dataSet;
    private TextField setWord;
    private TextField setWordTranslation;
    private TextButton addWordButton;

    public EditSetScreen(LearningGame main) {
        this.main = main;
        stage = new Stage(new ScreenViewport());
        skin = main.myAssetManager.manager.get(main.myAssetManager.skin);
        font = main.myAssetManager.manager.get(main.myAssetManager.font);
        if(Gdx.app.getType() == Application.ApplicationType.Android) {
            font.getData().setScale(2.0f);
            multiplier=2;
        }
    }

    private Actor createTable() {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(LearningGame.DEBUG);

        warningLabel = new Label("Minimum is three words", new Label.LabelStyle(font, Color.BLACK));
        setName = new TextField("Enter set name here", skin);

        createDictionaryButton = new TextButton("Save set name", skin);
        createDictionaryButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dataSet = main.getPreferences().createNewMyDataSet(setName.getText());
                createDictionaryButton.setVisible(false);
                hideSetName();
                showAddWord();
            }
        });


        addWordButton = new TextButton("Add Word", skin);
        addWordButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                Add check if word and translation is in array
                boolean hasSameWord = false;
                boolean hasSameTranslation = false;

                for (MyTuple tuple : dataSet.getWordsArray()) {
                    if (setWord.getText() == tuple.getFirstValue()) {
                        hasSameWord = true;
                        Gdx.app.log(TAG, "hasSameWord");
                    }
                    if (setWordTranslation.getText() == tuple.getSecondValue()) {
                        hasSameTranslation = true;
                        Gdx.app.log(TAG, "hasSameTranslation");
                    }

                }
                if (!hasSameWord && !hasSameTranslation) {
                    Gdx.app.log(TAG, "Word added: " + setWord.getText() + " " + setWordTranslation.getText());
                    dataSet.addWordsToArray(new MyTuple(setWord.getText(), setWordTranslation.getText()));
                    if (dataSet.getWordsArray().size() >= 3) {
                        saveToPreferencesButton.setVisible(true);
                    }
                    setWord.setText("");
                    setWordTranslation.setText("");
                } else if (hasSameWord) {
                    Gdx.app.log(TAG, "Word is already in dataset: " + setWord.getText());
                    warningLabel.setText("Word is already in the dataset");
                } else if (hasSameTranslation) {
                    Gdx.app.log(TAG, "Translation is already in dataset: " + setWordTranslation.getText());
                    warningLabel.setText("Translation is already in the dataset");
                } else {
                    Gdx.app.log(TAG, "Word is already ");
                }
                Gdx.app.log(TAG, dataSet.getWordsArray() + "");
            }
        });

        saveToPreferencesButton = new TextButton("Save dictionary", skin);
        saveToPreferencesButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ArrayList<MyDataSet> mainArrayList = main.getPreferences().getArrayList();
                mainArrayList.add(dataSet);
                main.getPreferences().saveMainArrayListPreferences(mainArrayList);
                resetScreen();
                main.changeScreen(LearningGame.CHOOSESET);
            }
        });

        final TextButton goBackMenu = new TextButton("Back", skin);
        goBackMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                resetScreen();
                main.changeScreen(LearningGame.CHOOSESET);
            }
        });

        setWord = new TextField("Enter word here", skin);
        setWordTranslation = new TextField("Enter translation here", skin);
        table.defaults().width(400*multiplier).height(100*multiplier);
        table.add(warningLabel).width(75*multiplier).height(75*multiplier).align(Align.left);
        table.row();
        table.add(setName).padBottom(10*multiplier);
        table.row();
        table.add(createDictionaryButton).padBottom(10*multiplier);
        table.row();
        table.add(setWord).padBottom(10*multiplier);
        table.row();
        table.add(setWordTranslation).padBottom(10*multiplier);
        setWord.setVisible(false);
        setWordTranslation.setVisible(false);
        table.row();
        table.add(addWordButton).padBottom(10*multiplier);
        addWordButton.setVisible(false);
        table.row();
        table.add(saveToPreferencesButton).padBottom(10*multiplier);
        saveToPreferencesButton.setVisible(false);
        table.row();
        table.add(goBackMenu);
        return table;
    }

    private void hideSetName() {
        createDictionaryButton.setVisible(false);
        setName.setVisible(false);
    }

    private void showAddWord() {
        setWord.setVisible(true);
        setWordTranslation.setVisible(true);
        addWordButton.setVisible(true);
    }

    private void resetScreen() {
        dataSet = main.getPreferences().createNewMyDataSet(setName.getText());

        createDictionaryButton.setVisible(true);
        setName.setVisible(true);
        setWord.setVisible(false);
        setWordTranslation.setVisible(false);
        addWordButton.setVisible(false);
        saveToPreferencesButton.setVisible(false);

        setName.setText("Enter set name here");
        setWord.setText("Enter word here");
        setWordTranslation.setText("Enter translation here");
    }

    private void saveDictionary() {
    }

    //    Use when fewer than three words
    private void setWarningLabel(String text) {
        warningLabel.setText(text);
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "Show");
        Gdx.input.setInputProcessor(stage);
        stage.addActor(createTable());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(LearningGame.R, LearningGame.G, LearningGame.B, LearningGame.A);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}