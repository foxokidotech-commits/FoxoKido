package pl.foxocraft.gui.screen;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import pl.foxocraft.FoxoCraft;
import pl.foxocraft.account.AccountManager;

import java.io.IOException;

public class AccountManagerGui extends GuiScreen {

    private GuiScreen parentScreen;
    private GuiTextField usernameField;
    private GuiTextField nicknameField;

    public AccountManagerGui(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    @Override
    public void initGui() {
        usernameField = new GuiTextField(1, this.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
        usernameField.setMaxStringLength(32);
        usernameField.setFocused(true);

        nicknameField = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, 100, 200, 20);
        nicknameField.setMaxStringLength(32);

        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, 140, 200, 20, "Add Account"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, 170, 200, 20, "Back"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        this.fontRendererObj.drawCenteredString("Account Manager", this.width / 2, 20, 0xFFFF8C00);
        this.fontRendererObj.drawString("Username:", this.width / 2 - 100, 45, 0xFFFFFFFF);
        this.fontRendererObj.drawString("Nickname:", this.width / 2 - 100, 85, 0xFFFFFFFF);

        usernameField.drawTextBox();
        nicknameField.drawTextBox();

        // Display accounts
        int y = 220;
        this.fontRendererObj.drawString("Accounts:", this.width / 2 - 100, y, 0xFFFF8C00);
        for (AccountManager.Account account : FoxoCraft.accountManager.getAccounts()) {
            y += 15;
            this.fontRendererObj.drawString(account.getUsername() + " (" + account.getNickname() + ")", this.width / 2 - 100, y, 0xFFFFFFFF);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        usernameField.textboxKeyTyped(typedChar, keyCode);
        nicknameField.textboxKeyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        usernameField.mouseClicked(mouseX, mouseY, mouseButton);
        nicknameField.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0: // Add Account
                if (!usernameField.getText().isEmpty() && !nicknameField.getText().isEmpty()) {
                    FoxoCraft.accountManager.addAccount(usernameField.getText(), nicknameField.getText());
                    usernameField.setText("");
                    nicknameField.setText("");
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