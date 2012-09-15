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
			// ��������
			if (arg1.equals("reload")) {
				plugin.reloadConfig();
				plugin.sendtoserver("�������ɿ���̨����.");
				return true;
			}
			plugin.sendtoserver("����!�����֧�ֿ���̨ʹ��!");
			return true;
		} else {
			if (args.length != 0) {
				String arg1 = args[0];
				Player p = (Player) sender;
				if (arg1.equals("reload") && Core.permission.has(p, "trd.reload")) {
					plugin.reloadConfig();
					plugin.sendtoserver("����������� " + p.getName() + " ����");
					plugin.sendtoplayer(p, "����������");
				}
				if (arg1.equals("exp") && Core.permission.has(p, "trd.exp")) {
					ifx.getItem(p, Integer.valueOf(args[1]));
				}
			}
		}
		return true;
	}
}
