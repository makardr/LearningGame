package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.LearningGame;
import com.mygdx.game.entities.B2dBodyEntity;
import com.mygdx.game.entities.ChooseButton;
import com.mygdx.game.entities.Player;
import com.mygdx.game.entities.Word;
import com.mygdx.game.hud.Hud;
import com.mygdx.game.model.B2dModel;
import com.mygdx.game.util.KeyboardController;

import java.util.Arrays;
import java.util.Random;

public class GameScreen implements Screen {
    private final String TAG = "GameScreen";
    private final LearningGame main;
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final Texture img;
    public final Word dummyWord;
    private B2dModel model;
    private final Box2DDebugRenderer renderer;
    private final Box2DDebugRenderer debugRenderer;
    private final BitmapFont font;
    private final KeyboardController controller;
    private Viewport viewport;
    private final World world;
    private Array<B2dBodyEntity> entities;
    private Array<ChooseButton> buttons;
    //    Public
    public Hud hud;
    private Stage stage;
    private Skin skin;
    public Word currentChosenWordEntity;

    public boolean wordChosenCorrectlyState;
    public boolean wordChosenIncorrectlyState;
    String[] currentGameArray = {"apple", "banana", "cherry", "date", "elderberry"};

    private long startTime = System.currentTimeMillis();
    private float currentTime;
    private float lastTime;
    private String remainingLives;

    public GameScreen(LearningGame main) {
        this.main = main;
        controller = new KeyboardController();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        viewport = new ExtendViewport(camera.viewportWidth, camera.viewportHeight, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();


//        Load assets
        img = main.b2dAssetManager.manager.get(main.b2dAssetManager.libgdxPlaceholder);
        font = main.b2dAssetManager.manager.get(main.b2dAssetManager.font);
        skin = main.b2dAssetManager.manager.get("skin/uiskin.json");

//        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(0.0f, 1.0f, 0.0f, 1.0f);
        font.getData().setScale(1f, 1f);

        hud = new Hud(batch);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
        renderer = new Box2DDebugRenderer(false, false, false, false, false, false);

        world = new World(new Vector2(0, 0f), true);
        model = new B2dModel(this, camera, controller, world);
        entities = model.getEntities();
        dummyWord = new Word(world, -100, -100, new Vector2(0, 0), "");
    }



    @Override
    public void show() {
        Gdx.app.log(TAG, "Screen shown");
        Gdx.app.log(TAG, "Screen is active" + this.toString());
        InputMultiplexer multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
        multiplexer.addProcessor(controller);
        multiplexer.addProcessor(stage);

        Gdx.app.log(TAG, Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());

        Table table = makeTable();

        stage.addActor(table);

        startNewGame();
    }

//    startNewGame When the screen is shown
    public void startNewGame(){

        destroyButtons();
        setCurrentWord(dummyWord);
        currentTime=0;
//        populateGameDictionary();

    }
//    restartGame is to reset data and change screen to main
    public void restartGame() {
        lastTime=currentTime;
        remainingLives=model.player.getLives();
        for (B2dBodyEntity entity : entities) {
//            Player resets lives in destroy method
            entity.destroy();
        }

        startNewGame();
        main.restartGame();
    }
    private void populateGameDictionary(String[] newArray) {
        currentGameArray=newArray;
    }

    public void setCurrentWord(Word word) {
        this.currentChosenWordEntity = word;
    }

    public String getCurrentWord() {
        return currentChosenWordEntity.getData();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateController();
        update(delta);
        drawBatch();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    private void update(float delta) {
        model.logicStep(delta);
        viewport.apply();
        camera.update();
        hud.updateHud(model.player.getLives(),getCurrentTime(),currentChosenWordEntity.getData());
        currentTime+=delta;
        gameLogic();
        if (model.player.lives <= 0) {
            restartGame();
        }

    }

    private void gameLogic() {
        if (wordChosenCorrectlyState == true) {
            Gdx.app.log(TAG, "wordChosenCorrectlyState true");
            wordChosenCorrectlyState = false;
            currentChosenWordEntity.destroy();
            currentChosenWordEntity=dummyWord;
            destroyButtons();
        }
        if (wordChosenIncorrectlyState == true) {
            Gdx.app.log(TAG, "wordChosenIncorrectlyState true");
            wordChosenIncorrectlyState = false;
            currentChosenWordEntity.destroy();
            model.player.damage();
            currentChosenWordEntity=dummyWord;
            destroyButtons();
        }
    }


    private void destroyButtons() {
        for (ChooseButton buttonObj : buttons) {
            buttonObj.destroy();
        }
    }

    private void drawBatch() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (B2dBodyEntity entity : entities) {
            if (entity.body.getUserData() instanceof Player) {
                entity.draw(batch, img);
            } else if (entity.body.getUserData() instanceof Word) {
                entity.draw(batch, font);
            }
        }
        batch.end();
        hud.stage.draw();
        debugRenderer.render(model.world, camera.combined);
    }

    private void updateController() {
        //        Camera test control
        if (controller.left) {
            camera.position.set(camera.position.x - 1f, camera.position.y, 0);
        } else if (controller.right) {
            camera.position.set(camera.position.x + 1f, camera.position.y, 0);
        } else if (controller.up) {
            camera.position.set(camera.position.x, camera.position.y + 1f, 0);
        } else if (controller.down) {
            camera.position.set(camera.position.x, camera.position.y - 1f, 0);
        }
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
        img.dispose();
        debugRenderer.dispose();
        renderer.dispose();
        model = null;
        Gdx.input.setInputProcessor(null);
        stage.dispose();
        skin.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public World getWorld() {
        return world;
    }

    private Table makeTable() {
        Table table = new Table();
        table.setBounds(viewport.getScreenX(), viewport.getScreenY(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 3);
//        table.setFillParent(true);
        table.setDebug(true);

//        Create buttons
        buttons = new Array<ChooseButton>();

        for (int i = 0; i < 3; i++) {
            final ChooseButton buttonObj = new ChooseButton("word" + i, skin, this);
            buttonObj.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    buttonObj.buttonAction();
                }
            });
            buttons.add(buttonObj);
        }

        for (ChooseButton buttonObj : buttons) {
            table.add(buttonObj).fillX().uniformX().uniformY().width(Gdx.graphics.getWidth() * 0.8f).height(Gdx.graphics.getHeight() / 8);
            buttonObj.activate("тест");
            table.row();
        }
        return table;
    }

