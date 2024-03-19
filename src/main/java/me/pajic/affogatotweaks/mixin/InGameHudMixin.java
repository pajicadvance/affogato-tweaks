package me.pajic.affogatotweaks.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.lucaargolo.seasons.FabricSeasons;
import io.github.lucaargolo.seasons.utils.Season;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Shadow @Final private MinecraftClient client;

    // Renders a HUD overlay when holding a clock, showing the current day, time of day, and season.
    // Intentionally made to look similar to Charm's Compass Overlay feature.
    @Inject(method = "render", at = @At("TAIL"))
    private void renderClockOverlayHud(DrawContext context, float tickDelta, CallbackInfo ci) {
        if (client.player != null && client.world != null) {
            if (client.player.getOffHandStack().getItem() == Items.CLOCK || client.player.getMainHandStack().getItem() == Items.CLOCK) {

                TextRenderer textRenderer = MinecraftClient.getInstance().inGameHud.getTextRenderer();

                String day = I18n.translate("gui.affogatotweaks.day", client.world.getTimeOfDay() / 24000L);
                long timeOffset = (client.world.getTimeOfDay() + 6000) % 24000;
                String time = I18n.translate("gui.affogatotweaks.time", timeOffset / 1000, String.format("%02d", (int) ((double) (timeOffset / 10 % 100) / 100 * 60)));

                int dayColor = 0xFFEEDD;
                int timeColor = 0xAA9988;
                int alpha = 220 << 24 & 0xFF000000;

                float x = MinecraftClient.getInstance().inGameHud.scaledWidth / 2.0F;
                float y = 40;

                int dayLength = textRenderer.getWidth(day);
                int timeLength = textRenderer.getWidth(time);
                int dayOffsetX = -dayLength / 2;
                int timeOffsetX = -timeLength / 2;

                Season currentSeason = FabricSeasons.getCurrentSeason(client.world, true);

                renderText(context, textRenderer, day, x, y, dayOffsetX, dayColor | alpha);
                y += 12;

                renderText(context, textRenderer, time, x, y, timeOffsetX, timeColor | alpha);
                y += 12;

                if (currentSeason == Season.SPRING) {
                    String season = I18n.translate("gui.affogatotweaks.season.spring");
                    int seasonLength = textRenderer.getWidth(season);
                    int seasonColor = 0x42F55A;
                    int seasonOffsetX = -seasonLength / 2;
                    renderText(context, textRenderer, season, x, y, seasonOffsetX, seasonColor | alpha);
                }
                else if (currentSeason == Season.SUMMER) {
                    String season = I18n.translate("gui.affogatotweaks.season.summer");
                    int seasonLength = textRenderer.getWidth(season);
                    int seasonColor = 0xF2F542;
                    int seasonOffsetX = -seasonLength / 2;
                    renderText(context, textRenderer, season, x, y, seasonOffsetX, seasonColor | alpha);
                }
                else if (currentSeason == Season.FALL) {
                    String season = I18n.translate("gui.affogatotweaks.season.fall");
                    int seasonLength = textRenderer.getWidth(season);
                    int seasonColor = 0xF57542;
                    int seasonOffsetX = -seasonLength / 2;
                    renderText(context, textRenderer, season, x, y, seasonOffsetX, seasonColor | alpha);
                }
                else if (currentSeason == Season.WINTER) {
                    String season = I18n.translate("gui.affogatotweaks.season.winter");
                    int seasonLength = textRenderer.getWidth(season);
                    int seasonColor = 0x42F5F5;
                    int seasonOffsetX = -seasonLength / 2;
                    renderText(context, textRenderer, season, x, y, seasonOffsetX, seasonColor | alpha);
                }
            }
        }
    }

    @Unique
    private void renderText(DrawContext drawContext, TextRenderer textRenderer, String text, float x, float y, int offsetX, int color) {
        MatrixStack matrixStack = drawContext.getMatrices();
        matrixStack.push();
        matrixStack.translate(x, y, 0.0);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        drawContext.drawTextWithShadow(textRenderer, text, offsetX, 0, color);
        RenderSystem.disableBlend();
        matrixStack.pop();
    }
}
