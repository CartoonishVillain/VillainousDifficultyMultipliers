package com.cartoonishvillain.vdm.Commands;


import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class checkMultiplierCommand implements Command<CommandSource> {
    private static final checkMultiplierCommand CMD = new checkMultiplierCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher){
        return Commands.literal("check")
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
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Black Eye=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.blackeye"), false);
                break;
            case "cannon":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Cannon=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.cannon"), false);
                break;
            case "venom":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Venom=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.venom"), false);
                break;
            case "shift":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Shift=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.shift"), false);
                break;
            case "karmicjustice":
            case "karmic_justice":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Karmic Justice=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.karmicjustice"), false);
                break;
            case "aging":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Aging=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.aging"), false);
                break;
            case "softskin":
            case "soft_skin":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Soft Skin=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.softskin"), false);
                break;
            case "fatigue":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Fatigue=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.fatigue"), false);
                break;
            case "heroic":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Heroic=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.heroic"), false);
                break;
            default:
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.invalid").withStyle(TextFormatting.RED), false);
                context.getSource().sendSuccess(new TranslationTextComponent("error.villainousdifficultymultipliers.listmultipliers"), false);
                break;
        }
        return 0;
    }
}
