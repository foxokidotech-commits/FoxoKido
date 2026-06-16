package pl.foxocraft.hud.element.impl;

import pl.foxocraft.FoxoCraft;
import pl.foxocraft.hud.element.HUDElement;
import pl.foxocraft.module.impl.FPSCounterModule;

public class FPSCounterHUD extends HUDElement {

    public FPSCounterHUD(int x, int y) {
        super("FPS Counter", x, y, 60, 12);
    }

    @Override
    public void render() {
        FPSCounterModule fpsModule = (FPSCounterModule) FoxoCraft.moduleManager.getModule("FPS Counter");
        if (fpsModule != null && fpsModule.isEnabled()) {
            int fps = fpsModule.getFPS();
            int color = fps > 60 ? 0xFF00FF00 : fps > 30 ? 0xFFFFFF00 : 0xFFFF0000;
            drawString("FPS: " + fps, x, y, color);
        }
    }
}