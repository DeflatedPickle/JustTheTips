package com.deflatedpickle.justthetips.proxy;

import com.deflatedpickle.justthetips.JustTheTips;
import com.deflatedpickle.justthetips.events.ForgeEventHandler;
import com.deflatedpickle.justthetips.events.ScreenHook;
import com.deflatedpickle.justthetips.utils.TipUtil;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy implements CommonProxy {
    @Override
    public void init() {
        TipUtil.loadTips(JustTheTips.tipList);
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
        MinecraftForge.EVENT_BUS.register(ScreenHook.class);
    }
}
