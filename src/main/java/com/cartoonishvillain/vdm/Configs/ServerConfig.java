package com.cartoonishvillain.vdm.Configs;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ServerConfig {
    public static final String SCATEGORY_DIFFICULTY_INCREASING = "Difficulty Increasing multiplier";
    public ConfigHelper.ConfigValueListener<Boolean> AGING;
    public ConfigHelper.ConfigValueListener<Boolean> BLACKEYE;
    public ConfigHelper.ConfigValueListener<Boolean> CANNON;
    public ConfigHelper.ConfigValueListener<Boolean> FATIGUE;
    public ConfigHelper.ConfigValueListener<Boolean> KARMICJUSTICE;
    public ConfigHelper.ConfigValueListener<Boolean> SHIFT;
    public ConfigHelper.ConfigValueListener<Boolean> SOFTSKIN;
    public ConfigHelper.ConfigValueListener<Boolean> VENOM;
    public ConfigHelper.ConfigValueListener<Boolean> HARDENED;
    public ConfigHelper.ConfigValueListener<Boolean> ANGER;
    public ConfigHelper.ConfigValueListener<Boolean> UNSTABLE;

    public ServerConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber){
        builder.comment("Enable or Disable Difficulty Increasing Multipliers. True is activated, False is deactivated").push(SCATEGORY_DIFFICULTY_INCREASING);
        this.AGING = subscriber.subscribe(builder.comment("We all get old at some point. Upon breeding 4 times, animals and villagers will die of old age.").define("agingMultiplier", false));
        this.BLACKEYE = subscriber.subscribe(builder.comment("When you take damage, your ability to heal is nullified until you hit a monster or a player with a melee attack. Punch 'em in the chin!").define("blackEyeMultiplier", false));
        this.CANNON = subscriber.subscribe(builder.comment("Bombs away! Creepers always explode on death, regardless of method.").define("cannonMultiplier", false));
        this.FATIGUE = subscriber.subscribe(builder.comment("Phantoms are a lame punishment for lack of sleep. Enjoy debuffs and eventual death by lack of sleep with this modifier!").define("fatigueMultiplier", false));
        this.KARMICJUSTICE = subscriber.subscribe(builder.comment("There is a 1 in 20 chance any animal usually farmed for food will explode violently when a player hits them. Tread Lightly.").define("karmicJusticeMultiplier", false));
        this.SHIFT = subscriber.subscribe(builder.comment("Fighting the same zombies gets old. Zombies are converted to drowned, husks, or zombie villagers instantly, skeletons are converted to strays instantly, and creepers are always supercharged.").define("shiftMultiplier", false));
        this.SOFTSKIN = subscriber.subscribe(builder.comment("Your particularly soft skin makes you more vulnerable to damage. All incoming damage has an extra 50% added to it.").define("softSkinMultiplier", false));
        this.VENOM = subscriber.subscribe(builder.comment("Cave spiders inflict poison for a brief moment on easy, and both wither and poison on normal and hard. Spiders also get the ability to poison you for a shorter amount of time.").define("venomMultiplier", false));
        this.HARDENED = subscriber.subscribe(builder.comment("All hostile mobs have increased health").define("hardenedMultiplier", false));
        this.ANGER = subscriber.subscribe(builder.comment("Pillagers, Skeletons, Strays, and Witches all have significantly sped up attack rates.").define("angerMultiplier", false));
        this.UNSTABLE = subscriber.subscribe(builder.comment("Creeper explosions and Ghast fireballs have a larger explosion radius. For all your mass terrain destruction needs.").define("unstableMultiplier", false));
        builder.pop();
    }

}