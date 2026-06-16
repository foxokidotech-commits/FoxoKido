package pl.foxocraft.cosmetics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.util.ResourceLocation;

public class CapeRenderer {

    private CosmeticsManager cosmeticsManager;

    public CapeRenderer(CosmeticsManager cosmeticsManager) {
        this.cosmeticsManager = cosmeticsManager;
    }

    public void renderCape(AbstractClientPlayer player) {
        if (!cosmeticsManager.hasCape(player.getName())) {
            return;
        }

        CosmeticsManager.Cape cape = cosmeticsManager.getCape(player.getName());
        if (cape.getTextureLocation() == null) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        mc.getTextureManager().bindTexture(cape.getTextureLocation());

        // Render cape on player
        // This would need proper implementation with OpenGL rendering
    }
}