package com.cartoonishvillain.vdm.Commands;


import com.cartoonishvillain.vdm.Configs.ServerConfig;
import com.cartoonishvillain.vdm.VDM;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.UUID;

public class deactivateMultiplierCommand implements Command<CommandSource> {
    private static final deactivateMultiplierCommand CMD = new deactivateMultiplierCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher){
        return Commands.literal("deactivate")
                .requires(cs -> cs.hasPermission(2))
                .then(Commands.argument("multiplier", StringArgumentType.word()).executes(CMD)
                );
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        String newString = context.getArgument("multiplier", String.class);
        ServerConfig config = VDM.config;
        newString = newString.toLowerCase();
        switch (newString){
            case "blackeye":
            case "black_eye":
            case "black eye":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.blackeye"));
                VDM.config.BLACKEYE.set(false);
                break;
            case "cannon":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.cannon"));
                VDM.config.CANNON.set(false);
            case "venom":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.venom"));
                VDM.config.VENOM.set(false);
                break;
            case "shift":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.shift"));
                VDM.config.SHIFT.set(false);
                break;
            case "karmicjustice":
            case "karmic_justice":
            case "karmic justice":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.karmicjustice"));
                VDM.config.KARMICJUSTICE.set(false);
                break;
            case "aging":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.aging"));
                VDM.config.AGING.set(false);
                break;
            case "softskin":
            case "soft_skin":
            case "soft skin":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.softskin"));
                VDM.config.SOFTSKIN.set(false);
                break;
            case "fatigue":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.fatigue"));
                VDM.config.FATIGUE.set(false);
                break;
            case "hardened":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.hardened"));
                    VDM.config.HARDENED.set(false);
                break;
            case "anger":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.anger"));
                    VDM.config.ANGER.set(false);
                break;
            case "unstable":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.unstable"));
                    VDM.config.UNSTABLE.set(false);
                break;
            case "kinetic":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.kinetic"));
                VDM.config.KINETIC.set(false);
                break;
            case "undying":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.undying"));
                VDM.config.UNDYING.set(false);
                break;
            case "flammable":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.flammable"));
                VDM.config.FLAMMABLE.set(false);
                break;
            case "fuelefficient":
            case "fuel_efficient":
            case "fuel efficient":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.fuelefficient"));
                VDM.config.FUELEFFICIENT.set(false);
                break;
            case "blacksmithing":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.blacksmithing"));
                VDM.config.BLACKSMITHING.set(false);
                break;
            case "warranty":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.warranty"));
                VDM.config.WARRANTY.set(false);
                break;
            case "heroic":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.allon"));
                VDM.config.BLACKEYE.set(false);
                VDM.config.CANNON.set(false);
                VDM.config.VENOM.set(false);
                VDM.config.SHIFT.set(false);
                VDM.config.KARMICJUSTICE.set(false);
                VDM.config.AGING.set(false);
                VDM.config.SOFTSKIN.set(false);
                VDM.config.FATIGUE.set(false);
                VDM.config.HARDENED.set(false);
                VDM.config.ANGER.set(false);
                VDM.config.UNSTABLE.set(false);
                VDM.config.FLAMMABLE.set(false);
                break;
            case "keystothecity":
                broadcast(context.getSource().getLevel().getServer(), new TranslationTextComponent("deactivation.villainousdifficultymultipliers.keysallon"));
                VDM.config.KINETIC.set(false);
                VDM.config.UNDYING.set(false);
                VDM.config.FUELEFFICIENT.set(false);
                VDM.config.BLACKSMITHING.set(false);
                VDM.config.WARRANTY.set(false);
                break;
            default:
                context.getSource().sendSuccess(new TranslationTextComponent("deactivation.villainousdifficultymultipliers.invalid").withStyle(TextFormatting.RED), false);
                context.getSource().sendSuccess(new TranslationTextComponent("error.villainousdifficultymultipliers.listmultipliers"), false);
                break;
        }

        return 0;
    }

    private void broadcast(MinecraftServer server, ITextComponent translationTextComponent){
        server.getPlayerList().broadcastMessage(translationTextComponent, ChatType.CHAT, UUID.randomUUID());
    }
}
