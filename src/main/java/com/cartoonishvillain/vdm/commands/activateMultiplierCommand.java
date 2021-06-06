package com.cartoonishvillain.vdm.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;

public class activateMultiplierCommand implements Command<CommandSource> {
    private static final activateMultiplierCommand CMD = new activateMultiplierCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher){
        return Commands.literal("activate")
                .requires(cs -> cs.hasPermission(0))
                .then(Commands.argument("multiplier", StringArgumentType.word()).executes(CMD)
                );
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        String newString = context.getArgument("multiplier", String.class);
        newString = newString.toLowerCase();
        switch (newString){
            case "blackeye":
            case "black_eye":
                context.getSource().sendSuccess(new TranslationTextComponent("activation.villainousdifficultymultipliers.blackeye"), false);
                break;
            case "cannon":
                context.getSource().sendSuccess(new TranslationTextComponent("activation.villainousdifficultymultipliers.cannon"), false);
                break;
            case "famine":
                context.getSource().sendSuccess(new TranslationTextComponent("activation.villainousdifficultymultipliers.famine"), false);
                break;
            case "venom":
                context.getSource().sendSuccess(new TranslationTextComponent("activation.villainousdifficultymultipliers.venom"), false);
                break;
            case "shift":
                context.getSource().sendSuccess(new TranslationTextComponent("activation.villainousdifficultymultipliers.shift"), false);
                break;
            case "karmicjustice":
            case "karmic_justice":
                context.getSource().sendSuccess(new TranslationTextComponent("activation.villainousdifficultymultipliers.karmicjustice"), false);
                break;
            case "aging":
                context.getSource().sendSuccess(new TranslationTextComponent("activation.villainousdifficultymultipliers.aging"), false);
                break;
            case "softskin":
            case "soft_skin":
                context.getSource().sendSuccess(new TranslationTextComponent("activation.villainousdifficultymultipliers.softskin"), false);
                break;
            case "fatigue":
                context.getSource().sendSuccess(new TranslationTextComponent("activation.villainousdifficultymultipliers.fatigue"), false);
                break;
            default:
                context.getSource().sendSuccess(new TranslationTextComponent("activation.villainousdifficultymultipliers.invalid"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("help.villainousdifficultymultipliers.listmultiplierslabel"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("help.villainousdifficultymultipliers.listmultipliers"), false);

                break;
        }

        return 0;
    }
}
