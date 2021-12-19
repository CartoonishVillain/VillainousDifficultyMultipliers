package com.cartoonishvillain.vdm.events;

import com.cartoonishvillain.vdm.capabilities.entitycapabilities.EntityCapability;
import com.cartoonishvillain.vdm.capabilities.entitycapabilities.IEntityCapability;
import com.cartoonishvillain.vdm.capabilities.playercapabilities.IPlayerCapability;
import com.cartoonishvillain.vdm.capabilities.playercapabilities.PlayerCapability;
import com.cartoonishvillain.vdm.capabilities.worldcapabilities.IWorldCapability;
import com.cartoonishvillain.vdm.VDM;
import com.cartoonishvillain.vdm.capabilities.worldcapabilities.WorldCapability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = VDM.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {
    @SubscribeEvent
    public static void CapabilityRegister(final RegisterCapabilitiesEvent event){
        event.register(IEntityCapability.class);
        event.register(IPlayerCapability.class);
        event.register(IWorldCapability.class);
        EntityCapability.INSTANCE = CapabilityManager.get(new CapabilityToken<IEntityCapability>() {});
        WorldCapability.INSTANCE = CapabilityManager.get(new CapabilityToken<IWorldCapability>() {});
        PlayerCapability.INSTANCE = CapabilityManager.get(new CapabilityToken<IPlayerCapability>() {});
    }

}
