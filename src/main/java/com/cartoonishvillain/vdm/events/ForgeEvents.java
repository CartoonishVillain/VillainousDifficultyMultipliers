package com.cartoonishvillain.vdm.events;

import com.cartoonishvillain.ImmortuosCalyx.ImmortuosCalyx;
import com.cartoonishvillain.ImmortuosCalyx.infection.InfectionManagerCapability;
import com.cartoonishvillain.vdm.capabilities.entitycapabilities.EntityCapability;
import com.cartoonishvillain.vdm.capabilities.entitycapabilities.EntityCapabilityManager;
import com.cartoonishvillain.vdm.capabilities.playercapabilities.PlayerCapability;
import com.cartoonishvillain.vdm.capabilities.playercapabilities.PlayerCapabilityManager;
import com.cartoonishvillain.vdm.capabilities.worldcapabilities.WorldCapability;
import com.cartoonishvillain.vdm.capabilities.worldcapabilities.WorldCapabilityManager;
import com.cartoonishvillain.vdm.commands.CheckMultiplierCommand;
import com.cartoonishvillain.vdm.commands.CommandManager;
import com.cartoonishvillain.vdm.commands.activateMultiplierCommand;
import com.cartoonishvillain.vdm.commands.deactivateMultiplierCommand;
import com.cartoonishvillain.vdm.entities.goals.CrossbowAngerManagement;
import com.cartoonishvillain.vdm.entities.goals.RangedAngerManagment;
import com.cartoonishvillain.vdm.Fatiguedamage;
import com.cartoonishvillain.vdm.VDM;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Mod.EventBusSubscriber(modid = VDM.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SubscribeEvent
    public static void serverLoad(RegisterCommandsEvent event){
        CommandManager.register(event.getDispatcher());
        activateMultiplierCommand.register(event.getDispatcher());
        deactivateMultiplierCommand.register(event.getDispatcher());
        CheckMultiplierCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void playerRegister(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            PlayerCapabilityManager provider = new PlayerCapabilityManager();
            event.addCapability(new ResourceLocation(VDM.MODID, "blackeyestatus"), provider);
        }
    }

    @SubscribeEvent
    public static void entityRegister(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof LivingEntity){
            EntityCapabilityManager provider = new EntityCapabilityManager();
            event.addCapability(new ResourceLocation(VDM.MODID, "entitycapabilities"), provider);
        }
    }

    @SubscribeEvent
    public static void worldRegister(AttachCapabilitiesEvent<Level> event){
            WorldCapabilityManager provider = new WorldCapabilityManager();
            event.addCapability(new ResourceLocation(VDM.MODID, "worldcapabilities"), provider);
    }

    @SubscribeEvent
    public static void SoftSkin(LivingDamageEvent event){
        if(event.getEntityLiving() instanceof Player && !event.getEntityLiving().level.isClientSide()){
            Player target = (Player) event.getEntityLiving();
                if(VDM.config.SOFTSKIN.get()){event.setAmount(event.getAmount() * VDM.config.DAMAGEMULTIPLIER.get().floatValue());}
        }
    }

    @SubscribeEvent
    public static void Fatigue(TickEvent.PlayerTickEvent event){
        Player playerEntity = event.player;
        if(!playerEntity.level.isClientSide()){
                if(VDM.config.FATIGUE.get()){
                    ServerStatsCounter serverstatisticsmanager = ((ServerPlayer)playerEntity).getStats();
                    int sleeptime = Mth.clamp(serverstatisticsmanager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)), 1, Integer.MAX_VALUE);
                    Random random = new Random();
                    int chance = random.nextInt(50000);
                    if(sleeptime > 72000 & sleeptime < 84000){
                        if(chance <= 12){
                            playerEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60*20, 0));
                            playerEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60*20, 0));
                        }
                    }else if(sleeptime >= 84000 && sleeptime <= 96000){
                        if(chance <= 10){
                            playerEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 60*20, 0));
                            playerEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 60*20, 0));
                        } if(chance <= 15){
                            playerEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 45*20, 1));
                            playerEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60*20, 1));
                            playerEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60*20, 1));
                        }
                    }else if(sleeptime > 96000 && sleeptime <= 132000){
                        if(chance <= 10){
                            playerEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 70*20, 0));
                            playerEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 70*20, 0));
                        }if(chance <= 13){
                            playerEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 45*20, 0));
                        }
                        if(chance <= 17){
                            playerEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 45*20, 2));
                            playerEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60*20, 2));
                            playerEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60*20, 2));
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
            if(event.getEntityLiving() instanceof Creeper){
                Creeper creeperEntity = (Creeper) event.getEntityLiving();
                    if(VDM.config.CANNON.get()){
                        boolean loot = true;
                        if (event.getSource().getEntity() == creeperEntity){loot = false;} //creeper naturally exploded. No loot!
                        if (event.getEntityLiving().level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)){loot = false;}
                        Explosion.BlockInteraction explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(creeperEntity.level, creeperEntity) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
                        float f = creeperEntity.isPowered() ? 2.0F : 1.0F;
                        Vec3 vector3d = new Vec3(creeperEntity.getX(), creeperEntity.getY(), creeperEntity.getZ());
                        creeperEntity.level.explode(creeperEntity, creeperEntity.getX(), creeperEntity.getY(), creeperEntity.getZ(), (float)3 * f, explosion$mode);
                        creeperEntity.remove(Entity.RemovalReason.KILLED);
                        //Phase 2 - artificial loot table.
                        if(loot){
                            Entity aggressor = event.getSource().getEntity();
                            if(aggressor instanceof Player || aggressor instanceof Wolf){
                                ExperienceOrb experienceOrbEntity = new ExperienceOrb(EntityType.EXPERIENCE_ORB, aggressor.level);
                                experienceOrbEntity.value = 5;
                                ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, aggressor.level);
                                int maxgun = 2;
                                if(aggressor instanceof Player){
                                    Map<Enchantment, Integer> map =  EnchantmentHelper.getEnchantments(((Player) aggressor).getMainHandItem());
                                    if(map.containsKey(Enchantments.MOB_LOOTING)){
                                        maxgun = 2 + map.get(Enchantments.MOB_LOOTING);
                                    }
                                }
                                Random random = new Random();
                                int gunpowderamount = random.nextInt(maxgun+1); // accounting for 0. I don't think it shows up in randoms.
                                itemEntity.setItem(new ItemStack(Items.GUNPOWDER, gunpowderamount));
                                experienceOrbEntity.setPos(vector3d.x, vector3d.y, vector3d.z);
                                itemEntity.setPos(vector3d.x, vector3d.y, vector3d.z);
                                aggressor.level.addFreshEntity(experienceOrbEntity);
                                aggressor.level.addFreshEntity(itemEntity);
                            }
                            else if(aggressor instanceof Skeleton){
                                ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, aggressor.level);
                                Random random = new Random();
                                int gunpowderamount = random.nextInt(3);
                                itemEntity.setItem(new ItemStack(Items.GUNPOWDER, gunpowderamount));
                                ItemEntity Disc = new ItemEntity(EntityType.ITEM, aggressor.level);
                                Disc.setItem(new ItemStack(MusicDisc(), 1));
                                itemEntity.setPos(vector3d.x, vector3d.y, vector3d.z);
                                Disc.setPos(vector3d.x, vector3d.y, vector3d.z);
                                aggressor.level.addFreshEntity(itemEntity);
                                aggressor.level.addFreshEntity(Disc);
                            }else if(aggressor instanceof Creeper && ((Creeper) aggressor).canDropMobsSkull()){
                                ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, aggressor.level);
                                Random random = new Random();
                                int gunpowderamount = random.nextInt(3);
                                itemEntity.setItem(new ItemStack(Items.GUNPOWDER, gunpowderamount));
                                ItemEntity Skull = new ItemEntity(EntityType.ITEM, aggressor.level);
                                Skull.setItem(new ItemStack(Items.CREEPER_HEAD, 1));
                                itemEntity.setPos(vector3d.x, vector3d.y, vector3d.z);
                                Skull.setPos(vector3d.x, vector3d.y, vector3d.z);
                                aggressor.level.addFreshEntity(itemEntity);
                                aggressor.level.addFreshEntity(Skull);

                            }
                        }
                    }
            }
        }
    }

