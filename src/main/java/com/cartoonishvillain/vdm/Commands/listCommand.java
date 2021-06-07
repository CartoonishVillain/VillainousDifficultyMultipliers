package com.cartoonishvillain.vdm.Commands;

import com.cartoonishvillain.vdm.Capabilities.WorldCapabilities.WorldCapability;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
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

public class listCommand implements Command<CommandSource> {
    private static final listCommand CMD = new listCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher){
        return Commands.literal("list")
                .requires(cs -> cs.hasPermission(0))
                .executes(CMD);


    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        context.getSource().sendSuccess(new TranslationTextComponent("info.villainousdifficultymodifiers.list").withStyle(TextFormatting.YELLOW, TextFormatting.BOLD), false);
        context.getSource().sendSuccess(new StringTextComponent("Black Eye, Cannon, Aging, Fatigue, Karmic Justice, Shift, Soft Skin, Venom"), false);


        return 0;
    }
}