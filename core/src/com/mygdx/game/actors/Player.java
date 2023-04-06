package com.mygdx.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends Body {
    /**
     * Constructs a new body with the given address
     *
     * @param world the world
     * @param addr  the address
     */
    protected Player(World world, long addr) {
        super(world, addr);
    }
}
