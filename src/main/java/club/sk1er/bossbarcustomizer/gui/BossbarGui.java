package club.sk1er.bossbarcustomizer.gui;

import club.sk1er.bossbarcustomizer.BossbarMod;
import club.sk1er.bossbarcustomizer.config.BossbarConfig;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.EnumChatFormatting;

public class BossbarGui extends GuiScreen {

    private GuiButton buttonBossbarAll;
    private GuiButton buttonBossbarText;
    private GuiButton buttonBossbarBar;

    private String previousBossName;
    private int previousStatusBarTime;
    private float previousHealthScale;

    @Override
    public void initGui() {
        previousBossName = BossStatus.bossName;
        BossStatus.bossName = "Sk1er LLC";
        previousStatusBarTime = BossStatus.statusBarTime;
        BossStatus.statusBarTime = Integer.MAX_VALUE;
        previousHealthScale = BossStatus.healthScale;
        BossStatus.healthScale = 1F;
        buttonList.add(buttonBossbarAll = new GuiButton(0, width / 2 - 155, calculateHeight(0), 155, 20,
                "Bossbar" + getSuffix(BossbarConfig.BOSSBAR_ALL)));
        buttonList.add(buttonBossbarText = new GuiButton(1, width / 2 + 5, calculateHeight(0), 155, 20,
                "Bossbar Text" + getSuffix(BossbarConfig.BOSSBAR_TEXT)));

        buttonList.add(buttonBossbarBar = new GuiButton(2, width / 2 - 155, calculateHeight(1), 155, 20,
                "Bossbar Bar" + getSuffix(BossbarConfig.BOSSBAR_BAR)));
        buttonList.add(new GuiButton(3, width / 2 + 5, calculateHeight(1), 155, 20, "Reset"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawCenteredString(mc.fontRendererObj, "Bossbar Mod " + BossbarMod.VERSION, width / 2, calculateHeight(-1), -1);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                BossbarConfig.BOSSBAR_ALL = !BossbarConfig.BOSSBAR_ALL;
                buttonBossbarAll.displayString = "Bossbar" + getSuffix(BossbarConfig.BOSSBAR_ALL);
                break;

            case 1:
                BossbarConfig.BOSSBAR_TEXT = !BossbarConfig.BOSSBAR_TEXT;
                buttonBossbarText.displayString = "Bossbar Text" + getSuffix(BossbarConfig.BOSSBAR_TEXT);
                break;

            case 2:
                BossbarConfig.BOSSBAR_BAR = !BossbarConfig.BOSSBAR_BAR;
                buttonBossbarBar.displayString = "Bossbar Bar" + getSuffix(BossbarConfig.BOSSBAR_BAR);
                break;

            case 3:
                BossbarConfig.BOSSBAR_ALL = true;
                BossbarConfig.BOSSBAR_TEXT = true;
                BossbarConfig.BOSSBAR_BAR = true;
                BossbarConfig.BOSSBAR_X = -1;
                BossbarConfig.BOSSBAR_Y = 12;
                break;
        }
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        BossbarConfig.BOSSBAR_X = mouseX;
        BossbarConfig.BOSSBAR_Y = mouseY;
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void onGuiClosed() {
        BossStatus.bossName = previousBossName;
        BossStatus.statusBarTime = previousStatusBarTime;
        BossStatus.healthScale = previousHealthScale;
        BossbarMod.INSTANCE.saveConfig();
        super.onGuiClosed();
    }

    private String getSuffix(boolean option) {
        return ": " + (option ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled");
    }

    private int calculateHeight(int row) {
        return 55 + row * 23;
    }
}
