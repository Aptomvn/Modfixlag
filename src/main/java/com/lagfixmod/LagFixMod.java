package com.lagfixmod;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(LagFixMod.MOD_ID)
public class LagFixMod {

    public static final String MOD_ID = "lagfixmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public LagFixMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        MinecraftForge.EVENT_BUS.register(new PerformanceEvents());
        LOGGER.info("[LagFixMod] Da khoi dong thanh cong - dang toi uu hieu nang the gioi.");
    }
}
