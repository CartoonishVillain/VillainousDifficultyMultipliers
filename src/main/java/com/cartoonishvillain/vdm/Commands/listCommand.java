package com.cartoonishvillain.vdm.Commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;

public class listCommand implements Command<CommandSourceStack> {
    private static final listCommand CMD = new listCommand();

    public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher){
        return Commands.literal("list")
                .requires(cs -> cs.hasPermission(0))
                .executes(CMD);
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        context.getSource().sendSuccess(new TranslatableComponent("info.villainousdifficultymultipliers.list").withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD), false);
        context.getSource().sendSuccess(new TranslatableComponent("info.villainousdifficultymultipliers.increasing").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
        context.getSource().sendSuccess(new TextComponent("Aging, Anger, Black Eye, Cannon, Fatigue, Flammable, Hardened, Karmic Justice, Shift, Soft Skin, Unstable, Venom"), false);
        context.getSource().sendSuccess(new TranslatableComponent("info.villainousdifficultymultipliers.heroic"), false);
        context.getSource().sendSuccess(new TranslatableComponent("info.villainousdifficultymultipliers.decreasing").withStyle(ChatFormatting.BLUE, ChatFormatting.BOLD), false);
        context.getSource().sendSuccess(new TextComponent("Blacksmithing, Fuel Efficient, Kinetic, Undying, Warranty"), false);
        context.getSource().sendSuccess(new TranslatableComponent("info.villainousdifficultymultipliers.keys"), false);

        return 0;
    }
}
