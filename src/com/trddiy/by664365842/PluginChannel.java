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
		//ע����Ƶ��
		plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, channel);
	}
	/**
	 * ��ͻ��˷���cd��Ϣ
	 * @param p �����
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
	 * ��ͻ��˷����ض���Ϣ
	 * @param p �����
	 * @param s ��Ҫ���͵��ַ���
	 */
	public void sendbroadcast(Player p,String s){
		if(p.getListeningPluginChannels().contains(channel)){
			sendbroadcast(p, bcast+s);
		}else{
			return;
		}
	}
	/**
	 * ��ͻ��˷���bossѪ����Ϣ
	 * @param p �����
	 * @param e ��boss
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
