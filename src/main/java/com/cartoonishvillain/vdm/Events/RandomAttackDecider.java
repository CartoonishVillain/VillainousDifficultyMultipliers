package com.cartoonishvillain.vdm.Events;

import com.cartoonishvillain.vdm.Capabilities.PlayerCapabilities.PlayerCapability;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.UUID;


public class RandomAttackDecider {

    public static void Activate(World level, ServerPlayerEntity player, LivingEntity livingEntity){
        ArrayList<String> possibilities = new ArrayList<>();
        ArrayList<Float> weights = new ArrayList<>();

        possibilities.add("explode"); weights.add(2f);
        possibilities.add("flame"); weights.add(2f);
        possibilities.add("heal"); weights.add(2f);
        possibilities.add("lightning"); weights.add(2f);
        possibilities.add("freeze"); weights.add(2f);
        possibilities.add("levitate"); weights.add(2f);
        possibilities.add("resistance"); weights.add(2f);
        possibilities.add("shout"); weights.add(2f);

        float Total = 0f;
        for(float totaling : weights) Total += totaling;
        float randomized = 0 + level.random.nextFloat() * (Total-0);
        int select = 0;
        String selected;

        for(Float percentage : weights){
            randomized -= percentage;
            if(randomized <= 0) break;
            else select++;
        }
        if(select == possibilities.size()) select = select - 1;
        selected = possibilities.get(select);
        RewardDispenser(level, player, livingEntity, selected);
    }

    private static void RewardDispenser(World level, ServerPlayerEntity player, LivingEntity livingEntity, String selected){

        switch (selected){
            case "explode":
                level.explode(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 3f, Explosion.Mode.NONE);
                player.sendMessage(new TranslationTextComponent("wild.villainousdifficultymultipliers.explode").withStyle(TextFormatting.LIGHT_PURPLE), UUID.randomUUID());
                break;

            case "flame":
                livingEntity.setSecondsOnFire(10);
                player.sendMessage(new TranslationTextComponent("wild.villainousdifficultymultipliers.flame").withStyle(TextFormatting.LIGHT_PURPLE), UUID.randomUUID());
                break;

            case "heal":
                float health = player.getHealth();
                float maxhealth = player.getMaxHealth();
                if((health + 5) > maxhealth){
                    player.setHealth(maxhealth);
                    player.addEffect(new EffectInstance(Effects.ABSORPTION, 20*30, 1));
                }
                else{player.setHealth(health+5);}
                player.sendMessage(new TranslationTextComponent("wild.villainousdifficultymultipliers.heal").withStyle(TextFormatting.LIGHT_PURPLE), UUID.randomUUID());
                break;

            case "lightning":
                LightningBoltEntity lightningBolt = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, level);
                lightningBolt.setPos(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
                level.addFreshEntity(lightningBolt);
                player.sendMessage(new TranslationTextComponent("wild.villainousdifficultymultipliers.lightning").withStyle(TextFormatting.LIGHT_PURPLE), UUID.randomUUID());
                break;

            case "freeze":
                livingEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20*15, 4));
                player.sendMessage(new TranslationTextComponent("wild.villainousdifficultymultipliers.freeze").withStyle(TextFormatting.LIGHT_PURPLE), UUID.randomUUID());
                break;

            case "levitate":
                int chance = level.random.nextInt(2);
                if(chance == 1){
                    player.addEffect(new EffectInstance(Effects.LEVITATION, 20*10, 0));
                    player.sendMessage(new TranslationTextComponent("wild.villainousdifficultymultipliers.levitates").withStyle(TextFormatting.LIGHT_PURPLE), UUID.randomUUID());}
                if (chance == 0){
                    livingEntity.addEffect(new EffectInstance(Effects.LEVITATION, 20*10, 0));
                    player.sendMessage(new TranslationTextComponent("wild.villainousdifficultymultipliers.levitatet").withStyle(TextFormatting.LIGHT_PURPLE), UUID.randomUUID());
                }
                break;

            case "resistance":
                player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 20*60, 0));
                player.sendMessage(new TranslationTextComponent("wild.villainousdifficultymultipliers.resistance").withStyle(TextFormatting.LIGHT_PURPLE), UUID.randomUUID());
                break;

            case "shout":
                player.getCapability(PlayerCapability.INSTANCE).ifPresent(h->{
                    h.setShoutTicks(1200);
                });
                player.sendMessage(new TranslationTextComponent("wild.villainousdifficultymultipliers.shout").withStyle(TextFormatting.LIGHT_PURPLE), UUID.randomUUID());
                break;

        }

    }

}