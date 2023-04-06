package com.mygdx.game.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class B2dAssetManager {
    public final AssetManager manager = new AssetManager();

    // Textures
    public final String libgdxPlaceholder = "badlogic.jpg";
    private final String loadingCircle = "images/loading.atlas";
    // Sounds

    // Music

    // Skin
    public final String skin = "skin/uiskin.json";


    public void queueAddImages() {
        manager.load(libgdxPlaceholder, Texture.class);
    }

    public void queueAddLoadingImages() {
        manager.load(loadingCircle, TextureAtlas.class);
    }

    public void queueAddSounds() {

    }

    public void queueAddMusic() {

    }

    public void queueAddSkin() {
        SkinLoader.SkinParameter params = new SkinLoader.SkinParameter("skin/uiskin.atlas");
        manager.load(skin, Skin.class, params);
    }

    public void queueAddFonts() {

    }

}
