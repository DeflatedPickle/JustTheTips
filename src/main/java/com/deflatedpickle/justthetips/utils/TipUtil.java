package com.deflatedpickle.justthetips.utils;

import com.deflatedpickle.justthetips.JustTheTips;
import com.deflatedpickle.justthetips.events.ForgeEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TipUtil {

    public static void loadTips(List<String> list, File configFile) {
        try {
            // Create our default tip list if it doesn't exist
            if (!configFile.exists())
                FileUtils.copyInputStreamToFile(TipUtil.class.getResourceAsStream("/assets/justthetips/tips.txt"), configFile);

            // Add all lines from the config file to the list
            list.addAll(FileUtils.readLines(configFile, StandardCharsets.UTF_8));
        } catch (IOException exception) {
            JustTheTips.logger.info("Could not load the tips: {}", exception.getMessage());
        }
    }

    public static void drawTips(FontRenderer fontRenderer){
        fontRenderer.drawString(TextFormatting.BOLD.toString() + TextFormatting.YELLOW.toString() + I18n.format("tip.title.name", ForgeEventHandler.tipIndex + 1), 5, new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight() - 50, 0xFFFFFF);
        fontRenderer.drawSplitString(TextFormatting.WHITE + ForgeEventHandler.tip, 5, new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight() - 40, new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth() - 5, 0xFFFFFF);
    }
}
