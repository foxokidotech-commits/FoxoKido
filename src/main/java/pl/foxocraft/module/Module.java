package pl.foxocraft.module;

import net.minecraftforge.fml.common.eventbus.Subscribe;
import pl.foxocraft.event.EventManager;
import pl.foxocraft.util.Category;

public abstract class Module {

    private String name;
    private String description;
    private Category category;
    private boolean enabled;
    private boolean hasToggle;
    private int keyCode;

    public Module(String name, String description, Category category, int keyCode, boolean hasToggle) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.keyCode = keyCode;
        this.hasToggle = hasToggle;
        this.enabled = false;
    }

    public void toggle() {
        if (hasToggle) {
            setEnabled(!enabled);
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract void onUpdate();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public boolean hasToggle() {
        return hasToggle;
    }
}