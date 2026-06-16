package pl.foxocraft.module;

import pl.foxocraft.module.impl.*;
import pl.foxocraft.util.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleManager {

    private List<Module> modules;
    private Map<String, Module> moduleMap;

    public ModuleManager() {
        this.modules = new ArrayList<>();
        this.moduleMap = new HashMap<>();
    }

    public void registerModules() {
        // Movement modules
        registerModule(new ZoomModule());
        registerModule(new ToggleSprintModule());

        // Render modules
        registerModule(new FullbrightModule());
        registerModule(new FPSCounterModule());
        registerModule(new CPSCounterModule());
        registerModule(new KeystrokesModule());
        registerModule(new ArmorStatusModule());
        registerModule(new CoordinatesModule());
        registerModule(new WaypointsModule());
    }

    public void registerModule(Module module) {
        modules.add(module);
        moduleMap.put(module.getName().toLowerCase(), module);
    }

    public Module getModule(String name) {
        return moduleMap.get(name.toLowerCase());
    }

    public List<Module> getModulesByCategory(Category category) {
        List<Module> categoryModules = new ArrayList<>();
        for (Module module : modules) {
            if (module.getCategory() == category) {
                categoryModules.add(module);
            }
        }
        return categoryModules;
    }

    public List<Module> getEnabledModules() {
        List<Module> enabled = new ArrayList<>();
        for (Module module : modules) {
            if (module.isEnabled()) {
                enabled.add(module);
            }
        }
        return enabled;
    }

    public List<Module> getAllModules() {
        return new ArrayList<>(modules);
    }

    public void updateModules() {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.onUpdate();
            }
        }
    }
}