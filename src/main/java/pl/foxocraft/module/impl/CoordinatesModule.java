package pl.foxocraft.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import pl.foxocraft.module.Module;
import pl.foxocraft.util.Category;

public class CoordinatesModule extends Module {

    public CoordinatesModule() {
        super("Coordinates", "Display player coordinates", Category.RENDER, -1, false);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onUpdate() {
    }

    public String getCoordinates() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer != null) {
            EntityPlayer player = mc.thePlayer;
            int x = (int) player.posX;
            int y = (int) player.posY;
            int z = (int) player.posZ;
            return String.format("X: %d Y: %d Z: %d", x, y, z);
        }
        return "X: 0 Y: 0 Z: 0";
    }

    public double getExactX() {
        Minecraft mc = Minecraft.getMinecraft();
        return mc.thePlayer != null ? mc.thePlayer.posX : 0;
    }

    public double getExactY() {
        Minecraft mc = Minecraft.getMinecraft();
        return mc.thePlayer != null ? mc.thePlayer.posY : 0;
    }

    public double getExactZ() {
        Minecraft mc = Minecraft.getMinecraft();
        return mc.thePlayer != null ? mc.thePlayer.posZ : 0;
    }
}