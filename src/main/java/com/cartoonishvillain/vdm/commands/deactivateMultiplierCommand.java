package com.cartoonishvillain.vdm.Commands;


import com.cartoonishvillain.vdm.Configs.ServerConfig;
import com.cartoonishvillain.vdm.VDM;
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
import net.minecraft.util.text.ITextComponent;
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
        ServerConfig config = VDM.config;
        newString = newString.toLowerCase();
        switch (newString){
            case "blackeye":
            case "black_eye":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.blackeye"));
                while (config.BLACKEYE.get()){
                VDM.config.BLACKEYE.set(false);}
                break;
            case "cannon":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.cannon"));
                while (config.CANNON.get()){
                VDM.config.CANNON.set(false);}
            case "venom":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.venom"));
                while (config.VENOM.get()){
                VDM.config.VENOM.set(false);}
                break;
            case "shift":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.shift"));
                while (config.SHIFT.get()){
                VDM.config.SHIFT.set(false);}
                break;
            case "karmicjustice":
            case "karmic_justice":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.karmicjustice"));
                while (config.KARMICJUSTICE.get()){
                VDM.config.KARMICJUSTICE.set(false);}
                break;
            case "aging":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.aging"));
                while (config.AGING.get()){
                VDM.config.AGING.set(false);}
                break;
            case "softskin":
            case "soft_skin":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.softskin"));
                while (config.SOFTSKIN.get()){
                VDM.config.SOFTSKIN.set(false);}
                break;
            case "fatigue":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.fatigue"));
                while (config.FATIGUE.get()){
                VDM.config.FATIGUE.set(false);}
                break;
            case "heroic":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.allon"));
                while (config.AGING.get() || config.BLACKEYE.get() || config.CANNON.get() || config.FATIGUE.get() || config.KARMICJUSTICE.get() || config.SHIFT.get() || config.SOFTSKIN.get() || config.VENOM.get()){
                VDM.config.BLACKEYE.set(false);
                VDM.config.CANNON.set(false);
                VDM.config.VENOM.set(false);
                VDM.config.SHIFT.set(false);
                VDM.config.KARMICJUSTICE.set(false);
                VDM.config.AGING.set(false);
                VDM.config.SOFTSKIN.set(false);
                VDM.config.FATIGUE.set(false);}
                break;
            default:
                context.getSource().sendSuccess(new TranslationTextComponent("deactivation.villainousdifficultymultipliers.invalid").withStyle(TextFormatting.RED), false);
                context.getSource().sendSuccess(new TranslationTextComponent("error.villainousdifficultymultipliers.listmultipliers"), false);
                break;
        }

        return 0;
    }

    private void broadcast(MinecraftServer server, ITextComponent translationTextComponent){
        server.getPlayerList().broadcastMessage(translationTextComponent, ChatType.CHAT, UUID.randomUUID());
    }
}
