package com.cartoonishvillain.vdm.Events;

import com.cartoonishvillain.vdm.Capabilities.EntityCapabilities.IEntityCapability;
import com.cartoonishvillain.vdm.Capabilities.PlayerCapabilities.IPlayerCapability;
import com.cartoonishvillain.vdm.Capabilities.WorldCapabilities.IWorldCapability;
import com.cartoonishvillain.vdm.VDM;
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
    }

}
