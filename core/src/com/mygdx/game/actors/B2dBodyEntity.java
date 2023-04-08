package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.views.GameScreen;

public abstract class B2dBodyEntity extends Sprite {
    protected World world;
    protected GameScreen screen;
    public Body body;
    public Vector2 velocity;

    //    public B2dBodyEntity(GameScreen screen,Body body, float x, float y) {
    public B2dBodyEntity(GameScreen screen, World world, Body body) {
        this.screen = screen;
//        setPosition(x, y);
        this.world = world;
        this.velocity = new Vector2(0, 0);
        this.body = body;
        defineEntity();
    }

    public abstract void update(float dt);

    protected abstract void defineEntity();

    public abstract void destroy();
}
