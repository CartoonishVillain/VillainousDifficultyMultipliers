package com.cartoonishvillain.vdm.Capabilities.WorldCapabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;

public class WorldCapability {
    @CapabilityInject(IWorldCapability.class)
    public static Capability<IWorldCapability> INSTANCE = null;

    public static void register(){
        CapabilityManager.INSTANCE.register(IWorldCapability.class, new Capability.IStorage<IWorldCapability>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IWorldCapability> capability, IWorldCapability instance, Direction side) {
                CompoundNBT tag = new CompoundNBT();
                tag.putBoolean("celebration", instance.getCelebrationStatus());
                tag.putBoolean("night", instance.isNight());
                return tag;
            }

            @Override
            public void readNBT(Capability<IWorldCapability> capability, IWorldCapability instance, Direction side, INBT nbt) {
                CompoundNBT tag = (CompoundNBT) nbt;
                instance.setisNight(tag.getBoolean("night"));
                instance.setCelebrationStatus(tag.getBoolean("celebration"));

            }
        }, new Callable<WorldCapabilityManager>(){
            @Override
            public WorldCapabilityManager call() throws Exception {
                return new WorldCapabilityManager();
            }

        });
    }
}
