package pl.foxocraft.gui.screen;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import pl.foxocraft.FoxoCraft;
import pl.foxocraft.module.impl.DigitalClockModule;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class DigitalClockSettingsGui extends GuiScreen {

    private GuiScreen parentScreen;
    private GuiTextField zoneInput;
    private DigitalClockModule clockModule;
    private List<String> commonTimeZones;
    private int scrollOffset = 0;
    private static final int ZONES_PER_PAGE = 5;

    public DigitalClockSettingsGui(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
        this.clockModule = (DigitalClockModule) FoxoCraft.moduleManager.getModule("Digital Clock");
        this.commonTimeZones = getCommonTimeZones();
    }

    private List<String> getCommonTimeZones() {
        List<String> zones = new ArrayList<>();
        zones.add("UTC");
        zones.add("GMT");
        zones.add("EST");
        zones.add("CST");
        zones.add("MST");
        zones.add("PST");
        zones.add("CET");
        zones.add("EET");
        zones.add("IST");
        zones.add("JST");
        zones.add("AEST");
        zones.add("NZST");
        return zones;
    }

    @Override
    public void initGui() {
        zoneInput = new GuiTextField(1, this.fontRendererObj, this.width / 2 - 100, 60, 150, 20);
        zoneInput.setMaxStringLength(32);
        zoneInput.setFocused(true);

        this.buttonList.add(new GuiButton(0, this.width / 2 - 75, 90, 150, 20, "Add Time Zone"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 75, 120, 150, 20, "Toggle Date"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 75, 150, 150, 20, "Toggle 12/24 Format"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 75, this.height - 30, 150, 20, "Back"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        this.fontRendererObj.drawCenteredString("Digital Clock Settings", this.width / 2, 20, 0xFFFF8C00);
        this.fontRendererObj.drawString("Time Zone:", this.width / 2 - 100, 45, 0xFFFFFFFF);

        zoneInput.drawTextBox();

        // Display current time zones
        int y = 200;
        this.fontRendererObj.drawString("Active Time Zones:", this.width / 2 - 100, y, 0xFFFF8C00);
        
        List<DigitalClockModule.TimeZoneDisplay> timeZones = clockModule.getTimeZones();
        int displayCount = 0;
        for (int i = scrollOffset; i < timeZones.size() && displayCount < ZONES_PER_PAGE; i++) {
            y += 15;
            DigitalClockModule.TimeZoneDisplay tz = timeZones.get(i);
            this.fontRendererObj.drawString(tz.getLabel() + ": " + tz.getCurrentTime(), this.width / 2 - 100, y, 0xFFFFFFFF);
            displayCount++;
        }

        // Display common time zones
        y = 200;
        this.fontRendererObj.drawString("Common Time Zones:", this.width / 2 + 50, y, 0xFFFF8C00);
        for (int i = 0; i < Math.min(5, commonTimeZones.size()); i++) {
            y += 15;
            this.fontRendererObj.drawString("- " + commonTimeZones.get(i), this.width / 2 + 50, y, 0xFF888888);
        }

        // Display format info
        y = 120;
        String dateDisplay = clockModule.isShowingDate() ? "Enabled" : "Disabled";
        String formatDisplay = clockModule.isUsing24HourFormat() ? "24-Hour" : "12-Hour";
        this.fontRendererObj.drawString("Date Display: " + dateDisplay, this.width / 2 - 75, y + 35, 0xFF888888);
        this.fontRendererObj.drawString("Time Format: " + formatDisplay, this.width / 2 - 75, y + 50, 0xFF888888);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        zoneInput.textboxKeyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        zoneInput.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0: // Add Time Zone
                String zoneInput = this.zoneInput.getText().trim();
                if (!zoneInput.isEmpty()) {
                    try {
                        ZoneId zoneId = ZoneId.of(zoneInput);
                        clockModule.addTimeZone(zoneInput, zoneId);
                        this.zoneInput.setText("");
                    } catch (Exception e) {
                        // Invalid zone ID
                    }
                }
                break;
            case 1: // Toggle Date
                clockModule.toggleDateDisplay();
                break;
            case 2: // Toggle Format
                clockModule.toggleTimeFormat();
                break;
            case 3: // Back
                mc.displayGuiScreen(parentScreen);
                break;
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}