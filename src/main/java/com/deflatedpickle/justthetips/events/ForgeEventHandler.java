package com.deflatedpickle.justthetips.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiScreenWorking;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ForgeEventHandler {
    @SubscribeEvent
    public void onGuiScreenEventDrawScreenEvent(GuiScreenEvent.DrawScreenEvent event) {
        if (event.getGui() instanceof GuiDownloadTerrain || event.getGui() instanceof GuiScreenWorking){
            event.getGui().drawString(Minecraft.getMinecraft().fontRendererObj, TextFormatting.YELLOW + "Tip:", 1, event.getGui().height - 50, 0xFFFFFF);
            event.getGui().drawString(Minecraft.getMinecraft().fontRendererObj, TextFormatting.WHITE + "I'm a cool tip.", 1, event.getGui().height - 40, 0xFFFFFF);
        }
    }
}
