package pl.foxocraft.gui.screen;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class FoxoCraftSettingsGui extends GuiScreen {

    private GuiScreen parentScreen;

    public FoxoCraftSettingsGui(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(0, this.width / 2 - 50, this.height / 2, 100, 20, "Back"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height / 2 + 30, 100, 20, "Accounts"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 50, this.height / 2 + 60, 100, 20, "Profiles"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        this.fontRendererObj.drawCenteredString("FoxoCraft Settings", this.width / 2, 20, 0xFFFF8C00);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0: // Back
                mc.displayGuiScreen(parentScreen);
                break;
            case 1: // Accounts
                mc.displayGuiScreen(new AccountManagerGui(this));
                break;
            case 2: // Profiles
                mc.displayGuiScreen(new ProfileManagerGui(this));
                break;
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}