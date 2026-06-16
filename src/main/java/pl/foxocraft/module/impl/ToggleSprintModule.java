package pl.foxocraft.module.impl;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import pl.foxocraft.module.Module;
import pl.foxocraft.util.Category;

public class ToggleSprintModule extends Module {

    private boolean sprinting = false;

    public ToggleSprintModule() {
        super("ToggleSprint", "Toggle sprint on/off", Category.MOVEMENT, Keyboard.KEY_V, true);
    }

    @Override
    public void onEnable() {
        sprinting = false;
    }

    @Override
    public void onDisable() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer != null) {
            mc.thePlayer.setSprinting(false);
        }
        sprinting = false;
    }

    @Override
    public void onUpdate() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null) return;

        if (Keyboard.isKeyDown(Keyboard.KEY_V)) {
            sprinting = !sprinting;
        }

        if (sprinting && mc.thePlayer.onGround) {
            mc.thePlayer.setSprinting(true);
        }
    }

    public boolean isSprinting() {
        return sprinting;
    }
}