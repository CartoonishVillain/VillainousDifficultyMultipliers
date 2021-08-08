package com.cartoonishvillain.vdm.Commands;

import com.cartoonishvillain.vdm.Configs.ServerConfig;
import com.cartoonishvillain.vdm.VDM;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;

import java.util.UUID;

public class activateMultiplierCommand implements Command<CommandSourceStack> {
    private static final activateMultiplierCommand CMD = new activateMultiplierCommand();

    public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher){
        return Commands.literal("activate")
                .requires(cs -> cs.hasPermission(2))
                .then(Commands.argument("multiplier", StringArgumentType.word()).executes(CMD)
                );
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String newString = context.getArgument("multiplier", String.class);
        newString = newString.toLowerCase();
        ServerConfig config = VDM.config;
        switch (newString){
            case "blackeye":
            case "black_eye":
            case "black eye":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.blackeye"));
                VDM.config.BLACKEYE.set(true);
                break;
            case "cannon":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.cannon"));
                VDM.config.CANNON.set(true);
                break;
            case "venom":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.venom"));
                VDM.config.VENOM.set(true);
                break;
            case "shift":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.shift"));
                VDM.config.SHIFT.set(true);
                break;
            case "karmicjustice":
            case "karmic_justice":
            case "karmic justice":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.karmicjustice"));
                VDM.config.KARMICJUSTICE.set(true);
                break;
            case "aging":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.aging"));
                VDM.config.AGING.set(true);
                break;
            case "softskin":
            case "soft_skin":
            case "soft skin":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.softskin"));
                VDM.config.SOFTSKIN.set(true);
                break;
            case "fatigue":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.fatigue"));

                VDM.config.FATIGUE.set(true);
                break;
            case "hardened":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.hardened"));
                    VDM.config.HARDENED.set(true);
                break;
            case "anger":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.anger"));
                    VDM.config.ANGER.set(true);
                break;
            case "unstable":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.unstable"));
                    VDM.config.UNSTABLE.set(true);
                break;
            case "kinetic":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.kinetic"));
                VDM.config.KINETIC.set(true);
                break;
            case "undying":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.undying"));
                VDM.config.UNDYING.set(true);
                break;
            case "fuelefficient":
            case "fuel_efficient":
            case "fuel efficient":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.fuelefficient"));
                VDM.config.FUELEFFICIENT.set(true);
                break;
            case "blacksmithing":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.blacksmithing"));
                VDM.config.BLACKSMITHING.set(true);
                break;
            case "warranty":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.warranty"));
                VDM.config.WARRANTY.set(true);
                break;
            case "flammable":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.flammable"));
                VDM.config.FLAMMABLE.set(true);
                break;
            case "vegetarian":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.vegetarian"));
                VDM.config.VEGETARIAN.set(true);
                break;
            case "heroic":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.allwarning").withStyle(ChatFormatting.RED, ChatFormatting.BOLD));
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.allon"));
                VDM.config.BLACKEYE.set(true);
                VDM.config.CANNON.set(true);
                VDM.config.VENOM.set(true);
                VDM.config.SHIFT.set(true);
                VDM.config.KARMICJUSTICE.set(true);
                VDM.config.AGING.set(true);
                VDM.config.SOFTSKIN.set(true);
                VDM.config.FATIGUE.set(true);
                VDM.config.HARDENED.set(true);
                VDM.config.ANGER.set(true);
                VDM.config.UNSTABLE.set(true);
                VDM.config.FLAMMABLE.set(true);
                VDM.config.VEGETARIAN.set(true);
                break;
            case "keystothecity":
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.keysallwarning").withStyle(ChatFormatting.BLUE, ChatFormatting.BOLD));
                broadcast(context.getSource().getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.keysallon"));
                VDM.config.KINETIC.set(true);
                VDM.config.UNDYING.set(true);
                VDM.config.FUELEFFICIENT.set(true);
                VDM.config.BLACKSMITHING.set(true);
                VDM.config.WARRANTY.set(true);
                break;
            default:
                context.getSource().sendSuccess(new TranslatableComponent("activation.villainousdifficultymultipliers.invalid").withStyle(ChatFormatting.RED), false);
                context.getSource().sendSuccess(new TranslatableComponent("error.villainousdifficultymultipliers.listmultipliers"), false);
                break;
        }
        return 0;
    }
    private void broadcast(MinecraftServer server, Component translationTextComponent){
        server.getPlayerList().broadcastMessage(translationTextComponent, ChatType.CHAT, UUID.randomUUID());
    }
}
