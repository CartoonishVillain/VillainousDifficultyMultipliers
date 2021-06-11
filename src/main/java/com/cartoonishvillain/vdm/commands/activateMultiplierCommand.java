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
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

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
        ServerConfig config = VDM.config;
        switch (newString){
            case "blackeye":
            case "black_eye":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.blackeye"));
                while (!config.BLACKEYE.get()){
                VDM.config.BLACKEYE.set(true);}
                break;
            case "cannon":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.cannon"));
                while (!config.CANNON.get()){
                VDM.config.CANNON.set(true);}
                break;
            case "venom":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.venom"));
                while (!config.VENOM.get()){
                VDM.config.VENOM.set(true);}
                break;
            case "shift":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.shift"));
                while (!config.SHIFT.get()){
                VDM.config.SHIFT.set(true);}
                break;
            case "karmicjustice":
            case "karmic_justice":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.karmicjustice"));
                while(!config.KARMICJUSTICE.get()){
                VDM.config.KARMICJUSTICE.set(true);}
                break;
            case "aging":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.aging"));
                while (!config.AGING.get()){
                VDM.config.AGING.set(true);}
                break;
            case "softskin":
            case "soft_skin":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.softskin"));
                while (!config.SOFTSKIN.get()){
                VDM.config.SOFTSKIN.set(true);}
                break;
            case "fatigue":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.fatigue"));
                while (!config.FATIGUE.get()){
                VDM.config.FATIGUE.set(true);}
                break;
            case "hardened":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.hardened"));
                while (!config.HARDENED.get()){
                    VDM.config.HARDENED.set(true);}
                break;
            case "heroic":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.allwarning").withStyle(TextFormatting.RED, TextFormatting.BOLD));
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("activation.villainousdifficultymultipliers.allon"));
                while(!(config.AGING.get() && config.BLACKEYE.get() && config.CANNON.get() && config.FATIGUE.get() && config.KARMICJUSTICE.get() && config.SHIFT.get() && config.SOFTSKIN.get() && config.VENOM.get() && config.HARDENED.get())){
                VDM.config.BLACKEYE.set(true);
                VDM.config.CANNON.set(true);
                VDM.config.VENOM.set(true);
                VDM.config.SHIFT.set(true);
                VDM.config.KARMICJUSTICE.set(true);
                VDM.config.AGING.set(true);
                VDM.config.SOFTSKIN.set(true);
                VDM.config.FATIGUE.set(true);
                VDM.config.HARDENED.set(true);}
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
