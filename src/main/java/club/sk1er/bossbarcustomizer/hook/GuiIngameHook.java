package club.sk1er.bossbarcustomizer.hook;

import club.sk1er.bossbarcustomizer.config.BossbarConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.boss.BossStatus;

public class GuiIngameHook extends Gui {

    private static GuiIngame guiIngame = new GuiIngame(Minecraft.getMinecraft());

    @SuppressWarnings("unused")
    public static void renderBossbarHealth() {
        if (BossStatus.bossName != null && BossStatus.statusBarTime > 0 && BossbarConfig.BOSSBAR_ALL) {
            --BossStatus.statusBarTime;

            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
            ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
            int scaledWidth = resolution.getScaledWidth();
            String bossName = BossStatus.bossName;

            if (BossbarConfig.BOSSBAR_TEXT) {
                if (BossbarConfig.BOSSBAR_X != -1) {
                    fontRenderer.drawStringWithShadow(bossName, (float) (BossbarConfig.BOSSBAR_X + 91 - fontRenderer.getStringWidth(bossName) / 2),
                            BossbarConfig.BOSSBAR_Y - 10, -1);
                } else {
                    fontRenderer.drawStringWithShadow(bossName, (float) (scaledWidth / 2 - fontRenderer.getStringWidth(bossName) / 2), BossbarConfig.BOSSBAR_Y - 10, -1);
                }

                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.icons);
            }

            int widthLocation = 182;

            if (BossbarConfig.BOSSBAR_BAR) {
                int healthScale = (int) (BossStatus.healthScale * (float) (widthLocation + 1));

                if (BossbarConfig.BOSSBAR_X != -1) {
                    guiIngame.drawTexturedModalRect(BossbarConfig.BOSSBAR_X, BossbarConfig.BOSSBAR_Y, 0, 74, widthLocation, 5);

                    if (healthScale > 0) {
                        guiIngame.drawTexturedModalRect(BossbarConfig.BOSSBAR_X, BossbarConfig.BOSSBAR_Y, 0, 79, widthLocation, 5);
                    }
                } else {
                    int xLocation = scaledWidth / 2 - widthLocation / 2;

                    guiIngame.drawTexturedModalRect(xLocation, BossbarConfig.BOSSBAR_Y, 0, 74, widthLocation, 5);

                    if (healthScale > 0) {
                        guiIngame.drawTexturedModalRect(xLocation, BossbarConfig.BOSSBAR_Y, 0, 79, widthLocation, 5);
                    }
                }
            }

            if (BossbarConfig.BOSSBAR_TEXT) {
                if (BossbarConfig.BOSSBAR_X != -1) {
                    fontRenderer.drawStringWithShadow(bossName, (float) (BossbarConfig.BOSSBAR_X + widthLocation / 2 - fontRenderer.getStringWidth(bossName) / 2), BossbarConfig.BOSSBAR_Y - 10, -1);
                } else {
                    fontRenderer.drawStringWithShadow(bossName, (float) (scaledWidth / 2 - fontRenderer.getStringWidth(bossName) / 2), BossbarConfig.BOSSBAR_Y - 10, -1);
                }
            }
        }
    }
}
