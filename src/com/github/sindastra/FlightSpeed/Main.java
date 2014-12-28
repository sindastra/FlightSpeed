package com.github.sindastra.FlightSpeed;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public void debugMsg(String m) {
		getLogger().info("[Debug] " + m);
	}
	@Override
	public void onEnable() {
		getLogger().info("Plugin has been loaded!");
	}
	@Override
	public void onDisable() {
		getLogger().info("Plugin has been disabled.");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("flyspeed")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				if (args.length == 1 && args[0].trim().matches("[0-9.]+")) {
					Player player = (Player) sender;
					float flyspeed = (float) Float.parseFloat(args[0]) / 10;
					if (flyspeed > 1.0f || flyspeed < 0.1f) {
						player.sendMessage("Please enter a value between 1 and 10");
					} else {
						if (player.getFlySpeed() != flyspeed) {
							player.setFlySpeed(flyspeed);
							player.sendMessage("Your flight speed has been set to " + Math.round((flyspeed * 10) * 10.0f) / 10.0f);
						}
						else
							player.sendMessage("This is already your flight speed!");
					}						
				} else {
					debugMsg("Wrong arg length or invalid arg!");
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
