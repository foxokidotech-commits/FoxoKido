package pl.foxocraft.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import pl.foxocraft.module.Module;
import pl.foxocraft.util.Category;

public class ArmorStatusModule extends Module {

    public ArmorStatusModule() {
        super("Armor Status", "Display armor durability", Category.RENDER, -1, false);
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

    public ItemStack getHelmet() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer != null) {
            return mc.thePlayer.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        }
        return null;
    }

    public ItemStack getChestplate() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer != null) {
            return mc.thePlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        }
        return null;
    }

    public ItemStack getLeggings() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer != null) {
            return mc.thePlayer.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        }
        return null;
    }

    public ItemStack getBoots() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer != null) {
            return mc.thePlayer.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        }
        return null;
    }
}