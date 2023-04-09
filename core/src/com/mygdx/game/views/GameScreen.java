package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.LearningGame;
import com.mygdx.game.hud.Hud;
import com.mygdx.game.model.B2dModel;
import com.mygdx.game.util.KeyboardController;

public class GameScreen implements Screen {
    private final String TAG = "GameScreen";
    private final LearningGame main;
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final Texture img;
    private B2dModel model;
    private final Box2DDebugRenderer renderer;
    private final Box2DDebugRenderer debugRenderer;
    private final BitmapFont font;
    private final KeyboardController controller;
    private Viewport viewport;
    private final World world;

    //    Public
    public Hud hud;


    public GameScreen(LearningGame main) {
        this.main = main;
        controller = new KeyboardController();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        viewport = new ExtendViewport(camera.viewportWidth, camera.viewportHeight, camera);
        batch = new SpriteBatch();

        hud = new Hud(batch);

        img = main.b2dAssetManager.manager.get(main.b2dAssetManager.libgdxPlaceholder);
        font = main.b2dAssetManager.manager.get(main.b2dAssetManager.font);
//        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(0.0f, 1.0f, 0.0f, 1.0f);
        font.getData().setScale(1f, 1f);

        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
        renderer = new Box2DDebugRenderer(false, false, false, false, false, false);

        world = new World(new Vector2(0, 0f), true);
        model = new B2dModel(this, camera, controller, world);

    }

    public void gameStart() {

    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "Screen shown");
        Gdx.app.log(TAG, "Screen is active" + this.toString());
        Gdx.input.setInputProcessor(controller);
        Gdx.app.log(TAG, Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());
        gameStart();

    }

    @Override
    public void render(float delta) {
        model.logicStep(delta);
        //Clear the screen (1)
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewport.apply();
        //Set ProjectionMatrix of SpriteBatch (2)
        batch.setProjectionMatrix(camera.combined);

//        Camera test control
        if (controller.left) {
            Gdx.app.log("Camera", "Left");
            camera.position.set(camera.position.x - 1f, camera.position.y, 0);
        } else if (controller.right) {
            Gdx.app.log("Camera", "Right");
            camera.position.set(camera.position.x + 1f, camera.position.y, 0);
        } else if (controller.up) {
            Gdx.app.log("Camera", "Up");
            camera.position.set(camera.position.x, camera.position.y + 1f, 0);
        } else if (controller.down) {
            Gdx.app.log("Camera", "Down");
            camera.position.set(camera.position.x, camera.position.y - 1f, 0);
        }
        camera.update();
        drawBatch();
        debugRenderer.render(model.world, camera.combined);
        hud.stage.draw();
        hud.updateHud(String.valueOf(model.player.lives));
        if (model.player.lives <= 0) {
            Gdx.app.log(TAG, "restarted");
            model.player.lives += 1;
            main.restartGame();
        }
//        Gdx.app.log(TAG,"Screen is active"+this.toString());
    }

    private void drawBatch() {
        batch.begin();

        int width = 100;
        int height = 100;
        batch.draw(img, model.player.body.getPosition().x - height / 2, model.player.body.getPosition().y - width / 2, width, height);

        font.draw(batch, "ää'õüfüаамьа", model.testWord.body.getPosition().x - 25, model.testWord.body.getPosition().y + 12.5f);
//        font.draw(batch, "ädkjflkjdlk", model.testWord2.body.getPosition().x - 25, model.testWord2.body.getPosition().y + 12.5f);

        batch.end();
        batch.setProjectionMatrix(hud.stage.getCamera().combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        hud.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        Gdx.app.log(TAG, this.toString() + " disposed");
        batch.dispose();
        font.dispose();
        hud.dispose();
//        img.dispose();
        debugRenderer.dispose();
        renderer.dispose();
//        model.dispose()
        model = null;
        Gdx.input.setInputProcessor(null);
    }

    public World getWorld() {
        return model.world;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

}
