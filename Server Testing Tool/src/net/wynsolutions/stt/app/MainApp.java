package net.wynsolutions.stt.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import net.wynsolutions.stt.launcher.LaunchServersStart;

public class MainApp implements ItemListener, ActionListener{
	
	private JFrame frame;
	private JPanel panel1, panel1a, panel1b, panel2, panel2a, panel2b, panel3, panel3a, panel3b, panel4, panel4a, panel4b, panel4c, panel4d, panel4e, panel4f;
	
	private JCheckBox bungeeProxyBox, spigotServerBox, bukkitServerBox, bungeeRestart, spigotRestart, bukkitRestart, deleteFiles, bungeeEula, spigotEula, bukkitEula;
	private JSpinner serverNo1, serverNo2, serverNo3;
	private SpinnerModel serverModel1, serverModel2, serverModel3;
	private JLabel label1, label2, label3, l1, l2, l3, l4, l5;
	public JTextField field1, field2, field3, field4, field4a, field4b, field4c, field4d;
	private JButton lanuchButton, choose1, choose2, choose3, choose4, choose4a, choose4b, choose4c;
	
	public PrintStream outStream;
	
	private boolean bungeeEnabled = false, spigotEnabled = false, bukkitEnabled = false
			, bungeeRestartScript = false, spigotRestartScript = false, bukkitRestartScript = false
			,deleteFile = true;
	
	public MainApp() {
		
		this.frame = new JFrame("STT - Server Testing Tool");
	}
	
