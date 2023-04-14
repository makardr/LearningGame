package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entities.B2dBodyEntity;
import com.mygdx.game.entities.Player;
import com.mygdx.game.entities.Word;
import com.mygdx.game.util.B2dContactListener;
import com.mygdx.game.util.KeyboardController;
import com.mygdx.game.views.GameScreen;


public class B2dModel {
    private final String TAG="B2dModel";
    private final OrthographicCamera cam;


    private KeyboardController controller;
    private GameScreen screen;

    private SpriteBatch batch;

    //    Public bodies to draw in GameScreen
    public World world;
    public Player player;

    private Array<B2dBodyEntity> entities;


    public B2dModel(GameScreen screen, OrthographicCamera cam, KeyboardController controller, World world) {
        this.screen = screen;
        this.cam = cam;
        this.controller = controller;
        this.world = world;
        world.setContactListener(new B2dContactListener(this));
        batch = screen.getBatch();
        entities = new Array<B2dBodyEntity>();
        createBodies();
    }

    private void startGame() {

    }

    public void logicStep(float delta) {
        for (B2dBodyEntity entity : entities) {
            if (entity.body.getUserData() instanceof Word) {
                if (screen.currentChosenWordEntity.equals(screen.dummyWord)) {
                    if (controller.isMouse1Down && pointIntersectsBody(entity.body, controller.mouseLocation)) {
                        screen.setCurrentWord((Word) entity);
                        screen.showButtons(entity.getData());
                    }
                }
            }
        }
        world.step(delta, 3, 3);
        updateEntities(delta);
    }



    public void createBodies() {
        player = new Player(world, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, new Vector2(0, 0));
        entities.add(player);
        Word testWord = new Word(world, Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2, new Vector2(10, 0), "word0");
        entities.add(testWord);
        Word testWord2 = new Word(world, Gdx.graphics.getWidth() / 2 + 150, Gdx.graphics.getHeight() / 2, new Vector2(-25, 100), "word2");
        entities.add(testWord2);
        Word testWord3 = new Word(world, Gdx.graphics.getWidth() / 2 + 150, Gdx.graphics.getHeight() / 2 + 250, new Vector2(-25, 100), "word2");
        entities.add(testWord3);
        Word testWord4 = new Word(world, Gdx.graphics.getWidth() / 2 + 200, Gdx.graphics.getHeight() / 2, new Vector2(-25, 0), "тестÕÜÄ");
        entities.add(testWord4);
    }

    public void updateEntities(float delta) {
        for (B2dBodyEntity entity : entities) {
            entity.update(delta);
        }
    }

    public Array<B2dBodyEntity> getEntities() {
        return entities;
    }
    public boolean pointIntersectsBody(Body body, Vector2 mouseLocation) {
        try {
            Vector3 mousePos = new Vector3(mouseLocation, 0); //convert mouseLocation to 3D position
            cam.unproject(mousePos); // convert from screen position to world position
            if (body.getFixtureList().first().testPoint(mousePos.x, mousePos.y)) {
                return true;
            }
            return false;
        } catch (IllegalStateException e) {
            return false;
        }
    }
}
