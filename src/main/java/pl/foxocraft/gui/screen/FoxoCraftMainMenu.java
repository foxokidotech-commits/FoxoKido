package pl.foxocraft.gui.screen;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import pl.foxocraft.gui.component.Button;

import java.io.IOException;

public class FoxoCraftMainMenu extends GuiScreen {

    private int width;
    private int height;

    @Override
    public void initGui() {
        width = this.mc.displayWidth;
        height = this.mc.displayHeight;

        // Create buttons
        this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 2, 200, 20, "Singleplayer"));
        this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 2 + 30, 200, 20, "Multiplayer"));
        this.buttonList.add(new GuiButton(2, width / 2 - 100, height / 2 + 60, 200, 20, "Realms"));
        this.buttonList.add(new GuiButton(3, width / 2 - 100, height / 2 + 100, 200, 20, "FoxoCraft Settings"));
        this.buttonList.add(new GuiButton(4, width / 2 - 100, height / 2 + 130, 200, 20, "Quit"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // Draw background gradient (FoxoCraft theme: Orange and White)
        drawGradientRect(0, 0, width, height, 0xFFFFFFFF, 0xFFFF8C00);

        // Draw FoxoCraft title
        this.fontRendererObj.drawCenteredString("FoxoCraft Client", width / 2, 50, 0xFFFF8C00);
        this.fontRendererObj.drawCenteredString("Minecraft PvP Client", width / 2, 65, 0xFFFFFFFF);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0: // Singleplayer
                mc.displayGuiScreen(new GuiMainMenu());
                break;
            case 1: // Multiplayer
                mc.displayGuiScreen(new GuiMainMenu());
                break;
            case 2: // Realms
                mc.displayGuiScreen(new GuiMainMenu());
                break;
            case 3: // FoxoCraft Settings
                mc.displayGuiScreen(new FoxoCraftSettingsGui(this));
                break;
            case 4: // Quit
                mc.shutdown();
                break;
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}