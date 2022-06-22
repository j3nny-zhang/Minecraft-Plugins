package me.merp.loseHealthHunger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static boolean cmdActivate = false;
	
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new BreakBlock(), this);
		Bukkit.getPluginManager().registerEvents(new PlaceBlock(), this);
	}
	
	public void onDisable() {
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (label.equalsIgnoreCase("loseHealthHunger")) {
			
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Only players can use this command!");
				return true;
			}

			Player player = (Player) sender;

			// true, false
			if (args.length == 0) {
				player.sendMessage(ChatColor.RED + "Missing argument: please type true or false after the command.");
				return true;
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("true")) {
					Bukkit.broadcastMessage(ChatColor.GREEN + "Command has been activated!");
					cmdActivate = true;
				} else if (args[0].equalsIgnoreCase("false")) {
					Bukkit.broadcastMessage(ChatColor.GREEN + "Command has been turned off!");
					cmdActivate = false;
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + "Not a valid command!");
					return true;
				}
			} else {
				player.sendMessage(ChatColor.RED + "Too many arguments: please type true or false after the command.");
				return true;
			}
			
		}
		
		return false;
	}

}
