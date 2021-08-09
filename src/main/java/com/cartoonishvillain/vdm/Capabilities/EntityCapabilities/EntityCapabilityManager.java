package com.cartoonishvillain.vdm.Capabilities.EntityCapabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class EntityCapabilityManager implements IEntityCapability, ICapabilityProvider, INBTSerializable<CompoundTag> {
    protected boolean retaliation = false;
    protected boolean wrong = false;
    protected int age = 0;
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
    public boolean getWrongStatus() {return wrong;}

    @Override
    public void setWrongStatus(boolean set) {this.wrong = set;}

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nonnull Direction side) {
        if(cap == EntityCapability.INSTANCE){ return EntityCapability.INSTANCE.orEmpty(cap, this.holder); }
        else return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("retaliation", retaliation);
        tag.putInt("age", age);
        tag.putBoolean("wrong", wrong);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        retaliation = nbt.getBoolean("retaliation");
        age = nbt.getInt("age");
        wrong = nbt.getBoolean("wrong");
    }
}
