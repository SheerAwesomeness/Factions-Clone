package com.Jok.Group;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	List<String> groups = new ArrayList<String>();
	
	@Override
    public void onEnable() {
        getLogger().info("Init Worked.");
    }
	
	@Override
    public void onDisable() {
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("j")) {
			if(args[0].equalsIgnoreCase("create")) {
				String groupName = args[1];
				
				if(!groups.contains(groupName))
					groups.add(groupName);
				else {
					print((Player)sender, "§cGroup '" + groupName + "' already exists.");
					return true;
				}
				
				print((Player)sender, "Created group '" + args[1] + "'.");
			} else if(args[0].equalsIgnoreCase("list")) {
				print((Player)sender, "All Groups:");
				
				for (int i = 0; i < groups.size(); i++) {
					print((Player)sender, " - §9" + groups.get(i));
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	public void print(Player player, String message) {
		player.sendMessage("§7[§cJokGroup§7] " + message);
	}
}
