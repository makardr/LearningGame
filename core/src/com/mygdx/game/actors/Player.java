package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.views.GameScreen;

public class Player extends B2dBodyEntity {
    public int lives;
    private boolean setToDestroy;
    private boolean destroyed;

    public void damage() {
        lives -= 1;
    }

    //    public Player(GameScreen screen, float x, float y,Body body) {
//        super(screen, x, y);
//        this.body = body;
//        lives = 3;
//    }
    public Player(GameScreen screen,World world, Body body) {
        super(screen,world, body);
        body.setUserData("PLAYERBODY");
        lives = 3;
    }

    @Override
    public void update(float dt) {
        if (setToDestroy && !destroyed) {
            destroyed = true;
            world.destroyBody(body);
//            setRegion(new TextureRegion(screen.getAtlas().findRegion("goomba"), 32, 0, 16, 16));
        } else if (!destroyed) {
//            b2body.setLinearVelocity(velocity);
////        -getWidth()/2 to offset sprite by half the width of the sprite, same with height
//            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        }
        if(lives<=0){
            Gdx.app.log("Player", "Lives ran out "+lives);
        }
    }

    @Override
    protected void defineEntity() {

    }

//    @Override
//    protected void defineEntity() {
//
//    }

    public void destroy() {
        Gdx.app.log("Player", "Player destroyed"+setToDestroy);
//        setToDestroy = true;
        body.setTransform(new Vector2(-100,100), body.getAngle());
//        damage();
    }
}