//    @SubscribeEvent
//    public static void Shift(EntityJoinWorldEvent event){
//        if(!event.getWorld().isClientSide() && event.getWorld().getServer().getPlayerCount() != 0){
//                if(VDM.config.SHIFT.get()) {
//                    if (event.getEntity().getType() == EntityType.ZOMBIE) {
//                        Zombie entity = (Zombie) event.getEntity();
//                        Random random = new Random();
//                        Vec3 vector3d = entity.position();
//
//                        ItemStack mainHand = entity.getItemInHand(InteractionHand.MAIN_HAND);
//                        ItemStack offHand = entity.getItemInHand(InteractionHand.OFF_HAND);
//                        ItemStack Helm = entity.getItemBySlot(EquipmentSlot.HEAD);
//                        ItemStack Chest = entity.getItemBySlot(EquipmentSlot.CHEST);
//                        ItemStack Leg = entity.getItemBySlot(EquipmentSlot.LEGS);
//                        ItemStack Boots = entity.getItemBySlot(EquipmentSlot.FEET);
//
//                        int chance = random.nextInt(12);
//                        if (chance < 6) {
//                            Drowned newMob = new Drowned(EntityType.DROWNED, entity.level);
//                            newMob.setPos(vector3d.x, vector3d.y, vector3d.z);
//                            newMob.setItemInHand(InteractionHand.MAIN_HAND, mainHand);
//                            newMob.setItemInHand(InteractionHand.OFF_HAND, offHand);
//                            newMob.setItemSlot(EquipmentSlot.HEAD, Helm);
//                            newMob.setItemSlot(EquipmentSlot.CHEST, Chest);
//                            newMob.setItemSlot(EquipmentSlot.LEGS, Leg);
//                            newMob.setItemSlot(EquipmentSlot.FEET, Boots);
//                            entity.level.addFreshEntity(newMob);
//                            entity.setPos(entity.getX(), -1, entity.getZ());
//                            entity.kill();
//                        } else if (chance < 11) {
//                            Husk newMob = new Husk(EntityType.HUSK, entity.level);
//                            newMob.setPos(vector3d.x, vector3d.y, vector3d.z);
//                            entity.level.addFreshEntity(newMob);
//                            entity.setPos(entity.getX(), -1, entity.getZ());
//                            entity.kill();
//                        } else {
//                            ZombieVillager newMob = new ZombieVillager(EntityType.ZOMBIE_VILLAGER, entity.level);
//                            newMob.setPos(vector3d.x, vector3d.y, vector3d.z);
//                            entity.level.addFreshEntity(newMob);
//                            entity.setPos(entity.getX(), -1, entity.getZ());
//                            entity.kill();
//                        }
//                    }
//                    if (event.getEntity().getType() == EntityType.SKELETON) {
//                        Skeleton entity = (Skeleton) event.getEntity();
//                        Vec3 vector3d = entity.position();
//
//                        ItemStack mainHand = entity.getItemInHand(InteractionHand.MAIN_HAND);
//                        ItemStack offHand = entity.getItemInHand(InteractionHand.OFF_HAND);
//                        ItemStack Helm = entity.getItemBySlot(EquipmentSlot.HEAD);
//                        ItemStack Chest = entity.getItemBySlot(EquipmentSlot.CHEST);
//                        ItemStack Leg = entity.getItemBySlot(EquipmentSlot.LEGS);
//                        ItemStack Boots = entity.getItemBySlot(EquipmentSlot.FEET);
//
//                        Stray newMob = new Stray(EntityType.STRAY, entity.level);
//                        newMob.setPos(vector3d.x, vector3d.y, vector3d.z);
//
//                        newMob.teleportTo(vector3d.x, vector3d.y, vector3d.z);
//                        newMob.setItemInHand(InteractionHand.MAIN_HAND, mainHand);
//                        newMob.setItemInHand(InteractionHand.OFF_HAND, offHand);
//                        newMob.setItemSlot(EquipmentSlot.HEAD, Helm);
//                        newMob.setItemSlot(EquipmentSlot.CHEST, Chest);
//                        newMob.setItemSlot(EquipmentSlot.LEGS, Leg);
//                        newMob.setItemSlot(EquipmentSlot.FEET, Boots);
//
//                        entity.setPos(entity.getX(), -1, entity.getZ());
//                        entity.level.addFreshEntity(newMob);
//                        entity.kill();
//                    }
//                    if (event.getEntity().getType() == EntityType.CREEPER) {
//                        Creeper creeperEntity = (Creeper) event.getEntity();
//                        if(creeperEntity.isPowered()){return;}
//                        else{
//                            try {
//                                creeperEntity.getEntityData().set(ObfuscationReflectionHelper.getPrivateValue(Creeper.class, creeperEntity, "f_32274_"), true);
//                            }catch (NullPointerException e){
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//        }
//    }

    @SubscribeEvent
    public static void BlackEye(LivingHealEvent event){
        if(!event.getEntity().level.isClientSide()){
            if(event.getEntity() instanceof Player){
                Player playerEntity = (Player) event.getEntity();
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
        if(event.getEntityLiving() instanceof Player && !event.getEntityLiving().level.isClientSide()){
            Player playerEntity = (Player) event.getEntityLiving();
            if(VDM.config.BLACKEYE.get()){
                playerEntity.getCapability(PlayerCapability.INSTANCE).ifPresent(h->{
                    h.setBlackEyeStatus(true);
                });
            }
        }
    }

    @SubscribeEvent
    public static void BlackEyeRemoval(LivingDamageEvent event){
        if(event.getSource().getEntity() instanceof Player && !event.getEntityLiving().level.isClientSide()){
            Player aggressor = (Player) event.getSource().getEntity();
            DamageSource damageSource = event.getSource();
            if(!damageSource.isExplosion() && !damageSource.isFire() && !damageSource.isMagic() && !damageSource.isProjectile() && aggressor != event.getEntityLiving()) {//damage source is (probably) melee
                if (event.getEntityLiving() instanceof Monster || event.getEntityLiving() instanceof Player) {
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
                            event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.POISON, 5 * 20, 0));
                        } else if (level == Difficulty.NORMAL) {
                            event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.WITHER, 2 * 20, 0));
                        } else if (level == Difficulty.HARD) {
                            event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.WITHER, 5 * 20, 0));
                        }
                    }
            }
            else if(event.getSource().getEntity() instanceof LivingEntity && event.getSource().getEntity().getType() == EntityType.SPIDER){
                    if (VDM.config.VENOM.get()) {
                        Difficulty level = event.getEntityLiving().level.getDifficulty();
                        if (level == Difficulty.EASY) {
                            event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.POISON, 2 * 20, 0));
                        } else if (level == Difficulty.NORMAL) {
                            event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.POISON, 4 * 20, 0));
                        } else if (level == Difficulty.HARD) {
                            event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.POISON, 6 * 20, 0));
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
                int chance = random.nextInt(VDM.config.KARMICJUSTICESPAWNCHANCE.get());
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
                if (VDM.config.KARMICJUSTICE.get() && event.getSource().getEntity() instanceof Player) {
                    AtomicBoolean isExplodey = new AtomicBoolean(false);
                    event.getEntityLiving().getCapability(EntityCapability.INSTANCE).ifPresent(h->{
                        isExplodey.set(h.getRetaliationStatus());
                    });
                    if(isExplodey.get()) {
                        event.getEntityLiving().level.explode(event.getEntityLiving(), event.getEntityLiving().getX(), event.getEntityLiving().getY(), event.getEntityLiving().getZ(), 2f, Explosion.BlockInteraction.NONE);
                        event.getEntityLiving().remove(Entity.RemovalReason.KILLED);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void Hardened(EntityJoinWorldEvent event){
        if(!event.getEntity().level.isClientSide() && event.getEntity() instanceof Monster){
            if(VDM.config.HARDENED.get()) {
                float health = ((Monster) event.getEntity()).getHealth() * VDM.config.HARDENEDBOOST.get().floatValue() - 1f;
                AttributeInstance modifiableAttributeInstance = ((Monster) event.getEntity()).getAttribute(Attributes.MAX_HEALTH);
                if (modifiableAttributeInstance == null) {
                    return;
                }
                if(modifiableAttributeInstance.getModifiers().size() == 0) {
                    modifiableAttributeInstance.addTransientModifier(new AttributeModifier(UUID.fromString("D6F0BA2-1186-46AC-B896-C61C5CEE99CC"), "Hardened health boost", health, AttributeModifier.Operation.ADDITION));
                    ((Monster) event.getEntity()).setHealth(((Monster) event.getEntity()).getHealth() * VDM.config.HARDENEDBOOST.get().floatValue());
                }
            }
        }
    }

    @SubscribeEvent
    public static void Anger(EntityJoinWorldEvent event){
        if(!event.getEntity().level.isClientSide()){
            if(VDM.config.ANGER.get()) {
                if (event.getEntity().getType().equals(EntityType.SKELETON) || event.getEntity().getType().equals(EntityType.STRAY)) {
                    AbstractSkeleton abstractSkeletonEntity = (AbstractSkeleton) event.getEntity();
                    try {
                        RangedBowAttackGoal<AbstractSkeleton> rangedBowAttackGoal = ObfuscationReflectionHelper.getPrivateValue(AbstractSkeleton.class, abstractSkeletonEntity, "f_32130_");
                        if (abstractSkeletonEntity.level.getDifficulty() != Difficulty.HARD)
                            rangedBowAttackGoal.setMinAttackInterval(30);
                        else rangedBowAttackGoal.setMinAttackInterval(15);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                }
                if(event.getEntity().getType().equals(EntityType.PILLAGER)){
                    Pillager pillagerEntity = (Pillager) event.getEntity();
                    Set<WrappedGoal> prioritizedGoals = ObfuscationReflectionHelper.getPrivateValue(GoalSelector.class, pillagerEntity.goalSelector, "f_25345_");
                    Goal toremove = null;
                    for(WrappedGoal prioritizedGoal : prioritizedGoals){
                        if(prioritizedGoal.getGoal() instanceof RangedCrossbowAttackGoal){
                            toremove = prioritizedGoal.getGoal();
                            if(toremove != null) break;
                        }
                    }
                    if(toremove != null){
                    pillagerEntity.goalSelector.removeGoal(toremove);
                    pillagerEntity.goalSelector.addGoal(3, new CrossbowAngerManagement(pillagerEntity, 1.5D, 8.0F));
                    }
                }if(event.getEntity().getType().equals(EntityType.WITCH)){
                    Witch witchEntity = (Witch) event.getEntity();
                    Set<WrappedGoal> prioritizedGoals = ObfuscationReflectionHelper.getPrivateValue(GoalSelector.class, witchEntity.goalSelector, "f_25345_");
                    Goal toremove = null;
                    for(WrappedGoal prioritizedGoal : prioritizedGoals) {
                        if (prioritizedGoal.getGoal() instanceof RangedAttackGoal) {
                            toremove = prioritizedGoal.getGoal();
                            if (toremove != null) break;
                        }
                    }
                        if(toremove != null){
                            witchEntity.goalSelector.removeGoal(toremove);
                            witchEntity.goalSelector.addGoal(3, new RangedAngerManagment(witchEntity, 1.0D, 60, 10.0F));
                            GoalSelector goalSelector = witchEntity.goalSelector;
                        }
                }

            }
        }
    }

    @SubscribeEvent
    public static void Unstable(EntityJoinWorldEvent event){
        if (!event.getEntity().level.isClientSide()){
            if(VDM.config.UNSTABLE.get()) {
                if (event.getEntity().getType() == EntityType.GHAST) {
                    Ghast ghastEntity = (Ghast) event.getEntity();
                    ObfuscationReflectionHelper.setPrivateValue(Ghast.class, ghastEntity, VDM.config.EXPLOSIONSIZE.get(), "f_32722_");
                }
                if (event.getEntity().getType().equals(EntityType.CREEPER)) {
                    Creeper creeperEntity = (Creeper) event.getEntity();
                    ObfuscationReflectionHelper.setPrivateValue(Creeper.class, creeperEntity, VDM.config.EXPLOSIONSIZE.get(), "f_32272_");
                }
            }
        }
    }

    @SubscribeEvent
    public static void Kinetic(LivingDamageEvent event){
        if(!event.getEntityLiving().level.isClientSide()){
            if(VDM.config.KINETIC.get()) {
                if (event.getEntityLiving() instanceof Player) {
                    DamageSource damageSource = event.getSource();
                    float damage = event.getAmount();
                    if (damageSource.isFire()) {
                    } else if (damageSource.isProjectile()) {
                        damage = damage * 1.2f;
                    } else if (damageSource.isMagic()) {
                        damage = damage * 0.5f;
                    } else if (damageSource.isExplosion()) {
                        damage = damage * 2f;
                    } else {
                        damage = damage * 1.5f;
                    }

                    float finalDamage = damage;
                    event.getEntityLiving().getCapability(PlayerCapability.INSTANCE).ifPresent(h -> {
                        h.setKineticBuildup(finalDamage);
                    });
                } else if (event.getSource().getDirectEntity() instanceof Player && event.getEntity() instanceof LivingEntity) {
                    AtomicReference<Float> atomicFloat = new AtomicReference<>(0f);
                    event.getSource().getDirectEntity().getCapability(PlayerCapability.INSTANCE).ifPresent(h -> {
                        atomicFloat.set(h.getKineticBuildup());
                        h.setKineticBuildup(h.getKineticBuildup() * -1);
                    });
                    float moreDamage = atomicFloat.get();
                    event.setAmount(event.getAmount() + moreDamage);
                }
            }else{
                if (event.getEntityLiving() instanceof Player) {
                    event.getEntityLiving().getCapability(PlayerCapability.INSTANCE).ifPresent(h -> {
                        h.setKineticBuildup(h.getKineticBuildup() * -1);
                    });
                }
            }
        }
    }

    @SubscribeEvent
    public static void Undying(LivingDeathEvent event){
        if(!event.getEntityLiving().level.isClientSide()){
            if (event.getEntityLiving() instanceof Player && VDM.config.UNDYING.get()){
                event.setCanceled(true);
                ((Player) event.getEntityLiving()).awardStat(Stats.DEATHS, 1);
                ((Player) event.getEntityLiving()).getScoreboard().forAllObjectives(ObjectiveCriteria.DEATH_COUNT, event.getEntityLiving().getScoreboardName(), Score::increment);
                event.getEntityLiving().setHealth(event.getEntityLiving().getMaxHealth());

            }
        }
    }

    @SubscribeEvent
    public static void Flammable(LivingEvent.LivingUpdateEvent event){
        if(!event.getEntityLiving().level.isClientSide() && VDM.config.FLAMMABLE.get()){
            if(event.getEntityLiving().getRemainingFireTicks() == 1){event.getEntityLiving().setRemainingFireTicks(20);}
        }
    }

    @SubscribeEvent
    public static void FuelEfficiency(FurnaceFuelBurnTimeEvent event){
        if(VDM.config.FUELEFFICIENT.get()) {event.setBurnTime(event.getBurnTime() * VDM.config.FUELEFFICIENTVALUE.get());}
    }

    @SubscribeEvent
    public static void Blacksmithing(AnvilRepairEvent event){if(VDM.config.BLACKSMITHING.get()){event.setBreakChance(VDM.config.BLACKSMITHINGCHANCE.get().floatValue());}}

    @SubscribeEvent
    public static void Warranty(PlayerDestroyItemEvent event){
        if(!event.getPlayer().level.isClientSide() && (event.getOriginal().getItem() instanceof DiggerItem || event.getOriginal().getItem() instanceof FlintAndSteelItem) && VDM.config.WARRANTY.get()){
        event.getOriginal().setDamageValue(0);
        CompoundTag nbt = event.getOriginal().serializeNBT();
        ItemStack replacement = new ItemStack(event.getOriginal().getItem(), 1);
        replacement.deserializeNBT(nbt);
        Vec3 vector3d = event.getPlayer().position();
        ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, event.getPlayer().level);
        itemEntity.setItem(replacement);
        itemEntity.setPos(vector3d.x, vector3d.y, vector3d.z);
        event.getPlayer().level.addFreshEntity(itemEntity);
        }
    }

    @SubscribeEvent
    public static void Vegetarian(LivingEntityUseItemEvent.Finish event){
        if(!event.getEntity().level.isClientSide() && event.getEntityLiving() instanceof Player && event.getItem().getItem().isEdible() && VDM.config.VEGETARIAN.get()){
            FoodProperties foodProperties = event.getItem().getItem().getFoodProperties();
            if(foodProperties.isMeat()){
                event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 15*20, 0));
                event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.HUNGER, 30*20, 1));
                event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.CONFUSION, 15*20, 0));
                event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 30*20, 0));
                event.getEntityLiving().sendMessage(new TranslatableComponent("status.villainousdifficultymultipliers.vegetarian").withStyle(ChatFormatting.RED), event.getEntityLiving().getUUID());
            }
        }
    }

    @SubscribeEvent
    public static void seedWrong(EntityJoinWorldEvent event){
        Entity e = event.getEntity();
        if (!e.level.isClientSide() && e instanceof LivingEntity){
            EntityType eType = e.getType();
            if(eType == EntityType.ENDERMAN || eType == EntityType.ZOMBIFIED_PIGLIN || eType == EntityType.WOLF || eType == EntityType.BEE || eType == EntityType.LLAMA){
                Random random = new Random();
                int chance = random.nextInt(VDM.config.WRONGSPAWNCHANCE.get());
                if(chance <= 0) e.getCapability(EntityCapability.INSTANCE).ifPresent(h->{
                    h.setWrongStatus(true);
                });
            }
        }
    }

    @SubscribeEvent
    public static void activateWrong(LivingEvent.LivingUpdateEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        if(livingEntity.tickCount == 100 && livingEntity instanceof Mob && VDM.config.WRONG.get()) {
            AtomicBoolean wrongStatus = new AtomicBoolean(false);
            livingEntity.getCapability(EntityCapability.INSTANCE).ifPresent(h -> {
                wrongStatus.set(h.getWrongStatus());
            });
            EntityType eType = livingEntity.getType();
            if (!livingEntity.level.isClientSide() && wrongStatus.get()){
                Set<WrappedGoal> prioritizedGoals = ObfuscationReflectionHelper.getPrivateValue(GoalSelector.class, ((Mob) livingEntity).targetSelector, "f_25345_");
            ArrayList<Goal> toRemove = new ArrayList<>();
            if(prioritizedGoals != null) {
                for (WrappedGoal prioritizedGoal : prioritizedGoals) {
                    toRemove.add(prioritizedGoal.getGoal());
                }
            }
            for(Goal goal : toRemove){
                ((Mob) livingEntity).targetSelector.removeGoal(goal);
            }
                    ((Mob) livingEntity).targetSelector.addGoal(3, new NearestAttackableTargetGoal<Player>((Mob) livingEntity, Player.class, true, false));
            }
        }
    }

    @SubscribeEvent
    public static void activatePandemic(LivingDamageEvent event){
        if(event.getEntityLiving() instanceof Player && event.getSource().getDirectEntity() instanceof Mob && VDM.isCalyxLoaded && VDM.config.PANDEMIC.get()){
            event.getEntityLiving().getCapability(InfectionManagerCapability.INSTANCE).ifPresent(h->{
                int roll = event.getEntityLiving().getRandom().nextInt(20);
                if(roll <= 1){
                    roll = event.getEntityLiving().getRandom().nextInt(60-15) + 15;
                    int armor = event.getEntityLiving().getArmorValue();
                    double resist = h.getResistance();
                    double armorInfectResist = ImmortuosCalyx.config.ARMORRESISTMULTIPLIER.get();
                    int conversionThreshold = (int) ((roll - (armor*armorInfectResist))/resist);
                    if(conversionThreshold > event.getEntityLiving().getRandom().nextInt(30)){ // rolls for infection. If random value rolls below threshold, target is at risk of infection.
                        h.setInfectionProgressIfLower(1);
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public static void CelebrationStart(TickEvent.WorldTickEvent event) {
        if (!event.world.isClientSide() && VDM.config.CELEBRATION.get()) {
            event.world.getCapability(WorldCapability.INSTANCE).ifPresent(h -> {
                if(h.isNight() && event.world.isDay()) {
                    h.setisNight(false);
                    int chance = event.world.getRandom().nextInt(VDM.config.CELEBRATIONCHANCE.get());
                    if (chance <= 0) {
                        h.setCelebrationStatus(true);
                        broadcast(event.world.getServer(), new TranslatableComponent("info.villainousdifficultymultipliers.party").withStyle(ChatFormatting.LIGHT_PURPLE));
                    }
                }
                else if(!h.isNight() && !event.world.isDay()){h.setisNight(true);}
            });

        }
    }

    @SubscribeEvent
    public static void Celebration(TickEvent.WorldTickEvent event){
        if (!event.world.isClientSide()){
            event.world.getCapability(WorldCapability.INSTANCE).ifPresent(h->{
                if (h.getCelebrationStatus()){
                    ArrayList<Player> players = (ArrayList<Player>) event.world.players();
                    for(Player player : players){
                        if (!player.hasEffect(MobEffects.HERO_OF_THE_VILLAGE)){
                            player.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 20, 0, false, false));
                        }
                    }
                }
                //celebrations stop at night with npc villagers, lest they want the zombies to get them.
                if(!event.world.isDay() && h.getCelebrationStatus()){
                    h.setCelebrationStatus(false);
                    broadcast(event.world.getServer(), new TranslatableComponent("info.villainousdifficultymultipliers.partyend").withStyle(ChatFormatting.LIGHT_PURPLE));
                }
            });
        }
    }

    @SubscribeEvent
    public static void Rested(PlayerWakeUpEvent event){

        if(!event.wakeImmediately() && !event.getEntityLiving().level.isClientSide() && event.getEntityLiving() instanceof Player && VDM.config.RESTED.get()){
            Player player = (Player) event.getEntityLiving();
            player.setHealth(player.getMaxHealth());
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20*30, 0));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20*30, 0));
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20*30, 1));
            player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 20, 1));
            player.getCapability(PlayerCapability.INSTANCE).ifPresent(h ->{
                h.setBlackEyeStatus(false);
            });
            player.sendMessage(new TranslatableComponent("info.villainousdifficultymultipliers.rested").withStyle(ChatFormatting.GREEN), UUID.randomUUID());
        }
    }

    @SubscribeEvent
    public static void WildMagic(LivingDamageEvent event){
        if(event.getSource().getEntity() instanceof Player && !event.getEntityLiving().level.isClientSide() && VDM.config.WILD.get()){
            Player aggressor = (Player) event.getSource().getEntity();
            DamageSource damageSource = event.getSource();
            if(!damageSource.isExplosion() && !damageSource.isFire() && aggressor != event.getEntityLiving()) {//damage source is (probably) melee
                Random random = new Random();
                if(random.nextInt(VDM.config.WILDCHANCE.get()) == 0){
                    aggressor.sendMessage(new TranslatableComponent("info.villainousdifficultymultipliers.wild").withStyle(ChatFormatting.DARK_PURPLE), UUID.randomUUID());
                    RandomAttackDecider.Activate(event.getEntityLiving().level, aggressor, event.getEntityLiving());
                }
            }
        }
    }


    @SubscribeEvent
    public static void PlayerShoutEvent(ServerChatEvent chatEvent){
        chatEvent.getPlayer().getCapability(PlayerCapability.INSTANCE).ifPresent(h->{
            if(h.getShoutTicks() > 0){
                String msg = chatEvent.getComponent().getString();
                msg = msg.toUpperCase();
                chatEvent.setComponent(new TextComponent(msg));
            }
        });
    }

    @SubscribeEvent
    public static void PlayerTickDown(TickEvent.PlayerTickEvent event){
        if(!event.player.level.isClientSide()) {
            event.player.getCapability(PlayerCapability.INSTANCE).ifPresent(h -> {
                if (h.getShoutTicks() > 0) {
                    h.setShoutTicks(h.getShoutTicks() - 1);
                }
            });
        }
    }

    private static void agecheck(int age, LivingEntity livingEntity){
        if(age >= VDM.config.MAXIMUMAGE.get()) livingEntity.remove(Entity.RemovalReason.DISCARDED);
    }

    private static Item MusicDisc(){
        ArrayList<Item> music = new ArrayList<Item>(Arrays.asList(Items.MUSIC_DISC_11, Items.MUSIC_DISC_13, Items.MUSIC_DISC_BLOCKS, Items.MUSIC_DISC_CAT, Items.MUSIC_DISC_CHIRP, Items.MUSIC_DISC_FAR, Items.MUSIC_DISC_MALL, Items.MUSIC_DISC_MELLOHI, Items.MUSIC_DISC_STAL, Items.MUSIC_DISC_STRAD, Items.MUSIC_DISC_WAIT, Items.MUSIC_DISC_WARD));
        Random random = new Random();
        int select = random.nextInt(music.size());
        if(select == music.size()) select--;
        return music.get(select);
    }

    @SubscribeEvent
    public static void Inferno(LivingDamageEvent event){
        if(event.getSource().equals(DamageSource.ON_FIRE) && !event.getEntityLiving().level.isClientSide && VDM.config.INFERNO.get()){
            event.setAmount(event.getAmount() * 4);
        }
    }


    @SubscribeEvent
    public static void EruptiveSwarm(LivingDamageEvent event){
        if(event.getSource().getEntity() != null && event.getSource().getEntity().getType() == EntityType.BEE && !event.getEntityLiving().level.isClientSide && VDM.config.ERUPTIVESWARM.get() && !event.getSource().isExplosion()){
            event.getSource().getEntity().level.explode(event.getSource().getEntity(), event.getSource().getEntity().getX(), event.getSource().getEntity().getY(), event.getSource().getEntity().getZ(), VDM.config.ERUPTIVESWARMSIZE.get(), Explosion.BlockInteraction.NONE);
        }
    }



    private static void broadcast(MinecraftServer server, Component translationTextComponent){
        server.getPlayerList().broadcastMessage(translationTextComponent, ChatType.CHAT, UUID.randomUUID());
    }



}
