package club.sk1er.bossbarcustomizer;

import club.sk1er.bossbarcustomizer.command.BossbarCommand;
import club.sk1er.bossbarcustomizer.config.BossbarConfig;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import gg.essential.api.EssentialAPI;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

@Mod(modid = BossbarMod.MODID, name = BossbarMod.NAME, version = BossbarMod.VERSION)
public class BossbarMod {

    public static final String NAME = "BossbarCustomizer";
    public static final String MODID = "bossbar_customizer";
    public static final String VERSION = "1.2.1";

    private File configFile;
    private final Logger logger = LogManager.getLogger("BossbarCustomizer");

    @Mod.Instance(MODID)
    public static BossbarMod INSTANCE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configFile = event.getSuggestedConfigurationFile();
        loadConfig();
        Runtime.getRuntime().addShutdownHook(new Thread(this::saveConfig));
    }

    private void loadConfig() {
        try {
            JsonObject object = new JsonParser().parse(FileUtils.readFileToString(configFile)).getAsJsonObject();
            if (object.has("BOSSBAR_TEXT")) BossbarConfig.BOSSBAR_TEXT = object.get("BOSSBAR_TEXT").getAsBoolean();
            if (object.has("BOSSBAR_ALL")) BossbarConfig.BOSSBAR_ALL = object.get("BOSSBAR_ALL").getAsBoolean();
            if (object.has("BOSSBAR_BAR")) BossbarConfig.BOSSBAR_BAR = object.get("BOSSBAR_BAR").getAsBoolean();
            if (object.has("BOSSBAR_X")) BossbarConfig.BOSSBAR_X = object.get("BOSSBAR_X").getAsDouble();
            if (object.has("BOSSBAR_Y")) BossbarConfig.BOSSBAR_Y = object.get("BOSSBAR_Y").getAsDouble();
            if (object.has("SCALE")) BossbarConfig.SCALE = object.get("SCALE").getAsDouble();
        } catch (IOException e) {
            this.logger.error("Failed to load config.", e);
        }

        System.out.println("loaded config");
    }

    public void saveConfig() {
        try {
            JsonObject object = new JsonObject();
            object.addProperty("BOSSBAR_ALL", BossbarConfig.BOSSBAR_ALL);
            object.addProperty("BOSSBAR_TEXT", BossbarConfig.BOSSBAR_TEXT);
            object.addProperty("BOSSBAR_BAR", BossbarConfig.BOSSBAR_BAR);
            object.addProperty("BOSSBAR_X", BossbarConfig.BOSSBAR_X);
            object.addProperty("BOSSBAR_Y", BossbarConfig.BOSSBAR_Y);
            object.addProperty("SCALE", BossbarConfig.SCALE);
            FileUtils.writeStringToFile(configFile, object.toString());
        } catch (IOException e) {
            this.logger.error("Failed to write config.", e);
        }

        System.out.println("saved config");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        EssentialAPI.getCommandRegistry().registerCommand(new BossbarCommand());
        MinecraftForge.EVENT_BUS.register(this);
    }
}
