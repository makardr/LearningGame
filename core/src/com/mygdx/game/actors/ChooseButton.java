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

public class ChooseButton extends B2dBodyEntity{
    public ChooseButton(World world, int positionX, int positionY, Vector2 velocity) {
        super(world, positionX, positionY, velocity);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    protected Body defineEntity() {
        B2dBodyFactory bodyFactory = B2dBodyFactory.getInstance(world);
        body=bodyFactory.makeCirclePolyBody(positionX, positionY,100, B2dBodyFactory.STEEL, false, BodyDef.BodyType.DynamicBody);
        return body;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void draw(Batch batch, Texture texture) {

    }

    @Override
    public void draw(Batch batch, BitmapFont font) {

    }
}
