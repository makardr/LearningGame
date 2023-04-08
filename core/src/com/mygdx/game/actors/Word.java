package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.views.GameScreen;

public class Word extends B2dBodyEntity {
    private boolean setToDestroy;
    private boolean destroyed;
    private final String TAG = "Word";


    public Word(GameScreen screen, World world, Body body) {
        super(screen, world, body);
        body.setUserData("WORDBODY");
        setToDestroy = false;


    }

    @Override
    public void update(float dt) {
        if (setToDestroy) {
            body.setTransform(-100, body.getPosition().y, body.getAngle());
            Gdx.app.log(TAG, "setToDestroy");
            setToDestroy = false;
        } else {
            body.setLinearVelocity(25, 0);
        }
    }

    @Override
    protected void defineEntity() {

    }

    public void destroy() {
        setToDestroy = true;

    }
}
