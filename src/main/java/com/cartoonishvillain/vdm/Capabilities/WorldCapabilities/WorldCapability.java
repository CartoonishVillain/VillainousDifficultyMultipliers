package com.cartoonishvillain.vdm.Capabilities.WorldCapabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class WorldCapability {
    @CapabilityInject(IWorldCapability.class)
    public static Capability<IWorldCapability> INSTANCE = null;

}
