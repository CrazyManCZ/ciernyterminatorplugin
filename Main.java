package me.CrazyManCZ.odmena;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor{
	
	public void onEnable(){
		getLogger().info("Plugin byl zapnut");
	}
	
	public void combine(String[] args, Player player, String targetNick, int castka) {
		  String product = StringUtils.join(args, ' ', 2, args.length);
		  player.sendMessage("Dal jsi hraci " + targetNick + " " + castka + "$ z duvodu " + product);
		  for (Player p : Bukkit.getOnlinePlayers()){
			  p.sendMessage(ChatColor.AQUA + ChatColor.BOLD.toString() + player.getName() + ChatColor.YELLOW + " dal hraci " + ChatColor.AQUA + ChatColor.BOLD.toString() + targetNick + ChatColor.GOLD + " " + castka + "$" + ChatColor.YELLOW + " z duvodu");
		  }
		 }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (label.equalsIgnoreCase("odmena")){
			if (sender instanceof Player){
				Player p = (Player) sender;
				if (p.hasPermission("odmena.dat")){
					if (args.length < 3){
						@SuppressWarnings("deprecation")
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if (target != null){
							try {
								int castka = Integer.parseInt(args[1]);
								combine(args, p, target.getName(), castka);
							} catch (NumberFormatException ex){
							}
						}
					}
				}
			}
		}
		return false;
	}

}
