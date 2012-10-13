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
			if (arg1.equals("debug")) {
				Boolean debug = Core.debug;
				if (debug == false) {
					Core.debug = true;
				} else {
					Core.debug = false;
				}
				plugin.sendtoserver("����״̬���Ϊ: " + Core.debug);
				return true;
			}
			plugin.sendtoserver("����!�����֧�ֿ���̨ʹ��!");
			return true;
		} else {
			Player p = (Player) sender;
			if (args.length != 0) {
				String arg1 = args[0];
				if (arg1.equals("reload")
						&& Core.permission.has(p, "trd.reload")) {
					plugin.reloadConfig();
					plugin.sendtoserver("����������� " + p.getName() + " ����");
					plugin.sendtoplayer(p, "����������");
				}
				if (arg1.equals("debug") && Core.permission.has(p, "trd.debug")) {
					Boolean debug = Core.debug;
					if (debug == false) {
						Core.debug = true;
					} else {
						Core.debug = false;
					}
					plugin.sendtoserver("����״̬�� " + p.getName() + " ���Ϊ: "
							+ Core.debug);
					plugin.sendtoplayer(p, "����״̬�Ѹ�Ϊ: " + Core.debug);
				}
				if (arg1.equals("exp") && Core.permission.has(p, "trd.exp")) {
					// plugin.sendtoplayer(p,"��Ǹ,����δ����");
					if (args.length >= 2 && args[1] != null) {
						ifx.getItem(p, Integer.valueOf(args[1]));
					} else {
						plugin.sendtoplayer(p, "���޴������Ȩ��/��û������Ҫ�һ������.");
					}
				}
				if (arg1.equals("help")) {
					plugin.sendtoplayer(p, "===== Trddiy���Ĳ������ =====");
					plugin.sendtoplayer(p, "/trd exp ���о���һ�");
					plugin.sendtoplayer(p, "/trd help �򿪰������");
					plugin.sendtoplayer(p, "/trd xiaodai ���С���Ľڲ�");
				}
				if (arg1.equals("xiaodai")) {
					plugin.sendtoplayer(p, "��Ǹ,����δ����.");
				}
				if (arg1.equals("bcast")) {
					if (args.length >= 2 && args[1] != null
							&& Core.permission.has(p, "trd.bcast")) {
						String s = args[1];
						Player[] ps= plugin.getServer().getOnlinePlayers();
						for (Player p2 : ps) {
							plugin.getpcl().sendbroadcast(p2, s);
						}
					}
					
					else if(args.length==1){
						String s = "";
						Player[] ps= plugin.getServer().getOnlinePlayers();
						for (Player p2 : ps) {
							plugin.getpcl().sendbroadcast(p2, s);
						}
					}
					else {
						plugin.sendtoplayer(p, "���޴������Ȩ��/������Ϣ����Ϊ��");
					}
				}
			} else {
				plugin.sendtoplayer(p, "===== Trddiy���Ĳ������ =====");
				plugin.sendtoplayer(p, "/trd exp ���о���һ�");
				plugin.sendtoplayer(p, "/trd help �򿪰������");
				plugin.sendtoplayer(p, "/trd xiaodai ���С���Ľڲ�");
			}
			return true;
		}
	}
}
