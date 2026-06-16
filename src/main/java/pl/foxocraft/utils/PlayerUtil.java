package pl.foxocraft.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;

public class PlayerUtil {

    public static boolean isPlayerNull() {
        return Minecraft.getMinecraft().thePlayer == null;
    }

    public static boolean isWorldNull() {
        return Minecraft.getMinecraft().theWorld == null;
    }

    public static BlockPos getPlayerBlockPos() {
        if (isPlayerNull()) return null;
        Minecraft mc = Minecraft.getMinecraft();
        return new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
    }

    public static double getPlayerHealth() {
        if (isPlayerNull()) return 0;
        return Minecraft.getMinecraft().thePlayer.getHealth();
    }

    public static int getPlayerFood() {
        if (isPlayerNull()) return 0;
        return Minecraft.getMinecraft().thePlayer.getFoodStats().getFoodLevel();
    }

    public static float getPlayerExperience() {
        if (isPlayerNull()) return 0;
        return Minecraft.getMinecraft().thePlayer.experience;
    }
}