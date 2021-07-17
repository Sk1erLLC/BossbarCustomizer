package club.sk1er.bossbarcustomizer.hook;

import club.sk1er.bossbarcustomizer.config.BossbarConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.boss.BossStatus;
import net.minecraftforge.client.GuiIngameForge;

@SuppressWarnings("unused")
public class GuiIngameHook extends Gui {

    private static final GuiIngame guiIngame = new GuiIngame(Minecraft.getMinecraft());

    @SuppressWarnings("unused")
    public static void renderBossbarHealth() {
        if (BossStatus.bossName != null && BossStatus.statusBarTime > 0 && BossbarConfig.BOSSBAR_ALL && GuiIngameForge.renderBossHealth) {
            --BossStatus.statusBarTime;

            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
            ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
            double scaledWidth = resolution.getScaledWidth();
            double scaledHeight = resolution.getScaledHeight();

            if (BossbarConfig.BOSSBAR_BAR) {
                int widthLocation = 182;
                int healthScale = (int) (BossStatus.healthScale * (float) (widthLocation + 1));

                GlStateManager.pushMatrix();
                GlStateManager.translate((BossbarConfig.BOSSBAR_X * scaledWidth) - ((widthLocation / 2F) * BossbarConfig.SCALE), BossbarConfig.BOSSBAR_Y * scaledHeight, 0);
                GlStateManager.scale(BossbarConfig.SCALE, BossbarConfig.SCALE, BossbarConfig.SCALE);

                guiIngame.drawTexturedModalRect(0, 0, 0, 74, widthLocation, 5);
                guiIngame.drawTexturedModalRect(0, 0, 0, 74, widthLocation, 5);

                if (healthScale > 0) {
                    guiIngame.drawTexturedModalRect(0, 0, 0, 79, healthScale, 5);
                }

                GlStateManager.popMatrix();
            }

            if (BossbarConfig.BOSSBAR_TEXT) {
                GlStateManager.pushMatrix();
                GlStateManager.translate(scaledWidth * BossbarConfig.BOSSBAR_X, scaledHeight * BossbarConfig.BOSSBAR_Y - (10D * BossbarConfig.SCALE), 0);
                GlStateManager.scale(BossbarConfig.SCALE, BossbarConfig.SCALE, BossbarConfig.SCALE);
                GlStateManager.translate(-fontRenderer.getStringWidth(BossStatus.bossName) / 2F, 0, 0);
                fontRenderer.drawStringWithShadow(BossStatus.bossName, 0, 0, -1);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.icons);
                GlStateManager.popMatrix();
            }
        }
    }
}
