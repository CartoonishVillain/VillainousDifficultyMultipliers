package com.cartoonishvillain.vdm.Events;

import com.cartoonishvillain.vdm.Capabilities.WorldCapabilities.WorldCapability;
import com.cartoonishvillain.vdm.Capabilities.WorldCapabilities.WorldCapabilityManager;
import com.cartoonishvillain.vdm.Fatiguedamage;
import com.cartoonishvillain.vdm.VDM;
import com.cartoonishvillain.vdm.Commands.CommandManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = VDM.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SubscribeEvent
    public static void serverLoad(RegisterCommandsEvent event){
        CommandManager.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void worldRegister(AttachCapabilitiesEvent<World> event){
        WorldCapabilityManager provider = new WorldCapabilityManager();
        event.addCapability(new ResourceLocation(VDM.MODID, "multipliersenabled"), provider);
    }

    @SubscribeEvent
    public static void SoftSkin(LivingDamageEvent event){
        if(event.getEntityLiving() instanceof PlayerEntity && !event.getEntityLiving().level.isClientSide()){
            PlayerEntity target = (PlayerEntity) event.getEntityLiving();
            event.getEntityLiving().level.getCapability(WorldCapability.INSTANCE).ifPresent(h->{
                if(h.getSoftSkin()){event.setAmount(event.getAmount() * 1.5f);}
            });
        }
    }

    @SubscribeEvent
    public static void Fatigue(TickEvent.PlayerTickEvent event){
        PlayerEntity playerEntity = event.player;
        if(!playerEntity.level.isClientSide()){
            playerEntity.level.getCapability(WorldCapability.INSTANCE).ifPresent(h->{
                if(h.getFatigue()){
                    ServerStatisticsManager serverstatisticsmanager = ((ServerPlayerEntity)playerEntity).getStats();
                    int sleeptime = MathHelper.clamp(serverstatisticsmanager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)), 1, Integer.MAX_VALUE);
                    Random random = new Random();
                    int chance = random.nextInt(50000);
                    if(sleeptime > 72000 & sleeptime < 84000){
                        if(chance <= 12){
                            playerEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 60*20, 0));
                            playerEntity.addEffect(new EffectInstance(Effects.WEAKNESS, 60*20, 0));
                        }
                    }else if(sleeptime >= 84000 && sleeptime <= 96000){
                        if(chance <= 10){
                            playerEntity.addEffect(new EffectInstance(Effects.CONFUSION, 60*20, 0));
                            playerEntity.addEffect(new EffectInstance(Effects.HUNGER, 60*20, 0));
                        } if(chance <= 15){
                            playerEntity.addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, 45*20, 1));
                            playerEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 60*20, 1));
                            playerEntity.addEffect(new EffectInstance(Effects.WEAKNESS, 60*20, 1));
                        }
                    }else if(sleeptime > 96000 && sleeptime <= 132000){
                        if(chance <= 10){
                            playerEntity.addEffect(new EffectInstance(Effects.CONFUSION, 70*20, 0));
                            playerEntity.addEffect(new EffectInstance(Effects.HUNGER, 70*20, 0));
                        }if(chance <= 13){
                            playerEntity.addEffect(new EffectInstance(Effects.BLINDNESS, 45*20, 0));
                        }
                        if(chance <= 17){
                            playerEntity.addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, 45*20, 2));
                            playerEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 60*20, 2));
                            playerEntity.addEffect(new EffectInstance(Effects.WEAKNESS, 60*20, 2));
                        }
                    }else if(sleeptime > 132000){
                        if (chance < 49000) playerEntity.hurt(Fatiguedamage.causeFatigueDamage(playerEntity), 1);
                    }
                }
            });

        }
    }

    @SubscribeEvent
    public static void FATIGUETEST(LivingEvent.LivingJumpEvent event){
        if(event.getEntity() instanceof PlayerEntity && !event.getEntity().level.isClientSide()){
            PlayerEntity playerEntity = (PlayerEntity) event.getEntity();
            playerEntity.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
        }
    }

    @SubscribeEvent
    public static void FATIGUETEST2(TickEvent.PlayerTickEvent event){
        if(!event.player.level.isClientSide()){
            if(event.player.isDiscrete()) event.player.awardStat(Stats.TIME_SINCE_REST, 1000);
            ServerStatisticsManager serverstatisticsmanager = ((ServerPlayerEntity)event.player).getStats();
            Integer sleeptime = MathHelper.clamp(serverstatisticsmanager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)), 1, Integer.MAX_VALUE);
            event.player.displayClientMessage(new StringTextComponent(sleeptime.toString()), true);
        }
    }
}
