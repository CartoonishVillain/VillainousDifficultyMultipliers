package com.cartoonishvillain.vdm.Commands;

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

public class listCommand implements Command<CommandSource> {
    private static final listCommand CMD = new listCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher){
        return Commands.literal("list")
                .requires(cs -> cs.hasPermission(0))
                .executes(CMD);
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        context.getSource().sendSuccess(new TranslationTextComponent("info.villainousdifficultymultipliers.list").withStyle(TextFormatting.YELLOW, TextFormatting.BOLD), false);
        context.getSource().sendSuccess(new TranslationTextComponent("info.villainousdifficultymultipliers.increasing").withStyle(TextFormatting.RED, TextFormatting.BOLD), false);
        context.getSource().sendSuccess(new StringTextComponent("Aging, Anger, Black Eye, Cannon, Eruptive Swarm, Fatigue, Flammable, Hardened, Inferno, Karmic Justice, Soft Skin, Unstable, Venom"), false);
        context.getSource().sendSuccess(new TranslationTextComponent("info.villainousdifficultymultipliers.heroic"), false);
        context.getSource().sendSuccess(new TranslationTextComponent("info.villainousdifficultymultipliers.decreasing").withStyle(TextFormatting.BLUE, TextFormatting.BOLD), false);
        context.getSource().sendSuccess(new StringTextComponent("Blacksmithing, Fuel Efficient, Kinetic, Undying, Warranty"), false);
        context.getSource().sendSuccess(new TranslationTextComponent("info.villainousdifficultymultipliers.keys"), false);

        return 0;
    }
}
