package com.cartoonishvillain.vdm.Commands;

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

public class helpCommand implements Command<CommandSource> {
    private static final helpCommand CMD = new helpCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher){
        return Commands.literal("help")
                .requires(cs -> cs.hasPermission(0))
                .executes(CMD);


    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        context.getSource().sendSuccess(new TranslationTextComponent("help.villainousdifficultymultipliers.help"), false);
        context.getSource().sendSuccess(new TranslationTextComponent("help.villainousdifficultymultipliers.activate"), false);
        context.getSource().sendSuccess(new TranslationTextComponent("help.villainousdifficultymultipliers.deactivate"), false);
        context.getSource().sendSuccess(new TranslationTextComponent("help.villainousdifficultymultipliers.activelist"), false);
        context.getSource().sendSuccess(new TranslationTextComponent("help.villainousdifficultymultipliers.list"), false);
        context.getSource().sendSuccess(new TranslationTextComponent("help.villainousdifficultymultipliers.check"), false);
        return 0;
    }
}
