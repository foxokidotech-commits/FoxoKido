package pl.foxocraft.hud;

import net.minecraft.client.Minecraft;
import pl.foxocraft.hud.element.HUDElement;
import pl.foxocraft.hud.element.impl.*;

import java.util.ArrayList;
import java.util.List;

public class HUDManager {

    private List<HUDElement> hudElements;
    private HUDElement draggingElement;
    private int dragOffsetX;
    private int dragOffsetY;
    private boolean editMode;

    public HUDManager() {
        this.hudElements = new ArrayList<>();
        this.editMode = false;
    }

    public void init() {
        // Register HUD elements
        hudElements.add(new FPSCounterHUD(5, 5));
        hudElements.add(new CPSCounterHUD(5, 20));
        hudElements.add(new KeystrokesHUD(5, 40));
        hudElements.add(new CoordinatesHUD(5, 100));
        hudElements.add(new ArmorStatusHUD(Minecraft.getMinecraft().displayWidth - 100, 5));
        hudElements.add(new WaypointsHUD(Minecraft.getMinecraft().displayWidth - 200, 50));
        hudElements.add(new FoxoCraftWatermark(Minecraft.getMinecraft().displayWidth - 150, Minecraft.getMinecraft().displayHeight - 20));
    }

    public void render(float partialTicks) {
        if (!editMode) {
            for (HUDElement element : hudElements) {
                if (element.isVisible()) {
                    element.render();
                }
            }
        } else {
            for (HUDElement element : hudElements) {
                element.renderEditMode();
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (!editMode) return;

        for (HUDElement element : hudElements) {
            if (element.isMouseOver(mouseX, mouseY)) {
                if (button == 0) {
                    draggingElement = element;
                    dragOffsetX = mouseX - element.getX();
                    dragOffsetY = mouseY - element.getY();
                } else if (button == 1) {
                    element.toggleVisible();
                }
                return;
            }
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        draggingElement = null;
    }

    public void mouseMoved(int mouseX, int mouseY) {
        if (draggingElement != null) {
            draggingElement.setPosition(mouseX - dragOffsetX, mouseY - dragOffsetY);
        }
    }

    public void toggleEditMode() {
        editMode = !editMode;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public HUDElement getElement(String name) {
        for (HUDElement element : hudElements) {
            if (element.getName().equalsIgnoreCase(name)) {
                return element;
            }
        }
        return null;
    }

    public List<HUDElement> getElements() {
        return new ArrayList<>(hudElements);
    }
}