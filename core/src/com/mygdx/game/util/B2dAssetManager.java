package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class B2dAssetManager {
    public final AssetManager manager = new AssetManager();

    private final String TAG = "B2dAssetManager";

    // Textures
    private final String libgdxPlaceholder = "badlogic.jpg";
    private final String loadingCircle = "images/loading.atlas";
    // Sounds

    // Music

    // Skin
    private final String skin = "skin/uiskin.json";
    //    Font
    private final String font = "font/white_font.fnt";


    public void queueAddImages() {
        manager.load(libgdxPlaceholder, Texture.class);
        Gdx.app.log(TAG, "Load textures");
    }

    public void queueAddLoadingImages() {
        manager.load(loadingCircle, TextureAtlas.class);
        Gdx.app.log(TAG, "Load loading textures");
    }

    public void queueAddSounds() {
        Gdx.app.log(TAG, "Load sounds");
    }

    public void queueAddMusic() {
        Gdx.app.log(TAG, "Load music");
    }

    public void queueAddSkin() {
        SkinLoader.SkinParameter params = new SkinLoader.SkinParameter("skin/uiskin.atlas");
        manager.load(skin, Skin.class, params);
        Gdx.app.log(TAG, "Load images");
    }

    public void queueAddFonts() {
        manager.load(font, BitmapFont.class);
        Gdx.app.log(TAG, "Load font");
    }

}
