package com.cartoonishvillain.vdm.Events;

import com.cartoonishvillain.vdm.Capabilities.EntityCapabilities.EntityCapability;
import com.cartoonishvillain.vdm.Capabilities.EntityCapabilities.EntityCapabilityManager;
import com.cartoonishvillain.vdm.Capabilities.PlayerCapabilities.PlayerCapability;
import com.cartoonishvillain.vdm.Capabilities.PlayerCapabilities.PlayerCapabilityManager;
import com.cartoonishvillain.vdm.Commands.CommandManager;
import com.cartoonishvillain.vdm.Fatiguedamage;
import com.cartoonishvillain.vdm.VDM;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Explosion;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Mod.EventBusSubscriber(modid = VDM.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SubscribeEvent
    public static void serverLoad(RegisterCommandsEvent event){
        CommandManager.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void playerRegister(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof PlayerEntity){
            PlayerCapabilityManager provider = new PlayerCapabilityManager();
            event.addCapability(new ResourceLocation(VDM.MODID, "blackeyestatus"), provider);
        }
    }

    @SubscribeEvent
    public static void entityRegister(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof AnimalEntity || event.getObject() instanceof VillagerEntity){
            EntityCapabilityManager provider = new EntityCapabilityManager();
            event.addCapability(new ResourceLocation(VDM.MODID, "entitycapabilities"), provider);
        }
    }

    @SubscribeEvent
    public static void SoftSkin(LivingDamageEvent event){
        if(event.getEntityLiving() instanceof PlayerEntity && !event.getEntityLiving().level.isClientSide()){
            PlayerEntity target = (PlayerEntity) event.getEntityLiving();
                if(VDM.config.SOFTSKIN.get()){event.setAmount(event.getAmount() * 1.5f);}
        }
    }

    @SubscribeEvent
    public static void Fatigue(TickEvent.PlayerTickEvent event){
        PlayerEntity playerEntity = event.player;
        if(!playerEntity.level.isClientSide()){
                if(VDM.config.FATIGUE.get()){
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

        }
    }

    @SubscribeEvent
    public static void Cannon(LivingDeathEvent event){
        if(!event.getEntityLiving().level.isClientSide()){
            if(event.getEntityLiving() instanceof CreeperEntity){
                CreeperEntity creeperEntity = (CreeperEntity) event.getEntityLiving();
                    if(VDM.config.CANNON.get()){
                        Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(creeperEntity.level, creeperEntity) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
                        float f = creeperEntity.isPowered() ? 2.0F : 1.0F;
                        creeperEntity.level.explode(creeperEntity, creeperEntity.getX(), creeperEntity.getY(), creeperEntity.getZ(), (float)3 * f, explosion$mode);
                        creeperEntity.remove();
                    }
            }
        }
    }

    @SubscribeEvent
    public static void Shift(LivingEvent.LivingUpdateEvent event){
        if(!event.getEntityLiving().level.isClientSide()){
                if(VDM.config.SHIFT.get()) {
                    if (event.getEntityLiving().getType() == EntityType.ZOMBIE) {
                        ZombieEntity entity = (ZombieEntity) event.getEntityLiving();
                        Random random = new Random();
                        Vector3d vector3d = entity.position();
                        int chance = random.nextInt(12);
                        if (chance < 6) {
                            DrownedEntity newMob = new DrownedEntity(EntityType.DROWNED, entity.level);
                            newMob.teleportTo(vector3d.x, vector3d.y, vector3d.z);
                            entity.level.addFreshEntity(newMob);
                            entity.remove();
                        } else if (chance < 11) {
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
                            try {
                                creeperEntity.getEntityData().set(ObfuscationReflectionHelper.getPrivateValue(CreeperEntity.class, creeperEntity, "field_184714_b"), true);
                            }catch (NullPointerException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
        }
    }

    @SubscribeEvent
    public static void BlackEye(LivingHealEvent event){
        if(!event.getEntity().level.isClientSide()){
            if(event.getEntity() instanceof PlayerEntity){
                PlayerEntity playerEntity = (PlayerEntity) event.getEntity();
                if(VDM.config.BLACKEYE.get()) {
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
            if(VDM.config.BLACKEYE.get()){
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

    @SubscribeEvent
    public static void Venom(LivingDamageEvent event){
        if(!event.getEntityLiving().level.isClientSide()) {
            if (event.getSource().getEntity() instanceof LivingEntity && event.getSource().getEntity().getType() == EntityType.CAVE_SPIDER) {
                    if (VDM.config.VENOM.get()) {
                        Difficulty level = event.getEntityLiving().level.getDifficulty();
                        if (level == Difficulty.EASY) {
                            event.getEntityLiving().addEffect(new EffectInstance(Effects.POISON, 5 * 20, 0));
                        } else if (level == Difficulty.NORMAL) {
                            event.getEntityLiving().addEffect(new EffectInstance(Effects.WITHER, 2 * 20, 0));
                        } else if (level == Difficulty.HARD) {
                            event.getEntityLiving().addEffect(new EffectInstance(Effects.WITHER, 5 * 20, 0));
                        }
                    }
            }
            else if(event.getSource().getEntity() instanceof LivingEntity && event.getSource().getEntity().getType() == EntityType.SPIDER){
                    if (VDM.config.VENOM.get()) {
                        Difficulty level = event.getEntityLiving().level.getDifficulty();
                        if (level == Difficulty.EASY) {
                            event.getEntityLiving().addEffect(new EffectInstance(Effects.POISON, 2 * 20, 0));
                        } else if (level == Difficulty.NORMAL) {
                            event.getEntityLiving().addEffect(new EffectInstance(Effects.POISON, 4 * 20, 0));
                        } else if (level == Difficulty.HARD) {
                            event.getEntityLiving().addEffect(new EffectInstance(Effects.POISON, 6 * 20, 0));
                        }
                    }
            }
        }
    }

    @SubscribeEvent
    public static void Aging(BabyEntitySpawnEvent event){
        if(!event.getParentA().level.isClientSide()){
            if(VDM.config.AGING.get()){
                AtomicInteger age = new AtomicInteger(0);
                event.getParentA().getCapability(EntityCapability.INSTANCE).ifPresent(h->{
                    h.setAge(h.getAge()+1);
                    age.set(h.getAge());
                });
                agecheck(age.get(), event.getParentA());

                age.set(0);
                event.getParentB().getCapability(EntityCapability.INSTANCE).ifPresent(h->{
                    h.setAge(h.getAge()+1);
                    age.set(h.getAge());
                });
                agecheck(age.get(), event.getParentB());
            }
        }
    }

    @SubscribeEvent
    public static void seedRetaliation(EntityJoinWorldEvent event){
        Entity e = event.getEntity();
        if (!e.level.isClientSide() && e instanceof LivingEntity){
            EntityType eType = e.getType();
            if(eType == EntityType.PIG || eType == EntityType.SHEEP || eType == EntityType.COW || eType == EntityType.MOOSHROOM || eType == EntityType.CHICKEN){
                Random random = new Random();
                int chance = random.nextInt(20);
                if(chance <= 1) e.getCapability(EntityCapability.INSTANCE).ifPresent(h->{
                    h.setRetaliationStatus(true);
                });
            }
        }
    }

    @SubscribeEvent
    public static void Retaliate(LivingDamageEvent event){
        if(!event.getEntityLiving().level.isClientSide() && event.getEntity() instanceof LivingEntity) {
            EntityType eType = event.getEntityLiving().getType();
            if (eType == EntityType.PIG || eType == EntityType.SHEEP || eType == EntityType.COW || eType == EntityType.MOOSHROOM || eType == EntityType.CHICKEN) {
                if (VDM.config.KARMICJUSTICE.get() && event.getSource().getEntity() instanceof PlayerEntity) {
                    AtomicBoolean isExplodey = new AtomicBoolean(false);
                    event.getEntityLiving().getCapability(EntityCapability.INSTANCE).ifPresent(h->{
                        isExplodey.set(h.getRetaliationStatus());
                    });
                    if(isExplodey.get()) {
                        event.getEntityLiving().level.explode(event.getEntityLiving(), event.getEntityLiving().getX(), event.getEntityLiving().getY(), event.getEntityLiving().getZ(), 2f, Explosion.Mode.NONE);
                        event.getEntityLiving().remove();
                    }
                }
            }
        }
    }

    private static void agecheck(int age, LivingEntity livingEntity){
        if(age >= 4) livingEntity.remove();
    }

}
