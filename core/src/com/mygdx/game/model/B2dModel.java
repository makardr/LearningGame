package com.mygdx.game.model;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.util.B2dBodyFactory;
import com.mygdx.game.util.B2dContactListener;
import com.mygdx.game.util.KeyboardController;

public class B2dModel {
    private final OrthographicCamera cam;
    public final World world;


    //    Public bodies to draw in GameScreen
    public Body player;
    private KeyboardController controller;

    public B2dModel(OrthographicCamera cam, KeyboardController controller) {
        this.cam = cam;
        this.controller = controller;
        world = new World(new Vector2(0, -10f), true);
        world.setContactListener(new B2dContactListener(this));
        createBodies();
    }

    public void logicStep(float delta) {
        if (controller.isMouse1Down && pointIntersectsBody(player, controller.mouseLocation)) {
            System.out.println("Player was clicked");
        }
        world.step(delta, 3, 3);
    }


    public boolean pointIntersectsBody(Body body, Vector2 mouseLocation) {
        Vector3 mousePos = new Vector3(mouseLocation, 0); //convert mouseLocation to 3D position
        cam.unproject(mousePos); // convert from screen position to world position
        if (body.getFixtureList().first().testPoint(mousePos.x, mousePos.y)) {
            return true;
        }
        return false;
    }

    public void createBodies() {
        B2dBodyFactory bodyFactory = B2dBodyFactory.getInstance(world);

        player = bodyFactory.makeBoxPolyBody(100, 100, 100, 100, bodyFactory.STEEL, BodyDef.BodyType.StaticBody);
    }
}
