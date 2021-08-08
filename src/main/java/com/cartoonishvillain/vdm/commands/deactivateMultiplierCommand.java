package com.cartoonishvillain.vdm.Commands;


import com.cartoonishvillain.vdm.Configs.ServerConfig;
import com.cartoonishvillain.vdm.VDM;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.UUID;

public class deactivateMultiplierCommand implements Command<CommandSourceStack> {
    private static final deactivateMultiplierCommand CMD = new deactivateMultiplierCommand();

    public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher){
        return Commands.literal("deactivate")
                .requires(cs -> cs.hasPermission(2))
                .then(Commands.argument("multiplier", StringArgumentType.word()).executes(CMD)
                );
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String newString = context.getArgument("multiplier", String.class);
        ServerConfig config = VDM.config;
        newString = newString.toLowerCase();
        switch (newString){
            case "blackeye":
            case "black_eye":
            case "black eye":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.blackeye"));
                VDM.config.BLACKEYE.set(false);
                break;
            case "cannon":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.cannon"));
                VDM.config.CANNON.set(false);
            case "venom":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.venom"));
                VDM.config.VENOM.set(false);
                break;
            case "shift":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.shift"));
                VDM.config.SHIFT.set(false);
                break;
            case "karmicjustice":
            case "karmic_justice":
            case "karmic justice":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.karmicjustice"));
                VDM.config.KARMICJUSTICE.set(false);
                break;
            case "aging":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.aging"));
                VDM.config.AGING.set(false);
                break;
            case "softskin":
            case "soft_skin":
            case "soft skin":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.softskin"));
                VDM.config.SOFTSKIN.set(false);
                break;
            case "fatigue":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.fatigue"));
                VDM.config.FATIGUE.set(false);
                break;
            case "hardened":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.hardened"));
                    VDM.config.HARDENED.set(false);
                break;
            case "anger":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.anger"));
                    VDM.config.ANGER.set(false);
                break;
            case "unstable":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.unstable"));
                    VDM.config.UNSTABLE.set(false);
                break;
            case "kinetic":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.kinetic"));
                VDM.config.KINETIC.set(false);
                break;
            case "undying":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.undying"));
                VDM.config.UNDYING.set(false);
                break;
            case "flammable":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.flammable"));
                VDM.config.FLAMMABLE.set(false);
                break;
            case "fuelefficient":
            case "fuel_efficient":
            case "fuel efficient":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.fuelefficient"));
                VDM.config.FUELEFFICIENT.set(false);
                break;
            case "blacksmithing":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.blacksmithing"));
                VDM.config.BLACKSMITHING.set(false);
                break;
            case "warranty":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.warranty"));
                VDM.config.WARRANTY.set(false);
                break;
            case "vegetarian":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.vegetarian"));
                VDM.config.VEGETARIAN.set(false);
                break;
            case "wrong":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.wrong"));
                VDM.config.WRONG.set(false);
                break;
            case "pandemic":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.pandemic"));
                VDM.config.PANDEMIC.set(false);
                break;
            case "heroic":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.allon"));
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
                VDM.config.VEGETARIAN.set(false);
                VDM.config.WRONG.set(false);
                VDM.config.PANDEMIC.set(false);

                break;
            case "keystothecity":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.keysallon"));
                VDM.config.KINETIC.set(false);
                VDM.config.UNDYING.set(false);
                VDM.config.FUELEFFICIENT.set(false);
                VDM.config.BLACKSMITHING.set(false);
                VDM.config.WARRANTY.set(false);
                break;
            default:
                context.getSource().sendSuccess(new TranslatableComponent("deactivation.villainousdifficultymultipliers.invalid").withStyle(ChatFormatting.RED), false);
                context.getSource().sendSuccess(new TranslatableComponent("error.villainousdifficultymultipliers.listmultipliers"), false);
                break;
        }

        return 0;
    }

    private void broadcast(MinecraftServer server, Component translationTextComponent){
        server.getPlayerList().broadcastMessage(translationTextComponent, ChatType.CHAT, UUID.randomUUID());
    }
}
