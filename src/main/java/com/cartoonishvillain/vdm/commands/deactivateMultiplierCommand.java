package com.cartoonishvillain.vdm.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;

public class deactivateMultiplierCommand implements Command<CommandSource> {
    private static final deactivateMultiplierCommand CMD = new deactivateMultiplierCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher){
        return Commands.literal("deactivate")
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
                context.getSource().sendSuccess(new TranslationTextComponent("deactivation.villainousdifficultymultipliers.blackeye"), false);
                break;
            case "cannon":
                context.getSource().sendSuccess(new TranslationTextComponent("deactivation.villainousdifficultymultipliers.cannon"), false);
                break;
            case "famine":
                context.getSource().sendSuccess(new TranslationTextComponent("deactivation.villainousdifficultymultipliers.famine"), false);
                break;
            case "venom":
                context.getSource().sendSuccess(new TranslationTextComponent("deactivation.villainousdifficultymultipliers.venom"), false);
                break;
            case "shift":
                context.getSource().sendSuccess(new TranslationTextComponent("deactivation.villainousdifficultymultipliers.shift"), false);
                break;
            case "karmicjustice":
            case "karmic_justice":
                context.getSource().sendSuccess(new TranslationTextComponent("deactivation.villainousdifficultymultipliers.karmicjustice"), false);
                break;
            case "aging":
                context.getSource().sendSuccess(new TranslationTextComponent("deactivation.villainousdifficultymultipliers.aging"), false);
                break;
            case "softskin":
            case "soft_skin":
                context.getSource().sendSuccess(new TranslationTextComponent("deactivation.villainousdifficultymultipliers.softskin"), false);
                break;
            case "fatigue":
                context.getSource().sendSuccess(new TranslationTextComponent("deactivation.villainousdifficultymultipliers.fatigue"), false);
                break;
            default:
                context.getSource().sendSuccess(new TranslationTextComponent("deactivation.villainousdifficultymultipliers.invalid"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("help.villainousdifficultymultipliers.listmultiplierslabel"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("help.villainousdifficultymultipliers.listmultipliers"), false);

                break;
        }

        return 0;
    }
}
