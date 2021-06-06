package com.cartoonishvillain.vdm.commands;

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

public class testCommand implements Command<CommandSource> {
    private static final testCommand CMD = new testCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher){
        return Commands.literal("test")
                .requires(cs -> cs.hasPermission(0))
                .then(Commands.argument("yeetlevel", IntegerArgumentType.integer())
                .executes(CMD));


    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        int yeetlvl = context.getArgument("yeetlevel", int.class);
        if (yeetlvl == 1) context.getSource().sendSuccess(new StringTextComponent("yaaayeeeeeet!"), false);
        else if (yeetlvl == 2) context.getSource().sendSuccess(new StringTextComponent("yote!"), false);
        else context.getSource().sendSuccess(new StringTextComponent(TextFormatting.ITALIC + "Invalid Yeet!"), false);
        return 0;
    }
}
