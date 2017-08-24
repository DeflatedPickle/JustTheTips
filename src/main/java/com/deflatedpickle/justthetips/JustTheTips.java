package com.deflatedpickle.justthetips;

import com.deflatedpickle.justthetips.proxy.CommonProxy;

import com.deflatedpickle.justthetips.utils.TipUtil;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS, dependencies = "after:*")
public class JustTheTips {
    @Instance
    public static JustTheTips instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    public static final Logger logger = LogManager.getLogger(Reference.NAME);
    public static final Random random = new Random();

    public static final List<String> tipList = new ArrayList<String>();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger.info("Starting preInit.");
        logger.info("Finished preInit.");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("Starting Init.");
        proxy.init();
        logger.info("Finished Init.");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        logger.info("Starting postInit.");

        logger.info("Finished postInit.");
    }
}
