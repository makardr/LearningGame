package com.mygdx.game.hud;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.LearningGame;
import com.mygdx.game.views.GameScreen;

public class Hud implements Disposable{
    private final GameScreen screen;
    private final BitmapFont font;
    public Stage stage;
    private Viewport viewport;
    //    Widgets
    private Label livesLabel;
    private final Label objectLabel;
    private final Label timeLabel;
    private LearningGame main;
    private Skin skin;
    public Hud(SpriteBatch spriteBatch, final LearningGame main, final GameScreen screen) {
        this.main=main;
        this.screen=screen;
        this.skin=main.myAssetManager.manager.get(main.myAssetManager.skin);
        viewport = new ScreenViewport(new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.setDebug(false);
        String value = "";
        font = main.myAssetManager.manager.get(main.myAssetManager.font);
        if(Gdx.app.getType() == Application.ApplicationType.Android) {
            font.getData().setScale(2.0f);
        }

//        final TextButton back = new TextButton("Back", skin);
//        back.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                main.changeScreen(LearningGame.ENDGAME);
//                screen.gameOver();
//            }
//        });

        livesLabel = new Label(value, new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(font, Color.BLACK));
        table.add(livesLabel).expandX().padTop(10);
        timeLabel = new Label(value, new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(font, Color.BLACK));
        table.add(timeLabel).expandX().padTop(10);
        objectLabel = new Label(value, new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(font, Color.BLACK));
        table.add(objectLabel).expandX().padTop(10);
//        table.add(back);

        stage.addActor(table);
    }

    public void updateHud(String newLivesValue, String newTimeValue, String newObjectValue) {
        livesLabel.setText("Lives: " + newLivesValue);
        timeLabel.setText(newTimeValue);
        objectLabel.setText(newObjectValue);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
