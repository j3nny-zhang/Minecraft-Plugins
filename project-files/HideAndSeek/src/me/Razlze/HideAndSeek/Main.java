package me.Razlze.HideAndSeek;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Main extends JavaPlugin {
	public static int counter = 20; 
	public static boolean gameRunning = false;
	public static Player seeker = null;
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		ArrayList<Player> players = new ArrayList<>( Bukkit.getServer().getOnlinePlayers());
		
		if(label.equalsIgnoreCase("hideandseek")) {
			if(gameRunning) {
				sender.sendMessage("There is already a game of hide and seek taking place.");
				return false;
			}
			
			counter = 20;
			gameRunning = true;
			
			if(args.length != 0) {
				for(int i = 0; i < players.size(); i++) {
					if(players.get(i).getName().equalsIgnoreCase(args[0])) {
						seeker = players.get(i);
					} else {
						sender.sendMessage(args[0] + " is not a valid player.");
						return false;
					}
				}
			} else {
				Random rand = new Random();
				int randomIndex = rand.nextInt(players.size());
				
				seeker = players.get(randomIndex);
			}
			
			//sending starting messages
			
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i) == seeker) {
					players.get(i).sendTitle("You are the seeker!", "Wait for the hiders to cower away!");
				} else {
					players.get(i).sendTitle("You are a hider!", "Go hide before the timer counts down!");
				}
			}
			
			
			BukkitTask task = new BukkitRunnable() {
				
				public void run() {
					
					seeker.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 9999));
					
					for(Player p : players) {
		            	p.sendTitle(String.valueOf(counter), "", 5, 20, 5);
		            }
		            
		            counter--;
		            
		            if(counter == 0) {
		            	cancel();
		            }
				}
			}.runTaskTimer(this,70,20);

	        
			
			
			return true;
		}
		
		return false;
	}
}


