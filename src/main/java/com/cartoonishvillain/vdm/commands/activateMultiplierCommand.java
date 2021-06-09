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
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.*;

import java.util.UUID;

public class activateMultiplierCommand implements Command<CommandSource> {
    private static final activateMultiplierCommand CMD = new activateMultiplierCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher){
        return Commands.literal("activate")
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
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.blackeye"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setBlackEye(true);});
                break;
            case "cannon":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.cannon"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setCannon(true);});
                break;
            case "venom":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.venom"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setVenom(true);});
                break;
            case "shift":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.shift"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setShift(true);});
                break;
            case "karmicjustice":
            case "karmic_justice":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.karmicjustice"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setKarmicJustice(true);});
                break;
            case "aging":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.aging"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setAging(true);});
                break;
            case "softskin":
            case "soft_skin":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.softskin"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setSoftSkin(true);});
                break;
            case "fatigue":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.fatigue"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setFatigue(true);});
                break;
            case "heroic":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.allwarning").withStyle(TextFormatting.RED, TextFormatting.BOLD));
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.allon"));
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setBlackEye(true);});
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setCannon(true);});
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setVenom(true);});
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setShift(true);});
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setKarmicJustice(true);});
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setAging(true);});
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setSoftSkin(true);});
                context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{h.setFatigue(true);});
                break;
            default:
                context.getSource().sendSuccess(new TranslationTextComponent("activation.villainousdifficultymultipliers.invalid").withStyle(TextFormatting.RED), false);
                context.getSource().sendSuccess(new TranslationTextComponent("error.villainousdifficultymultipliers.listmultipliers"), false);
                break;
        }
        return 0;
    }
    private void broadcast(MinecraftServer server, ITextComponent translationTextComponent){
        server.getPlayerList().broadcastMessage(translationTextComponent, ChatType.CHAT, UUID.randomUUID());
    }
}
