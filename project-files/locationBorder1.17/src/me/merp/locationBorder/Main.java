package me.merp.locationBorder;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	
	double size = 616.0;
	World world = Bukkit.getServer().getWorlds().get(0);
	WorldBorder border = world.getWorldBorder();
	
	//timer
	int secondsPassed = 0;
	
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
	
	@Override
	public void run() {
		secondsPassed++;
		border.setSize(32.0, 300);
		Bukkit.broadcastMessage(ChatColor.RED + "The border is closing!");
	}
	};
	
	
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("locationBorder")) {
			
			//set work border size
			border.setCenter(0.0, 0);
			border.setSize(size);
			border.setDamageBuffer(0.0);
			border.setDamageAmount(0.5);
			
			//randomly teleport players
			for(Player p: Bukkit.getOnlinePlayers()) {
				int x = (int) (Math.random() * 600-300);
				int z = (int) (Math.random() * 600-300);
				int y = p.getWorld().getHighestBlockYAt(x, z);
				p.teleport(new Location(p.getWorld(), x, y, z));
				
				
				timer.schedule(task, 900000);
			}
			
		}
		
		return false;
	}

}
