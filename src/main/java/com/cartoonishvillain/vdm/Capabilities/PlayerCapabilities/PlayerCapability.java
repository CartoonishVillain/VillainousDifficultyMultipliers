package com.cartoonishvillain.vdm.Capabilities.PlayerCapabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;

public class PlayerCapability {
    @CapabilityInject(IPlayerCapability.class)
    public static Capability<IPlayerCapability> INSTANCE = null;

    public static void register(){
        CapabilityManager.INSTANCE.register(IPlayerCapability.class, new Capability.IStorage<IPlayerCapability>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IPlayerCapability> capability, IPlayerCapability instance, Direction side) {
                CompoundNBT tag = new CompoundNBT();
                tag.putBoolean("blackeyestatus", instance.getBlackEyeStatus());
                tag.putFloat("kineticbuildup", instance.getKineticBuildup());
                tag.putInt("shout", instance.getShoutTicks());
                return tag;
            }

            @Override
            public void readNBT(Capability<IPlayerCapability> capability, IPlayerCapability instance, Direction side, INBT nbt) {
                CompoundNBT tag = (CompoundNBT) nbt;
                instance.setBlackEyeStatus(tag.getBoolean("blackeyestatus"));
                instance.setKineticBuildup(tag.getFloat("kineticbuildup"));
                instance.setShoutTicks(tag.getInt("shout"));
            }
        }, new Callable<PlayerCapabilityManager>(){
            @Override
            public PlayerCapabilityManager call() throws Exception {
                return new PlayerCapabilityManager();
            }

        });
    }
}
