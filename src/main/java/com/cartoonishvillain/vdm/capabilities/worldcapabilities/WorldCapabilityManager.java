package com.cartoonishvillain.vdm.capabilities.worldcapabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class WorldCapabilityManager implements IWorldCapability, ICapabilityProvider, INBTSerializable<CompoundTag> {
    public final LazyOptional<IWorldCapability> holder = LazyOptional.of(()->this);
    protected boolean celebration = false;
    protected boolean night = false;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nonnull Direction side) {
        if(cap == WorldCapability.INSTANCE){ return WorldCapability.INSTANCE.orEmpty(cap, this.holder); }
        else return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("celebration", celebration);
        tag.putBoolean("night", night);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        celebration = nbt.getBoolean("celebration");
        night = nbt.getBoolean("night");
    }

    @Override
    public boolean getCelebrationStatus() {
        return celebration;
    }

    @Override
    public void setCelebrationStatus(boolean status) {
        celebration = status;
    }

    @Override
    public boolean isNight() {
        return night;
    }

    @Override
    public void setisNight(boolean status) {
        night = status;
    }
}
