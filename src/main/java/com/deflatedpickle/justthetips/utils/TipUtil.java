package com.deflatedpickle.justthetips.utils;

import com.deflatedpickle.justthetips.JustTheTips;
import com.deflatedpickle.justthetips.Reference;
import com.deflatedpickle.justthetips.events.ForgeEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import java.io.InputStream;
import java.util.List;

public class TipUtil {
    public static void loadTips(List<String> list) {
        // TODO: Look for tips.txt in all resource domains so that mods may add their own tips.
        try {
            InputStream inputStream = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(Reference.MOD_ID + ":texts/tips.txt")).getInputStream();

            for (LineIterator lineIterator = IOUtils.lineIterator(inputStream, Charsets.UTF_8); lineIterator.hasNext(); ) {
                String line = lineIterator.next();
                list.add(line);
            }

        } catch (java.io.IOException exception) {
            JustTheTips.logger.info("Could not load the tips.");
        }
    }

    public static void drawTips(FontRenderer fontRenderer){
        fontRenderer.drawString(TextFormatting.BOLD.toString() + TextFormatting.YELLOW.toString() + I18n.format("tip.title.name") + " #" + (ForgeEventHandler.tipIndex + 1) + ":", 5, new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight() - 50, 0xFFFFFF);
        fontRenderer.drawSplitString(TextFormatting.WHITE + ForgeEventHandler.tip, 5, new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight() - 40, new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth() - 5, 0xFFFFFF);
    }
}
