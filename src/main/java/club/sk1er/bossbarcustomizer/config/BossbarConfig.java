package club.sk1er.bossbarcustomizer.config;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.Property;
import club.sk1er.vigilance.data.PropertyType;
import java.io.File;

public class BossbarConfig extends Vigilant {

    @Property(
        type = PropertyType.SWITCH,
        name = "Bossbar All",
        category = "Bossbar Selection",
        description = "All of the Bossbar",
        hidden = true
    )
    public static boolean BOSSBAR_ALL = true;

    @Property(
        type = PropertyType.SWITCH,
        name = "Bossbar Text",
        category = "Bossbar Selection",
        description = "Text portion of the Bossbar",
        hidden = true
    )
    public static boolean BOSSBAR_TEXT = true;

    @Property(
        type = PropertyType.SWITCH,
        name = "Bossbar Bar",
        category = "Bossbar Selection",
        description = "Bar portion of the Bossbar",
        hidden = true
    )
    public static boolean BOSSBAR_BAR = true;

    @Property(
        type = PropertyType.SWITCH,
        name = "Bossbar X",
        category = "Bossbar Position",
        description = "Bossbar X Position",
        hidden = true
    )
    public static double BOSSBAR_X = .5;

    @Property(
        type = PropertyType.SWITCH,
        name = "Bossbar Y",
        category = "Bossbar Position",
        description = "Bossbar Y Position",
        hidden = true
    )
    public static double BOSSBAR_Y = .05;

    @Property(
        type = PropertyType.SWITCH,
        name = "Bossbar Scale",
        category = "Bossbar Scale",
        description = "Bossbar Scale",
        hidden = true
    )
    public static double SCALE = 1.0;

    public BossbarConfig() {
        super(new File("./config/bossbar_customizer.toml"));
        initialize();
    }
}
