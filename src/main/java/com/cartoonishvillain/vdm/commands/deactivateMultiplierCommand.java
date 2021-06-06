package com.cartoonishvillain.vdm.Commands;

import com.cartoonishvillain.vdm.Capabilities.WorldCapabilities.WorldCapability;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Predicate;

public class deactivateMultiplierCommand implements Command<CommandSource> {
    private static final deactivateMultiplierCommand CMD = new deactivateMultiplierCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher){
        return Commands.literal("deactivate")
                .requires(cs -> cs.hasPermission(2))
                .then(Commands.argument("multiplier", StringArgumentType.word()).executes(CMD)
                );
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        String newString = context.getArgument("multiplier", String.class);
        newString = newString.toLowerCase();
        switch (newString){
            case "blackeye":
            case "black_eye":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.blackeye"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setBlackEye(false);});
                break;
            case "cannon":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.cannon"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setCannon(false);});
                break;
            case "famine":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.famine"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setFamine(false);});
                break;
            case "venom":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.venom"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setVenom(false);});
                break;
            case "shift":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.shift"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setShift(false);});
                break;
            case "karmicjustice":
            case "karmic_justice":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.karmicjustice"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setKarmicJustice(false);});
                break;
            case "aging":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.aging"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setAging(false);});
                break;
            case "softskin":
            case "soft_skin":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.softskin"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setSoftSkin(false);});
                break;
            case "fatigue":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.fatigue"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setFatigue(false);});
                break;
            default:
                context.getSource().sendSuccess(new TranslationTextComponent("deactivation.villainousdifficultymultipliers.invalid").withStyle(TextFormatting.RED), false);
                context.getSource().sendSuccess(new TranslationTextComponent("error.villainousdifficultymultipliers.listmultipliers"), false);
                break;
        }

        return 0;
    }

    private void broadcast(MinecraftServer server, TranslationTextComponent translationTextComponent){
        server.getPlayerList().broadcastMessage(translationTextComponent, ChatType.CHAT, UUID.randomUUID());
    }
}
