package com.cartoonishvillain.vdm.Capabilities.PlayerCapabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class PlayerCapabilityManager implements IPlayerCapability, ICapabilityProvider, INBTSerializable<CompoundNBT> {
    protected boolean blackEye = false;
    public final LazyOptional<IPlayerCapability> holder = LazyOptional.of(()->this);
    @Override
    public boolean getBlackEyeStatus() {
        return blackEye;
    }

    @Override
    public void setBlackEyeStatus(boolean set) {
        blackEye = set;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nonnull Direction side) {
        if(cap == PlayerCapability.INSTANCE){ return PlayerCapability.INSTANCE.orEmpty(cap, this.holder); }
        else return LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putBoolean("blackeyestatus", blackEye);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        blackEye = nbt.getBoolean("blackeyestatus");
    }
}
