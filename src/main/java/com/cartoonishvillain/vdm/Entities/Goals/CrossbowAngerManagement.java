package com.cartoonishvillain.vdm.Entities.Goals;

import net.minecraft.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class CrossbowAngerManagement extends RangedCrossbowAttackGoal {
    public CrossbowAngerManagement(MonsterEntity p_i50322_1_, double p_i50322_2_, float p_i50322_4_) {
        super(p_i50322_1_, p_i50322_2_, p_i50322_4_);
    }

    @Override
    public void tick() {
        super.tick();
        int delay = 0;
        try{
        delay = ObfuscationReflectionHelper.getPrivateValue(RangedCrossbowAttackGoal.class, this, "field_220753_f");}catch (NullPointerException e){e.printStackTrace();}
        if(delay > 13){delay = 13;}
        ObfuscationReflectionHelper.setPrivateValue(RangedCrossbowAttackGoal.class, this, delay, "field_220753_f");
    }
}
