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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.LearningGame;

public class GameOverScreen implements Screen {
    private final String TAG = "GameOverScreen";
    private final LearningGame main;
    private final Stage stage;
    private final BitmapFont font;
    private Skin skin;
    private Label timeLabel;
    private Label setName;
    private Label livesLabel;
    private Label gameStatus;


    public GameOverScreen(LearningGame main) {
        this.main = main;
        stage = new Stage(new ScreenViewport());
        skin = main.myAssetManager.manager.get(main.myAssetManager.skin);
        font = main.myAssetManager.manager.get(main.myAssetManager.font);
        font.getData().setScale(1f);
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "Show");
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(createTable());

        setName.setText("Set name");
        if (!main.isGameLost()){
            livesLabel.setText("Lives left: " + main.getLives());
            gameStatus.setText("Game finished");
        } else {
            gameStatus.setText("Game lost");
        }


        if (main.getTimeDt() < main.getPreferences().getMyDataSet(main.getId()).getSetPB()) {
            timeLabel.setText("Your new personal best record is " + main.getTime());
            main.getPreferences().setPb(main.getTimeDt(), main.getId());
        } else {
            timeLabel.setText(main.getTime() + "");
        }
    }

    private Table createTable() {

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(LearningGame.DEBUG);


//        Current set name
        setName = new Label("", new Label.LabelStyle(font, Color.BLACK));
//        Lives
        livesLabel = new Label("", new Label.LabelStyle(font, Color.BLACK));
        //        Timer how much time the game lasted and personal best
        timeLabel = new Label("", new Label.LabelStyle(font, Color.BLACK));
//      Game status
        gameStatus = new Label("", new Label.LabelStyle(font, Color.BLACK));

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

        table.add(gameStatus);
        table.row();
        table.add(timeLabel);
        table.row();

        table.add(playGain).fillX().uniformX().uniformY().width(400).height(100).padBottom(10);
        table.row();

        table.add(returnToMainMenu).fillX().uniformX().uniformY().width(400).height(100).padBottom(10);
        table.row();

        return table;
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
