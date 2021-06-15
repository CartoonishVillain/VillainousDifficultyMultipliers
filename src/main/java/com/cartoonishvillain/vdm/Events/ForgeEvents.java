package com.cartoonishvillain.vdm.Events;

import com.cartoonishvillain.vdm.Capabilities.EntityCapabilities.EntityCapability;
import com.cartoonishvillain.vdm.Capabilities.EntityCapabilities.EntityCapabilityManager;
import com.cartoonishvillain.vdm.Capabilities.PlayerCapabilities.PlayerCapability;
import com.cartoonishvillain.vdm.Capabilities.PlayerCapabilities.PlayerCapabilityManager;
import com.cartoonishvillain.vdm.Commands.CommandManager;
import com.cartoonishvillain.vdm.Entities.Goals.CrossbowAngerManagement;
import com.cartoonishvillain.vdm.Entities.Goals.RangedAngerManagment;
import com.cartoonishvillain.vdm.Fatiguedamage;
import com.cartoonishvillain.vdm.VDM;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Explosion;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.*;
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
                        boolean loot = true;
                        if (event.getSource().getEntity() == creeperEntity){loot = false;} //creeper naturally exploded. No loot!
                        Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(creeperEntity.level, creeperEntity) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
                        float f = creeperEntity.isPowered() ? 2.0F : 1.0F;
                        Vector3d vector3d = new Vector3d(creeperEntity.getX(), creeperEntity.getY(), creeperEntity.getZ());
                        creeperEntity.level.explode(creeperEntity, creeperEntity.getX(), creeperEntity.getY(), creeperEntity.getZ(), (float)3 * f, explosion$mode);
                        creeperEntity.remove();
                        //Phase 2 - artificial loot table.
                        if(loot){
                            Entity aggressor = event.getSource().getEntity();
                            if(aggressor instanceof PlayerEntity || aggressor instanceof WolfEntity){
                                ExperienceOrbEntity experienceOrbEntity = new ExperienceOrbEntity(EntityType.EXPERIENCE_ORB, aggressor.level);
                                experienceOrbEntity.value = 5;
                                ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, aggressor.level);
                                int maxgun = 2;
                                if(aggressor instanceof PlayerEntity){
                                    Map<Enchantment, Integer> map =  EnchantmentHelper.getEnchantments(((PlayerEntity) aggressor).getMainHandItem());
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
                            else if(aggressor instanceof SkeletonEntity){
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
                            }else if(aggressor instanceof CreeperEntity && ((CreeperEntity) aggressor).canDropMobsSkull()){
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

    @SubscribeEvent
    public static void Shift(EntityJoinWorldEvent event){
        if(!event.getWorld().isClientSide() && event.getWorld().getServer().getPlayerCount() != 0){
                if(VDM.config.SHIFT.get()) {
                    if (event.getEntity().getType() == EntityType.ZOMBIE) {
                        ZombieEntity entity = (ZombieEntity) event.getEntity();
                        Random random = new Random();
                        Vector3d vector3d = entity.position();

                        ItemStack mainHand = entity.getItemInHand(Hand.MAIN_HAND);
                        ItemStack offHand = entity.getItemInHand(Hand.OFF_HAND);
                        ItemStack Helm = entity.getItemBySlot(EquipmentSlotType.HEAD);
                        ItemStack Chest = entity.getItemBySlot(EquipmentSlotType.CHEST);
                        ItemStack Leg = entity.getItemBySlot(EquipmentSlotType.LEGS);
                        ItemStack Boots = entity.getItemBySlot(EquipmentSlotType.FEET);

                        int chance = random.nextInt(12);
                        if (chance < 6) {
                            DrownedEntity newMob = new DrownedEntity(EntityType.DROWNED, entity.level);
                            newMob.setPos(vector3d.x, vector3d.y, vector3d.z);
                            newMob.setItemInHand(Hand.MAIN_HAND, mainHand);
                            newMob.setItemInHand(Hand.OFF_HAND, offHand);
                            newMob.setItemSlot(EquipmentSlotType.HEAD, Helm);
                            newMob.setItemSlot(EquipmentSlotType.CHEST, Chest);
                            newMob.setItemSlot(EquipmentSlotType.LEGS, Leg);
                            newMob.setItemSlot(EquipmentSlotType.FEET, Boots);
                            entity.level.addFreshEntity(newMob);
                            entity.setPos(entity.getX(), -1, entity.getZ());
                            entity.kill();
                        } else if (chance < 11) {
                            HuskEntity newMob = new HuskEntity(EntityType.HUSK, entity.level);
                            newMob.setPos(vector3d.x, vector3d.y, vector3d.z);
                            entity.level.addFreshEntity(newMob);
                            entity.setPos(entity.getX(), -1, entity.getZ());
                            entity.kill();
                        } else {
                            ZombieVillagerEntity newMob = new ZombieVillagerEntity(EntityType.ZOMBIE_VILLAGER, entity.level);
                            newMob.setPos(vector3d.x, vector3d.y, vector3d.z);
                            entity.level.addFreshEntity(newMob);
                            entity.setPos(entity.getX(), -1, entity.getZ());
                            entity.kill();
                        }
                    }
                    if (event.getEntity().getType() == EntityType.SKELETON) {
                        SkeletonEntity entity = (SkeletonEntity) event.getEntity();
                        Vector3d vector3d = entity.position();

                        ItemStack mainHand = entity.getItemInHand(Hand.MAIN_HAND);
                        ItemStack offHand = entity.getItemInHand(Hand.OFF_HAND);
                        ItemStack Helm = entity.getItemBySlot(EquipmentSlotType.HEAD);
                        ItemStack Chest = entity.getItemBySlot(EquipmentSlotType.CHEST);
                        ItemStack Leg = entity.getItemBySlot(EquipmentSlotType.LEGS);
                        ItemStack Boots = entity.getItemBySlot(EquipmentSlotType.FEET);

                        StrayEntity newMob = new StrayEntity(EntityType.STRAY, entity.level);
                        newMob.setPos(vector3d.x, vector3d.y, vector3d.z);

                        newMob.teleportTo(vector3d.x, vector3d.y, vector3d.z);
                        newMob.setItemInHand(Hand.MAIN_HAND, mainHand);
                        newMob.setItemInHand(Hand.OFF_HAND, offHand);
                        newMob.setItemSlot(EquipmentSlotType.HEAD, Helm);
                        newMob.setItemSlot(EquipmentSlotType.CHEST, Chest);
                        newMob.setItemSlot(EquipmentSlotType.LEGS, Leg);
                        newMob.setItemSlot(EquipmentSlotType.FEET, Boots);

                        entity.setPos(entity.getX(), -1, entity.getZ());
                        entity.level.addFreshEntity(newMob);
                        entity.kill();
                    }
                    if (event.getEntity().getType() == EntityType.CREEPER) {
                        CreeperEntity creeperEntity = (CreeperEntity) event.getEntity();
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

    @SubscribeEvent
    public static void Hardened(EntityJoinWorldEvent event){
        if(!event.getEntity().level.isClientSide() && event.getEntity() instanceof MonsterEntity){
            if(VDM.config.HARDENED.get()) {
                float health = ((MonsterEntity) event.getEntity()).getHealth() * 0.5f;
                ModifiableAttributeInstance modifiableAttributeInstance = ((MonsterEntity) event.getEntity()).getAttribute(Attributes.MAX_HEALTH);
                if (modifiableAttributeInstance == null) {
                    return;
                }
                modifiableAttributeInstance.addTransientModifier(new AttributeModifier(UUID.fromString("D6F0BA2-1186-46AC-B896-C61C5CEE99CC"), "Hardened health boost", health, AttributeModifier.Operation.ADDITION));
                ((MonsterEntity) event.getEntity()).setHealth(((MonsterEntity) event.getEntity()).getHealth() * 1.5f);
            }
        }
    }

    @SubscribeEvent
    public static void Anger(EntityJoinWorldEvent event){
        if(!event.getEntity().level.isClientSide()){
            if(VDM.config.ANGER.get()) {
                if (event.getEntity().getType().equals(EntityType.SKELETON) || event.getEntity().getType().equals(EntityType.STRAY)) {
                    AbstractSkeletonEntity abstractSkeletonEntity = (AbstractSkeletonEntity) event.getEntity();
                    try {
                        RangedBowAttackGoal<AbstractSkeletonEntity> rangedBowAttackGoal = ObfuscationReflectionHelper.getPrivateValue(AbstractSkeletonEntity.class, abstractSkeletonEntity, "field_85037_d");
                        if (abstractSkeletonEntity.level.getDifficulty() != Difficulty.HARD)
                            rangedBowAttackGoal.setMinAttackInterval(30);
                        else rangedBowAttackGoal.setMinAttackInterval(15);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                }
                if(event.getEntity().getType().equals(EntityType.PILLAGER)){
                    PillagerEntity pillagerEntity = (PillagerEntity) event.getEntity();
                    Set<PrioritizedGoal> prioritizedGoals = ObfuscationReflectionHelper.getPrivateValue(GoalSelector.class, pillagerEntity.goalSelector, "field_220892_d");
                    Goal toremove = null;
                    for(PrioritizedGoal prioritizedGoal : prioritizedGoals){
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
                    WitchEntity witchEntity = (WitchEntity) event.getEntity();
                    Set<PrioritizedGoal> prioritizedGoals = ObfuscationReflectionHelper.getPrivateValue(GoalSelector.class, witchEntity.goalSelector, "field_220892_d");
                    Goal toremove = null;
                    for(PrioritizedGoal prioritizedGoal : prioritizedGoals) {
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
                    GhastEntity ghastEntity = (GhastEntity) event.getEntity();
                    ObfuscationReflectionHelper.setPrivateValue(GhastEntity.class, ghastEntity, 5, "field_92014_j");
                }
                if (event.getEntity().getType().equals(EntityType.CREEPER)) {
                    CreeperEntity creeperEntity = (CreeperEntity) event.getEntity();
                    ObfuscationReflectionHelper.setPrivateValue(CreeperEntity.class, creeperEntity, 5, "field_82226_g");
                }
            }
        }
    }

    private static void agecheck(int age, LivingEntity livingEntity){
        if(age >= 4) livingEntity.remove();
    }
    private static Item MusicDisc(){
        ArrayList<Item> music = new ArrayList<Item>(Arrays.asList(Items.MUSIC_DISC_11, Items.MUSIC_DISC_13, Items.MUSIC_DISC_BLOCKS, Items.MUSIC_DISC_CAT, Items.MUSIC_DISC_CHIRP, Items.MUSIC_DISC_FAR, Items.MUSIC_DISC_MALL, Items.MUSIC_DISC_MELLOHI, Items.MUSIC_DISC_STAL, Items.MUSIC_DISC_STRAD, Items.MUSIC_DISC_WAIT, Items.MUSIC_DISC_WARD));
        Random random = new Random();
        int select = random.nextInt(music.size());
        if(select == music.size()) select--;
        return music.get(select);
    }



}
