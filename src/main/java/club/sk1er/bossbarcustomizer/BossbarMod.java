package club.sk1er.bossbarcustomizer;

import club.sk1er.bossbarcustomizer.command.BossbarCommand;
import club.sk1er.bossbarcustomizer.config.BossbarConfig;
import gg.essential.api.EssentialAPI;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = BossbarMod.MODID, name = BossbarMod.NAME, version = BossbarMod.VERSION)
public class BossbarMod {

    public static final String NAME = "BossbarCustomizer";
    public static final String MODID = "bossbar_customizer";
    public static final String VERSION = "1.2.1";

    @Mod.Instance(MODID)
    public static BossbarMod INSTANCE;

    private BossbarConfig bossbarConfig;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        bossbarConfig = new BossbarConfig();
        bossbarConfig.preload();

        EssentialAPI.getCommandRegistry().registerCommand(new BossbarCommand());
        MinecraftForge.EVENT_BUS.register(this);
    }

    public BossbarConfig getBossbarConfig() {
        return bossbarConfig;
    }
}
