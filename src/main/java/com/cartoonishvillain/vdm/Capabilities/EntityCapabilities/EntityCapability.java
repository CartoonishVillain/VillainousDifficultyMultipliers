package com.cartoonishvillain.vdm.Capabilities.EntityCapabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class EntityCapability {
    @CapabilityInject(IEntityCapability.class)
    public static Capability<IEntityCapability> INSTANCE = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IEntityCapability.class);
    }
}
