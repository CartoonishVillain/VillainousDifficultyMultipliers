package com.cartoonishvillain.vdm.Commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;

public class helpCommand implements Command<CommandSourceStack> {
    private static final helpCommand CMD = new helpCommand();

    public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher){
        return Commands.literal("help")
                .requires(cs -> cs.hasPermission(0))
                .executes(CMD);


    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        context.getSource().sendSuccess(new TranslatableComponent("help.villainousdifficultymultipliers.help"), false);
        context.getSource().sendSuccess(new TranslatableComponent("help.villainousdifficultymultipliers.activate"), false);
        context.getSource().sendSuccess(new TranslatableComponent("help.villainousdifficultymultipliers.deactivate"), false);
        context.getSource().sendSuccess(new TranslatableComponent("help.villainousdifficultymultipliers.activelist"), false);
        context.getSource().sendSuccess(new TranslatableComponent("help.villainousdifficultymultipliers.list"), false);
        context.getSource().sendSuccess(new TranslatableComponent("help.villainousdifficultymultipliers.check"), false);
        return 0;
    }
}
