package pl.foxocraft.hud.element.impl;

import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import pl.foxocraft.FoxoCraft;
import pl.foxocraft.hud.element.HUDElement;
import pl.foxocraft.module.impl.KeystrokesModule;

public class KeystrokesHUD extends HUDElement {

    private static final int KEY_SIZE = 16;
    private static final int KEY_SPACING = 2;

    public KeystrokesHUD(int x, int y) {
        super("Keystrokes", x, y, 70, 70);
    }

    @Override
    public void render() {
        KeystrokesModule keystrokesModule = (KeystrokesModule) FoxoCraft.moduleManager.getModule("Keystrokes");
        if (keystrokesModule != null && keystrokesModule.isEnabled()) {
            renderKeystrokes(keystrokesModule);
        }
    }

    private void renderKeystrokes(KeystrokesModule module) {
        // W key
        drawKey(x + KEY_SIZE + KEY_SPACING, y, "W", module.isMovingForward());

        // A, S, D keys
        drawKey(x, y + KEY_SIZE + KEY_SPACING, "A", module.isMovingLeft());
        drawKey(x + KEY_SIZE + KEY_SPACING, y + KEY_SIZE + KEY_SPACING, "S", module.isMovingBackward());
        drawKey(x + 2 * (KEY_SIZE + KEY_SPACING), y + KEY_SIZE + KEY_SPACING, "D", module.isMovingRight());

        // Space key
        drawKey(x, y + 2 * (KEY_SIZE + KEY_SPACING), "SPACE", module.isJumping());
    }

    private void drawKey(int x, int y, String label, boolean pressed) {
        int backgroundColor = pressed ? 0xFFFF8C00 : 0xFF3d3d3d;
        drawRect(x, y, x + KEY_SIZE, y + KEY_SIZE, backgroundColor);
        drawRect(x, y, x + KEY_SIZE, y + 1, 0xFF555555);
        drawRect(x, y + KEY_SIZE - 1, x + KEY_SIZE, y + KEY_SIZE, 0xFF555555);
        drawRect(x, y, x + 1, y + KEY_SIZE, 0xFF555555);
        drawRect(x + KEY_SIZE - 1, y, x + KEY_SIZE, y + KEY_SIZE, 0xFF555555);

        drawString(label, x + 2, y + 5, 0xFFFFFFFF);
    }
}