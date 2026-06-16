package pl.foxocraft.profile;

import pl.foxocraft.util.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileManager {

    private List<GameProfile> profiles;
    private GameProfile selectedProfile;
    private FileManager fileManager;

    public ProfileManager() {
        this.profiles = new ArrayList<>();
        this.fileManager = new FileManager();
        loadProfiles();
    }

    public void createProfile(String name) {
        GameProfile profile = new GameProfile(name);
        profiles.add(profile);
        saveProfile(profile);
    }

    public void deleteProfile(String name) {
        profiles.removeIf(p -> p.getName().equals(name));
        File profileFile = new File(fileManager.getProfileDirectory(), name + ".json");
        fileManager.deleteFile(profileFile);
    }

    public void selectProfile(String name) {
        for (GameProfile profile : profiles) {
            if (profile.getName().equals(name)) {
                this.selectedProfile = profile;
                return;
            }
        }
    }

    public GameProfile getSelectedProfile() {
        return selectedProfile;
    }

    public List<GameProfile> getProfiles() {
        return new ArrayList<>(profiles);
    }

    private void saveProfile(GameProfile profile) {
        try {
            File profileFile = new File(fileManager.getProfileDirectory(), profile.getName() + ".json");
            String json = profile.toJSON();
            fileManager.saveFile(profileFile, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadProfiles() {
        File profileDir = fileManager.getProfileDirectory();
        File[] files = profileDir.listFiles((dir, name) -> name.endsWith(".json"));

        if (files != null) {
            for (File file : files) {
                try {
                    String json = fileManager.loadFile(file);
                    GameProfile profile = GameProfile.fromJSON(json);
                    if (profile != null) {
                        profiles.add(profile);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class GameProfile {
        private String name;
        private Map<String, Boolean> moduleSettings;
        private Map<String, Object> clientSettings;
        private long createdAt;

        public GameProfile(String name) {
            this.name = name;
            this.moduleSettings = new HashMap<>();
            this.clientSettings = new HashMap<>();
            this.createdAt = System.currentTimeMillis();
        }

        public void setModuleEnabled(String moduleName, boolean enabled) {
            moduleSettings.put(moduleName, enabled);
        }

        public boolean isModuleEnabled(String moduleName) {
            return moduleSettings.getOrDefault(moduleName, false);
        }

        public void setClientSetting(String key, Object value) {
            clientSettings.put(key, value);
        }

        public Object getClientSetting(String key) {
            return clientSettings.get(key);
        }

        public String toJSON() {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("\"name\":\"" + name + "\",");
            sb.append("\"createdAt\":").append(createdAt).append(",");
            sb.append("\"modules\":{}");
            sb.append("}");
            return sb.toString();
        }

        public static GameProfile fromJSON(String json) {
            try {
                String name = extractValue(json, "name");
                GameProfile profile = new GameProfile(name);
                String createdAt = extractValue(json, "createdAt");
                if (!createdAt.isEmpty()) {
                    profile.createdAt = Long.parseLong(createdAt);
                }
                return profile;
            } catch (Exception e) {
                return null;
            }
        }

        private static String extractValue(String json, String key) {
            String search = "\"" + key + "\":\"";
            int start = json.indexOf(search);
            if (start == -1) {
                search = "\"" + key + "\":";
                start = json.indexOf(search);
                if (start == -1) return "";
                start += search.length();
                int end = json.indexOf(",", start);
                if (end == -1) end = json.indexOf("}", start);
                return json.substring(start, end).trim();
            }
            start += search.length();
            int end = json.indexOf("\"", start);
            return json.substring(start, end);
        }

        public String getName() { return name; }
        public long getCreatedAt() { return createdAt; }
    }
}