package pl.foxocraft.cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import pl.foxocraft.util.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CosmeticsManager {

    private Map<String, Cape> capes;
    private FileManager fileManager;

    public CosmeticsManager() {
        this.capes = new HashMap<>();
        this.fileManager = new FileManager();
        loadCapes();
    }

    public void registerCape(String playerName, String capeUrl) {
        Cape cape = new Cape(playerName, capeUrl);
        capes.put(playerName.toLowerCase(), cape);
    }

    public void removeCape(String playerName) {
        capes.remove(playerName.toLowerCase());
    }

    public Cape getCape(String playerName) {
        return capes.get(playerName.toLowerCase());
    }

    public boolean hasCape(String playerName) {
        return capes.containsKey(playerName.toLowerCase());
    }

    private void loadCapes() {
        // Load capes from file system or external URL
        // Implementation depends on cape provider
    }

    public static class Cape {
        private String playerName;
        private String capeUrl;
        private ResourceLocation textureLocation;

        public Cape(String playerName, String capeUrl) {
            this.playerName = playerName;
            this.capeUrl = capeUrl;
        }

        public String getPlayerName() {
            return playerName;
        }

        public String getCapeUrl() {
            return capeUrl;
        }

        public ResourceLocation getTextureLocation() {
            return textureLocation;
        }

        public void setTextureLocation(ResourceLocation location) {
            this.textureLocation = location;
        }
    }
}