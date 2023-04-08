package com.mygdx.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.views.GameScreen;

public class Word extends B2dBodyEntity{
    private boolean setToDestroy;
    private boolean destroyed;



    public Word(GameScreen screen, World world, Body body) {
        super(screen,world, body);
        body.setUserData("WORDBODY");
    }
    @Override
    public void update(float dt) {

    }

    @Override
    protected void defineEntity() {

    }
    public void destroy(){

    }
}
