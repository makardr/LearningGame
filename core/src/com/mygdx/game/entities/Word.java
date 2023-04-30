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
import com.mygdx.game.util.MyTuple;

public class Word extends B2dBodyEntity {
    private boolean setToDestroy;
    private final String TAG = "Word";
    private Vector2 resetPosition;
    private MyTuple dataSet;
    public int id;

    public Word(World world, int positionX, int positionY, Vector2 velocity, MyTuple dataSet, int id) {
        super(world, positionX, positionY, velocity);
        this.body = defineEntity();
        this.dataSet = dataSet;
        this.id = id;
        setToDestroy = false;
        resetPosition = new Vector2(body.getPosition().x, body.getPosition().y);
        active = false;
    }

    @Override
    public void update(float dt) {
        if (!setToDestroy) {
            if (active) {
                body.setLinearVelocity(velocity);
            }
        } else {
            Gdx.app.log(TAG, "Word " + id + " set to be destroyed: " + dataSet.getFirstValue());
            body.setTransform(resetPosition, body.getAngle());
            body.setLinearVelocity(0, 0);
            setInactive();
            dataSet = new MyTuple("", "");
            setToDestroy = false;
        }

    }

    @Override
    protected Body defineEntity() {
        B2dBodyFactory bodyFactory = B2dBodyFactory.getInstance(world);
        body = bodyFactory.makeCirclePolyBody(positionX, positionY, 100, B2dBodyFactory.STEEL, true, BodyDef.BodyType.DynamicBody);
//        Not related to MyTuple dataSet
        body.setUserData(this);
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
        font.draw(batch, getWord(), body.getPosition().x - 25, body.getPosition().y + 12.5f);
    }

    @Override
    public MyTuple getData() {
        return dataSet;
    }

    public void setData(MyTuple dataSet) {
        this.dataSet = dataSet;
    }

    public String getWord() {
        return dataSet.getFirstValue();
    }

    public String getTranslation() {
        return dataSet.getSecondValue();
    }

    public void setActive(MyTuple data) {
        setData(data);
        active = true;
        Gdx.app.log(TAG, "Word " + id + " activated: " + dataSet.getFirstValue());
    }

    public void setInactive() {
        active = false;
    }
}
