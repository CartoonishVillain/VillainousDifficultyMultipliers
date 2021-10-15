package com.cartoonishvillain.vdm.Commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class CommandManager {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        LiteralCommandNode<CommandSourceStack> commander = dispatcher.register(Commands.literal("vdm").then(helpCommand.register(dispatcher)).then(activeListCommand.register(dispatcher)).then(listCommand.register(dispatcher)));    }
}
