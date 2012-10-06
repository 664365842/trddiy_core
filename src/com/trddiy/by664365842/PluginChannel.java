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
	String bosshealth = "bosshp:";
	String bcast = "bcast:";
	String cmes = "cd:";
	String mana = "mana:";
	String hp = "hp:";
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
				sendpluginmessage(p, cmes+s+":"+cd);
				if(plugin.debug = true){
					plugin.sendtoserver("cd: "+p.getDisplayName());
				}
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
			if(plugin.debug = true){
				plugin.sendtoserver("bcast: "+p.getDisplayName());
			}
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
		sendpluginmessage(p, bosshealth+health+":"+maxhealth);
		if(plugin.debug = true){
			plugin.sendtoserver("bosshealth: "+p.getDisplayName());
		}
	}
	}
	/**
	 * ��ͻ��˷���ħ��ֵ
	 * @param p �����
	 */
	public void sendmana(Hero h){
		if(h.getPlayer().getListeningPluginChannels().contains(channel)){
			sendpluginmessage(h.getPlayer(),mana+h.getMana()+":"+h.getMaxMana());
			if(plugin.debug = true){
				plugin.sendtoserver("mana: "+h.getPlayer().getDisplayName());
			}
		}else{
			return;
		}
	}
	public void sendmana(Hero h, int amount) {
		int sendmana = h.getMana()+amount;
		if(sendmana>h.getMaxMana())
		    sendmana=h.getMaxMana();
		if(h.getPlayer().getListeningPluginChannels().contains(channel)){
			sendpluginmessage(h.getPlayer(),mana+sendmana+":"+h.getMaxMana());
			if(plugin.debug = true){
				plugin.sendtoserver("mana: "+h.getPlayer().getDisplayName());
			}
		}else{
			return;
		}
	}
	public void sendhp(Hero h){
		if(h.getPlayer().getListeningPluginChannels().contains(channel)){
			sendpluginmessage(h.getPlayer(),hp+h.getHealth()+":"+h.getMaxHealth());
			if(plugin.debug = true){
				plugin.sendtoserver("hp: "+h.getPlayer().getDisplayName());
			}
		}else{
			return;
		}
	}
/*	public void sendhp(Hero hero,int amount) {
		int sendhp = hero.getHealth()+amount;
		if(sendhp>hero.getMaxHealth())
			sendhp = hero.getMaxHealth();
		if(sendhp<0)
			sendhp=0;
		if(hero.getPlayer().getListeningPluginChannels().contains(channel)){
			sendpluginmessage(hero.getPlayer(),hp+sendhp+":"+hero.getMaxHealth());
			if(plugin.debug = true){
				plugin.sendtoserver("hp: "+hero.getPlayer().getDisplayName());
			}
		}else{
			return;
		}
	}*/
	public void sendpluginmessage(Player p,String message){
		p.sendPluginMessage(plugin,channel , message.getBytes(java.nio.charset.Charset.forName("UTF-8")));
	}
}