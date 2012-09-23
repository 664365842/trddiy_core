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
			Player p = (Player) sender;
			if (args.length != 0) {
				String arg1 = args[0];
				if (arg1.equals("reload") && Core.permission.has(p, "trd.reload")) {
					plugin.reloadConfig();
					plugin.sendtoserver("设置已由玩家 " + p.getName() + " 重载");
					plugin.sendtoplayer(p, "设置已重载");
				}
				if (args.length >=2&&arg1.equals("exp") && Core.permission.has(p, "trd.exp")) {
					//plugin.sendtoplayer(p,"抱歉,功能未开放");
					if(args[1] != null){
					ifx.getItem(p, Integer.valueOf(args[1]));
					}else{
						plugin.sendtoplayer(p, "请输入要兑换的物品数!");
					}
				}
				if(arg1.equals("help")){
					plugin.sendtoplayer(p, "===== Trddiy核心插件帮助 =====");
					plugin.sendtoplayer(p, "/trd exp 进行经验兑换");
					plugin.sendtoplayer(p, "/trd help 打开帮助界面");
					plugin.sendtoplayer(p, "/trd xiaodai 获得小呆的节操");
				}
				if(arg1.equals("xiaodai")){
					plugin.sendtoplayer(p, "抱歉,功能未开放.");
				}
			}else{
				plugin.sendtoplayer(p, "===== Trddiy核心插件帮助 =====");
				plugin.sendtoplayer(p, "/trd exp 进行经验兑换");
				plugin.sendtoplayer(p, "/trd help 打开帮助界面");
				plugin.sendtoplayer(p, "/trd xiaodai 获得小呆的节操");
			}
		}
		return true;
	}
}