    public void showButtons(String wordData) {
        String[] threeArray = createArray(wordData, currentGameArray);
        int i=0;
        for (ChooseButton buttonObj : buttons) {
            buttonObj.activate(threeArray[i]);
            i+=1;
        }
    }

    public static String[] createArray(String passedString, String[] inputArray) {
        String[] newArray = new String[3];
        newArray[0] = passedString;
        Random rand = new Random();
        int randomIndex1 = rand.nextInt(inputArray.length);
        int randomIndex2 = rand.nextInt(inputArray.length);
        while (randomIndex2 == randomIndex1) {
            randomIndex2 = rand.nextInt(inputArray.length);
        }
        newArray[1] = inputArray[randomIndex1];
        newArray[2] = inputArray[randomIndex2];
        return shuffleArray(newArray);
    }
    public static String[] shuffleArray(String[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            String temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }
    public String getCurrentTime() {
        float minutes = (float)Math.floor(currentTime / 60.0f);
        float seconds = currentTime - minutes * 60.0f;
        return String.format("%.0fm%.0fs", minutes, seconds);
    }
    public String getCurrentTimeDt() {
        float minutes = (float)Math.floor(currentTime / 60.0f);
        float seconds = currentTime - minutes * 60.0f;
        return String.format("%.0fm%.0fs", minutes, seconds);
    }
    public String getLastTime() {
        float minutes = (float)Math.floor(lastTime / 60.0f);
        float seconds = lastTime - minutes * 60.0f;
        return String.format("%.0fm%.0fs", minutes, seconds);
    }
    public float getLastTimeDt() {
        return lastTime;
    }
    public String getCurrentLives(){
        return model.player.getLives();
    }

//    public void setCurrentTime(String currentTime) {
//        this.currentTime = currentTime;
//    }
}
