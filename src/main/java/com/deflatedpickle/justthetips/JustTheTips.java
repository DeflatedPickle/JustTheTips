package com.deflatedpickle.justthetips;

import com.deflatedpickle.justthetips.utils.TipUtil;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, clientSideOnly = true, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS, dependencies = "after:*")
public class JustTheTips {

    public static final Logger logger = LogManager.getLogger(Reference.NAME);
    public static final Random random = new Random();

    public static final List<String> tipList = new ArrayList<>();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        TipUtil.loadTips(tipList, new File(event.getModConfigurationDirectory(), "tips.txt"));
    }

    @EventHandler
    public void onImcMessage(FMLInterModComms.IMCEvent event) {
        for (FMLInterModComms.IMCMessage message : event.getMessages()) {
            if (!message.isStringMessage())
                continue;

            if (message.key.equalsIgnoreCase("add_tip") && !tipList.contains(message.getStringValue()))
                tipList.add(message.getStringValue());
        }
    }
}
