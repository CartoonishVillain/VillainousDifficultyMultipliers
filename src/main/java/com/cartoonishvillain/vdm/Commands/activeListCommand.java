package com.cartoonishvillain.vdm.Commands;

import com.cartoonishvillain.vdm.VDM;
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

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class activeListCommand implements Command<CommandSourceStack> {
    private static final activeListCommand CMD = new activeListCommand();

    public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher){
        return Commands.literal("activelist")
                .requires(cs -> cs.hasPermission(0))
                .executes(CMD);


    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        AtomicInteger count = new AtomicInteger(0);
        AtomicReference<String> string = new AtomicReference<>("");
            if(VDM.config.AGING.get()) {string.set(string.get() + ChatFormatting.RED + "Aging, "); count.set(count.get()+1);}
            if(VDM.config.ANGER.get()) {string.set(string.get() + ChatFormatting.RED + "Anger, "); count.set(count.get()+1);}
            if(VDM.config.BLACKEYE.get()) {string.set(string.get() + ChatFormatting.RED + "Black Eye, "); count.set(count.get()+1);}
            if(VDM.config.BLACKSMITHING.get()) {string.set(string.get() + ChatFormatting.BLUE + "Blacksmithing, "); count.set(count.get()+1);}
            if(VDM.config.CANNON.get()) {string.set(string.get() + ChatFormatting.RED + "Cannon, "); count.set(count.get()+1);}
            if(VDM.config.FATIGUE.get()) {string.set(string.get() + ChatFormatting.RED + "Fatigue, "); count.set(count.get()+1);}
            if(VDM.config.FLAMMABLE.get()) {string.set(string.get() + ChatFormatting.RED + "Flammable, "); count.set(count.get()+1);}
            if(VDM.config.FUELEFFICIENT.get()) {string.set(string.get() + ChatFormatting.BLUE + "Fuel Efficient, "); count.set(count.get()+1);}
            if(VDM.config.HARDENED.get()) {string.set(string.get() + ChatFormatting.RED + "Hardened, "); count.set(count.get()+1);}
            if(VDM.config.KARMICJUSTICE.get()) {string.set(string.get() + ChatFormatting.RED + "Karmic Justice, "); count.set(count.get()+1);}
            if(VDM.config.KINETIC.get()) {string.set(string.get() + ChatFormatting.BLUE + "Kinetic, "); count.set(count.get()+1);}
            if(VDM.config.SHIFT.get()) {string.set(string.get() + ChatFormatting.RED + "Shift, "); count.set(count.get()+1);}
            if(VDM.config.SOFTSKIN.get()) {string.set(string.get() + ChatFormatting.RED + "Soft Skin, "); count.set(count.get()+1);}
            if(VDM.config.UNDYING.get()) {string.set(string.get() + ChatFormatting.BLUE + "Undying, "); count.set(count.get()+1);}
            if(VDM.config.UNSTABLE.get()) {string.set(string.get() + ChatFormatting.RED + "Unstable, "); count.set(count.get()+1);}
            if(VDM.config.VEGETARIAN.get()) {string.set(string.get() + ChatFormatting.RED + "Vegetarian, "); count.set(count.get()+1);}
            if(VDM.config.VENOM.get()) {string.set(string.get() + ChatFormatting.RED + "Venom, "); count.set(count.get()+1);}
            if(VDM.config.WARRANTY.get()) {string.set(string.get() + ChatFormatting.BLUE + "Warranty, "); count.set(count.get()+1);}
            if(VDM.config.WRONG.get()) {string.set(string.get() + ChatFormatting.RED + "Wrong, "); count.set(count.get()+1);}


        String finalString = string.toString();
        if(finalString.length() > 0){
        finalString = finalString.substring(0, finalString.length()-2);
        context.getSource().sendSuccess(new TranslatableComponent("info.villainousdifficultymultipiers.count", count.get()), false);
        context.getSource().sendSuccess(new TextComponent(finalString), false);}
        else{context.getSource().sendSuccess(new TranslatableComponent("info.villainousdifficultymultipliers.none"), false);}


        return 0;
    }
}
