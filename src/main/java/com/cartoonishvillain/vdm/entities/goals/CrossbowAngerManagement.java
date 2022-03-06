package com.cartoonishvillain.vdm.entities.goals;

import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class CrossbowAngerManagement extends RangedCrossbowAttackGoal {
    public CrossbowAngerManagement(Monster p_i50322_1_, double p_i50322_2_, float p_i50322_4_) {
        super(p_i50322_1_, p_i50322_2_, p_i50322_4_);
    }

    @Override
    public void tick() {
        super.tick();
        int delay = 0;
        try{
        delay = ObfuscationReflectionHelper.getPrivateValue(RangedCrossbowAttackGoal.class, this, "f_25810_");}catch (NullPointerException e){e.printStackTrace();}
        if(delay > 13){delay = 13;}
        ObfuscationReflectionHelper.setPrivateValue(RangedCrossbowAttackGoal.class, this, delay, "f_25810_");
    }
}
