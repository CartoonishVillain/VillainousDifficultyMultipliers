package com.cartoonishvillain.vdm.Commands;

import com.cartoonishvillain.vdm.VDM;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.UUID;

public class DeactivateMultiplierCommand {
    public static void register(CommandDispatcher<CommandSource> source) {
        source.register(Commands.literal("vdm")
                .then(Commands.literal("deactivate").requires(requirement -> {
                    return requirement.hasPermission(2);
                }).then(Commands.literal("anger").executes(context -> {
                    return deactivate(context.getSource(), "anger");
                })).then(Commands.literal("blackeye").executes(context -> {
                    return deactivate(context.getSource(), "blackeye");
                })).then(Commands.literal("cannon").executes(context -> {
                    return deactivate(context.getSource(), "cannon");
                })).then(Commands.literal("venom").executes(context -> {
                    return deactivate(context.getSource(), "venom");
                })).then(Commands.literal("karmicjustice").executes(context -> {
                    return deactivate(context.getSource(), "karmicjustice");
                })).then(Commands.literal("aging").executes(context -> {
                    return deactivate(context.getSource(), "aging");
                })).then(Commands.literal("softskin").executes(context -> {
                    return deactivate(context.getSource(), "softskin");
                })).then(Commands.literal("fatigue").executes(context -> {
                    return deactivate(context.getSource(), "fatigue");
                })).then(Commands.literal("hardened").executes(context -> {
                    return deactivate(context.getSource(), "hardened");
                })).then(Commands.literal("hardened").executes(context -> {
                    return deactivate(context.getSource(), "hardened");
                })).then(Commands.literal("unstable").executes(context -> {
                    return deactivate(context.getSource(), "unstable");
                })).then(Commands.literal("kinetic").executes(context -> {
                    return deactivate(context.getSource(), "kinetic");
                })).then(Commands.literal("undying").executes(context -> {
                    return deactivate(context.getSource(), "undying");
                })).then(Commands.literal("fuelefficient").executes(context -> {
                    return deactivate(context.getSource(), "fuelefficient");
                })).then(Commands.literal("blacksmithing").executes(context -> {
                    return deactivate(context.getSource(), "blacksmithing");
                })).then(Commands.literal("warranty").executes(context -> {
                    return deactivate(context.getSource(), "warranty");
                })).then(Commands.literal("flammable").executes(context -> {
                    return deactivate(context.getSource(), "flammable");
                })).then(Commands.literal("vegetarian").executes(context -> {
                    return deactivate(context.getSource(), "vegetarian");
                })).then(Commands.literal("wrong").executes(context -> {
                    return deactivate(context.getSource(), "wrong");
                })).then(Commands.literal("pandemic").executes(context -> {
                    return deactivate(context.getSource(), "pandemic");
                })).then(Commands.literal("wild").executes(context -> {
                    return deactivate(context.getSource(), "wild");
                })).then(Commands.literal("rested").executes(context -> {
                    return deactivate(context.getSource(), "rested");
                })).then(Commands.literal("celebration").executes(context -> {
                    return deactivate(context.getSource(), "celebration");
                })).then(Commands.literal("heroic").executes(context -> {
                    return deactivate(context.getSource(), "heroic");
                })).then(Commands.literal("keystothecity").executes(context -> {
                    return deactivate(context.getSource(), "keystothecity");
                })).then(Commands.literal("inferno").executes(context -> {
                    return deactivate(context.getSource(), "inferno");
                })).then(Commands.literal("eruptiveswarm").executes(context -> {
                    return deactivate(context.getSource(), "eruptiveswarm");
                }))));

        //Temporarily Removed
//        .then(Commands.literal("shift").executes(context -> {
//        return deactivate(context.getSource(), "shift");
//        }))
    }


