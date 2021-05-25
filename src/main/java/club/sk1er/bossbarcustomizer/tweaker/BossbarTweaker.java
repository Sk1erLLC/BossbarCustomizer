package club.sk1er.bossbarcustomizer.tweaker;

import club.sk1er.bossbarcustomizer.forge.FMLLoadingPlugin;
import gg.essential.loader.EssentialSetupTweaker;

@SuppressWarnings("unused")
public class BossbarTweaker extends EssentialSetupTweaker {
    public BossbarTweaker() {
        super(new String[]{FMLLoadingPlugin.class.getName()});
    }
}
