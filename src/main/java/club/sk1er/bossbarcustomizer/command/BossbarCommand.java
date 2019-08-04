package club.sk1er.bossbarcustomizer.command;

import club.sk1er.bossbarcustomizer.BossbarMod;
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
        BossbarMod.INSTANCE.initializeGui();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }
}
