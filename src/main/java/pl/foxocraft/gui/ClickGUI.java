package pl.foxocraft.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import pl.foxocraft.gui.component.Button;
import pl.foxocraft.gui.component.Panel;
import pl.foxocraft.gui.component.Slider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends GuiScreen {

    private List<Panel> panels;
    private Panel draggingPanel;
    private int dragOffsetX;
    private int dragOffsetY;
    private boolean visible;

    public ClickGUI() {
        this.panels = new ArrayList<>();
        this.visible = false;
        initPanels();
    }

    private void initPanels() {
        // Create panels for each category
        // Panel positions: Movement, Render, Combat, Utility, Player
        panels.add(new Panel("Movement", 10, 10, 200, 300));
        panels.add(new Panel("Render", 220, 10, 200, 300));
        panels.add(new Panel("Combat", 430, 10, 200, 300));
        panels.add(new Panel("Utility", 10, 320, 200, 300));
        panels.add(new Panel("Player", 220, 320, 200, 300));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (!visible) return;

        drawDefaultBackground();

        for (Panel panel : panels) {
            panel.draw(mouseX, mouseY);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (!visible) return;

        for (Panel panel : panels) {
            if (panel.isMouseOver(mouseX, mouseY)) {
                if (mouseButton == 0) {
                    draggingPanel = panel;
                    dragOffsetX = mouseX - panel.getX();
                    dragOffsetY = mouseY - panel.getY();
                }
                panel.mouseClicked(mouseX, mouseY, mouseButton);
                return;
            }
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        if (!visible) return;

        draggingPanel = null;

        for (Panel panel : panels) {
            panel.mouseReleased(mouseX, mouseY, state);
        }
    }

    @Override
    public void handleMouseInput() throws IOException {
        if (!visible) return;

        int mouseX = org.lwjgl.input.Mouse.getEventX() * width / mc.displayWidth;
        int mouseY = height - org.lwjgl.input.Mouse.getEventY() * height / mc.displayHeight - 1;

        if (draggingPanel != null) {
            draggingPanel.setX(mouseX - dragOffsetX);
            draggingPanel.setY(mouseY - dragOffsetY);
        }

        super.handleMouseInput();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (!visible) return;

        if (keyCode == Keyboard.KEY_ESCAPE) {
            toggle();
            return;
        }

        for (Panel panel : panels) {
            panel.keyTyped(typedChar, keyCode);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public void toggle() {
        this.visible = !visible;
        if (visible) {
            Minecraft.getMinecraft().displayGuiScreen(this);
        } else {
            Minecraft.getMinecraft().displayGuiScreen(null);
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Panel> getPanels() {
        return panels;
    }
}