package com.cartoonishvillain.vdm.capabilities.playercapabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class PlayerCapabilityManager implements IPlayerCapability, ICapabilityProvider, INBTSerializable<CompoundTag> {
    protected boolean blackEye = false;
    protected float kineticBuildup = 0f;
    protected int shoutticks = 0;
    public final LazyOptional<IPlayerCapability> holder = LazyOptional.of(()->this);
    @Override
    public boolean getBlackEyeStatus() {
        return blackEye;
    }

    @Override
    public float getKineticBuildup() {return kineticBuildup;}

    @Override
    public void setBlackEyeStatus(boolean set) {
        blackEye = set;
    }

    @Override
    public void setKineticBuildup(float damage) {kineticBuildup += damage;
    if(kineticBuildup > 100f) kineticBuildup = 100;}

    @Override
    public void setShoutTicks(int ticks) {
        shoutticks = ticks;
    }

    @Override
    public int getShoutTicks() {
        return shoutticks;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nonnull Direction side) {
        if(cap == PlayerCapability.INSTANCE){ return PlayerCapability.INSTANCE.orEmpty(cap, this.holder); }
        else return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("blackeyestatus", blackEye);
        tag.putFloat("kineticbuildup", kineticBuildup);
        tag.putInt("shoutticks", shoutticks);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        blackEye = nbt.getBoolean("blackeyestatus");
        kineticBuildup = nbt.getFloat("kineticbuildup");
        shoutticks = nbt.getInt("shoutticks");
    }
}
