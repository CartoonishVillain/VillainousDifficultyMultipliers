package com.cartoonishvillain.vdm.Events;

import com.cartoonishvillain.vdm.Capabilities.WorldCapabilities.WorldCapabilityManager;
import com.cartoonishvillain.vdm.VDM;
import com.cartoonishvillain.vdm.Commands.CommandManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = VDM.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SubscribeEvent
    public static void serverLoad(RegisterCommandsEvent event){
        CommandManager.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void worldRegister(AttachCapabilitiesEvent<World> event){
        WorldCapabilityManager provider = new WorldCapabilityManager();
        event.addCapability(new ResourceLocation(VDM.MODID, "multipliersenabled"), provider);
    }
}
