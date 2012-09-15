package com.trddiy.by664365842;

import java.util.Map;
import java.util.Set;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.garbagemule.MobArena.ArenaClass;
import com.garbagemule.MobArena.events.ArenaPlayerJoinEvent;
import com.garbagemule.MobArena.events.NewWaveEvent;
import com.garbagemule.MobArena.framework.Arena;
import com.garbagemule.MobArena.waves.MABoss;
import com.garbagemule.MobArena.waves.enums.WaveType;
import com.garbagemule.MobArena.waves.types.BossWave;
import com.herocraftonline.heroes.characters.CharacterManager;
import com.herocraftonline.heroes.characters.Hero;

public class MobArenaListener implements Listener {
	private Core plugin;
	private CharacterManager chm; 

	public MobArenaListener(Core plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onWave(NewWaveEvent event) {
		// TODO ��boss��֮���ٸ�����
		int wn = event.getWaveNumber();
		Arena arena = event.getArena();
		Set<Player> ap = arena.getPlayersInArena();
		if (event.getWave().getType() == WaveType.BOSS) {
			BossWave bw = (BossWave)(event.getWave());
			for(MABoss mb : bw.getMABosses()){
				sethealth(mb.getEntity(),mb.getMaxHealth());
			}
			for (Player p : ap) {
				if (p != null) {
					addexp(p, wn);
				}
			}
		}
	}
	@EventHandler
	public void onJoin(ArenaPlayerJoinEvent event){
		Arena a =event.getArena();
		Player p = event.getPlayer();
		setclass(p,a);
	}

	public void addexp(Player p, int wn) {
		chm = plugin.getheroesplugin().getCharacterManager();
		int waven = plugin.getConfig().getInt("MobArena.multiply");
		Hero h = chm.getHero(p);
		h.addExp(wn * waven, h.getHeroClass());
		h.syncExperience();
		plugin.sendtoplayer(p, "�����˽���: " + wn * waven + " ����");
	}
	/**
	 * ������ҵ�pveְҵ,���޷���������ְҵ������Ϊ������
	 * @param p �����
	 * @param a ��pve����
	 */
	public void setclass(Player p,Arena a){
		chm = plugin.getheroesplugin().getCharacterManager();
		Hero h = chm.getHero(p);
		String s = h.getHeroClass().getName();
		Map<String, ArenaClass> map = a.getClasses();
		if(map.containsValue(s)){
			a.assignClass(p, s);
		}else{
			a.assignClass(p, "������");
		}
		
	}
	public void sethealth(LivingEntity e,int health){
		chm = plugin.getheroesplugin().getCharacterManager();
		chm.getCharacter(e).setHealth(health);
	}

}
