package com.mygdx.game.views;

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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.LearningGame;

public class EditSetScreen implements Screen {
    private final String TAG = "EditSetScreen";
    private final LearningGame main;
    private Stage stage;
    private Skin skin;

    private Array<String> tempDictionary;
    private Label warningLabel;
    private TextField wordTextField;

    public EditSetScreen(LearningGame main) {
        this.main = main;
        stage = new Stage(new ScreenViewport());
        skin = main.b2dAssetManager.manager.get("skin/uiskin.json");
    }

    private Actor createTable() {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);

        warningLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        wordTextField = new TextField("Write words here", skin);

        final TextButton addWordButton = new TextButton("Add Word", skin);
        addWordButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                addWordToDictionary();
            }
        });

        final TextButton saveDictionaryButton = new TextButton("Save Dictionary", skin);
        saveDictionaryButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                saveDictionary();
            }
        });
        final TextButton cancelDictionaryButton = new TextButton("Cancel Dictionary", skin);
        cancelDictionaryButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.changeScreen(LearningGame.CHOOSESET);
            }
        });

        table.add(warningLabel).width(200).height(75);
        table.row();
        table.add(wordTextField).padRight(10).width(250).height(75);
        table.add(addWordButton).width(250).height(75);
        table.row();
        table.add(saveDictionaryButton).padRight(10).width(250).height(75);
        table.add(cancelDictionaryButton).width(250).height(75);
        return table;
    }

    private void addWordToDictionary() {
        Gdx.app.log(TAG, wordTextField.getText());
    }

    private void saveDictionary() {
    }

//    Use when fewer than three words
    private void setWarningLabel(String text){
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
        Gdx.gl.glClearColor(0, 0, 0, 1); //  clear the screen
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