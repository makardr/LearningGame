package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.views.GameScreen;

public abstract class B2dBodyEntity extends Sprite {
    public Body body;
    public Vector2 velocity;

    //    public B2dBodyEntity(GameScreen screen,Body body, float x, float y) {
    public B2dBodyEntity(Body body,Vector2 velocity) {
        this.velocity = velocity;
        this.body = body;
        defineEntity();
    }

    public abstract void update(float dt);

    protected abstract void defineEntity();

    public abstract void destroy();
}
