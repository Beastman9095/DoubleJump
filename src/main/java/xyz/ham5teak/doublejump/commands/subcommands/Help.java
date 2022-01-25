package xyz.ham5teak.doublejump.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import xyz.ham5teak.doublejump.DoubleJump;
import xyz.ham5teak.doublejump.commands.CommandManager;
import xyz.ham5teak.doublejump.commands.SubCommand;
import xyz.ham5teak.doublejump.utils.Configuration;

public class Help extends SubCommand {

    public Configuration config;

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Shows usage of the plugin";
    }

    @Override
    public String getSyntax() {
        return "/doublejump help";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(sender instanceof ConsoleCommandSender || sender.hasPermission("doublejump.command.help")){
            for(int i = 0; i < new CommandManager().getSubcommands().size(); i++){
                String str = new CommandManager().getSubcommands().get(i).getName();
                String str2 = new CommandManager().getSubcommands().get(i).getSyntax();
                sender.sendMessage(String.format("§a%s §6usage: §b%s", str, str2));
            }
        }
    }
}