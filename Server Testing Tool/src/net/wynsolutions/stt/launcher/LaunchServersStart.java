package net.wynsolutions.stt.launcher;

import java.io.File;

import net.wynsolutions.stt.app.MainApp;

public class LaunchServersStart {

	private MainApp app;
	private int port;
	
	public LaunchServersStart(MainApp par1) {
		this.app = par1;
		this.port = Integer.parseInt(this.app.field4d.getText());
		
		if(par1.isDeleteFile()){
			this.purgeDirectory(new File(par1.field4.getText()));
		}
		
	}

	public void launch(){

		if(this.app.isBungeeEnabled()){
			for(int i = 0; i < this.app.getBungeeServerNo(); i++){
				//this.startServerConsole(this.app.field4a.getText(), this.app.isBungeeRestartScript());
				if(i == 0)
					new Server(this.app.field4.getText(), String.valueOf(this.port), "bungee" + i, "512M", "1G", this.app.field4a.getText(), this.app.field1.getText(), this.app,
						this.app.isBungeeRestartScript(), true, true).startServer();
				else
					new Server(this.app.field4.getText(), String.valueOf(this.port), "bungee" + i, "512M", "1G", this.app.field4a.getText(), this.app.field1.getText(), this.app,
							this.app.isBungeeRestartScript(), true).startServer();
				port++;
			}
		}
		if(this.app.isSpigotEnabled()){
			for(int i = 0; i < this.app.getSpigotServerNo(); i++){
				//this.startServerConsole(this.app.field4b.getText(), this.app.isSpigotRestartScript());
				
				new Server(this.app.field4.getText(), String.valueOf(this.port), "spigot" + i, "512M", "1G", this.app.field4b.getText(), this.app.field2.getText(),this.app,
						this.app.isBungeeRestartScript(), true).startServer();
				port++;
			}
		}
		if(this.app.isBukkitEnabled()){
			for(int i = 0; i < this.app.getBukkitServerNo(); i++){
				//this.startServerConsole(this.app.field4c.getText(), this.app.isBukkitRestartScript());
				
				new Server(this.app.field4.getText(), String.valueOf(this.port), "bukkit" + i, "512M", "1G", this.app.field4c.getText(), this.app.field3.getText(), this.app,
						this.app.isBungeeRestartScript(), true).startServer();
				port++;
			}
		}

	}
	
	private void purgeDirectory(File dir) {
	    for (File file: dir.listFiles()) {
	        if (file.isDirectory()) purgeDirectory(file);
	        file.delete();
	    }
	}
	
}