	public void createandShowGUI(){
		
		this.outStream = System.out;
		
		this.frame.getContentPane().setLayout(new GridLayout(4, 1));
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocation(new java.awt.Point(100, 100));
		
		//Panel 1 
		this.panel1 = new JPanel();
		this.panel1.setLayout(new GridLayout(1, 5));
		this.panel1.setSize(new Dimension(600, 100));
		TitledBorder border = BorderFactory.createTitledBorder("Bungee Proxy Settings");
		this.panel1.setBorder(border);
		this.panel1.setVisible(true);
		
		this.bungeeProxyBox = new JCheckBox("Enabled?");
		this.bungeeProxyBox.addItemListener(this);
		this.bungeeProxyBox.setSelected(false);
		this.panel1.add(this.bungeeProxyBox);
		
		this.panel1a = new JPanel(new GridLayout(5,1));
		this.panel1a.setSize(new Dimension(25, 30));
		this.panel1a.setEnabled(false);
		
		this.label1 = new JLabel("Server No.");
		this.label1.setEnabled(false);
		this.panel1a.add(this.label1);
		
		this.serverModel1 = new SpinnerNumberModel(0, 0, 150, 1);
		this.serverNo1 = new JSpinner(this.serverModel1);
		this.serverNo1.setToolTipText("Number of server instances.");
		this.serverNo1.setEnabled(false);
		this.panel1a.add(this.serverNo1);
		
		this.bungeeRestart = new JCheckBox("Restart Script");
		this.bungeeRestart.setEnabled(false);
		this.panel1a.add(this.bungeeRestart);
		
		this.bungeeEula = new JCheckBox("Agree to EULA");
		this.bungeeEula.setEnabled(false);
		this.panel1a.add(this.bungeeEula);
		
		this.panel1b = new JPanel(new GridLayout(5, 2));
		this.panel1b.setSize(new Dimension(25, 30));
		this.panel1b.setEnabled(false);
		TitledBorder plugin1 = BorderFactory.createTitledBorder("Plugins Folder");
		this.panel1b.setBorder(plugin1);
		
		this.field1 = new JTextField();
		this.choose1 = new JButton("Select Folder");
		
		this.field1.setSize(new Dimension(500, 100));
		this.field1.setEnabled(false);
		this.choose1.setEnabled(false);
		this.choose1.addActionListener(this);
		
		this.panel1b.add(field1);
		this.panel1b.add(choose1);
		
		this.panel1.add(this.panel1a);
		this.panel1.add(this.panel1b);
		
		//Panel 2
		this.panel2 = new JPanel();
		this.panel2.setLayout(new GridLayout(1, 4));
		this.panel2.setSize(new Dimension(600, 100));
		TitledBorder border2 = BorderFactory.createTitledBorder("Spigot Server Settings");
		this.panel2.setBorder(border2);
		this.panel2.setVisible(true);	
		
		this.spigotServerBox = new JCheckBox("Enabled?");
		this.spigotServerBox.addItemListener(this);
		this.spigotServerBox.setSelected(false);
		this.panel2.add(this.spigotServerBox);
		
		this.panel2a = new JPanel(new GridLayout(5, 1));
		this.panel2a.setSize(new Dimension(25, 30));
		
		this.label2 = new JLabel("Server No.");
		this.label2.setEnabled(false);
		this.panel2a.add(this.label2);
		
		this.serverModel2 = new SpinnerNumberModel(0, 0, 150, 1);
		this.serverNo2 = new JSpinner(this.serverModel2);
		this.serverNo2.setToolTipText("Number of server instances.");
		this.serverNo2.setEnabled(false);
		this.panel2a.add(this.serverNo2);
		
		this.spigotRestart = new JCheckBox("Restart Script");
		this.spigotRestart.setEnabled(false);
		this.panel2a.add(this.spigotRestart);
		
		this.spigotEula = new JCheckBox("Agree to EULA");
		this.spigotEula.setEnabled(false);
		this.panel2a.add(this.spigotEula);
		
		this.panel2b = new JPanel(new GridLayout(5, 2));
		this.panel2b.setSize(new Dimension(25, 30));
		this.panel2b.setEnabled(false);
		TitledBorder plugin2 = BorderFactory.createTitledBorder("Plugins Folder");
		this.panel2b.setBorder(plugin2);
		
		this.field2 = new JTextField();
		this.choose2 = new JButton("Select Folder");
		
		this.field2.setSize(new Dimension(500, 100));
		this.field2.setEnabled(false);
		this.choose2.setEnabled(false);
		this.choose2.addActionListener(this);
		
		this.panel2b.add(field2);
		this.panel2b.add(choose2);
		
		this.panel2.add(this.panel2a);
		this.panel2.add(this.panel2b);
		
		//Panel 3
		this.panel3 = new JPanel();
		this.panel3.setLayout(new GridLayout(1, 4));
		this.panel3.setSize(new Dimension(600, 100));
		TitledBorder border3 = BorderFactory.createTitledBorder("Bukkit Server Settings");
		this.panel3.setBorder(border3);
		this.panel3.setVisible(true);
		
		this.bukkitServerBox = new JCheckBox("Enabled?");
		this.bukkitServerBox.addItemListener(this);
		this.bukkitServerBox.setSelected(false);
		this.bukkitServerBox.setSize(new Dimension(10, 10));
		this.panel3.add(this.bukkitServerBox);
		
		this.panel3a = new JPanel(new GridLayout(5, 1));
		this.panel3a.setSize(new Dimension(25, 30));
		this.panel3a.setVisible(true);
		
		this.label3 = new JLabel("Server No.");
		this.label3.setEnabled(false);
		this.panel3a.add(this.label3);
		
		this.serverModel3 = new SpinnerNumberModel(0, 0, 150, 1);
		this.serverNo3 = new JSpinner(this.serverModel3);
		this.serverNo3.setToolTipText("Number of server instances.");
		this.serverNo3.setEnabled(false);
		this.panel3a.add(this.serverNo3);
		
		this.bukkitRestart = new JCheckBox("Restart Script");
		this.bukkitRestart.setEnabled(false);
		this.panel3a.add(this.bukkitRestart);
		
		this.bukkitEula = new JCheckBox("Agree To EULA");
		this.bukkitEula.setEnabled(false);
		this.panel3a.add(this.bukkitEula);
		
		this.panel3b = new JPanel(new GridLayout(5, 2));
		this.panel3b.setSize(new Dimension(40, 30));
		this.panel3b.setEnabled(false);
		this.panel3b.setVisible(true);
		TitledBorder plugin3 = BorderFactory.createTitledBorder("Plugins Folder");
		this.panel3b.setBorder(plugin3);
		
		this.field3 = new JTextField();
		this.choose3 = new JButton("Select Folder");
		
		this.field3.setSize(new Dimension(20, 10));
		this.field3.setEnabled(false);
		this.choose3.setEnabled(false);
		this.choose3.addActionListener(this);
		
		this.panel3b.add(field3);
		this.panel3b.add(choose3);
		
		this.panel3.add(this.panel3a);
		this.panel3.add(this.panel3b);
		
		//Panel 4
		this.panel4 = new JPanel();
		this.panel4.setLayout(new GridLayout(6, 1));
		this.panel4.setSize(new Dimension(600, 300));
		this.panel4.setVisible(true);
		
		this.panel4a = new JPanel(new BorderLayout());
		this.panel4a.setSize(new Dimension(25, 30));
		//TitledBorder dirTitle = BorderFactory.createTitledBorder("Main Dir.");
		//this.panel4a.setBorder(dirTitle);
		this.panel4a.setVisible(true);
		
		this.panel4b = new JPanel(new BorderLayout());
		this.panel4b.setSize(new Dimension(25, 30));
		//TitledBorder dirTitle1 = BorderFactory.createTitledBorder("BungeeCord Jar File");
		//this.panel4b.setBorder(dirTitle1);
		this.panel4b.setVisible(true);
		
		this.panel4c = new JPanel(new BorderLayout());
		this.panel4c.setSize(new Dimension(25, 30));
		//TitledBorder dirTitle2 = BorderFactory.createTitledBorder("Spigot Jar File");
		//this.panel4c.setBorder(dirTitle2);
		this.panel4c.setVisible(true);
		
		this.panel4d = new JPanel(new BorderLayout());
		this.panel4d.setSize(new Dimension(25, 30));
		//TitledBorder dirTitle3 = BorderFactory.createTitledBorder("Bukkit Jar File");
		//this.panel4d.setBorder(dirTitle3);
		this.panel4d.setVisible(true);
		
		this.panel4e = new JPanel(new BorderLayout());
		this.panel4e.setSize(new Dimension(25, 30));
		//TitledBorder dirTitle4 = BorderFactory.createTitledBorder("Port start");
		//this.panel4e.setBorder(dirTitle4);
		this.panel4e.setVisible(true);
		
		this.panel4f = new JPanel(new GridLayout(1, 4));
		this.panel4f.setSize(new Dimension(25, 30));
		this.panel4f.setVisible(true);
		
		this.field4 = new JTextField();
		this.field4a = new JTextField();
		this.field4b = new JTextField();
		this.field4c = new JTextField();
		this.field4d = new JTextField();
		this.choose4 = new JButton("Select Folder");
		this.choose4a = new JButton("Select File");
		this.choose4b = new JButton("Select File");
		this.choose4c = new JButton("Select File");
		this.l1 = new JLabel("BungeeCord jar");
		this.l2 = new JLabel("Spigot jar");
		this.l3 = new JLabel("Bukkit jar");
		this.l4 = new JLabel("Server port Start");
		this.l5 = new JLabel("Main Dir.");
		
		this.field4.setSize(new Dimension(500, 100));
		this.field4.setEditable(true);
		this.field4a.setEnabled(false);
		this.field4b.setEnabled(false);
		this.field4c.setEnabled(false);
		this.choose4a.setEnabled(false);
		this.choose4b.setEnabled(false);
		this.choose4c.setEnabled(false);
		l1.setEnabled(false);
		l2.setEnabled(false);
		l3.setEnabled(false);
		this.choose4.addActionListener(this);
		this.choose4a.addActionListener(this);
		this.choose4b.addActionListener(this);
		this.choose4c.addActionListener(this);
		
		this.panel4a.add(l5, BorderLayout.WEST);
		this.panel4a.add(this.field4, BorderLayout.CENTER);
		this.panel4a.add(this.choose4, BorderLayout.EAST);
		
		this.panel4b.add(l1, BorderLayout.WEST);
		this.panel4b.add(this.field4a, BorderLayout.CENTER);
		this.panel4b.add(this.choose4a, BorderLayout.EAST);
		
		this.panel4c.add(l2, BorderLayout.WEST);
		this.panel4c.add(this.field4b, BorderLayout.CENTER);
		this.panel4c.add(this.choose4b, BorderLayout.EAST);
		
		this.panel4d.add(l3, BorderLayout.WEST);
		this.panel4d.add(this.field4c, BorderLayout.CENTER);
		this.panel4d.add(this.choose4c, BorderLayout.EAST);
		
		this.panel4e.add(l4, BorderLayout.WEST);
		this.panel4e.add(this.field4d, BorderLayout.CENTER);
		
		this.deleteFiles = new JCheckBox("Delete Files?");
		this.deleteFiles.setSelected(true);
		this.panel4f.add(this.deleteFiles);
		
		this.lanuchButton = new JButton("Launch");
		this.lanuchButton.setPreferredSize(new Dimension(30, 30));
		this.lanuchButton.addActionListener(this);
		this.panel4f.add(this.lanuchButton);
		
		this.panel4.add(this.panel4a);
		this.panel4.add(this.panel4b);
		this.panel4.add(this.panel4c);
		this.panel4.add(this.panel4d);
		this.panel4.add(this.panel4e);
		this.panel4.add(this.panel4f);
			
		this.frame.getContentPane().add(this.panel1);
		this.frame.getContentPane().add(this.panel2);
		this.frame.getContentPane().add(this.panel3);
		this.frame.getContentPane().add(this.panel4);
		
		this.frame.setPreferredSize(new Dimension(700, 900));
		this.frame.setResizable(false);
		this.frame.pack();
		this.frame.setVisible(true);
		
	}
	
