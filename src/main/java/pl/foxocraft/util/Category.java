package pl.foxocraft.util;

public enum Category {
    MOVEMENT("Movement"),
    RENDER("Render"),
    COMBAT("Combat"),
    UTILITY("Utility"),
    PLAYER("Player");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}