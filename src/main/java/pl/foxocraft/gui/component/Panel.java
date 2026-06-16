package pl.foxocraft.gui.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class Panel extends Gui {

    private String title;
    private int x, y;
    private int width, height;
    private List<Component> components;
    private boolean dragging;
    private int headerHeight = 20;

    public Panel(String title, int x, int y, int width, int height) {
        this.title = title;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.components = new ArrayList<>();
        this.dragging = false;
    }

    public void draw(int mouseX, int mouseY) {
        // Draw panel background
        drawRect(x, y, x + width, y + height, 0xCC1a1a1a);

        // Draw header
        drawRect(x, y, x + width, y + headerHeight, 0xFF2d2d2d);

        // Draw title
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(title, x + 5, y + 6, 0xFFFFFFFF);

        // Draw components
        for (Component component : components) {
            component.draw(mouseX, mouseY);
        }
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public boolean isMouseOverHeader(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + headerHeight;
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        for (Component component : components) {
            if (component.isMouseOver(mouseX, mouseY)) {
                component.mouseClicked(mouseX, mouseY, button);
            }
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        for (Component component : components) {
            component.mouseReleased(mouseX, mouseY, state);
        }
    }

    public void keyTyped(char typedChar, int keyCode) {
        for (Component component : components) {
            component.keyTyped(typedChar, keyCode);
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}