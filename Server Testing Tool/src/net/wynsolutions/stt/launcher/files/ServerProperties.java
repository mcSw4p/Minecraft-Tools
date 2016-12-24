package net.wynsolutions.stt.launcher.files;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerProperties {

	public ServerProperties(String filePath, int port, boolean onlineMode) {
		File configFile = new File(filePath);
		PrintWriter write = null;
		try {
			write = new PrintWriter(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		write.println("generator-settings=");
		write.println("op-permission-level=4");
		write.println("allow-nether=true");
		write.println("level-name=world");
		write.println("enable-query=false");
		write.println("allow-flight=false");
		write.println("announce-player-achievements=true");
		write.println("prevent-proxy-connections=false");
		write.println("max-world-size=29999984");
		write.println("level-type=DEFAULT");
		write.println("enable-rcon=false");
		write.println("force-gamemode=false");
		write.println("level-seed=");
		write.println("server-port=" + port);
		write.println("server-ip=");
		write.println("network-compression-threshold=256");
		write.println("max-build-height=256");
		write.println("spawn-npcs=true");
		write.println("white-list=false");
		write.println("spawn-animals=true");
		write.println("snooper-enabled=true");
		write.println("hardcore=false");
		write.println("resource-pack-sha1=");
		
		write.println("online-mode=" + String.valueOf(onlineMode));
		
		write.println("resource-pack=");
		write.println("pvp=true");
		write.println("difficulty=1");
		write.println("enable-command-block=false");
		write.println("player-idle-timeout=0");
		write.println("gamemode=0");
		write.println("max-players=20");
		write.println("spawn-monsters=true");
		write.println("view-distance=10");
		write.println("generate-structures=true");
		write.println("motd=A Minecraft Server");
		write.println();

		write.close();

	}
	
}
