package com.trddiy.by664365842;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListener implements CommandExecutor {
	private Core plugin;
	public ItemForXP ifx;
	public CommandListener(Core plugin) {
		this.plugin = plugin;
		this.ifx = new ItemForXP(plugin);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			String arg1 = args[0];
			// 重载命令
			if (arg1.equals("reload")) {
				plugin.reloadConfig();
				plugin.sendtoserver("设置已由控制台重载.");
				return true;
			}
			plugin.sendtoserver("错误!本命令不支持控制台使用!");
			return true;
		} else {
			if (args.length != 0) {
				String arg1 = args[0];
				Player p = (Player) sender;
				if (arg1.equals("reload") && Core.permission.has(p, "trd.reload")) {
					plugin.reloadConfig();
					plugin.sendtoserver("设置已由玩家 " + p.getName() + " 重载");
					plugin.sendtoplayer(p, "设置已重载");
				}
				if (arg1.equals("exp") && Core.permission.has(p, "trd.exp")) {
					ifx.getItem(p, Integer.valueOf(args[1]));
				}
			}
		}
		return true;
	}
}
