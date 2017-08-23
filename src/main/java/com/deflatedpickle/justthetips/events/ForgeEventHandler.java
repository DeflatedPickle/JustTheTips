package com.deflatedpickle.justthetips.events;

import com.deflatedpickle.justthetips.JustTheTips;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ForgeEventHandler {
    private String tip;
    private Integer tipIndex;

    @SubscribeEvent
    public void onGuiScreenEventDrawScreenEvent(GuiScreenEvent.DrawScreenEvent event) {
        // if (event.getGui() instanceof GuiDownloadTerrain || event.getGui() instanceof GuiScreenWorking){
            event.getGui().drawString(Minecraft.getMinecraft().fontRendererObj, TextFormatting.BOLD.toString() + TextFormatting.YELLOW.toString() + "Tip #" + tipIndex + ":", 5, event.getGui().height - 50, 0xFFFFFF);
            event.getGui().drawString(Minecraft.getMinecraft().fontRendererObj, TextFormatting.WHITE + tip, 5, event.getGui().height - 40, 0xFFFFFF);
        // }
    }

    @SubscribeEvent
    public void onGuiScreenEventInitGuiEvent(GuiScreenEvent.InitGuiEvent event) {
        tip = JustTheTips.tipList.get(JustTheTips.random.nextInt(JustTheTips.tipList.size()));
        tipIndex = JustTheTips.tipList.indexOf(tip);
    }
}
