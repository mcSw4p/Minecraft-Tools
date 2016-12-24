package net.wynsolutions.stt.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;

import net.wynsolutions.stt.app.MainApp;
import net.wynsolutions.stt.launcher.files.BungeeConfig;
import net.wynsolutions.stt.launcher.files.EulaFile;
import net.wynsolutions.stt.launcher.files.ServerProperties;

public class Server {

	private String mainPath, serverPort, serverName, dirPath, batDir, minRam, maxRam, jarFileName, jarFilePathOrg, pluginsPath;
	private boolean restartScript = false, agreeEula= false, mainBungee = false;
	private File mainDir, batFile;
	private MainApp app;
	
	public Server(String path, String port, String name, String miRam, String mxRam, String jarFile, String pluginPath, MainApp par1) {
		this.setMainPath(path);
		this.setServerPort(port);
		this.setServerName(name);
		this.minRam = miRam;
		this.maxRam = mxRam;
		this.jarFilePathOrg = jarFile;
		this.pluginsPath = pluginPath;
		this.app = par1;
		
		this.createServer();
	}
	
	public Server(String path, String port, String name, String miRam, String mxRam, String jarFile, String pluginPath, MainApp par1, boolean restart, boolean eula) {
		this.setMainPath(path);
		this.setServerPort(port);
		this.setServerName(name);
		this.minRam = miRam;
		this.maxRam = mxRam;
		this.jarFilePathOrg = jarFile;
		this.pluginsPath = pluginPath;
		this.restartScript = restart;
		this.agreeEula = eula;
		this.app = par1;
		
		this.createServer();
	}
	
	public Server(String path, String port, String name, String miRam, String mxRam, String jarFile, String pluginPath, MainApp par1, boolean restart, boolean eula, boolean firstBungee) {
		this.setMainPath(path);
		this.setServerPort(port);
		this.setServerName(name);
		this.minRam = miRam;
		this.maxRam = mxRam;
		this.jarFilePathOrg = jarFile;
		this.pluginsPath = pluginPath;
		this.restartScript = restart;
		this.agreeEula = eula;
		this.app = par1;
		this.mainBungee = firstBungee;
		
		this.createServer();
	}
	
	private void createServer(){
		this.dirPath = mainPath + "\\" + serverName;
		
		if(this.restartScript)
			this.batDir = this.dirPath + "\\restart.bat";
		else
			this.batDir = this.dirPath + "\\run.bat";
		
		this.mainDir = new File(this.dirPath);
		this.batFile = new File(this.batDir);
		File jarFile = new File(this.jarFilePathOrg);
		this.jarFileName = jarFile.getName();
		
		if(!this.mainDir.exists()){
			this.mainDir.mkdirs();
			try {
				this.batFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			this.createBatFile();
			
			if(this.getServerName().contains("bungee")){
				new BungeeConfig(this.dirPath + "\\config.yml", this.app.getBukkitServerNo(), this.app.getSpigotServerNo(),Integer.parseInt(this.serverPort), this.mainBungee);
			}else{
				boolean flag = true;
				if(this.app.getBungeeServerNo() > 0){
					flag = false;
				}
				
				new ServerProperties(this.dirPath + "\\server.properties", Integer.parseInt(this.serverPort), flag);
			}
			
			if(this.agreeEula){
				new EulaFile(this.dirPath + "\\eula.txt", true);
			}
			
			if(this.pluginsPath != ""){
				try {
					this.copyFolder(new File(this.pluginsPath), new File(this.dirPath + "\\plugins"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			if(this.jarFilePathOrg != ""){
				try {
					Files.copy(jarFile.toPath(), new File(this.dirPath + "\\" + this.jarFileName.replace(".jar", "") + ".jar").toPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("ERROR: No path for jar file was specified.");
			}

			
			
		}
	}

	private void copyFolder(File src, File dest) throws IOException{

	    	if(src.isDirectory()){
	    		if(!dest.exists()){
	    		   dest.mkdir();
	    		}
	    		String files[] = src.list();
	    		for (String file : files) {
	    		   File srcFile = new File(src, file);
	    		   File destFile = new File(dest, file);
	    		   copyFolder(srcFile,destFile);
	    		}

	    	}else{
	    		InputStream in = new FileInputStream(src);
	    	        OutputStream out = new FileOutputStream(dest);

	    	        byte[] buffer = new byte[1024];

	    	        int length;
	    	        //copy the file content in bytes
	    	        while ((length = in.read(buffer)) > 0){
	    	    	   out.write(buffer, 0, length);
	    	        }

	    	        in.close();
	    	        out.close();
	    	}
	    }
	
	public void startServer(){
		new Thread(new Runnable(){
			@Override public void run() {
				Runtime runtime = Runtime.getRuntime();
				try {
					String batFile = "run.bat";
					if(restartScript)
						batFile = "restart.bat";
					
				    Process p1 = runtime.exec(new String[]{"cmd","/c", "start", batFile}, null, new File(dirPath));
					p1.waitFor();
	
				} catch(IOException ioException) {
				    System.out.println(ioException.getMessage());
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}

		}).start();
	}
	
	private void createBatFile(){
		PrintWriter write = null;
		try {
			write = new PrintWriter(this.batFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String mainCmd = "java -Xms" + minRam + " -Xmx" + maxRam + " -XX:+UseConcMarkSweepGC" + " -jar " + jarFileName.replace(".jar", "") + ".jar";

		if(this.restartScript){

			//Create a batch file that loops the main jar file
			write.println("@echo off");
			write.println(":restart");
			write.println(mainCmd);
			write.println("TIMEOUT 10");
			write.println("goto restart");
			write.println("exit");
			write.close();
		}else{

			//Create a batch file that just runs the jar file and closes
			write.println("@echo off");
			write.println(mainCmd);
			write.println("exit");
			write.close();
		}
	}
	
	public boolean isRestartScript() {
		return restartScript;
	}

	public void setRestartScript(boolean restartScript) {
		this.restartScript = restartScript;
	}

	public String getMainPath() {
		return mainPath;
	}

	public void setMainPath(String mainPath) {
		this.mainPath = mainPath;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
}
