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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.LearningGame;

public class GameOverScreen implements Screen {
    private final String TAG = "GameOverScreen";
    private final LearningGame main;
    private final Stage stage;
    private Skin skin;


    public GameOverScreen(LearningGame main) {
        this.main = main;
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "Show");
        Gdx.input.setInputProcessor(stage);
        skin = main.b2dAssetManager.manager.get("skin/uiskin.json");
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);


//        Current set name
        Label setName = new Label("Set name", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
//        Lives
        Label livesLabel = new Label("Lives left: " + main.getLives(), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //        Timer how much time the game lasted
        Label timeLabel = new Label(main.getTime() + "", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label newPb = new Label("Congratulations! Your new personal best record is " + main.getTime(), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

//        Personal best for this dictionary

        TextButton playGain = new TextButton("Play again", skin);
        playGain.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.changeScreen(LearningGame.GAMESCREEN);
            }
        });
        TextButton returnToMainMenu = new TextButton("Menu", skin);
        returnToMainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.changeScreen(LearningGame.MENU);
            }
        });

        table.add(setName);
        table.row();

        table.add(livesLabel);
        table.row();

        float testRecord = 1.1f;
        if (main.getTimeDt() < testRecord) {
            table.add(newPb);
            table.row();
        } else {
            table.add(timeLabel);
            table.row();
        }

        table.add(playGain).fillX().uniformX().uniformY().width(400).height(150);
        table.row();

        table.add(returnToMainMenu).fillX().uniformX().uniformY().width(400).height(150);
        table.row();

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); //  clear the screen
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        main.changeScreen(main.MENU);
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

    }
}
