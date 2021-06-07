package com.cartoonishvillain.vdm.Events;

import com.cartoonishvillain.vdm.Capabilities.PlayerCapabilities.PlayerCapability;
import com.cartoonishvillain.vdm.Capabilities.PlayerCapabilities.PlayerCapabilityManager;
import com.cartoonishvillain.vdm.Capabilities.WorldCapabilities.WorldCapability;
import com.cartoonishvillain.vdm.Capabilities.WorldCapabilities.WorldCapabilityManager;
import com.cartoonishvillain.vdm.Fatiguedamage;
import com.cartoonishvillain.vdm.VDM;
import com.cartoonishvillain.vdm.Commands.CommandManager;
import net.minecraft.client.gui.screen.EditGamerulesScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

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
    public static void worldRegiser(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof PlayerEntity){
            PlayerCapabilityManager provider = new PlayerCapabilityManager();
            event.addCapability(new ResourceLocation(VDM.MODID, "blackeyestatus"), provider);
        }
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
    public static void Cannon(LivingDeathEvent event){
        if(!event.getEntityLiving().level.isClientSide()){
            if(event.getEntityLiving() instanceof CreeperEntity){
                CreeperEntity creeperEntity = (CreeperEntity) event.getEntityLiving();
                creeperEntity.level.getCapability(WorldCapability.INSTANCE).ifPresent(h->{
                    if(h.getCannon()){
                        Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(creeperEntity.level, creeperEntity) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
                        float f = creeperEntity.isPowered() ? 2.0F : 1.0F;
                        creeperEntity.level.explode(creeperEntity, creeperEntity.getX(), creeperEntity.getY(), creeperEntity.getZ(), (float)3 * f, explosion$mode);
                        creeperEntity.remove();
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public static void Shift(LivingEvent.LivingUpdateEvent event){
        if(!event.getEntityLiving().level.isClientSide()){
            event.getEntityLiving().level.getCapability(WorldCapability.INSTANCE).ifPresent(h->{
                if(h.getShift()) {
                    if (event.getEntityLiving().getType() == EntityType.ZOMBIE) {
                        ZombieEntity entity = (ZombieEntity) event.getEntityLiving();
                        Random random = new Random();
                        Vector3d vector3d = entity.position();
                        int chance = random.nextInt(13);
                        if (chance > 6) {
                            DrownedEntity newMob = new DrownedEntity(EntityType.DROWNED, entity.level);
                            newMob.teleportTo(vector3d.x, vector3d.y, vector3d.z);
                            entity.level.addFreshEntity(newMob);
                            entity.remove();
                        } else if (chance >= 6 && chance < 11) {
                            HuskEntity newMob = new HuskEntity(EntityType.HUSK, entity.level);
                            newMob.teleportTo(vector3d.x, vector3d.y, vector3d.z);
                            entity.level.addFreshEntity(newMob);
                            entity.remove();
                        } else {
                            ZombieVillagerEntity newMob = new ZombieVillagerEntity(EntityType.ZOMBIE_VILLAGER, entity.level);
                            newMob.teleportTo(vector3d.x, vector3d.y, vector3d.z);
                            entity.level.addFreshEntity(newMob);
                            entity.remove();
                        }
                    }
                    if (event.getEntityLiving().getType() == EntityType.SKELETON) {
                        SkeletonEntity entity = (SkeletonEntity) event.getEntityLiving();
                        Vector3d vector3d = entity.position();
                        StrayEntity newMob = new StrayEntity(EntityType.STRAY, entity.level);
                        newMob.teleportTo(vector3d.x, vector3d.y, vector3d.z);
                        entity.level.addFreshEntity(newMob);
                        entity.remove();
                    }
                    if (event.getEntityLiving().getType() == EntityType.CREEPER) {
                        CreeperEntity creeperEntity = (CreeperEntity) event.getEntityLiving();
                        if(creeperEntity.isPowered()){return;}
                        else{
                            try{
                                Field field;
                                field = creeperEntity.getClass().getDeclaredField("DATA_IS_POWERED");
                                field.setAccessible(true);
                                DataParameter<Boolean> powered = (DataParameter<Boolean>) field.get(creeperEntity);
                                creeperEntity.getEntityData().set(powered, true);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public static void BlackEye(LivingHealEvent event){
        if(!event.getEntity().level.isClientSide()){
            if(event.getEntity() instanceof PlayerEntity){
                PlayerEntity playerEntity = (PlayerEntity) event.getEntity();
                AtomicBoolean isSkullOn = new AtomicBoolean(false);
                event.getEntityLiving().level.getCapability(WorldCapability.INSTANCE).ifPresent(h->{
                    isSkullOn.set(h.getBlackEye());
                });
                if(isSkullOn.get()) {
                    playerEntity.getCapability(PlayerCapability.INSTANCE).ifPresent(h -> {
                        if(h.getBlackEyeStatus()){ //user has a black eye
                            event.setCanceled(true); //uses afflicted are unable to heal.
                        }
                    });
                }
            }

        }
    }

    @SubscribeEvent
    public static void BlackEyeApplication(LivingDamageEvent event){
        if(event.getEntityLiving() instanceof PlayerEntity && !event.getEntityLiving().level.isClientSide()){
            PlayerEntity playerEntity = (PlayerEntity) event.getEntityLiving();
            AtomicBoolean isSkullOn = new AtomicBoolean(false);
            event.getEntityLiving().level.getCapability(WorldCapability.INSTANCE).ifPresent(h->{
                isSkullOn.set(h.getBlackEye());
            });
            if(isSkullOn.get()){
                playerEntity.getCapability(PlayerCapability.INSTANCE).ifPresent(h->{
                    h.setBlackEyeStatus(true);
                });
            }
        }
    }

    @SubscribeEvent
    public static void BlackEyeRemoval(LivingDamageEvent event){
        if(event.getSource().getEntity() instanceof PlayerEntity && !event.getEntityLiving().level.isClientSide()){
            PlayerEntity aggressor = (PlayerEntity) event.getSource().getEntity();
            DamageSource damageSource = event.getSource();
            if(!damageSource.isExplosion() && !damageSource.isFire() && !damageSource.isMagic() && !damageSource.isProjectile() && aggressor != event.getEntityLiving()) {//damage source is (probably) melee
                if (event.getEntityLiving() instanceof MonsterEntity || event.getEntityLiving() instanceof PlayerEntity) {
                    aggressor.getCapability(PlayerCapability.INSTANCE).ifPresent(h -> {
                        h.setBlackEyeStatus(false);
                    });
                }
            }
        }
    }

}
