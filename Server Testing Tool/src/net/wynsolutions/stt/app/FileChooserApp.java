package net.wynsolutions.stt.app;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class FileChooserApp {

	private JFileChooser chooser;
	
	public FileChooserApp(MainApp par1, JPanel par2, int par3) {
		chooser = new JFileChooser(); 
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Select Files");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setAcceptAllFileFilterUsed(false);  
		
		if (chooser.showOpenDialog(par2) == JFileChooser.APPROVE_OPTION) { 
			switch(par3){
			case 1:
				par1.field1.setText(chooser.getSelectedFile().toString());
				break;
			case 2:
				par1.field2.setText(chooser.getSelectedFile().toString());
				break;
			case 3:
				par1.field3.setText(chooser.getSelectedFile().toString());
				break;
			case 4:
				par1.field4.setText(chooser.getSelectedFile().toString());
				break;
			case 5:
				par1.field4a.setText(chooser.getSelectedFile().toString());
				break;
			case 6:
				par1.field4b.setText(chooser.getSelectedFile().toString());
				break;
			case 7:
				par1.field4c.setText(chooser.getSelectedFile().toString());
				break;
				default:
					break;
					
			}
		}
		
	}

}
