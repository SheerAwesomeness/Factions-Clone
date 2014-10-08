package com.Jok.Group;

import org.bukkit.entity.Player;

public class Group {
	
	private String name = "";
	
	public Group(String name) {
		this.name = name;
	}
	
	public void addPlayer(Player player) {
		if(!Main.players.containsValue(this)) {
			Main.players.put(player.getUniqueId(), this);

			player.sendMessage("§7[§cJokGroup§7] You are now in group '" + name + "'.");
		}
		else
			player.sendMessage("§7[§cJokGroup§7] §cYou already belong to this group.");
	}
	
	public String getName() {
		return name;
	}
	
}
