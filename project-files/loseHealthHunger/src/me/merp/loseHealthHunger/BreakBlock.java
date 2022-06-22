package me.merp.loseHealthHunger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import java.util.Random;

public class BreakBlock implements Listener {

	@EventHandler
	public static void onBlockBreak(BlockBreakEvent event) {
		if (Main.cmdActivate == false) {
			return;
		}

		Player player = (Player) event.getPlayer();
		int curr_food = player.getFoodLevel();
		

		Random random = new Random();
		int to_lose = random.nextInt(curr_food);

		
		if (curr_food - to_lose <= 0) {
			return;
		}

		player.setFoodLevel(curr_food - to_lose);
	}

}
