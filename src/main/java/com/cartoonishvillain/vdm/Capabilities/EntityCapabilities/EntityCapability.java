package com.cartoonishvillain.vdm.Capabilities.EntityCapabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;

public class EntityCapability {
    @CapabilityInject(IEntityCapability.class)
    public static Capability<IEntityCapability> INSTANCE = null;

    public static void register(){
        CapabilityManager.INSTANCE.register(IEntityCapability.class, new Capability.IStorage<IEntityCapability>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IEntityCapability> capability, IEntityCapability instance, Direction side) {
                CompoundNBT tag = new CompoundNBT();
                tag.putBoolean("retaliation", instance.getRetaliationStatus());
                tag.putInt("age", instance.getAge());
                return tag;
            }

            @Override
            public void readNBT(Capability<IEntityCapability> capability, IEntityCapability instance, Direction side, INBT nbt) {
                CompoundNBT tag = (CompoundNBT) nbt;
                instance.setRetaliationStatus(tag.getBoolean("retaliation"));
                instance.setAge(tag.getInt("age"));
            }
        }, new Callable<EntityCapabilityManager>(){
            @Override
            public EntityCapabilityManager call() throws Exception {
                return new EntityCapabilityManager();
            }

        });
    }
}
