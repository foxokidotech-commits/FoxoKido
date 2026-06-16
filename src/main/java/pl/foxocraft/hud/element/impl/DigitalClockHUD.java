package pl.foxocraft.hud.element.impl;

import pl.foxocraft.FoxoCraft;
import pl.foxocraft.hud.element.HUDElement;
import pl.foxocraft.module.impl.DigitalClockModule;

import java.util.List;

public class DigitalClockHUD extends HUDElement {

    private static final int LINE_HEIGHT = 12;
    private static final int PADDING = 5;

    public DigitalClockHUD(int x, int y) {
        super("Digital Clock", x, y, 150, 150);
    }

    @Override
    public void render() {
        DigitalClockModule clockModule = (DigitalClockModule) FoxoCraft.moduleManager.getModule("Digital Clock");
        if (clockModule != null && clockModule.isEnabled()) {
            renderClock(clockModule);
        }
    }

    private void renderClock(DigitalClockModule clockModule) {
        List<DigitalClockModule.TimeZoneDisplay> timeZones = clockModule.getTimeZones();
        
        // Draw header
        drawString("World Clock", x + PADDING, y + PADDING, 0xFFFF8C00);
        
        int currentY = y + PADDING + LINE_HEIGHT;
        int displayedCount = 0;
        
        for (DigitalClockModule.TimeZoneDisplay tz : timeZones) {
            // Limit visible time zones to fit in HUD element
            if (displayedCount >= 8) break;
            
            String timeStr = tz.getCurrentTime();
            String tzLabel = tz.getLabel();
            
            // Color code based on time of day
            int color = getTimeColor(tz.hour);
            
            // Draw timezone label and time
            drawString(tzLabel + ": " + timeStr, x + PADDING, currentY, color);
            
            // Draw date if enabled
            if (clockModule.isShowingDate()) {
                drawString(tz.getCurrentDate(), x + PADDING, currentY + LINE_HEIGHT - 2, 0xFF888888);
                currentY += LINE_HEIGHT * 2;
            } else {
                currentY += LINE_HEIGHT;
            }
            
            displayedCount++;
        }
        
        // Update width/height based on content
        this.height = PADDING + LINE_HEIGHT + (displayedCount * (clockModule.isShowingDate() ? LINE_HEIGHT * 2 : LINE_HEIGHT)) + PADDING;
        this.width = 150;
    }

    private int getTimeColor(int hour) {
        if (hour >= 6 && hour < 12) {
            return 0xFFFFFF00; // Morning - Yellow
        } else if (hour >= 12 && hour < 17) {
            return 0xFFFFAAAA; // Afternoon - Light Yellow
        } else if (hour >= 17 && hour < 21) {
            return 0xFFFF6600; // Evening - Orange
        } else {
            return 0xFF6666FF; // Night - Blue
        }
    }
}