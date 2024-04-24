package me.pajic.affogatotweaks.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.lucaargolo.seasons.FabricSeasons;
import io.github.lucaargolo.seasons.utils.Season;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Shadow @Final private Minecraft minecraft;

    // Renders a HUD overlay when holding a clock, showing the current day, time of day, and season.
    // Intentionally made to look similar to Charm's Compass Overlay feature.
    @Inject(method = "render", at = @At("TAIL"))
    private void renderClockOverlayHud(GuiGraphics context, float tickDelta, CallbackInfo ci) {
        if (minecraft.player != null && minecraft.level != null) {
            if (minecraft.player.getOffhandItem().getItem() == Items.CLOCK || minecraft.player.getMainHandItem().getItem() == Items.CLOCK) {

                Font textRenderer = Minecraft.getInstance().gui.getFont();

                String day = I18n.get("gui.affogatotweaks.day", minecraft.level.getDayTime() / 24000L);
                long timeOffset = (minecraft.level.getDayTime() + 6000) % 24000;
                String time = I18n.get("gui.affogatotweaks.time", timeOffset / 1000, String.format("%02d", (int) ((double) (timeOffset / 10 % 100) / 100 * 60)));

                int dayColor = 0xFFEEDD;
                int timeColor = 0xAA9988;
                int alpha = 220 << 24 & 0xFF000000;

                float x = Minecraft.getInstance().gui.screenWidth / 2.0F;
                float y = 40;

                int dayLength = textRenderer.width(day);
                int timeLength = textRenderer.width(time);
                int dayOffsetX = -dayLength / 2;
                int timeOffsetX = -timeLength / 2;

                Season currentSeason = FabricSeasons.getCurrentSeason(minecraft.level, true);

                renderText(context, textRenderer, day, x, y, dayOffsetX, dayColor | alpha);
                y += 12;

                renderText(context, textRenderer, time, x, y, timeOffsetX, timeColor | alpha);
                y += 12;

                if (currentSeason == Season.SPRING) {
                    String season = I18n.get("gui.affogatotweaks.season.spring");
                    int seasonLength = textRenderer.width(season);
                    int seasonColor = 0x42F55A;
                    int seasonOffsetX = -seasonLength / 2;
                    renderText(context, textRenderer, season, x, y, seasonOffsetX, seasonColor | alpha);
                }
                else if (currentSeason == Season.SUMMER) {
                    String season = I18n.get("gui.affogatotweaks.season.summer");
                    int seasonLength = textRenderer.width(season);
                    int seasonColor = 0xF2F542;
                    int seasonOffsetX = -seasonLength / 2;
                    renderText(context, textRenderer, season, x, y, seasonOffsetX, seasonColor | alpha);
                }
                else if (currentSeason == Season.FALL) {
                    String season = I18n.get("gui.affogatotweaks.season.fall");
                    int seasonLength = textRenderer.width(season);
                    int seasonColor = 0xF57542;
                    int seasonOffsetX = -seasonLength / 2;
                    renderText(context, textRenderer, season, x, y, seasonOffsetX, seasonColor | alpha);
                }
                else if (currentSeason == Season.WINTER) {
                    String season = I18n.get("gui.affogatotweaks.season.winter");
                    int seasonLength = textRenderer.width(season);
                    int seasonColor = 0x42F5F5;
                    int seasonOffsetX = -seasonLength / 2;
                    renderText(context, textRenderer, season, x, y, seasonOffsetX, seasonColor | alpha);
                }
            }
        }
    }

    @Unique
    private void renderText(GuiGraphics drawContext, Font textRenderer, String text, float x, float y, int offsetX, int color) {
        PoseStack matrixStack = drawContext.pose();
        matrixStack.pushPose();
        matrixStack.translate(x, y, 0.0);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        drawContext.drawString(textRenderer, text, offsetX, 0, color);
        RenderSystem.disableBlend();
        matrixStack.popPose();
    }
}
