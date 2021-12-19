package com.cartoonishvillain.vdm.commands;


import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;

import java.util.UUID;

public class CheckMultiplierCommand  {

    public static void register(CommandDispatcher<CommandSourceStack> source) {
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
                })).then(Commands.literal("inferno").executes(context -> {
                    return check(context.getSource(), "inferno");
                })).then(Commands.literal("eruptiveswarm").executes(context -> {
                    return check(context.getSource(), "eruptiveswarm");}
                ))));

        //Temporarily removed.
//        .then(Commands.literal("shift").executes(context -> {
//            return check(context.getSource(), "shift");
//        }))
    }


    private static int check(CommandSourceStack context, String string){
        switch (string){
            case "inferno":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Inferno=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.inferno"), false);
                break;
            case "eruptiveswarm":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Eruptive Swarm=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.eruptiveswarm"), false);
                break;
            case "blackeye":
            case "black_eye":
            case "black eye":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Black Eye=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.blackeye"), false);
                break;
            case "cannon":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Cannon=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.cannon"), false);
                break;
            case "venom":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Venom=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.venom"), false);
                break;
            case "shift":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Shift=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.shift"), false);
                break;
            case "karmicjustice":
            case "karmic_justice":
            case "karmic justice":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Karmic Justice=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.karmicjustice"), false);
                break;
            case "aging":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Aging=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.aging"), false);
                break;
            case "softskin":
            case "soft_skin":
            case "soft skin":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Soft Skin=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.softskin"), false);
                break;
            case "fatigue":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Fatigue=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.fatigue"), false);
                break;
            case "hardened":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Hardened=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.hardened"), false);
                break;
            case "heroic":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Heroic=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.heroic"), false);
                break;
            case "keystothecity":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Keys to the City=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.keystothecity"), false);
            case "anger":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Anger=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.anger"), false);
                break;
            case "unstable":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Unstable=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.unstable"), false);
                break;
            case "kinetic":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Kinetic=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.kinetic"), false);
                break;
            case "undying":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Undying=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.undying"), false);
                break;
            case "flammable":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Flammable=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.flammable"), false);
                break;
            case "fuelefficient":
            case "fuel_efficient":
            case "fuel efficient":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Fuel Efficient=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.fuelefficient"), false);
                break;
            case "blacksmithing":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Blacksmithing=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.blacksmithing"), false);
                break;
            case "warranty":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Warranty=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.warranty"), false);
                break;
            case "vegetarian":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Vegetarian=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.vegetarian"), false);
                break;
            case "wrong":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Wrong=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.wrong"), false);
                break;
            case "celebration":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Celebration=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.celebration"), false);
                break;
            case "rested":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Rested=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.rested"), false);
                break;
            case "wild":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Wild=-"), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.wild"), false);
                break;
            case "pandemic":
                context.sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Pandemic=-"), false);
                context.sendSuccess(new TranslatableComponent("info.villainousdifficultymultipiers.modrequirement", "Immortuos Calyx").withStyle(ChatFormatting.YELLOW), false);
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.pandemic"), false);
                break;
            default:
                context.sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.invalid").withStyle(ChatFormatting.RED), false);
                context.sendSuccess(new TranslatableComponent("error.villainousdifficultymultipliers.listmultipliers"), false);
                break;
        }
        return 0;
    }

    private static void broadcast(MinecraftServer server, TextComponent translationTextComponent){
        server.getPlayerList().broadcastMessage(translationTextComponent, ChatType.CHAT, UUID.randomUUID());
    }
}
