package xyz.ham5teak.doublejump.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import xyz.ham5teak.doublejump.utils.Configuration;
import xyz.ham5teak.doublejump.DoubleJump;
import xyz.ham5teak.doublejump.commands.SubCommand;

public class ToggleFlight extends SubCommand {

    public Configuration config;

    @Override
    public String getName() {
        return "toggleflight";
    }

    @Override
    public String getDescription() {
        return "Toggles CMI Flight on/off";
    }

    @Override
    public String getSyntax() {
        return "/doublejump toggleflight";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(sender instanceof ConsoleCommandSender || sender.hasPermission("doublejump.command.toggleflight")){
            try{
                String flight = "unknown";
                if(DoubleJump.getInstance().CMIFlightEnabled().equals(Boolean.TRUE)){
                    DoubleJump.ToggleFlight(Boolean.FALSE);
                    flight = "§4disabled";
                }else if(DoubleJump.getInstance().CMIFlightEnabled().equals(Boolean.FALSE)){
                    DoubleJump.ToggleFlight(Boolean.TRUE);
                    flight = "§2enabled";
                }
                DoubleJump.SaveConfig();
                DoubleJump.ReloadConfig();
                sender.sendMessage(ChatColor.GOLD + String.format("Flight successfully %s§6!", flight));
            }catch(Exception e){
                sender.sendMessage(ChatColor.RED + "There was an error executing this command.");
                e.printStackTrace();
            }
        }else{
            sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
        }
    }
}
