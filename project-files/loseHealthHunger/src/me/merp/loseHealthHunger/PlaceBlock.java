package me.merp.loseHealthHunger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import java.util.Random;

public class PlaceBlock implements Listener {
	
	@EventHandler
	public static void onBlockPlace(BlockPlaceEvent event) {
		if (Main.cmdActivate == false) {
			return;
		}

		Player player = (Player) event.getPlayer();
		int curr_health = (int) player.getHealth();

		Random random = new Random();
		int to_lose = random.nextInt(curr_health);
		
		if (curr_health - to_lose <= 0) {
			return;
		}
		
		player.setHealth(curr_health - to_lose);
	}

}
