package pl.foxocraft;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.foxocraft.account.AccountManager;
import pl.foxocraft.event.EventManager;
import pl.foxocraft.gui.ClickGUI;
import pl.foxocraft.hud.HUDManager;
import pl.foxocraft.module.ModuleManager;
import pl.foxocraft.profile.ProfileManager;
import pl.foxocraft.util.FileManager;

@Mod(modid = "foxocraft", name = "FoxoCraft Client", version = "1.0.0")
public class FoxoCraft {

    public static final Logger LOGGER = LogManager.getLogger("FoxoCraft");
    public static final String MOD_ID = "foxocraft";
    public static final String MOD_NAME = "FoxoCraft Client";
    public static final String VERSION = "1.0.0";

    public static ModuleManager moduleManager;
    public static EventManager eventManager;
    public static HUDManager hudManager;
    public static AccountManager accountManager;
    public static ProfileManager profileManager;
    public static FileManager fileManager;
    public static ClickGUI clickGUI;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("FoxoCraft Client - Pre-initialization");
        fileManager = new FileManager();
        fileManager.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("FoxoCraft Client - Initialization");
        
        accountManager = new AccountManager();
        profileManager = new ProfileManager();
        moduleManager = new ModuleManager();
        eventManager = new EventManager();
        hudManager = new HUDManager();
        clickGUI = new ClickGUI();

        moduleManager.registerModules();
        eventManager.registerListeners();
        hudManager.init();

        LOGGER.info("FoxoCraft Client v" + VERSION + " loaded successfully!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LOGGER.info("FoxoCraft Client - Post-initialization");
    }
}