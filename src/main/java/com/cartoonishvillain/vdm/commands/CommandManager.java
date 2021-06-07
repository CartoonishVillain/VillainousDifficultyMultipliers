package com.cartoonishvillain.vdm.Commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class CommandManager {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        LiteralCommandNode<CommandSource> commander = dispatcher.register(Commands.literal("vdm").then(helpCommand.register(dispatcher)).then(activateMultiplierCommand.register(dispatcher)).then(deactivateMultiplierCommand.register(dispatcher)).then(activeListCommand.register(dispatcher)).then(listCommand.register(dispatcher)).then(checkMultiplierCommand.register(dispatcher)));
    }
}
