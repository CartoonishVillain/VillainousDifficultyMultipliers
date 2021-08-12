package com.cartoonishvillain.vdm.Events;

import com.cartoonishvillain.vdm.Capabilities.PlayerCapabilities.PlayerCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.UUID;


public class RandomAttackDecider {

    public static void Activate(Level level, Player player, LivingEntity livingEntity){
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

    private static void RewardDispenser(Level level, Player player, LivingEntity livingEntity, String selected){

        switch (selected){
            case "explode" ->{
                level.explode(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 3f, Explosion.BlockInteraction.NONE);
                player.sendMessage(new TranslatableComponent("wild.villainousdifficultymultipliers.explode").withStyle(ChatFormatting.LIGHT_PURPLE), UUID.randomUUID());
            }
            case "flame"->{
                livingEntity.setSecondsOnFire(10);
                player.sendMessage(new TranslatableComponent("wild.villainousdifficultymultipliers.flame").withStyle(ChatFormatting.LIGHT_PURPLE), UUID.randomUUID());
            }
            case "heal" ->{
                float health = player.getHealth();
                float maxhealth = player.getMaxHealth();
                if((health + 5) > maxhealth){
                    player.setHealth(maxhealth);
                    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20*30, 1));
                }
                else{player.setHealth(health+5);}
                player.sendMessage(new TranslatableComponent("wild.villainousdifficultymultipliers.heal").withStyle(ChatFormatting.LIGHT_PURPLE), UUID.randomUUID());
            }
            case "lightning"->{
                LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                lightningBolt.setPos(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
                level.addFreshEntity(lightningBolt);
                player.sendMessage(new TranslatableComponent("wild.villainousdifficultymultipliers.lightning").withStyle(ChatFormatting.LIGHT_PURPLE), UUID.randomUUID());
            }
            case "freeze"->{
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20*15, 4));
                player.sendMessage(new TranslatableComponent("wild.villainousdifficultymultipliers.freeze").withStyle(ChatFormatting.LIGHT_PURPLE), UUID.randomUUID());
            }
            case "levitate"->{
                int chance = level.random.nextInt(2);
                if(chance == 1){
                player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20*10, 0));
                    player.sendMessage(new TranslatableComponent("wild.villainousdifficultymultipliers.levitates").withStyle(ChatFormatting.LIGHT_PURPLE), UUID.randomUUID());}
                if (chance == 0){
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20*10, 0));
                    player.sendMessage(new TranslatableComponent("wild.villainousdifficultymultipliers.levitatet").withStyle(ChatFormatting.LIGHT_PURPLE), UUID.randomUUID());
                }
            }
            case "resistance"->{
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20*60, 0));
                player.sendMessage(new TranslatableComponent("wild.villainousdifficultymultipliers.resistance").withStyle(ChatFormatting.LIGHT_PURPLE), UUID.randomUUID());
            }
            case "shout"->{
                player.getCapability(PlayerCapability.INSTANCE).ifPresent(h->{
                    h.setShoutTicks(1200);
                });
                player.sendMessage(new TranslatableComponent("wild.villainousdifficultymultipliers.shout").withStyle(ChatFormatting.LIGHT_PURPLE), UUID.randomUUID());
            }
        }

    }

}
