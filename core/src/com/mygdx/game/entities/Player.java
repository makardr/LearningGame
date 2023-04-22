package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.util.B2dBodyFactory;

public class Player extends B2dBodyEntity {
    private final Vector2 resetPosition;
    public int lives;
    private int defaultLives=10;
    private boolean setToDestroy;
    private final String TAG = "Player";

    private boolean playerWasDamaged = false;



    public Player(World world, int positionX, int positionY, Vector2 velocity) {
        super(world, positionX, positionY, velocity);
        this.body = defineEntity();
        resetPosition = new Vector2(body.getPosition().x, body.getPosition().y);
        lives = defaultLives;

    }

    @Override
    public void update(float dt) {
        if (setToDestroy) {
            Gdx.app.log(TAG, "setToDestroy");
            body.setTransform(resetPosition, body.getAngle());
            lives=defaultLives;
            setToDestroy = false;
        } else {
            body.setLinearVelocity(velocity);
        }
    }

    public void damage() {
        lives -= 1;
        playerWasDamaged=true;
    }


    public void destroy() {
        setToDestroy = true;
    }

    @Override
    protected Body defineEntity() {
        B2dBodyFactory bodyFactory = B2dBodyFactory.getInstance(world);
        body = bodyFactory.makeCirclePolyBody(positionX,positionY,100, B2dBodyFactory.STEEL, BodyDef.BodyType.StaticBody, true);
        body.setUserData(this);
        bodyFactory.makeAllFixturesSensors(body);
        return body;
    }


    public void draw(Batch batch, Texture texture) {
        batch.draw(texture, body.getPosition().x - 100 / 2, body.getPosition().y - 100 / 2, 100, 100);
    }

    @Override
    public void draw(Batch batch, BitmapFont font) {

    }

    @Override
    public String getData() {
        return null;
    }

    @Override
    public void setActive() {

    }

    @Override
    public void setInactive() {

    }
    public String getLives(){
        return Integer.toString(lives);
    }

    public void setDefaultLives(int defaultLives) {
        this.defaultLives = defaultLives;
    }
    public boolean wasPlayerDamaged() {
        if (playerWasDamaged){
            playerWasDamaged=false;
            return true;
        } else {
            return false;
        }

    }
}
