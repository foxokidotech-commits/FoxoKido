package pl.foxocraft.settings;

import pl.foxocraft.util.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SettingsManager {

    private Map<String, Object> settings;
    private FileManager fileManager;
    private File settingsFile;

    public SettingsManager() {
        this.settings = new HashMap<>();
        this.fileManager = new FileManager();
        this.settingsFile = new File(fileManager.getConfigDirectory(), "settings.json");
        loadSettings();
        setDefaults();
    }

    private void setDefaults() {
        if (!settings.containsKey("theme")) {
            settings.put("theme", "ORANGE_WHITE");
        }
        if (!settings.containsKey("hudEditMode")) {
            settings.put("hudEditMode", false);
        }
        if (!settings.containsKey("clickguiKey")) {
            settings.put("clickguiKey", 46); // C key
        }
        if (!settings.containsKey("hudEditKey")) {
            settings.put("hudEditKey", 35); // H key
        }
    }

    public void setSetting(String key, Object value) {
        settings.put(key, value);
        saveSettings();
    }

    public Object getSetting(String key) {
        return settings.get(key);
    }

    public String getStringSettings(String key, String defaultValue) {
        Object value = settings.get(key);
        return value != null ? value.toString() : defaultValue;
    }

    public int getIntSetting(String key, int defaultValue) {
        Object value = settings.get(key);
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return defaultValue;
    }

    public boolean getBooleanSetting(String key, boolean defaultValue) {
        Object value = settings.get(key);
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        return defaultValue;
    }

    private void saveSettings() {
        try {
            StringBuilder json = new StringBuilder();
            json.append("{");
            boolean first = true;
            for (Map.Entry<String, Object> entry : settings.entrySet()) {
                if (!first) json.append(",");
                json.append("\"" + entry.getKey() + "\":");
                if (entry.getValue() instanceof String) {
                    json.append("\"" + entry.getValue() + "\"");
                } else {
                    json.append(entry.getValue());
                }
                first = false;
            }
            json.append("}");
            fileManager.saveFile(settingsFile, json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSettings() {
        if (!settingsFile.exists()) {
            return;
        }

        try {
            String json = fileManager.loadFile(settingsFile);
            // Simple JSON parsing
            String[] pairs = json.split(",");
            for (String pair : pairs) {
                String[] keyValue = pair.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim().replace("\"", "").replace("{", "");
                    String value = keyValue[1].trim().replace("\"", "").replace("}", "");
                    try {
                        settings.put(key, Integer.parseInt(value));
                    } catch (NumberFormatException e) {
                        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
                            settings.put(key, Boolean.parseBoolean(value));
                        } else {
                            settings.put(key, value);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}