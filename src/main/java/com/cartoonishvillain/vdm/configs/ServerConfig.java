package com.cartoonishvillain.vdm.configs;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ServerConfig {
    public static final String SCATEGORY_DIFFICULTY_INCREASING = "Difficulty Increasing multiplier";
    public static final String SCATEGORY_DIFFICULTY_DECREASING = "Difficulty Decreasing multiplier";
    public static final String SCATEGORY_DIFFICULTY_EXTERIOR = "Exterior Difficulty multiplier";
    public static final String SCATEGORY_CUSTOM_VALUES = "Custom Multiplier Values";

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
    public ConfigHelper.ConfigValueListener<Boolean> FLAMMABLE;
    public ConfigHelper.ConfigValueListener<Boolean> VEGETARIAN;
    public ConfigHelper.ConfigValueListener<Boolean> WRONG;
    public ConfigHelper.ConfigValueListener<Boolean> INFERNO;
    public ConfigHelper.ConfigValueListener<Boolean> ERUPTIVESWARM;


    public ConfigHelper.ConfigValueListener<Boolean> KINETIC;
    public ConfigHelper.ConfigValueListener<Boolean> UNDYING;
    public ConfigHelper.ConfigValueListener<Boolean> FUELEFFICIENT;
    public ConfigHelper.ConfigValueListener<Boolean> BLACKSMITHING;
    public ConfigHelper.ConfigValueListener<Boolean> WARRANTY;
    public ConfigHelper.ConfigValueListener<Boolean> CELEBRATION;
    public ConfigHelper.ConfigValueListener<Boolean> RESTED;
    public ConfigHelper.ConfigValueListener<Boolean> WILD;

    public ConfigHelper.ConfigValueListener<Boolean> PANDEMIC;

    public ConfigHelper.ConfigValueListener<Integer> MAXIMUMAGE;
    public ConfigHelper.ConfigValueListener<Double> HARDENEDBOOST;
    public ConfigHelper.ConfigValueListener<Double> DAMAGEMULTIPLIER;
    public ConfigHelper.ConfigValueListener<Integer> EXPLOSIONSIZE;
    public ConfigHelper.ConfigValueListener<Integer> ERUPTIVESWARMSIZE;
    public ConfigHelper.ConfigValueListener<Integer> WRONGSPAWNCHANCE;
    public ConfigHelper.ConfigValueListener<Integer> KARMICJUSTICESPAWNCHANCE;
    public ConfigHelper.ConfigValueListener<Integer> FUELEFFICIENTVALUE;
    public ConfigHelper.ConfigValueListener<Integer> WILDCHANCE;
    public ConfigHelper.ConfigValueListener<Integer> CELEBRATIONCHANCE;
    public ConfigHelper.ConfigValueListener<Double> BLACKSMITHINGCHANCE;


    public ServerConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber){
        builder.comment("Enable or Disable Difficulty Increasing Multipliers. True is activated, False is deactivated").push(SCATEGORY_DIFFICULTY_INCREASING);
        this.AGING = subscriber.subscribe(builder.comment("We all get old at some point. Upon breeding a finite number of times, animals and villagers will die of old age.").define("agingMultiplier", false));
        this.BLACKEYE = subscriber.subscribe(builder.comment("When you take damage, your ability to heal is nullified until you hit a monster or a player with a melee attack. Punch 'em in the chin!").define("blackEyeMultiplier", false));
        this.CANNON = subscriber.subscribe(builder.comment("Bombs away! Creepers always explode on death, regardless of method.").define("cannonMultiplier", false));
        this.FATIGUE = subscriber.subscribe(builder.comment("Phantoms are a lame punishment for lack of sleep. Enjoy debuffs and eventual death by lack of sleep with this modifier!").define("fatigueMultiplier", false));
        this.KARMICJUSTICE = subscriber.subscribe(builder.comment("There is a chance any animal usually farmed for food will explode violently when a player hits them. Tread Lightly.").define("karmicJusticeMultiplier", false));
        this.SHIFT = subscriber.subscribe(builder.comment("!!!Temporarily Disabled in Code!!! Fighting the same zombies gets old. Zombies are converted to drowned, husks, or zombie villagers instantly, skeletons are converted to strays instantly, and creepers are always supercharged.").define("shiftMultiplier", false));
        this.SOFTSKIN = subscriber.subscribe(builder.comment("Your particularly soft skin makes you more vulnerable to damage. All incoming damage has an extra added to it.").define("softSkinMultiplier", false));
        this.VENOM = subscriber.subscribe(builder.comment("Cave spiders inflict poison for a brief moment on easy, and both wither and poison on normal and hard. Spiders also get the ability to poison you for a shorter amount of time.").define("venomMultiplier", false));
        this.HARDENED = subscriber.subscribe(builder.comment("All hostile mobs have increased health").define("hardenedMultiplier", false));
        this.ANGER = subscriber.subscribe(builder.comment("Pillagers, Skeletons, Strays, and Witches all have significantly sped up attack rates.").define("angerMultiplier", false));
        this.UNSTABLE = subscriber.subscribe(builder.comment("Creeper explosions and Ghast fireballs have a larger explosion radius. For all your mass terrain destruction needs.").define("unstableMultiplier", false));
        this.FLAMMABLE = subscriber.subscribe(builder.comment("Entities on fire will stay on fire until water is applied (or they have died)").define("flammableMultiplier", false));
        this.VEGETARIAN = subscriber.subscribe(builder.comment("Eating meat makes you feel sick to your stomach. Whether or not it's for moral reasons, health reasons, or anything in between.").define("vegetarianMultiplier", false));
        this.WRONG = subscriber.subscribe(builder.comment("That's just... Wrong - Some neutral mobs may have more aggression towards you.").define("wrongMultiplier", false));
        this.INFERNO = subscriber.subscribe(builder.comment("Burn! BURN! BUUURRRRN! - Fire damage does significantly more damage.").define("infernoMultiplier", false));
        this.ERUPTIVESWARM = subscriber.subscribe(builder.comment("Bees will explode violently upon stinging.").define("eruptiveSwarmMultiplier", false));
        builder.pop();

        builder.comment("Enabled or Disable Difficulty Decreasing Multipliers. True is activated, False is deactivated").push(SCATEGORY_DIFFICULTY_DECREASING);
        this.KINETIC = subscriber.subscribe(builder.comment("Future technology embedded into your skin allows you to store up kinetic energy from attacks to release on your foes on your next attack, adding up to 50 hearts of damage maximum.").define("KineticMultiplier", false));
        this.UNDYING = subscriber.subscribe(builder.comment("When you are about to die, you're instantly brought back to full health. Allows you to get back into the fight immediately, but does still increase your death counters.").define("undyingMultiplier", false));
        this.FUELEFFICIENT = subscriber.subscribe(builder.comment("Modern furnaces can get more mileage out of fuel.").define("fuelEfficientMultiplier", false));
        this.BLACKSMITHING = subscriber.subscribe(builder.comment("Stronger understanding of proper anvil usage makes you less likely to damage it.").define("blacksmithingMultiplier", false));
        this.WARRANTY = subscriber.subscribe(builder.comment("Tools may be replaced when they are destroyed.").define("warrantyMultiplier", false));
        this.CELEBRATION = subscriber.subscribe(builder.comment("Npc villagers may celebrate on any given day").define("celebrationMultiplier", false));
        this.RESTED = subscriber.subscribe(builder.comment("Resting works wonders to heal you...").define("restedMultiplier", false));
        this.WILD = subscriber.subscribe(builder.comment("Your attacks cause a surge of uncontrollable magic to manifest.. for better or worse.").define("wildMultiplier", false));
        builder.pop();

        builder.comment("Exterior multipliers. May be increasing or decreasing. All of these are dependent on mods for registering.").push(SCATEGORY_DIFFICULTY_EXTERIOR);
        this.PANDEMIC = subscriber.subscribe(builder.comment("All living entities that harm you have a chance to infect you with Immortuos Calyx, even if they aren't infected themselves").define("pandemicMultiplier", false));
        builder.pop();

        builder.comment("Additional Multiplier modifications. Want to make multipliers stronger or weaker? Here's where you can do it.").push(SCATEGORY_CUSTOM_VALUES);
        this.MAXIMUMAGE = subscriber.subscribe(builder.comment("The amount of times a creature can breed before dying of old age with the aging multiplier enabled").defineInRange("maximumAge", 4, 1, Integer.MAX_VALUE));
        this.HARDENEDBOOST = subscriber.subscribe(builder.comment("The health multiplier given to monsters when spawned while Hardened is enabled").defineInRange("hardenedBoost", 1.5, 1, Integer.MAX_VALUE));
        this.DAMAGEMULTIPLIER = subscriber.subscribe(builder.comment("The multiplier given to damage taken by players when SoftSkin is enabled").defineInRange("softskinBoost", 1.5, 1, Integer.MAX_VALUE));
        this.EXPLOSIONSIZE = subscriber.subscribe(builder.comment("Explosion size of creepers and ghast fireballs when Unstable is enabled").defineInRange("unstableExplosive", 5, 1, Integer.MAX_VALUE));
        this.ERUPTIVESWARMSIZE = subscriber.subscribe(builder.comment("Explosion size of bees when Eruptive Swarm is enabled").defineInRange("eruptiveSwarm", 4, 1, Integer.MAX_VALUE));
        this.WRONGSPAWNCHANCE = subscriber.subscribe(builder.comment("1 in X chance that a valid neutral mob will be effected by wrong when it is enabled.").defineInRange("wrongChance", 30, 1, Integer.MAX_VALUE));
        this.KARMICJUSTICESPAWNCHANCE = subscriber.subscribe(builder.comment("1 in X chance that a valid passive mob will be effected by karmic justice when it is enabled.").defineInRange("karmicJusticeChance", 20, 1, Integer.MAX_VALUE));
        this.FUELEFFICIENTVALUE = subscriber.subscribe(builder.comment("Fuel efficiency multiplier when fuel efficient is enabled").defineInRange("fuelEfficiencyMultiplier", 4, 1, Integer.MAX_VALUE));
        this.WILDCHANCE = subscriber.subscribe(builder.comment("1 in X chance that an attack will invoke a wild effect when Wild is enabled.").defineInRange("wildMagicChance", 6, 1, Integer.MAX_VALUE));
        this.CELEBRATIONCHANCE = subscriber.subscribe(builder.comment("1 in X chance during the day to activate a celebration when it is enabled").defineInRange("celebrationChance", 9, 1, Integer.MAX_VALUE));
        this.BLACKSMITHINGCHANCE = subscriber.subscribe(builder.comment("Percent chance, divided by 100, that a player damages an anvil with blacksmithing on.").defineInRange("blacksmithingChange", 0.06, 0, 1));
    }
}
