package com.cartoonishvillain.vdm.Capabilities.WorldCapabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WorldCapabilityManager implements IWorldCapbilityManager, ICapabilityProvider, INBTSerializable<CompoundNBT> {
    protected Boolean blackeye = false;
    protected Boolean cannon = false;
    protected Boolean venom = false;
    protected Boolean shift = false;
    protected Boolean karmicjustice = false;
    protected Boolean aging = false;
    protected Boolean softskin = false;
    protected Boolean fatigue = false;
    public final LazyOptional<IWorldCapbilityManager> holder = LazyOptional.of(() -> this);
    @Override
    public boolean getBlackEye() {
        return blackeye;
    }

    @Override
    public boolean getCannon() {
        return cannon;
    }


    @Override
    public boolean getVenom() {
        return venom;
    }

    @Override
    public boolean getShift() {
        return shift;
    }

    @Override
    public boolean getKarmicJustice() {
        return karmicjustice;
    }

    @Override
    public boolean getAging() {
        return aging;
    }

    @Override
    public boolean getSoftSkin() {
        return softskin;
    }

    @Override
    public boolean getFatigue() {
        return fatigue;
    }

    @Override
    public void setBlackEye(boolean set) {
        blackeye = set;
    }

    @Override
    public void setCannon(boolean set) {
        cannon = set;
    }

    @Override
    public void setVenom(boolean set) {
        venom = set;
    }

    @Override
    public void setShift(boolean set) {
        shift = set;
    }

    @Override
    public void setKarmicJustice(boolean set) {
        karmicjustice = set;
    }

    @Override
    public void setAging(boolean set) {
        aging = set;
    }

    @Override
    public void setSoftSkin(boolean set) {
        softskin = set;
    }

    @Override
    public void setFatigue(boolean set) {
        fatigue = set;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == WorldCapability.INSTANCE){ return WorldCapability.INSTANCE.orEmpty(cap, this.holder); }
        else return LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putBoolean("blackeye", blackeye);
        tag.putBoolean("cannon", cannon);
        tag.putBoolean("venom", venom);
        tag.putBoolean("shift", shift);
        tag.putBoolean("karmicjustice", karmicjustice);
        tag.putBoolean("aging", aging);
        tag.putBoolean("softskin", softskin);
        tag.putBoolean("fatigue", fatigue);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        blackeye = nbt.getBoolean("blackeye");
        cannon = nbt.getBoolean("cannon");
        venom = nbt.getBoolean("venom");
        shift = nbt.getBoolean("shift");
        karmicjustice = nbt.getBoolean("karmicjustice");
        aging = nbt.getBoolean("aging");
        softskin = nbt.getBoolean("softskin");
        fatigue = nbt.getBoolean("fatigue");
    }
}
