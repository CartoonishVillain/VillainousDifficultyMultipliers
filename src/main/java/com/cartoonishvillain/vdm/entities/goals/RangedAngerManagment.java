package com.cartoonishvillain.vdm.entities.goals;

import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class RangedAngerManagment extends RangedAttackGoal {
    public RangedAngerManagment(RangedAttackMob p_i1649_1_, double p_i1649_2_, int p_i1649_4_, float p_i1649_5_) {
        super(p_i1649_1_, p_i1649_2_, p_i1649_4_, p_i1649_5_);
    }

    @Override
    public void tick() {
        super.tick();
        int delay = 0;
        try{
            delay = ObfuscationReflectionHelper.getPrivateValue(RangedAttackGoal.class, this, "f_25760_");}catch (NullPointerException e){e.printStackTrace();}
        if(delay > 25){delay = 25;}
        ObfuscationReflectionHelper.setPrivateValue(RangedAttackGoal.class, this, delay, "f_25760_");

    }
}
