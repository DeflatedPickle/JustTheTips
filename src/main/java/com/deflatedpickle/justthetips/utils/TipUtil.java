package com.deflatedpickle.justthetips.utils;

import com.deflatedpickle.justthetips.JustTheTips;
import com.deflatedpickle.justthetips.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import java.io.InputStream;
import java.util.List;

public class TipUtil {
    public static void loadTips(List<String> list) {
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
}
