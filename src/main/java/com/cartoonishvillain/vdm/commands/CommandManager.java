package com.cartoonishvillain.vdm.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class CommandManager {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        LiteralCommandNode<CommandSource> commander = dispatcher.register(Commands.literal("VDM").then(testCommand.register(dispatcher)).then(multiParameterTest.register(dispatcher)).then(activateMultiplierCommand.register(dispatcher)).then(deactivateMultiplierCommand.register(dispatcher)));
    }
}
