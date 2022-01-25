package xyz.ham5teak.doublejump.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import xyz.ham5teak.doublejump.commands.subcommands.*;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private final ArrayList<SubCommand> subcommands = new ArrayList<>();

    public CommandManager(){
        subcommands.add(new Reload());
        subcommands.add(new ToggleJump());
        subcommands.add(new ToggleFlight());
        subcommands.add(new Set());
        subcommands.add(new Help());
        subcommands.add(new Current());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if(args.length > 0){
            for(int i = 0;i < getSubcommands().size();i++){
                if(args[0].equalsIgnoreCase(getSubcommands().get(i).getName())){
                    getSubcommands().get(i).perform(sender, args);
                }
            }
        }else {
            sender.sendMessage("Please try using: /doublejump <set/reload/toggleflight/togglejump/help/current>");
        }
        return false;

    }

    public ArrayList<SubCommand> getSubcommands(){
        return subcommands;
    }

}
