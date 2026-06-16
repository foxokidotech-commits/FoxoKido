package pl.foxocraft.gui.screen;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import pl.foxocraft.FoxoCraft;
import pl.foxocraft.screenshot.ScreenshotManager;

import java.io.IOException;

public class ScreenshotManagerGui extends GuiScreen {

    private GuiScreen parentScreen;
    private int scrollY = 0;

    public ScreenshotManagerGui(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, 30, 200, 20, "Take Screenshot"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height - 30, 200, 20, "Back"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        this.fontRendererObj.drawCenteredString("Screenshot Manager", this.width / 2, 10, 0xFFFF8C00);

        // Display screenshots
        int y = 70;
        for (ScreenshotManager.Screenshot screenshot : FoxoCraft.screenshotManager.getScreenshots()) {
            if (y > this.height - 50) break;
            this.fontRendererObj.drawString(screenshot.getFilename(), 20, y, 0xFFFFFFFF);
            this.fontRendererObj.drawString(screenshot.getFormattedDate(), 20, y + 10, 0xFF888888);
            y += 30;
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0: // Take Screenshot
                if (FoxoCraft.screenshotManager != null) {
                    FoxoCraft.screenshotManager.takeScreenshot();
                }
                break;
            case 1: // Back
                mc.displayGuiScreen(parentScreen);
                break;
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}