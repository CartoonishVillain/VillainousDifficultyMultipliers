package com.cartoonishvillain.vdm.Commands;


import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;

public class checkMultiplierCommand implements Command<CommandSourceStack> {
    private static final checkMultiplierCommand CMD = new checkMultiplierCommand();

    public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher){
        return Commands.literal("check")
                .requires(cs -> cs.hasPermission(0))
                .then(Commands.argument("multiplier", StringArgumentType.word()).executes(CMD)
                );
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String newString = context.getArgument("multiplier", String.class);
        newString = newString.toLowerCase();
        switch (newString){
            case "blackeye":
            case "black_eye":
            case "black eye":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Black Eye=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.blackeye"), false);
                break;
            case "cannon":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Cannon=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.cannon"), false);
                break;
            case "venom":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Venom=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.venom"), false);
                break;
            case "shift":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Shift=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.shift"), false);
                break;
            case "karmicjustice":
            case "karmic_justice":
            case "karmic justice":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Karmic Justice=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.karmicjustice"), false);
                break;
            case "aging":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Aging=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.aging"), false);
                break;
            case "softskin":
            case "soft_skin":
            case "soft skin":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Soft Skin=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.softskin"), false);
                break;
            case "fatigue":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Fatigue=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.fatigue"), false);
                break;
            case "hardened":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Hardened=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.hardened"), false);
                break;
            case "heroic":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Heroic=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.heroic"), false);
                break;
            case "keystothecity":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Keys to the City=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.keystothecity"), false);
            case "anger":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Anger=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.anger"), false);
                break;
            case "unstable":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Unstable=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.unstable"), false);
                break;
            case "kinetic":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Kinetic=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.kinetic"), false);
                break;
            case "undying":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Undying=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.undying"), false);
                break;
            case "flammable":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Flammable=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.flammable"), false);
                break;
            case "fuelefficient":
            case "fuel_efficient":
            case "fuel efficient":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Fuel Efficient=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.fuelefficient"), false);
                break;
            case "blacksmithing":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Blacksmithing=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.blacksmithing"), false);
                break;
            case "warranty":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Warranty=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.warranty"), false);
                break;
            case "vegetarian":
                context.getSource().sendSuccess(new TextComponent(ChatFormatting.BOLD + "-=Vegetarian=-"), false);
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.vegetarian"), false);
                break;
            default:
                context.getSource().sendSuccess(new TranslatableComponent("check.villainousdifficultymultipliers.invalid").withStyle(ChatFormatting.RED), false);
                context.getSource().sendSuccess(new TranslatableComponent("error.villainousdifficultymultipliers.listmultipliers"), false);
                break;
        }
        return 0;
    }
}
