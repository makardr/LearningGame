package com.mygdx.game.util;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class DigitFilter implements TextField.TextFieldListener {
    @Override
    public void keyTyped(TextField textField, char c) {
        if (!Character.isDigit(c)) {
            textField.setText(textField.getText().replaceAll("\\D+", ""));
        }
    }
}
