package pl.foxocraft.module.impl;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import pl.foxocraft.module.Module;
import pl.foxocraft.util.Category;

public class FPSCounterModule extends Module {

    private int fps;
    private long lastTime;
    private int frameCount;

    public FPSCounterModule() {
        super("FPS Counter", "Display current FPS", Category.RENDER, -1, false);
        this.lastTime = System.currentTimeMillis();
        this.frameCount = 0;
    }

    @Override
    public void onEnable() {
        lastTime = System.currentTimeMillis();
        frameCount = 0;
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onUpdate() {
        frameCount++;
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastTime >= 1000) {
            fps = frameCount;
            frameCount = 0;
            lastTime = currentTime;
        }
    }

    public int getFPS() {
        return fps;
    }
}