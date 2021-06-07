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
    @CapabilityInject(IWorldCapbilityManager.class)
    public static Capability<IWorldCapbilityManager> INSTANCE = null;

    public static void register(){
        CapabilityManager.INSTANCE.register(IWorldCapbilityManager.class, new Capability.IStorage<IWorldCapbilityManager>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IWorldCapbilityManager> capability, IWorldCapbilityManager instance, Direction side) {
                CompoundNBT tag = new CompoundNBT();
                tag.putBoolean("blackeye", instance.getBlackEye());
                tag.putBoolean("cannon", instance.getCannon());
                tag.putBoolean("venom", instance.getVenom());
                tag.putBoolean("shift", instance.getShift());
                tag.putBoolean("karmicjustice", instance.getKarmicJustice());
                tag.putBoolean("aging", instance.getAging());
                tag.putBoolean("softskin", instance.getSoftSkin());
                tag.putBoolean("fatigue", instance.getFatigue());
                return tag;
            }

            @Override
            public void readNBT(Capability<IWorldCapbilityManager> capability, IWorldCapbilityManager instance, Direction side, INBT nbt) {
                CompoundNBT tag = (CompoundNBT) nbt;
                instance.setAging(tag.getBoolean("aging"));
                instance.setBlackEye(tag.getBoolean("blackeye"));
                instance.setCannon(tag.getBoolean("cannon"));
                instance.setFatigue(tag.getBoolean("fatigue"));
                instance.setKarmicJustice(tag.getBoolean("karmicjustice"));
                instance.setShift(tag.getBoolean("shift"));
                instance.setSoftSkin(tag.getBoolean("softskin"));
                instance.setVenom(tag.getBoolean("venom"));
            }
        }, new Callable<WorldCapabilityManager>(){
            @Override
            public WorldCapabilityManager call() throws Exception {
                return new WorldCapabilityManager();
            }
        });
    }
}
