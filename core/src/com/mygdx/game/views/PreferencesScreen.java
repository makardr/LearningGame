package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.LearningGame;
import com.mygdx.game.util.DigitFilter;

import java.util.Objects;

public class PreferencesScreen implements Screen {
    private final String TAG = "PreferencesScreen";
    private final DigitFilter filter;
    private LearningGame main;
    private Stage stage;
    private Skin skin;
    private TextField livesSettingTextField;
    private TextField spawnTimeTextField;
    private BitmapFont font;

    public PreferencesScreen(LearningGame main) {
        this.main = main;
        stage = new Stage(new ScreenViewport());
        skin = main.myAssetManager.manager.get(main.myAssetManager.skin);
        font = main.myAssetManager.manager.get(main.myAssetManager.font);
        filter = new DigitFilter();
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "Show");
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(createTable());
        stage.addActor(createSecondTable());
    }

    private Actor createTable() {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(LearningGame.DEBUG);
        table.defaults().width(200).height(100);
        Label speedLabel = new Label("Game speed:", new Label.LabelStyle(font, Color.BLACK));

        final Slider gameSpeedSlider = new Slider(1f, 2f, 0.1f, false, skin);
        gameSpeedSlider.setValue(main.getPreferences().getGameSpeed());
        gameSpeedSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                main.getPreferences().setGameSpeed(gameSpeedSlider.getValue());
                return false;
            }
        });

        Label livesLabel = new Label("Lives:", new Label.LabelStyle(font, Color.BLACK));
        livesSettingTextField = new TextField(String.valueOf(main.getPreferences().getLivesNumber()), skin);
        livesSettingTextField.setAlignment(Align.center);
        livesSettingTextField.setTextFieldListener(filter);
        livesSettingTextField.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (!Objects.equals(livesSettingTextField.getText(), "")) {
                    main.getPreferences().setLivesNumber(Integer.parseInt(livesSettingTextField.getText()));
                }
                return false;
            }
        });

        Label spawnTimeLabel = new Label("Spawn time:", new Label.LabelStyle(font, Color.BLACK));
        spawnTimeTextField = new TextField(String.valueOf(main.getPreferences().getSpawnTimer()), skin);
        spawnTimeTextField.setTextFieldListener(filter);
        spawnTimeTextField.setAlignment(Align.center);
        spawnTimeTextField.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (!Objects.equals(spawnTimeTextField.getText(), "")) {
                    main.getPreferences().setSpawnTimer(Integer.parseInt(spawnTimeTextField.getText()));
                }
                return false;
            }
        });

        table.add(speedLabel).padRight(10);
        table.add(gameSpeedSlider);
        table.row();
        table.add(livesLabel).padRight(10);
        table.add(livesSettingTextField);
        table.row();
        table.add(spawnTimeLabel).padRight(10);
        table.add(spawnTimeTextField);
        table.row();
        return table;
    }

    private Actor createSecondTable() {
        Table table = new Table();
        table.setFillParent(false);
        table.setDebug(LearningGame.DEBUG);
        table.setBounds(0, 100, Gdx.graphics.getWidth(), 300);

        final TextButton back = new TextButton("Back", skin);
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.changeScreen(LearningGame.MENU);
            }
        });
        table.add(back).width(400).height(100).padTop(30);
        return table;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(LearningGame.R, LearningGame.G, LearningGame.B, LearningGame.A);
//        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
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
