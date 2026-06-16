package pl.foxocraft.hud.element.impl;

import pl.foxocraft.FoxoCraft;
import pl.foxocraft.hud.element.HUDElement;
import pl.foxocraft.module.impl.CoordinatesModule;

public class CoordinatesHUD extends HUDElement {

    public CoordinatesHUD(int x, int y) {
        super("Coordinates", x, y, 90, 35);
    }

    @Override
    public void render() {
        CoordinatesModule coordModule = (CoordinatesModule) FoxoCraft.moduleManager.getModule("Coordinates");
        if (coordModule != null && coordModule.isEnabled()) {
            double x = coordModule.getExactX();
            double y = coordModule.getExactY();
            double z = coordModule.getExactZ();

            drawString("XYZ Coordinates", this.x, this.y, 0xFFFF8C00);
            drawString(String.format("X: %.1f", x), this.x, this.y + 12, 0xFFFFFFFF);
            drawString(String.format("Y: %.1f", y), this.x, this.y + 22, 0xFFFFFFFF);
            drawString(String.format("Z: %.1f", z), this.x, this.y + 32, 0xFFFFFFFF);
        }
    }
}