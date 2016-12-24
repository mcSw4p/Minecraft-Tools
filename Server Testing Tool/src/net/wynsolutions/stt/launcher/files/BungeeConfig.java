package net.wynsolutions.stt.launcher.files;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class BungeeConfig {

	public BungeeConfig(String filePath, int bukkitNo, int spigotNo, int serverPort, boolean bungeeFirst) {
		File configFile = new File(filePath);
		PrintWriter write = null;
		try {
			write = new PrintWriter(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		write.println("ip_forward: false");
		write.println("network_compression_threshold: 256");
		write.println("stats: ");
		write.println("groups:");
		write.println("  md_5:");
		write.println("  - admin");
		write.println("servers:");

		if(bungeeFirst){
			int bukkitServers = bukkitNo,
					spigotServers = spigotNo,
					port = serverPort + 1;

			for(int i = 0; i < bukkitServers; i++){
				write.println("  bukkit" + i + ":");
				write.println("    motd: '&1Just another BungeeCord - Forced Host'");
				write.println("    address: localhost:" + port);
				write.println("    restricted: false");
				port++;
			}

			for(int i = 0; i < spigotServers; i++){
				write.println("  spigot" + i + ":");
				write.println("    motd: '&1Just another BungeeCord - Forced Host'");
				write.println("    address: localhost:" + port);
				write.println("    restricted: false");
				port++;
			}

		}

		write.println("timeout: 30000");
		write.println("player_limit: -1");
		write.println("listeners:");
		write.println("- query_port: 25577");
		write.println("  motd: '&1Another Bungee server'");
		write.println("  priorities:");
		if(bukkitNo > 0){
			write.println("  - bukkit0");
		}else if(spigotNo > 0){
			write.println("  - spigot0");
		}else{
			write.println("  - lobby");
			System.out.println("Could not set a default server in bungeecord config. Setting to \'lobby\'");
		}

		write.println("  bind_local_address: true");
		write.println("  tab_list: GLOBAL_PING");
		write.println("  query_enabled: false");

		write.println("  host: 0.0.0.0:" + serverPort);

		write.println("  forced_hosts:");
		write.println("    pvp.md-5.net: pvp");
		write.println("  max_players: 1");
		write.println("  tab_size: 60");
		write.println("  ping_passthrough: false");
		write.println("  force_default_server: false");
		write.println("permissions:");
		write.println("  default:");
		write.println("  - bungeecord.command.server");
		write.println("  - bungeecord.command.list");
		write.println("  admin:");
		write.println("  - bungeecord.command.alert");
		write.println("  - bungeecord.command.end");
		write.println("  - bungeecord.command.ip");
		write.println("  - bungeecord.command.reload");
		write.println("online_mode: true");
		write.println("log_commands: false");
		write.println("disabled_commands:");
		write.println("- disabledcommandhere");
		write.println("connection_throttle: 4000");
		write.println();

		write.close();

	}

}
