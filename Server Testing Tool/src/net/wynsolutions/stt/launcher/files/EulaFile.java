package net.wynsolutions.stt.launcher.files;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class EulaFile {

	public EulaFile(String filePath, boolean flag) {
		File configFile = new File(filePath);
		PrintWriter write = null;
		try {
			write = new PrintWriter(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		write.println("eula=" + String.valueOf(flag));
		write.println();
		
		write.close();
	}
	
}
