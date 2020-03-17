package club.sk1er.bossbarcustomizer;

import club.sk1er.bossbarcustomizer.command.BossbarCommand;
import club.sk1er.bossbarcustomizer.config.BossbarConfig;
import club.sk1er.modcore.ModCoreInstaller;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = BossbarMod.MODID, name = BossbarMod.NAME, version = BossbarMod.VERSION)
public class BossbarMod {

    public static final String NAME = "BossbarCustomizer";
    public static final String MODID = "bossbar_customizer";
    public static final String VERSION = "1.1";

    @Mod.Instance(MODID)
    public static BossbarMod INSTANCE;

    private BossbarConfig bossbarConfig;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModCoreInstaller.initializeModCore(Minecraft.getMinecraft().mcDataDir);
        bossbarConfig = new BossbarConfig();
        bossbarConfig.preload();
        ClientCommandHandler.instance.registerCommand(new BossbarCommand());
        MinecraftForge.EVENT_BUS.register(this);
    }

    public BossbarConfig getBossbarConfig() {
        return bossbarConfig;
    }
}
