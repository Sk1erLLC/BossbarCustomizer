package club.sk1er.bossbarcustomizer.tweaker;

import club.sk1er.bossbarcustomizer.forge.FMLLoadingPlugin;
import gg.essential.loader.EssentialTweaker;

@SuppressWarnings("unused")
public class BossbarTweaker extends EssentialTweaker {
    public BossbarTweaker() {
        super(new String[]{FMLLoadingPlugin.class.getName()});
    }
}
