package pl.foxocraft.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class RenderUtil {

    public static int getScreenWidth() {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc);
        return sr.getScaledWidth();
    }

    public static int getScreenHeight() {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc);
        return sr.getScaledHeight();
    }

    public static void drawCenteredString(String text, int x, int y, int color) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.fontRendererObj.drawCenteredString(text, x, y, color);
    }

    public static void drawString(String text, int x, int y, int color) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.fontRendererObj.drawStringWithShadow(text, x, y, color);
    }

    public static void drawRect(int x1, int y1, int x2, int y2, int color) {
        net.minecraft.client.gui.Gui.drawRect(x1, y1, x2, y2, color);
    }

    public static int getStringWidth(String text) {
        return Minecraft.getMinecraft().fontRendererObj.getStringWidth(text);
    }
}