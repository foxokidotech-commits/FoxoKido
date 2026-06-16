package pl.foxocraft.gui.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class Button extends Component {

    private String text;
    private Runnable onClickListener;
    private boolean hovering;
    private int backgroundColor;
    private int hoverColor;

    public Button(int x, int y, int width, int height, String text) {
        super(x, y, width, height);
        this.text = text;
        this.backgroundColor = 0xFF3d3d3d;
        this.hoverColor = 0xFF5d5d5d;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        hovering = isMouseOver(mouseX, mouseY);
        int color = hovering ? hoverColor : backgroundColor;

        drawRect(x, y, x + width, y + height, color);
        drawBorder();

        Minecraft.getMinecraft().fontRendererObj.drawCenteredString(text, x + width / 2, y + height / 2 - 4, 0xFFFFFFFF);
    }

    private void drawBorder() {
        drawRect(x, y, x + width, y + 1, 0xFF555555);
        drawRect(x, y + height - 1, x + width, y + height, 0xFF555555);
        drawRect(x, y, x + 1, y + height, 0xFF555555);
        drawRect(x + width - 1, y, x + width, y + height, 0xFF555555);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isMouseOver(mouseX, mouseY) && button == 0 && onClickListener != null) {
            onClickListener.run();
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
    }

    public void setOnClickListener(Runnable listener) {
        this.onClickListener = listener;
    }

    public void setText(String text) {
        this.text = text;
    }

    private void drawRect(int x1, int y1, int x2, int y2, int color) {
        GL11.glColor4f(1, 1, 1, 1);
        net.minecraft.client.gui.Gui.drawRect(x1, y1, x2, y2, color);
    }
}