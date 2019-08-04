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
            double scaledWidth = resolution.getScaledWidth();
            double scaledHeight = resolution.getScaledHeight();

            String bossName = BossStatus.bossName;

            if (BossbarConfig.BOSSBAR_TEXT) {
                GlStateManager.pushMatrix();
                GlStateManager.translate(scaledWidth * BossbarConfig.BOSSBAR_X, scaledHeight * BossbarConfig.BOSSBAR_Y - (10D * BossbarConfig.SCALE), 0);
                GlStateManager.scale(BossbarConfig.SCALE, BossbarConfig.SCALE, BossbarConfig.SCALE);
                GlStateManager.translate(-fontRenderer.getStringWidth(bossName) / 2F , 0, 0);
                fontRenderer.drawStringWithShadow(bossName, 0, 0, -1);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.icons);
                GlStateManager.popMatrix();
            }

            int widthLocation = 182;

            if (BossbarConfig.BOSSBAR_BAR) {
                int healthScale = (int) (BossStatus.healthScale * (float) (widthLocation + 1) * BossbarConfig.SCALE);

                GlStateManager.pushMatrix();
                GlStateManager.translate(BossbarConfig.BOSSBAR_X * scaledWidth - widthLocation / 2F * BossbarConfig.SCALE, BossbarConfig.BOSSBAR_Y * scaledHeight , 0);
                GlStateManager.scale(BossbarConfig.SCALE, BossbarConfig.SCALE, BossbarConfig.SCALE);

                guiIngame.drawTexturedModalRect(0, 0, 0, 74, widthLocation, 5);
                if (healthScale > 0) {
                    guiIngame.drawTexturedModalRect(0, 0, 0, 79, widthLocation, 5);
                }
                GlStateManager.popMatrix();
            }

        }
    }
}
