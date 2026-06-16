package pl.foxocraft.hud.element.impl;

import pl.foxocraft.hud.element.HUDElement;

public class FoxoCraftWatermark extends HUDElement {

    private String version = "1.0.0";

    public FoxoCraftWatermark(int x, int y) {
        super("FoxoCraft Watermark", x, y, 120, 12);
    }

    @Override
    public void render() {
        drawString("FoxoCraft Client v" + version, x, y, 0xFFFF8C00);
    }
}