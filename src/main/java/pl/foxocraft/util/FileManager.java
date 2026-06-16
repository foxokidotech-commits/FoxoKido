package pl.foxocraft.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {

    private File baseDirectory;
    private File configDirectory;
    private File accountDirectory;
    private File profileDirectory;
    private File screenshotsDirectory;
    private File waypointsDirectory;

    public void init() {
        baseDirectory = new File(System.getProperty("user.home"), ".foxocraft");
        configDirectory = new File(baseDirectory, "config");
        accountDirectory = new File(baseDirectory, "accounts");
        profileDirectory = new File(baseDirectory, "profiles");
        screenshotsDirectory = new File(baseDirectory, "screenshots");
        waypointsDirectory = new File(baseDirectory, "waypoints");

        createDirectories();
    }

    private void createDirectories() {
        baseDirectory.mkdirs();
        configDirectory.mkdirs();
        accountDirectory.mkdirs();
        profileDirectory.mkdirs();
        screenshotsDirectory.mkdirs();
        waypointsDirectory.mkdirs();
    }

    public File getBaseDirectory() {
        return baseDirectory;
    }

    public File getConfigDirectory() {
        return configDirectory;
    }

    public File getAccountDirectory() {
        return accountDirectory;
    }

    public File getProfileDirectory() {
        return profileDirectory;
    }

    public File getScreenshotsDirectory() {
        return screenshotsDirectory;
    }

    public File getWaypointsDirectory() {
        return waypointsDirectory;
    }

    public void saveFile(File file, String content) throws IOException {
        file.getParentFile().mkdirs();
        Files.write(file.toPath(), content.getBytes());
    }

    public String loadFile(File file) throws IOException {
        if (!file.exists()) {
            return "";
        }
        return new String(Files.readAllBytes(file.toPath()));
    }

    public boolean deleteFile(File file) {
        return file.delete();
    }
}