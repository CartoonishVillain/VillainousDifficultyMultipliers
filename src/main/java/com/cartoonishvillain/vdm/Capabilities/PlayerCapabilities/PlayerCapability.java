package com.cartoonishvillain.vdm.Capabilities.PlayerCapabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class PlayerCapability {
    @CapabilityInject(IPlayerCapability.class)
    public static Capability<IPlayerCapability> INSTANCE = null;

    public static void register(){
        CapabilityManager.INSTANCE.register(IPlayerCapability.class);
    }
}
