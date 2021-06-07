package com.cartoonishvillain.vdm.Commands;

import com.cartoonishvillain.vdm.Capabilities.WorldCapabilities.WorldCapability;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class activeListCommand implements Command<CommandSource> {
    private static final activeListCommand CMD = new activeListCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher){
        return Commands.literal("activelist")
                .requires(cs -> cs.hasPermission(0))
                .executes(CMD);


    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        AtomicInteger count = new AtomicInteger(0);
        AtomicReference<String> string = new AtomicReference<>("");
        context.getSource().getLevel().getCapability(WorldCapability.INSTANCE).ifPresent(h->{
            if(h.getBlackEye()) {string.set(string.get() + "Black Eye, "); count.set(count.get()+1);}
            if(h.getCannon()) {string.set(string.get() + "Cannon, "); count.set(count.get()+1);}
            if(h.getAging()) {string.set(string.get() + "Aging, "); count.set(count.get()+1);}
            if(h.getFatigue()) {string.set(string.get() + "Fatigue, "); count.set(count.get()+1);}
            if(h.getKarmicJustice()) {string.set(string.get() + "Karmic Justice, "); count.set(count.get()+1);}
            if(h.getShift()) {string.set(string.get() + "Shift, "); count.set(count.get()+1);}
            if(h.getSoftSkin()) {string.set(string.get() + "Soft Skin, "); count.set(count.get()+1);}
            if(h.getVenom()) {string.set(string.get() + "Venom, "); count.set(count.get()+1);}
        });
        String finalString = string.toString();
        if(finalString.length() > 0){
        finalString = finalString.substring(0, finalString.length()-2);
        context.getSource().sendSuccess(new TranslationTextComponent("info.villainousdifficultymultipiers.count", count.get()), false);
        context.getSource().sendSuccess(new StringTextComponent(finalString), false);}
        else{context.getSource().sendSuccess(new TranslationTextComponent("info.villainousdifficultymultipliers.none"), false);}


        return 0;
    }
}
