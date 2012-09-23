package com.trddiy.by664365842;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener implements Listener {
	//定义变量
	private Core plugin;
	private ItemForXP ifx;
	private static String name = ChatColor.BLUE + "[经验兑换]";
	//构造
	public SignListener(Core plugin) {
		this.plugin = plugin;
		ifx = new ItemForXP(plugin);
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {// 检查牌子使用
		Player player = event.getPlayer();
		if (event.getClickedBlock().getTypeId() == 323) {
			Sign sign = (Sign) event.getClickedBlock();
			if (sign.getLine(0).equals(name)) {
				if (Core.permission.has(player, "trd.sign.use")) {
					int amount = Integer.valueOf(sign.getLine(1));
					ifx.getItem(player, amount);
				} else {
					plugin.sendtoplayer(player, ChatColor.RED + "你无使用此牌子的权限!");
				}
			}
		}
	}

	@EventHandler
	public void onplayerplaceblockevent(SignChangeEvent event) {//检查牌子创建
		Player p = event.getPlayer();
		String[] lines = event.getLines();
		if (Core.permission.has(p, "trd.sign.create")) {
			if (lines[0].equals("[经验兑换]")) {
				if (lines[1].matches("\\d+")) {
					event.setLine(0, name);
					plugin.sendtoplayer(p, "建立牌子成功!");
				} else {
					plugin.sendtoplayer(p, "格式错误!第二行应为整数!");
				}
			}
		} else {
			plugin.sendtoplayer(p, ChatColor.RED + "权限不足!");
			event.setCancelled(true);
		}
	}
}