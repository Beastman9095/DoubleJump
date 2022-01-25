package xyz.ham5teak.doublejump.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import xyz.ham5teak.doublejump.DoubleJump;
import xyz.ham5teak.doublejump.commands.SubCommand;
import xyz.ham5teak.doublejump.utils.Configuration;

import java.util.function.DoubleBinaryOperator;

public class Current extends SubCommand {

    public Configuration config;

    @Override
    public String getName() {
        return "current";
    }

    @Override
    public String getDescription() {
        return "Shows current value of the plugin";
    }

    @Override
    public String getSyntax() {
        return "/doublejump current";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(sender instanceof ConsoleCommandSender || sender.hasPermission("doublejump.command.current")){
            sender.sendMessage(String.format("§2Flight: §6%s\n§2Double Jump: §6%s\n§2X-Z: §3%s\n" +
                    "§2Y: §3%s\n§2Cooldown: §3%s", DoubleJump.getInstance().CMIFlightEnabled(),
                    DoubleJump.getInstance().DoubleJumpEnabled(), DoubleJump.getInstance().XZInterval(),
                    DoubleJump.getInstance().YInterval(), DoubleJump.getInstance().Cooldown()));
        }
    }
}