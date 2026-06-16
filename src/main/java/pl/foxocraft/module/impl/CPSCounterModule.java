package pl.foxocraft.module.impl;

import org.lwjgl.input.Mouse;
import pl.foxocraft.module.Module;
import pl.foxocraft.util.Category;

import java.util.LinkedList;
import java.util.Queue;

public class CPSCounterModule extends Module {

    private Queue<Long> leftClicks;
    private Queue<Long> rightClicks;
    private int leftCPS;
    private int rightCPS;
    private boolean lastLeftButtonState = false;
    private boolean lastRightButtonState = false;

    public CPSCounterModule() {
        super("CPS Counter", "Display clicks per second", Category.RENDER, -1, false);
        this.leftClicks = new LinkedList<>();
        this.rightClicks = new LinkedList<>();
        this.leftCPS = 0;
        this.rightCPS = 0;
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        leftClicks.clear();
        rightClicks.clear();
        leftCPS = 0;
        rightCPS = 0;
    }

    @Override
    public void onUpdate() {
        long currentTime = System.currentTimeMillis();
        long oneSecondAgo = currentTime - 1000;

        // Left click
        boolean leftButtonPressed = Mouse.isButtonDown(0);
        if (leftButtonPressed && !lastLeftButtonState) {
            leftClicks.offer(currentTime);
        }
        lastLeftButtonState = leftButtonPressed;

        // Right click
        boolean rightButtonPressed = Mouse.isButtonDown(1);
        if (rightButtonPressed && !lastRightButtonState) {
            rightClicks.offer(currentTime);
        }
        lastRightButtonState = rightButtonPressed;

        // Remove old clicks
        while (!leftClicks.isEmpty() && leftClicks.peek() < oneSecondAgo) {
            leftClicks.poll();
        }
        while (!rightClicks.isEmpty() && rightClicks.peek() < oneSecondAgo) {
            rightClicks.poll();
        }

        leftCPS = leftClicks.size();
        rightCPS = rightClicks.size();
    }

    public int getLeftCPS() {
        return leftCPS;
    }

    public int getRightCPS() {
        return rightCPS;
    }
}