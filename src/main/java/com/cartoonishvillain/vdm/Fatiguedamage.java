package com.cartoonishvillain.vdm;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class Fatiguedamage extends DamageSource {
    public Fatiguedamage(String p_i1566_1_) {
        super(p_i1566_1_);
    }

    public static DamageSource causeFatigueDamage(Entity entity){
        return (new EntityDamageSource("fatigue", entity).bypassArmor().setMagic());
    }
}
