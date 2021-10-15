package com.cartoonishvillain.vdm.Commands;

import com.cartoonishvillain.vdm.VDM;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.*;

import java.util.UUID;

public class CheckMultiplierCommand {
    public static void register(CommandDispatcher<CommandSource> source) {
        source.register(Commands.literal("vdm")
                .then(Commands.literal("check").requires(requirement -> {
                    return requirement.hasPermission(2);
                }).then(Commands.literal("anger").executes(context -> {
                    return check(context.getSource(), "anger");
                })).then(Commands.literal("blackeye").executes(context -> {
                    return check(context.getSource(), "blackeye");
                })).then(Commands.literal("cannon").executes(context -> {
                    return check(context.getSource(), "cannon");
                })).then(Commands.literal("venom").executes(context -> {
                    return check(context.getSource(), "venom");
                })).then(Commands.literal("shift").executes(context -> {
                    return check(context.getSource(), "shift");
                })).then(Commands.literal("karmicjustice").executes(context -> {
                    return check(context.getSource(), "karmicjustice");
                })).then(Commands.literal("aging").executes(context -> {
                    return check(context.getSource(), "aging");
                })).then(Commands.literal("softskin").executes(context -> {
                    return check(context.getSource(), "softskin");
                })).then(Commands.literal("fatigue").executes(context -> {
                    return check(context.getSource(), "fatigue");
                })).then(Commands.literal("hardened").executes(context -> {
                    return check(context.getSource(), "hardened");
                })).then(Commands.literal("hardened").executes(context -> {
                    return check(context.getSource(), "hardened");
                })).then(Commands.literal("unstable").executes(context -> {
                    return check(context.getSource(), "unstable");
                })).then(Commands.literal("kinetic").executes(context -> {
                    return check(context.getSource(), "kinetic");
                })).then(Commands.literal("undying").executes(context -> {
                    return check(context.getSource(), "undying");
                })).then(Commands.literal("fuelefficient").executes(context -> {
                    return check(context.getSource(), "fuelefficient");
                })).then(Commands.literal("blacksmithing").executes(context -> {
                    return check(context.getSource(), "blacksmithing");
                })).then(Commands.literal("warranty").executes(context -> {
                    return check(context.getSource(), "warranty");
                })).then(Commands.literal("flammable").executes(context -> {
                    return check(context.getSource(), "flammable");
                })).then(Commands.literal("vegetarian").executes(context -> {
                    return check(context.getSource(), "vegetarian");
                })).then(Commands.literal("wrong").executes(context -> {
                    return check(context.getSource(), "wrong");
                })).then(Commands.literal("pandemic").executes(context -> {
                    return check(context.getSource(), "pandemic");
                })).then(Commands.literal("wild").executes(context -> {
                    return check(context.getSource(), "wild");
                })).then(Commands.literal("rested").executes(context -> {
                    return check(context.getSource(), "rested");
                })).then(Commands.literal("celebration").executes(context -> {
                    return check(context.getSource(), "celebration");
                })).then(Commands.literal("heroic").executes(context -> {
                    return check(context.getSource(), "heroic");
                })).then(Commands.literal("keystothecity").executes(context -> {
                    return check(context.getSource(), "keystothecity");
                }))));
    }


    private static int check(CommandSource context, String string){
        switch (string){
            case "blackeye":
            case "black_eye":
            case "black eye":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Black Eye=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.blackeye"), false);
                break;
            case "cannon":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Cannon=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.cannon"), false);
                break;
            case "venom":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Venom=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.venom"), false);
                break;
            case "shift":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Shift=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.shift"), false);
                break;
            case "karmicjustice":
            case "karmic_justice":
            case "karmic justice":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Karmic Justice=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.karmicjustice"), false);
                break;
            case "aging":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Aging=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.aging"), false);
                break;
            case "softskin":
            case "soft_skin":
            case "soft skin":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Soft Skin=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.softskin"), false);
                break;
            case "fatigue":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Fatigue=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.fatigue"), false);
                break;
            case "hardened":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Hardened=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.hardened"), false);
                break;
            case "heroic":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Heroic=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.heroic"), false);
                break;
            case "keystothecity":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Keys to the City=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.keystothecity"), false);
            case "anger":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Anger=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.anger"), false);
                break;
            case "unstable":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Unstable=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.unstable"), false);
                break;
            case "kinetic":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Kinetic=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.kinetic"), false);
                break;
            case "undying":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Undying=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.undying"), false);
                break;
            case "flammable":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Flammable=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.flammable"), false);
                break;
            case "fuelefficient":
            case "fuel_efficient":
            case "fuel efficient":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Fuel Efficient=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.fuelefficient"), false);
                break;
            case "blacksmithing":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Blacksmithing=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.blacksmithing"), false);
                break;
            case "warranty":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Warranty=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.warranty"), false);
                break;
            case "vegetarian":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Vegetarian=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.vegetarian"), false);
                break;
            case "wrong":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Wrong=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.wrong"), false);
                break;
            case "celebration":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Celebration=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.celebration"), false);
                break;
            case "rested":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Rested=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.rested"), false);
                break;
            case "wild":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Wild=-"), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.wild"), false);
                break;
            case "pandemic":
                context.sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Pandemic=-"), false);
                context.sendSuccess(new TranslationTextComponent("info.villainousdifficultymultipiers.modrequirement", "Immortuos Calyx").withStyle(TextFormatting.YELLOW), false);
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.pandemic"), false);
                break;
            default:
                context.sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.invalid").withStyle(TextFormatting.RED), false);
                context.sendSuccess(new TranslationTextComponent("error.villainousdifficultymultipliers.listmultipliers"), false);
                break;
        }
        return 0;
    }

    private static void broadcast(MinecraftServer server, ITextComponent translationTextComponent){
        server.getPlayerList().broadcastMessage(translationTextComponent, ChatType.CHAT, UUID.randomUUID());
    }
}
