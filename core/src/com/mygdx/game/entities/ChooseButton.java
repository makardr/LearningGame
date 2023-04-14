package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.views.GameScreen;

public class ChooseButton extends TextButton {
    private final String TAG = "Button";
    private String buttonText = "";
    GameScreen parent;

    public ChooseButton(String text, Skin skin, GameScreen screen) {
        super(text, skin);
        this.parent = screen;
    }

    public void buttonAction() {
        Gdx.app.log(TAG, getText() + " Action");
        if (checkIfTrue(parent.getCurrentWord())) {
            parent.wordChosenCorrectlyState = true;
        } else {
            parent.wordChosenIncorrectlyState = true;
        }

    }

    public void activate(String text) {
        this.setText(text);
        buttonText = text;
        setVisible();
    }

    public void destroy() {
        setInvisible();
        setText("");
    }

    private boolean checkIfTrue(String rightTranslation) {
        if (rightTranslation.equals(buttonText)) {
            Gdx.app.log(TAG, parent.getCurrentWord() + " true");
            return true;
        } else {
            Gdx.app.log(TAG, parent.getCurrentWord() + getText() + " false");
            return false;
        }
    }

    public void setVisible() {
        this.setVisible(true);
    }

    public void setInvisible() {
        this.setVisible(false);
    }


}
