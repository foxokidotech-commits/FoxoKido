package pl.foxocraft.gui.screen;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import pl.foxocraft.FoxoCraft;
import pl.foxocraft.profile.ProfileManager;

import java.io.IOException;

public class ProfileManagerGui extends GuiScreen {

    private GuiScreen parentScreen;
    private GuiTextField profileNameField;

    public ProfileManagerGui(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    @Override
    public void initGui() {
        profileNameField = new GuiTextField(1, this.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
        profileNameField.setMaxStringLength(32);
        profileNameField.setFocused(true);

        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, 100, 200, 20, "Create Profile"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, 130, 200, 20, "Back"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        this.fontRendererObj.drawCenteredString("Profile Manager", this.width / 2, 20, 0xFFFF8C00);
        this.fontRendererObj.drawString("Profile Name:", this.width / 2 - 100, 45, 0xFFFFFFFF);

        profileNameField.drawTextBox();

        // Display profiles
        int y = 180;
        this.fontRendererObj.drawString("Profiles:", this.width / 2 - 100, y, 0xFFFF8C00);
        for (ProfileManager.GameProfile profile : FoxoCraft.profileManager.getProfiles()) {
            y += 15;
            this.fontRendererObj.drawString(profile.getName(), this.width / 2 - 100, y, 0xFFFFFFFF);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        profileNameField.textboxKeyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        profileNameField.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0: // Create Profile
                if (!profileNameField.getText().isEmpty()) {
                    FoxoCraft.profileManager.createProfile(profileNameField.getText());
                    profileNameField.setText("");
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