package com.trddiy.by664365842;

import org.bukkit.entity.Player;

public class PluginChannel {
	public Core plugin;
	private final String channel ="trd_core";
	public PluginChannel(Core plugin){
		this.plugin = plugin;
		plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, channel);
	}
	public boolean send(Player p ,String message){
		if(p.getListeningPluginChannels().contains("trd_message")){
			p.sendPluginMessage(plugin,channel , message.getBytes(java.nio.charset.Charset.forName("UTF-8")));
			return true;
		}else{
			return false;
		}
	}
}
