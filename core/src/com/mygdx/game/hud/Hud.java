package com.mygdx.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.LearningGame;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    //    Widgets
    private Label livesLabel;
    private final Label objectLabel;
    private final Label timeLabel;

    public Hud(SpriteBatch spriteBatch) {
        viewport = new ScreenViewport(new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.setDebug(true);
        String value = "";

        BitmapFont bitmapFont = new BitmapFont();

        livesLabel = new Label(value, new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(bitmapFont, Color.BLACK));
        table.add(livesLabel).expandX().padTop(10);
        timeLabel = new Label(value, new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(bitmapFont, Color.BLACK));
        table.add(timeLabel).expandX().padTop(10);
        objectLabel = new Label(value, new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(bitmapFont, Color.BLACK));
        table.add(objectLabel).expandX().padTop(10);
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
