package club.sk1er.bossbarcustomizer;

import club.sk1er.bossbarcustomizer.command.BossbarCommand;
import club.sk1er.bossbarcustomizer.config.BossbarConfig;
import club.sk1er.bossbarcustomizer.gui.BossbarGui;
import club.sk1er.bossbarcustomizer.util.Sk1erMod;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

@Mod(modid = BossbarMod.MODID, name = BossbarMod.NAME, version = BossbarMod.VERSION)
public class BossbarMod {

    public static final String NAME = "Bossbar Customizer";
    public static final String MODID = "bossbar_customizer";
    public static final String VERSION = "1.0";
    @Mod.Instance(MODID)
    public static BossbarMod INSTANCE;
    private File configFile;
    private boolean gui;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        configFile = event.getSuggestedConfigurationFile();
        loadConfig();
        Runtime.getRuntime().addShutdownHook(new Thread(this::saveConfig));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        new Sk1erMod(MODID, VERSION, NAME).checkStatus();
        ClientCommandHandler.instance.registerCommand(new BossbarCommand());
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void saveConfig() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("BOSSBAR_ALL", BossbarConfig.BOSSBAR_ALL);
            jsonObject.addProperty("BOSSBAR_TEXT", BossbarConfig.BOSSBAR_TEXT);
            jsonObject.addProperty("BOSSBAR_BAR", BossbarConfig.BOSSBAR_BAR);
            jsonObject.addProperty("BOSSBAR_X", BossbarConfig.BOSSBAR_X);
            jsonObject.addProperty("BOSSBAR_Y", BossbarConfig.BOSSBAR_Y);
            jsonObject.addProperty("SCALE", BossbarConfig.SCALE);
            FileUtils.writeStringToFile(configFile, jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadConfig() {

        try {
            JsonObject object = new JsonParser().parse(FileUtils.readFileToString(configFile)).getAsJsonObject();

            if (object.has("BOSSBAR_TEXT")) {
                BossbarConfig.BOSSBAR_TEXT = object.get("BOSSBAR_TEXT").getAsBoolean();
            }

            if (object.has("BOSSBAR_ALL")) {
                BossbarConfig.BOSSBAR_ALL = object.get("BOSSBAR_ALL").getAsBoolean();
            }

            if (object.has("BOSSBAR_BAR")) {
                BossbarConfig.BOSSBAR_BAR = object.get("BOSSBAR_BAR").getAsBoolean();
            }

            if (object.has("BOSSBAR_X")) {
                BossbarConfig.BOSSBAR_X = object.get("BOSSBAR_X").getAsDouble();
            }

            if (object.has("BOSSBAR_Y")) {
                BossbarConfig.BOSSBAR_X = object.get("BOSSBAR_Y").getAsDouble();
            }
            if (object.has("SCALE")) {
                BossbarConfig.SCALE = object.get("SCALE").getAsDouble();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void openGui(TickEvent.ClientTickEvent event) {
        if (gui) {
            Minecraft.getMinecraft().displayGuiScreen(new BossbarGui());
            gui = false;
        }
    }

    public void initializeGui() {
        gui = true;
    }
}
