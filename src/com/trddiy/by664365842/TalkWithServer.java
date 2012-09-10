package com.trddiy.by664365842;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class TalkWithServer implements Listener {
	public TalkWithServer(Core plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	//∂®“Â—’…´
	ChatColor white = ChatColor.WHITE;
	ChatColor red = ChatColor.RED;
	ChatColor green = ChatColor.GREEN;
	ChatColor gold = ChatColor.GOLD;
	public void onChat(PlayerChatEvent event){
		String s = event.getMessage();
		if(s.contains("@")){
			String[] s2 = s.split("@");
			for(int i = 1;i<s2.length-1;i++){
			}
		}
		}
	}
