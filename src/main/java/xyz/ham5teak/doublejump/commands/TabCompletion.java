package xyz.ham5teak.doublejump.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompletion implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args){

        if(args.length == 1){
            List<String> subcommands = new ArrayList<>();
            for(int i = 0; i < new CommandManager().getSubcommands().size(); i++){
                subcommands.add(new CommandManager().getSubcommands().get(i).getName());
            }
            return subcommands;
        }

        return null;

    }

}
