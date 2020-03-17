package club.sk1er.bossbarcustomizer.command;

import club.sk1er.bossbarcustomizer.gui.BossbarGui;
import club.sk1er.mods.core.ModCore;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class BossbarCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "bossbar";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/bossbar";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        ModCore.getInstance().getGuiHandler().open(new BossbarGui());
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }
}
