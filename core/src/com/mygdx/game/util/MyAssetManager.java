package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MyAssetManager {
    public final AssetManager manager = new AssetManager();

    private final String TAG = "MyAssetManager";

    // Textures
    public final String libgdxPlaceholder = "badlogic.jpg";
    public final String player_texture = "images/player_placeholder.png";
    public final String loadingCircle = "images/loading.atlas";

    // Sounds

    // Music

    // Skin
    public final String skin = "skin/cloud_form_skin/cloud-form-ui.json";
    public final String skin_atlas = "skin/cloud_form_skin/cloud-form-ui.atlas";

    public final String old_skin = "skin/uiskin/uiskin.json";
    public final String old_skin_atlas = "skin/uiskin/uiskin.atlas";
    //    Font
    public final String font = "font/white_font.fnt";



    public void queueAddImages() {
        manager.load(libgdxPlaceholder, Texture.class);
        manager.load(player_texture, Texture.class);
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
        SkinLoader.SkinParameter params = new SkinLoader.SkinParameter(skin_atlas);
        manager.load(skin, Skin.class, params);

        Gdx.app.log(TAG, "Load skin");
    }

    public void queueAddFonts() {
        manager.load(font, BitmapFont.class);
        Gdx.app.log(TAG, "Load font");
    }

}
