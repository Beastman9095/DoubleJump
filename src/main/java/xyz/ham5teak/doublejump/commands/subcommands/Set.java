package xyz.ham5teak.doublejump.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import xyz.ham5teak.doublejump.utils.Configuration;
import xyz.ham5teak.doublejump.DoubleJump;
import xyz.ham5teak.doublejump.commands.SubCommand;

public class Set extends SubCommand {

    public Configuration config;

    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getDescription() {
        return "Sets values for the plugin.";
    }

    @Override
    public String getSyntax() {
        return "/doublejump set <xz/y/cooldown> <value>";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(sender instanceof ConsoleCommandSender || sender.hasPermission("doublejump.command.set")){
            try{
                if(args[2].isEmpty() || args[1].isEmpty()){
                    sender.sendMessage(getSyntax());
                    return;
                }
            }catch(ArrayIndexOutOfBoundsException e){
                sender.sendMessage("Please make sure you have an float as value. " + getSyntax());
                return;
            }
            if(args[1].equalsIgnoreCase("xz")){
                try{
                    DoubleJump.SetX(Float.parseFloat(args[2]));
                }catch(Exception e){
                    sender.sendMessage("Please make sure you have an float as value. " + getSyntax());
                    return;
                }
            }
            else if(args[1].equalsIgnoreCase("y")){
                try{
                    DoubleJump.SetY(Float.parseFloat(args[2]));
                }catch(Exception e){
                    sender.sendMessage("Please make sure you have an float as value. " + getSyntax());
                    return;
                }
            }
            else if(args[1].equalsIgnoreCase("cooldown")){
                try{
                    DoubleJump.SetCooldown(Integer.parseInt(args[2]));
                }catch(Exception e){
                    sender.sendMessage("Please make sure you have an integer as value. " + getSyntax());
                    return;
                }
            }else{
                sender.sendMessage("Please make sure you have entered the command correctly. " + getSyntax());
                return;
            }
            DoubleJump.SaveConfig();
            DoubleJump.ReloadConfig();
            sender.sendMessage(ChatColor.GOLD + args[1] + " changed to " + args[2]);
        }else{
            sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
        }
    }
}
