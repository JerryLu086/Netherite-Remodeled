package com.jerrylu086.netherite_remodeled;

import com.jerrylu086.netherite_remodeled.client.model.armor.HornedHelmetModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(NetheriteRemodeled.MOD_ID)
public class NetheriteRemodeled {
    public static final String MOD_ID = "netherite_remodeled";
    public static final Logger LOGGER = LogManager.getLogger();

    public static final ModelLayerLocation HORNED_HELMET_LOCATION = new ModelLayerLocation(NetheriteRemodeled.getPath("horned_helmet"), "main");

    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HORNED_HELMET_LOCATION, HornedHelmetModel::createLayer);
    }

    public NetheriteRemodeled() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            modEventBus.addListener(NetheriteRemodeled::registerModelLayers);
        });

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation getPath(String path) {
        return new ResourceLocation(NetheriteRemodeled.MOD_ID, path);
    }
}