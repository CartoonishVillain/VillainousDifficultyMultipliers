package com.cartoonishvillain.vdm;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;

public class Fatiguedamage extends DamageSource {
    public Fatiguedamage(String p_i1566_1_) {
        super(p_i1566_1_);
    }

    public static DamageSource causeFatigueDamage(Entity entity){
        return (new EntityDamageSource("fatigue", entity).bypassArmor().setMagic());
    }
}
