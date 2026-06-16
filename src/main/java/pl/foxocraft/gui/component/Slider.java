package pl.foxocraft.gui.component;

import net.minecraft.client.Minecraft;

public class Slider extends Component {

    private float value;
    private float minValue;
    private float maxValue;
    private String label;
    private boolean dragging;

    public Slider(int x, int y, int width, int height, String label, float minValue, float maxValue, float defaultValue) {
        super(x, y, width, height);
        this.label = label;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = defaultValue;
        this.dragging = false;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        // Draw background
        drawRect(x, y, x + width, y + height, 0xFF3d3d3d);

        // Draw slider progress
        float progress = (value - minValue) / (maxValue - minValue);
        int filledWidth = (int) (width * progress);
        drawRect(x, y, x + filledWidth, y + height, 0xFFFF8C00);

        // Draw label and value
        String text = label + ": " + String.format("%.1f", value);
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(text, x + 5, y + height / 2 - 4, 0xFFFFFFFF);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isMouseOver(mouseX, mouseY) && button == 0) {
            dragging = true;
            updateValue(mouseX);
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        dragging = false;
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
    }

    private void updateValue(int mouseX) {
        float percentage = (float) (mouseX - x) / width;
        percentage = Math.max(0, Math.min(1, percentage));
        this.value = minValue + (maxValue - minValue) * percentage;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = Math.max(minValue, Math.min(maxValue, value));
    }

    private void drawRect(int x1, int y1, int x2, int y2, int color) {
        net.minecraft.client.gui.Gui.drawRect(x1, y1, x2, y2, color);
    }
}