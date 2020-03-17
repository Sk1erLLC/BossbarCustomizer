package club.sk1er.bossbarcustomizer.gui;

import club.sk1er.bossbarcustomizer.BossbarMod;
import club.sk1er.bossbarcustomizer.config.BossbarConfig;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.client.config.GuiSlider;

import java.io.IOException;

public class BossbarGui extends GuiScreen {

    private GuiButton buttonBossbarAll;
    private GuiButton buttonBossbarText;
    private GuiButton buttonBossbarBar;

    private String previousBossName;
    private int previousStatusBarTime;
    private float previousHealthScale;


    private boolean dragging = false;
    private boolean updated = false;
    private int lastMouseX;
    private int lastMouseY;


    @Override
    public void initGui() {
        buttonList.clear();
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
                "Bossbar Health Bar" + getSuffix(BossbarConfig.BOSSBAR_BAR)));
        buttonList.add(new GuiButton(3, width / 2 - 155 / 2, calculateHeight(2), 155, 20, "Reset"));
        buttonList.add(new GuiSlider(4, width / 2 + 6, calculateHeight(1) + 1, 150, 20, "Scale: ", "", .25, 2, Math.round(BossbarConfig.SCALE * 10D) / 10D, true, true, slider -> {
            BossbarConfig.SCALE = slider.getValue();
        }));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawCenteredString(mc.fontRendererObj, "BossbarCustomizer " + BossbarMod.VERSION, width / 2, calculateHeight(-1), -1);
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
                buttonBossbarBar.displayString = "Bossbar Health Bar" + getSuffix(BossbarConfig.BOSSBAR_BAR);
                break;

            case 3:
                BossbarConfig.BOSSBAR_ALL = true;
                BossbarConfig.BOSSBAR_TEXT = true;
                BossbarConfig.BOSSBAR_BAR = true;
                BossbarConfig.BOSSBAR_X = .5;
                BossbarConfig.BOSSBAR_Y = .05;
                BossbarConfig.SCALE = 1.0;
                initGui();
                break;
        }
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (this.dragging) {
            BossbarConfig.BOSSBAR_X = (BossbarConfig.BOSSBAR_X * width + (mouseX - this.lastMouseX)) / (double) width;
            BossbarConfig.BOSSBAR_Y = (BossbarConfig.BOSSBAR_Y * height + (mouseY - this.lastMouseY)) / (double) height;
            if (BossbarConfig.BOSSBAR_X * width - (182 * BossbarConfig.SCALE / 2) < 0)
                BossbarConfig.BOSSBAR_X = (182 * BossbarConfig.SCALE / 2) / (double) width;

            if (BossbarConfig.BOSSBAR_X * width + (182 * BossbarConfig.SCALE / 2) > width)
                BossbarConfig.BOSSBAR_X = ((width - (182 * BossbarConfig.SCALE / 2)) / (double) width);

            if (BossbarConfig.BOSSBAR_Y * height - 10 < 0)
                BossbarConfig.BOSSBAR_Y = 10 / (double) height;
            if (BossbarConfig.BOSSBAR_Y * height + 5 > height)
                BossbarConfig.BOSSBAR_Y = (height - 5) / (double) height;
            this.lastMouseX = mouseX;
            this.lastMouseY = mouseY;
            this.updated = true;
        }
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) throws IOException {
        super.mouseClicked(mouseX, mouseY, button);
        if (button == 0) {
            if (!BossbarConfig.BOSSBAR_ALL) {
                return;
            }
            int startX = (int) ((BossbarConfig.BOSSBAR_X * width) - (182 / 2 * BossbarConfig.SCALE));
            int startY = (int) (BossbarConfig.BOSSBAR_Y * height) - 10;
            int endX = (int) (startX + 182 * BossbarConfig.SCALE);
            int endY = (int) (startY + (15 * BossbarConfig.SCALE));
            if (mouseX >= startX && mouseX <= endX && mouseY >= startY && mouseY <= endY) {
                this.dragging = true;
                this.lastMouseX = mouseX;
                this.lastMouseY = mouseY;
            }
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        this.dragging = false;

    }

    @Override
    public void onGuiClosed() {
        BossbarMod.INSTANCE.getBossbarConfig().markDirty();
        BossbarMod.INSTANCE.getBossbarConfig().writeData();

        BossStatus.bossName = previousBossName;
        BossStatus.statusBarTime = previousStatusBarTime;
        BossStatus.healthScale = previousHealthScale;
        super.onGuiClosed();
    }

    private String getSuffix(boolean option) {
        return ": " + (option ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled");
    }

    private int calculateHeight(int row) {
        return 55 + row * 23;
    }
}
