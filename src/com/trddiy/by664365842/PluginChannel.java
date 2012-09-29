package com.trddiy.by664365842;

import java.util.Map;
import java.util.Set;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.herocraftonline.heroes.characters.CharacterTemplate;
import com.herocraftonline.heroes.characters.Hero;

public class PluginChannel {
	public Core plugin;
	private final String channel ="trd_core";
	String bosshealth = "bosshp|";
	String bcast = "bcast|";
	String cmes = "cd|";
	public PluginChannel(Core plugin){
		this.plugin = plugin;
		//注册插件频道
		plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, channel);
	}
	/**
	 * 向客户端发送cd信息
	 * @param p 是玩家
	 */
	public void sendcd(Player p){
		if(p.getListeningPluginChannels().contains(channel)){
			Hero h = plugin.getheroesplugin().getCharacterManager().getHero(p);
			Map<String, Long> cds = h.getCooldowns();
			Set<String> names = cds.keySet();
			for(String s : names){
				long cd = cds.get(s);
				sendpluginmessage(p, cmes+s+"|"+cd);
			}
		}else{
			return;
		}
	}
	/**
	 * 向客户端发送特定消息
	 * @param p 是玩家
	 * @param s 是要发送的字符串
	 */
	public void sendbroadcast(Player p,String s){
		if(p.getListeningPluginChannels().contains(channel)){
			sendbroadcast(p, bcast+s);
		}else{
			return;
		}
	}
	/**
	 * 向客户端发送boss血量信息
	 * @param p 是玩家
	 * @param e 是boss
	 */
	public void sendbosshealth(Player p,LivingEntity e){
		if(p.getListeningPluginChannels().contains(channel)){
		CharacterTemplate he = plugin.getheroesplugin().getCharacterManager().getCharacter(e);
		int maxhealth = he.getMaxHealth();
		int health = he.getHealth();
		sendpluginmessage(p, bosshealth+health+"|"+maxhealth);
	}
	}
	public void sendpluginmessage(Player p,String message){
		p.sendPluginMessage(plugin,channel , message.getBytes(java.nio.charset.Charset.forName("UTF-8")));
	}
}
