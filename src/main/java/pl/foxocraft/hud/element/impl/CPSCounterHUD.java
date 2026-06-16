package pl.foxocraft.hud.element.impl;

import pl.foxocraft.FoxoCraft;
import pl.foxocraft.hud.element.HUDElement;
import pl.foxocraft.module.impl.CPSCounterModule;

public class CPSCounterHUD extends HUDElement {

    public CPSCounterHUD(int x, int y) {
        super("CPS Counter", x, y, 80, 12);
    }

    @Override
    public void render() {
        CPSCounterModule cpsModule = (CPSCounterModule) FoxoCraft.moduleManager.getModule("CPS Counter");
        if (cpsModule != null && cpsModule.isEnabled()) {
            int leftCPS = cpsModule.getLeftCPS();
            int rightCPS = cpsModule.getRightCPS();
            drawString("L: " + leftCPS + " R: " + rightCPS, x, y, 0xFFFFFFFF);
        }
    }
}