	@Override public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource() == choose1){
			new FolderChooserApp(this, this.panel1b, 1);
		}else if(arg0.getSource() == choose2){
			new FolderChooserApp(this, this.panel2b, 2);
		}else if(arg0.getSource() == choose3){
			new FolderChooserApp(this, this.panel3b, 3);
		}else if(arg0.getSource() == choose4){
			new FolderChooserApp(this, this.panel4a, 4);
		}else if(arg0.getSource() == lanuchButton){
			LaunchServersStart lss = new LaunchServersStart(this);
			lss.launch();
		}else if(arg0.getSource() == choose4a){
			new FileChooserApp(this, this.panel4b, 5);
		}else if(arg0.getSource() == choose4b){
			new FileChooserApp(this, this.panel4c, 6);
		}else if(arg0.getSource() == choose4c){
			new FileChooserApp(this, this.panel4d, 7);
		}
		
	}

	@Override public void itemStateChanged(ItemEvent e) {

        Object source = e.getItemSelectable();
        
        // Is bungee enabled?
        if (source == this.bungeeProxyBox) {
            if (e.getStateChange() == ItemEvent.DESELECTED) {
            	
            	this.serverNo1.setEnabled(false);
            	this.label1.setEnabled(false);
            	this.field1.setEnabled(false);
            	this.choose1.setEnabled(false);
            	this.panel1b.setEnabled(false);
            	this.panel1a.setEnabled(false);
            	this.bungeeRestart.setEnabled(false);
            	
            	this.field4a.setEnabled(false);
            	this.choose4a.setEnabled(false);
            	this.l1.setEnabled(false);
            	this.bungeeEula.setEnabled(false);
            	
               this.setBungeeEnabled(false);    
            }else{
            	
            	this.serverNo1.setEnabled(true);
            	this.label1.setEnabled(true);
            	this.field1.setEnabled(true);
            	this.choose1.setEnabled(true);
            	this.panel1b.setEnabled(true);
            	this.panel1a.setEnabled(true);
            	this.bungeeRestart.setEnabled(true);
            	
            	this.field4a.setEnabled(true);
            	this.choose4a.setEnabled(true);
            	this.l1.setEnabled(true);
            	this.bungeeEula.setEnabled(true);
            	
            	this.setBungeeEnabled(true);
            }
        }else if(source == this.spigotServerBox){
            if (e.getStateChange() == ItemEvent.DESELECTED) {
            	
            	this.serverNo2.setEnabled(false);
            	this.label2.setEnabled(false);
            	this.field2.setEnabled(false);
            	this.choose2.setEnabled(false);
            	this.panel2b.setEnabled(false);
            	this.panel2a.setEnabled(false);
            	this.spigotRestart.setEnabled(false);
            	
            	this.field4b.setEnabled(false);
            	this.choose4b.setEnabled(false);
            	this.l2.setEnabled(false);
            	this.spigotEula.setEnabled(false);
            	
                this.setSpigotEnabled(false);    
             }else{
            	 
             	this.serverNo2.setEnabled(true);
             	this.label2.setEnabled(true);
             	this.field2.setEnabled(true);
             	this.choose2.setEnabled(true);
             	this.panel2b.setEnabled(true);
             	this.panel2a.setEnabled(true);
             	this.spigotRestart.setEnabled(true); 
             	
            	this.field4b.setEnabled(true);
            	this.choose4b.setEnabled(true);
            	this.l2.setEnabled(true);
            	this.spigotEula.setEnabled(true);
             	
             	this.setSpigotEnabled(true);
             }
        }else if(source == this.bukkitServerBox){
            if (e.getStateChange() == ItemEvent.DESELECTED) {
            	
            	this.serverNo3.setEnabled(false);
            	this.label3.setEnabled(false);
            	this.field3.setEnabled(false);
            	this.choose3.setEnabled(false);
            	this.panel3b.setEnabled(false);
            	this.panel3a.setEnabled(false);
            	this.bukkitRestart.setEnabled(false);
            	
            	this.field4c.setEnabled(false);
            	this.choose4c.setEnabled(false);
            	this.l3.setEnabled(false);
            	this.bukkitEula.setEnabled(false);
            	
                this.setBukkitEnabled(false);    
             }else{
            	 
             	this.serverNo3.setEnabled(true);
             	this.label3.setEnabled(true);
             	this.field3.setEnabled(true);
             	this.choose3.setEnabled(true);
             	this.panel3b.setEnabled(true);
             	this.panel3a.setEnabled(true);
             	this.bukkitRestart.setEnabled(true); 
             	
            	this.field4c.setEnabled(true);
            	this.choose4c.setEnabled(true);
            	this.l3.setEnabled(true);
            	this.bukkitEula.setEnabled(true);
             	
             	this.setBukkitEnabled(true);
             }
        }else if(source == this.bungeeRestart){
        	if (e.getStateChange() == ItemEvent.DESELECTED) {
        		this.bungeeRestartScript = false;
        	}else{
        		this.bungeeRestartScript = true;
        	}
        }else if(source == this.spigotRestart){
        	if (e.getStateChange() == ItemEvent.DESELECTED) {
        		this.spigotRestartScript = false;
        	}else{
        		this.spigotRestartScript = true;
        	}
        }else if(source == this.bukkitRestart){
        	if (e.getStateChange() == ItemEvent.DESELECTED) {
        		this.bukkitRestartScript = false;
        	}else{
        		this.bukkitRestartScript = true;
        	}
        }else if(source == this.deleteFiles){
        	if (e.getStateChange() == ItemEvent.DESELECTED) {
        		this.setDeleteFile(false);
        	}else{
        		this.setDeleteFile(true);
        	}
        }

	}
	
	public boolean isBungeeEnabled() {
		return bungeeEnabled;
	}

	public void setBungeeEnabled(boolean bungeeEnabled) {
		this.bungeeEnabled = bungeeEnabled;
	}

	public boolean isSpigotEnabled() {
		return spigotEnabled;
	}

	public void setSpigotEnabled(boolean spigotEnabled) {
		this.spigotEnabled = spigotEnabled;
	}

	public boolean isBukkitEnabled() {
		return bukkitEnabled;
	}

	public void setBukkitEnabled(boolean bukkitEnabled) {
		this.bukkitEnabled = bukkitEnabled;
	}
	
	public boolean isBungeeRestartScript() {
		return bungeeRestartScript;
	}

	public void setBungeeRestartScript(boolean bungeeRestartScript) {
		this.bungeeRestartScript = bungeeRestartScript;
	}

	public boolean isSpigotRestartScript() {
		return spigotRestartScript;
	}

	public void setSpigotRestartScript(boolean spigotRestartScript) {
		this.spigotRestartScript = spigotRestartScript;
	}

	public boolean isBukkitRestartScript() {
		return bukkitRestartScript;
	}

	public void setBukkitRestartScript(boolean bukkitRestartScript) {
		this.bukkitRestartScript = bukkitRestartScript;
	}

	public int getBungeeServerNo(){
		return (int)this.serverModel1.getValue();
	}
	
	public int getSpigotServerNo(){
		return (int)this.serverModel2.getValue();
	}
	
	public int getBukkitServerNo(){
		return (int)this.serverModel3.getValue();
	}

	public boolean isDeleteFile() {
		return deleteFile;
	}

	public void setDeleteFile(boolean deleteFile) {
		this.deleteFile = deleteFile;
	}
	
}
