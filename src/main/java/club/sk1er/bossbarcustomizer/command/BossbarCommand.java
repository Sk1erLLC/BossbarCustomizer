package club.sk1er.bossbarcustomizer.command;

import club.sk1er.bossbarcustomizer.gui.BossbarGui;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import gg.essential.api.utils.GuiUtil;

public class BossbarCommand extends Command {
    public BossbarCommand() {
        super("bossbar");
    }

    @DefaultHandler
    public void handle() {
        GuiUtil.open(new BossbarGui());
    }
}
