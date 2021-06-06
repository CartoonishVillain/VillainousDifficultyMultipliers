package com.cartoonishvillain.vdm.Events;

import com.cartoonishvillain.vdm.VDM;
import com.cartoonishvillain.vdm.commands.CommandManager;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = VDM.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SubscribeEvent
    public static void serverLoad(RegisterCommandsEvent event){
        CommandManager.register(event.getDispatcher());
    }
}
