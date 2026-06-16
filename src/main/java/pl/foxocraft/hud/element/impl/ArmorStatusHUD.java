package pl.foxocraft.hud.element.impl;

import net.minecraft.item.ItemStack;
import pl.foxocraft.FoxoCraft;
import pl.foxocraft.hud.element.HUDElement;
import pl.foxocraft.module.impl.ArmorStatusModule;

public class ArmorStatusHUD extends HUDElement {

    public ArmorStatusHUD(int x, int y) {
        super("Armor Status", x, y, 50, 70);
    }

    @Override
    public void render() {
        ArmorStatusModule armorModule = (ArmorStatusModule) FoxoCraft.moduleManager.getModule("Armor Status");
        if (armorModule != null && armorModule.isEnabled()) {
            drawString("Armor", x, y, 0xFFFF8C00);

            ItemStack helmet = armorModule.getHelmet();
            ItemStack chestplate = armorModule.getChestplate();
            ItemStack leggings = armorModule.getLeggings();
            ItemStack boots = armorModule.getBoots();

            drawArmorPiece("Helmet", helmet, 0);
            drawArmorPiece("Chest", chestplate, 1);
            drawArmorPiece("Legs", leggings, 2);
            drawArmorPiece("Boots", boots, 3);
        }
    }

    private void drawArmorPiece(String name, ItemStack armor, int index) {
        if (armor != null && armor.stackSize > 0) {
            int durability = armor.getMaxDamage() - armor.getItemDamage();
            int maxDurability = armor.getMaxDamage();
            int percent = (int) ((durability / (float) maxDurability) * 100);
            int color = percent > 50 ? 0xFF00FF00 : percent > 25 ? 0xFFFFFF00 : 0xFFFF0000;
            drawString(name + ": " + percent + "%", x, y + 10 + (index * 10), color);
        }
    }
}