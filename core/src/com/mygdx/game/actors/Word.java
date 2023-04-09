package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.util.B2dBodyFactory;

public class Word extends B2dBodyEntity {
    private boolean setToDestroy;
    private boolean destroyed;
    private final String TAG = "Word";
    private Vector2 resetPosition;
    private String currentText;


    public Word(World world, int positionX, int positionY, Vector2 velocity,String currentText) {
        super(world, positionX, positionY, velocity);
        this.body = defineEntity();
        this.currentText=currentText;
        setToDestroy = false;
        resetPosition = new Vector2(body.getPosition().x, body.getPosition().y);
    }

    @Override
    public void update(float dt) {
        if (setToDestroy) {
            body.setTransform(resetPosition, body.getAngle());
            Gdx.app.log(TAG, "setToDestroy");
            setToDestroy = false;
        } else {
            body.setLinearVelocity(velocity);
        }
    }

    @Override
    protected Body defineEntity() {
        B2dBodyFactory bodyFactory = B2dBodyFactory.getInstance(world);
        body = bodyFactory.makeCirclePolyBody(positionX, positionY, 100, B2dBodyFactory.STEEL, true, BodyDef.BodyType.DynamicBody);
//        body.setUserData("WORDBODY");
        body.setUserData(this);
        if (body.getUserData() instanceof Word){

        }
        return body;
    }

    public void destroy() {
        setToDestroy = true;

    }


    @Override
    public void draw(Batch batch, Texture texture) {

    }

    @Override
    public void draw(Batch batch, BitmapFont font) {
        font.draw(batch, getCurrentText(), body.getPosition().x - 25, body.getPosition().y + 12.5f);
    }

    public String getCurrentText() {
        return currentText;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }
}
