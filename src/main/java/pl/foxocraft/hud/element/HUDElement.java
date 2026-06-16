package pl.foxocraft.hud.element;

import net.minecraft.client.Minecraft;

public abstract class HUDElement {

    protected int x, y;
    protected int width, height;
    protected String name;
    protected boolean visible;
    protected Minecraft mc;

    public HUDElement(String name, int x, int y, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.visible = true;
        this.mc = Minecraft.getMinecraft();
    }

    public abstract void render();

    public void renderEditMode() {
        drawBorder();
        render();
    }

    protected void drawBorder() {
        drawRect(x, y, x + width, y + height, 0x80FF8C00);
        drawRect(x, y, x + width, y + 1, 0xFFFF8C00);
        drawRect(x, y + height - 1, x + width, y + height, 0xFFFF8C00);
        drawRect(x, y, x + 1, y + height, 0xFFFF8C00);
        drawRect(x + width - 1, y, x + width, y + height, 0xFFFF8C00);
    }

    protected void drawRect(int x1, int y1, int x2, int y2, int color) {
        net.minecraft.client.gui.Gui.drawRect(x1, y1, x2, y2, color);
    }

    protected void drawString(String text, int x, int y, int color) {
        mc.fontRendererObj.drawStringWithShadow(text, x, y, color);
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public void toggleVisible() {
        this.visible = !visible;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getName() { return name; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public boolean isVisible() { return visible; }
}