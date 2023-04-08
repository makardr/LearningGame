package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.model.B2dModel;

public class B2dContactListener implements ContactListener {
    private final String TAG = "B2dContactListener";
    private final B2dModel parent;

    public B2dContactListener(B2dModel parent) {
        this.parent = parent;
    }

    @Override
    public void beginContact(Contact contact) {
        Gdx.app.log(TAG, "Begin contact");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (fa.getBody().getUserData() == "PLAYERBODY") {
            Gdx.app.log("B2dContactListener", "Contact1");
            parent.testWord.destroy();
            parent.player.damage();
            return;
        } else if (fb.getBody().getUserData() == "PLAYERBODY") {
            Gdx.app.log("B2dContactListener", "Contact2");
            parent.testWord.destroy();
            parent.player.damage();
            return;
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
