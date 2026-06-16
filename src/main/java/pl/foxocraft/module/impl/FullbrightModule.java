package pl.foxocraft.module.impl;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import pl.foxocraft.module.Module;
import pl.foxocraft.util.Category;

public class FullbrightModule extends Module {

    private float originalGamma;

    public FullbrightModule() {
        super("Fullbright", "Maximum brightness", Category.RENDER, Keyboard.KEY_F, true);
    }

    @Override
    public void onEnable() {
        Minecraft mc = Minecraft.getMinecraft();
        originalGamma = mc.gameSettings.gammaSetting;
        mc.gameSettings.gammaSetting = 100.0f;
    }

    @Override
    public void onDisable() {
        Minecraft mc = Minecraft.getMinecraft();
        mc.gameSettings.gammaSetting = originalGamma;
    }

    @Override
    public void onUpdate() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.gameSettings != null) {
            mc.gameSettings.gammaSetting = 100.0f;
        }
    }
}