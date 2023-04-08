package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.actors.B2dBodyEntity;
import com.mygdx.game.actors.Player;
import com.mygdx.game.actors.Word;
import com.mygdx.game.util.B2dBodyFactory;
import com.mygdx.game.util.B2dContactListener;
import com.mygdx.game.util.KeyboardController;
import com.mygdx.game.views.GameScreen;


public class B2dModel {
    private final OrthographicCamera cam;



    private KeyboardController controller;
    private GameScreen screen;
    private Array<Player> players;
    private SpriteBatch batch;


    //    Public bodies to draw in GameScreen
    public final World world;
    public Word testWord;
    public Player player;

    public B2dModel(GameScreen screen, OrthographicCamera cam, KeyboardController controller) {
        this.screen = screen;
        this.cam = cam;
        this.controller = controller;
        world = new World(new Vector2(0, 0f), true);
        world.setContactListener(new B2dContactListener(this));
        batch=screen.getBatch();
        players=new Array<Player>();
        createBodies();
    }

    public void logicStep(final float delta) {
        if (controller.isMouse1Down && pointIntersectsBody(player.body, controller.mouseLocation)) {
            player.destroy();
        }
        world.step(delta, 3, 3);
        testWord.update(delta);
        for (B2dBodyEntity entity : players) {
            entity.update(delta);
        }

    }


    public boolean pointIntersectsBody(Body body, Vector2 mouseLocation) {
        try {
            Vector3 mousePos = new Vector3(mouseLocation, 0); //convert mouseLocation to 3D position
            cam.unproject(mousePos); // convert from screen position to world position
            if (body.getFixtureList().first().testPoint(mousePos.x, mousePos.y)) {
                return true;
            }
            return false;
        } catch (IllegalStateException e){
            return false;
        }

    }

    public void createBodies() {
        B2dBodyFactory bodyFactory = B2dBodyFactory.getInstance(world);
        player=new Player(screen,world,bodyFactory.makeCirclePolyBody(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2,100,B2dBodyFactory.STEEL, BodyDef.BodyType.StaticBody,false));
        bodyFactory.makeAllFixturesSensors(player.body);
        players.add(player);
        testWord=new Word(screen,world,bodyFactory.makeCirclePolyBody(Gdx.graphics.getWidth()/2-150, Gdx.graphics.getHeight()/2,100,B2dBodyFactory.STEEL,false,BodyDef.BodyType.DynamicBody));
//        bodyFactory.makeCirclePolyBody(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2+100,100,B2dBodyFactory.STEEL,false, BodyDef.BodyType.DynamicBody);

    }
}
