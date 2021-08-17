package com.cartoonishvillain.vdm.Capabilities.WorldCapabilities;

import com.cartoonishvillain.vdm.Capabilities.EntityCapabilities.EntityCapability;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class WorldCapabilityManager implements IWorldCapability, ICapabilityProvider, INBTSerializable<CompoundNBT> {
    protected boolean isNight = false;
    protected boolean celebration = false;
    public final LazyOptional<IWorldCapability> holder = LazyOptional.of(()->this);


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nonnull Direction side) {
        if(cap == WorldCapability.INSTANCE){ return WorldCapability.INSTANCE.orEmpty(cap, this.holder); }
        else return LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putBoolean("celebration", celebration);
        tag.putBoolean("night", isNight);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        celebration = nbt.getBoolean("celebration");
        isNight = nbt.getBoolean("night");
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
        return isNight;
    }

    @Override
    public void setisNight(boolean status) {
        isNight = status;
    }
}
