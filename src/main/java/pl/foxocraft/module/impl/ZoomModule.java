package pl.foxocraft.module.impl;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import pl.foxocraft.module.Module;
import pl.foxocraft.util.Category;

public class ZoomModule extends Module {

    private float zoomLevel = 1.0f;
    private static final float MAX_ZOOM = 5.0f;
    private static final float ZOOM_SPEED = 0.1f;

    public ZoomModule() {
        super("Zoom", "Zoom in with mouse wheel", Category.RENDER, Keyboard.KEY_C, true);
    }

    @Override
    public void onEnable() {
        zoomLevel = 1.0f;
    }

    @Override
    public void onDisable() {
        zoomLevel = 1.0f;
        Minecraft.getMinecraft().gameSettings.fovSetting = 70.0f;
    }

    @Override
    public void onUpdate() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.gameSettings != null) {
            mc.gameSettings.fovSetting = 70.0f / zoomLevel;
        }
    }

    public void setZoomLevel(float level) {
        this.zoomLevel = Math.min(level, MAX_ZOOM);
    }

    public float getZoomLevel() {
        return zoomLevel;
    }
}