    private static int deactivate(CommandSource context, String string){
        switch (string){
            case "inferno":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.inferno"));
                VDM.config.INFERNO.set(false);
                break;
            case "eruptiveswarm":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.eruptiveswarm"));
                VDM.config.ERUPTIVESWARM.set(false);
                break;
            case "blackeye":
            case "black_eye":
            case "black eye":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.blackeye"));
                VDM.config.BLACKEYE.set(false);
                break;
            case "cannon":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.cannon"));
                VDM.config.CANNON.set(false);
            case "venom":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.venom"));
                VDM.config.VENOM.set(false);
                break;
            case "shift":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.shift"));
                VDM.config.SHIFT.set(false);
                break;
            case "karmicjustice":
            case "karmic_justice":
            case "karmic justice":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.karmicjustice"));
                VDM.config.KARMICJUSTICE.set(false);
                break;
            case "aging":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.aging"));
                VDM.config.AGING.set(false);
                break;
            case "softskin":
            case "soft_skin":
            case "soft skin":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.softskin"));
                VDM.config.SOFTSKIN.set(false);
                break;
            case "fatigue":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.fatigue"));
                VDM.config.FATIGUE.set(false);
                break;
            case "hardened":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.hardened"));
                VDM.config.HARDENED.set(false);
                break;
            case "anger":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.anger"));
                VDM.config.ANGER.set(false);
                break;
            case "unstable":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.unstable"));
                VDM.config.UNSTABLE.set(false);
                break;
            case "kinetic":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.kinetic"));
                VDM.config.KINETIC.set(false);
                break;
            case "undying":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.undying"));
                VDM.config.UNDYING.set(false);
                break;
            case "flammable":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.flammable"));
                VDM.config.FLAMMABLE.set(false);
                break;
            case "fuelefficient":
            case "fuel_efficient":
            case "fuel efficient":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.fuelefficient"));
                VDM.config.FUELEFFICIENT.set(false);
                break;
            case "blacksmithing":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.blacksmithing"));
                VDM.config.BLACKSMITHING.set(false);
                break;
            case "warranty":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.warranty"));
                VDM.config.WARRANTY.set(false);
                break;
            case "vegetarian":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.vegetarian"));
                VDM.config.VEGETARIAN.set(false);
                break;
            case "wrong":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.wrong"));
                VDM.config.WRONG.set(false);
                break;
            case "pandemic":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.pandemic"));
                VDM.config.PANDEMIC.set(false);
                break;
            case "celebration":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.celebration"));
                VDM.config.CELEBRATION.set(false);
                break;
            case "rested":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.rested"));
                VDM.config.RESTED.set(false);
                break;
            case "wild":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.wild"));
                VDM.config.RESTED.set(false);
                break;
            case "heroic":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.allon"));
                VDM.config.BLACKEYE.set(false);
                VDM.config.CANNON.set(false);
                VDM.config.VENOM.set(false);
                VDM.config.SHIFT.set(false);
                VDM.config.KARMICJUSTICE.set(false);
                VDM.config.AGING.set(false);
                VDM.config.SOFTSKIN.set(false);
                VDM.config.FATIGUE.set(false);
                VDM.config.HARDENED.set(false);
                VDM.config.ANGER.set(false);
                VDM.config.UNSTABLE.set(false);
                VDM.config.FLAMMABLE.set(false);
                VDM.config.WRONG.set(false);
                VDM.config.VEGETARIAN.set(false);
                VDM.config.PANDEMIC.set(false);
                VDM.config.INFERNO.set(true);
                VDM.config.ERUPTIVESWARM.set(true);
                break;
            case "keystothecity":
                broadcast(context.getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.keysallon"));
                VDM.config.KINETIC.set(false);
                VDM.config.UNDYING.set(false);
                VDM.config.FUELEFFICIENT.set(false);
                VDM.config.BLACKSMITHING.set(false);
                VDM.config.WARRANTY.set(false);
                VDM.config.WILD.set(false);
                VDM.config.RESTED.set(false);
                VDM.config.CELEBRATION.set(false);
                break;
            default:
                context.sendSuccess(new TranslationTextComponent("deactivation.villainousdifficultymultipliers.invalid").withStyle(TextFormatting.RED), false);
                context.sendSuccess(new TranslationTextComponent("error.villainousdifficultymultipliers.listmultipliers"), false);
                break;
        }
        return 0;
    }

    private static void broadcast(MinecraftServer server, ITextComponent translationTextComponent){
        server.getPlayerList().broadcastMessage(translationTextComponent, ChatType.CHAT, UUID.randomUUID());
    }
}
