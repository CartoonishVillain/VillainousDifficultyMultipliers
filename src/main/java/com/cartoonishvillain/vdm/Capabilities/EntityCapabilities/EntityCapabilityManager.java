package com.cartoonishvillain.vdm.Capabilities.EntityCapabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class EntityCapabilityManager implements IEntityCapability, ICapabilityProvider, INBTSerializable<CompoundNBT> {
    protected boolean retaliation = false;
    protected int age = 0;
    protected boolean wrong = false;
    public final LazyOptional<IEntityCapability> holder = LazyOptional.of(()->this);
    @Override
    public boolean getRetaliationStatus() {
        return retaliation;
    }

    @Override
    public void setRetaliationStatus(boolean set) {
        retaliation = set;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean getWrong() {
        return wrong;
    }

    @Override
    public void setWrong(boolean set) {
        wrong = set;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nonnull Direction side) {
        if(cap == EntityCapability.INSTANCE){ return EntityCapability.INSTANCE.orEmpty(cap, this.holder); }
        else return LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putBoolean("retaliation", retaliation);
        tag.putInt("age", age);
        tag.putBoolean("wrong", wrong);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        retaliation = nbt.getBoolean("retaliation");
        age = nbt.getInt("age");
        wrong = nbt.getBoolean("wrong");
    }
}
