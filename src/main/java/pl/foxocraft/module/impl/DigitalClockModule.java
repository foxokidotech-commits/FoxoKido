package pl.foxocraft.module.impl;

import org.lwjgl.input.Keyboard;
import pl.foxocraft.module.Module;
import pl.foxocraft.util.Category;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DigitalClockModule extends Module {

    private List<TimeZoneDisplay> timeZones;
    private DateTimeFormatter timeFormatter;
    private DateTimeFormatter dateFormatter;
    private boolean showDate;
    private boolean use24HourFormat;
    private int updateInterval;
    private long lastUpdate;

    public DigitalClockModule() {
        super("Digital Clock", "Display current time in multiple time zones", Category.UTILITY, Keyboard.KEY_T, true);
        this.timeZones = new ArrayList<>();
        this.showDate = true;
        this.use24HourFormat = true;
        this.updateInterval = 100; // milliseconds
        this.lastUpdate = 0;
        
        // Initialize formatters
        this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Add default time zones
        addTimeZone("Local", ZoneId.systemDefault());
        addTimeZone("UTC", ZoneId.of("UTC"));
        addTimeZone("EST", ZoneId.of("America/New_York"));
        addTimeZone("CST", ZoneId.of("America/Chicago"));
        addTimeZone("PST", ZoneId.of("America/Los_Angeles"));
        addTimeZone("CET", ZoneId.of("Europe/Paris"));
        addTimeZone("JST", ZoneId.of("Asia/Tokyo"));
        addTimeZone("AEST", ZoneId.of("Australia/Sydney"));
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onUpdate() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate >= updateInterval) {
            updateTimeZones();
            lastUpdate = currentTime;
        }
    }

    private void updateTimeZones() {
        for (TimeZoneDisplay tz : timeZones) {
            ZonedDateTime zdt = ZonedDateTime.now(tz.zoneId);
            tz.currentTime = zdt.format(timeFormatter);
            tz.currentDate = zdt.format(dateFormatter);
            tz.hour = zdt.getHour();
            tz.minute = zdt.getMinute();
            tz.second = zdt.getSecond();
        }
    }

    public void addTimeZone(String label, ZoneId zoneId) {
        timeZones.add(new TimeZoneDisplay(label, zoneId));
    }

    public void removeTimeZone(String label) {
        timeZones.removeIf(tz -> tz.label.equalsIgnoreCase(label));
    }

    public void toggleDateDisplay() {
        showDate = !showDate;
    }

    public void toggleTimeFormat() {
        use24HourFormat = !use24HourFormat;
        if (use24HourFormat) {
            this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        } else {
            this.timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        }
    }

    public List<TimeZoneDisplay> getTimeZones() {
        return new ArrayList<>(timeZones);
    }

    public boolean isShowingDate() {
        return showDate;
    }

    public boolean isUsing24HourFormat() {
        return use24HourFormat;
    }

    public String getFormattedTime(String label) {
        for (TimeZoneDisplay tz : timeZones) {
            if (tz.label.equalsIgnoreCase(label)) {
                return tz.currentTime;
            }
        }
        return "";
    }

    public String getFormattedDate(String label) {
        for (TimeZoneDisplay tz : timeZones) {
            if (tz.label.equalsIgnoreCase(label)) {
                return tz.currentDate;
            }
        }
        return "";
    }

    public static class TimeZoneDisplay {
        public String label;
        public ZoneId zoneId;
        public String currentTime;
        public String currentDate;
        public int hour;
        public int minute;
        public int second;

        public TimeZoneDisplay(String label, ZoneId zoneId) {
            this.label = label;
            this.zoneId = zoneId;
            this.currentTime = "00:00:00";
            this.currentDate = "0000-00-00";
        }

        public String getLabel() {
            return label;
        }

        public String getCurrentTime() {
            return currentTime;
        }

        public String getCurrentDate() {
            return currentDate;
        }
    }
}