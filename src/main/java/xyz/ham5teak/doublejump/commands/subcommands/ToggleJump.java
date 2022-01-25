package xyz.ham5teak.doublejump.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import xyz.ham5teak.doublejump.utils.Configuration;
import xyz.ham5teak.doublejump.DoubleJump;
import xyz.ham5teak.doublejump.commands.SubCommand;

public class ToggleJump extends SubCommand {

    public Configuration config;

    @Override
    public String getName() {
        return "togglejump";
    }

    @Override
    public String getDescription() {
        return "Toggles double jump on/off";
    }

    @Override
    public String getSyntax() {
        return "/doublejump togglejump";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(sender instanceof ConsoleCommandSender || sender.hasPermission("doublejump.command.togglejump")){
            try{
                String doublejump = "unknown";
                if(DoubleJump.getInstance().DoubleJumpEnabled().equals(Boolean.TRUE)){
                    DoubleJump.ToggleJump(Boolean.FALSE);
                    doublejump = "§4disabled";
                }else if(DoubleJump.getInstance().DoubleJumpEnabled().equals(Boolean.FALSE)){
                    DoubleJump.ToggleJump(Boolean.TRUE);
                    doublejump = "§2enabled";
                }
                DoubleJump.SaveConfig();
                DoubleJump.ReloadConfig();
                sender.sendMessage(ChatColor.GOLD + String.format("Double Jump successfully %s§6!", doublejump));
            }catch(Exception e){
                sender.sendMessage(ChatColor.RED + "There was an error executing this command.");
                e.printStackTrace();
            }
        }else{
            sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
        }
    }
}
