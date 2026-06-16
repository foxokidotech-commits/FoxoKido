package pl.foxocraft.screenshot;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ScreenShotHelper;
import pl.foxocraft.util.FileManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScreenshotManager {

    private FileManager fileManager;
    private List<Screenshot> screenshots;
    private SimpleDateFormat dateFormat;

    public ScreenshotManager() {
        this.fileManager = new FileManager();
        this.screenshots = new ArrayList<>();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        loadScreenshots();
    }

    public void takeScreenshot() {
        File screenshotDir = fileManager.getScreenshotsDirectory();
        String timestamp = dateFormat.format(new Date());
        File screenshotFile = new File(screenshotDir, "screenshot_" + timestamp + ".png");

        try {
            Minecraft mc = Minecraft.getMinecraft();
            ScreenShotHelper.saveScreenshot(screenshotDir, screenshotFile.getName(), mc.displayWidth, mc.displayHeight, mc.getFramebuffer());

            Screenshot screenshot = new Screenshot(screenshotFile.getName(), System.currentTimeMillis());
            screenshots.add(screenshot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Screenshot> getScreenshots() {
        return new ArrayList<>(screenshots);
    }

    public void deleteScreenshot(String filename) {
        screenshots.removeIf(s -> s.getFilename().equals(filename));
        File screenshotFile = new File(fileManager.getScreenshotsDirectory(), filename);
        fileManager.deleteFile(screenshotFile);
    }

    private void loadScreenshots() {
        File screenshotDir = fileManager.getScreenshotsDirectory();
        File[] files = screenshotDir.listFiles((dir, name) -> name.endsWith(".png"));

        if (files != null) {
            for (File file : files) {
                Screenshot screenshot = new Screenshot(file.getName(), file.lastModified());
                screenshots.add(screenshot);
            }
        }
    }

    public static class Screenshot {
        private String filename;
        private long timestamp;

        public Screenshot(String filename, long timestamp) {
            this.filename = filename;
            this.timestamp = timestamp;
        }

        public String getFilename() {
            return filename;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public String getFormattedDate() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
        }
    }
}