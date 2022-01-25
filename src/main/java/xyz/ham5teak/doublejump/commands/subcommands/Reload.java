package xyz.ham5teak.doublejump.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import xyz.ham5teak.doublejump.utils.Configuration;
import xyz.ham5teak.doublejump.DoubleJump;
import xyz.ham5teak.doublejump.commands.SubCommand;

public class Reload extends SubCommand {

    public Configuration config;

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads the plugin";
    }

    @Override
    public String getSyntax() {
        return "/doublejump reload";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(sender instanceof ConsoleCommandSender || sender.hasPermission("doublejump.command.reload")){
            try{
                DoubleJump.ReloadConfig();
                sender.sendMessage(ChatColor.GOLD + "Config successfully reloaded!");
            }catch(Exception e){
                sender.sendMessage(ChatColor.RED + "There was an error executing this command.");
                e.printStackTrace();
            }
        }else{
            sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
        }
    }
}
