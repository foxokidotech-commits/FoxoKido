package pl.foxocraft.module.impl;

import org.lwjgl.input.Keyboard;
import pl.foxocraft.module.Module;
import pl.foxocraft.util.Category;

public class KeystrokesModule extends Module {

    private boolean[] keyStates;

    public KeystrokesModule() {
        super("Keystrokes", "Display key presses", Category.RENDER, -1, false);
        this.keyStates = new boolean[Keyboard.KEYBOARD_SIZE];
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onUpdate() {
        for (int i = 0; i < Keyboard.KEYBOARD_SIZE; i++) {
            keyStates[i] = Keyboard.isKeyDown(i);
        }
    }

    public boolean isKeyPressed(int keyCode) {
        return keyCode < keyStates.length && keyStates[keyCode];
    }

    public boolean isMovingForward() {
        return isKeyPressed(Keyboard.KEY_W);
    }

    public boolean isMovingBackward() {
        return isKeyPressed(Keyboard.KEY_S);
    }

    public boolean isMovingLeft() {
        return isKeyPressed(Keyboard.KEY_A);
    }

    public boolean isMovingRight() {
        return isKeyPressed(Keyboard.KEY_D);
    }

    public boolean isJumping() {
        return isKeyPressed(Keyboard.KEY_SPACE);
    }
}