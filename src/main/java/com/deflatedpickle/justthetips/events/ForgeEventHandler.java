package com.deflatedpickle.justthetips.events;

import com.deflatedpickle.justthetips.JustTheTips;
import com.deflatedpickle.justthetips.utils.TipUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiScreenWorking;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ForgeEventHandler {
    public static String tip;
    public static Integer tipIndex;

    @SubscribeEvent
    public void onGuiScreenEventInitGuiEvent(GuiScreenEvent.InitGuiEvent event) {
        if (JustTheTips.tipList.size() > 0) {
            tip = JustTheTips.tipList.get(JustTheTips.random.nextInt(JustTheTips.tipList.size()));
        } else {
            tip = I18n.format("tip.error.name");
        }
        tipIndex = JustTheTips.tipList.indexOf(tip);
    }

    @SubscribeEvent
    public void onGuiScreenEventDrawScreenEvent(GuiScreenEvent.DrawScreenEvent event) {
        if (event.getGui() instanceof GuiDownloadTerrain || event.getGui() instanceof GuiScreenWorking){
            TipUtil.drawTips(Minecraft.getMinecraft().fontRendererObj);
        }
    }
}
