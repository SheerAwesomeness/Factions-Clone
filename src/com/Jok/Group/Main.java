package com.Jok.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static List<Group> groups = new ArrayList<Group>();
	public static HashMap<UUID, Group> players = new HashMap<UUID, Group>();
	
	@Override
    public void onEnable() {
        getLogger().info("Init Worked.");
    }
	
	@Override
    public void onDisable() {
		
	}
	
	/**
	 * Returns the group with the specified name, or null.
	 */
	public Group getGroup(String groupName) {
		for (int i = 0; i < groups.size(); i++) {
			if(groups.get(i).getName().equals(groupName))
				return groups.get(i);
		}
		
		return null;
	}
	
	/**
	 * Returns the group that belongs to that player, or null.
	 */ 
	public Group getGroup(Player player) {
		return players.get(player.getUniqueId());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player)sender;
		
		if (cmd.getName().equalsIgnoreCase("j")) {
			if(args[0].equalsIgnoreCase("create")) {
				Group group;
				String groupName = args[1];
				
				if(getGroup(groupName) != null) {
					print(player, "§cGroup '" + groupName + "' already exists.");
					return true;
				} else {
					group = new Group(groupName);
					groups.add(group);
				}
				
				print(player, "Created group '" + groupName + "'.");
				group.addPlayer(player);
				
				return true;
			} else if(args[0].equalsIgnoreCase("join")) {
				Group group = getGroup(args[1]);
				
				if(group == null) {
					print(player, "§cGroup '" + args[1] + "' doesn't exist.");
					return true;
				} else {
					group.addPlayer(player);
				}
			} else if(args[0].equalsIgnoreCase("list")) {
				print(player, "All Groups:");
				
				for (int i = 0; i < groups.size(); i++) {
					print(player, " - §9" + groups.get(i).getName());
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
