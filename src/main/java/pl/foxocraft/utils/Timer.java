package pl.foxocraft.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Timer {

    private long startTime;
    private long lastTime;
    private long deltaTime;

    public Timer() {
        this.startTime = System.currentTimeMillis();
        this.lastTime = startTime;
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        this.deltaTime = currentTime - lastTime;
        this.lastTime = currentTime;
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }

    public long getDeltaTime() {
        return deltaTime;
    }

    public float getDeltaTimeSeconds() {
        return deltaTime / 1000.0f;
    }

    public boolean hasElapsed(long milliseconds) {
        return getElapsedTime() >= milliseconds;
    }

    public void reset() {
        this.startTime = System.currentTimeMillis();
        this.lastTime = startTime;
    }
}