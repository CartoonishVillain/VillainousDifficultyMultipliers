package com.cartoonishvillain.vdm.Commands;

import com.cartoonishvillain.vdm.VDM;
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

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class activeListCommand implements Command<CommandSource> {
    private static final activeListCommand CMD = new activeListCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher){
        return Commands.literal("activelist")
                .requires(cs -> cs.hasPermission(0))
                .executes(CMD);


    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        AtomicInteger count = new AtomicInteger(0);
        AtomicReference<String> string = new AtomicReference<>("");
        if(VDM.config.AGING.get()) {string.set(string.get() + TextFormatting.RED + "Aging, "); count.set(count.get()+1);}
        if(VDM.config.ANGER.get()) {string.set(string.get() + TextFormatting.RED + "Anger, "); count.set(count.get()+1);}
        if(VDM.config.BLACKEYE.get()) {string.set(string.get() + TextFormatting.RED + "Black Eye, "); count.set(count.get()+1);}
        if(VDM.config.BLACKSMITHING.get()) {string.set(string.get() + TextFormatting.BLUE + "Blacksmithing, "); count.set(count.get()+1);}
        if(VDM.config.CANNON.get()) {string.set(string.get() + TextFormatting.RED + "Cannon, "); count.set(count.get()+1);}
        if(VDM.config.CELEBRATION.get()) {string.set(string.get() + TextFormatting.BLUE + "Celebration, "); count.set(count.get()+1);}
        if(VDM.config.FATIGUE.get()) {string.set(string.get() + TextFormatting.RED + "Fatigue, "); count.set(count.get()+1);}
        if(VDM.config.FLAMMABLE.get()) {string.set(string.get() + TextFormatting.RED + "Flammable, "); count.set(count.get()+1);}
        if(VDM.config.FUELEFFICIENT.get()) {string.set(string.get() + TextFormatting.BLUE + "Fuel Efficient, "); count.set(count.get()+1);}
        if(VDM.config.HARDENED.get()) {string.set(string.get() + TextFormatting.RED + "Hardened, "); count.set(count.get()+1);}
        if(VDM.config.KARMICJUSTICE.get()) {string.set(string.get() + TextFormatting.RED + "Karmic Justice, "); count.set(count.get()+1);}
        if(VDM.config.KINETIC.get()) {string.set(string.get() + TextFormatting.BLUE + "Kinetic, "); count.set(count.get()+1);}
        if(VDM.config.PANDEMIC.get()) {string.set(string.get() + checkForSupport("Pandemic, ", VDM.isCalyxLoaded, true)); count.set(count.get()+1);}
        if(VDM.config.RESTED.get()) {string.set(string.get() + TextFormatting.BLUE + "Rested, "); count.set(count.get()+1);}
        if(VDM.config.SHIFT.get()) {string.set(string.get() + TextFormatting.RED + "Shift, "); count.set(count.get()+1);}
        if(VDM.config.SOFTSKIN.get()) {string.set(string.get() + TextFormatting.RED + "Soft Skin, "); count.set(count.get()+1);}
        if(VDM.config.UNDYING.get()) {string.set(string.get() + TextFormatting.BLUE + "Undying, "); count.set(count.get()+1);}
        if(VDM.config.UNSTABLE.get()) {string.set(string.get() + TextFormatting.RED + "Unstable, "); count.set(count.get()+1);}
        if(VDM.config.VEGETARIAN.get()) {string.set(string.get() + TextFormatting.RED + "Vegetarian, "); count.set(count.get()+1);}
        if(VDM.config.VENOM.get()) {string.set(string.get() + TextFormatting.RED + "Venom, "); count.set(count.get()+1);}
        if(VDM.config.WARRANTY.get()) {string.set(string.get() + TextFormatting.BLUE + "Warranty, "); count.set(count.get()+1);}
        if(VDM.config.WILD.get()) {string.set(string.get() + TextFormatting.BLUE + "Wild, "); count.set(count.get()+1);}
        if(VDM.config.WRONG.get()) {string.set(string.get() + TextFormatting.RED + "Wrong, "); count.set(count.get()+1);}

        String finalString = string.toString();
        if(finalString.length() > 0){
        finalString = finalString.substring(0, finalString.length()-2);
        context.getSource().sendSuccess(new TranslationTextComponent("info.villainousdifficultymultipiers.count", count.get()), false);
        context.getSource().sendSuccess(new StringTextComponent(finalString), false);}
        else{context.getSource().sendSuccess(new TranslationTextComponent("info.villainousdifficultymultipliers.none"), false);}


        return 0;
    }

    private String checkForSupport(String name, boolean modNeeded, boolean IncreasingMultiplier){
        if(modNeeded){
            if(IncreasingMultiplier) return TextFormatting.RED + name;
            else return TextFormatting.BLUE + name;
        }
        else return TextFormatting.GRAY + name;
    }
}
