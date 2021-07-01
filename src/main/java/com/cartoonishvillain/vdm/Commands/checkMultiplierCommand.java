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
            case "black eye":
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
            case "karmic justice":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Karmic Justice=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.karmicjustice"), false);
                break;
            case "aging":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Aging=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.aging"), false);
                break;
            case "softskin":
            case "soft_skin":
            case "soft skin":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Soft Skin=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.softskin"), false);
                break;
            case "fatigue":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Fatigue=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.fatigue"), false);
                break;
            case "hardened":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Hardened=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.hardened"), false);
                break;
            case "heroic":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Heroic=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.heroic"), false);
                break;
            case "keystothecity":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Keys to the City=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.keystothecity"), false);
            case "anger":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Anger=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.anger"), false);
                break;
            case "unstable":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Unstable=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.unstable"), false);
                break;
            case "kinetic":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Kinetic=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.kinetic"), false);
                break;
            case "undying":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Undying=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.undying"), false);
                break;
            case "flammable":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Flammable=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.flammable"), false);
                break;
            case "fuelefficient":
            case "fuel_efficient":
            case "fuel efficient":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Fuel Efficient=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.fuelefficient"), false);
                break;
            case "blacksmithing":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Blacksmithing=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.blacksmithing"), false);
                break;
            case "warranty":
                context.getSource().sendSuccess(new StringTextComponent(TextFormatting.BOLD + "-=Warranty=-"), false);
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.warranty"), false);
                break;
            default:
                context.getSource().sendSuccess(new TranslationTextComponent("check.villainousdifficultymultipliers.invalid").withStyle(TextFormatting.RED), false);
                context.getSource().sendSuccess(new TranslationTextComponent("error.villainousdifficultymultipliers.listmultipliers"), false);
                break;
        }
        return 0;
    }
}
