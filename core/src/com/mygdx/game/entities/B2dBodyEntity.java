package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.util.MyTuple;

public abstract class B2dBodyEntity extends Sprite {
    public Body body;
    public Vector2 velocity;
    public int positionX;
    public int positionY;
    public World world;
    public boolean active;

    //    public B2dBodyEntity(GameScreen screen,Body body, float x, float y) {
    public B2dBodyEntity(World world, int positionX, int positionY, Vector2 velocity) {
        this.world = world;
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocity = velocity;

    }

    public abstract void update(float dt);

    protected abstract Body defineEntity();

    public abstract void destroy();

    public abstract void draw(Batch batch, Texture texture);

    public abstract void draw(Batch batch, BitmapFont font);
    public abstract MyTuple getData();

}
